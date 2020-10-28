package com.pjgl.suppliers.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.suppliers.bean.Suppliers;
import com.pjgl.suppliers.service.serviceImpl.SuppliersService;
import com.yewu.zscq.bean.User;

@Controller("editSuppliersController")   
@Scope("prototype") 
public class EditSuppliersController  extends GenericForwardComposer{
	
	//窗体对象
		Window editSuppliersWin;
		
		//页面输入组件
		Textbox name;//名称
		Textbox address;//地址
		Textbox companyName;//公司名称
		Textbox contact;//联系人
		Textbox mobile;//联系方式
		Textbox remarks;//备注
		
	
	private Suppliers suppliers;//接收要编辑的记录
	
		//获取service对象
		SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
		CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		suppliers = (Suppliers) Executions.getCurrent().getArg().get("suppliers");
		
		name.setValue(suppliers.getName());
		address.setValue(suppliers.getAddress());
		contact.setValue(suppliers.getContact());
		mobile.setValue(suppliers.getMobile());
		remarks.setValue(suppliers.getRemarks());
		User user = (User)session.getAttribute("user");
		String userCompanyId = user.getCompanyid() ;
		if (userCompanyId != null && !"".equals(userCompanyId)) {
			Company company = companyService.getById(Integer.valueOf(userCompanyId));
			if (company != null ) {
				String getCompanyName = company.getCompanyName();
				if (getCompanyName != null && !"".equals(getCompanyName)) {
					companyName.setValue(getCompanyName);
				}
			}
		}
	}
	/**
	 * 
	* @Title: onClick$editBtn
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月18日下午2:04:17
	 */
	public void onClick$editBtn() throws ParseException{
		
		Map map = new HashMap<Object,Object>();
		getInfoFromPage(map);
		
		map.put("id", suppliers.getId());
		try {
			suppliersService.updateSuppliers(map);
			editSuppliersWin.detach();
			
			SuppliersController suppliersController =(SuppliersController) session.getAttribute("suppliersController");
			suppliersController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			Messagebox.show("供货商名称重复","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	}
	
	private void getInfoFromPage(Map<Object,Object> map){
		if(name.getValue() != null && !"".equals(name.getValue())){
			map.put("name", name.getValue().trim());
		}
		User user = (User)session.getAttribute("user");
		if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
			map.put("companyid", user.getCompanyid());
		}
		if(address.getValue() != null && !"".equals(address.getValue())){
			map.put("address", address.getValue().trim());
		}
		if(contact.getValue() != null && !"".equals(contact.getValue())){
			map.put("contact",contact.getValue().trim() );
		}          
		
		if(mobile.getValue() != null && !"".equals(mobile.getValue())){
			map.put("mobile",mobile.getValue().trim() );
		}
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue().trim() );
		}
	}
}
