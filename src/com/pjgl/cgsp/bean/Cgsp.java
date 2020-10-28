package com.pjgl.cgsp.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Cgsp {

	private int id;
	private int cghtid; //'采购合同ID',
	private String htbh;
	private Double zje;
	private String jbrid; //'经办人',
	private Double spje; //'票据金额',
	private Date sprq; //'收票日期',
	private String sprqStr;
	private String companyid; //'所属公司',
	private String wjmc; //文件名称
	private String remarks;
	private String path;
	private String userName;
	private String userMobile;
	private String[] names;
	private String[] paths;
	private int state;
	private String stateStr;
	private Date sprq1;
	private Date sprq2;
	private String style;
	private List<Map<String, Object>> pathList;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	public final static int STATE_INVALID = 0;//审批完成
	public final static int STATE_VALID = 1;//新建
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCghtid() {
		return cghtid;
	}
	public void setCghtid(int cghtid) {
		this.cghtid = cghtid;
	}
	public String getJbrid() {
		return jbrid;
	}
	public void setJbrid(String jbrid) {
		this.jbrid = jbrid;
	}
	public Double getSpje() {
		return spje;
	}
	public void setSpje(Double spje) {
		this.spje = spje;
	}
	public Date getSprq() {
		return sprq;
	}
	public void setSprq(Date sprq) {
		this.sprq = sprq;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getSprqStr() {
		if(sprq != null){
			return f.format(sprq);
		}else{
			return "";
		}
	}
	public void setSprqStr(String sprqStr) {
		this.sprqStr = sprqStr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	public String[] getPaths() {
		return paths;
	}
	public void setPaths(String[] paths) {
		this.paths = paths;
	}
	public List<Map<String, Object>> getPathList() {
		return pathList;
	}
	public void setPathList(List<Map<String, Object>> pathList) {
		this.pathList = pathList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getSprq1() {
		return sprq1;
	}
	public void setSprq1(Date sprq1) {
		this.sprq1 = sprq1;
	}
	public Date getSprq2() {
		return sprq2;
	}
	public void setSprq2(Date sprq2) {
		this.sprq2 = sprq2;
	}
	public Double getZje() {
		return zje;
	}
	public void setZje(Double zje) {
		this.zje = zje;
	}
	public String getStateStr() {
		if (state == STATE_INVALID) {
			return "已作废";
		}
		return "";
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	
}
