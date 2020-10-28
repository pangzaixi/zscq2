package com.system.qxgl.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.qxgl.bean.Qxgl;
import com.system.qxgl.service.QxglService;
import com.system.role.bean.Role;
import com.system.role.controller.RoleManageController;
import com.system.role.service.serviceImpl.RoleManageService;
import com.yewu.zscq.bean.User;

@Controller("addQxglController")   
@Scope("prototype") 
public class AddQxglController extends GenericForwardComposer{
	//窗体
	Window addQxglWin;
  
	Combobox employeeList;
	Combobox titleList;
	Textbox fl;
	Textbox path;
	Textbox fl_text;
	Textbox orderno;
	Checkbox allRa;
   //下拉框数据
	ListModel employeeModel = new ListModelList<EmployeeManage>();
	ListModel titleModel =  new ListModelList<Qxgl>();
	
	//获取service对象
	QxglService qxglService = (QxglService)SpringUtil.getBean("qxglService");
	EmployeeManageService  employeeManageService = (EmployeeManageService) SpringUtil.getBean("employeeManageService");
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//当前登录用户所在公司的所有登录名
		Map map = new HashMap<Object,Object>();
		User user = (User) session.getAttribute("user");
		map.put("companyid", user.getCompanyid());
		List<EmployeeManage> list = employeeManageService.getByCompanyId(map);
		if (list != null && list.size() >0) {
			employeeModel = new ListModelList<>(list);
			employeeList.setModel(employeeModel);
		}
		Map map1 = new HashMap<Object,Object>();
		List<Qxgl> list2 = qxglService.selectQxglDistinct(map1);
		if (list2 != null && list2.size() >0) {
			titleModel = new ListModelList<>(list2);
			titleList.setModel(titleModel);
		}
	}
	/**
	 * 
	* @Title: onClick$addBtn
	* @Description: 分配权限
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年8月8日上午10:34:34
	 */
public void onClick$addBtn() throws Exception{
	Map map = new HashMap<Object,Object>();
	if(allRa.isChecked()){//如果授予全部权限，则不走后面的单权限逻辑
		map.put("login_name", employeeList.getSelectedItem().getContext());
		if(null == map.get("login_name")){
			Messagebox.show("权限分配异常，请联系管理员","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		if("superadmin".equals(employeeList.getSelectedItem().getContext())){
			Messagebox.show("超级管理员不能整体授权","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		qxglService.deleteQxgl(map);
		setAllPersion();
		return ;
	}
	
	map.clear();
	getInfoFromPage(map);
	try {
		qxglService.insertQxgl(map);
		addQxglWin.detach();
		QxglController qxglController = (QxglController ) session.getAttribute("qxglController");
		qxglController.onClick$queryButton();
		Messagebox.show("分配成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	} catch (Exception e) {
		Messagebox.show("该账号已有该权限","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
}
	/**
	 * 
	* @Title: getInfoFromPage
	* @Description: 获取页面的数据
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年8月8日上午10:21:07
	 */
	private void getInfoFromPage(Map<Object,Object> map){
		if(employeeList.getValue() != null && !"".equals(employeeList.getValue())){
			if (employeeList.getSelectedItem().getContext() != null && !"".equals(employeeList.getSelectedItem().getContext())) {
			map.put("login_name", employeeList.getSelectedItem().getContext());
			}
		}
		if(titleList.getValue() != null && !"".equals(titleList.getValue())){
			if (titleList.getSelectedItem().getValue() != null && !"".equals(titleList.getSelectedItem().getValue())) {
				map.put("title", titleList.getSelectedItem().getValue());
			}
			if (fl.getValue() != null && !"".equals(fl.getValue())) {
				map.put("fl", fl.getValue());
			}
			if (path.getValue() != null && !"".equals(path.getValue())) {
				map.put("path", path.getValue());
			}
			if (orderno.getValue() != null && !"".equals(orderno.getValue())) {
				map.put("orderno", orderno.getValue());
			}
			
		}
	}
	
	
	public void setAllPersion() throws Exception{
		Map map1 = new HashMap<Object,Object>();
		List<Qxgl> list2 = qxglService.selectQxglDistinct(map1);
		for(Qxgl tmp:list2){
			Map map = new HashMap<Object,Object>();
			map.put("login_name", employeeList.getSelectedItem().getContext());
		
			map.put("title", tmp.getTitle());
			map.put("fl", tmp.getFl());
			map.put("path", tmp.getPath());
			map.put("orderno", tmp.getOrderno());
			
			
			qxglService.insertQxgl(map);
			
			
		}
		addQxglWin.detach();
		QxglController qxglController = (QxglController ) session.getAttribute("qxglController");
		qxglController.onClick$queryButton();
		Messagebox.show("分配成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		
	}
	/**
	 * 
	* @Title: onSelect$titleList
	* @Description:选中模块名称，显示相应的分类
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年8月8日上午10:08:55
	 */
	public void onSelect$titleList() throws ParseException{
		//根据选择的模块名称，显示分类，获取path,orderno
		String titleGet = titleList.getSelectedItem().getValue();
		if (titleGet != null && !"".equals(titleGet)) {
			Map map = new HashMap<Object,Object>();
			map.put("title", titleGet);
			List<Qxgl> list = qxglService.selectQxglDistinct(map);
			if (list != null && list.size()>0) {
				for (Qxgl qxgl : list) {
					
					path.setValue(qxgl.getPath());
					orderno.setValue(qxgl.getOrderno());
	
					String  flGet= qxgl.getFl();
					String flText = null;
					if (flGet != null && !"".equals(flGet)) {
						fl.setValue(flGet);
						if ("0".equals(flGet)) {
							flText = "权限";
						}if ("1".equals(flGet)) {
							flText = "基础";
						}if ("2".equals(flGet)) {
							flText = "采购";
						}if ("3".equals(flGet)) {
							flText = "库存";
						}
				      fl_text.setValue(flText);
				}
					
				}
				
			}
		}
	
	}
}
