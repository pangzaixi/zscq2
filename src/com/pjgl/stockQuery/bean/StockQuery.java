package com.pjgl.stockQuery.bean;

import java.sql.Date;

/**
 * 库存
 * @author thinker
 *
 */
public class StockQuery {
	private int id;
	private int pjid;// '配件ID',
	private String pjname;//配件名称
	private double amounts;// '库存金额',
	private double totalNum;// '总数量',
	private double ageragePrice;// '平均单价',
	private int companyId;// '所属公司ID',
	private Date checkTime; //盘库时间
	private String pkr;//盘库人姓名
	private String amountsContrast;//盘库后金额
	private String totalNumContrast;//盘库后库存量
	private String averagePriceContrast;//盘库后平均单价
	private String remarks;//盘库备注
	
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
	public double getAmounts() {
		return amounts;
	}
	public void setAmounts(double amounts) {
		this.amounts = amounts;
	}
	public double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}
	public double getAgeragePrice() {
		return ageragePrice;
	}
	public void setAgeragePrice(double ageragePrice) {
		this.ageragePrice = ageragePrice;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getPkr() {
		return pkr;
	}
	public void setPkr(String pkr) {
		this.pkr = pkr;
	}
	public String getAmountsContrast() {
		return amountsContrast;
	}
	public void setAmountsContrast(String amountsContrast) {
		this.amountsContrast = amountsContrast;
	}
	public String getTotalNumContrast() {
		return totalNumContrast;
	}
	public void setTotalNumContrast(String totalNumContrast) {
		this.totalNumContrast = totalNumContrast;
	}
	public String getAveragePriceContrast() {
		return averagePriceContrast;
	}
	public void setAveragePriceContrast(String averagePriceContrast) {
		this.averagePriceContrast = averagePriceContrast;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
