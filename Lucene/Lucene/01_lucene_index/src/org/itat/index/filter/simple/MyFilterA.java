package org.itat.index.filter.simple;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;
import org.itat.index.query.simple.MyQueryA;

public class MyFilterA {

	public static Filter getFilter() {
		Filter filter = new TermRangeFilter("name", "jake", "john", true, false);	// �ַ������͹��ˣ�����name�ֶε�ֵΪjake��john�ļ�¼������jake��������john
		filter = NumericRangeFilter.newIntRange("attach", 2, 4, true, true);		// �������͹��ˣ�...
		filter = new QueryWrapperFilter(MyQueryA.getQueryA());	// ͨ��query���й���
		return filter;
	}
}
