package com.pictem.common;

import com.pictem.common.BaseConfig;

public class LoadConfig extends BaseConfig{
	
	/** 上传系统标志 当前系统 1-外网上传，2-内网上传 **/
	private static int uploadType;
	
	private static String uploadFilePath;
	
	private static String FILE_PATH = "conf/constant.properties";

	static {
		loadValue(FILE_PATH, new LoadConfig());
	}

	/**
	 * 重新加载配置
	 * 
	 * @author fantasy
	 * @date 2013-9-23
	 */
	public static void reloadValue() {
		reloadValue(FILE_PATH, new LoadConfig());
	}

	public static int getFusysType() {
		return uploadType;
	}

	public static void setFusysType(int fusysType) {
		LoadConfig.uploadType = fusysType;
	}

	public static String getUploadFilePath() {
		return uploadFilePath;
	}

	public static void setUploadFilePath(String uploadFilePath) {
		LoadConfig.uploadFilePath = uploadFilePath;
	}
}
