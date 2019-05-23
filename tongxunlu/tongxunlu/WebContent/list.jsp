<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.sql.*" import="cn.lzjtu.tuxunlu.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示联系人</title>
</head>

<%	
Statement stat = null;
ResultSet rs = null;
Connection conn = null;
conn=DBConnection.getConnection();
String sql = "select * from tb_tongxunlu";  
try{
    stat = conn.createStatement();  
	rs = stat.executeQuery(sql); 
} catch(Exception e){
	e.printStackTrace();
}
%>   
<body background ="images/page_t.jpg">


  <center>
<br/><br/>
<p align = "right">当前用户：<%=request.getSession().getAttribute("user")%>&nbsp;&nbsp;
	<a href = "login.jsp">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
</p>
   <hr color = "00ffff"/>
   <table border="1" width="600"> 
   <caption>  <strong>通讯录 </strong></caption>
       <tr bgcolor="#dddddd">  
           <th align="center" width="80">姓名</th>   
           <th align="center" width="100">电话</th>  
           <th align="center" width="100">关系</th>
           <th align="center" width="100">电子邮件</th>
           <th align="center" width="100">操作</th>
       </tr> 
        

<%
           String number,name,relation,email;  
             
            while (rs != null && rs.next()){  
               
                name = rs.getString("username");  
                number = rs.getString("usernumber");  
                relation = rs.getString("relation");  
                email = rs.getString("email");  
                out.println("<tr>");  
                out.println("<td>"+name+"</td>");  
                out.println("<td>"+number+"</td>"); 
                out.println("<td>"+relation+"</td>"); 
                out.println("<td>"+email +"</td>");
                out.println("<td>");
                out.println("<a href='delete.jsp?op=del&usernumber="+number+ "'>删除</a>/");
                out.println("<a href='edit.jsp?op=edi&usernumber="+number+ "'>编辑</a>");
                out.println("</td>");
                out.println("</tr>");
                
            } 
%>
         <tr>
             <td>
                 <a href = "add.jsp">添加联系人</a>
             </td>
             <td>&nbsp;</td>
             <td>&nbsp;</td>
             <td>&nbsp;</td>
             <td>
                  <a href = "find.jsp">查询联系人</a>
             </td>
         </tr>
    </table>
   
    <hr color = "#ffff00">     
</center>
   
    
</body>
</html>