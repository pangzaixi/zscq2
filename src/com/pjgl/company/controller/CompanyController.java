package com.pjgl.company.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.yewu.zscq.bean.WenJian;
@Controller("companyController")   
@Scope("prototype") 
public class CompanyController extends GenericForwardComposer{
	Boolean pagingOnClick = false;//是否点击的分页标签
	//窗体对象
	Window companywin;
	private Company company;//接收要编辑的记录
	//页面查询条件
//	Textbox id;//公司id
	Textbox companyName;//公司名称
	Textbox address;//公司地址
	Textbox contact;//联系人
//	Textbox mobile;//联系方式

	
	//××××××××××××数据源
	
	public Listbox commanybox;//页面遍历方式加载数据
	Paging paging;
	
	//获取service对象
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	 
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("companyController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
	}	
	
	public void onClick$queryButton(){
		
	    	
    	Map map = new HashMap<Object,Object>();
    	
//    	if(id.getValue() !=null && !"".equals(id.getValue().trim())){
//    		map.put("id", id.getValue().trim());
//    	}
    	if(companyName.getValue() !=null && !"".equals(companyName.getValue().trim())){
    		map.put("companyName", companyName.getValue().trim());
    	}
    	if(address.getValue() != null && !"".equals(address.getValue())){
			map.put("address", address.getValue().trim());
		}
    	if(contact.getValue() != null && !"".equals(contact.getValue())){
			map.put("contact", contact.getValue().trim());
		}
    	if(pagingOnClick == true){
    		pagingOnClick = false;
    		map.put("begin", paging.getPageSize()*paging.getActivePage());
    		map.put("end", paging.getPageSize());
    	}else{
    		paging.setActivePage(0);
    		map.put("begin", 0);
    		map.put("end", paging.getPageSize());
    	}
		
		List<Company> list = companyService.selectCompany(map);
    	
		ListModel listModelList =new ListModelList<WenJian>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}

    	
    	
    	commanybox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(companyService.selectCompany_count(map));
	}
	
	 /**
	  * 分页标签
	  */
	 public void onClick$paging(){
		 System.out.println(paging.getActivePage());
		 System.out.println(paging.getPageSize());
		 int activepage = paging.getActivePage();
		 pagingOnClick = true;
		 onClick$queryButton();
		 if(activepage<=paging.getPageCount()-1){
			 paging.setActivePage(activepage);	 
		 }else{
			 paging.setActivePage(0);
		 }
		 
	 }
	 
	 /**
	  * 清空查询条件
	  */
	 public void onClick$clearButton(){
//		 id.setValue("");;//公司名称
		companyName.setValue("");;//公司名称
		address.setValue("");//公司地址
		contact.setValue("");//联系人
//		mobile.setValue("");//联系方式
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/company/addCompany.zul", null, null);
	     window.doModal();
	 }
	 /**
	  * 编辑按钮,弹出编辑窗口
	  */
	 public void onClick$editButton() throws ParseException{
		
		 if(commanybox.getSelectedItem() == null){
				Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			/*}else if(company.getId() == 1){
				Messagebox.show("总公司不能修改","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;	*/			
			}else{
				Company company = commanybox.getSelectedItem().getValue();
				/**
				 * 整个总公司都不能修改，
				 */
				/*
				 * 
				 * if(company.getId() == 1){
				Messagebox.show("总公司不能修改","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
				}*/
				
				Map<String, Object> arg1 = new HashMap<String, Object>();
				arg1.put("company", company);
				Window window = (Window)Executions.createComponents(
	    		 "/jsp/pjgl/company/editCompany.zul", null, arg1);
				window.doModal();
			}
		 onClick$queryButton();
	     
	 }
	 /**
	  * 
	 * @Title: onClick$delButton
	 * @Description: 删除供货商
	  */
	 public void onClick$delButton() throws ParseException{
		 Company company = commanybox.getSelectedItem().getValue();
		//获取service对象
//		 EmployeeManageService employeeManageService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");		 
//		 Integer count = employeeManageService.selectCompany_employee(String.valueOf(company.getId()));
		 Integer count = companyService.selectCompany_employee(String.valueOf(company.getId()));
//		 System.out.println("count ============="+ count);
		 
		 	if(commanybox.getSelectedItem() == null){
		 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		return;
		 	}else{
		 		Map map = new HashMap<Object,Object>();
		 		if(company.getId() == 1){
		 			Messagebox.show("总公司不能删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		}else{
		 			if(count >0){
						Messagebox.show("存在关联业务数据，不可删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
						return;
					}
		 			map.put("id", company.getId());			 		
			 		int result = companyService.deleteCompany(map);			 	    
			 	    onClick$queryButton();
			 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		}
		 		
		 	}
		 }
}
