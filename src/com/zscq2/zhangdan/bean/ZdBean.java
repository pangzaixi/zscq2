package com.zscq2.zhangdan.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 账单
 * @author pang
 *
 */
public class ZdBean {
	private int index;
	private int id;
	private String zdnum;//账单号
	private String wtkh;//委托客户
	private String ajlx;//案件类型
	private Date zddate;//账单生成日期
	private Date wtrqZd;//账单委托日期
	private String zddate_str;//账单生成日期
	private String wtrqZd_str;//账单委托日期
	private double sumgf;//总官费
	private double sumdlf;//总代理费
	private double sumgfYt;//总官费
	private double sumdlfYt;//总代理费
	private String remark;//备注
	
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWtkh() {
		return wtkh;
	}
	public void setWtkh(String wtkh) {
		this.wtkh = wtkh;
	}
	public String getAjlx() {
		return ajlx;
	}
	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}
	public Date getZddate() {
		return zddate;
	}
	public void setZddate(Date zddate) {
		this.zddate = zddate;
	}
	
	public Date getWtrqZd() {
		return wtrqZd;
	}
	public void setWtrqZd(Date wtrqZd) {
		this.wtrqZd = wtrqZd;
	}
	
	public double getSumgf() {
		return sumgf;
	}
	public void setSumgf(double sumgf) {
		this.sumgf = sumgf;
	}
	public double getSumdlf() {
		return sumdlf;
	}
	public void setSumdlf(double sumdlf) {
		this.sumdlf = sumdlf;
	}
	public double getSumgfYt() {
		return sumgfYt;
	}
	public void setSumgfYt(double sumgfYt) {
		this.sumgfYt = sumgfYt;
	}
	public double getSumdlfYt() {
		return sumdlfYt;
	}
	public void setSumdlfYt(double sumdlfYt) {
		this.sumdlfYt = sumdlfYt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZdnum() {
		return zdnum;
	}
	public void setZdnum(String zdnum) {
		this.zdnum = zdnum;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getZddate_str() {
		if(zddate == null){
			return null;
		}else{
			return f.format(zddate);
		}
		
	}
	public void setZddate_str(String zddate_str) {
		this.zddate_str = zddate_str;
	}
	public String getWtrqZd_str() {
		if(wtrqZd == null){
			return null;
		}else{
			return f.format(wtrqZd);
		}
		
	}
	public void setWtrqZd_str(String wtrqZd_str) {
		this.wtrqZd_str = wtrqZd_str;
	}

}
