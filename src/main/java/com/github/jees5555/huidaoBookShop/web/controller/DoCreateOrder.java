package com.github.jees5555.huidaoBookShop.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.OrderService;
import com.github.jees5555.huidaoBookShop.service.impl.OrderServiceImpl;

@WebServlet("/order/create")
public class DoCreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService os=new OrderServiceImpl();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bidlist");
		if(bid!=null &&! "".equals(bid)){
			List <Integer> cartlist =new ArrayList<>();
			cartlist=Arrays.stream(bid.split(",")).filter(e->!e.equals("")).map(Integer::parseInt).collect(Collectors.toList());
			User user =new User();
			Integer uid=(Integer) request.getSession().getAttribute("uid");
			String username=(String)request.getSession().getAttribute("username");
			if(uid ==null || username==null){
				throw new ServletException();
			}
			user.setUid(uid);
			user.setUsername(username);
			int result=os.createOrder(cartlist,user);
			if(result==0){
				request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/pages/createorder_success.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
		
	}



}