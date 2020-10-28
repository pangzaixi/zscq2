package com.system.zzjg.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.controller.JxqdController;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;


@Controller("addZzjgController")   
@Scope("prototype") 
public class AddZzjgController    extends GenericForwardComposer{
//窗口对象
	Window addZzjg;
	
//页面输入组件
	Textbox department;//部门名称
	Textbox manager;//部门负责人
	Textbox phone;//部门电话
	Textbox address;//办公地址
	Textbox remarks;//备注
	
/**
 * 初始化方法	
 */
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	//session.setAttribute("jxqdController", this.getController());
		
}
/**
 * 增加组织机构记录
 */
public void onClick$addZzjgBtn() throws ParseException{
	
	//获取service对象
	ZzjgService zzjgService = (ZzjgService)SpringUtil.getBean("zzjgService");
	
	Map<Object,Object> map = new HashMap<>();
	
	getInfoFromPage(map);//获取输入参数
	int result = zzjgService.insertZzjg(map);
	System.out.println();
	addZzjg.detach();//关闭窗口
	ZzjgController zzjgController = (ZzjgController)session.getAttribute("zzjgController");
	zzjgController.onClick$queryButton();
	Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
}

/**
 * 将页面传递来的信息存入MAP
 * @return
 */
private void getInfoFromPage(Map<Object,Object> map){
	if(department.getValue() != null && !"".equals(department.getValue().trim())){
		map.put("department", department.getValue().trim());
	}// 部门名称
	if(manager.getValue() != null && !"".equals(manager.getValue())){
		map.put("manager", manager.getValue().trim());
	}//  部门负责人
	if(phone.getValue() != null && !"".equals(phone.getValue())){
		map.put("phone", phone.getValue().trim());
	}//  部门电话
	if(address.getValue() != null && !"".equals(address.getValue())){
		map.put("address",address.getValue().trim() );
	}//  办公地址                    
	
	if(remarks.getValue() != null && !"".equals(remarks.getValue())){
		map.put("remarks",remarks.getValue() );
	}//备注
	
	
	map.put("inputdate", new Date());//录入日期
	User user = (User)session.getAttribute("user");
	map.put("creater", user.getUser_name());
	map.put("companyid", user.getCompanyid());
}
}