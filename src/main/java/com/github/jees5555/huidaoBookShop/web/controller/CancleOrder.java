package com.github.jees5555.huidaoBookShop.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.OrderService;
import com.github.jees5555.huidaoBookShop.service.impl.OrderServiceImpl;
import com.github.jees5555.huidaoBookShop.util.Page;

/**
 * Servlet implementation class CancleOrder
 */
@WebServlet("/order/cancle")
public class CancleOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService os =new OrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancleOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid =request.getParameter("oid");
		HttpSession session =request.getSession();
		Integer uid =(Integer) session.getAttribute("uid");
		String username=(String)session.getAttribute("username");
		User user =new User();
		user.setUid(uid);
		user.setUsername(username);
		int res=os.cancleOrder(oid, user);
		if(res!=0){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failure");
		}
	}

}
