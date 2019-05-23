<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加页面</title>
</head>
<body>
<br/><br/><br/><br/><br/>

<center>
<strong>添加联系人</strong>
<br/><br/>
<form action="tianjia.jsp" method="post">
	<table border="1" bordercolor = "#aaffdd" bgcolor = "aaffdd" width = "350" height = "200">
	    <tr>
			<td>姓名：</td><td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>电话号码：</td><td><input type="text" name="usernumber"></td>
		</tr>
		<tr>
			<td>关系：</td><td> <input type="text" name="relation"> </td>
			               
		</tr>
		<tr>
			<td>电子邮件：</td><td> <input type="text" name="email"> </td>
			               
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="add" value="添加">
				<input type ="reset" value = "重置" name = "Reset">
			</td>
		</tr>
	</table>
	</form>
	</center>
</body>
</html>