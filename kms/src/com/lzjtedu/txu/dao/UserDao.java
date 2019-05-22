package com.lzjtedu.txu.dao;

import com.lzjtedu.txu.domain.User;

public interface UserDao {
	
		//保存用户信息即注册用户是调用
		public void userSave(User user);
		
		// 用户登录，获取id
		public User findUser(String username,String password);
		
		//更新用户信息，只能修改密码
		public void updatePassword(String username, String password);
		
		// 删除用户信息
		public void deleteUserById(int id);
		
		// 注册时，判断用户名是否存在
		public boolean isUser(String name);
		
}
