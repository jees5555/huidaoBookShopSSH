package com.github.jees5555.huidaoBookShop.web.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFliter
 */
@WebFilter(filterName="LoginFliter",urlPatterns={"/book/*","/cart/*","/order/*"})
public class LoginFliter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFliter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr=(HttpServletRequest) request;
		HttpServletResponse hsre=(HttpServletResponse) response;
		HttpSession session =hsr.getSession();
		Integer uid=(Integer) session.getAttribute("uid");
		String username= (String) session.getAttribute("username");
		if(uid !=null && username!=null){
			chain.doFilter(request, response);
		}else{
			String requestType = hsr.getHeader("x-requested-with");
			if(requestType != null && "XMLHttpRequest".equalsIgnoreCase(requestType.trim())) {
			    //如果是ajax请求
				hsre.getWriter().print("timeout");
			}else{
				hsre.sendRedirect(hsr.getContextPath()+"/");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
