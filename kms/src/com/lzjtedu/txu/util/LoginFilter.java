package com.lzjtedu.txu.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.User;


public class LoginFilter implements Filter {

    
    public LoginFilter() {
    }

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//获取当前的访问路径 urlString
		String urlString = req.getRequestURI();
		
		if(urlString.endsWith("login.jsp") || urlString.endsWith("login.action") || urlString.endsWith("/kms/")){
			chain.doFilter(request, response);
			return;
		}else{
			
			if(req.getSession().getAttribute("user") == null){
				res.sendRedirect("login.jsp");
				return;
			}else {
				chain.doFilter(request, response);
				return;
			}
			
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
