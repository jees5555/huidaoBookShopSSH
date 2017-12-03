package com.github.jees5555.huidaoBookShop.test;

import org.junit.Test;

import com.github.jees5555.huidaoBookShop.service.BookService;
import com.github.jees5555.huidaoBookShop.service.impl.BookServiceImpl;
import com.github.jees5555.huidaoBookShop.util.Page;

public class TestService {
	
	@Test
	public void  test1() throws Exception{
		BookService bs=new BookServiceImpl();
		//client
		String pagenum="3";//service
		Page p=bs.showPageRecords(pagenum,null);
		
		//	servlet - setattribre("page",page)--jsp
		
	
		
	}

}
