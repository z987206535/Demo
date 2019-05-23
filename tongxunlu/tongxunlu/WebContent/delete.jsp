<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="cn.lzjtu.tuxunlu.*" import="java.sql.*" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>删除</title>
</head>
<body>
	<% 
	request.setCharacterEncoding("utf-8");
	String number=request.getParameter("usernumber");
    Connection conn=DBConnection.getConnection();
	PreparedStatement ps=null;
	   String addSql="delete from tb_tongxunlu where usernumber = '"+number+"'";
	   try {
				ps=conn.prepareStatement(addSql);
			     int t = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        response.sendRedirect("list.jsp");
%>
	
</body>
</html>