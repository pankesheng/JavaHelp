package org.itat.index.sort.simple;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

/**
 * ��������
 * @author ZCJ
 * @data 2012-5-17
 */
public class MySort {

	public static Sort getSort() {
		Sort sort = Sort.RELEVANCE;	// �����ڲ���������
		sort = new Sort(SortField.FIELD_SCORE);	// �����ڲ���������
		sort = new Sort(new SortField("attach", SortField.INT));		// ������������
		sort = new Sort(new SortField("date", SortField.LONG));			// ������������
		sort = new Sort(new SortField("name", SortField.STRING));		// �ַ�����������
		sort = new Sort(new SortField("name", SortField.STRING, true));	// �ַ����������򣬽���
		sort = new Sort(new SortField("name", SortField.STRING), SortField.FIELD_SCORE);	// ��������������ȸ���name�����ٸ����ڲ���������
		return sort;
	}
}
