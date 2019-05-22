package cn.tedu.oa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.tedu.oa.dao.IRoleDAO;
import cn.tedu.oa.domain.Person;
import cn.tedu.oa.domain.Role;
import cn.tedu.oa.utils.ApplicationException;
import cn.tedu.oa.utils.DBUtils;

public class RoleMySQLDAOImpl extends AbstractBaseDAO<Role> implements IRoleDAO {

	@Override
	public void save(Role r) {
		String sql = "INSERT INTO t_role VALUES(null,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, r.getName());
		stmt.executeUpdate();
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(null, stmt, conn);
		}  
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_role where id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(null, stmt, conn);
		}
	}

	@Override
	public Role findById(int id) {
		String sql = "SELECT id,name,url FROM t_role WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Role person = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if(rs.next()){
			person = new Role();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));

		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}		
		return person;
	}

	@Override
	public List<Role> findRoleByPageNo(int pageNo) {
		return findByPageNo("t_role", " ", pageNo, new InitPO<Role>() {
			
			@Override
			public Role init(ResultSet rs) {
				Role role = null;
				try {
					role = new Role();
					role.setId(rs.getInt("id"));
					role.setName(rs.getString("name"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return role;
			}
		});
	}

	@Override
	public long findRoleTotalPage() {
		return findTotalPage("t_role", " ");
	}

	/**
	 * 1:如果角色对某个模块没有授权,现在添加 t_acl
	 * 2:如果角色对某个模块有授权,  现在更新    t_acl
	 * 如果解决
	 * 去t_acl rid mid 是否在授权表
	 * 如果存在你更新
	 * 如果不存在添加
	 * 
	 * 示例: rid 1 mid 1  C  0 
	 * 1:SELECT count(id) from t_acl
	 *   where mid = 1 and rid = 1;
	 * 1
	 * 更新 update
	 * 0 
	 * 添加 insert  
	 */
	@Override
	public void setAcl(int rid, int mid, String opt, int val) {
		//1:判断该记录是否存在
		boolean rs = this.isMidRid(rid, mid);
		String sql = "";
		if(rs){
			//更新
			sql = "update t_acl set "+opt+"='"+val+"' where rid=? and mid = ?";
		}else{
			//添加
			sql = "insert into t_acl(rid,mid,"+opt+")values(?,?,1)";
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, rid);
		stmt.setInt(2, mid);
		stmt.executeUpdate();
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(null, stmt, conn);
		}	
		
	}
	
	//内部方法为setAcl方法
	public  boolean isMidRid(int rid,int mid){
		String sql = "SELECT count(id) FROM t_acl WHERE rid = ? and mid = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, rid);
		stmt.setInt(2, mid);
		
		rs = stmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1) > 0;
		}
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}		
		return result;	
	}
	
	
	
	
	
	

}
