package org.itat.index.query.simple;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;

public class MyQueryA {
	
	public static Query getQueryA() {
		Query query = new TermQuery(new Term("content", "like"));
		query = new TermRangeQuery("content", "A", "Z", true, false); // 查字符串：包含A,不包含Z.
		query = NumericRangeQuery.newIntRange("size", 2, 4, true, true); // 查数字
		query = new PrefixQuery(new Term("name", "z"));// 根据field以value开头的搜索
		query = new WildcardQuery(new Term("email", "*@si?n.org"));// 通配符搜索：?表示匹配一个字符，*表示匹配任意多个字符
		// BooleanQuery query = new BooleanQuery();// 多条件搜索
			//query.add(new TermQuery(new Term("name","zhangsan")), Occur.MUST_NOT);
			//query.add(new TermQuery(new Term("content","game")),Occur.MUST);
		// PhraseQuery短语查询	FuzzyQuery模糊查询
		return query;
	}
	
	public static Query getQueryC() throws Exception {
		// 多域查找
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, new String[]{"title","content"}, new StandardAnalyzer(Version.LUCENE_35));
		return parser.parse("love");
	}
	
	public static Query getQueryB() throws Exception {
		//1、创建QueryParser对象,默认搜索域为content
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
		
		
		//改变空格的默认操作符，以下可以改成AND
		parser.setDefaultOperator(Operator.AND);
		
		//搜索content中包含有love的
		Query query = parser.parse("love");
		
		//有basketball或者football的，空格默认就是OR
		query = parser.parse("basketball football");
		
		//改变搜索域为name,查询字为like
		query = parser.parse("name:like");
		
		//同样可以使用*和?来进行通配符匹配
		query = parser.parse("name:j*");
		
		//开启第一个字符的通配符匹配，默认关闭因为效率不高
		parser.setAllowLeadingWildcard(true);
		//通配符默认不能放在首位
		query = parser.parse("email:*@itat.org");
		
		//匹配name中没有mike但是content中必须有football的，+和-要放置到域说明前面
		query = parser.parse("- name:mike + like");
		
		//匹配一个区间，注意:TO必须是大写
		query = parser.parse("id:[1 TO 6]");
		
		//闭区间匹配只会匹配到2
		query = parser.parse("id:{1 TO 3}");
		
		//完全匹配I Like Football的
		query = parser.parse("\"I like football\"");
		
		//匹配I 和football之间有一个单词距离的
		query = parser.parse("\"I football\"~1");
		
		//模糊查询
		query = parser.parse("name:make~");
		
		return query;
	}
}
