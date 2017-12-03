package com.github.jees5555.huidaoBookShop.service.impl;

import java.util.List;

import com.github.jees5555.huidaoBookShop.dao.BookDao;
import com.github.jees5555.huidaoBookShop.dao.impl.BookDaoImpl;
import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.service.BookService;
import com.github.jees5555.huidaoBookShop.util.Page;

public class BookServiceImpl implements BookService{
	private BookDao bd=new BookDaoImpl();
	@Override
	public int findAllRecords(String keywords) throws Exception {
		return bd.findAllRecords(keywords);
	}

	@Override
	public Page showPageRecords(String pagenum,String keywords)  throws Exception{
		// TODO 业务
		int num=1;
		if(pagenum!=null){
			num=Integer.parseInt(pagenum);
		}
		int totalRecords=bd.findAllRecords(keywords);
		Page<Book> page=new Page<>(num,totalRecords);
		
		List<Book> books=bd.showPageRecords(page.getStartIndex(), page.getPagesize(),keywords);
		page.setRecords(books);
		return page;
	}
	
	
	@Override
	public int add(Book t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Book t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Book t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Book findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
