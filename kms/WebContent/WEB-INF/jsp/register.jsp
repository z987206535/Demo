<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>注册页面</title>
  </head>
  <body>
   <div>
      <p align="center">用户注册</p>
     <form action="register.action" method="post">
      <table align="center" border="1" bordercolor="#ccc">
  <tr>
    <td>用户名：</td>
    <td><input type="text" name="name" /></td>
  </tr>
  <tr>
    <td>密码：</td>
    <td><input type="password" name="password" /></td>
  </tr>
  <tr>
    <td>再次确认密码：</td>
    <td><input type="password" name="password1" /></td>
  </tr>
  <tr>
    <td colspan="2" >
    	<input type="submit" value="注册" />
    </td>
  </tr>
</table>
</form>
</div>
  </body>
</html>
