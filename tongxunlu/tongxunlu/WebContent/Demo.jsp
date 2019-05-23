<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.lzjtu.tuxunlu.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交数据库</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String user=request.getParameter("user");
	    String pass=request.getParameter("pass");
	    User pi=new User();
		pi.setUser(user);
		pi.setPassword(pass);
		boolean f = UserDao.isValidUser(pi);
		if(f){
		    request.getSession().setAttribute("user", user);
	 %>
		    <jsp:forward page = "list.jsp" ></jsp:forward >
	 <%
		    //response.sendRedirect("list.jsp");
		}
		else{
			response.sendRedirect("login.jsp");
		}
%>
</body>
</html>