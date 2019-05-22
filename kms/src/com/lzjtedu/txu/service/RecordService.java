package com.lzjtedu.txu.service;

import java.util.List;

import com.lzjtedu.txu.domain.Record;

public interface RecordService {

	/**
	 * 添加记录
	 */
	void save(Record record);
	
	/**
	 * 查询所有记录
	 * @param pageNo 
	 * @return
	 */
	List<Record> findAll(int pageNo);

	/**
	 * 通过type查询
	 * @param types
	 * @param pageNo 
	 * @return
	 */
	List<Record> findByTypes(int types, int pageNo);
	/**
	 * 通过type和时间 查询
	 * @param types
	 * @param startTime
	 * @return
	 */
	List<Record> findByTypesAndStartTime(int types, String startTime,int pageNo);

	/**
	 * 通过type和时间 查询
	 * @param types
	 * @param startTime
	 * @param pageNo 
	 * @return
	 */
	List<Record> findByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime, int pageNo);

	/**
	 * 通过指定时间查询
	 * @param startTime
	 * @param pageNo 
	 * @return
	 */
	List<Record> findByStartTime(String startTime, int pageNo);

	/**
	 * 通过时间段查询数据
	 * @param startTime
	 * @param endTime
	 * @param pageNo 
	 * @return
	 */
	List<Record> findByStartTimeAndEndTime(String startTime, String endTime, int pageNo);
	/**
	 * 查询总记录的页数
	 * @return
	 */
	Long findRecordTotalPage();
	/**
	 * 查询指定类型的总页数
	 * @param types
	 * @return
	 */
	Long findRecordTotalPageByTypes(int types);
	
	/**
	 * 查询指定类型和时间的总页数
	 * @param types
	 * @param startTime
	 * @return
	 */
	Long findRecordTotalPageByTypesAndStartTime(int types, String startTime);
	
	/**
	 * 查询指定类型和时间段的总页数
	 * @param types
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Long findRecordTotalPageByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime);

	/**
	 * 查询指定时间段的总页数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Long findRecordTotalPageByStartTimeAndEndTime(String startTime, String endTime);

	/**
	 * 查询指定时间的总页数
	 * @param startTime
	 * @return
	 */
	Long findRecordTotalPageByStartTime(String startTime);

	/**
	 * 通过id查询记录
	 * @param rid
	 * @return
	 */
	Record findById(int rid);
	
	/**
	 * 更新记录
	 * @param re
	 */
	void update(Record re);

	int findRecordCount();

	
	

}
