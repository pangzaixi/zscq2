package com.pjgl.lysqSub.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.controller.LysqController;
import com.pjgl.lysq.service.serviceImpl.LysqService;
import com.pjgl.lysqSub.service.serviceImpl.LysqSubService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;

public class AddLysqSubController extends GenericForwardComposer{
	Textbox companyName;
	Datebox lyrq;
	
//	Combobox pj; //'所购配件ID',
	Doublebox num; //'数量',
	Doublebox sscx; //'金额',
	Textbox wz; //'单价',
	Textbox remarks; //'备注',
	Textbox pjName;
	Intbox pjid;
	Textbox spec;
	Textbox unit;
	
	Window addLysqSubWin;
	
	Lysq lysq;
	
	//获取Service
	LysqSubService lysqSubService = (LysqSubService)SpringUtil.getBean("lysqSubService");
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	LysqService  lysqService = (LysqService)SpringUtil.getBean("lysqService");
		
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		lysq = (Lysq)Executions.getCurrent().getArg().get("lysq");
		if (lysq != null) {
			
			lyrq.setValue(lysq.getLyrq());
			companyName.setValue(lysq.getCompanyName());
			
		}
		
		Pj pj = (Pj)Executions.getCurrent().getArg().get("pj");	
		if (pj != null) {
			pjName.setValue(pj.getName());
			pjid.setValue(pj.getId());
			spec.setValue(pj.getSpec());
			unit.setValue(pj.getUnit());
		}
		
//		User user = (User)session.getAttribute("user");
//		Map map = new HashMap<Object,Object>();
//		map.put("begin", 0);
//		map.put("end", 100);
//		map.put("companyid", user.getCompanyid());
//		List<Pj> list = pjService.selectPj(map);
//		ListModel listModel = new ListModelList(list);
//		pj.setModel(listModel);
	}
	
	/**
	 * 添加配件记录
	 * @throws java.text.ParseException 
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
		
		map.put("lysqid", lysq.getId());
		map.put("num", num.getValue());
		map.put("sscx", sscx.getValue());
		map.put("wz", wz.getValue());
		map.put("pjid", pjid.getValue());
		map.put("remarks", remarks.getValue().trim());
		int result = lysqSubService.insertLysqSub(map);
	
		
		LysqController lysqController = (LysqController)session.getAttribute("lysqController");
		lysqController.onClick$queryButton();
		System.out.println();
		addLysqSubWin.detach();//关闭窗口
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void onClick$pjName() throws ParseException{
//		Window window = (Window)Executions.createComponents(
//	             "/jsp/pjgl/lysqSub/selectPj.zul", null, null);
//	     window.doModal();
	}
}
