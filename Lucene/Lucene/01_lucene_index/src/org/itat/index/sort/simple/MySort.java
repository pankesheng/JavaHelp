package org.itat.index.sort.simple;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

/**
 * 设置排序
 * @author ZCJ
 * @data 2012-5-17
 */
public class MySort {

	public static Sort getSort() {
		Sort sort = Sort.RELEVANCE;	// 根据内部评分排序
		sort = new Sort(SortField.FIELD_SCORE);	// 根据内部评分排序
		sort = new Sort(new SortField("attach", SortField.INT));		// 数字类型排序
		sort = new Sort(new SortField("date", SortField.LONG));			// 日期类型排序
		sort = new Sort(new SortField("name", SortField.STRING));		// 字符串类型排序
		sort = new Sort(new SortField("name", SortField.STRING, true));	// 字符串类型排序，降序
		sort = new Sort(new SortField("name", SortField.STRING), SortField.FIELD_SCORE);	// 多个排序条件：先根据name升序，再根据内部评分升序
		return sort;
	}
}
