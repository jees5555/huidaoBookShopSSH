package com.github.jees5555.huidaoBookShop.web.controller;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.BookService;
import com.github.jees5555.huidaoBookShop.service.CartService;
import com.github.jees5555.huidaoBookShop.service.impl.BookServiceImpl;
import com.github.jees5555.huidaoBookShop.service.impl.CartServiceImpl;
import com.github.jees5555.huidaoBookShop.vo.CartVo;

@WebServlet("/cart/cartlist")
public class ShowCartlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cs = new CartServiceImpl();
	private BookService bs=new BookServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			List<CartVo>  list=null;
			String keywords=request.getParameter("keywords");
			try{
				HttpSession session =request.getSession();
				Integer uid =(Integer) session.getAttribute("uid");
				User user =new User();
				user.setUid(uid);
				list=cs.findCartByUid(user,keywords);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			request.setAttribute("cartlist", list);
			request.setAttribute("model", "cart/cartlist");
			request.getRequestDispatcher("/WEB-INF/pages/cartlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}