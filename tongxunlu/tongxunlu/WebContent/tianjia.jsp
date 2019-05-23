<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="cn.lzjtu.tuxunlu.*"  import="java.sql.*"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");  //  设置编码格式
	String Name=request.getParameter("username");
	String phone=request.getParameter("usernumber");
	String Relation=request.getParameter("relation");
	String Email=request.getParameter("email");  // 获取表单提交的内容

	if(Name==""||phone==""||Relation==""||Email==""){
		response.sendRedirect("add.jsp"); 
	}
	else{
		Person per = new Person();
		per.setName(Name);
		per.setNumber(phone);
		per.setRelation(Relation);
		per.setEmail(Email);
		PersonDao.insert(per);
		response.sendRedirect("list.jsp");  // 重定向到list.jsp页面
	}
%>
</body>
</html>