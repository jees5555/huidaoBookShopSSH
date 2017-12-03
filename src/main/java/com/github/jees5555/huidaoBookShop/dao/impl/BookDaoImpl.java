package com.github.jees5555.huidaoBookShop.dao.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.github.jees5555.huidaoBookShop.dao.BookDao;
import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.exception.Myexception;
import com.github.jees5555.huidaoBookShop.util.DBCPUtil;
import com.github.jees5555.huidaoBookShop.util.TransactionUtil;

public class BookDaoImpl implements BookDao{
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public int findAllRecords(String keywords) throws SQLException {
		String sql;
		Long rec =null;
		if(keywords==null ||"".equals(keywords)){
			sql="select count(*) from book";
			rec = qr.query(sql, new ScalarHandler<Long>());
		}else{
			keywords="%"+keywords+"%";
			sql="select count(*) from book where bookname like ?";
			rec = qr.query(sql, new ScalarHandler<Long>(),keywords);
		}
	
	
			
			return rec==null?0:rec.intValue();
		
	}
	@Override
	public int updateBookStock(Book book) throws SQLException {
		String sql="update book set stock=? where bid=?";
		return qr.update(TransactionUtil.getConnection(),sql,book.getStock(),book.getBid());
		
	}

	@Override
	public List<Book> showPageRecords(int startnum, int pagesize,String keywords) throws SQLException {	
			if(keywords==null ||"".equals(keywords)){
				return qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class),startnum,pagesize);
			}else{
				keywords="%"+keywords+"%";
				return qr.query("select * from book where bookname like ? limit ?,?", new BeanListHandler<Book>(Book.class),keywords,startnum,pagesize);
			}
		
	}
	
	@Override
	public Book findById(int bid) throws Exception {
		String sql ="select * from book where bid=?";
		return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
	}


	

}
