package com.system.role.controller;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
/**
 * 
* @ClassName: EditRoleManageController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 徐玲
* @date 2019年7月23日下午3:49:49
*
 */
import org.zkoss.zul.Window;

import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;

import com.system.role.bean.Role;
import com.system.role.service.serviceImpl.RoleManageService;
import com.yewu.zscq.bean.User;
@Controller("editRoleManageController")   
@Scope("prototype") 
public class EditRoleManageController extends GenericForwardComposer {
	Window editUserRoleWin;
	
	Combobox employeeList;
	Combobox roleList;
	Textbox remarks;
	
	private Role role;
	ListModel employeeModel = new ListModelList<EmployeeManage>();
	ListModel roleModel =  new ListModelList<Role>();
	
	RoleManageService roleManageService = (RoleManageService) SpringUtil.getBean("roleManageService");
	EmployeeManageService  employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
    
	@Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	//初始化登录用户名下拉框，显示的是当前登录用户公司的所有用户
    	Map map = new HashMap<Object,Object>();
		User user = (User) session.getAttribute("user");
		map.put("companyid", user.getCompanyid());
		List<EmployeeManage> list = employeeManageService.getByCompanyId(map);
		if (list != null && list.size() >0) {
			employeeModel = new ListModelList<>(list);
			employeeList.setModel(employeeModel);
		}
		
		Map map1 = new HashMap<Object,Object>();
		List<Role> roles = roleManageService.selectRoleDistinct(map1);
		if (roles !=null && roles.size() >0) {
			roleModel = new ListModelList<>(roles);
			roleList.setModel(roleModel);
		}
    }
public void onClick$editBtn() throws ParseException{
		
		Map map = new HashMap<Object,Object>();
		getInfoFromPage(map);
		try {
			roleManageService.insertRole(map);
			editUserRoleWin.detach();
			RoleManageController roleManageController = (RoleManageController) session.getAttribute("roleManageController");
			roleManageController.onClick$queryButton();
			Messagebox.show("分配成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("该账号已有该角色","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	private void getInfoFromPage(Map<Object,Object> map){
		if(employeeList.getValue() != null && !"".equals(employeeList.getValue())){
			if (employeeList.getSelectedItem().getContext() != null && !"".equals(employeeList.getSelectedItem().getContext())) {
				map.put("login_name", employeeList.getSelectedItem().getContext());
			}
		}
		if(roleList.getValue() != null && !"".equals(roleList.getValue())){
			if (roleList.getSelectedItem().getValue() != null && !"".equals(roleList.getSelectedItem().getValue())) {
				map.put("role", roleList.getSelectedItem().getValue());
			}
			if (remarks.getValue() != null && !"".equals(remarks.getValue())) {
				map.put("remarks", remarks.getValue());
			}
			
		}
	}
	/**
	 * 
	* @Title: onSelect$roleList
	* @Description: 选中角色获取相对应的remarks
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月25日下午3:35:33
	 */
	public void onSelect$roleList() throws ParseException{
		String remarksOfRole = roleList.getSelectedItem().getContext();
		remarks.setValue(remarksOfRole);
	}

	
}
