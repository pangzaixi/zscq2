package com.pjgl.car.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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

@Controller("editCarController")   
@Scope("prototype")
public class EditCarController extends GenericForwardComposer{
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	private Car car; //接收要编辑的记录
	Window editCarWin;
	
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
	
    ListModel driverModel =new ListModelList<EmployeeManage>();
	//获取service对象
  	CarService carService = (CarService)SpringUtil.getBean("carService");
  	EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
		
  	@Override
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);//该行必须存在，否则异常
			User user_login = (User)session.getAttribute("user");
			Map map = new HashMap<Object,Object>();
			
			
			car = (Car)Executions.getCurrent().getArg().get("car");
			plate_num.setValue(car.getPlate_num()); // '车牌号'
		    driver.setValue(car.getDriverName()); // '当前驾驶人，从人员表中获取，下拉选'
		    mobile.setValue(car.getMobile()); // '联系方式'
	       car_brand.setValue(car.getCar_brand()); // '拖拉机品牌'
	       car_model.setValue(car.getCar_model()); // '拖拉机型号'
	       engine_num.setValue(car.getEngine_num()); // '发动机号'
	       engine_powner.setValue(car.getEngine_powner()); // '发动机功率'
	       car_rack_num.setValue(car.getCar_rack_num()); // '车架号'
	       car_buy_date.setValue(car.getCar_buy_date()); // '购置时间'
	       remarks.setValue(car.getRemarks()); // '备注'
//	       company_id.setValue(car.getCompany_id()); // '所属公司ID'
	       map.put("begin", 0);
			map.put("end", 100);
			map.put("companyid", user_login.getCompanyid());
			List<EmployeeManage> list = userService.selectEmployeeManage(map);
			driverModel= new ListModelList(list);
			driver.setModel(driverModel);
			
		}	
	/**
	 * 更新记录
	 */
	public void onClick$editBtn() throws ParseException{	
		Map<Object,Object> map = new HashMap<>();
		getInfoFromPage(map);
		map.put("id", car.getId());
			
		try{
			int result = carService.editCar(map);
			editCarWin.detach();//关闭窗口
			CarController carController = (CarController)session.getAttribute("carController");
			carController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			
		} catch(Exception e){
			Messagebox.show("该车辆已存在","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	private void getInfoFromPage(Map<Object,Object> map){
		if(plate_num.getValue() != null && !"".equals(plate_num.getValue())){
			map.put("plate_num", plate_num.getValue().trim());
		}
		if(driver.getValue() != null && !"".equals(driver.getValue())){
			if (driver.getSelectedItem().getValue() != null) {
				map.put("driver", driver.getSelectedItem().getValue());
			}else {
				map.put("driver", null);
			}
		}else{
			map.put("driver", driver.getValue());
		}

		/*if(driver.getValue() != null && !"".equals(driver.getValue())){
			map.put("driver", driver.getValue().trim());
		}*/
		if(mobile.getValue() != null && !"".equals(mobile.getValue())){
			map.put("mobile",mobile.getValue().trim() );
		}          
		
		if(car_brand.getValue() != null && !"".equals(car_brand.getValue())){
			map.put("car_brand",car_brand.getValue().trim() );
		}
		if(car_model.getValue() != null && !"".equals(car_model.getValue())){
			map.put("car_model",car_model.getValue().trim() );
		}
		if(engine_num.getValue() != null && !"".equals(engine_num.getValue())){
			map.put("engine_num",engine_num.getValue().trim() );
		}
		if(engine_powner.getValue() != null && !"".equals(engine_powner.getValue())){
			map.put("engine_powner",engine_powner.getValue().trim() );
		}
		if(car_rack_num.getValue() != null && !"".equals(car_rack_num.getValue())){
			map.put("car_rack_num",car_rack_num.getValue().trim() );
		}
		if(car_buy_date.getValue() != null && !"".equals(car_buy_date.getValue())){
			map.put("car_buy_date",car_buy_date.getValue() );
		}
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue().trim() );
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


