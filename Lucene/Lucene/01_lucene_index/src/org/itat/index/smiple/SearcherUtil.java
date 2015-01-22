package org.itat.index.smiple;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class SearcherUtil {
	
	/** �����Ĳ�ѯ������ҳ��(������) */
	public void searchByQueryParse(Query query, int num) throws Exception{
		
		/** Directory ==> IndexReader ==> IndexSearcher */
		IndexSearcher searcher = IndexUtils.getSearcher();

		/** Query + IndexSearcher ==> TopDocs */
		TopDocs tds = searcher.search(query, num);
				
//		TopDocs tds = searcher.search(query, MyFilterA.getFilter(), num);	// �򵥹���������
//		TopDocs tds = searcher.search(query, new MyFilter(new MyFilterAccessorImpl()), num);	// �Զ������������
//		TopDocs tds = searcher.search(MyQueryA.getQueryA(), num);	// ��������ѯ
//		TopDocs tds = searcher.search(MyQueryC.getQuery(), num);	// �Զ���QueryParser
//		TopDocs tds = searcher.search(MyQueryB.getQuery(), num);	// �������޸����ֺ�Ĳ�ѯ
//		TopDocs tds = searcher.search(query, num, MySort.getSort());	// ������
		
		/** TopDocs ==> ScoreDoc */
		for (ScoreDoc sd : tds.scoreDocs) {
			/** ScoreDoc + IndexSearcher ==> Document */
			Document doc = searcher.doc(sd.doc);
			System.out.println(doc.get("title"));
		}
		searcher.close();
	}
	
	/** �����Ĳ�ѯ����ҳ */
	public void searchByQueryParse2(Query query, int pageIndex, int pageSize) throws Exception{
		
		IndexSearcher searcher = IndexUtils.getSearcher();
		
		/** �Ȼ�ȡ��һҳ�����һ��Ԫ�� */
		ScoreDoc lastSd = null;
		if (pageIndex != 1) {
			int num = pageSize * (pageIndex - 1);
			TopDocs tdsTemp = searcher.search(query, num);
			lastSd =  tdsTemp.scoreDocs[num - 1];
		}
		
		/** ͨ�����һ��Ԫ��������ҳ��pageSize��Ԫ�� */
		TopDocs tds = searcher.searchAfter(lastSd, query, pageSize);
		
		for (ScoreDoc sd : tds.scoreDocs) {
			Document doc = searcher.doc(sd.doc);
			System.out.println(doc.get("id"));
		}
		searcher.close();
	}
}
