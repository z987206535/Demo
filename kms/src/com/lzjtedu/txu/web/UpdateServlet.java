package com.lzjtedu.txu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.service.RecordService;
import com.lzjtedu.txu.serviceImpl.RecordServiceImpl;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		RecordService recordService = new RecordServiceImpl();
		//获取当前页信息
		int currentPage = Integer.parseInt(request.getParameter("CurrentPage"));
		
		//获取Record信息
		int rid = Integer.parseInt(request.getParameter("rid"));
		//System.out.println(rid);
		String times = request.getParameter("times");
		//System.out.println(times);
		String matter = request.getParameter("matter");
		//System.out.println(matter);
		Double cost = Double.valueOf(request.getParameter("cost"));
		//System.out.println(cost);
		int types = Integer.parseInt(request.getParameter("type"));
		//System.out.println(types);
		String remark = request.getParameter("remark");
		//System.out.println(remark);
		Record re = new Record();
		re.setId(rid);
		re.setTimestamp(times);
		re.setMatter(matter);
		re.setCost(cost);
		re.setRemark(remark);
		re.setType(types);
		recordService.update(re);
		String path = "list?CurrentPage="+currentPage;
		request.getRequestDispatcher(path).forward(request, response);
	}

}
