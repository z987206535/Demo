package cn.lzjtu.tuxunlu;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 
			//建立数据库连接  
			String url ="jdbc:mysql://localhost:3306/db_database01?user=root&password=123456";  
			 conn= DriverManager.getConnection(url);  
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(java.sql.Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pstmt){
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
