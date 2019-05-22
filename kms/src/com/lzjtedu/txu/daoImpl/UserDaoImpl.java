package com.lzjtedu.txu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lzjtedu.txu.dao.UserDao;
import com.lzjtedu.txu.domain.User;
import com.lzjtedu.txu.util.DBUtils;
import com.lzjtedu.txu.util.MD5Utils;

public class UserDaoImpl implements UserDao{
	
	private Connection conn= null;
	private PreparedStatement stmt = null;
	private ResultSet rt = null;
	
	//查看用户信息
	@Override
	public User findUser(String username, String password) {
		String sql = "SELECT * FROM user WHERE name=? AND password=?";
		User user = null;
		// 建立连接
		conn = DBUtils.getConnection();
		try {
			// 创建语句对象
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,username);
			stmt.setString(2, MD5Utils.md5(password));
			// 执行sql语句
			rt = stmt.executeQuery();
			if(rt.next()){
				user = new User();
				user.setId(rt.getInt("uid"));
				user.setName(rt.getString("name"));
				user.setRealName(rt.getString("realName"));
				user.setAddress(rt.getString("address"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 关闭连接
			DBUtils.close(rt, stmt, conn);
		}
		return user;
	}

	// 修改密码
	@Override
	public void updatePassword(String username, String password) {
		String sql = "UPDATE user SET password = ? WHERE name=?";
		//建立连接
		conn = DBUtils.getConnection();
		try {
			// 创建语句对象
			stmt = conn.prepareStatement(sql);
			stmt.setString(2,username);
			stmt.setString(1, MD5Utils.md5(password));
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
	
	//删除用的所有信息
	@Override
	public void deleteUserById(int uid) {
		String sql = "DELETE FROM user WHERE uid=?";
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, uid);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rt, stmt, conn);
		}
	}
	// 注册
	@Override
	public void userSave(User user) {
		String sql = "INSERT INTO user(uid,name,password,address) VALUES(null,?,?,?)";
		//建立连接
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getName());
			stmt.setString(2, MD5Utils.md5(user.getPassword()));
			stmt.setString(3, user.getAddress());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null, stmt, conn);
		}
	}
	//注册检验
	@Override
	public boolean isUser(String name) {
		String sql = "SELECT * FROM user WHERE name=?";
		// 建立连接
		conn = DBUtils.getConnection();
		try {
			// 
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name);
			// 执行sql语句
			rt = stmt.executeQuery();
			if(rt.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 关闭连接
			DBUtils.close(rt, stmt, conn);
		}
		return false;
	}

	
}
