package com.pjgl.pj.bean;

public class Pj {
	
	private int id;
	private String name; // '配件名称',
	private String spec; // '规格',
	private String unit; //'单位，件、个、只、公斤等',
	private String brand; //'品牌，约翰迪尔、凯斯、通用等',
	private String remarks; //'备注',
	private String companyid; //'所属公司ID',
	private String companyName; //'所属公司名称'
	private int upper_limit; //'库存提醒上限',
	private int lower_limit; // '库存提醒下限',
	private String code;
	  
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public int getUpper_limit() {
		return upper_limit;
	}
	public void setUpper_limit(int upper_limit) {
		this.upper_limit = upper_limit;
	}
	public int getLower_limit() {
		return lower_limit;
	}
	public void setLower_limit(int lower_limit) {
		this.lower_limit = lower_limit;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	  
	  
}
