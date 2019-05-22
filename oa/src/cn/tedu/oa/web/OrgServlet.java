package cn.tedu.oa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.oa.domain.Org;
import cn.tedu.oa.service.IOrgService;
import cn.tedu.oa.service.impl.OrgServiceImpl;


public class OrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String  command = request.getParameter("command");
      IOrgService service = new OrgServiceImpl();
      if(command == null){
    	  int pageNo = 1;   //默认显示第一页
    	  int pid = 0;      //默认显示顶级级别机构
    	  String spageNo = request.getParameter("pageNo");
    	  String spid = request.getParameter("pid");
    	  //如果有参数依据参数获取数据，没有按默认值
    	  if(spageNo != null){
    		  pageNo = Integer.parseInt(spageNo);
    	  }
    	  if(spid != null){
    		  pid = Integer.parseInt(spid);
    	  }
    	  //1:保存当前页内容
    	  request.setAttribute("rows", 
    			  service.findOrgAllByPageNo(pageNo, pid));
    	  //2:当前pid所有页数
    	  request.setAttribute("page", 
    			  service.findOrgTotalPage(pid));
    	  //3:保存当前页数
    	  request.setAttribute("pageNo", pageNo);
    	  //4:保存pid
    	  request.setAttribute("pid", pid);
    	  //5:保存ppid
    	  request.setAttribute("ppid", service.getPPid(pid));
    	  
    	  String path = "/WEB-INF/jsp/org.jsp";
    	  RequestDispatcher dr = request.getRequestDispatcher(path);
    	  dr.forward(request, response);
      }else if("del".equals(command)){
    	  int id = Integer.parseInt(request.getParameter("id"));
    	  boolean rs = service.deleteOrgById(id);
    	  String msg = "该机构存在子机构不存在允许删除";
    	  if(rs){
    		  msg = "删除成功";
    	  }
    	  
    	  request.getSession(true).setAttribute("msg", msg);
    	  //重新显示列表
    	  response.sendRedirect("org");
      }else if("add".equals(command)){
    	  int pid = Integer.parseInt(request.getParameter("pid"));
    	  String name = request.getParameter("name");
    	  String descr = request.getParameter("descr");
    	  Org org = new Org();
    	  org.setPid(pid);
    	  org.setName(name);
    	  org.setDescr(descr);
    	  //设置默认值
    	  org.setSn("");
    	  org.setPname("");
    	  service.addOrg(org);
    	  request.getSession(true)
    	  .setAttribute("msg", "添加机构成功");
    	  response.sendRedirect("org?pid="+pid+"&pageNo="
    	                    +service.findOrgTotalPage(pid)); 
    	  //14:50--15:00
    	  //添加成功后->跳回本级机构->最后一页
      }
	}

}
