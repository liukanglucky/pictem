package com.pictem.dao;

import java.util.List;

import com.pictem.common.mybatis.MybatisMapper;
import com.pictem.model.User;

@MybatisMapper
public interface UserDao {
	
	public int insertUser(User user);
	
	public List<User> findAllUser();
	
	public int updateUser(User user);
	
	public int delUser(int id);
}
