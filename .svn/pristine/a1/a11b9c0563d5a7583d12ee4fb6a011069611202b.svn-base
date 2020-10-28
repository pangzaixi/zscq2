package com.pjgl.company.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.qxgl.service.QxglService;

/**
 * 新增公司
 * @author thinker
 *
 */
@Controller("addCompanyController")  
@Scope("prototype") 
public class AddCompanyController  extends GenericForwardComposer{

	Textbox companyName;
	Textbox address;
	Textbox contact;
	Textbox mobile;
	Textbox email;
	Textbox remarks;
	Textbox fax_1;
	
	Window addCompanyWin;
	//获取service对象
		CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
		EmployeeManageService employeeManageService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
		QxglService qxglService = (QxglService)SpringUtil.getBean("qxglService");
		
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);//该行必须存在，否则异常
			CompanyController companyController = (CompanyController)session.getAttribute("companyController");
			 Listbox commanybox1 = companyController.commanybox;
//			 Company company =  (Company)companyController.commanybox.getSelectedItem().get;
			 
//			 companyName.setValue("2222");
			
		}	
	/**
	 * 增加公司记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
		
		map.put("companyName", companyName.getValue().trim());
		map.put("address", address.getValue().trim());
		map.put("contact", contact.getValue().trim());
		map.put("mobile", mobile.getValue().trim());
		map.put("email", email.getValue().trim());
		map.put("fax_1", fax_1.getValue().trim());
		map.put("remarks", remarks.getValue().trim());
//		getInfoFromPage(map);//获取输入参数	
		try{
			int result = companyService.insertCompany(map);
			System.out.println();
			
			Company company = companyService.getByName(companyName.getValue().trim());
			
			map.clear();
			map.put("login_name", companyName.getValue().trim());
			map.put("login_pwd", "123456");
			map.put("companyid",company.getId() );
			map.put("user_name", companyName.getValue().trim());
			map.put("departmentid", null);
			map.put("mobile", null);
			map.put("fax", null);
			map.put("email", null);
			map.put("status", 1);
			
			employeeManageService.insertEmployeeManage(map);//创建公司管理员，账号为公司名称
			map.clear();
			map.put("login_name", companyName.getValue().trim());
			qxglService.insertAllQx(map);//为管理员授予所有权限
			
			
			
			addCompanyWin.detach();//关闭窗口
			CompanyController companyController = (CompanyController)session.getAttribute("companyController");
			companyController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			Messagebox.show("该公司已经存在","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}	
	}

}
