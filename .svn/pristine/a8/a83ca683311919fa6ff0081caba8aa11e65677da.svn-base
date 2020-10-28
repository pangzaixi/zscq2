package com.pjgl.car.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.car.bean.Car;
import com.pjgl.car.service.serviceimpl.CarService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.yewu.zscq.bean.User;

@Controller("addCarController")   
@Scope("prototype")
public class AddCarController extends GenericForwardComposer{

	Textbox plate_num; // '车牌号'
	Combobox driver ; // '当前驾驶人，从人员表中获取，下拉选'
    Textbox mobile ; // '联系方式'
    Textbox car_brand ; // '拖拉机品牌'
    Textbox car_model ; // '拖拉机型号'
    Textbox engine_num ; // '发动机号'
    Textbox engine_powner ; // '发动机功率'
    Textbox car_rack_num ; // '车架号'
    Datebox car_buy_date; // '购置时间'
    Textbox remarks ; // '备注'
    Textbox company_id; // '所属公司ID'
	
	Window addCarWin;
	
	ListModel driverModel =new ListModelList<EmployeeManage>();
	//获取service对象
  	CarService carService = (CarService)SpringUtil.getBean("carService");
  	EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
		
		
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);//该行必须存在，否则异常
			
			
			
		}	
	/**
	 * 增加公司记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		System.out.println();
		User user_login = (User)session.getAttribute("user");
		Map map = new HashMap<Object,Object>();
//		getInfoFromPage(map);
		
		
		map.put("mobile", mobile.getValue().trim());
		map.put("remarks", remarks.getValue().trim());
//		
		
	
		try{
			int result = carService.insertCar(map);
			System.out.println();
			addCarWin.detach();//关闭窗口
//			CarController 
			CarController carController = (CarController)session.getAttribute("carController");
			carController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			
		} catch(Exception e){
			Messagebox.show("保存失败，一般是案件类型或者案件号前缀重复，请重新编辑","注意", Messagebox.OK, Messagebox.ERROR);
		}
	}
	/**
	 * 根据驾驶员，获取电话
	 */
	public void onSelect$driver() throws ParseException{
		String driverMobile = driver.getSelectedItem().getContext();
		mobile.setValue(driverMobile);
	}

}

