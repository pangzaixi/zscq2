package com.zscq2.jxqd.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 立案总表
 * @author thinker
 *
 */
public class Lazb {
	  private String id;
	  private String ajh;		  //'案件号',
	  private String ajlx;		  // '案件类型',
	  private String sbmc;		  // '商标名称',
	  private String lb;          //, '类别',
	  private String sbh;         // '商标号',
	  private String ajzt;			//案件状态
	  private Date khwtrq;        // '客户委托日期',
	  private String khwtrq_str;  //转换字段
	  private Date sqrq;		//申请日期
	  private String sqrq_str;		//申请日期
	  private Date swrq;		//收文日期
	  private String swrq_str;		//收文日期
	  private Date csrq;		//初审日期
	  private String csrq_str;//初审日期
	  private String ggq;         //'公告期',
	  private Date jxr;           //'绝限日',
	  private String jxr_str;     //'绝限日',
	  private Date sctjr;         //'首次提交日',
	  private String scbsbh;		//首次报送编号
	  private String sctjr_str;   //'首次提交日',
	  private Date bcqx;        //'补充期限',
	  private String bcqx_str;
	  private Date bctjrq;        //'补充提交日期',
	  private String bctjrq_str;  //'补充提交日期',
	  private String ecbsbh;		//二次报送编号
	  private String wtkhmc;      //'委托客户名称',
	  private String byyr;        //'被异议人
	  private String gfyj;        //'官方意见',
	  private Date	 gfyjswr;		//官方意见收文日
	  private Date   gfyjjxr;		//官方意见绝限日
	  private String dlr;         //'代理人',
	  private String dlr2;        //'代理人2',
	  private String yysqr;		//异议申请人
	  private String khfk;        //'客户付款',
	  private String ytfk;		//盈天付款
	  private String ayr;         //'案源人',
	  private String dlgs;        //'代理公司',
	  private Date inputdate;	  //录入日期
	  private String creater;     //录入人
	  private Date updatedate;		//更新日期
	  private String updater;		//更新人
	  private String remarks;     //备注
	  private String status;		//状态 状态，默认0：未完结，完结的案件为：1
	  private String style;			//保存记录颜色用，用于页面显示
	  
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
	
	public String getScbsbh() {
		return scbsbh;
	}
	public void setScbsbh(String scbsbh) {
		this.scbsbh = scbsbh;
	}
	public String getKhwtrq_str() {
		if(khwtrq != null){
			return f.format(khwtrq);
		}else{
			return "";
		}
		
	}
	
	public String getAjzt() {
		return ajzt;
	}
	public void setAjzt(String ajzt) {
		this.ajzt = ajzt;
	}
	public Date getGfyjswr() {
		return gfyjswr;
	}
	public void setGfyjswr(Date gfyjswr) {
		this.gfyjswr = gfyjswr;
	}
	public Date getGfyjjxr() {
		return gfyjjxr;
	}
	public void setGfyjjxr(Date gfyjjxr) {
		this.gfyjjxr = gfyjjxr;
	}
	public void setKhwtrq_str(String khwtrq_str) {
		this.khwtrq_str = khwtrq_str;
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
	
	public String getWtkhmc() {
		return wtkhmc;
	}
	public void setWtkhmc(String wtkhmc) {
		this.wtkhmc = wtkhmc;
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
	
	public String getKhfk() {
		return khfk;
	}
	public void setKhfk(String khfk) {
		this.khfk = khfk;
	}
	
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getByyr() {
		return byyr;
	}
	public void setByyr(String byyr) {
		this.byyr = byyr;
	}
	public String getGfyj() {
		return gfyj;
	}
	public void setGfyj(String gfyj) {
		this.gfyj = gfyj;
	}
	public String getYysqr() {
		return yysqr;
	}
	public void setYysqr(String yysqr) {
		this.yysqr = yysqr;
	}
	public String getYtfk() {
		return ytfk;
	}
	public void setYtfk(String ytfk) {
		this.ytfk = ytfk;
	}
	public Date getSqrq() {
		return sqrq;
	}
	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
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
	public String getEcbsbh() {
		return ecbsbh;
	}
	public void setEcbsbh(String ecbsbh) {
		this.ecbsbh = ecbsbh;
	}
	public String getSqrq_str() {
		
		if(sqrq != null){
			return f.format(sqrq);
		}else{
			return "";
		}
	}
	public void setSqrq_str(String sqrq_str) {
		this.sqrq_str = sqrq_str;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getAllInfo(){
		
		String resl = "";
		resl=this.getId()+"|"+this.getAjh()+"|"+this.getAjlx()+"|"+this.getSbmc()+"|"+this.getLb()+"|"+this.getSbh()+"|"+this.getAjzt()+"|"+this.getKhwtrq_str()
		+"|"+this.getSqrq_str()+"|"+this.getSwrq_str()+"|"+this.getCsrq_str()+"|"+this.getGgq()+"|"+this.getJxr_str()+"|"+this.getSctjr_str()
		+"|"+this.getScbsbh()+"|"+this.getBcqx_str()+"|"+this.getBctjrq_str()+"|"+this.getEcbsbh()+"|"+this.getWtkhmc()+"|"+this.getByyr()
		+"|"+this.getGfyj()+"|"+this.getGfyjswr()+"|"+this.getGfyjjxr()+"|"+this.getDlr()+"|"+this.getDlr2()+"|"+this.getYysqr()+"|"+this.getKhfk()+"|"+this.getYtfk()+"|"+this.getAyr()
		+"|"+this.getDlgs()+"|"+this.remarks+"|"+this.getStatus();
		return resl;
	}
	  
	public Map<Object,Object> getMap(){
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		map.put("ajlx", this.getAjlx());		map.put("sbmc", this.getSbmc());		map.put("lb", this.getLb());		map.put("sbh", this.getSbh());
		map.put("ajzt", this.getAjzt());
		map.put("khwtrq", this.getKhwtrq());		map.put("sqrq", this.getSqrq());		map.put("swrq", this.getSwrq());		map.put("csrq", this.getCsrq());
		map.put("ggq", this.getGgq());		map.put("jxr", this.getJxr());		map.put("sctjr", this.getSctjr());		map.put("scbsbh", this.getScbsbh());
		map.put("bcqx", this.getBcqx());		map.put("bctjrq", this.getBctjrq());		map.put("ecbsbh", this.getEcbsbh());		
		map.put("wtkhmc", this.getWtkhmc());
		map.put("byyr", this.getByyr());		map.put("gfyj", this.getGfyj());map.put("gfyjswr", this.getGfyjswr());map.put("gfyjjxr", this.getGfyjjxr());
		map.put("dlr", this.getDlr());		map.put("dlr2", this.getDlr2());
		map.put("yysqr", this.getYysqr());		map.put("khfk", this.getKhfk());		map.put("ytfk", this.getYtfk());
		map.put("ayr", this.getAyr());
		map.put("dlgs", this.getDlgs());		map.put("remarks", this.getRemarks());
		map.put("inputdate", new Date());		
		
		//如果官方意见不是空，则将案件状态更新为官方意见
		if(this.getGfyj() != null && !"".endsWith(this.getGfyj().trim())){
			map.put("ajzt", this.getGfyj().trim());
		}
		return map;
	}
}
