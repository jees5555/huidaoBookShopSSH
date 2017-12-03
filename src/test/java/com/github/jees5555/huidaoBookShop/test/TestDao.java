package com.github.jees5555.huidaoBookShop.test;

import java.util.List;

import org.junit.Test;

import com.github.jees5555.huidaoBookShop.dao.BookDao;
import com.github.jees5555.huidaoBookShop.dao.impl.BookDaoImpl;
import com.github.jees5555.huidaoBookShop.entity.Book;

public class TestDao {
		
	//jnuit 4
	@Test
	public void testBookDao() throws Exception{
		BookDao bd=new BookDaoImpl();//多态
		int a=bd.findAllRecords(null);
		List<Book> books=bd.showPageRecords(0, 3, null);
		System.out.println(books);
		
	}
	
	
}
