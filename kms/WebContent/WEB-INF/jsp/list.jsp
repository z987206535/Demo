<%@page import="com.lzjtedu.txu.domain.Record"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>list页面</title>
    <script type="text/javascript" src="my97/WdatePicker.js"></script>
    <script type="text/javascript" src="script/list.js"></script> 
    <script type="text/javascript" src="script/jquery-1.4.2.js"></script>
    
   <link rel="stylesheet" type="text/css" href="style/list.css" />
 	
  </head>
 
  <body  bgcolor="#ccffff">
  <p align="right">欢迎用户，${user.realName} &nbsp;&nbsp;<a onclick="firm()" href="#">注销</a>&nbsp;&nbsp;</p>
  <h1 align="center">账务清单</h1>
	<!-- 显示数据的表格  start -->
 	<table id="tab"  border="1px" bgcolor="#ff0066">
 	<tr>
	 	<td colspan="2">类型:
	 		<select name="types"  class="${types}" id="types">
	 			 <option  value="0" >默认</option>
				 <option  value="1" >收入</option>
				 <option  value="-1" >支出</option>
			</select>
		</td>
	 	<td>起始时间: <input type="text" name="startTime" value="${startTime}" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"/></td>
	 	<td>结束时间: <input type="text" name="endTime" value="${endTime}" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"/></td>
	 	<td><button onclick="find()">查询</button></td>
	 	<td colspan="2"><button onclick="add()">添加</button></td>
 	</tr>
 	<tr>
 		<th>序号</th>
	 	<th>时间</th>
	 	<th>事项</th>
	 	<th>费用类型</th>
	 	<th>费用</th>
	 	<th>备注</th>
	 	<th>操作</th>
 	</tr>
	 <c:forEach items="${pageBeen.recordList}" begin="0" var="record" varStatus="i">
	 	<tr>
	 		<td>${(pageBeen.currentPage-1)*5+i.index+1}<input type="hidden" value="${record.id}"></td>	
	 		<td>${record.timestamp}</td>	
	 		<td>${record.matter}</td>	
	 		
	 		<td class="type">${record.type==1?'收入':'支出'}</td>	
	 		<td class="cost">${record.cost}</td>	
	 		<td>${record.remark}</td>	
	 		<td><a href ='${pageContext.request.contextPath}/edit?rid=${record.id}&CurrentPage=${pageBeen.currentPage}'>更新</a></td>
	 	</tr>
	 </c:forEach>
 	<tr>
	 	<td colspan="2">总收入:&nbsp;<span id="earning"></span></td>
	 	<td colspan="2">总支出:&nbsp;<span id="expend"></span></td>
	 	<td colspan="3">盈利:&nbsp;<span id="count"></span></td>
 	</tr>
 	</table>
<!-- 显示数据的表格  end -->

 
 
 <!-- 分页  信息  start -->
 <%-- <div id = "paging">
 	
     <%
           //下一页  当前页改变颜色红色
           if(pageNo==1){
        	  out.println("首页");  
        	  out.println("上一页");  
           }else{
     %>
     		<a href="${pageContext.request.contextPath}/list?types=${types}&pageNo=1&startTime=${startTime}&endTime=${endTime}">首页</a>
         	<a href="${pageContext.request.contextPath}/list?types=${types}&pageNo=<%=pageNo-1%>&startTime=${startTime}&endTime=${endTime}">上一页</a>
      <%} %>
		<!-- 相当于一个循环 -->
         <%
           for(int i=1;i<=page1;i++){
           if(pageNo == i){
         %>
         <span style="color:red"><%=i %></span>&nbsp;
         <a  style="color:red" href="list?types=${types}&pageNo=<%=i%>&startTime=${startTime}&endTime=${endTime}"><%=i %></a>&nbsp;
         <%}else{%>
           <a href="list?types=${types}&pageNo=<%=i %>&startTime=${startTime}&endTime=${endTime}"><%=i %></a>&nbsp;
         <%
           }  
         }
         %>
         <%if(pageNo == page1){
        	 out.println("下一页");
        	 out.println("最后一页");
         }else{ %>
          <a href="${pageContext.request.contextPath}/list?types=${types}&pageNo=<%=pageNo+1%>&startTime=${startTime}&endTime=${endTime}">下一页</a>
           <a href="${pageContext.request.contextPath}/list?types=${types}&pageNo=<%=page1 %>&startTime=${startTime}&endTime=${endTime}">最后一页</a>
         <%} %>
         
        
 </div>
 --%>
 <div class="pageContainer" >
	共<span class="${pageBeen.recordCount}" id="recordCount">${pageBeen.recordCount}</span>条 
	第<span class="${pageBeen.currentPage}" id="currentPage">${pageBeen.currentPage}</span>
	/
	<span class="${pageBeen.pageCount}" id="pageCount">${pageBeen.pageCount}</span>页 
	<c:if test="${pageBeen.currentPage==1}">首页  上一页</c:if>
	<c:if test="${pageBeen.currentPage>1 && pageBeen.currentPage<=pageBeen.pageCount}">
		<a href="${pageContext.request.contextPath}/list?types=${types}&CurrentPage=1&startTime=${startTime}&endTime=${endTime}">首页</a>
		<a href="${pageContext.request.contextPath}/list?types=${types}&CurrentPage=${pageBeen.currentPage-1}&startTime=${startTime}&endTime=${endTime}">上一页</a>
	</c:if>

	<c:if test="${pageBeen.currentPage>=pageBeen.pageCount}">下一页  末页</c:if>
	<c:if test="${pageBeen.currentPage<pageBeen.pageCount}">
	<a href="${pageContext.request.contextPath}/list?types=${types}&CurrentPage=${pageBeen.currentPage+1}&startTime=${startTime}&endTime=${endTime}">下一页 </a>
	<a href="${pageContext.request.contextPath}/list?types=${types}&CurrentPage=${pageBeen.pageCount}&startTime=${startTime}&endTime=${endTime}">末页</a>
	</c:if>
	跳转到<input type="number" style="width:35px" id="target"/><a href="#" onclick="jumpTargetPage()">GO</a>
</div>
 
 
 <!-- 分页  信息  end -->
 
 
 
<!-- 添加的div  start -->
 <div class = "add" id = "tj">
 	<div id="close" onclick="close1()" >X</div>
 	<form action="add" method="post">
 	<table align="center" border="0">
	 <tr>
		<td>时间 :</td>
		<td>
			<input type="text" name="times"  class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"/>
			<span id = "checkTimes" style="color:red"></span>
		</td>
		</tr>
 		<tr>
		 	<td>事项:</td>
		 	<td>
			 	<select name = "matter">
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
			 	 <select name="type">
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
	 		<input name = "cost" onkeyup="this.value=this.value.replace(/\D/g,'')" >¥
	 		<span style="color: green">只能填写数字</span>
	 		</td>
 			
 		</tr>
 	<tr> 
 		<td>备注：</td>
 		<td><textarea  name="remark"></textarea></td>
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
 <!-- 添加的div  end -->
 
 <p align="center">用户的IP为：${user.address}</p>

 <script type="text/javascript">
 window.onload = function(){
		//计算总支出、总收入和盈利的值.
	    var types = document.getElementsByClassName("type");
		var costs = document.getElementsByClassName("cost");
		var earning = 0;
		var expend = 0;
		var count = 0;
		for (var i = 0; i < types.length; i++) {
			var type = types[i].innerHTML == '支出'?-1:1;
			if(type == 1){
				 earning += parseFloat(costs[i].innerHTML);
			}else{
				expend +=parseFloat(costs[i].innerHTML);
			}
		}
		document.getElementById("earning").innerHTML = earning+".0¥";
		document.getElementById("expend").innerHTML = expend+".0¥";
		document.getElementById("count").innerHTML = (earning-expend)+".0¥";
			
		
		
			   
		//给每行记录添加不同的背景色
		var trs = document.querySelectorAll("#tab tr");   
		//循环，判断如果奇数行添加背景颜色
		for(var i=2;i<trs.length-1;i++){
			if(i%2==1){
				trs[i].style.backgroundColor="#ccc";
			}else{
				trs[i].style.backgroundColor="#fff";
			}
		}
		
		//添加数据的基本校验
		 //时间的检验
		 var times = document.getElementsByName("times")[0];
		 times.onblur = testTimes;
		 function testTimes(){
			var times = document.getElementsByName("times")[0];
			if("" == times.value){
				document.getElementById("checkTimes").innerHTML = "不能为空";
				times.style.borderColor="red";
			}else{
				document.getElementById("checkTimes").innerHTML = "";
				times.style.borderColor="#abc";
			}
		 }
		 
		 //事项校验
		var matter = document.getElementsByName("matter")[0];
		matter.onblur = testMatter;
		function testMatter(){
			var matter = document.getElementsByName("matter")[0];
			if("-请选择-" == matter.value){
				document.getElementById("checkMatter").innerHTML = "请选择事项";
				matter.style.borderColor="red";
			}else{
				document.getElementById("checkMatter").innerHTML = "";
				matter.style.borderColor="#abc";
			}
		}
		
		 //费用类型校验
		var types = document.getElementsByName("type")[0];
	
		types.onblur = testTypes;
		function testTypes(){
			var types = document.getElementsByName("type")[0];
			console.log(types.value);
			if("-请选择-" == types.value){
				document.getElementById("checkTypes").innerHTML = "请选择费用类型";
				types.style.borderColor="red";
			}else{
				document.getElementById("checkTypes").innerHTML = "";
				types.style.borderColor="#abc";
			}
		} 
			
		//select标签数据的回显
		var val = document.getElementById("types");
		for (var i = 0; i < val.length; i++) {
			if(val.className ==val[i].value){
				val[i].selected= true;  
			}
		}
	}  
	 
	function firm(){
	 	 if(confirm('您确定要注销用户吗？')){
			location.href="/kms/logout";
	 	 }
	}
	
	function jumpTargetPage(){
		 var targetPageNum = $("#target");
		 if($("#target").val() == ""){
			 targetPageNum = 1;
		 }else{
		 	targetPageNum = parseInt(targetPageNum.val());
		 }
		 
		 var pageCount = parseInt($("#pageCount").attr("class"));
		 
		 console.log(pageCount);
		 console.log(targetPageNum);
		 
		 if(targetPageNum <= 1){
			 targetPageNum = 1;
		 }
		 if(targetPageNum >=pageCount){
			 targetPageNum = pageCount;
		 }
		 
		 var url = "/kms/list?types=${types}&CurrentPage="+targetPageNum;
		 location.href = url;
	}
 </script>
 
  </body>
</html>
