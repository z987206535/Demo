<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.tedu.oa.domain.*" %>    
<%
  //page1变量-总页数-
  long page1 = (Long)request.getAttribute("page");
  //当前页数
  int  pageNo = (Integer)request.getAttribute("pageNo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/index.css" />
<link href="styles/oa.css" rel="stylesheet" type="text/css" />
<link href="styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/public.js"></script>
<style>
  #deleteDialog{
     width:275px;
     height:60px;
     background-color: #fc0;
     border-radius:10px; 
     position: absolute;
     top:20%;
     left:50%;
     text-align: center;
     display:none;
  }
  #deleteDialog h1{
     color:#000;
     font-size:18px;
     background-color: #abc;
  }
  #deleteDialog a{
     display: inline-block;
     border-radius: 5px;
     width:45px;
     height:15px; 
     background-color: #9fc;
     font-weight: bold;
     cursor: pointer;
  }
  
  #addPersonDialog{
    width:320px;
    height:250px;
    background-color: #fc0;
    border:1px solid #abc;
    border-radius: 15px;
    padding:10px;
    position: absolute;
    top:25%;
    left:35%;
    text-align: left;
    display:none;
  }
  ul{
    list-style-type:none;
  }
  #a1{
    width:300px;
    height:300px;
    background:#abc;
    position: absolute;
    top:100px;
    left:100px;
    z-index:1000;
  }
  #orgList{
    width:645px;
    height:370px;
    background:#fff;
    position: absolute;
    border:2px solid #abc;
    top:100px;
    left:265px;
    z-index:999; 
    overflow-y:auto;/*滚动条件 y*/ 
    display: none;  
  }
  
  table{
    border-collapse: collapse;
    width:650px;
  }
  td,th{
    border:1px solid #abc;
  }
</style>
</head>
<body>
 <div id="main">
   <div id="menu">
    <dl>
      <dt>组织管理</dt>
      <dd><a href="org.php">机构管理</a></dd>
      <dd><a href="person.php">人员管理</a></dd>
      <dt>权限管理</dt>
      <dd><a href="role.php">角色管理</a></dd>      
      <dd><a href="#">模块管理</a></dd>
      <dd><a href="#">用户管理</a></dd>
    </dl>
   </div>
   <div id="content">
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align=middle width=20 background=images/title_left.gif 
          bgColor=#dee7ff></TD>
            <TD align=middle width=120 background=images/title_left.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7> 人员管理<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align=middle width=11 background=images/title_middle.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align=middle background=images/title_right.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign=center noWrap>
            </TD>
            <TD width="18%" align=right vAlign=center noWrap>　</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign=center noWrap>
            	<!-- 在这里插入查询表单 -->
            </TD>
            <TD height=14 align="left" vAlign=center noWrap>
            <a href="javascript:;" id="addPerson">添加人员</a>
          </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=left vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <span style="color:red">
             <%
             String msg = "";
             if(session.getAttribute("msg")!=null){
            	 msg = (String)session.getAttribute("msg");
            	 session.removeAttribute("msg");
             }
             out.println(msg);
             %>
            </span>
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6"  id="tab">
          <!-- 列表标题栏 -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="5%" height="37" align="center"><b>序号</b></td>
		      <td width="15%" height="37" align="center"><B>姓名</B></td>
              <td width="5%" height="37" align="center"><b>性别</b></td>
              <td width="10%" height="37" align="center"><b>职务</b></td>
              <td width="20%" height="37" align="center"><b>电话</b></td>
              <td width="25%" height="37" align="center"><b>所属机构</b></td>
              <td width="5%" height="37" align="center"><b>操作</b></td>
          </tr>
          <!-- 列表数据栏 -->

          <%
          List<Person> rows = (List)request.getAttribute("rows");
          for(int i=0;i<rows.size();i++){
        	  Person o = rows.get(i);
          %>
          <tr  class="TableBody1">
		      <td align="center" vAlign="center"><%=o.getId() %></td>
	          <td align="center" vAlign="center"><a href="#"><%=o.getName() %></a></td>
              <td align="center" vAlign="center"><%=o.getGender() %></td>
              <td align="center" vAlign="center"><%=o.getJob() %></td>
              <td align="center" vAlign="center"><%=o.getTel() %></td>
              <td align="center" vAlign="center"><%=o.getOname() %></td>
	          <td align="center" vAlign="center"><a  href="javascript:void(0)" class="delOrg">删除</a></td>
          </tr>
         <% }%>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
         <a href="person?pageNo=1">首页</a>
         <%
           //下一页  当前页改变颜色红色
           if(pageNo==1){
        	  out.println("上一页");  
           }else{
         %>
         <a href="person?pageNo=<%=pageNo-1%>">上一页</a>
         <%} %>
         
         <!-- 相当于一个循环 -->
         <%
           for(int i=1;i<=page1;i++){
           if(pageNo == i){
         %>
         <a  style="color:red" href="person?pageNo=<%=i%>"><%=i %></a>&nbsp;
         <%}else{%>
           <a href="person?pageNo=<%=i %>"><%=i %></a>&nbsp;
         <%
           }  
         }
         %>
         <%if(pageNo == page1){
        	 out.println("下一页");
         }else{ %>
          <a href="person?pageNo=<%=pageNo+1%>">下一页</a>
         <%} %>
         <a href="person?pageNo=<%=page1 %>">最后一页</a>
    		</TD>
          </TR>
        </TBODY>
      </TABLE>
</center>
   </div>
 </div>
<p>
     版权所有
</p>
<div id="deleteDialog">
   <h1>确认要删除<span id="delName">##</span>?</h1>
   <a id="delOK">确认</a>
   <a id="delNo">取消</a>
</div>

<div id="addPersonDialog">
  <form action="person" method="post" id="addPersonForm">
    <input type="hidden" name="command" value="add" />
    <input type="hidden" name="oid" value="" id="oid" />
    
    <ul> 
    <li>
    所属机构
     <input type="text" disabled="disabled" name="oname" value="" id="orgname"/>
     <a href="javascript:;"  id="selOrg">选择</a>
     </li>
     <li>
     姓名  
     <input type="text" name="name" value="" id="name"/>
    </li>
    <li>
      性别 
    <input type="radio" checked="checked" name="gender" value="男"/>男
    <input type="radio" name="gender" value="女"/>女
    </li>
    <li>
      年龄
    <input type="text" name="age" value="" id="age" class="Wdate"/>
    </li>
    <li>
    电话  
    <input type="text" name="tel" value="" id="tel"/>
    </li>
    <li>
    地址
    <input type="text" name="addr" value="" id="addr"/>
    </li>
    <li>
    描述
    <input type="text" name="descr" value="" id="descr"/>
    </li>    
    <li>
     <span id="addOrgMsg" style="color:red;"></span>
    <br /><br />
    <input type="submit" value="保存人员信息" />
    </li>
    </ul>
  </form>
</div>
<div id="orgList">
  <table>
     <tr>
       <td>选择</td>
       <td>机构名称</td>
       <td>机构SN</td>
       <td>父机构名称</td>
     </tr>
    <%
      List<Org> orows = (List)request.getAttribute("orows");
      for(int i=0;i<orows.size();i++){
    	  Org o = orows.get(i);
    %> 
     <tr>
       <td><input type="radio" name="rid" value="<%=o.getId() %>" class="<%=o.getId() %>_<%=o.getName() %>"/></td>
       <td><%=o.getName() %></td>
       <td><%=o.getSn() %></td>
       <td><%=o.getPname() %></td>
     </tr> 
    <%} %>     
  </table>
</div>
<script type="text/javascript" src="scripts/person.js">
</script>
<script type="text/javascript" src="my97/WdatePicker.js"></script>
</body>
</html>