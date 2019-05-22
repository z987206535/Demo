package com.lzjtedu.txu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.service.RecordService;
import com.lzjtedu.txu.serviceImpl.RecordServiceImpl;


public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		RecordService recordService = new RecordServiceImpl();
		String times = request.getParameter("times");
		System.out.println(times);
		String matter = request.getParameter("matter");
		//System.out.println(matter);
		Double cost = Double.valueOf(request.getParameter("cost"));
		//System.out.println(cost);
		int types = Integer.parseInt(request.getParameter("type"));
		//System.out.println(types);
		String remark = request.getParameter("remark");
		//System.out.println(remark);
		Record re = new Record();
		
		re.setTimestamp(times);
		re.setMatter(matter);
		re.setCost(cost);
		re.setRemark(remark);
		re.setType(types);
		recordService.save(re);
		String path = "list";
		response.sendRedirect(path);
		
	}

}
