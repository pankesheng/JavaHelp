package org.itat.index.query.scoreQuery;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class MyQueryB {

	/**
	 * �������޸����֣�����һ��Query���󹩲�ѯ
	 */
	public static Query getQuery() {
		// ��ѯ����
		Query q = new TermQuery(new Term("content","java"));
		// ����һ��������
		MyCustomScoreQuery query = new MyCustomScoreQuery(q);
		return query;
	}
}
