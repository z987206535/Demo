package com.lzjtedu.txu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    //1:加载驱动程序
	static{
		try {
			Class.forName(ConfigUtils.getVal("dbdriver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//2:创建连接
	public static Connection getConnection(){
		try{
		return	DriverManager.getConnection(ConfigUtils.getVal("dburl"),
				ConfigUtils.getVal("dbname"),
				ConfigUtils.getVal("dbpwd"));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	//3:关闭资源
	public static void close(ResultSet rs,
			Statement stmt,
			Connection conn){
	   try {
		if(rs!=null){
			   rs.close();
		   }
		   if(stmt!=null){
			   stmt.close();
		   }
		   if(conn!=null){
			   conn.close();
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
}
