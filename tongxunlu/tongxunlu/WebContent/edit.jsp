<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="cn.lzjtu.tuxunlu.*" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑页面</title>


<% 
    Person pi=new Person();
    request.setCharacterEncoding("utf-8");
	String num = request.getParameter("usernumber");
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=null;
	String FindSql="select * from tb_tongxunlu where usernumber = '"+num+"'";
    try {
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery(FindSql); 
		while(rs.next()){
			
			pi.setName(rs.getString(1));
			pi.setNumber(rs.getString(2));
			pi.setRelation(rs.getString(3));
			pi.setEmail(rs.getString(4));
		}
	}catch(Exception e){
		e.getStackTrace();
	}
	PersonDao.delete(num);
	%>
<body>
<br/><br/><br/><br/><br/><br/>
<center>
	<form action="tianjia.jsp" method="post">
	<table border="1" bordercolor = "#aaffdd" bgcolor = "aaffdd" width = "350" height = "200">
	    <tr>
			<td>姓名：</td>
			<td><input type="text" name="username" value = <%=pi.getName() %> /></td>
		</tr>
		<tr>
			<td>电话号码：</td>
			<td><input type="text" name="usernumber" value = <%=pi.getNumber() %> /></td>
		</tr>
		<tr>
			<td>关系：</td>
			<td> <input type="text" name="relation" value = <%=pi.getRelation() %> /> </td>
		</tr>
		<tr>
			<td>电子邮件：</td>
			<td> <input type="text" name="email" value = <%=pi.getEmail() %> /> </td>
			               
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="add" value="添加">
			</td>
		</tr>
	</table>
	</form>
	</center>
</body>
</html>