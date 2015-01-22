package org.itat.index.filter.custom;

public interface MyFilterAccessor {

	/** 取得需要过滤的值 */
	public String[] getValues();
	
	/** 取得需要操作的域 */
	public String getField();
	
	/** 过滤的记录是否显示 */
	public boolean isSet();
}
