package com.pictem.dao;

import java.util.List;

import com.pictem.common.mybatis.MybatisMapper;
import com.pictem.model.Tag;

@MybatisMapper
public interface TagDao {
	public int insertTag(Tag tag);
	
	public List<Tag> findAllTag();
	
}
