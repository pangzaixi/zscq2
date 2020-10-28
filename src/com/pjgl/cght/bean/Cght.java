package com.pjgl.cght.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cght {

	private int id;
	private String htbh; //'合同编号',
	private Date qdrq; //'合同签订日期',
	private String qdrqStr;
	private Double zje; //'总金额',
	private String jbrid; //'经办人',
	private String gysid; //'供应商ID',
	private String companyid; //'所属公司ID',
	private String remarks; //'备注',
	private String supplierMobile;//供应商电话
	private String userName;
	private String userMobile;
	private String supplierName;
	private int state;
	private String stateStr;
	private Date qdrq1;
	private Date qdrq2;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	public final static int STATE_END = 0;//审批完成
	public final static int STATE_CREATE = 1;//新建
	public final static int STATE_DELETE = 2;//删除
	
	public String getQdrqStr() {
		if(qdrq != null){
			return f.format(qdrq);
		}else{
			return "";
		}
	}
	public void setQdrqStr(String qdrqStr) {
		this.qdrqStr = qdrqStr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public Date getQdrq() {
		return qdrq;
	}
	public void setQdrq(Date date) {
		this.qdrq = date;
	}
	public Double getZje() {
		return zje;
	}
	public void setZje(Double zje) {
		this.zje = zje;
	}
	public String getJbrid() {
		return jbrid;
	}
	public void setJbrid(String jbrid) {
		this.jbrid = jbrid;
	}
	public String getGysid() {
		return gysid;
	}
	public void setGysid(String gysid) {
		this.gysid = gysid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSupplierMobile() {
		return supplierMobile;
	}
	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateStr() {
		if(state == 0){
			return "已审批";
		}else{
			return "待审批";
		}
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public Date getQdrq1() {
		return qdrq1;
	}
	public void setQdrq1(Date qdrq1) {
		this.qdrq1 = qdrq1;
	}
	public Date getQdrq2() {
		return qdrq2;
	}
	public void setQdrq2(Date qdrq2) {
		this.qdrq2 = qdrq2;
	}
	
}
