package com.pjgl.cght.sub.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.controller.CghtController;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cght.sub.bean.CghtSub;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.suppliers.bean.Suppliers;
import com.yewu.zscq.bean.User;

@Controller("cghtSubController")   
@Scope("prototype") 
public class CghtSubController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Textbox pjName;
	Textbox htbh;
	Datebox qdrq;
	Doublebox zje;
	Button editSubButton;
	Button delSubButton;
	
	//××××××××××××数据源
	
	public Listbox cghtsubbox;//页面遍历方式加载数据
	Paging subpaging;
	
	Cght cght;
	
	//获取Service
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("cghtSubController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		cght = (Cght)Executions.getCurrent().getArg().get("cght");
		if (cght.getState() == Cght.STATE_END) {
			editSubButton.setVisible(false);
			delSubButton.setVisible(false);
		}
		htbh.setValue(cght.getHtbh());
		qdrq.setValue(cght.getQdrq());
		zje.setValue(cght.getZje());
		onClick$querySubButton();
	}
	
	public void onClick$querySubButton() throws ParseException{
    	
    	Map map = new HashMap<Object,Object>();
    	
    	map.put("cghtid", cght.getId());
    	if(pjName.getValue() !=null && !"".equals(pjName.getValue().trim())){
    		map.put("pjName", pjName.getValue().trim());
    	}
    	if(pagingOnClick == true){
    		pagingOnClick = false;
    		map.put("begin", subpaging.getPageSize()*subpaging.getActivePage());
    		map.put("end", subpaging.getPageSize());
    	}else{
    		map.put("begin", 0);
    		map.put("end", subpaging.getPageSize());
    		subpaging.setActivePage(0);
    	}
		
		List<CghtSub> list = cghtSubService.selectCghtSub(map);
		
		ListModel listModelList =new ListModelList<CghtSub>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}
    	
    	cghtsubbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	subpaging.setTotalSize(cghtSubService.selectCghtSub_count(map));
    	CghtController cghtController = (CghtController)session.getAttribute("cghtController");
		cghtController.onClick$queryButton();
	}
	
	/**
	  * 分页标签
    * @throws ParseException 
	  */
	 public void onClick$subpaging() throws ParseException{
		 int activepage = subpaging.getActivePage();
		 pagingOnClick = true;
		 onClick$querySubButton();
		 if(activepage<=subpaging.getPageCount()-1){
			 subpaging.setActivePage(activepage);	 
		 }else{
			 subpaging.setActivePage(0);
		 }
		 
	 }
	 
	 /**
	  * 清空查询条件
	  */
	 public void onClick$clearSubButton(){
		pjName.setValue("");;//配件名称
//		unit.setValue("");
	 }
	 
	 /**
	  * 编辑配件记录
	  * @throws ParseException 
	  */
	 public void onClick$editSubButton() throws ParseException{
	 	
	 	if(cghtsubbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		CghtSub cghtSub = (CghtSub)cghtsubbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cghtSub", cghtSub);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cght/sub/editCghtSub.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
	 	    onClick$querySubButton();//刷新父窗口表格
	 	}
	 }
	 
	 public void onClick$delSubButton() throws ParseException{
		 	
		 	if(cghtsubbox.getSelectedItem() == null){
		 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		return;
		 	}else{
		 		CghtSub cghtSub = (CghtSub)cghtsubbox.getSelectedItem().getValue();
				Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
		 		        if (evt.getName().equals("onOK")) {
		 			 		cghtSubService.deleteCghtSub(cghtSub.getId());
		 			 		Map<Object,Object> cghtMap = new HashMap<>();
		 					cghtMap.put("id", cght.getId());
		 					cghtMap.put("zje", cght.getZje() - cghtSub.getAmounts());
		 					cghtService.changeZje(cghtMap);
		 					zje.setValue(cght.getZje() - cghtSub.getAmounts());
		 					changeZje(cght.getZje() - cghtSub.getAmounts());
		 					System.out.println(zje.getValue());
		 			 		onClick$querySubButton();//刷新父窗口表格
		 		        } else {
//			 		            alert("Save Canceled !");
		 		        }
		 		    }

		 		});
			}
		 	    
		 }
	 
	 public void changeZje(Double money){
		 zje.setValue(money);
	 }
}
