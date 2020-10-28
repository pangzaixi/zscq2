package com.pjgl.lysqSub.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.controller.LysqController;
import com.pjgl.lysqSub.bean.LysqSub;
import com.pjgl.lysqSub.service.serviceImpl.LysqSubService;

@Controller("editLysqSubController")
@Scope("prototype")
public class EditLysqSubController  extends GenericForwardComposer {

	Window editLysqSubWin;
	Textbox companyName;
	Datebox lyrq;
	Textbox pjName;
	Textbox spec;
	Textbox unit;
	Doublebox num;
	Textbox sscx;
	Textbox wz;
	Textbox remarks;
	
	
	
	LysqSub lysqSub;
	Lysq lysq;
	
	LysqSubService lysqSubService = (LysqSubService)SpringUtil.getBean("lysqSubService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("editLysqSubController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		lysq = (Lysq)Executions.getCurrent().getArg().get("lysq");
		lysqSub = (LysqSub)Executions.getCurrent().getArg().get("lysqSub");
		
		
		companyName.setValue(lysq.getCompanyName());
		lyrq.setValue(lysq.getLyrq());
		pjName.setValue(lysqSub.getPjName());
		spec.setValue(lysqSub.getSpec());
		unit.setValue(lysqSub.getUnit());
		num.setValue(lysqSub.getNum());
		sscx.setValue(lysqSub.getSscx());
		wz.setValue(lysqSub.getWz());
		remarks.setValue(lysqSub.getRemarks());
		
//		LysqSubController lysqSubController = (LysqSubController)session.getAttribute("lysqSubController");
//		lysqSubController.onClick$querySubButton();
	}
		/**
	 * 编辑申领子项
	 * @throws java.text.ParseException 
	 */
	public void onClick$editBtn() throws ParseException{
		Map<Object,Object> map = new HashMap<>();
		
		map.put("id", lysqSub.getId());
		map.put("num", num.getValue());
		map.put("sscx", sscx.getValue());
		map.put("wz", wz.getValue());
		map.put("remarks", remarks.getValue().trim());
		int result = lysqSubService.editLysqSub(map);
	
		
		LysqSubController lysqSubController = (LysqSubController)session.getAttribute("lysqSubController");
		lysqSubController.onClick$querySubButton();
		editLysqSubWin.detach();//关闭窗口
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
}
