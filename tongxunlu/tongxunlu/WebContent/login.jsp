<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body background = "images/page_t.jpg">
<br/><br/><br/>
<br/><br/><br/>

<form action = "Demo.jsp" method = "POST" OnSubmit="return cheek()">
<table border = "0" width = "350" height = "300" align ="center">
<tr height = "150" bgcolor = "#ff00ff">
<td colspan = "4" align = "center">用户登录</td>
</tr>
<tr >
<td width = "20%" align = "right" >
  <font size = "2">用户名：</font>
</td>
<td colspan = "2" width = "45%">
 <input type = "text" id = "user" name = "user" size = "25" />
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td align = "right">
  <font size = "2">密码：</font>
</td>
<td colspan = "2" >
 <input type = "password" id = "pass"name = "pass" size = "25" />
</td>
<td>&nbsp;</td>
</tr>
<tr height = "20">
<td>&nbsp;</td>
<td colspan = "2">
<input type = "submit" value = "登录" name = "Submit">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href = "zhuce.jsp" >注册</a></td>
</tr>


</table>
</form>

</body>
</html>