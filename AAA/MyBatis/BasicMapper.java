package com.xt.clue.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BasicMapper <T, ID extends Serializable> {

	String OBJECT = "object";
	String ID = "id";
	String IDS = "ids";
	String CODE = "code";
	String ORDERBY = "orderby";
	String START = "start";
	String SIZE = "size";
	String QBUILDER = "qbuilder";

	/**
	 * 保存数据到指定语种表
	 */
	void insert(@Param(OBJECT) T object);

	/**
	 * 修改数据到指定语种表
	 */
	void update(@Param(OBJECT) T object);

	/**
	 * 根据id字段在指定语种表中删除数据
	 */
	void delete(@Param(ID) ID id);

	/**
	 * 根据id字段集合在指定语种表中删除数据
	 */
	void deleteByIds(@Param(IDS) Collection<ID> ids);

	/**
	 * 根据id字段在指定语种表中查询数据
	 */
	T findById(@Param(ID) ID id);

	/**
	 * 查询指定语种表中的记录
	 */
	List<T> find(@Param(QBUILDER) Map<String, Object> qbuilder, @Param(START) Integer start, @Param(SIZE) Integer size, @Param(ORDERBY) String orderby);

	/**
	 * 在指定语种表中查询总记录数
	 */
	int getTotalRows(@Param(QBUILDER) Map<String, Object> qbuilder);

}