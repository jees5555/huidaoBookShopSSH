package com.github.jees5555.huidaoBookShop.util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
/**
 * 得到连接及事务有关的方法
 * 我擦那个：杭州
 * @author Administrator
 *上午8:13:52
 */
public class TransactionUtil {
	//同一个线程共享一个资源
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private static DataSource dataSource= DBCPUtil.getDataSource();
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection(){
		try {
			Connection conn = tl.get();
			if(conn==null){
				conn = dataSource.getConnection();
				tl.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void startTransaction(){
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void rollback(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void commit(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void close(){
		try {
			getConnection().close();
			tl.remove();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
