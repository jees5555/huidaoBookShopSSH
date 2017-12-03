package com.github.jees5555.huidaoBookShop.service;

import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.util.Page;


public interface BookService extends BaseService<Book>{
		/**
		 * 分页显示
		 * select * from books limit 0,3;
		 */
	Page showPageRecords(String pagenum,String keywords) throws Exception;
	
}
