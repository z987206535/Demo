<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.sql.*" import="cn.lzjtu.tuxunlu.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<%
  String user,Pass0,Pass1;
  request.setCharacterEncoding("utf-8");
  user=request.getParameter("user");
  Pass0=request.getParameter("pass0");
  Pass1=request.getParameter("pass1");
  
  if(Pass0=="" || Pass1==""|| user==""){
		
	   response.sendRedirect("zhuce.jsp");
	  	
	}
 	else{
 		if(Pass0.equals(Pass1)){
 		   Connection conn=DBConnection.getConnection();
 		   PreparedStatement ps=null;
 		   String addSql="insert into tb_user(user,password) values('"+user+"','"+Pass0+"')";
 		   try {
 					ps=conn.prepareStatement(addSql);
 				     int t = ps.executeUpdate();
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 		 response.sendRedirect("login.jsp");
 		}
 		else{
 			 response.sendRedirect("zhuce.jsp");
 		}
 	}
 
	
	

%>
<body>

</body>
</html>