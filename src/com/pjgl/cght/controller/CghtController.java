package com.pjgl.cght.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.suppliers.bean.Suppliers;
import com.pjgl.suppliers.service.serviceImpl.SuppliersService;
import com.yewu.zscq.bean.User;

@Controller("cghtController")   
@Scope("prototype") 
public class CghtController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Textbox htbh;//合同编号
//	Datebox qdrq;
	Combobox state;
	Button delButton;
	Button checkButton;
	Intbox role;
	Datebox qdrq1;
	Datebox qdrq2;
	Button editButton;
	
	//××××××××××××数据源
	
	public Listbox cghtbox;//页面遍历方式加载数据
	Paging paging;
	Window selectCghtWin;
	
	//获取service对象
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("cghtController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		if (delButton == null) {
			onClick$queryButton();
		}
	}
	
    public void onClick$queryButton() throws ParseException{
    	
    	Map map = new HashMap<Object,Object>();
    	User user = (User)session.getAttribute("user");
    	
    	map.put("companyid", user.getCompanyid());
    	if(htbh.getValue() !=null && !"".equals(htbh.getValue().trim())){
    		map.put("htbh", htbh.getValue().trim());
    	}
    	if (state.getSelectedItem() != null) {
			map.put("state", state.getSelectedItem().getValue());
		}
    	if (qdrq1.getValue() != null) {
			map.put("qdrq1", qdrq1.getValue());
		}
    	if (qdrq2.getValue() != null) {
			map.put("qdrq2", qdrq2.getValue());
		}
    	if(pagingOnClick == true){
    		pagingOnClick = false;
    		map.put("begin", paging.getPageSize()*paging.getActivePage());
    		map.put("end", paging.getPageSize());
    	}else{
    		map.put("begin", 0);
    		map.put("end", paging.getPageSize());
    		paging.setActivePage(0);
    	}
		
		List<Cght> list = cghtService.selectCght(map);
		for (Cght cght : list) {
			if (cght.getGysid() != null && !cght.getGysid().equals("")) {
				Suppliers suppliers = suppliersService.getById(Integer.parseInt(cght.getGysid()));
				cght.setSupplierName(suppliers.getName());
				cght.setSupplierMobile(suppliers.getMobile());
			}
		}
		
		ListModel listModelList =new ListModelList<Cght>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}

    	
    	cghtbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(cghtService.selectCght_count(map));
	}
    
    /**
	  * 分页标签
     * @throws ParseException 
	  */
	 public void onClick$paging() throws ParseException{
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
		htbh.setValue("");;//配件名称
//		unit.setValue("");
		state.setSelectedItem(null);
		qdrq1.setValue(null);
		qdrq2.setValue(null);
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cght/addCght.zul", null, null);
	     window.doModal();
	 }
	 
	 /**
	  * 编辑合同记录
	  * @throws ParseException 
	  */
	 public void onClick$editButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cght", cght);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cght/editCght.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
	 	    onClick$queryButton();//刷新父窗口表格
	 	}
	 }
	 
	 /**
	  * 删除合同记录
	  * @throws ParseException 
	  */
	 public void onClick$delButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		if (cght.getState() == 0) {
	 			Messagebox.show("该记录已审批，不可删除！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}else {
				Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
		 		        if (evt.getName().equals("onOK")) {
//		 		        	Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
		 			 		Map<Object, Object> map = new HashMap<Object, Object>();
		 			 		map.put("id", cght.getId());
		 			 		map.put("state", Cght.STATE_DELETE);
		 			 		cghtService.changeZje(map);;
		 			 		cghtSubService.deleteSubByCght(cght.getId());
		 			 		onClick$queryButton();//刷新父窗口表格
		 		        } else {
//		 		            alert("Save Canceled !");
		 		        }
		 		    }

		 		});
			}
	 	    
	 	}
	 }
	 
	 /**
	  * 新增配件
	  * @throws ParseException 
	  */
	 public void onClick$addSubButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cght", cght);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cght/sub/addCghtSub.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
	 	    onClick$queryButton();//刷新父窗口表格
	 	}
	 }
	 
	 /**
	  * 审核
	  * @throws ParseException 
	  */
	 public void onClick$checkButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		Messagebox.show("确定通过审批吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
	 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
	 		        if (evt.getName().equals("onOK")) {
//	 		        	Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 			 		Map<Object, Object> map = new HashMap<Object, Object>();
	 			 		map.put("id", cght.getId());
	 			 		map.put("state", Cght.STATE_END);
	 			 		cghtService.changeZje(map);
	 			 		onClick$queryButton();//刷新父窗口表格
	 		        } else {
//	 		            alert("Save Canceled !");
	 		        }
	 		    }

	 		});
	 	    
	 	}
	 }
	 
	 public void onSelect$cghtbox(){
		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
		int state = cght.getState();
		if (delButton != null) {
			if (state == Cght.STATE_CREATE) {
				delButton.setVisible(true);
				editButton.setVisible(true);
				if (role.getValue() == 1) {
					checkButton.setVisible(true);
				}
			}else{
				delButton.setVisible(false);
				editButton.setVisible(false);
				checkButton.setVisible(false);
			}
		}
	}
	 
	 /**
	  * 为采购收票选择采购合同
	  * @throws ParseException 
	  */
	 public void onClick$selectButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		if (cght.getState() == Cght.STATE_END) {
	 			Map<String, Object> arg1 = new HashMap<String, Object>();
		 		arg1.put("cght", cght);
		 		
		 		Window window = (Window)Executions.createComponents(
		 	            "/jsp/pjgl/cgsp/addCgsp.zul", null, arg1);
		 	    
		 	    window.doModal();//模式弹窗
//		 	    window.doPopup();//非模式弹窗
		 	    selectCghtWin.detach();
		 	    
		 	    onClick$queryButton();//刷新父窗口表格
			}else{
				Messagebox.show("该合同未审批生效！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}
	 	}
	 }
	 
	 /**
	  * 为采购付款选择采购合同
	  * @throws ParseException 
	  */
	 public void onClick$selectfkButton() throws ParseException{
	 	
	 	if(cghtbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cght", cght);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cgfk/addCgfk.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    selectCghtWin.detach();
	 	    
	 	    onClick$queryButton();//刷新父窗口表格
	 	}
	 }
	 
}
