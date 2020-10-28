package com.pjgl.stockUse.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class StockUse {

	private int id;
	private int stockId; //'库存id',
	private int pjid; //'配件id',
	private Double totalNum; //'库存总数',
	private Double amounts; //'库存金额',
	private Double averagePrice; //'平均单价',
	private int companyId; //'所属公司',
	private Date useTime; //'领用时间',
	private String useTimeStr;
	private Double totalNumContrast; //'领用数量',
	private Double amountsContrast; //'领用金额',
	private Double averagePriceContrast; //'领用单价',
	private String plateNum; //'车票号',
	private String driver; //'驾驶员',
	private String creator; //'操作人',
	private String mobile; //'联系方式',
	private String remarks; //'备注',
	private String name;
	private String spec;
	private String unit;
	private String month;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getPjid() {
		return pjid;
	}
	public void setPjid(int pjid) {
		this.pjid = pjid;
	}
	public Double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}
	public Double getAmounts() {
		return amounts;
	}
	public void setAmounts(Double amounts) {
		this.amounts = amounts;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Double getTotalNumContrast() {
		return totalNumContrast;
	}
	public void setTotalNumContrast(Double totalNumContrast) {
		this.totalNumContrast = totalNumContrast;
	}
	public Double getAmountsContrast() {
		return amountsContrast;
	}
	public void setAmountsContrast(Double amountsContrast) {
		this.amountsContrast = amountsContrast;
	}
	public Double getAveragePriceContrast() {
		return averagePriceContrast;
	}
	public void setAveragePriceContrast(Double averagePriceContrast) {
		this.averagePriceContrast = averagePriceContrast;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getUseTimeStr() {
		if (useTime != null) {
			return format.format(useTime);
		}
		return "";
	}
	public void setUseTimeStr(String useTimeStr) {
		this.useTimeStr = useTimeStr;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
}
