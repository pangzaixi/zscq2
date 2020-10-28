package com.pjgl.cght.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.lang.Exceptions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.pjgl.pj.controller.PjController;
import com.pjgl.suppliers.bean.Suppliers;
import com.pjgl.suppliers.service.serviceImpl.SuppliersService;
import com.yewu.zscq.usermanage.bean.User;
import com.yewu.zscq.usermanage.service.UserService;

@Controller("editCghtController")   
@Scope("prototype") 
public class EditCghtController extends GenericForwardComposer{

	Textbox htbh; //'合同编号',
	Datebox qdrq; //'合同签订日期',
	Doublebox zje; //'总金额',
	Combobox jbr; //'经办人',
	Combobox gys; //'供应商ID',
	Textbox remarks; //'备注',
	Textbox supplierMobile;
	Textbox userMobile;
	
	Cght cght;
	Window editCghtWin;
	
	//获取service对象
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
	SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		
		cght = (Cght)Executions.getCurrent().getArg().get("cght");
		htbh.setValue(cght.getHtbh());
		qdrq.setValue(cght.getQdrq());
		if (cght.getJbrid() !=null && !cght.getJbrid().equals("")) {
			EmployeeManage employeeManage = userService.getById(Integer.parseInt(cght.getJbrid()));
			if (employeeManage != null) {
				jbr.setValue(employeeManage.getUser_name());
				userMobile.setValue(employeeManage.getMobile());
			}
		}
		if (cght.getGysid() != null && !cght.getGysid().equals("")) {
			Suppliers suppliers = suppliersService.getById(Integer.parseInt(cght.getGysid()));
			if (suppliers != null) {
				gys.setValue(suppliers.getName());
				supplierMobile.setValue(suppliers.getMobile());
			}
		}
		remarks.setValue(cght.getRemarks());
		
		Map map = new HashMap<Object,Object>();
		map.put("begin", 0);
		map.put("end", 1000);
		map.put("companyid", cght.getCompanyid());
		List<EmployeeManage> list = userService.selectEmployeeManage(map);
		ListModel userModel = new ListModelList(list);
		jbr.setModel(userModel);
		
		Map map1 = new HashMap<Object,Object>();
		map1.put("begin", 0);
		map1.put("end", 1000);
		map1.put("companyid", cght.getCompanyid());
		List<Suppliers> list1 = suppliersService.selectSuppliers(map1);
		ListModel supplierModel = new ListModelList(list1);
		gys.setModel(supplierModel);
	}
	
	/**
	 * 修改记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
		
		if (jbr.getSelectedItem() != null) {
			map.put("jbrid", jbr.getSelectedItem().getValue());
		}
		if (gys.getSelectedItem() != null) {
			map.put("gysid", gys.getSelectedItem().getValue());
		}
		map.put("remarks", remarks.getValue().trim());
		map.put("id", cght.getId());
		map.put("qdrq", qdrq.getValue());
		
//		getInfoFromPage(map);//获取输入参数
		int result = cghtService.editCght(map);
		System.out.println();
		editCghtWin.detach();//关闭窗口
		CghtController cghtController = (CghtController)session.getAttribute("cghtController");
		cghtController.onClick$queryButton();
		Messagebox.show("修改成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	/**
	  * 获取供应商电话
	  * @throws ParseException 
	  */
	 public void onSelect$gys() throws ParseException{
	 	String supplierMobile2 = gys.getSelectedItem().getContext();
	 	supplierMobile.setValue(supplierMobile2);
	 }
	 
	 /**
	  * 获取联系人电话
	  * @throws ParseException 
	  */
	 public void onSelect$jbr() throws ParseException{
	 	String jbrMobile = jbr.getSelectedItem().getContext();
	 	userMobile.setValue(jbrMobile);
	 }
}
