<%@page import="com.lzjtedu.txu.domain.Record"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑页面</title>
</head>
<body>
<div class = "add" id = "tj">
 	<form action="update" method="post">
 	<table align="center" border="0">
	 <tr>
		<td>时间 :</td>
		<td>
			<input type="hidden" name="rid" value="${record.id}" >
			<input type="hidden" name="CurrentPage" value="${currentPage}" >
			<input type="text" name="times" readonly="readonly" value="${record.timestamp}" />
		</td>
		</tr>
 		<tr>
		 	<td>事项:</td>
		 	<td>
			 	<select name = "matter" class= "${record.matter}" id="matter">
			 		<option selected>-请选择-</option>
				 	<option value="物流承运">物流承运</option>
				 	<option value="货运信息">货运信息</option>
				 	<option value="代办保险">代办保险</option>
				 	<option value="机油轮胎">机油轮胎</option>
				 	<option value="修理配件">修理配件</option>
				 	<option value="代销车辆">代销车辆</option>
				 	<option value="代收快递">代收快递</option>
				 	<option value="二手车交易">二手车交易</option>
				 	<option value="挂牌审验">挂牌审验</option>
				 	<option value="车辆过户">车辆过户</option>
			 	</select>
			 	<span id = "checkMatter" style="color:red"></span>
		 	<td>
	 	</tr>
	 	<tr>
		 	<td>费用类型 :</td>
		 	<td>
			 	 <select name="type" class="${record.type}" id="type">
			 		<option selected>-请选择-</option>
			 		<option value="1" >&nbsp;&nbsp;&nbsp;&nbsp; 收入</option>
			 		<option value="-1">&nbsp;&nbsp;&nbsp;&nbsp; 支出</option>
			 	</select>
			 	<span id = "checkTypes" style="color:red"></span>
		 	</td>
	 	</tr>
 		<tr>
	 		<td>费用:</td>
	 		<td>
	 		<input name = "cost" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${record.cost}" onafterpaste="this.value=this.value.replace(/\D/g,'')">¥
	 		<span style="color: green">只能填写数字</span>
	 		</td>
 			
 		</tr>
 	<tr> 
 		<td>备注：</td>
 		<td><textarea  name="remark">${record.remark}</textarea></td>
 		</tr>
		<tr>
			<td>
			<input type="submit" value="提交" />
			</td>
			<td align="right">
			<input type="reset" value="重置" />
			</td>
		</tr>
		</table>
 	</form>
 </div>
 <script type="text/javascript">

 //select标签数据的回显
 window.onload = function(){
		var val = document.getElementById("type");
		for (var i = 0; i < val.length; i++) {
			if(val.className ==val[i].value){
				val[i].selected= true;  
			}
		}
		
		var val = document.getElementById("matter");
		for (var i = 0; i < val.length; i++) {
			if(val.className ==val[i].value){
				val[i].selected= true;  
			}
		}
 }
</script>
</body>
</html>