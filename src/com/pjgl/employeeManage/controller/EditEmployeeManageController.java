package com.pjgl.employeeManage.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.Null;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.zscq.bean.User;

@Controller("editEmployeeManageController")   
@Scope("prototype") 
public class EditEmployeeManageController extends GenericForwardComposer  {
	Window editEmployeeWin;
	
	   //页面输入控件
		Textbox login_name;//登录名
		Textbox login_pwd;//密码
		Textbox user_name ;//用户名
		Combobox department;//部门
		Textbox mobile;//联系方式
		Textbox email;//电子邮件
		Textbox fax;//传真
		
		private EmployeeManage employeeManage;//编辑要接收的对象
		ListModel departmentModel  = new ListModelList<Zzjg>();
		//获取service对象
		EmployeeManageService employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
		ZzjgService zzjgService = (ZzjgService) SpringUtil.getBean("zzjgService");
		CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
     @Override
 	public void doAfterCompose(Component comp) throws Exception {
 		super.doAfterCompose(comp);
 		session.setAttribute("editEmployeeManageController", this.getController());
 		
 		employeeManage = (EmployeeManage) Executions.getCurrent().getArg().get("employeeManage");
 		login_name.setValue(employeeManage.getLogin_name());
 		login_pwd.setValue(employeeManage.getLogin_pwd());
 		//如果该登录账户已经有采购合同了，该用户名不能编辑
 		Map map1 = new HashMap<Object,Object>();
 		map1.put("jbrid", employeeManage.getId());
 		List<Cght> cghts = cghtService.selectCghtByJbr(map1);
 		if (cghts != null && cghts.size() >0) {
 			user_name.setValue(employeeManage.getUser_name());
 			user_name.setReadonly(true);//只读
		}else {
			user_name.setValue(employeeManage.getUser_name());
		}
 		department.setValue(employeeManage.getDepartmentName());
 		mobile.setValue(employeeManage.getMobile());
 		email.setValue(employeeManage.getEmail());
 		fax.setValue(employeeManage.getFax());
 		
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
     public void onClick$updateBtn() throws ParseException{
 		Map map = new HashMap<Object,Object>();
 		getInfoFromPage(map);
 		map.put("id", employeeManage.getId());
 		map.put("status", employeeManage.getStatus());
 		try {
				int result = employeeManageService.updateEmployeeManage(map);
				editEmployeeWin.detach();
				
				EmployeeManageController employeeManageController = (EmployeeManageController) session.getAttribute("employeeManageController");
			    employeeManageController.onClick$queryButton();
				Messagebox.show("更新成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
 		} catch (Exception e) {
 			e.printStackTrace();
 			Messagebox.show("登录账号重复，请修改","注意", Messagebox.OK, Messagebox.EXCLAMATION);
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
				System.out.println("department.getValue()"+department.getValue());
				if (department.getSelectedItem().getValue() != null) {
					map.put("departmentid", department.getSelectedItem().getValue());
				}else {
					map.put("deparmentid", null);
				}
			}else {
				map.put("deparmentid", department.getValue());
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
