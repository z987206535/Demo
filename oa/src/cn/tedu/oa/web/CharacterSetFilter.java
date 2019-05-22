package cn.tedu.oa.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterSetFilter implements Filter{
	private String encoding = null;
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
	 //1:修改编码为utf-8
	 request.setCharacterEncoding(encoding);
	 System.out.println("doFilter"+encoding);
     //2:继续执行后续程序
	 chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("CharFilter..init()"+this);
		encoding = config.getInitParameter("encoding");
	}

}
