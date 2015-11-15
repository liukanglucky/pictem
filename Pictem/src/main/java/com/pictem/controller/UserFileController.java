package com.pictem.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pictem.common.Constants;
import com.pictem.model.UserFile;
import com.pictem.service.UserFileService;
import com.pictem.util.*;

/**
 * 
 * 创建人：liukang <br>
 * 功能描述： 用户文件上传下载<br>
 */
@Controller
@RequestMapping("/userFile")
public class UserFileController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "userFileService")
	private UserFileService userFileService;
	
	public String getFolder(MultipartHttpServletRequest request, HttpServletResponse response, Map<String, Object> result){
	    if(StringUtil.isEmpty(Constants.UPLOAD_FILE_PATH)){
	        return request.getSession().getServletContext().getRealPath("/");
	    }
	    return Constants.UPLOAD_FILE_PATH;
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		String folder = "";
		try {
			folder = getFolder(request, response, result);
		} catch (Exception ex) {
			result.put("message", "获取folder失败");
			return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
		}
		if (StringUtil.isEmpty(folder)) {// step-1 获得文件夹
			response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			if (!result.containsKey("message")) {
				result.put("message", "处理失败");
			}
			return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
		}
		if (handler(request, response, result, folder)) {
			result.put("status", "success");
			result.put("message", "上传成功");
		}
		return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	
	/**
	 * 处理文件上传
	 */
	public boolean handler(MultipartHttpServletRequest request, HttpServletResponse response, Map<String, Object> result, String folder) throws IOException{
		Date baseDate = new Date();
		String fileName = DateTime.toDate("yyyyMMddHHmmss", baseDate);
		MultipartFile file = request.getFile("file");
		if (file == null) {// step-2 判断file
			return getError("文件内容为空", HttpStatus.UNPROCESSABLE_ENTITY, result, response, null);
		}
		String orgFileName = file.getOriginalFilename();
		orgFileName = (orgFileName == null) ? "" : orgFileName;
		Pattern p = Pattern.compile("\\s|\t|\r|\n");
        Matcher m = p.matcher(orgFileName);
        orgFileName = m.replaceAll("_");
		String realFilePath = folder  + File.separator + "admin" + File.separator;
		if(!(new File(realFilePath).exists())){
			new File(realFilePath).mkdirs();
		}
		String bigRealFilePath = realFilePath  + File.separator + FilenameUtils.getBaseName(orgFileName).concat(".") + fileName.concat(".").concat(FilenameUtils.getExtension(orgFileName).toLowerCase());
		if (file.getSize() > 0) {
			File targetFile = new File(folder.concat(orgFileName));
			file.transferTo(targetFile);//写入目标文件
		}
		
		return true;
	}
	
	boolean getError(String message, HttpStatus status, Map<String, Object> result, HttpServletResponse response, Exception ex) {
		response.setStatus(status.value());
		result.put("message", message);
		LOG.warn(message, ex);
		return false;
	}
	
	/**文件下载**/
    @RequestMapping("download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		String fileId = request.getParameter("fileId");
    		if(StringUtil.isEmpty(fileId)){
    			map.put("status", "error");
    			map.put("message", "下载错误");
    			return ajaxJson(JsonUtil.getJsonString4JavaPOJO(map), response);
    		}
        	map.put("file_id", fileId);
        	List<UserFile> list = userFileService.find(map);
        	UserFile file = list.get(0);
			FileOperateUtil.download(request, response, "application/octet-stream; charset=utf-8", file.getFilePath(), file.getFileName());
			return null;
		} catch (IOException e) {
			logger.error("文件下载出错");
			map.put("status", "error");
			map.put("message", "下载错误");
		}
        return ajaxJson(JsonUtil.getJsonString4JavaPOJO(map), response);
    }

   
    
}