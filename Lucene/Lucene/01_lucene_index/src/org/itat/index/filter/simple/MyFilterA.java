package org.itat.index.filter.simple;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;
import org.itat.index.query.simple.MyQueryA;

public class MyFilterA {

	public static Filter getFilter() {
		Filter filter = new TermRangeFilter("name", "jake", "john", true, false);	// 字符串类型过滤：过滤name字段的值为jake到john的记录，包含jake，不包含john
		filter = NumericRangeFilter.newIntRange("attach", 2, 4, true, true);		// 数字类型过滤：...
		filter = new QueryWrapperFilter(MyQueryA.getQueryA());	// 通过query进行过滤
		return filter;
	}
}
