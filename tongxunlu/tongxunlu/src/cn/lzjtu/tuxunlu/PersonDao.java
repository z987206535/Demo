package cn.lzjtu.tuxunlu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDao {

	public static void delete(String  number){
		Connection conn=DBConnection.getConnection();
		PreparedStatement ps=null;
		String deleteSql="delete from tb_tongxunlu where usernumber=? ";
	
		try {
			
			ps=conn.prepareStatement(deleteSql);
			ps.setString(1, number);
		    ps.executeUpdate();
			
		} catch (SQLException e) {
		     e.printStackTrace();
		}finally{
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
	}
	public static void insert(Person  per){
		String Name,Phone,Relation,Email;
		Name = per.getName();
		Phone = per.getNumber();
		Relation = per.getRelation();
		Email = per.getEmail();
		Connection conn=DBConnection.getConnection(); //连接数据库
		PreparedStatement ps=null;
		String addSql="insert into tb_tongxunlu(username,usernumber,relation,email) values('"+Name+"','"+Phone+"','"+Relation+"','"+Email+"')";
				try {
					ps=conn.prepareStatement(addSql);
				    ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DBConnection.close(ps);
					DBConnection.close(conn);
				}// 执行插入语句（将表单提交的内容放进数据库）
	}
	
	public static  List<Person> update(String name) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement ps=null;
		String updateSql="select * from tb_tongxunlu where username = '"+name+"'";
		
		try {
			ps=conn.prepareStatement(updateSql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Person pi=new Person();
				pi.setName(rs.getString(1));
				pi.setNumber(rs.getString(2));
				pi.setRelation(rs.getString(3));
				pi.setEmail(rs.getString(4));
				list.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
		return list;
	}
	

	
}
