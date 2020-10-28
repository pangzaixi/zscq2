package com.yewu.jxqd.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 绝限清单
 * @author thinker
 *
 */
public class Jxqd {
	  private String id;
	  private String ajh;		  //'案件号',
	  private String ajlx;		  // '案件类型',
	  private String sbmc;		  // '商标名称',
	  private String lb;          //, '类别',
	  private String sbh;         // '商标号',
	  private Date khwtrq;        // '客户委托日期',
	  private String khwtrq_str;  //转换字段
	  private Date sqr;           //'申请日',
	  private String sqr_str;     //'申请日',
	  private Date swrq;          //'收文日期',
	  private String swrq_str;    //'收文日期',
	  private Date csrq;          //'初审日期',
	  private String csrq_str;    //'初审日期',
	  private String ggq;         //'公告期',
	  private Date jxr;           //'绝限日',
	  private String jxr_str;     //'绝限日',
	  private Date sctjr;         //'首次提交日',
	  private String sctjr_str;   //'首次提交日',
	  private Date bcqx;        //'补充期限',
	  private String bcqx_str;
	  private Date bctjrq;        //'补充提交日期',
	  private String bctjrq_str;  //'补充提交日期',
	  private String scbsbh;      //'首次报送编号',
	  private String ecbsbh;      //'二次报送编号',
	  private String wtkhmc;      //'委托客户名称',
	  private String bqqr;        //'被请求人/商标权人/对方相对人',
	  private String wfdsr;       //'申请人/请求人/我方当事人',
	  private String gfjg;        //'官方结果',
	  private Date gfjgswrq;	  //官方结果收文日期
	  private Date gfjgjxrq;	  //官方结果绝限日期
	  private String gfjgswrq_str;	  //官方结果收文日期
	  private String gfjgjxrq_str;	  //官方结果绝限日期
	  private String dlr;         //'代理人',
	  private String dlr2;        //'代理人2',
	  private String ayr;         //'案源人',
	  private String dlgs;        //'代理公司',
	  private Double gf;          //'官费',
	  private Double dlf;         //'代理费',
	  private Double khfk;        //'客户付款',
	  private Double ygkhkp;      //'已给客户开票',
	  private Double djgsfk;      //'代交公司付款',
	  private Double ygdjgskp;    //'已给代交公司开票',
	  private Double dlryzftcf;   //'代理人已支付提成费',
	  private Double ayryzftcf;   //'案源人已支付提成费',
	  private Date inputdate;	  //录入日期
	  private String creater;     //录入人
	  private Date updatedate;		//更新日期
	  private String updater;		//更新人
	  private String remarks;     //备注
	  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getSbmc() {
		return sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getSbh() {
		return sbh;
	}
	public void setSbh(String sbh) {
		this.sbh = sbh;
	}
	public Date getKhwtrq() {
		return khwtrq;
	}
	public void setKhwtrq(Date khwtrq) {
		this.khwtrq = khwtrq;
	}
	
	public String getKhwtrq_str() {
		if(khwtrq != null){
			return f.format(khwtrq);
		}else{
			return "";
		}
		
	}
	public void setKhwtrq_str(String khwtrq_str) {
		this.khwtrq_str = khwtrq_str;
	}
	public Date getSqr() {
		return sqr;
	}
	public void setSqr(Date sqr) {
		this.sqr = sqr;
	}
	public Date getSwrq() {
		return swrq;
	}
	public void setSwrq(Date swrq) {
		this.swrq = swrq;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public String getGgq() {
		return ggq;
	}
	public void setGgq(String ggq) {
		this.ggq = ggq;
	}
	public Date getJxr() {
		return jxr;
	}
	public void setJxr(Date jxr) {
		this.jxr = jxr;
	}
	public Date getSctjr() {
		return sctjr;
	}
	public void setSctjr(Date sctjr) {
		this.sctjr = sctjr;
	}
	
	public Date getBcqx() {
		return bcqx;
	}
	public void setBcqx(Date bcqx) {
		this.bcqx = bcqx;
	}
	
	public String getBcqx_str() {
		if(bcqx != null){
			return f.format(bcqx);
		}else{
			return "";
		}
	}
	public void setBcqx_str(String bcqx_str) {
		this.bcqx_str = bcqx_str;
	}
	public Date getBctjrq() {
		return bctjrq;
	}
	public void setBctjrq(Date bctjrq) {
		this.bctjrq = bctjrq;
	}
	public String getScbsbh() {
		return scbsbh;
	}
	public void setScbsbh(String scbsbh) {
		this.scbsbh = scbsbh;
	}
	public String getEcbsbh() {
		return ecbsbh;
	}
	public void setEcbsbh(String ecbsbh) {
		this.ecbsbh = ecbsbh;
	}
	public String getWtkhmc() {
		return wtkhmc;
	}
	public void setWtkhmc(String wtkhmc) {
		this.wtkhmc = wtkhmc;
	}
	public String getBqqr() {
		return bqqr;
	}
	public void setBqqr(String bqqr) {
		this.bqqr = bqqr;
	}
	public String getWfdsr() {
		return wfdsr;
	}
	public void setWfdsr(String wfdsr) {
		this.wfdsr = wfdsr;
	}
	public String getGfjg() {
		return gfjg;
	}
	public void setGfjg(String gfjg) {
		this.gfjg = gfjg;
	}
	
	public Date getGfjgswrq() {
		return gfjgswrq;
	}
	public void setGfjgswrq(Date gfjgswrq) {
		this.gfjgswrq = gfjgswrq;
	}
	public Date getGfjgjxrq() {
		return gfjgjxrq;
	}
	public void setGfjgjxrq(Date gfjgjxrq) {
		this.gfjgjxrq = gfjgjxrq;
	}
	
	
	public String getGfjgswrq_str() {
		if(gfjgswrq != null){
			return f.format(gfjgswrq);
		}else{
			return "";
		}
	}
	public void setGfjgswrq_str(String gfjgswrq_str) {
		this.gfjgswrq_str = gfjgswrq_str;
	}
	public String getGfjgjxrq_str() {
		if(gfjgjxrq != null){
			return f.format(gfjgjxrq);
		}else{
			return "";
		}
	}
	public void setGfjgjxrq_str(String gfjgjxrq_str) {
		this.gfjgjxrq_str = gfjgjxrq_str;
	}
	public String getDlr() {
		return dlr;
	}
	public void setDlr(String dlr) {
		this.dlr = dlr;
	}
	public String getDlr2() {
		return dlr2;
	}
	public void setDlr2(String dlr2) {
		this.dlr2 = dlr2;
	}
	public String getAyr() {
		return ayr;
	}
	public void setAyr(String ayr) {
		this.ayr = ayr;
	}
	public String getDlgs() {
		return dlgs;
	}
	public void setDlgs(String dlgs) {
		this.dlgs = dlgs;
	}
	public Double getGf() {
		return gf;
	}
	public void setGf(Double gf) {
		this.gf = gf;
	}
	public Double getDlf() {
		return dlf;
	}
	public void setDlf(Double dlf) {
		this.dlf = dlf;
	}
	public Double getKhfk() {
		return khfk;
	}
	public void setKhfk(Double khfk) {
		this.khfk = khfk;
	}
	public Double getYgkhkp() {
		return ygkhkp;
	}
	public void setYgkhkp(Double ygkhkp) {
		this.ygkhkp = ygkhkp;
	}
	public Double getDjgsfk() {
		return djgsfk;
	}
	public void setDjgsfk(Double djgsfk) {
		this.djgsfk = djgsfk;
	}
	public Double getYgdjgskp() {
		return ygdjgskp;
	}
	public void setYgdjgskp(Double ygdjgskp) {
		this.ygdjgskp = ygdjgskp;
	}
	public Double getDlryzftcf() {
		return dlryzftcf;
	}
	public void setDlryzftcf(Double dlryzftcf) {
		this.dlryzftcf = dlryzftcf;
	}
	public Double getAyryzftcf() {
		return ayryzftcf;
	}
	public void setAyryzftcf(Double ayryzftcf) {
		this.ayryzftcf = ayryzftcf;
	}
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public String getSqr_str() {
		if(sqr != null){
			return f.format(sqr);
		}else{
			return "";
		}
		
	}
	public void setSqr_str(String sqr_str) {
		this.sqr_str = sqr_str;
	}
	public String getSwrq_str() {
		if(swrq != null){
			return f.format(swrq);
		}else{
			return "";
		}
		
	}
	public void setSwrq_str(String swrq_str) {
		this.swrq_str = swrq_str;
	}
	public String getCsrq_str() {
		if(csrq != null){
			return f.format(csrq);
		}else{
			return "";
		}
		
	}
	public void setCsrq_str(String csrq_str) {
		this.csrq_str = csrq_str;
	}
	public String getJxr_str() {
		if(jxr != null){
			return f.format(jxr);
		}else{
			return "";
		}
		
	}
	public void setJxr_str(String jxr_str) {
		this.jxr_str = jxr_str;
	}
	public String getSctjr_str() {
		if(sctjr != null){
			return f.format(sctjr);
		}else{
			return "";
		}
		
	}
	public void setSctjr_str(String sctjr_str) {
		this.sctjr_str = sctjr_str;
	}
	
	public String getBctjrq_str() {
		if(bctjrq != null){
			return f.format(bctjrq);
		}else{
			return "";
		}
		
	}
	public void setBctjrq_str(String bctjrq_str) {
		this.bctjrq_str = bctjrq_str;
	}
	public SimpleDateFormat getF() {
		return f;
	}
	public void setF(SimpleDateFormat f) {
		this.f = f;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	  
	  
}
