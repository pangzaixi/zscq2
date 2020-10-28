package com.yewu.jxqd.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Lazb;

@Controller("editJxqdController")   
@Scope("prototype") 
public class EditJxqdController  extends GenericForwardComposer{

	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	Window editJxqd;
	private Lazb lazb;//接收要编辑的记录
	  Textbox ajh;// '案件号',
	  Textbox ajlx;//  '案件类型',
	  Textbox sbmc;//  '商标名称',
	  Textbox lb;// , '类别',
	  Textbox sbh;//  '商标号',
	  Datebox khwtrq;//  '客户委托日期',
	  Textbox khwtrq_str;//转换字段
	  Datebox sqrq;// '申请日',
	  Textbox sqrq_str;// '申请日',
	  Datebox swrq;// '收文日期',
	  Textbox swrq_str;// '收文日期',
	  Datebox csrq;// '初审日期',
	  Textbox csrq_str;// '初审日期',
	  Textbox ggq;// '公告期',
	  Datebox jxr;// '绝限日',
	  Textbox jxr_str;// '绝限日',
	  Datebox sctjr;// '首次提交日',
	  Textbox sctjr_str;// '首次提交日',
	  Textbox scbsbh;// '首次报送编号',
	  Datebox bcqx;// '补充期限',
	  Datebox bctjrq;// '补充提交日期',
	  Textbox bctjrq_str;// '补充提交日期',
	  Textbox ecbsbh;// '二次报送编号',
	  Textbox wtkhmc;// '委托客户名称',
	  Textbox byyr;// '被异议人',
	  Textbox gfyj;// '官方结果',
	  Textbox dlr;// '代理人',
	  Textbox dlr2;// '代理人2',
	  Textbox yysqr;//异议申请人
	  Doublebox khfk;//客户付款
	  Doublebox ykfk;//盈科付款
	  Textbox ayr;// '案源人',
	  Textbox dlgs;// '代理公司',
	  Textbox remarks;//备注
	  Combobox  status;//状态
	  
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		System.out.println();
		lazb = (Lazb)Executions.getCurrent().getArg().get("lazb");
		
		ajh.setValue(lazb.getAjh());
		ajlx.setValue(lazb.getAjlx());
		sbmc.setValue(lazb.getSbmc());
		lb.setValue(lazb.getLb());
		sbh.setValue(lazb.getSbh());
		khwtrq.setValue(lazb.getKhwtrq());
		sqrq.setValue(lazb.getSqrq());
		swrq.setValue(lazb.getSwrq());
		csrq.setValue(lazb.getCsrq());
		ggq.setValue(lazb.getGgq());
		jxr.setValue(lazb.getJxr());
		sctjr.setValue(lazb.getSctjr());
		bcqx.setValue(lazb.getBcqx());
		bctjrq.setValue(lazb.getBctjrq());
		scbsbh.setValue(lazb.getScbsbh());
		ecbsbh.setValue(lazb.getEcbsbh());
		wtkhmc.setValue(lazb.getWtkhmc());
		byyr.setValue(lazb.getByyr());
		dlr.setValue(lazb.getDlr());
		dlr2.setValue(lazb.getDlr2());
		ayr.setValue(lazb.getAyr());
		dlgs.setValue(lazb.getDlgs());
		remarks.setValue(lazb.getRemarks());
		status.setValue(lazb.getStatus());
	}
	
	/**
	 * 更新记录
	 * @throws ParseException 
	 */
	public void onClick$editBtn() throws ParseException{
		//获取service对象
		JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
		Map<Object,Object> map = new HashMap<>();
		getInfoFromPage(map);
		map.put("id", lazb.getId());
		map.put("updatedate", new Date());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		jxqdService.editJxqd(map);
		editJxqd.detach();
		System.out.println();
		JxqdController jxqdController = (JxqdController)session.getAttribute("jxqdController");
		jxqdController.onClick$queryButton();
		
	}
	/**
	 * 将页面传递来的信息存入MAP
	 * @return
	 */
	private void getInfoFromPage(Map<Object,Object> map){
		if(ajh.getValue() != null && !"".equals(ajh.getValue())){
			map.put("ajh", ajh.getValue());
		}// '案件号',
		if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
			map.put("ajlx", ajlx.getValue());
		}//  '案件类型',
		if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
			map.put("sbmc",sbmc.getValue() );
		}//  '商标名称',                    
		if(lb.getValue() != null && !"".equals(lb.getValue())){
			map.put("lb",lb.getValue() );
		}// , '类别',
		if(sbh.getValue() != null && !"".equals(sbh.getValue())){
			map.put("sbh",sbh.getValue() );
		}//  '商标号',
		if(khwtrq.getValue() != null && !"".equals(khwtrq.getValue())){
			map.put("khwtrq",khwtrq.getValue() );
		}//  '客户委托日期',
		
		if(sqrq.getValue() != null && !"".equals(sqrq.getValue())){
			map.put("sqr",sqrq.getValue() );
		}// '申请日',
		
		if(swrq.getValue() != null && !"".equals(swrq.getValue())){
			map.put("swrq",swrq.getValue() );
		}// '收文日期',
		
		if(csrq.getValue() != null && !"".equals(csrq.getValue())){
			map.put("csrq",csrq.getValue() );
		}// '初审日期',
		if(ggq.getValue() != null && !"".equals(ggq.getValue())){
			map.put("ggq",ggq.getValue() );
		}// '公告期',
		if(jxr.getValue() != null && !"".equals(jxr.getValue())){
			map.put("jxr",jxr.getValue() );
		}// '绝限日',
		
		if(sctjr.getValue() != null && !"".equals(sctjr.getValue())){
			map.put("sctjr",sctjr.getValue() );
		}// '首次提交日',
		
		if(bcqx.getValue() != null && !"".equals(bcqx.getValue())){
			map.put("bcqx",bcqx.getValue() );
		}// '补充期限',
		if(bctjrq.getValue() != null && !"".equals(bctjrq.getValue())){
			map.put("bctjrq",bctjrq.getValue() );
		}// '补充提交日期',
		
		if(scbsbh.getValue() != null && !"".equals(scbsbh.getValue())){
			map.put("scbsbh",scbsbh.getValue() );
		}// '首次报送编号',
		if(ecbsbh.getValue() != null && !"".equals(ecbsbh.getValue())){
			map.put("ecbsbh",ecbsbh.getValue() );
		}// '二次报送编号',
		if(wtkhmc.getValue() != null && !"".equals(wtkhmc.getValue())){
			map.put("wtkhmc",wtkhmc.getValue() );
		}// '委托客户名称',
		
		if(dlr.getValue() != null && !"".equals(dlr.getValue())){
			map.put("dlr",dlr.getValue() );
		}// '代理人',
		if(dlr2.getValue() != null && !"".equals(dlr2.getValue())){
			map.put("dlr2",dlr2.getValue() );
		}// '代理人2',
		if(ayr.getValue() != null && !"".equals(ayr.getValue())){
			map.put("ayr",ayr.getValue() );
		}// '案源人',
		if(dlgs.getValue() != null && !"".equals(dlgs.getValue())){
			map.put("dlgs",dlgs.getValue() );
		}// '代理公司',
		
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue() );
		}//备注
		
		
	}
}
