package com.pjgl.lysq.controller;

import java.text.ParseException;

import java.util.Date;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.service.serviceImpl.LysqService;
import com.yewu.zscq.bean.User;


@Controller("EditLysqController")  
@Scope("prototype") 
public class EditLysqController extends GenericForwardComposer {

	Combobox companyName;//公司名称
	Combobox jbr;//经办人名字
	Doublebox amounts; //'总金额',
	Datebox lyrq;    //领用日期
	Textbox remarks; //备注
	
	Lysq lysq;
	Window editLysqWin;
	//获取service对象
		LysqService lysqService = (LysqService)SpringUtil.getBean("lysqService");
		EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
		//CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);//该行必须存在，否则异常
		    lysq =(Lysq) Executions.getCurrent().getArg().get("lysq");
		    lyrq.setValue(lysq.getLyrq());
		    remarks.setValue(lysq.getRemarks());
		    if (lysq.getJbrid() !=null && !lysq.getJbrid().equals("")) {
				EmployeeManage employeeManage = userService.getById(Integer.parseInt(lysq.getJbrid()));
				if (employeeManage != null) {
					jbr.setValue(employeeManage.getUser_name());
				}
			}
		    
			 User user = (User)session.getAttribute("user");
				Map map = new HashMap<Object,Object>();
				map.put("begin", 0);
				map.put("end", 1000);
				map.put("companyid", user.getCompanyid());
				List<EmployeeManage> list = userService.selectEmployeeManage(map);
				ListModel userModel = new ListModelList(list);
				jbr.setModel(userModel);
			/*	
				Map map1 = new HashMap<Object,Object>();
				map1.put("begin", 0);
				map1.put("end", 1000);
				map1.put("companyid", user.getCompanyid());
				List<Company> list1 = companyService.selectCompany(map);
				ListModel userModel1 = new ListModelList(list);
				companyName.setModel(userModel);*/
		}
	/**
	 * 修改公司记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
//		User user = (User)session.getAttribute("user");
//		
//		
//		map.put("companyid", user.getCompanyid());
	
		map.put("id", lysq.getId());
		if (jbr.getSelectedItem() != null) {
			map.put("jbrid", jbr.getSelectedItem().getValue());
		}
		map.put("lyrq", lyrq.getValue());
		
		map.put("remarks", remarks.getValue().trim());
		
	    
//		getInfoFromPage(map);//获取输入参数
		int result = lysqService.editLysq(map);
		System.out.println();
		editLysqWin.detach();//关闭窗口
		LysqController lysqController = (LysqController)session.getAttribute("lysqController");
		lysqController.onClick$queryButton();
		Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	private int getList(String companyid) {
		
			Map<Object,Object> map = new HashMap<>();
			map.put("lyrq", new Date());
			map.put("companyid", companyid);
			int size = lysqService.selectLysq_count(map);
			return size;
		
	}
	  
	
}
