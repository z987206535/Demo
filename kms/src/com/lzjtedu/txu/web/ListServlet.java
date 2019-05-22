package com.lzjtedu.txu.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjtedu.txu.domain.PageBeen;
import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.service.RecordService;
import com.lzjtedu.txu.serviceImpl.RecordServiceImpl;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecordService  recordService = new RecordServiceImpl();
		PageBeen pageBeen = new PageBeen();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		List<Record> list = null;
		//分页数据准备
		 pageBeen.setCurrentPage(1);
		 int types = 0;
		 String startTime = "";
		 String endTime = "";
		 if(request.getParameter("types") != null){
			 types = Integer.parseInt(request.getParameter("types"));
		 }
		if(request.getParameter("CurrentPage")!=null){
			pageBeen.setCurrentPage(Integer.parseInt(request.getParameter("CurrentPage")));
    	}
	//	request.setAttribute("pageBeen", pageBeen);
		
		request.setAttribute("types", types);
		if(request.getParameter("startTime") != null){
			startTime = request.getParameter("startTime");
		}
		request.setAttribute("startTime", startTime);
		if(request.getParameter("endTime") != null){
			endTime = request.getParameter("endTime");
		}
		request.setAttribute("endTime", endTime);
		
		System.out.println("类型:"+types + "起始时间:"+ startTime + "结束时间:"+endTime);
		 
		if(types != 0  && (null == startTime || "".equals(startTime)) && (null == endTime || "".equals(endTime))){
			//按照类型查询
			System.out.println("按照类型查询");
			request.setAttribute("page", recordService.findRecordTotalPageByTypes(types));
			pageBeen.setRecordList(recordService.findByTypes(types,pageBeen.getCurrentPage()));
		}else if(types != 0  && !("".equals(startTime)) && (null == endTime || "".equals(endTime))){
			//按照指定的类型和时间查询
			System.out.println("按照指定的类型和时间查询");
			request.setAttribute("page", recordService.findRecordTotalPageByTypesAndStartTime(types,startTime));
			pageBeen.setRecordList(recordService.findByTypesAndStartTime(types,startTime,pageBeen.getCurrentPage()));
		}else if(types != 0  && !("".equals(startTime)) && !("".equals(endTime)) ){
			//按照指定的类型，和时间段查询.
			System.out.println("按照指定的类型，和时间段查询.");
			request.setAttribute("page", recordService.findRecordTotalPageByTypesAndStartTimeAndEndTime(types,startTime,endTime));
			pageBeen.setRecordList(recordService.findByTypesAndStartTimeAndEndTime(types,startTime,endTime,pageBeen.getCurrentPage()));
		}else if (types == 0  && !("".equals(startTime))  && !("".equals(endTime)) ){
			//按照指定的时间段查询
			System.out.println("按照指定的时间段查询.");
			request.setAttribute("page", recordService.findRecordTotalPageByStartTimeAndEndTime(startTime,endTime));
			pageBeen.setRecordList(recordService.findByStartTimeAndEndTime(startTime,endTime,pageBeen.getCurrentPage()));
		}else if(types == 0 && !("".equals(startTime))){ 
			//按指定时间查询，指定时间默认放在startTime中.
			System.out.println("按指定时间查询，指定时间默认放在startTime中.");
			request.setAttribute("page", recordService.findRecordTotalPageByStartTime(startTime));
			pageBeen.setRecordList(recordService.findByStartTime(startTime,pageBeen.getCurrentPage()));
		}else{
			//默认查询方式，查询所有.
			System.out.println("默认查询方式，查询所有.");
			pageBeen.setPageCount(recordService.findRecordTotalPage());
			System.out.println(pageBeen.toString());
			pageBeen.setRecordList(recordService.findAll(pageBeen.getCurrentPage()));
			pageBeen.setRecordCount(recordService.findRecordCount());
			request.setAttribute("pageBeen",pageBeen );
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		rd.forward(request, response);
	}

}
