package com.lzjtedu.txu.dao;

import java.util.List;
import com.lzjtedu.txu.domain.Record;


public interface RecordDao {
	
	// 保存记录
	public void save(Record record);
	
	//删除记录
	public void delete(int id);
	
	//更改记录
	public void update(Record record);
	
	
	
	// 查找所有的记录
	public List<Record> findAll(int pageNo);
	
	//带有一个条件的查询
	public List<Record> findByTypes(int types,int pageNo);

	//类型和时间
	public List<Record> findByTypesAndStartTime(int types, String startTime, int pageNo);

	//类型和时间段
	public List<Record> findByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime, int pageNo);

	//时间查询
	public List<Record> findByStartTime(String startTime, int pageNo);

	//时间段查询
	public List<Record> findByStartTimeAndEndTime(String startTime, String endTime, int pageNo);

	//查询总记录页数
	public Long findRecordTotalPage();

	//查询指定类型的页数
	public Long findRecordTotalPageByTypes(int types);
	
	//查询指定类型和时间的页数
	public Long findRecordTotalPageByTypesAndStartTime(int types, String startTime);

	//查询指定类型和时间段的页数
	public Long findRecordTotalPageByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime);

	//查询指定时间段的页数
	public Long findRecordTotalPageByStartTimeAndEndTime(String startTime, String endTime);

	//查询指定时间的页数
	public Long findRecordTotalPageByStartTime(String startTime);

	//通过id查询记录
	public Record findById(int rid);
	//查询总的记录数
	public int findRecordTotal();

}
