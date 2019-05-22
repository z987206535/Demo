package cn.tedu.oa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.oa.domain.Person;
import cn.tedu.oa.service.IOrgService;
import cn.tedu.oa.service.IPersonService;
import cn.tedu.oa.service.impl.OrgServiceImpl;
import cn.tedu.oa.service.impl.PersonServiceImpl;


public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonServlet() {
        super();
    }

    //10:40---11:05 
	protected void service(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
         request.setCharacterEncoding("utf-8");
         IPersonService service = new PersonServiceImpl();
         IOrgService   os = new OrgServiceImpl();
         String command = request.getParameter("command");
         if(command == null){
        	 //1:获取当前页内容
        	 int pageNo = 1;
        	 //2:总页数
        	 //int page = 0;
        	 
        	 if(request.getParameter("pageNo")!=null){
        		 pageNo = Integer.parseInt(request.getParameter("pageNo"));
        	 }
        	 
        	 request.setAttribute("rows", service.findPersonAllByPageNo(pageNo));
             request.setAttribute("page", service.findPersonTotalPage());
             request.setAttribute("orows", os.findAll());
             request.setAttribute("pageNo", pageNo);
        	 String path = "/WEB-INF/jsp/person.jsp";
        	 RequestDispatcher rd = 
        	 request.getRequestDispatcher(path);
        	 rd.forward(request, response);
         }else if("del".equals(command)){
        	 int id = Integer.parseInt(request.getParameter("id"));
        	 service.delPersonById(id);
        	 request.getSession(true).setAttribute("msg", "删除成功");
        	 response.sendRedirect("person");
         }else if("add".equals(command)){
        	 //16:17---16:27
        	 Person p = new Person();
        	 p.setJob("普通员工");
        	 p.setOid(request.getParameter("oid"));
        	 p.setGender(request.getParameter("gender"));
        	 p.setAge(request.getParameter("age"));
        	 p.setAddr(request.getParameter("addr"));
        	 p.setName(request.getParameter("name"));
        	 p.setDescr(request.getParameter("descr"));
        	 p.setTel(request.getParameter("tel"));
        	 service.addPerson(p);
        	 response.sendRedirect("person");
         }
	}
}
