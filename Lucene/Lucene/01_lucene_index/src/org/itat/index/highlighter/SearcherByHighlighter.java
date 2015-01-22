package org.itat.index.highlighter;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.similar.MoreLikeThis;
import org.apache.lucene.search.vectorhighlight.FastVectorHighlighter;
import org.apache.lucene.search.vectorhighlight.FieldQuery;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;
import org.itat.index.smiple.IndexUtils;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class SearcherByHighlighter {
	
	public void searcherByFastHighlighter(String name) {
		try {
			Analyzer a = new MMSegAnalyzer();
			IndexSearcher searcher = new IndexSearcher(IndexReader.open(IndexUtils.getDirectory()));
			//QueryParser parser = new QueryParser(Version.LUCENE_35,"title",a);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, new String[]{"title","content"}, a);
			Query query = parser.parse(name);

			FastVectorHighlighter fvh = new FastVectorHighlighter(false,false);
			FieldQuery fq = fvh.getFieldQuery(query);
			
			TopDocs tds = searcher.search(query, 20);
			for(ScoreDoc sd:tds.scoreDocs) {
				String highTitle = fvh.getBestFragment(fq, searcher.getIndexReader(), sd.doc, "title", 100);
				System.out.println(highTitle);
				String highContent = fvh.getBestFragment(fq, searcher.getIndexReader(), sd.doc, "content", 100);
				System.out.println(highContent);
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searcherByHighlighter(String name) {
		try {
			Analyzer a = new MMSegAnalyzer();
			IndexSearcher searcher = new IndexSearcher(IndexReader.open(IndexUtils.getDirectory()));
			// QueryParser parser = new QueryParser(Version.LUCENE_35,"title",a);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, new String[]{"title","content"}, a);
			Query query = parser.parse(name);
			TopDocs tds = searcher.search(query, 20);
			
			MoreLikeThis mlt = new MoreLikeThis(searcher.getIndexReader());
			mlt.setFieldNames(new String[]{"title","content"});
			mlt.setMinDocFreq(1);
			mlt.setMinTermFreq(1);
			
			for(ScoreDoc sd:tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				String title = doc.get("title");
				title = lighterStr(a, query, title, "title");
				System.out.println(title);
				
				String content = new Tika().parseToString(new File(doc.get("path")));
				content = lighterStr(a, query, content, "content");
				System.out.println(content);
				
				Query moreLike = mlt.like(sd.doc);
				TopDocs stds = searcher.search(moreLike, 10);
				for(ScoreDoc ssd:stds.scoreDocs) {
					Document d = searcher.doc(ssd.doc);
					System.out.println(d.get("title"));
				}
				
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String lighterStr(Analyzer a,Query query,String txt,String fieldname) throws IOException, InvalidTokenOffsetsException {
		String str =  null;
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		Formatter fmt = new SimpleHTMLFormatter("<b>", "</b>");
		Highlighter lighter = new Highlighter(fmt, scorer);
		lighter.setTextFragmenter(fragmenter);
		str = lighter.getBestFragment(a, fieldname, txt);
		// str = lighter.getBestFragments(a.tokenStream(fieldname,new StringReader(txt)),txt, 3, "......\n");
		if(str==null)return txt;
		return str.trim();
	}
}
