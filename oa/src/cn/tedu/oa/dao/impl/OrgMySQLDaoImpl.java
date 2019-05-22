package cn.tedu.oa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.oa.dao.IOrgDAO;
import cn.tedu.oa.domain.Org;
import cn.tedu.oa.utils.ApplicationException;
import cn.tedu.oa.utils.ConfigUtils;
import cn.tedu.oa.utils.DBUtils;

public class OrgMySQLDaoImpl implements IOrgDAO{

	//添中记录更新sn 
	@Override
	public void save(Org org) {
		//1:添加记录
		String sql = "insert into t_org values(null,?,?,?,?,?)";
		//2:更新sn
		String updatesql = "update t_org set sn = ?,pname=? where id = ?";
		//3:查询父机构 sn/name
		String selectsql = "select sn,name from t_org where id = ?";
		
		//4:连接
		Connection conn = null;
		//5:三条sql对应三个stmt对象
		PreparedStatement stmt = null;
		PreparedStatement updatestmt = null;
		PreparedStatement selectstmt = null;
		ResultSet srs = null;
		String pname = "";
		try{
		//6:获取连接执行添加	
		conn = DBUtils.getConnection();
		//7:获取添加记录id,设置标记
		stmt = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, org.getName());
		stmt.setString(2, org.getSn());
		stmt.setString(3, org.getDescr());
		stmt.setInt(4, org.getPid());
		stmt.setString(5, org.getPname());
		stmt.executeUpdate();
		//8:获取新添加记录id值
		ResultSet rs = stmt.getGeneratedKeys();
		//9:移动光标
		rs.next();
		//10:获取id值
		int id = rs.getInt(1);
		//11: 更新 sn
		updatestmt = conn.prepareStatement(updatesql);
		String sn = "";
		if(org.getPid() != 0){
		 //12：查询stmt	
		 selectstmt = conn.prepareStatement(selectsql);
		 selectstmt.setInt(1, org.getPid());
		 //13:查询结果集
		 srs = selectstmt.executeQuery();
		 if(srs.next()){
			sn =  srs.getString("sn");
			pname = srs.getString("name");
		 }
		 sn  = sn+"_"+id;
		}else{
		 sn = id+"";	
		}
		//14:更新
		updatestmt.setString(1, sn);
		updatestmt.setString(2, pname);
        updatestmt.setInt(3, id);
        updatestmt.executeUpdate();
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(srs, null, null);				
			DBUtils.close(null, selectstmt, null);			
			DBUtils.close(null, updatestmt, null);
			DBUtils.close(null, stmt, conn);
		}
		
		
	}

	@Override
	public int getPPid(int pid) {
		if(pid==0){return 0;}
		String sql = "SELECT pid FROM t_org WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ppid = 0;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pid);
		rs = stmt.executeQuery();
		if(rs.next()){
			ppid = rs.getInt("pid");
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}		
		return ppid;		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_org WHERE id = ?";
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
	public void update(Org org) {
	}

	/**
	 * 依据id查找机构对象
	 * 1:如果该机构不存在返回null
	 * 2:如果机构存在返回对象
	 */
	public Org findById(int id) {
		String sql = "SELECT id,name,sn,pname,pid,descr FROM t_org WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Org org = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if(rs.next()){
			org = new Org();
			org.setId(rs.getInt("id"));
			org.setName(rs.getString("name"));
			org.setSn(rs.getString("sn"));
			org.setPid(rs.getInt("pid"));
			org.setPname(rs.getString("pname"));
			org.setDescr(rs.getString("descr"));
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}		
		return org;
	}

	@Override
	public List<Org> findAll() {
		String sql = "SELECT id,name,sn,pname,pid,descr FROM t_org ";
		List<Org> rows = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
	    while(rs.next()){
			Org org = new Org();
			org.setId(rs.getInt("id"));
			org.setName(rs.getString("name"));
			org.setSn(rs.getString("sn"));
			org.setPid(rs.getInt("pid"));
			org.setPname(rs.getString("pname"));
			org.setDescr(rs.getString("descr"));
			rows.add(org);
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}	
		return rows;
	}
   
	//16:40--17:00
	//SELECT * FROM t_org LIMIT 20,10
	//1    -> 0 
	//2    -> (2-1)*10
	@Override
	public List<Org> findOrgByPageNo(int pageNo, int pid) {
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
		int offset = (pageNo - 1) * pageSize;
		if(offset<0){offset=0;}
		String sql = "SELECT id,name,sn,pname,pid,descr FROM t_org WHERE pid = ? LIMIT ?,? ";
		List<Org> rows = new ArrayList<Org>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pid);
		stmt.setInt(2, offset);
		stmt.setInt(3, pageSize);
		rs = stmt.executeQuery();
	    while(rs.next()){
			Org org = new Org();
			org.setId(rs.getInt("id"));
			org.setName(rs.getString("name"));
			org.setSn(rs.getString("sn"));
			org.setPid(rs.getInt("pid"));
			org.setPname(rs.getString("pname"));
			org.setDescr(rs.getString("descr"));
			rows.add(org);
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}	
		return rows;
	}

	@Override
	public long findOrgTotalPage(int pid) {
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));

		String sql = "SELECT count(id) FROM t_org WHERE pid = ?";
		long count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pid);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count % pageSize == 0 ?
				count/pageSize:
						count/pageSize+1;
	}

	@Override
	public boolean isChildren(int id) {
		String sql = "SELECT count(id) FROM t_org WHERE pid=?";
		long count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
	    if(rs.next()){
			count = rs.getInt(1);
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return count>0;
	}

}
