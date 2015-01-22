package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
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
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import util.LuceneContext;
import bean.Index;
import bean.IndexField;
import bean.PageObject;

public class IndexServiceLuceneImpl implements IndexService {
	
	@Override
	public void addIndex(IndexField fields, boolean inDatabase) throws Exception {
		if (inDatabase) {
			//TODO 保存到TempIndex表中
		}
		NRTManager nrtMgr = LuceneContext.getInstance().getNRTManager();
		Document doc = field2Doc(fields);
		nrtMgr.addDocument(doc);
	}

	@Override
	public void deleteIndex(String id, String type) throws Exception {
		//TODO 保存到TempIndex表中
		LuceneContext.getInstance().getNRTManager().deleteDocuments(new Term("id", id));
	}

	@Override
	public void updateIndex(IndexField fields) throws Exception {
		//TODO 保存到TempIndex表中
		NRTManager nrtMgr = LuceneContext.getInstance().getNRTManager();
		Document doc = field2Doc(fields);
		nrtMgr.updateDocument(new Term("id",fields.getId()), doc);
	}
	
	@Override
	public void updateCommitIndex() {
		//TODO 清空TempIndex表
		LuceneContext.getInstance().commitIndex();
	}

	@Override
	public void updateReconstructorIndex() throws Exception {
		LuceneContext.getInstance().getNRTManager().deleteAll();
		//TODO 查出所有的留言数据，转成IndexField类型，调用addIndex方法
		LuceneContext.getInstance().commitIndex();
		//TODO 清空TempIndex表
	}
	
	@Override
	public void updateSetIndex() {
		//TODO 查出所有的TempIndex数据，查出对应的message，转成IndexField类型，调用addIndex方法
		LuceneContext.getInstance().commitIndex();
		//TODO 清空TempIndex表
	}
	
	private String highligher(String text,Query query,String field) {
		try {
			QueryScorer scorer = new QueryScorer(query);
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			Formatter formatter = new SimpleHTMLFormatter("<span class='lighter'>","</span>");
			Highlighter lighter = new Highlighter(formatter,scorer);
			lighter.setTextFragmenter(fragmenter);
			String ht = lighter.getBestFragment(LuceneContext.getInstance().getAnalyzer(),
					field,text);
			if(ht==null) {
				if(text.length()>=200) {
					text = text.substring(0, 200);
					text=text+"....";
				}
				return text;
			}
			else return ht.trim();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return text;
	}

	@Override
	public PageObject<Index> findByIndex(String condition) {
		if(condition==null) condition = "";
		IndexSearcher searcher = LuceneContext.getInstance().getSearcher();
		PageObject<Index> pages = new PageObject<Index>();
		List<Index> datas = new ArrayList<Index>();
		try {
			int pageSize = 15;
			int pageOffset = 1;
			MultiFieldQueryParser parser = new MultiFieldQueryParser(LuceneContext.getInstance().getVersion(),
						new String[]{"title","content"}, LuceneContext.getInstance().getAnalyzer());
			Query query = parser.parse(condition);
			TopDocs tds = searcher.searchAfter(getLastDoc(pageOffset,searcher,query),
						  query, pageSize);
			int totalRecord = tds.totalHits;
			List<Integer> msgs = new ArrayList<Integer>();
			for(ScoreDoc sd:tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				Index index = new Index();
				index.setCreateDate(new Date(Long.parseLong(doc.get("createDate"))));
				String title = doc.get("title");
				index.setTitle(highligher(title,query,"title"));
				String[] ans = doc.getValues("atts");
				StringBuffer content = new StringBuffer();
				if(ans!=null) {
					for(String fn:ans) {
						content.append(file2String(fn));
					}
				}
				index.setSummary(content.toString());
				int msgId = Integer.parseInt(doc.get("id"));
				index.setMsgId(msgId);
				msgs.add(msgId);
				datas.add(index);
			}
			for(int i=0;i<datas.size();i++) {
				Index index = datas.get(i);
				datas.get(i).setSummary(highligher(index.getSummary(),query,"content"));
			}
			
			pages.setDatas(datas);
			pages.setOffset(pageOffset);
			pages.setPageSize(pageSize);
			pages.setTotalRecord(totalRecord);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		} finally {
			LuceneContext.getInstance().releaseSearcher(searcher);
		}
		return pages;
	}
	
	private ScoreDoc getLastDoc(int pageOffset,IndexSearcher searcher,Query query) {
		if(pageOffset<=0) return null;
		try {
			TopDocs tds = searcher.search(query,pageOffset-1);
			return tds.scoreDocs[pageOffset-1];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Document field2Doc(IndexField field) {
		Document doc = new Document();
		doc.add(new Field("id",field.getId(),Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
		doc.add(new Field("title",field.getTitle(),Field.Store.YES,Field.Index.ANALYZED));
		for(String content:field.getContent()) {
			doc.add(new Field("content",content,Field.Store.NO,Field.Index.ANALYZED));
		}
		if(field.getAtths()!=null) {
			for(String att:field.getAtths()) {
				doc.add(new Field("atts",att,Field.Store.YES,Field.Index.NO));
			}
		}
		doc.add(new NumericField("objId",Field.Store.YES,true).setIntValue(field.getObjId()));
		doc.add(new NumericField("createDate",Field.Store.YES,true).setLongValue(field.getCreateDate().getTime()));
		return doc;
	}

	private String file2String(String name) throws IOException, TikaException {
		String path = "";//TODO 文件真实路径
		return new Tika().parseToString(new File(path));
	}
}
