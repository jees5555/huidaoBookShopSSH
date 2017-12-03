package com.github.jees5555.huidaoBookShop.service;

import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.User;

/**
 * 
 * 杭州汇到：T  type  E  element  K  key   V   value
 * 描述:定义增删改查的接口
 *	@author Administrator
 *	
 */
public interface BaseService<T>{
	/**
	 * 增加
	 */
	default int add(T t) throws Exception{
		throw new Exception("add method is not avaliable");
	};
	/**
	 * 删除
	 */
	default int del(T t) throws Exception{
		throw new Exception("del method is not avaliable");
	};
	/**
	 * 更新
	 */
	default int update(T t) throws Exception{
		throw new Exception("update method is not avaliable");
	};
	/**
	 * 查询 1个
	 */
	default T findById(int id) throws Exception{
		throw new Exception("findbyid method is not avaliable");
	};
	/**
	 * 查询一堆
	 */
	default List<T> findAll() throws Exception{
		throw new Exception("findall method is not avaliable");
	};
	
	/**
	 * 获取信息的总条数
	 */
	default int findAllRecords(String keywords) throws Exception{
		throw new Exception("findallrecords method is not avaliable");
	};
}