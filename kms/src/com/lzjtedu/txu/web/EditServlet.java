package com.lzjtedu.txu.web;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.service.RecordService;
import com.lzjtedu.txu.serviceImpl.RecordServiceImpl;


public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RecordService  recordService = new RecordServiceImpl();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		//准备数据
		int rid = Integer.parseInt(request.getParameter("rid"));
		int currentPage = Integer.parseInt(request.getParameter("CurrentPage"));
		//PrintWriter out = response.getWriter();
		Record record = recordService.findById(rid);
		request.setAttribute("record", record);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		rd.forward(request, response);
		
		
	}

}
