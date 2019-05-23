<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册页面</title>
</head>
<body background = "images/page_t.jpg">
<br/><br><br/><br/><br/><br/><br><br/>
<form action = "jiaoyan.jsp" method = "POST">
<table border = "1" align = "center" height = "200">
<tr height = "60">
<td colspan = "3" align = "center" bgcolor = "aabbcc">用户注册</td>
</tr>
<tr>

<td width = "20%" align = "right">
  <font size = "2">用户名:</font>
</td>
<td colspan = "2" >
  &nbsp;<input type = "text" name = "user" size = "24"  />
</td>
</tr>

<tr>
<td align = "right">
  <font size = "2">密码:</font>
</td>
<td colspan = "2" >
  &nbsp;<input type = "password" name = "pass0" size = "25" />
</td>
</tr>

<tr>
<td align = "right">
  <font size = "2">确认密码:</font>
</td>
<td colspan = "2" >
 &nbsp;<input type = "password" name = "pass1" size = "25" />
</tr>

<tr height = "20">
<td>&nbsp;</td>
<td colspan = "2">
 &nbsp;<input type = "submit" value = "注册" name = "Submit">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type ="reset" value = "重置" name = "Reset"></td>
</tr>
</table>
</form>
</body>
</html>