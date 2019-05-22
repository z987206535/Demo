package com.lzjtedu.txu.serviceImpl;

import java.util.List;

import com.lzjtedu.txu.dao.RecordDao;
import com.lzjtedu.txu.daoImpl.RecordDaoImpl;
import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.service.RecordService;

public class RecordServiceImpl implements RecordService {
	
	RecordDao recordDao = new RecordDaoImpl();

	@Override
	public void save(Record record) {
		recordDao.save(record);	
	}

	@Override
	public List<Record> findAll(int pageNo) {
		
		return recordDao.findAll(pageNo);
	}

	@Override
	public List<Record> findByTypes(int types,int pageNo) {
		
		return recordDao.findByTypes(types,pageNo);
	}

	@Override
	public List<Record> findByTypesAndStartTime(int types, String startTime,int pageNo) {
		
		return recordDao.findByTypesAndStartTime(types,startTime,pageNo);
	}

	@Override
	public List<Record> findByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime ,int pageNo) {
		
		return recordDao.findByTypesAndStartTimeAndEndTime(types,startTime,endTime,pageNo);
	}

	@Override
	public List<Record> findByStartTime(String startTime, int pageNo) {
		
		return recordDao.findByStartTime(startTime,pageNo);
	}

	@Override
	public List<Record> findByStartTimeAndEndTime(String startTime, String endTime,int pageNo) {
		
		return recordDao.findByStartTimeAndEndTime(startTime,endTime,pageNo);
	}

	@Override
	public Long findRecordTotalPage() {
		
		return recordDao.findRecordTotalPage();
	}

	@Override
	public Long findRecordTotalPageByTypes(int types) {
		
		return recordDao.findRecordTotalPageByTypes(types);
	}

	@Override
	public Long findRecordTotalPageByTypesAndStartTime(int types, String startTime) {
		
		return recordDao.findRecordTotalPageByTypesAndStartTime(types,startTime);
	}

	@Override
	public Long findRecordTotalPageByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime) {
		
		return recordDao.findRecordTotalPageByTypesAndStartTimeAndEndTime(types, startTime,endTime);
	}

	@Override
	public Long findRecordTotalPageByStartTimeAndEndTime(String startTime, String endTime) {
		
		return recordDao.findRecordTotalPageByStartTimeAndEndTime(startTime, endTime);
	}

	@Override
	public Long findRecordTotalPageByStartTime(String startTime) {

		return recordDao.findRecordTotalPageByStartTime(startTime);
	}

	@Override
	public Record findById(int rid) {
		
		return recordDao.findById(rid);
	}

	@Override
	public void update(Record re) {
		recordDao.update(re);
	}

	@Override
	public int findRecordCount() {
		
		return recordDao.findRecordTotal();
	}

}
