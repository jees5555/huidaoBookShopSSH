package com.github.jees5555.huidaoBookShop.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.UserService;
import com.github.jees5555.huidaoBookShop.service.impl.UserServiceImpl;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/dologout")
public class DoLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService us =new  UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session =request.getSession();
				session.removeAttribute("uid");
				session.removeAttribute("username");
				response.sendRedirect(request.getContextPath()+"/");
		
		
	}

}
