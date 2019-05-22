package com.lzjtedu.txu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.lzjtedu.txu.dao.RecordDao;
import com.lzjtedu.txu.domain.Record;
import com.lzjtedu.txu.util.DBUtils;
import com.lzjtedu.txu.util.ConfigUtils;

public class RecordDaoImpl implements RecordDao{
	private Connection conn= null;
	private PreparedStatement stmt = null;
	private ResultSet rs= null;
	private int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
	

	/*
	 * 保存数据
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#save(com.lzjtedu.txu.domain.Record)
	 */
	@Override
	public void save(Record record) {
		String sql = "INSERT INTO record(times,matter,types,cost,remark) values(?,?,?,?,?);";
		//建立连接
		conn = DBUtils.getConnection();
		try {
			//创建语句对象
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, record.getTimestamp());
			stmt.setString(2, record.getMatter());
			stmt.setInt(3, record.getType());
			stmt.setDouble(4, record.getCost());
			stmt.setString(5,record.getRemark());
			//执行sql语句
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭连接
			DBUtils.close(rs, stmt, conn);
		}
	}

	/*
	 * 删除数据
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		String sql="DELETE FROM record WHERE rid = ?";
		//建立连接
		conn = DBUtils.getConnection();
		try {
			//创建语句对象
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			//执行sql语句
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 关闭连接
			DBUtils.close(rs, stmt, conn);
		}
	}
	
	/*
	 * 更新数据
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#update(com.lzjtedu.txu.domain.Record)
	 */
	@Override
	public void update(Record record) {
		String sql = "UPDATE record SET matter=?,types=?,cost=?,remark=? WHERE rid=?";
		conn = DBUtils.getConnection();
		try { 
			// 创建语句对象
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, record.getMatter());
			stmt.setInt(2, record.getType());
			stmt.setDouble(3, record.getCost());
			stmt.setString(4,record.getRemark());
			stmt.setInt(5, record.getId());
			//执行sql语句
			stmt.execute();
			// 处理结果集 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 关闭连接
			DBUtils.close(null, stmt, conn);
		}
	}


	/*
	 * 查询所有数据
	 * (non-Javadoc) 
	 * @see com.lzjtedu.txu.dao.RecordDao#findAll()
	 */
	@Override
	public List<Record> findAll(int pageNo) {
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try { 
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, (pageNo-1)*pageSize);  
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}

	/*
	 * 按照类型查询
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findByTypes(int)
	 */
	@Override
	public List<Record> findByTypes(int types , int pageNo) {
		
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record WHERE types = ? ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, types);
			stmt.setInt(2, (pageNo-1)*pageSize);
			stmt.setInt(3,pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	/*
	 * 按照指定的类型和时间查询
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findByTypesAndStartTime(int, java.lang.String)
	 */
	@Override
	public List<Record> findByTypesAndStartTime(int types, String startTime, int pageNo) {
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record WHERE types = ? AND  times = ? ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, types);
			stmt.setString(2, startTime);
			stmt.setInt(3, (pageNo-1)*pageSize);
			stmt.setInt(4, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}	

	
	/*
	 * 按照指定的类型，和时间段查询.
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findByTypesAndStartTimeAndEndTime(int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Record> findByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime,int pageNo) {
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record WHERE types = ? AND  times >= ? AND times <= ? ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, types);
			stmt.setString(2, startTime);
			stmt.setString(3, endTime);
			stmt.setInt(4, (pageNo-1)*pageSize);
			stmt.setInt(5, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	/*
	 * 按指定时间查询，指定时间默认放在startTime中.
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findByStartTime(java.lang.String)
	 */
	@Override
	public List<Record> findByStartTime(String startTime, int pageNo) {
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record WHERE times = ? ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, startTime);
			stmt.setInt(2, (pageNo-1)*pageSize);
			stmt.setInt(3, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}
	
	/*
	 * 按时间段查询
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findByStartTimeAndEndTime(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Record> findByStartTimeAndEndTime(String startTime, String endTime ,int pageNo) {
		List<Record> list = new ArrayList<Record>();
		String sql = "SELECT * FROM record WHERE times >= ? AND times <= ? ORDER BY times ASC LIMIT ?,?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, startTime);
			stmt.setString(2, endTime);
			stmt.setInt(3, (pageNo-1)*pageSize);
			stmt.setInt(4, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Record re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return list;
	}
	
	 
	/*
	 * 查询默认情况下总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPage()
	 */
	@Override
	public Long findRecordTotalPage() {
		String sql = "SELECT count(rid) FROM record";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		if(count == 0){
			return count+1;
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}
	
	
	/*
	 * 查询指定类型的总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByTypes(int)
	 */
	@Override
	public Long findRecordTotalPageByTypes(int types) {
		String sql = "SELECT count(rid) FROM record WHERE types = ?";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, types);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}

	/*
	 * 查询指定类型和时间的总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByTypesAndStartTime(int, java.lang.String)
	 */
	@Override
	public Long findRecordTotalPageByTypesAndStartTime(int types, String startTime) {
		String sql = "SELECT count(rid) FROM record WHERE types = ? AND times = ?";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, types);
		stmt.setString(2, startTime);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}

	
	/*
	 * 查询指定类型和时间段的总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByTypesAndStartTimeAndEndTime(int, java.lang.String, java.lang.String)
	 */
	@Override
	public Long findRecordTotalPageByTypesAndStartTimeAndEndTime(int types, String startTime, String endTime) {
		String sql = "SELECT count(rid) FROM record WHERE types = ? AND times >= ? AND times <= ?";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, types);
		stmt.setString(2, startTime);
		stmt.setString(3, endTime);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}
	
	/*
	 * 查询指定时间段的总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByStartTimeAndEndTime(java.lang.String, java.lang.String)
	 */
	@Override
	public Long findRecordTotalPageByStartTimeAndEndTime(String startTime, String endTime) {
		String sql = "SELECT count(rid) FROM record WHERE times >= ? AND times <= ?";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, startTime);
		stmt.setString(2, endTime);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}
	
	/*
	 * 查询指定时间的总页数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByStartTime(java.lang.String)
	 */
	@Override
	public Long findRecordTotalPageByStartTime(String startTime) {
		String sql = "SELECT count(rid) FROM record WHERE times = ?";
		long count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, startTime);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}

	
	
	/*
	 * 查询总记录数
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findRecordTotalPageByStartTime(java.lang.String)
	 */
	@Override
	public int findRecordTotal() {
		String sql = "SELECT count(rid) FROM record";
		int count = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count;
	}
	
	
	
	/*
	 * 通过id查询记录
	 * (non-Javadoc)
	 * @see com.lzjtedu.txu.dao.RecordDao#findById(int)
	 */
	@Override
	public Record findById(int rid) {
		Record re = null;
		String sql = "SELECT * FROM record WHERE rid = ?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rid);
			rs = stmt.executeQuery();
			if(rs.next()){
				re = new Record();
				re.setId(rs.getInt("rid"));
				re.setTimestamp(rs.getString("times"));
				re.setMatter(rs.getString("matter"));
				re.setCost(rs.getDouble("cost"));
				re.setType(rs.getInt("types"));
				re.setRemark(rs.getString("remark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return re;
	}
	
	
	
	
	public  void del(){
		
		String sql="delete from record where times=null";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		
		
	}

	
	
	/*public static void main(String[] args) {
		Record record = new Record();
		record.setMatter("修理配件");
		record.setRemark("测试");
		
		for (int i = 0; i < 50; i++) {
			record.setTimestamp("2018-03-2"+i);
			record.setCost(100.00+20*i);
			if(i%2 == 0){
				record.setType(0);
			}else{
				record.setType(1);
			}
			
			
			new RecordDaoImpl().save(record);
		}
		}
		*
		*/
	
	}

	

	

	
	

