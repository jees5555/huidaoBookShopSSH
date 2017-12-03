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
import com.github.jees5555.huidaoBookShop.service.CartService;
import com.github.jees5555.huidaoBookShop.service.impl.CartServiceImpl;

/**
 * Servlet implementation class DelCart
 */
@WebServlet("/cart/delete")
public class DelCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cs =new CartServiceImpl(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid=request.getParameter("bid");
		if(bid!=null ||! "".equals(bid)){
			List <Integer> cartlist =new ArrayList<>();
			cartlist=Arrays.stream(bid.split(",")).filter(e->!e.equals("")).map(Integer::parseInt).collect(Collectors.toList());
			User user =new User();
			Integer uid=(Integer) request.getSession().getAttribute("uid");
			if(uid ==null){
				throw new ServletException();
			}
			user.setUid(uid);		
			int result=cs.deleteCart(cartlist,user);
			if(result==0){
				response.getWriter().print("failure");
			}else{
				response.getWriter().print("success");
			}
		}else{
			response.getWriter().print("failure");
		}
	}


}
