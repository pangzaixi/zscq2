package com.zscq2.jxqd.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 立案总表中转用表，用于以案件号批量更新
 * @author thinker
 *
 */
public class LazbTmp {
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
	  
	  //----------------------------------
	  private String id1;
	  private String ajh1;		  //'案件号',
	  private String ajlx1;		  // '案件类型',
	  private String sbmc1;		  // '商标名称',
	  private String lb1;          //, '类别',
	  private String sbh1;         // '商标号',
	  private String ajzt1;			//案件状态
	  private Date khwtrq1;        // '客户委托日期',
	  private String khwtrq_str1;  //转换字段
	  private Date sqrq1;		//申请日期
	  private String sqrq_str1;		//申请日期
	  private Date swrq1;		//收文日期
	  private String swrq_str1;		//收文日期
	  private Date csrq1;		//初审日期
	  private String csrq_str1;//初审日期
	  private String ggq1;         //'公告期',
	  private Date jxr1;           //'绝限日',
	  private String jxr_str1;     //'绝限日',
	  private Date sctjr1;         //'首次提交日',
	  private String scbsbh1;		//首次报送编号
	  private String sctjr_str1;   //'首次提交日',
	  private Date bcqx1;        //'补充期限',
	  private String bcqx_str1;
	  private Date bctjrq1;        //'补充提交日期',
	  private String bctjrq_str1;  //'补充提交日期',
	  private String ecbsbh1;		//二次报送编号
	  private String wtkhmc1;      //'委托客户名称',
	  private String byyr1;        //'被异议人
	  private String gfyj1;        //'官方意见',
	  private Date	 gfyjswr1;		//官方意见收文日
	  private Date   gfyjjxr1;		//官方意见绝限日
	  private String dlr1;         //'代理人',
	  private String dlr21;        //'代理人2',
	  private String yysqr1;		//异议申请人
	  private String khfk1;        //'客户付款',
	  private String ytfk1;		//盈天付款
	  private String ayr1;         //'案源人',
	  private String dlgs1;        //'代理公司',
	  private Date inputdate1;	  //录入日期
	  private String creater1;     //录入人
	  private Date updatedate1;		//更新日期
	  private String updater1;		//更新人
	  private String remarks1;     //备注
	  private String status1;		//状态 状态，默认0：未完结，完结的案件为：1
	  private String style1;			//保存记录颜色用，用于页面显示
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
	public String getAjzt() {
		return ajzt;
	}
	public void setAjzt(String ajzt) {
		this.ajzt = ajzt;
	}
	public Date getKhwtrq() {
		return khwtrq;
	}
	public void setKhwtrq(Date khwtrq) {
		this.khwtrq = khwtrq;
	}
	public String getKhwtrq_str() {
		return khwtrq_str;
	}
	public void setKhwtrq_str(String khwtrq_str) {
		this.khwtrq_str = khwtrq_str;
	}
	public Date getSqrq() {
		return sqrq;
	}
	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
	}
	public String getSqrq_str() {
		return sqrq_str;
	}
	public void setSqrq_str(String sqrq_str) {
		this.sqrq_str = sqrq_str;
	}
	public Date getSwrq() {
		return swrq;
	}
	public void setSwrq(Date swrq) {
		this.swrq = swrq;
	}
	public String getSwrq_str() {
		return swrq_str;
	}
	public void setSwrq_str(String swrq_str) {
		this.swrq_str = swrq_str;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public String getCsrq_str() {
		return csrq_str;
	}
	public void setCsrq_str(String csrq_str) {
		this.csrq_str = csrq_str;
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
	public String getJxr_str() {
		return jxr_str;
	}
	public void setJxr_str(String jxr_str) {
		this.jxr_str = jxr_str;
	}
	public Date getSctjr() {
		return sctjr;
	}
	public void setSctjr(Date sctjr) {
		this.sctjr = sctjr;
	}
	public String getScbsbh() {
		return scbsbh;
	}
	public void setScbsbh(String scbsbh) {
		this.scbsbh = scbsbh;
	}
	public String getSctjr_str() {
		return sctjr_str;
	}
	public void setSctjr_str(String sctjr_str) {
		this.sctjr_str = sctjr_str;
	}
	public Date getBcqx() {
		return bcqx;
	}
	public void setBcqx(Date bcqx) {
		this.bcqx = bcqx;
	}
	public String getBcqx_str() {
		return bcqx_str;
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
	public String getBctjrq_str() {
		return bctjrq_str;
	}
	public void setBctjrq_str(String bctjrq_str) {
		this.bctjrq_str = bctjrq_str;
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
	public String getYysqr() {
		return yysqr;
	}
	public void setYysqr(String yysqr) {
		this.yysqr = yysqr;
	}
	public String getKhfk() {
		return khfk;
	}
	public void setKhfk(String khfk) {
		this.khfk = khfk;
	}
	public String getYtfk() {
		return ytfk;
	}
	public void setYtfk(String ytfk) {
		this.ytfk = ytfk;
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
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getAjh1() {
		return ajh1;
	}
	public void setAjh1(String ajh1) {
		this.ajh1 = ajh1;
	}
	public String getAjlx1() {
		return ajlx1;
	}
	public void setAjlx1(String ajlx1) {
		this.ajlx1 = ajlx1;
	}
	public String getSbmc1() {
		return sbmc1;
	}
	public void setSbmc1(String sbmc1) {
		this.sbmc1 = sbmc1;
	}
	public String getLb1() {
		return lb1;
	}
	public void setLb1(String lb1) {
		this.lb1 = lb1;
	}
	public String getSbh1() {
		return sbh1;
	}
	public void setSbh1(String sbh1) {
		this.sbh1 = sbh1;
	}
	public String getAjzt1() {
		return ajzt1;
	}
	public void setAjzt1(String ajzt1) {
		this.ajzt1 = ajzt1;
	}
	public Date getKhwtrq1() {
		return khwtrq1;
	}
	public void setKhwtrq1(Date khwtrq1) {
		this.khwtrq1 = khwtrq1;
	}
	public String getKhwtrq_str1() {
		return khwtrq_str1;
	}
	public void setKhwtrq_str1(String khwtrq_str1) {
		this.khwtrq_str1 = khwtrq_str1;
	}
	public Date getSqrq1() {
		return sqrq1;
	}
	public void setSqrq1(Date sqrq1) {
		this.sqrq1 = sqrq1;
	}
	public String getSqrq_str1() {
		return sqrq_str1;
	}
	public void setSqrq_str1(String sqrq_str1) {
		this.sqrq_str1 = sqrq_str1;
	}
	public Date getSwrq1() {
		return swrq1;
	}
	public void setSwrq1(Date swrq1) {
		this.swrq1 = swrq1;
	}
	public String getSwrq_str1() {
		return swrq_str1;
	}
	public void setSwrq_str1(String swrq_str1) {
		this.swrq_str1 = swrq_str1;
	}
	public Date getCsrq1() {
		return csrq1;
	}
	public void setCsrq1(Date csrq1) {
		this.csrq1 = csrq1;
	}
	public String getCsrq_str1() {
		return csrq_str1;
	}
	public void setCsrq_str1(String csrq_str1) {
		this.csrq_str1 = csrq_str1;
	}
	public String getGgq1() {
		return ggq1;
	}
	public void setGgq1(String ggq1) {
		this.ggq1 = ggq1;
	}
	public Date getJxr1() {
		return jxr1;
	}
	public void setJxr1(Date jxr1) {
		this.jxr1 = jxr1;
	}
	public String getJxr_str1() {
		return jxr_str1;
	}
	public void setJxr_str1(String jxr_str1) {
		this.jxr_str1 = jxr_str1;
	}
	public Date getSctjr1() {
		return sctjr1;
	}
	public void setSctjr1(Date sctjr1) {
		this.sctjr1 = sctjr1;
	}
	public String getScbsbh1() {
		return scbsbh1;
	}
	public void setScbsbh1(String scbsbh1) {
		this.scbsbh1 = scbsbh1;
	}
	public String getSctjr_str1() {
		return sctjr_str1;
	}
	public void setSctjr_str1(String sctjr_str1) {
		this.sctjr_str1 = sctjr_str1;
	}
	public Date getBcqx1() {
		return bcqx1;
	}
	public void setBcqx1(Date bcqx1) {
		this.bcqx1 = bcqx1;
	}
	public String getBcqx_str1() {
		return bcqx_str1;
	}
	public void setBcqx_str1(String bcqx_str1) {
		this.bcqx_str1 = bcqx_str1;
	}
	public Date getBctjrq1() {
		return bctjrq1;
	}
	public void setBctjrq1(Date bctjrq1) {
		this.bctjrq1 = bctjrq1;
	}
	public String getBctjrq_str1() {
		return bctjrq_str1;
	}
	public void setBctjrq_str1(String bctjrq_str1) {
		this.bctjrq_str1 = bctjrq_str1;
	}
	public String getEcbsbh1() {
		return ecbsbh1;
	}
	public void setEcbsbh1(String ecbsbh1) {
		this.ecbsbh1 = ecbsbh1;
	}
	public String getWtkhmc1() {
		return wtkhmc1;
	}
	public void setWtkhmc1(String wtkhmc1) {
		this.wtkhmc1 = wtkhmc1;
	}
	public String getByyr1() {
		return byyr1;
	}
	public void setByyr1(String byyr1) {
		this.byyr1 = byyr1;
	}
	public String getGfyj1() {
		return gfyj1;
	}
	public void setGfyj1(String gfyj1) {
		this.gfyj1 = gfyj1;
	}
	public Date getGfyjswr1() {
		return gfyjswr1;
	}
	public void setGfyjswr1(Date gfyjswr1) {
		this.gfyjswr1 = gfyjswr1;
	}
	public Date getGfyjjxr1() {
		return gfyjjxr1;
	}
	public void setGfyjjxr1(Date gfyjjxr1) {
		this.gfyjjxr1 = gfyjjxr1;
	}
	public String getDlr1() {
		return dlr1;
	}
	public void setDlr1(String dlr1) {
		this.dlr1 = dlr1;
	}
	public String getDlr21() {
		return dlr21;
	}
	public void setDlr21(String dlr21) {
		this.dlr21 = dlr21;
	}
	public String getYysqr1() {
		return yysqr1;
	}
	public void setYysqr1(String yysqr1) {
		this.yysqr1 = yysqr1;
	}
	public String getKhfk1() {
		return khfk1;
	}
	public void setKhfk1(String khfk1) {
		this.khfk1 = khfk1;
	}
	public String getYtfk1() {
		return ytfk1;
	}
	public void setYtfk1(String ytfk1) {
		this.ytfk1 = ytfk1;
	}
	public String getAyr1() {
		return ayr1;
	}
	public void setAyr1(String ayr1) {
		this.ayr1 = ayr1;
	}
	public String getDlgs1() {
		return dlgs1;
	}
	public void setDlgs1(String dlgs1) {
		this.dlgs1 = dlgs1;
	}
	public Date getInputdate1() {
		return inputdate1;
	}
	public void setInputdate1(Date inputdate1) {
		this.inputdate1 = inputdate1;
	}
	public String getCreater1() {
		return creater1;
	}
	public void setCreater1(String creater1) {
		this.creater1 = creater1;
	}
	public Date getUpdatedate1() {
		return updatedate1;
	}
	public void setUpdatedate1(Date updatedate1) {
		this.updatedate1 = updatedate1;
	}
	public String getUpdater1() {
		return updater1;
	}
	public void setUpdater1(String updater1) {
		this.updater1 = updater1;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStyle1() {
		return style1;
	}
	public void setStyle1(String style1) {
		this.style1 = style1;
	}
	  
	  
	  
	  
}
