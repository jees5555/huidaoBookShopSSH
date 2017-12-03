package com.github.jees5555.huidaoBookShop.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.BookService;
import com.github.jees5555.huidaoBookShop.service.UserService;
import com.github.jees5555.huidaoBookShop.service.impl.BookServiceImpl;
import com.github.jees5555.huidaoBookShop.service.impl.UserServiceImpl;
import com.github.jees5555.huidaoBookShop.util.Page;

@WebServlet("/doregister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us =new  UserServiceImpl();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email =request.getParameter("email");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		try {
			user=us.register(user);
			HttpSession session =request.getSession();
			session.setAttribute("uid", user.getUid());
			session.setAttribute("username", user.getUsername());
			request.getRequestDispatcher("WEB-INF/pages/register_success.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "服务器出错！");
			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
		}
		
	}



}