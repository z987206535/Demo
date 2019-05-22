package cn.tedu.oa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.oa.dao.IModule;
import cn.tedu.oa.domain.Module;
import cn.tedu.oa.domain.Org;
import cn.tedu.oa.utils.ApplicationException;
import cn.tedu.oa.utils.DBUtils;

public class ModuleMySQLImpl extends AbstractBaseDAO<Module> implements IModule {

	@Override
	public void save(Module m) {
		    String sql = "INSERT INTO t_module VALUES(null,?,?)";
			Connection conn = null;
			PreparedStatement stmt = null;
			try{
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getName());
			stmt.setString(2, m.getUrl());
			stmt.executeUpdate();
			}catch(SQLException e){
				throw new ApplicationException(e);
			}finally{
				DBUtils.close(null, stmt, conn);
			}     
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public Module findById(int id) {
		return null;
	}

	@Override
	public List<Module> findAll() {
		String sql = "SELECT * FROM t_module ";
		List<Module> rows = new ArrayList<Module>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
	    while(rs.next()){
	    	Module org = new Module();
			org.setId(rs.getInt("id"));
			org.setName(rs.getString("name"));
			org.setUrl(rs.getString("url"));
			rows.add(org);
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}	
		return rows;
	}

}
