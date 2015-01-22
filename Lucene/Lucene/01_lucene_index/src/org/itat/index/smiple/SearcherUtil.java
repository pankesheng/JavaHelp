package org.itat.index.smiple;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class SearcherUtil {
	
	/** 索引的查询，不分页，(可排序) */
	public void searchByQueryParse(Query query, int num) throws Exception{
		
		/** Directory ==> IndexReader ==> IndexSearcher */
		IndexSearcher searcher = IndexUtils.getSearcher();

		/** Query + IndexSearcher ==> TopDocs */
		TopDocs tds = searcher.search(query, num);
				
//		TopDocs tds = searcher.search(query, MyFilterA.getFilter(), num);	// 简单过滤器功能
//		TopDocs tds = searcher.search(query, new MyFilter(new MyFilterAccessorImpl()), num);	// 自定义过滤器功能
//		TopDocs tds = searcher.search(MyQueryA.getQueryA(), num);	// 简单条件查询
//		TopDocs tds = searcher.search(MyQueryC.getQuery(), num);	// 自定义QueryParser
//		TopDocs tds = searcher.search(MyQueryB.getQuery(), num);	// 根据域修改评分后的查询
//		TopDocs tds = searcher.search(query, num, MySort.getSort());	// 排序功能
		
		/** TopDocs ==> ScoreDoc */
		for (ScoreDoc sd : tds.scoreDocs) {
			/** ScoreDoc + IndexSearcher ==> Document */
			Document doc = searcher.doc(sd.doc);
			System.out.println(doc.get("title"));
		}
		searcher.close();
	}
	
	/** 索引的查询，分页 */
	public void searchByQueryParse2(Query query, int pageIndex, int pageSize) throws Exception{
		
		IndexSearcher searcher = IndexUtils.getSearcher();
		
		/** 先获取上一页的最后一个元素 */
		ScoreDoc lastSd = null;
		if (pageIndex != 1) {
			int num = pageSize * (pageIndex - 1);
			TopDocs tdsTemp = searcher.search(query, num);
			lastSd =  tdsTemp.scoreDocs[num - 1];
		}
		
		/** 通过最后一个元素搜索下页的pageSize个元素 */
		TopDocs tds = searcher.searchAfter(lastSd, query, pageSize);
		
		for (ScoreDoc sd : tds.scoreDocs) {
			Document doc = searcher.doc(sd.doc);
			System.out.println(doc.get("id"));
		}
		searcher.close();
	}
}
