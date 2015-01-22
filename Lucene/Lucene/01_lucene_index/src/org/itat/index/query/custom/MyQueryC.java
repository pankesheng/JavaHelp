package org.itat.index.query.custom;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class MyQueryC {

	/** 
	 * 设置query
	 * 功能1、禁用影响性能的查询方式
	 * 功能2、扩展不同字段类型的区域查询
	 */
	public static Query getQuery() throws ParseException {
		MyQueryParser queryParser = new MyQueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
		Query query = queryParser.parse("lo?ve");
		query = queryParser.parse("attach:[2 TO 4]");
		query = queryParser.parse("date:[2011-12-02 TO 2013-10-02]");
		query = queryParser.parse("content:[a TO j]");
		return query;
	}
}
