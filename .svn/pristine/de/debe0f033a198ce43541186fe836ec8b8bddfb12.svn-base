package com.pjgl.employeeManage.bean;

import java.util.Date;
import java.util.List;
/**
 * 
* @ClassName: EmployeeManage
* @Description: 员工bean
* @author 徐玲
* @date 2019年7月22日上午9:34:29
*
 */
public class EmployeeManage {
	
	private int id ;
	private String login_name ;
	private String login_pwd ;
	private String user_name ;
	private String  level ;//'1:管理员,2:操作人,3:浏览账号',
	private String  status; //'0:禁用   1：启用',
	private String statusStr;
	private Date updatetime;
	private int departmentid; //'部门id',
	private String departmentName;
	private String mobile; // '手机号',
	private String identity;  // '身份：机手、公司总经理、财务等',
	private String companyid;//'所属公司',
   private String companyName;
	private String fax;  //'传真',
	private String email ;//电子邮箱
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
	public String getLogin_pwd() {
		return login_pwd;
	}
	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getStatusStr() {
		String statusStr = null;
		if (status.equals("1")) {
			statusStr="启用";
		}if (status.equals("0")) {
			statusStr="禁用";
		}
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
