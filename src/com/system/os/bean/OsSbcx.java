package com.system.os.bean;

/**
 * 设备查询
 * @author thinker
 *
 */
public class OsSbcx {
	private String id;
	private String sim_num;//设备编号
	private String dtuphone_num;//采集号
	private String dev_type;//设备类型
	private String platenum;//车牌号
	private String owner;//机主
	private String driver;//驾驶员
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSim_num() {
		return sim_num;
	}
	public void setSim_num(String sim_num) {
		this.sim_num = sim_num;
	}
	public String getDtuphone_num() {
		return dtuphone_num;
	}
	public void setDtuphone_num(String dtuphone_num) {
		this.dtuphone_num = dtuphone_num;
	}
	public String getDev_type() {
		return dev_type;
	}
	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}
	
	public String getPlatenum() {
		return platenum;
	}
	public void setPlatenum(String platenum) {
		this.platenum = platenum;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	
	
}
