package org.itat.index.query.scoreQuery;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class MyQueryB {

	/**
	 * 根据域修改评分，返回一个Query对象供查询
	 */
	public static Query getQuery() {
		// 查询条件
		Query q = new TermQuery(new Term("content","java"));
		// 创建一个评分域
		MyCustomScoreQuery query = new MyCustomScoreQuery(q);
		return query;
	}
}
