<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
 
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>登录页面</title>
  <link rel="stylesheet" href="style/index.css">
  <script type="text/javascript" src="script/jquery-1.4.2.js"></script>
  <script type="text/javascript" src="script/jquery-cookie.js"></script>
  <script type="text/javascript">
  
 
  function validateName(){
	  var name = $('input[name="name"]');
	 if("" == name.val()){
		  alert("用户名不能为空");
	  }
  }
  function validatePassword(){
	  var password = $('input[name="password"]');
	  if("" == password.val()){
		  alert("密码不能为空");
	  }
  }
  $(function(){
	  var name_pass = $.cookie("user");
	  if("undefined"!=name_pass){
		 var strs = name_pass.split("@"); 
		 console.log(strs[0]);
		 $('#user1').val(strs[0]);
		 $('#password').val(strs[1]);
	  }
  }); 
  </script>
</head>
<body>

  <div class="container">
  
  <h1 align="center" style = "color:blue;font-size:50px;">卡盟汽车运输服务有限公司</h1>
  <h3 class="bt">账务管理系统</h3>
  	
    <div class="login">
      <h1>用户登录</h1>
      <form method="post" action="login.action">
        <p><input type="text" name="name" value="" id="user1" placeholder="请输入用户名" onblur="validateName()" /></p>
        <p><input type="password" name="password" value="" id ="password" placeholder="请输入密码" onblur="validatePassword()"></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me" value="remember_me">
            记住密码
          </label>
          
        </p>
        <span style="color: red">${message}</span>
        <p class="submit"><input type="submit" name="commit" value="登录"></p>
      </form>
    </div>
   
  </div>
  
 <div class="foot">
 	<p>欢迎加盟合作共赢，联系电话：029-84527455</p>
 </div>

</body>
</html>
