package com.zscq2.jxqd.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.utils.LogManage;
import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;

@Controller("editLazbController")   
@Scope("prototype") 
public class EditLazbController  extends GenericForwardComposer{

	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	Window editJxqd;
	
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
		
	private Lazb lazb;//接收要编辑的记录
	  Textbox ajh;// '案件号',
	  Textbox ajlx;//  '案件类型',
	  Textbox sbmc;//  '商标名称',
	  Textbox lb;// , '类别',
	  Textbox sbh;//  '商标号',
	  Combobox ajzt_c;//案件状态
	  
	  Datebox khwtrq;//  '客户委托日期',
	  Textbox khwtrq_str;//转换字段
	  Datebox sqrq;// '申请日',
	  Textbox sqr_str;// '申请日',
	  Datebox swrq;// '收文日期',
	  Textbox swrq_str;// '收文日期',
	  Datebox csrq;// '初审日期',
	  Textbox csrq_str;// '初审日期',
	  Textbox ggq;// '公告期',
	  Datebox jxr;// '绝限日',
	  Textbox jxr_str;// '绝限日',
	  Datebox sctjr;// '首次提交日',
	  Textbox sctjr_str;// '首次提交日',
	  Datebox bcqx;// '补充期限',
	  Datebox bctjrq;// '补充提交日期',
	  Textbox bctjrq_str;// '补充提交日期',
	  Textbox scbsbh;// '首次报送编号',
	  Textbox ecbsbh;// '二次报送编号',
	  Textbox wtkhmc;// '委托客户名称',
	  Textbox byyr;// '被异议人',
	  Textbox yysqr;//异议申请人
	  Textbox gfyj;// '官方意见',
	  Textbox wfdsr;// '申请人/请求人/我方当事人',
	  
	  Datebox gfyjswr;//官方结果收文日期
	  Datebox gfyjjxr;//官方结果绝限日期
	  Textbox dlr;// '代理人',
	  Textbox dlr2;// '代理人2',
	  
	  Textbox ayr;// '案源人',
	  Textbox dlgs;// '代理公司',
	  Doublebox gf;// '官费',
	  Doublebox dlf;// '代理费',
	  Textbox khfk;// '客户付款',
	  Textbox ytfk;//盈天付款
	  Doublebox ygkhkp;// '已给客户开票',
	  Doublebox djgsfk;// '代交公司付款',
	  Doublebox ygdjgskp;// '已给代交公司开票',
	  Doublebox dlryzftcf;// '代理人已支付提成费',
	  Doublebox ayryzftcf;// '案源人已支付提成费',
	  Textbox remarks;//备注
	  
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		System.out.println();
		lazb = (Lazb)Executions.getCurrent().getArg().get("lazb");		
		ajh.setValue(lazb.getAjh());
		ajlx.setValue(lazb.getAjlx());
		sbmc.setValue(lazb.getSbmc());
		lb.setValue(lazb.getLb());
		sbh.setValue(lazb.getSbh());
		ajzt_c.setValue(lazb.getAjzt());
		ajzt_c.setText(lazb.getAjzt());
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
		gfyj.setValue(lazb.getGfyj());
		gfyjswr.setValue(lazb.getGfyjswr());
		gfyjjxr.setValue(lazb.getGfyjjxr());
		dlr.setValue(lazb.getDlr());
		dlr2.setValue(lazb.getDlr2());
		yysqr.setValue(lazb.getYysqr());
		ayr.setValue(lazb.getAyr());
		dlgs.setValue(lazb.getDlgs());
		khfk.setValue(lazb.getKhfk());
		ytfk.setValue(lazb.getYtfk());
		remarks.setValue(lazb.getRemarks());
		///-------------------------
		List<Ajzt> list_ajzt = new ArrayList<Ajzt>();
		
		
		Ajzt ajzt0 = new Ajzt();
		ajzt0.setAjzt("待提交");
		list_ajzt.add(ajzt0);
		Ajzt ajzt1 = new Ajzt();
		ajzt1.setAjzt("受理");
		list_ajzt.add(ajzt1);
		Ajzt ajzt2 = new Ajzt();
		ajzt2.setAjzt("待补正");
		list_ajzt.add(ajzt2);
		Ajzt ajzt3 = new Ajzt();
		ajzt3.setAjzt("已提交补正");
		list_ajzt.add(ajzt3);
		Ajzt ajzt4 = new Ajzt();
		ajzt4.setAjzt("不予受理");
		list_ajzt.add(ajzt4);
		Ajzt ajzt5 = new Ajzt();
		ajzt5.setAjzt("权利终止结案");
		list_ajzt.add(ajzt5);
		//案件状态
		
		 if(lazb.getAjlx() != null && !"".equals(lazb.getAjlx().trim())){
			 Map map = new HashMap<Object,Object>();
			 map.put("ajlx", lazb.getAjlx().trim());
				List<Ajzt> list_ajzt2 = lazbService.selectAjzt(map);
				list_ajzt.addAll(list_ajzt2);
				
		 }
		 ajzt_c.setModel(new ListModelList(list_ajzt));
	}
	
	/**
	 * 更新记录
	 * @throws ParseException 
	 */
	public void onClick$editBtn() throws ParseException{
		//获取service对象
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		Map<Object,Object> map = new HashMap<>();
		getInfoFromPage(map);
		if(jxr.getValue() == null && bcqx.getValue() !=null){
			Messagebox.show("绝限日为空时，不能编辑补充期限，保存失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
//		Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false； 
		if(jxr.getValue() != null && bcqx.getValue() !=null && !bcqx.getValue().after(jxr.getValue())){
			Messagebox.show("补充期限需要晚于绝限日，保存失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		map.put("id", lazb.getId());
		map.put("updatedate", new Date());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		try {
			lazbService.editLazb(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messagebox.show("保存失败，一般是某些信息长度超出限制，请复制以下信息提交给管理员\n"+map.toString(),"注意", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		editJxqd.detach();
		System.out.println();
		LazbController lazbController = (LazbController)session.getAttribute("lazbController");
		lazbController.onClick$queryButton();
		
		//记录操作日志
		LogManage logManage = new LogManage();
		logManage.addLog("更新案件："+lazb.getAjh(),user,"更新案件");
		
		
		
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
		if(ajzt_c.getValue() != null && !"".equals(ajzt_c.getValue())){
			map.put("ajzt",ajzt_c.getValue() );
		}//  '案件状态',
		if(khwtrq.getValue() != null && !"".equals(khwtrq.getValue())){
			map.put("khwtrq",khwtrq.getValue() );
		}//  '客户委托日期',
		
		if(sqrq.getValue() != null && !"".equals(sqrq.getValue())){
			map.put("sqrq",sqrq.getValue() );
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
		if(byyr.getValue() != null && !"".equals(byyr.getValue())){
			map.put("byyr",byyr.getValue() );
		}// '被请求人/商标权人/对方相对人',
		if(gfyj.getValue() != null && !"".equals(gfyj.getValue())){
			map.put("gfyj",gfyj.getValue() );
		}// '官方结果',
		if(gfyjswr.getValue() != null && !"".equals(gfyjswr.getValue())){
			map.put("gfyjswr",gfyjswr.getValue() );
		}// '官方结果收文日',
		if(gfyjjxr.getValue() != null && !"".equals(gfyjjxr.getValue())){
			map.put("gfyjjxr",gfyjjxr.getValue() );
		}// '官方结果绝限日',
		if(dlr.getValue() != null && !"".equals(dlr.getValue())){
			map.put("dlr",dlr.getValue() );
		}// '代理人',
		if(dlr2.getValue() != null && !"".equals(dlr2.getValue())){
			map.put("dlr2",dlr2.getValue() );
		}// '代理人2',
		if(yysqr.getValue() != null && !"".equals(yysqr.getValue())){
			map.put("yysqr",yysqr.getValue() );
		}// '异议申请人',
		if(ayr.getValue() != null && !"".equals(ayr.getValue())){
			map.put("ayr",ayr.getValue() );
		}// '案源人',
		if(dlgs.getValue() != null && !"".equals(dlgs.getValue())){
			map.put("dlgs",dlgs.getValue() );
		}// '代理公司',
		
		if(khfk.getValue() != null && !"".equals(khfk.getValue())){
			map.put("khfk",khfk.getValue() );
		}// '客户付款',
		if(ytfk.getValue() != null && !"".equals(ytfk.getValue())){
			map.put("ytfk",ytfk.getValue() );
		}//
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue() );
		}//备注
		
		
	}
}
