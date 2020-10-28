package com.pjgl.stock.bean;

/**
 * 库存
 * @author thinker
 *
 */
public class Stock {
	private int id;
	private int pjid;// '配件ID',
	private String pjname;//配件名称
	private String pjgg;//配件规格
	private String pjdw;//配件单位
	private double amounts;// '库存金额',
	private double totalNum;// '总数量',
	private double ageragePrice;// '平均单价',
	private int companyId;// '所属公司ID',
	private Integer upperLimit;//库存上限
	private Integer lowerLimit;//库存下限
	private String style;//页面样式
	
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
	public String getPjgg() {
		return pjgg;
	}
	public void setPjgg(String pjgg) {
		this.pjgg = pjgg;
	}
	public String getPjdw() {
		return pjdw;
	}
	public void setPjdw(String pjdw) {
		this.pjdw = pjdw;
	}
	
	public Integer getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	

}
