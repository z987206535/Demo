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
		 	//���÷��ص��������ͺ��ַ���
	        response.setContentType("text/html;charset=UTF-8");
	        //���û�ȡ�����ݵ��ַ���
	        request.setCharacterEncoding("UTF-8");
	        
	        PrintWriter out = response.getWriter();
	        String uname = request.getParameter("uname");
	        String name = "����"; //ģ������ݿ��л�ȡ������.
	        if(name.equals(uname)){
	           out.println("���û���������");
	        }else{
	           out.println("����ʹ��");
	        }
	 }        
	          
}

