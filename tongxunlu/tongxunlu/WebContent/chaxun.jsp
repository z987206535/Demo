 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.*" import="java.sql.*" import="cn.lzjtu.tuxunlu.*"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询页面</title>
</head>
<body>
<br><br><br>
<center>

<%
 
	String name,number,relation,email;
ResultSet rs = null;
    request.setCharacterEncoding("utf-8");
	name=request.getParameter("username");
	Connection conn=DBConnection.getConnection();
	PreparedStatement ps=null;
	String updateSql="select * from tb_tongxunlu where username = '"+name+"'";
	try {
		ps=conn.prepareStatement(updateSql);
		 rs=ps.executeQuery();
	}catch(Exception e){
		e.printStackTrace();
	}
	%>
<%  if(rs.next()){ %>	     
<table>
	     <tr bgcolor="#dddddd">  
	    	   <th align="center" width="80">姓名</th>   
	    	   <th align="center" width="100">电话</th>  
	    	   <th align="center" width="100">关系</th>
	    	   <th align="center" width="100">电子邮件</th>
	    	</tr> 
<% 
			name = rs.getString("username");
			number = rs.getString("usernumber");  
			relation = rs.getString("relation");  
			email = rs.getString("email");  
	     	out.println("<tr>");  
	     	out.println("<td>"+name+"</td>");  
	     	out.println("<td>"+number+"</td>"); 
	    	out.println("<td>"+relation+"</td>"); 
	    	out.println("<td>"+email+"</td>");
	     	out.println("</tr>");
         }
	     else{
	    	out.print("<h2>"+"对不起！您查找的联系人不存在。"+"</h2>");
	     }
%>
</table>
	<br/>
	<a href="list.jsp">返回列表</a>
	</center>
</body>
</html>