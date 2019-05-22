package com.lzjtedu.txu.domain;

import java.util.List;

import com.lzjtedu.txu.util.ConfigUtils;

public class PageBeen {
	
	//1、总的记录数
	private int recordCount;
	//2、每页显示多少条记录
	private int pageSize;
	//3、本页的数据列表
	private List<Record> recordList;
	
	//4、总共分了多少页
	private Long pageCount;
	//5、当前是第几页
	private int currentPage;
	//6、页码列表的开始索引
	private int beginPageIndex;
	//7、页码列表的结束索引
	private int endPageIndex;
	
	public PageBeen() {
		this.pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
	}
	
	
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Record> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}


	@Override
	public String toString() {
		return "PageBeen [recordCount=" + recordCount + ", pageSize=" + pageSize + ", recordList=" + recordList
				+ ", pageCount=" + pageCount + ", currentPage=" + currentPage + ", beginPageIndex=" + beginPageIndex
				+ ", endPageIndex=" + endPageIndex + "]";
	}


	
	
	

}
