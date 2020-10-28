package com.pjgl.car.controller;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.car.bean.Car;
import com.pjgl.car.service.serviceimpl.CarService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.yewu.zscq.bean.User;

@Controller("carController")   
@Scope("prototype") 
public class CarController extends GenericForwardComposer{
	Boolean pagingOnClick = false;//是否点击的分页标签
	//窗体对象
  	Window carwin;
  //页面查询条件
  	
  	Textbox ajlx; // '车牌号'
//    Textbox company_id; // '所属公司ID'
    
//××××××××××××数据源
	
  	public Listbox listbox;//页面遍历方式加载数据
  	Paging paging;
  	ListModel driverModel =new ListModelList<EmployeeManage>();
  //获取service对象
  	CarService carService = (CarService)SpringUtil.getBean("carService");
  	EmployeeManageService userService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
  	
  	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        session.setAttribute("carController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
        
    }
    
public void onClick$queryButton() throws ParseException{
		
    	
		User user_login = (User)session.getAttribute("user");
	    Map map = new HashMap<Object,Object>();
	    param(map);
		/*map.put("begin", 0);
		map.put("end", 1000);*/
		
    	
    	if(ajlx.getValue() !=null && !"".equals(ajlx.getValue().trim())){
    		map.put("ajlx", ajlx.getValue().trim());
    	}
    	
		
		List<Car> list = carService.selectCar(map);    	
		ListModel listModelList =new ListModelList<Car>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	} 	
    	listbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(carService.selectCar_count(map));
	}
	
/**
 * 分页标签
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
	ajlx.setValue(""); // '车牌号'
 	
// 	company_id.setValue("");  // 所属公司
}

/**
 * 新增按钮,弹出编辑窗口
 */
public void onClick$addButton() {
    //create a window programmatically and use it as a modal dialog.
    Window window = (Window)Executions.createComponents(
            "/jsp/pjgl/car/addCar.zul", null, null);
    window.doModal();
}
/**
 * 编辑按钮,弹出编辑窗口
 */
public void onClick$editButton() throws ParseException{
	 if(listbox.getSelectedItem() == null){
			Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}else{
			Car car = listbox.getSelectedItem().getValue();
			Map<String, Object> arg1 = new HashMap<String, Object>();
			arg1.put("car", car);
    Window window = (Window)Executions.createComponents(
   		 "/jsp/pjgl/car/editCar.zul", null, arg1);
    window.doModal();
    }
	 onClick$queryButton();
    
}
/**
 * 
* @Title: onClick$delButton
* @Description: 删除车辆
 */
public void onClick$delButton() throws ParseException{
	 	
	 	if(listbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Car car = listbox.getSelectedItem().getValue();
	 		Map map = new HashMap<Object,Object>();
	 		map.put("id", car.getId());
	 		
	 		int result = carService.deleteCar(map);
	 	    
	 	    onClick$queryButton();
	 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 	}
	 }
/**
 *
*  查询条件的设置
 */
private void param(Map<Object,Object> map) throws ParseException{
	 	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
			map.put("ajlx", ajlx.getValue().trim());
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
