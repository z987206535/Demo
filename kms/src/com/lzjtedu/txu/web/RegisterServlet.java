package com.lzjtedu.txu.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.User;
import com.lzjtedu.txu.service.UserService;
import com.lzjtedu.txu.serviceImpl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		UserService service = new UserServiceImpl();
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		if(username == null && password == null){
			String path = "/WEB-INF/jsp/register.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}else{
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			user.setAddress(request.getLocalAddr());
			service.saveUser(user);
			response.sendRedirect("login.jsp");
		}
	}
}
