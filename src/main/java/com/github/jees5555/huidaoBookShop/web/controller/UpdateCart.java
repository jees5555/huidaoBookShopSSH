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
 * Servlet implementation class UpdateCart
 */
@WebServlet("/cart/update")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cs =new CartServiceImpl();   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidStr=request.getParameter("bid");
		String bookcountStr=request.getParameter("bookcount");
		if(bidStr!=null &&! "".equals(bidStr)&&bookcountStr!=null&&!"".equals(bookcountStr)){
			User user =new User();
			Integer uid=(Integer) request.getSession().getAttribute("uid");
			if(uid ==null){
				throw new ServletException();
			}
			user.setUid(uid);
			Integer bid=Integer.parseInt(bidStr);
			Integer bookcount=Integer.parseInt(bookcountStr);
			int result=cs.updateCartBookCount(bid,bookcount,user);
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
