package com.lzjtedu.txu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzjtedu.txu.domain.User;
import com.lzjtedu.txu.service.UserService;
import com.lzjtedu.txu.serviceImpl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService  service = new UserServiceImpl();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String remember_me = request.getParameter("remember_me");
		User user = service.getUser(username,password);
		if(user == null){
			String message = "用户名或密码错误";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else{
			
			if(""!=remember_me && "remember_me".equals(remember_me)){
				
				//记住用户名和密码并保存到Cookie
				Cookie cookie = new Cookie("user",URLEncoder.encode(username,"utf-8")+"@"+password);
				//设置有效时间
				cookie.setMaxAge(60*60);
				//设置有效路径
				cookie.setPath("/");
				//会写Cookie
				response.addCookie(cookie);
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/kms/list");
//			RequestDispatcher rd = request.getRequestDispatcher("list");
//			rd.forward(request, response);
		}
	}
}
