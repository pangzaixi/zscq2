package com.system.role.controller;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.role.bean.Role;
import com.system.role.service.serviceImpl.RoleManageService;
import com.yewu.zscq.bean.User;
/**
 * 
* @ClassName: RoleManageController
* @Description: 角色管理Controller
* @author 徐玲
* @date 2019年7月22日下午3:51:32
*
 */
@Controller("roleManageController")   
@Scope("prototype") 
public class RoleManageController extends GenericForwardComposer{
	Boolean pagingOnClick = false;
	//窗体对象
		Window roleManageWin;
		//页面查询条件
		Textbox login_name;//
		Textbox user_name;//
		Combobox roleList;//角色下拉框
		
		ListModel roleModel =  new ListModelList<Role>();
		
		//获取service对象
		RoleManageService roleManageService = (RoleManageService) SpringUtil.getBean("roleManageService");
		EmployeeManageService employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
		//固定对象
		Listbox listbox;//页面数据
		Paging paging;//分页
		
		
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);
			session.setAttribute("roleManageController", this.getController());
			Map map1 = new HashMap<Object,Object>();
			List<Role> roles = roleManageService.selectRoleDistinct(map1);
			if (roles !=null && roles.size() >0) {
				roleModel = new ListModelList<>(roles);
				roleList.setModel(roleModel);
			}
				}
	  
		public void onClick$queryButton() throws ParseException{
			Map map = new HashMap<Object,Object>();
			param(map);
		
			List<Role> list = roleManageService.selectRole(map);
			ListModelList listModelList = new ListModelList<Role>();
			listModelList = null ;
			if (list != null && list.size() >0) {
				listModelList = new ListModelList<>(list);
			}
			
			listbox.setModel(listModelList);
			
			Integer a = Integer.MAX_VALUE;
			Integer a1 = Integer.MIN_VALUE;
			paging.setTotalSize(roleManageService.selectRole_count(map));
		}
		
	
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
	
		 public void onClick$clearButton(){
			 login_name.setValue("");
			 user_name.setValue("");
		 	 roleList.setValue("");
		 }
	
		
		 public void onClick$editButton() throws ParseException {
		    Window window = (Window)Executions.createComponents(
		             "/jsp/system/roleManage/editRole.zul", null, null);
		     window.doModal();
		 }
		
		 public void onClick$delButton() throws ParseException{
			 	
			 	if(listbox.getSelectedItem() == null){
			 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 		return;
			 	}else{
			 		Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
			 		    public void onEvent(Event evt) throws  ParseException {
			 		        if (evt.getName().equals("onOK")) {
         		        //确认删除
			 		       	Role roleGet = listbox.getSelectedItem().getValue();
			 		       	String login_name = roleGet.getLogin_name();
			 		       	
					 		Map map = new HashMap<Object,Object>();
					 		map.put("id", roleGet.getId());
					 	    try {
					 	    	if (!"superadmin".equals(login_name)) {
					 	    		//不是superadmin的账户可以删除
					 	    		int result = roleManageService.deleteRole(map);
							 	    onClick$queryButton();
							 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
								}else {
									  Messagebox.show("该账户不允许删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
								}
							} catch (Exception e) {
								// TODO: handle exception
								   Messagebox.show("删除失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
							}
			 		        } else {
	                         
			 		        }
			 		    }
			 		});
			 	
			 	}
			 }
		
		 private void param(Map<Object,Object> map) throws ParseException{
			 if(login_name.getValue() != null && !"".equals(login_name.getValue())){
					map.put("login_name", login_name.getValue().trim());
				}
			 if(user_name.getValue() != null && !"".equals(user_name.getValue())){
					map.put("user_name", user_name.getValue().trim());
				}
			 if(roleList.getValue() != null && !"".equals(roleList.getValue())){
					if (roleList.getSelectedItem().getValue() != null && !"".equals(roleList.getSelectedItem().getValue())) {
						map.put("role", roleList.getSelectedItem().getValue());
					}
				}
			 //和登录账号统一公司的用户名
				User user = (User) session.getAttribute("user");
				if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
					Map map1 = new HashMap<Object,Object>();
					map1.put("companyid", user.getCompanyid());
					
					List<EmployeeManage> employeeManages = employeeManageService.getByCompanyId(map1);
					List<String > login_names = new ArrayList<String>();
					
				if (employeeManages != null && employeeManages.size() >0) {
						for (EmployeeManage employeeManage : employeeManages) {
								String login_name = employeeManage.getLogin_name();
								login_names.add(login_name);
						}
				}
					map.put("login_names", login_names);
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
