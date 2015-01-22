package org.itat.index.query.custom;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

/**
 * ��չQueryParser
 * ����1������Ӱ�����ܵĲ�ѯ��ʽ
 * ����2����չ��ͬ�ֶ����͵������ѯ
 * @author ZCJ
 * @data 2012-5-16
 */
public class MyQueryParser extends QueryParser {

	public MyQueryParser(Version matchVersion, String f, Analyzer a) {
		super(matchVersion, f, a);
	}
	
	@Override
	protected Query getWildcardQuery(String field, String termStr) throws ParseException {
		throw new ParseException("��������ԭ���Ѿ�������ͨ�����ѯ�����������ȷ����Ϣ���в�ѯ");
	}
	
	@Override
	protected Query getFuzzyQuery(String field, String termStr, float minSimilarity) throws ParseException {
		throw new ParseException("��������ԭ���Ѿ�������ģ����ѯ�����������ȷ����Ϣ���в�ѯ");
	}
	
	@Override
	protected Query getRangeQuery(String field, String part1, String part2, boolean inclusive) throws ParseException {
		if(field.equals("attach")) {	// ����int�����ֶ������ѯ
			return NumericRangeQuery.newIntRange(field,Integer.parseInt(part1), Integer.parseInt(part2), inclusive, inclusive);
		} else if(field.equals("date")) {	// ����long�����ֶ������ѯ��������ʱ��β�ѯ��
			Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
			if(pattern.matcher(part1).matches()&&pattern.matcher(part2).matches()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					long start = sdf.parse(part1).getTime();
					long end = sdf.parse(part2).getTime();
					return NumericRangeQuery.newLongRange(field, start, end, inclusive, inclusive);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			} else {
				throw new ParseException("Ҫ���������ڸ�ʽ����ȷ");
			}
		}
		return super.newRangeQuery(field, part1, part2, inclusive);	// ����string���������ѯ
	}
}
