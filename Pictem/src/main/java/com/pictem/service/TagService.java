package com.pictem.service;

import java.util.List;

import com.pictem.model.Tag;

public interface TagService {
	public int insertTag(Tag tag);
	
	public List<Tag> findAllTag();
	
	
}
