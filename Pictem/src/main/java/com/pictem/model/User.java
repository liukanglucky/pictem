package com.pictem.model;

public class User {
	
	private int id;
	private String name;     //昵称
	private String realname;    //真实姓名
	private String phonenum;	//电话号
	private String mail;
	private String rolename;      //角色,区分用户管理员，超级管理员(做一次冗余存储)
	private int roleid;
	private String authstate;         //认证状态 0未认证 1认证中 2认证完成
	private String authlevel;		  //认证等级 0未认证 1邮箱 2实名
	private String verifyCode;   //验证码，如找回密码，邮箱认证等时使用
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getAuthstate() {
		return authstate;
	}
	public void setAuthstate(String authstate) {
		this.authstate = authstate;
	}
	public String getAuthlevel() {
		return authlevel;
	}
	public void setAuthlevel(String authlevel) {
		this.authlevel = authlevel;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
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
	
}
