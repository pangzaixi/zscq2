package com.pjgl.lysq.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lysq {
	public final static int status_start = 0;//发起中
	public final static int status_psss = 1;//通过
	public final static int status_refuse = 2;//拒绝
	private int id;
	private String jbrid;//经办人id
	private Date lyrq; //领用日期
	private String lyrqStr; //领用日期
	private Date lyrq1;
	private Date lyrq2;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	private int amounts;//总价格
	private String companyid;//领用公司id
	private String companyName;//领用公司
	private String userName;
	private int state;//状态 0：发起，  1 ：已审批通过，2：审批拒绝，0和2情况下可删除记录
	
	private String stateStr;
	private String remarks;//备注
	
	public String getStateStr() {
		if(state == 0){
			return "待审批";
		}else if(state == 1){
			return "已审批";
		}else if(state == 2){
			return "已拒绝";
		}
		return "";
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLyrq1() {
		return lyrq1;
	}
	public void setLyrq1(Date lyrq1) {
		this.lyrq1 = lyrq1;
	}
	public Date getLyrq2() {
		return lyrq2;
	}
	public void setLyrq2(Date lyrq2) {
		this.lyrq2 = lyrq2;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJbrid() {
		return jbrid;
	}
	public void setJbrid(String jbrid) {
		this.jbrid = jbrid;
	}
	public Date getLyrq() {
		return lyrq;
	}
	public void setLyrq(Date lyrq) {
		this.lyrq = lyrq;
	}
	public int getAmounts() {
		return amounts;
	}
	public void setAmounts(int amounts) {
		this.amounts = amounts;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int  state) {
		this.state = state;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLyrqStr() {
		if(lyrq != null){
			return f.format(lyrq);
		}else{
			return "";
		}
	}
	public void setLyrqStr(String lyrqStr) {
		this.lyrqStr = lyrqStr;
	}
	
	
	

}
