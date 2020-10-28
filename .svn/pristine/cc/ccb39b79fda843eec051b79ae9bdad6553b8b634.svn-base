package com.pjgl.car.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {

	  private int id ;
	  private String plate_num; // '车牌号'
	  private int driver ; // '当前驾驶人，从人员表中获取，下拉选'
	  private String driverName;//通过driver从zscq_user表中获取user_name;
	  private String mobile ; // '联系方式'
	  private String car_brand ; // '拖拉机品牌'
	  private String car_model ; // '拖拉机型号'
	  private String engine_num ; // '发动机号'
	  private String engine_powner ; // '发动机功率'
	  private String car_rack_num ; // '车架号'
	  private Date car_buy_date; // '购置时间'
	  private String buyDateStr;//购置时间的简化格式
	  private String remarks ; // '备注'
	  private int company_id; // '所属公司ID'
	  private String companyName;
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getCar_buy_date() {
		return car_buy_date;
	}
	public void setCar_buy_date(Date car_buy_date) {
		this.car_buy_date = car_buy_date;
	}
	public String getBuyDateStr() {
		if(car_buy_date !=null){
			return format.format(car_buy_date);
		}
		return "";
	}
	public void setBuyDateStr(String buyDateStr) {
		this.buyDateStr = buyDateStr;
	}
	public String getPlate_num() {
		return plate_num;
	}
	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}
	public int getDriver() {
		return driver;
	}
	public void setDriver(int driver) {
		this.driver = driver;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCar_brand() {
		return car_brand;
	}
	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getEngine_num() {
		return engine_num;
	}
	public void setEngine_num(String engine_num) {
		this.engine_num = engine_num;
	}
	public String getEngine_powner() {
		return engine_powner;
	}
	public void setEngine_powner(String engine_powner) {
		this.engine_powner = engine_powner;
	}
	public String getCar_rack_num() {
		return car_rack_num;
	}
	public void setCar_rack_num(String car_rack_num) {
		this.car_rack_num = car_rack_num;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
  
}
