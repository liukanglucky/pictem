package com.pictem.model;

public class Tag {
	private int id;
	private String name;
	private String memo;
	private int type;  //类型  0系统保留 1用户自定义
	private String parentids; //1,2,3
	private String childrenids;
	private int weight; //权重
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getParentids() {
		return parentids;
	}
	public void setParentids(String parentids) {
		this.parentids = parentids;
	}
	public String getChildrenids() {
		return childrenids;
	}
	public void setChildrenids(String childrenids) {
		this.childrenids = childrenids;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
