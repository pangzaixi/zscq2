package com.pjgl.cgfk.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Cgfk {

	private int id;
	private int cghtid; //'采购合同ID',
	private String htbh;
	private String jbrid; //'经办人',
	private Double fkje; //'付款金额',
	private Double zje;
	private Date fkrq; //'付款日期',
	private String fkrqStr;
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
	private Date fkrq1;
	private Date fkrq2;
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
	public Double getFkje() {
		return fkje;
	}
	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}
	public Double getZje() {
		return zje;
	}
	public void setZje(Double zje) {
		this.zje = zje;
	}
	public Date getFkrq() {
		return fkrq;
	}
	public void setFkrq(Date fkrq) {
		this.fkrq = fkrq;
	}
	public String getFkrqStr() {
		if(fkrq != null){
			return f.format(fkrq);
		}else{
			return "";
		}
	}
	public void setFkrqStr(String fkrqStr) {
		this.fkrqStr = fkrqStr;
	}
	public Date getFkrq1() {
		return fkrq1;
	}
	public void setFkrq1(Date fkrq1) {
		this.fkrq1 = fkrq1;
	}
	public Date getFkrq2() {
		return fkrq2;
	}
	public void setFkrq2(Date fkrq2) {
		this.fkrq2 = fkrq2;
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
