package cn.tedu.oa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.oa.service.IModuleService;
import cn.tedu.oa.service.IRoleService;
import cn.tedu.oa.service.impl.ModuleServiceImpl;
import cn.tedu.oa.service.impl.RoleServiceImpl;

public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RoleServlet() {
        super();
    }
    @Override
    protected void service(
    		HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        String  command = request.getParameter("command");
        IRoleService service = new RoleServiceImpl();
        IModuleService mser = new ModuleServiceImpl();
        
        
        if(command == null){
      	  int pageNo = 1;   //默认显示第一页
      	  String spageNo = request.getParameter("pageNo");
      	  //如果有参数依据参数获取数据，没有按默认值
      	  if(spageNo != null){
      		  pageNo = Integer.parseInt(spageNo);
      	  }
      	  //1:保存当前页内容
      	  request.setAttribute("rows", 
      			  service.findByPage(pageNo));
      	  //2:当前pid所有页数
      	  request.setAttribute("page", 
      			  service.findTotalPage());
      	  
      	  //2.1:保存所有模块列表
      	  request.setAttribute("mrows", mser.findAll());
      	  
      	  //3:保存当前页数
      	  request.setAttribute("pageNo", pageNo);
      	  String path = "/WEB-INF/jsp/role.jsp";
      	  RequestDispatcher dr = request.getRequestDispatcher(path);
      	  dr.forward(request, response);
        }
    }
}
