package com.pictem.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseModel implements Serializable{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 834988352627234075L;
	
	/**
	 * 排序字段名
	 */
	protected String sidx;// sortKey
	/**
	 * 排序方式
	 */
	protected String sord;// sortType

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * toString 方法通过反射得到子类的属性与属性值 如果不需要所有的属性值就在子类里重写toString
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}
}
