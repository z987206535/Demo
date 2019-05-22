package com.lzjtedu.txu.serviceImpl;

import com.lzjtedu.txu.dao.UserDao;
import com.lzjtedu.txu.daoImpl.UserDaoImpl;
import com.lzjtedu.txu.domain.User;
import com.lzjtedu.txu.service.UserService;

public class UserServiceImpl implements UserService {
	
	public UserDao dao = new UserDaoImpl();

	@Override
	public void saveUser(User user) {
		dao.userSave(user);

	}

	@Override
	public void updatePassword(User user) {
		dao.updatePassword(user.getName(), user.getPassword());
	}

	@Override
	public boolean isUsername(String username) {
		return dao.isUser(username);
	}

	@Override
	public User getUser(String username, String password) {
		
		return dao.findUser(username, password);
	}
	
	
}
