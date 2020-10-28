package com.pjgl.employeeManage.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.zscq.bean.User;

@Controller("addEmployeeManageController")   
@Scope("prototype") 
public class AddEmployeeManageController extends GenericForwardComposer  {
	
	Window addEmployeeWin;
	
	   //页面输入控件
		Textbox login_name;//登录名
		Textbox login_pwd;//密码
		Textbox user_name ;//用户名
		Combobox department;//部门
		Textbox mobile;//联系方式
		Textbox email;//电子邮件
		Textbox fax;//传真
		
		ListModel departmentModel  = new ListModelList<Zzjg>();
		//获取service对象
		EmployeeManageService employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
        ZzjgService zzjgService = (ZzjgService) SpringUtil.getBean("zzjgService");
	
        @Override
    	public void doAfterCompose(Component comp) throws Exception {
    		super.doAfterCompose(comp);
    		session.setAttribute("addEmployeeManageController", this.getController());
    		
    		//给部门下拉框获取值
    		Map map = new HashMap<Object,Object>();
    		User user = (User) session.getAttribute("user");
    		if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
    			map.put("companyid", user.getCompanyid());
    		}
    		map.put("begin", 0);
    		map.put("end", 100);
    		List<Zzjg> list = zzjgService.selectZzjg(map);
    		departmentModel = new ListModelList<>(list);
    		department.setModel(departmentModel);
    		
    	}
        public void onClick$addBtn() throws ParseException{
    		Map map = new HashMap<Object,Object>();
    		getInfoFromPage(map);
    		try {
    			//添加员工默认状态是1
    			String  status = "1";
    			 map.put("status", status);
				int result = employeeManageService.insertEmployeeManage(map);
				addEmployeeWin.detach();
				
				EmployeeManageController employeeManageController = (EmployeeManageController) session.getAttribute("employeeManageController");
			    employeeManageController.onClick$queryButton();
			    
				Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
    		} catch (Exception e) {
			   e.printStackTrace();
				Messagebox.show("登录账号重复","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}
    		}
    	private void getInfoFromPage(Map<Object,Object> map){
    		 if(login_name.getValue() != null && !"".equals(login_name.getValue())){
 				map.put("login_name", login_name.getValue().trim());
 			}
    		 if(login_pwd.getValue() != null && !"".equals(login_pwd.getValue())){
  				map.put("login_pwd", login_pwd.getValue().trim());
  			}
 			if(user_name.getValue() != null && !"".equals(user_name.getValue())){
 				map.put("user_name", user_name.getValue().trim());
 			}
 			if (department.getValue() != null && !"".equals(department.getValue())) {
 				if (department.getSelectedItem().getValue() != null) {
 					map.put("departmentid", department.getSelectedItem().getValue());
 				}else {
 					map.put("deparmentid", null);
 				}
 			}else {
 				map.put("deparmentid", null);
 			}
 			if(mobile.getValue() != null && !"".equals(mobile.getValue())){
 				map.put("mobile", mobile.getValue().trim());
 			}
 			if(email.getValue() != null && !"".equals(email.getValue())){
 				map.put("email", email.getValue().trim());
 			}
 			if(fax.getValue() != null && !"".equals(fax.getValue())){
 				map.put("fax", fax.getValue().trim());
 			}
 			User user = (User) session.getAttribute("user");
 			if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
 				map.put("companyid", user.getCompanyid());
 			}
 			
 			
    	}
        
}
