package com.lzjtedu.txu.service;

import com.lzjtedu.txu.domain.User;

public interface UserService {
	
	// 保存用户信息
	public void saveUser(User user);
	// 修改密码
	public void updatePassword(User user);
	
	// 判断用户是否存在
	public boolean isUsername(String username); 
	
	//用户登录，判断用户是否存在
	public User getUser(String username, String password);

}
