package com.pictem.service;

import java.util.List;

import com.pictem.model.User;

public interface UserService {
	public int insertUser(User user);
	
	public List<User> findAllUser();
}
