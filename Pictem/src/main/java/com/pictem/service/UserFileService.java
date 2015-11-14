package com.pictem.service;


import java.util.List;
import java.util.Map;

import com.pictem.model.UserFile;


/**
 * 
 * 创建人：liukang <br>
 * 功能描述： 用户上传下载文件操作DAO<br>
 */
public interface UserFileService {
		
	
	/**
	 * 保存上传文件
	 * 
	 * @param userFile
	 */
	public void save(UserFile userFile);

	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 */
	public List<UserFile> find(Map<String, Object> map);
	
	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 */
	public List<UserFile> findPage(Map<String, Object> map);
}
