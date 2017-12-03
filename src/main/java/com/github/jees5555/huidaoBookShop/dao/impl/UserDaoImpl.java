package com.github.jees5555.huidaoBookShop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.github.jees5555.huidaoBookShop.dao.UserDao;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.exception.Myexception;
import com.github.jees5555.huidaoBookShop.util.DBCPUtil;
/**
 * public   protected 默认friendly  private
 * 所有		子类			本包			本类
 * 杭州汇到：
 * 描述:
 *	@author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public User login(User user) throws SQLException {
		String sql ="select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
	}
	
	@Override
	public int findUserByName(String username) throws Exception {
		String sql ="select count(*) from user where username=?";
		return  qr.query(sql, new ScalarHandler<Long>(), username).intValue();
	}
	
	@Override
	public int add(User user) throws SQLException {
		String sql ="insert into user values(null,?,?,?)";
		return qr.update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

	@Override
	public User findByName(String name) throws SQLException{
		String sql ="select * from user where username=?";
		return qr.query(sql, new BeanHandler<User>(User.class), name);
	}
	
	

}
