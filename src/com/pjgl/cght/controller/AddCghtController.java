package com.pjgl.cght.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
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
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.usermanage.service.UserService;

@Controller("addCghtController")   
@Scope("prototype") 
public class AddCghtController extends GenericForwardComposer{

//	Textbox htbh; //'合同编号',
	Datebox qdrq; //'合同签订日期',
	Doublebox zje; //'总金额',
	Combobox jbr; //'经办人',
	Combobox gys; //'供应商ID',
	Textbox remarks; //'备注',
	Textbox supplierMobile;
	Textbox userMobile;
	Combobox zcg;
	
	Window addCghtWin;
	ListModel supplierModel;
	
	//获取service对象
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
	SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		User user = (User)session.getAttribute("user");
		Map map = new HashMap<Object,Object>();
		map.put("begin", 0);
		map.put("end", 1000);
		map.put("companyid", user.getCompanyid());
		List<EmployeeManage> list = userService.selectEmployeeManage(map);
		ListModel userModel = new ListModelList(list);
		jbr.setModel(userModel);
		
		Map map1 = new HashMap<Object,Object>();
		map1.put("begin", 0);
		map1.put("end", 1000);
		map1.put("companyid", user.getCompanyid());
		List<Suppliers> list1 = suppliersService.selectSuppliers(map1);
		supplierModel = new ListModelList(list1);
		gys.setModel(supplierModel);
	}
	
	/**
	 * 增加合同记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		Map<Object,Object> map = new HashMap<>();
		User user = (User)session.getAttribute("user");
		int listSize = getList(user.getCompanyid());
		listSize += 1;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String htbh = "";
		if (zcg.getSelectedItem() != null && zcg.getSelectedItem().getValue().equals("是")) {
			htbh = "自采购" + f.format(qdrq.getValue()) + String.format("%02d", listSize);
		}else {
			htbh = f.format(qdrq.getValue()) + String.format("%02d", listSize);
		}
		
		map.put("companyid", user.getCompanyid());
		map.put("htbh", htbh);
		map.put("qdrq", qdrq.getValue());
		if (jbr.getSelectedItem() != null) {
			map.put("jbrid", jbr.getSelectedItem().getValue());
		}
		if (gys.getSelectedItem() != null) {
			map.put("gysid", gys.getSelectedItem().getValue());
		}
		map.put("remarks", remarks.getValue().trim());
		map.put("state", Cght.STATE_CREATE);
		
		int result = cghtService.insertCght(map);
		System.out.println();
		addCghtWin.detach();//关闭窗口
		CghtController cghtController = (CghtController)session.getAttribute("cghtController");
		cghtController.onClick$queryButton();
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	private int getList(String companyid) {
		Map<Object,Object> map = new HashMap<>();
		map.put("qdrq", qdrq.getValue());
		map.put("companyid", companyid);
		int size = cghtService.selectCount(map);
		return size;
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
