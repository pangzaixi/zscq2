package com.zscq2.ss.bean;

import java.util.Date;

/**
 * 案件实体
 * @author thinker
 *
 */
public class Ssaj {
	int  id;// int(255) NOT NULL AUTO_INCREMENT,
	String  ajh;// varchar(255) DEFAULT NULL COMMENT '案件号',
	String  ajlx;// varchar(255) DEFAULT NULL COMMENT 'LMY 民事一审\nLME 民事二审\nLXY 行政一审\nLXE 行政二审\nLSY 刑事一审\nLSE 刑事二审\nLLZ 劳动仲裁\nLMZ商事仲裁\nLGW顾问咨询\nLCH出具律师函\nLQT法律其它',
	String hth;//合同号
	String  ay;// varchar(255) DEFAULT NULL COMMENT '案由合同纠纷、劳动纠纷、离婚纠纷等，可以手动输入',
	String  ajzt;// varchar(255) DEFAULT NULL COMMENT '案件状态：\r\n待立案、已立案\r\n管辖权异议、待开庭、已开庭、待第N次开庭、待提交材料、庭审结束、撤诉、调解结案、判决结案、中止审理、移送其院\r\n',
	String  yg;// varchar(255) DEFAULT NULL COMMENT '原告',
	String  bg;// varchar(255) DEFAULT NULL COMMENT '被告',
	String  dsr;// varchar(255) DEFAULT NULL COMMENT '第三人',
	String  gxfy;// varchar(255) DEFAULT NULL COMMENT '管辖法院',
	String  szbd;// varchar(255) DEFAULT NULL COMMENT '诉争标地',
	String  sj;// varchar(255) DEFAULT NULL COMMENT '审级',
	Date  wtrq;// date DEFAULT NULL COMMENT '委托日期',
	Date  larq;// date DEFAULT NULL COMMENT '立案日期',
	Date  dbrq;// date DEFAULT NULL COMMENT '答辩日期',
	Date  ktrq;// date DEFAULT NULL COMMENT '开庭日期',
	Date  dtjclrq;// date DEFAULT NULL COMMENT '带提交材料日期',
	Date  pjsdrq;// date DEFAULT NULL COMMENT '判决收到日期',
	Date  ssqjxr;// date DEFAULT NULL COMMENT '上诉期绝限日',
	String  dlr;// varchar(255) DEFAULT NULL COMMENT '代理人',
	String  lhbar;// varchar(255) DEFAULT NULL COMMENT '联合办案人',
	String  sjydh;// varchar(255) DEFAULT NULL COMMENT '书记员电话',
	String cbfg;// varchar(255) DEFAULT NULL COMMENT '承办法官',
	String  jcdlf;// double(255,0) DEFAULT NULL COMMENT '基础代理费',
	String  fxdlf;// double(255,0) DEFAULT NULL COMMENT '风险代理费',
	boolean  zf;// tinyint(1) DEFAULT NULL COMMENT '是否支付',
	boolean  kjfp;// varchar(255) DEFAULT NULL COMMENT '是否开具发票',
	String  remarks;// varchar(255) DEFAULT NULL COMMENT '备注',
	String  creater;//
	Date  createdate;//
	String  updater;//
	Date  updatedate;//

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAjh() {
		return ajh;
	}
	public void setAjh(String ajh) {
		this.ajh = ajh;
	}
	public String getAjlx() {
		return ajlx;
	}
	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}
	public String getAy() {
		return ay;
	}
	public void setAy(String ay) {
		this.ay = ay;
	}
	public String getAjzt() {
		return ajzt;
	}
	public void setAjzt(String ajzt) {
		this.ajzt = ajzt;
	}
	public String getYg() {
		return yg;
	}
	public void setYg(String yg) {
		this.yg = yg;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getDsr() {
		return dsr;
	}
	public void setDsr(String dsr) {
		this.dsr = dsr;
	}
	public String getGxfy() {
		return gxfy;
	}
	public void setGxfy(String gxfy) {
		this.gxfy = gxfy;
	}
	public String getSzbd() {
		return szbd;
	}
	public void setSzbd(String szbd) {
		this.szbd = szbd;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public Date getWtrq() {
		return wtrq;
	}
	public void setWtrq(Date wtrq) {
		this.wtrq = wtrq;
	}
	public Date getLarq() {
		return larq;
	}
	public void setLarq(Date larq) {
		this.larq = larq;
	}
	public Date getDbrq() {
		return dbrq;
	}
	public void setDbrq(Date dbrq) {
		this.dbrq = dbrq;
	}
	public Date getKtrq() {
		return ktrq;
	}
	public void setKtrq(Date ktrq) {
		this.ktrq = ktrq;
	}
	public Date getDtjclrq() {
		return dtjclrq;
	}
	public void setDtjclrq(Date dtjclrq) {
		this.dtjclrq = dtjclrq;
	}
	public Date getPjsdrq() {
		return pjsdrq;
	}
	public void setPjsdrq(Date pjsdrq) {
		this.pjsdrq = pjsdrq;
	}
	public Date getSsqjxr() {
		return ssqjxr;
	}
	public void setSsqjxr(Date ssqjxr) {
		this.ssqjxr = ssqjxr;
	}
	public String getDlr() {
		return dlr;
	}
	public void setDlr(String dlr) {
		this.dlr = dlr;
	}
	public String getLhbar() {
		return lhbar;
	}
	public void setLhbar(String lhbar) {
		this.lhbar = lhbar;
	}
	public String getSjydh() {
		return sjydh;
	}
	public void setSjydh(String sjydh) {
		this.sjydh = sjydh;
	}
	public String getCbfg() {
		return cbfg;
	}
	public void setCbfg(String cbfg) {
		this.cbfg = cbfg;
	}
	
	public String getJcdlf() {
		return jcdlf;
	}
	public void setJcdlf(String jcdlf) {
		this.jcdlf = jcdlf;
	}
	public String getFxdlf() {
		return fxdlf;
	}
	public void setFxdlf(String fxdlf) {
		this.fxdlf = fxdlf;
	}
	public boolean isZf() {
		return zf;
	}
	public void setZf(boolean zf) {
		this.zf = zf;
	}
	public boolean getKjfp() {
		return kjfp;
	}
	public void setKjfp(boolean kjfp) {
		this.kjfp = kjfp;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getHth() {
		return hth;
	}
	public void setHth(String hth) {
		this.hth = hth;
	}
	
	
}
