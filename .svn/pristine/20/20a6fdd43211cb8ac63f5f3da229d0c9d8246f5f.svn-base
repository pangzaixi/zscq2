package com.pjgl.lysq.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.service.serviceImpl.LysqService;
import com.pjgl.lysqSub.bean.LysqSub;


@Controller("lyspController")   
@Scope("prototype") 
public class LyspController extends GenericForwardComposer {	
	Window spWin;
	
	Radio ra1;
	Radio ra2;
	Textbox remarks;
	
	
	Lysq lysq;
	
	LysqService lysqService = (LysqService)SpringUtil.getBean("lysqService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		ra1.setSelected(true);
		 lysq = (Lysq)Executions.getCurrent().getArg().get("lysq");
	}
	/**
	  * 审核
	  * @throws ParseException 
	  */
	 public void onClick$saveBtn() throws ParseException{
		
		 Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("id", lysq.getId());
			if(ra1.isSelected()){
				map.put("state", Lysq.status_psss);
			}else{
				map.put("state", Lysq.status_refuse);
			}
			
			String tmp = lysq.getRemarks();
			if(tmp == null){
				tmp="";
			}
			if(remarks.getValue() == null || "".equals(remarks.getValue().trim())){
				map.put("remarks", lysq.getRemarks());	
			}else{
				map.put("remarks", tmp+"  "+remarks.getValue());
			}
			
			lysqService.editLysqState(map);
			spWin.detach();
			LysqController lysqController = (LysqController)session.getAttribute("lysqController");
			lysqController.onClick$queryButton();//刷新父窗口表格
	 }
	
	
}
