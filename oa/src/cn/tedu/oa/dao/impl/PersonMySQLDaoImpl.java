package cn.tedu.oa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.oa.dao.IPersonDAO;
import cn.tedu.oa.domain.Org;
import cn.tedu.oa.domain.Person;
import cn.tedu.oa.utils.ApplicationException;
import cn.tedu.oa.utils.ConfigUtils;
import cn.tedu.oa.utils.DBUtils;

public class PersonMySQLDaoImpl implements IPersonDAO {

	@Override
	public void save(Person p) {
      String sql = "INSERT INTO t_person VALUES(null,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, p.getName());
		stmt.setString(2, p.getGender());
		stmt.setString(3, p.getJob());
		stmt.setString(4, p.getTel());
		stmt.setString(5, p.getDescr());
		stmt.setString(6, p.getAddr());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
		java.sql.Date now = new java.sql.Date(sdf.parse(p.getAge()).getTime());
		stmt.setDate(7, now);
		}catch(Exception e){
			e.printStackTrace();
		}
		stmt.setInt(8, Integer.parseInt(p.getOid()));
		stmt.executeUpdate();
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(null, stmt, conn);
		}      
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_person WHERE id = ?";
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
	public Person findById(int id) {
		String sql = "SELECT id,name,gender,job,tel,descr,addr,age,oid FROM t_person WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Person person = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if(rs.next()){
			person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setGender(rs.getString("gender"));
			person.setJob(rs.getString("job"));
			person.setTel(rs.getString("tel"));
			person.setDescr(rs.getString("descr"));
			person.setAddr(rs.getString("addr"));
			person.setAge(rs.getString("age"));
			person.setOid(rs.getString("oid"));
		}//15:32---15:37
		}catch(SQLException e){
			throw new ApplicationException(e);
		}finally{
			DBUtils.close(rs, stmt, conn);
		}		
		return person;
	}

	@Override
	public List<Person> findPersonByPageNo(int pageNo) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select p.id,p.name,p.gender,p.job,p.tel,o.name oname");
		sb.append(" from t_org o,t_person p");
		sb.append(" where o.id = p.oid");
		sb.append(" limit ?,?");
		
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
		int offset = (pageNo - 1) * pageSize;
		if(offset<0){offset=0;}
		List<Person> rows = new ArrayList<Person>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		conn = DBUtils.getConnection();
		stmt = conn.prepareStatement(sb.toString());
		stmt.setInt(1, offset);
		stmt.setInt(2, pageSize);
		rs = stmt.executeQuery();
	    while(rs.next()){
	    	Person org = new Person();
			org.setId(rs.getInt("id"));
			org.setName(rs.getString("name"));
			org.setGender(rs.getString("gender"));
			org.setJob(rs.getString("job"));
			org.setTel(rs.getString("tel"));
			org.setOname(rs.getString("oname"));
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
	public long findPersonTotalPage() {
		int pageSize = Integer.parseInt(ConfigUtils.getVal("pagesize"));
		String sql = "SELECT count(id) FROM t_person";
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
