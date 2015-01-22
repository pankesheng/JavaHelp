package service;

import bean.Index;
import bean.IndexField;
import bean.PageObject;

public interface IndexService {
	
	public void addIndex(IndexField fields,boolean inDatabase) throws Exception;
	
	public void deleteIndex(String id,String type) throws Exception;
	
	public void updateIndex(IndexField fields) throws Exception;
	
	public PageObject<Index> findByIndex(String condition) throws Exception;
	
	/**
	 * 提交索引,仅仅是从内存中提交
	 */
	public void updateCommitIndex() throws Exception;
	
	/**
	 * 重构索引
	 */
	public void updateReconstructorIndex() throws Exception;
	
	/**
	 * 设置索引，将数据库中没有添加为索引的对象完全添加
	 */
	public void updateSetIndex() throws Exception; 
	
}
