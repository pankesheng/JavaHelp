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
		query = new TermRangeQuery("content", "A", "Z", true, false); // ���ַ���������A,������Z.
		query = NumericRangeQuery.newIntRange("size", 2, 4, true, true); // ������
		query = new PrefixQuery(new Term("name", "z"));// ����field��value��ͷ������
		query = new WildcardQuery(new Term("email", "*@si?n.org"));// ͨ���������?��ʾƥ��һ���ַ���*��ʾƥ���������ַ�
		// BooleanQuery query = new BooleanQuery();// ����������
			//query.add(new TermQuery(new Term("name","zhangsan")), Occur.MUST_NOT);
			//query.add(new TermQuery(new Term("content","game")),Occur.MUST);
		// PhraseQuery�����ѯ	FuzzyQueryģ����ѯ
		return query;
	}
	
	public static Query getQueryC() throws Exception {
		// �������
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, new String[]{"title","content"}, new StandardAnalyzer(Version.LUCENE_35));
		return parser.parse("love");
	}
	
	public static Query getQueryB() throws Exception {
		//1������QueryParser����,Ĭ��������Ϊcontent
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
		
		
		//�ı�ո��Ĭ�ϲ����������¿��Ըĳ�AND
		parser.setDefaultOperator(Operator.AND);
		
		//����content�а�����love��
		Query query = parser.parse("love");
		
		//��basketball����football�ģ��ո�Ĭ�Ͼ���OR
		query = parser.parse("basketball football");
		
		//�ı�������Ϊname,��ѯ��Ϊlike
		query = parser.parse("name:like");
		
		//ͬ������ʹ��*��?������ͨ���ƥ��
		query = parser.parse("name:j*");
		
		//������һ���ַ���ͨ���ƥ�䣬Ĭ�Ϲر���ΪЧ�ʲ���
		parser.setAllowLeadingWildcard(true);
		//ͨ���Ĭ�ϲ��ܷ�����λ
		query = parser.parse("email:*@itat.org");
		
		//ƥ��name��û��mike����content�б�����football�ģ�+��-Ҫ���õ���˵��ǰ��
		query = parser.parse("- name:mike + like");
		
		//ƥ��һ�����䣬ע��:TO�����Ǵ�д
		query = parser.parse("id:[1 TO 6]");
		
		//������ƥ��ֻ��ƥ�䵽2
		query = parser.parse("id:{1 TO 3}");
		
		//��ȫƥ��I Like Football��
		query = parser.parse("\"I like football\"");
		
		//ƥ��I ��football֮����һ�����ʾ����
		query = parser.parse("\"I football\"~1");
		
		//ģ����ѯ
		query = parser.parse("name:make~");
		
		return query;
	}
}
