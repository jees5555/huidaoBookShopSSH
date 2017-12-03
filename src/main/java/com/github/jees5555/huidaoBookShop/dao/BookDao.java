package com.github.jees5555.huidaoBookShop.dao;

import java.sql.SQLException;
import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.Book;


public interface BookDao extends BaseDao<Book>{
		/**
		 * 分页显示
		 * select * from books limit 0,3;
		 * @throws SQLException 
		 */
	List<Book> showPageRecords(int startnum,int pagesize,String keywords) throws SQLException;

		int updateBookStock(Book book) throws SQLException;
}
