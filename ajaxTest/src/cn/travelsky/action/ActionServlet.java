package cn.travelsky.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	 public void service(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	//设置返回的数据类型和字符集
	        response.setContentType("text/html;charset=UTF-8");
	        //设置获取到数据的字符集
	        request.setCharacterEncoding("UTF-8");
	        
	        PrintWriter out = response.getWriter();
	        String uname = request.getParameter("uname");
	        String name = "张三"; //模拟从数据库中获取的数据.
	        if(name.equals(uname)){
	           out.println("该用户名不可用");
	        }else{
	           out.println("可以使用");
	        }
	 }        
	          
}

