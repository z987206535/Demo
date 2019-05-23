package cn.lzjtu.tuxunlu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDao {
	public static boolean isValidUser(User user){
		Connection conn;
		PreparedStatement pst;
		String sql;
		ResultSet rs;
		try{
			conn = DBConnection.getConnection();
			sql = "select * from tb_user where user = ? and password=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,user.getUser());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
}

