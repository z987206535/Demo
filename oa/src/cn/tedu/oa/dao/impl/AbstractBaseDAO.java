package cn.tedu.oa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.oa.utils.ApplicationException;
import cn.tedu.oa.utils.ConfigUtils;
import cn.tedu.oa.utils.DBUtils;

public abstract class AbstractBaseDAO<T> {
	/**
	 * 
	 * @param tableName  表名
	 * @param where      查询条件
	 * @param pageNo     查询第几页
	 * @param po         po{查询保存一个对象准备}
	 * @return
	 */
  public List<T> findByPageNo(String tableName,String where,int pageNo,InitPO<T> po){
	    String sql = "SELECT * FROM "+tableName+where+" limit ?,?";
		//T 代表任意类型 Person/Org/Module/Role/...
	    List<T> rows = new ArrayList<T>();
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
		int offset = (pageNo - 1) * pageSize;
		if(offset<0){offset=0;}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, offset);
		stmt.setInt(2, pageSize);
		rs = stmt.executeQuery();
	    while(rs.next()){
			rows.add(po.init(rs));
		}
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}	
		return rows;
  }
  
  public long findTotalPage(String tableName,String where) {
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));

		String sql = "SELECT count(id) FROM "+tableName+where;
		long count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}
}
