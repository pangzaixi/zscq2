package com.system.role.bean;

import java.util.List;

/**
 * 
* @ClassName: Role
* @Description: 角色bean
* @author 徐玲
* @date 2019年7月22日下午3:57:45
*
 */
public class Role {
	private int id ;
	private String  login_name ;//'登录账号',
	private String user_name;//登录人姓名
	 private String  role ;// '角色',
	 private String  remarks ;//备注
	 private List<String> login_names;//登录账号集合
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<String> getLogin_names() {
		return login_names;
	}
	public void setLogin_names(List<String> login_names) {
		this.login_names = login_names;
	}
	 
}
