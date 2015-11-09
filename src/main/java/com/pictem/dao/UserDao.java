package com.pictem.dao;

import java.util.List;

import com.pictem.model.User;

public interface UserDao {
	
	public int insertUser(User user);
	
	public List<User> findAllUser();
}
