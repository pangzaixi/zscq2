package com.pjgl.lysqSub.bean;



public class LysqSub {

	private int id;
	private int lysqid; //'领用申请ID',
	private int pjid; //'所购配件ID',
	private Double num; //'数量',
	private String sscx; //'所属车型',
	private String wz; //'位置',
	private String remarks; //'备注',
	private String pjName; //配件名称
	private String spec; //配件规格
	private String unit; //单位
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLysqid() {
		return lysqid;
	}
	public void setLysqid(int lysqid) {
		this.lysqid = lysqid;
	}
	public int getPjid() {
		return pjid;
	}
	public void setPjid(int pjid) {
		this.pjid = pjid;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public String getSscx() {
		return sscx;
	}
	public void setSscx(String sscx) {
		this.sscx = sscx;
	}
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPjName() {
		return pjName;
	}
	public void setPjName(String pjName) {
		this.pjName = pjName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
}
