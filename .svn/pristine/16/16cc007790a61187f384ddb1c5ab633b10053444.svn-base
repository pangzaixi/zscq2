package com.zscq2.ss.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.controller.LazbController;
import com.zscq2.ss.bean.Ssaj;
import com.zscq2.ss.service.SsajService;


@Controller("editSsController")   
@Scope("prototype") 
public class EditSsController extends GenericForwardComposer{
	
	//获取service对象
	SsajService ssajService = (SsajService)SpringUtil.getBean("ssajService");
	//页面元素
	Window editSs;//新增窗口ID
	  Textbox ajh;// '案件号',
	  Combobox ajlx;//  '案件类型',
	  Textbox hth;
	  Combobox ay;//案由
	  Combobox ajzt;//案件状态
	  Combobox sj;//审级
	  Textbox yg;//  '原告
	  Textbox bg;// 被告
	  Textbox dsr;//第三人
	  Textbox gxfy;//管辖法院
	  Textbox szbd;//诉争标地
	  Datebox wtrq;//  委托日期',
	  Datebox larq;// 立案日期
	  Datebox dbrq;// 答辩日期
	  Datebox ktrq;// '开庭日期',
	  Datebox dtjclrq;//待提交材料日期
	  Datebox pjsdrq;//判决收到日期
	  Datebox ssqjxr;//上诉期绝限日
	  
	  Textbox dlr;// 代理人
	  Textbox lhbar;// 联合办案人
	  Textbox sjydh;//书记员电话
	  Textbox cbfg;//承办法官
	  Textbox jcdlf;//基础代理费
	  Textbox fxdlf;//风险代理费
	  Checkbox zf;//是否支付
	  Checkbox kjfp;//是否开具发票
	  Textbox remarks;//备注
	 //后台全局变量
	  Ssaj ssaj;
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		ssaj = (Ssaj)Executions.getCurrent().getArg().get("ssaj");	
		//页面初始化
		ajh.setValue(ssaj.getAjh());
		ajlx.setValue(ssaj.getAjlx());
		hth.setValue(ssaj.getHth());
		ay.setValue(ssaj.getAy());
		ajzt.setValue(ssaj.getAjzt());
		sj.setValue(ssaj.getSj());
		yg.setValue(ssaj.getYg());
		bg.setValue(ssaj.getBg());
		dsr.setValue(ssaj.getDsr());
		gxfy.setValue(ssaj.getGxfy());
		szbd.setValue(ssaj.getSzbd());
		wtrq.setValue(ssaj.getWtrq());
		larq.setValue(ssaj.getLarq());
		dbrq.setValue(ssaj.getDbrq());
		ktrq.setValue(ssaj.getKtrq());
		dtjclrq.setValue(ssaj.getDtjclrq());
		pjsdrq.setValue(ssaj.getPjsdrq());
		ssqjxr.setValue(ssaj.getSsqjxr());
		
		dlr.setValue(ssaj.getDlr());
		lhbar.setValue(ssaj.getLhbar());
		sjydh.setValue(ssaj.getSjydh());
		cbfg.setValue(ssaj.getCbfg());
		jcdlf.setValue(ssaj.getJcdlf());
		fxdlf.setValue(ssaj.getFxdlf());
		zf.setChecked(ssaj.isZf());
		kjfp.setChecked(ssaj.getKjfp());
		remarks.setValue(ssaj.getRemarks());

		componentInit();//页面下拉选初始化
	}	
	
	/**
	 * 更新诉讼案件
	 * @throws ParseException 
	 */
	public void onClick$addBtn() throws ParseException{
		Map<Object,Object> map = new HashMap<>();
		map.put("id", ssaj.getId());
		map.put("hth", hth.getValue());
		map.put("ay",ay.getValue());
		map.put("ajzt",ajzt.getValue());
		map.put("sj",sj.getValue());
		map.put("yg",yg.getValue());
		map.put("bg",bg.getValue());
		map.put("dsr",dsr.getValue());
		map.put("gxfy",gxfy.getValue());
		map.put("szbd",szbd.getValue());
		map.put("wtrq",wtrq.getValue());
		map.put("larq",larq.getValue());
		map.put("dbrq",dbrq.getValue());
		map.put("ktrq",ktrq.getValue());
		map.put("dtjclrq",dtjclrq.getValue());
		map.put("pjsdrq",pjsdrq.getValue());
		map.put("ssqjxr",ssqjxr.getValue());
		map.put("dlr",dlr.getValue());
		map.put("lhbar",lhbar.getValue());
		map.put("sjydh",sjydh.getValue());
		map.put("cbfg",cbfg.getValue());
		map.put("jcdlf",jcdlf.getValue());
		map.put("fxdlf",fxdlf.getValue());
		map.put("zf",zf.isChecked());
		map.put("kjfp",kjfp.isChecked());
		map.put("remarks",remarks.getValue());

		ssajService.updateSsaj(map);
		
		SsController ssController = (SsController)session.getAttribute("ssController");
		ssController.onClick$queryButton();
		Messagebox.show("更新保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		editSs.detach();
	}
	/**
	 * 页面下拉选初始化
	 */
	private void componentInit(){
		//案由
		List<Ajlx> list_ay = new ArrayList<Ajlx>();
		Ajlx ay1 = new Ajlx();
		ay1.setAjlx("合同纠纷");
		list_ay.add(ay1);
		
		Ajlx ay2 = new Ajlx();
		ay2.setAjlx("劳动纠纷");
		list_ay.add(ay2);
		
		Ajlx ay3 = new Ajlx();
		ay3.setAjlx("离婚纠纷");
		list_ay.add(ay3);
		
		ListModel ayModelList =new ListModelList<Ajlx>();
		ayModelList = new ListModelList(list_ay);
		
		ay.setModel(ayModelList);
		
		
		//案件状态
		List<Ajlx> list_ajzt = new ArrayList<Ajlx>();
		Ajlx ajzt1 = new Ajlx();
		ajzt1.setAjlx("待立案");
		list_ajzt.add(ajzt1);
		
		Ajlx ajzt2 = new Ajlx();
		ajzt2.setAjlx("已立案");
		list_ajzt.add(ajzt2);
		
		Ajlx ajzt3 = new Ajlx();
		ajzt3.setAjlx("管辖权异议");
		list_ajzt.add(ajzt3);
		
		Ajlx ajzt4 = new Ajlx();
		ajzt4.setAjlx("待开庭");
		list_ajzt.add(ajzt4);

		Ajlx ajzt5 = new Ajlx();
		ajzt5.setAjlx("已开庭");
		list_ajzt.add(ajzt5);

		Ajlx ajzt6 = new Ajlx();
		ajzt6.setAjlx("待第N次开庭");
		list_ajzt.add(ajzt6);

		Ajlx ajzt7 = new Ajlx();
		ajzt7.setAjlx("待提交材料");
		list_ajzt.add(ajzt7);
		

		Ajlx ajzt8 = new Ajlx();
		ajzt8.setAjlx("庭审结束");
		list_ajzt.add(ajzt8);

		Ajlx ajzt9 = new Ajlx();
		ajzt9.setAjlx("撤诉");
		list_ajzt.add(ajzt9);

		Ajlx ajzt10 = new Ajlx();
		ajzt10.setAjlx("调解结案");
		list_ajzt.add(ajzt10);

		Ajlx ajzt11 = new Ajlx();
		ajzt11.setAjlx("判决结案");
		list_ajzt.add(ajzt11);

		Ajlx ajzt12 = new Ajlx();
		ajzt12.setAjlx("中止审理");
		list_ajzt.add(ajzt12);

		Ajlx ajzt13 = new Ajlx();
		ajzt13.setAjlx("移送其院");
		list_ajzt.add(ajzt13);

		
		ListModel ajztModelList =new ListModelList<Ajlx>();
		ajztModelList = new ListModelList(list_ajzt);
		
		ajzt.setModel(ajztModelList);
		
		//审级
		List<Ajlx> list_sj = new ArrayList<Ajlx>();
		Ajlx sj1 = new Ajlx();
		sj1.setAjlx("一审");
		list_sj.add(sj1);
		
		Ajlx sj2 = new Ajlx();
		sj2.setAjlx("二审");
		list_sj.add(sj2);
		
		Ajlx sj3 = new Ajlx();
		sj3.setAjlx("再审");
		list_sj.add(sj3);
		
		Ajlx sj4 = new Ajlx();
		sj4.setAjlx("仲裁");
		list_sj.add(sj4);
		
		Ajlx sj5 = new Ajlx();
		sj5.setAjlx("非诉审理");
		list_sj.add(sj5);
		
		ListModel sjModelList =new ListModelList<Ajlx>();
		sjModelList = new ListModelList(list_sj);
		
		sj.setModel(sjModelList);
	}
}