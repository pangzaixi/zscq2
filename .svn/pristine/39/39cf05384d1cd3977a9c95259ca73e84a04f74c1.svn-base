package com.pjgl.cght.sub.controller;


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

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.controller.CghtController;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;


public class AddCghtSubController extends GenericForwardComposer{

	Textbox htbh;
	Datebox qdrq;
	Doublebox zje;
//	Combobox pj; //'所购配件ID',
	Doublebox num; //'数量',
	Doublebox amounts; //'金额',
	Doublebox price; //'单价',
	Textbox remarks; //'备注',
	Textbox pjName;
	Intbox pjid;
	Textbox spec;
	Textbox unit;
	
	Window addCghtSubWin;
	
	Cght cght;
	
	//获取Service
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
		
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		cght = (Cght)Executions.getCurrent().getArg().get("cght");
		if (cght != null) {
			htbh.setValue(cght.getHtbh());
			qdrq.setValue(cght.getQdrq());
			zje.setValue(cght.getZje());
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
		
		map.put("cghtid", cght.getId());
		map.put("num", num.getValue());
		map.put("price", price.getValue());
//		Double n = num.getValue();
		String format = String.format("%.2f", amounts.getValue());
		map.put("amounts", Double.parseDouble(format));
		map.put("pjid", pjid.getValue());
		map.put("remarks", remarks.getValue().trim());
		int result = cghtSubService.insertCghtSub(map);
		
		Map<Object,Object> cghtMap = new HashMap<>();
		cghtMap.put("id", cght.getId());
		Double zje = 0.00;
		if (cght.getZje() != null) {
			zje = cght.getZje();
		}
		cghtMap.put("zje", zje + amounts.getValue());
		cghtService.changeZje(cghtMap);
		
		CghtController cghtController = (CghtController)session.getAttribute("cghtController");
		cghtController.onClick$queryButton();
		System.out.println();
		addCghtSubWin.detach();//关闭窗口
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void onClick$pjName() throws ParseException{
		Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cght/sub/selectPj.zul", null, null);
	     window.doModal();
	}
}
