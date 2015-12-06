package com.pictem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pictem.dao.TagDao;
import com.pictem.model.Tag;
import com.pictem.service.TagService;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagDao tagDao;

	@Override
	public int insertTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagDao.insertTag(tag);
	}

	@Override
	public List<Tag> findAllTag() {
		// TODO Auto-generated method stub
		return tagDao.findAllTag();
	}
}
