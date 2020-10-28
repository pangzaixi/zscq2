package com.system.zzjg.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.controller.JxqdController;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;


@Controller("editZzjgController")   
@Scope("prototype") 
public class EditZzjgController  extends GenericForwardComposer{
//窗口对象
	Window editZzjg;
	
//页面输入组件
	Textbox department;//部门名称
	Textbox manager;//部门负责人
	Textbox phone;//部门电话
	Textbox address;//办公地址
	Textbox remarks;//备注
//正在编辑的对象	
	Zzjg zzjg;	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		System.out.println();
		zzjg = (Zzjg)Executions.getCurrent().getArg().get("zzjg");
		
		department.setValue(zzjg.getDepartment());
		manager.setValue(zzjg.getManager());
		phone.setValue(zzjg.getPhone());
		address.setValue(zzjg.getAddress());
	
		remarks.setValue(zzjg.getRemarks());
		
	}
	
	/**
	 * 更新记录
	 * @throws ParseException 
	 */
	public void onClick$editZzjgBtn() throws ParseException{
		//获取service对象
		ZzjgService zzjgService = (ZzjgService)SpringUtil.getBean("zzjgService");
		Map<Object,Object> map = new HashMap<>();
		getInfoFromPage(map);
		map.put("id", zzjg.getId());
		zzjgService.editZzjg(map);
		editZzjg.detach();
		System.out.println();
		ZzjgController zzjgController = (ZzjgController)session.getAttribute("zzjgController");
		zzjgController.onClick$queryButton();
		
	}
	/**
	 * 将页面传递来的信息存入MAP
	 * @return
	 */
	private void getInfoFromPage(Map<Object,Object> map){
		if(department.getValue() != null && !"".equals(department.getValue())){
			map.put("department", department.getValue().trim());
		}// 部门名称
		if(manager.getValue() != null && !"".equals(manager.getValue())){
			map.put("manager", manager.getValue().trim());
		}//  部门管理者
		if(phone.getValue() != null && !"".equals(phone.getValue())){
			map.put("phone", phone.getValue().trim());
		}//  部门电话
		if(address.getValue() != null && !"".equals(address.getValue())){
			map.put("address", address.getValue().trim());
		}//  办公地址
		
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue().trim() );
		}//备注
		User user = (User)session.getAttribute("user");
		map.put("companyid", user.getCompanyid());
		
	}	
}