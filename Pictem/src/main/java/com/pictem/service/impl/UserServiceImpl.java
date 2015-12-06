package com.pictem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pictem.dao.UserDao;
import com.pictem.model.User;
import com.pictem.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}
	
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public int delUser(int uid) {
		// TODO Auto-generated method stub
		return userDao.delUser(uid);
	}
}
