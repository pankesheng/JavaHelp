package org.itat.index.filter.custom;

public interface MyFilterAccessor {

	/** ȡ����Ҫ���˵�ֵ */
	public String[] getValues();
	
	/** ȡ����Ҫ�������� */
	public String getField();
	
	/** ���˵ļ�¼�Ƿ���ʾ */
	public boolean isSet();
}
