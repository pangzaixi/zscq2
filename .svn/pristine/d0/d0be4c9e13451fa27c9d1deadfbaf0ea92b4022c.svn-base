package com.pjgl.instock.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class InStock {

	private int id;
	private int pjid; //'配件ID',
	private String name;
	private String spec;
	private String unit;
	private Double amounts; //'入库金额',
	private Double totalNum; //'入库数量',
	private Double averagePrice; //'平均单价',
	private int companyid;
	private Date createDate; //'入库时间',
	private String cerateDateStr;
	private int creator; //'操作人',
	private String creatorName;
	private String type;
	private String remarks;
	private String month;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPjid() {
		return pjid;
	}
	public void setPjid(int pjid) {
		this.pjid = pjid;
	}
	public Double getAmounts() {
		String amount = String.format("%.2f", amounts);
		return Double.parseDouble(amount);
	}
	public void setAmounts(Double amounts) {
		this.amounts = amounts;
	}
	public Double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCerateDateStr() {
		if (createDate != null) {
			return f.format(createDate);
		}else {
			return "";
		}
	}
	public void setCerateDateStr(String cerateDateStr) {
		this.cerateDateStr = cerateDateStr;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
