package com.pjgl.cght.sub.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.controller.CghtController;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cght.sub.bean.CghtSub;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.yewu.zscq.bean.User;

public class EditCghtSubController extends GenericForwardComposer{

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
	
	Window editCghtSubWin;
	
	CghtSub cghtSub;
	Cght cght2;
	
	//获取Service
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
		
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		cghtSub = (CghtSub)Executions.getCurrent().getArg().get("cghtSub");		
		cght2 = cghtService.getById(cghtSub.getCghtid());
		htbh.setValue(cght2.getHtbh());
		qdrq.setValue(cght2.getQdrq());
		zje.setValue(cght2.getZje());
		num.setValue(cghtSub.getNum());
		price.setValue(cghtSub.getPrice());
		amounts.setValue(cghtSub.getAmounts());
		Pj pj2 = pjService.getById(cghtSub.getPjid());
		pjName.setValue(pj2.getName());
		pjid.setValue(pj2.getId());
		spec.setValue(pj2.getSpec());
		unit.setValue(pj2.getUnit());
		
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
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
		String format = String.format("%.2f", amounts.getValue());
		Double money = Double.parseDouble(format);
		Double zjeMoney = cght2.getZje() - cghtSub.getAmounts();
				
		map.put("num", num.getValue());
		map.put("price", price.getValue());
//		Double n = num.getValue();
		map.put("amounts", money);
		map.put("pjid", pjid.getValue());
		map.put("remarks", remarks.getValue().trim());
		map.put("id", cghtSub.getId());
		int result = cghtSubService.editCghtSub(map);
		
		if (zjeMoney + money != cght2.getZje()) {
			Map<Object,Object> cghtMap = new HashMap<>();
			cghtMap.put("id", cght2.getId());
			cghtMap.put("zje", zjeMoney + money);
			cghtService.changeZje(cghtMap);
		}
		CghtSubController cghtSubController = (CghtSubController)session.getAttribute("cghtSubController");
		cghtSubController.onClick$querySubButton();
		cghtSubController.changeZje(zjeMoney + money);
//		CghtController cghtController = (CghtController)session.getAttribute("cghtSubController");
//		cghtController.onClick$queryButton();
		System.out.println();
		editCghtSubWin.detach();//关闭窗口
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
}
