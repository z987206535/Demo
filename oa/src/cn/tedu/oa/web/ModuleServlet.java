package cn.tedu.oa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.oa.service.IModuleService;
import cn.tedu.oa.service.impl.ModuleServiceImpl;


public class ModuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ModuleServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String command = request.getParameter("command");
	   IModuleService sf = new ModuleServiceImpl();
	   if(command == null){
		int pageNo = 1;
		//int page = 0;
		if(request.getParameter("pageNo")!=null){
		  pageNo = Integer.parseInt(request.getParameter("pageNo"));	
		}
		request.setAttribute("rows", sf.findModuleAllByPageNo(pageNo));
		request.setAttribute("page", sf.findModuleTotalPage());
		request.setAttribute("pageNo", pageNo);
		String path = "/WEB-INF/jsp/module.jsp";
		RequestDispatcher pc = request.getRequestDispatcher(path);
		pc.forward(request, response);
	   }
	}

}
