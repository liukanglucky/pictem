package com.pictem.common.exception;


import net.sf.json.JSONObject;

import com.pictem.enums.CommonStateEnum;
import com.pictem.enums.CommonStateEnum.FieldEnum;
import com.pictem.enums.ExceptionTypeEnum;


public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 8151877886020885741L;

	private String message;

	private int code;

	private int stateCode;

	public BusinessException(CommonStateEnum type) {
		super(type.getMessage());
		this.code = type.getState();
		this.stateCode = type.getStateCode();
		this.message = type.getMessage();
	}

	public BusinessException(CommonStateEnum type, String message) {
		super(message);
		this.code = type.getState();
		this.message = message;
	}

	public BusinessException(ExceptionTypeEnum type) {
		super(type.getMessage());
		this.code = type.getCode();
		this.message = type.getMessage();
	}

	public BusinessException(ExceptionTypeEnum type, String message) {
		super(message);
		this.code = type.getCode();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public int getStateCode() {
		return stateCode;
	}

	public String getJSONString() {
		JSONObject obj = new JSONObject();
		obj.put(FieldEnum.CODE.getField(), this.getCode());
		obj.put(FieldEnum.STATE_CODE.getField(), this.getStateCode());
		obj.put(FieldEnum.MESSAGE.getField(), this.getMessage());
		return obj.toString();
	}
}
