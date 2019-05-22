<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.tedu.oa.domain.*" %>    
<%
  //page1变量-总页数-
  long page1 = (Long)request.getAttribute("page");
  //当前页数
  int  pageNo = (Integer)request.getAttribute("pageNo");
  //获取pid 父机构id
  int pid = (Integer)request.getAttribute("pid");
  //获ppid
  int ppid = (Integer)request.getAttribute("ppid");
  
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
  
  #addOrgDialog{
    width:470px;
    height:110px;
    background-color: #fc0;
    border:1px solid #abc;
    border-radius: 5px;
    padding:10px;
    position: absolute;
    top:25%;
    left:35%;
    text-align: center;
    display: none;
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
          bgColor=#dee7ff><FONT color=#f7f7f7> 机构管理<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
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
            <a href="javascript:;" id="addOrg">添加机构</a>
            <a href="org?pid=<%=ppid%>">返回上级机构</a>  
          </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=left vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <span style="color:red">
             <%
             String msg = "";
             if(session.getAttribute("msg")!=null){
            	 msg = (String)session.getAttribute("msg");
             }
             out.println(msg);
             %>
            </span>
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
          <!-- 列表标题栏 -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="5%" height="37" align="center"><b>序号</b></td>
		      <td width="18%" height="37" align="center"><B>机构名称</B></td>
              <td width="18%" height="37" align="center"><b>机构编号</b></td>
              <td width="18%" height="37" align="center"><b>父机机构名称</b></td>
              <td width="5%" height="37" align="center"><b>操作</b></td>
          </tr>
          <!-- 列表数据栏 -->

          <%
          List<Org> rows = (List)request.getAttribute("rows");
          for(int i=0;i<rows.size();i++){
        	  Org o = rows.get(i);
          %>
          <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="center"><%=o.getId() %></td>
	          <td align="center" vAlign="center"><a href="org?pid=<%=o.getId() %>"><%=o.getName() %></a></td>
              <td align="center" vAlign="center"><%=o.getSn() %></td>
              <td align="center" vAlign="center"><%=o.getPname() %></td>
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
         <a href="org?pageNo=1&pid=<%=pid%>">首页</a>
         <%
           //下一页  当前页改变颜色红色
           if(pageNo==1){
        	  out.println("上一页");  
           }else{
         %>
         <a href="org?pageNo=<%=pageNo-1%>&pid=<%=pid%>">上一页</a>
         <%} %>
         
         <!-- 相当于一个循环 -->
         <%
           for(int i=1;i<=page1;i++){
           if(pageNo == i){
         %>
         <a  style="color:red" href="org?pageNo=<%=i %>&pid=<%=pid%>"><%=i %></a>&nbsp;
         <%}else{%>
           <a href="org?pageNo=<%=i %>&pid=<%=pid%>"><%=i %></a>&nbsp;
         <%
           }  
         }
         %>
         
         
         
         <%if(pageNo == page1){
        	 out.println("下一页");
         }else{ %>
          <a href="org?pageNo=<%=pageNo+1%>&pid=<%=pid%>">下一页</a>
         <%} %>
         
         
         <a href="org?pageNo=<%=page1%>&pid=<%=pid%>">最后一页</a>
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

<div id="addOrgDialog">
  <form action="org" method="post" id="addOrgForm">
    <input type="hidden" name="command" value="add" />
    <input type="hidden" name="pid" value="<%=pid%>" />
     机构名称
    <input type="text" name="name" value="" id="orgname"/>
    机构描述
    <input type="text" name="descr" value="" id="orgdescr"/>
    <br />
     <span id="addOrgMsg" style="color:red;"></span>
    <br /><br />
    <input type="submit" value="保存机构信息" />
  </form>
</div>



</body>
</html>