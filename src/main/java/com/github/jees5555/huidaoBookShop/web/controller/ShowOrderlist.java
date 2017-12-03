package com.github.jees5555.huidaoBookShop.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.OrderService;
import com.github.jees5555.huidaoBookShop.service.impl.OrderServiceImpl;
import com.github.jees5555.huidaoBookShop.util.Page;

@WebServlet("/order/orderlist")
public class ShowOrderlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService os=new OrderServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			
		String pagenum=request.getParameter("page");
		String keywords=request.getParameter("keywords");
		String history=request.getParameter("history");
		Integer uid= (Integer) request.getSession().getAttribute("uid");
		if(uid==null){
			throw new ServletException();
		}
		Page page;
		User user =new User();
		user.setUid(uid);
		try {
			page = os.showOrderList(user,pagenum,keywords,history);
		
			request.setAttribute("page", page);
			request.setAttribute("keywords", keywords);
			request.setAttribute("history", history);
			request.setAttribute("model", "order/orderlist");
			request.getRequestDispatcher("/WEB-INF/pages/orderlist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}