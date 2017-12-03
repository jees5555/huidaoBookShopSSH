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
@WebServlet("/dologin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService us =new  UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=null;
		try {
			user = us.login(username, password);
			if(user==null){
				request.setAttribute("error", "密码错误！");
				request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
			}else{
				HttpSession session =request.getSession();
				session.setAttribute("uid", (Integer)user.getUid());
				session.setAttribute("username", user.getUsername());
				response.sendRedirect("book/booklist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "服务器出错！");
			request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		}
		
	}

}
