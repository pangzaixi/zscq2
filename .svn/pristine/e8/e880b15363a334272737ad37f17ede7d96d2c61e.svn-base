package com.pjgl.employeeManage.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.zscq.bean.User;





@Controller("employeeManageController")   
@Scope("prototype") 
public class EmployeeManageController extends GenericForwardComposer {
	Boolean pagingOnClick = false;
	Window employeeManagewin;
	
	//页面查询条件
		Textbox login_name;//登录名
		Textbox user_name ;//用户名
		Combobox department;//部门
		Button statusSwitchButton;//切换按钮
		ListModel departmentModel  = new ListModelList<Zzjg>();
		//获取service对象
		EmployeeManageService employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
        ZzjgService zzjgService = (ZzjgService) SpringUtil.getBean("zzjgService");
	
        //固定对象
	Listbox listbox;//页面数据
	Paging paging;//分页
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		session.setAttribute("employeeManageController", this.getController());
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
		 statusSwitchButton.setLabel("启用");
	}
	/**
	 * 
	* @Title: onClick$queryButton
	* @Description: 查询实现
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:34:13
	 */
	public void onClick$queryButton() throws ParseException{
		Map map = new HashMap<Object,Object>();
		param(map);
		
		List<EmployeeManage>  list = employeeManageService.selectEmployeeManage(map);
		ListModelList listModelList = new ListModelList<EmployeeManage>();
		listModelList = null ;
		if (list != null && list.size() >0) {
			listModelList = new ListModelList<>(list);
		}
		listbox.setModel(listModelList);
		
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		paging.setTotalSize(employeeManageService.selectEmployeeManage_count(map));
	}
	/**
	 * 
	* @Title: onClick$paging
	* @Description: 分页实现
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:33:59
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
	 * 
	* @Title: onClick$clearButton
	* @Description: 清空按钮
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:33:44
	 */
	
	public void onClick$clearButton(){
	 	login_name.setValue("");
	 	user_name.setValue("");
	 	department.setValue(null);
	 }
	/**
	 * 
	* @Title: onClick$addButton
	* @Description: 打开新增对话框
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:33:24
	 */
	public void onClick$addButton() {
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/employeeManage/addEmployee.zul", null, null);
	     window.doModal();
	 }
	/**
	 * 
	* @Title: onClick$editButton
	* @Description: 打开员工编辑对话框
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:31:29
	 */
	public void onClick$editButton() throws ParseException {
		 if(listbox.getSelectedItem() == null){
				Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}else{
				EmployeeManage employeeManage = listbox.getSelectedItem().getValue();
				Map<String, Object> arg1 = new HashMap<String, Object>();
				arg1.put("employeeManage", employeeManage);
			     Window window = (Window)Executions.createComponents(
			             "/jsp/pjgl/employeeManage/editEmployee.zul", null, arg1);
			     window.doModal();
	     }
		 onClick$queryButton();
	 }
	/**
	 * 
	* @Title: onSelect$listbox
	* @Description: 当选中某条记录时，根据账号的状态显示按钮的label值
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午5:30:20
	 */
	public void onSelect$listbox(){
		EmployeeManage em = (EmployeeManage)listbox.getSelectedItem().getValue();
		if("0".equals(em.getStatus())){
			statusSwitchButton.setLabel("启用");
		}else{
			statusSwitchButton.setLabel("禁用");
		}
	}
	/**
	 * 
	* @Title: onClick$statusSwitchButton
	* @Description: 启用/禁用切换员工状态
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午2:19:35
	 */
	public void onClick$statusSwitchButton() throws ParseException{
		Map map = new HashMap<Object,Object>();
	 	if(listbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return ;
	 	}else{
	 		getStatus(map);
	 		//询问一下是否修改状态，如果点击是的话就修改成功，否的话就不用变
	 		Messagebox.show("确定要修改该员工的状态吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
	 		    public void onEvent(Event evt) throws  ParseException {
	 		        if (evt.getName().equals("onOK")) {
			 		       	int result = employeeManageService.statusSwitchEmployeeManage(map);
			 			 	onClick$queryButton();
			 			 	 Messagebox.show("修改状态成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		        }else {
						
					}
	 		        }
	 		    });
		 
	 	}
		
	 	 
	 }
	/**
	* @Title: getStatus
	* @Description: 更改状态
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月24日下午4:03:12
	 */
	  private void getStatus(Map<Object,Object> map){
			String status = "1";
		 	EmployeeManage employeeManage = listbox.getSelectedItem().getValue();
		 	map.put("id", employeeManage.getId());
		    if ("0".equals(employeeManage.getStatus())) {
		 	    	 status="1";
		 	    	 map.put("status", status);
				}if ("1".equals(employeeManage.getStatus())) {
					 status="0";
		 	    	 map.put("status", status);
				}
	  }
	private void param(Map<Object,Object> map) throws ParseException{
		 if(login_name.getValue() != null && !"".equals(login_name.getValue())){
				map.put("login_name", login_name.getValue().trim());
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
			User user = (User) session.getAttribute("user");
			if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
				map.put("companyid", user.getCompanyid());
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
	 }
}
