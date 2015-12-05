package com.pictem.common;

import com.pictem.common.BaseConfig;

public class LoadConfig extends BaseConfig{
	
	/** 上传系统标志 当前系统 1-外网上传，2-内网上传 **/
	private static int uploadType;
	
	private static String uploadFilePath;
	
	private static String sendUser;
	
	private static String sendUname;
	
	private static String sendPwd;
	
	private static String keySmtp;
	
	private static String valueSmtp;
	
	private static String keyProps;
	
	private static boolean valueProps;
	
	
	
	public static int getUploadType() {
		return uploadType;
	}

	public static void setUploadType(int uploadType) {
		LoadConfig.uploadType = uploadType;
	}

	public static String getSendUser() {
		return sendUser;
	}

	public static void setSendUser(String sendUser) {
		LoadConfig.sendUser = sendUser;
	}

	public static String getSendUname() {
		return sendUname;
	}

	public static void setSendUname(String sendUname) {
		LoadConfig.sendUname = sendUname;
	}

	public static String getSendPwd() {
		return sendPwd;
	}

	public static void setSendPwd(String sendPwd) {
		LoadConfig.sendPwd = sendPwd;
	}

	public static String getKeySmtp() {
		return keySmtp;
	}

	public static void setKeySmtp(String keySmtp) {
		LoadConfig.keySmtp = keySmtp;
	}

	public static String getValueSmtp() {
		return valueSmtp;
	}

	public static void setValueSmtp(String valueSmtp) {
		LoadConfig.valueSmtp = valueSmtp;
	}

	public static String getKeyProps() {
		return keyProps;
	}

	public static void setKeyProps(String keyProps) {
		LoadConfig.keyProps = keyProps;
	}

	public static boolean getValueProps() {
		return valueProps;
	}

	public static void setValueProps(boolean valueProps) {
		LoadConfig.valueProps = valueProps;
	}

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
