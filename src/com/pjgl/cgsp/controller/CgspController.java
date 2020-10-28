package com.pjgl.cgsp.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.sub.controller.CghtSubController;
import com.pjgl.cgsp.bean.Cgsp;
import com.pjgl.cgsp.service.serviceimpl.CgspService;
import com.pjgl.stock.bean.Stock;
import com.yewu.zscq.bean.User;

@Controller("cgspController")   
@Scope("prototype")
public class CgspController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Textbox wjmc;//合同编号
	Button delButton;
	Button editButton;
	Datebox sprq1;
	Datebox sprq2;
	Textbox htbh;
	
	String cghtid;
	Date date1;
	Date date2;
	
	//××××××××××××数据源
	
	public Listbox cgspbox;//页面遍历方式加载数据
	Paging paging;
	Grid countcgspbox;
	private Footer footercategory;
	Paging pagingCount;
//	private Footer footerDetails;
	
	//获取service对象
//	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	CgspService cgspService = (CgspService)SpringUtil.getBean("cgspService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("cgspController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		if (footercategory != null) {
			footercategory.setLabel("收票金额：0 元");
		}
		if (wjmc != null) {
//			footerDetails.setLabel("入库金额：0元");
			cghtid = (String)Executions.getCurrent().getArg().get("cghtid");
			date1 = (Date)Executions.getCurrent().getArg().get("sprq1");
			date2 = (Date)Executions.getCurrent().getArg().get("sprq2");
			onClick$queryButton();
		}
	}
	
    public void onClick$queryButton() throws ParseException{
    	
    	Map map = new HashMap<Object,Object>();
    	User user = (User)session.getAttribute("user");
    	
    	map.put("companyid", user.getCompanyid());
    	if(wjmc.getValue() !=null && !"".equals(wjmc.getValue().trim())){
    		map.put("wjmc", wjmc.getValue().trim());
    	}
//    	if(htbh.getValue() !=null && !"".equals(htbh.getValue().trim())){
//    		map.put("htbh", htbh.getValue().trim());
//    	}
//    	if(sprq1.getValue() !=null){
//    		map.put("sprq1", sprq1.getValue());
//    	}
//    	if(sprq2.getValue() !=null){
//    		map.put("sprq2", sprq2.getValue());
//    	}
//    	map.put("htbh", htbh2);
    	map.put("cghtid", cghtid);
    	map.put("sprq1", date1);
    	map.put("sprq2", date2);
    	if(pagingOnClick == true){
    		pagingOnClick = false;
    		map.put("begin", paging.getPageSize()*paging.getActivePage());
    		map.put("end", paging.getPageSize());
    	}else{
    		map.put("begin", 0);
    		map.put("end", paging.getPageSize());
    		paging.setActivePage(0);
    	}
		
		List<Cgsp> list = cgspService.selectCgsp(map);
		for (Cgsp cgsp : list) {
			String[] names = null;
			String[] paths = null;
			if (cgsp.getWjmc() != null && !cgsp.getWjmc().equals("")) {
				names = cgsp.getWjmc().split(";");
			}
			if (cgsp.getPath() !=null && !cgsp.getPath().equals("")) {
				paths = cgsp.getPath().split(";");
			}
			cgsp.setNames(names);
			cgsp.setPaths(paths);
//			cgsp.setPathList(pathList);
		}
		
		ListModel listModelList =new ListModelList<Cgsp>();
    	if(list!= null && list.size()>0){
    		Cgsp tmp = new Cgsp();
    		for(int i=0;i<list.size();i++){
    			tmp = list.get(i);
    			if(tmp.getState() == Cgsp.STATE_INVALID){
    				tmp.setStyle("color:red");
    			}
    		}
    		listModelList = new ListModelList(list);
    		
    	}

    	
    	cgspbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(cgspService.selectCgsp_count(map));
    	
    	CountCgspController countCgspController = (CountCgspController)session.getAttribute("countCgspController");
    	if (countCgspController != null) {
    		countCgspController.onClick$queryCgspButton();
		}
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
		wjmc.setValue("");;//配件名称
//		htbh.setValue("");
//		sprq1.setValue(null);
//		sprq2.setValue(null);
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cgsp/selectCght.zul", null, null);
	     window.doModal();
	 }
	 
	 /**
	  * 编辑记录
	  * @throws ParseException 
	  */
	 public void onClick$editButton() throws ParseException{
	 	
	 	if(cgspbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cgsp cgsp = (Cgsp)cgspbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cgsp", cgsp);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cgsp/editCgsp.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
//	 	    onClick$queryButton();//刷新父窗口表格
	 	}
	 }
	 
	 /**
	  * 作废
	  * @throws ParseException 
	  */
	 public void onClick$delButton() throws ParseException{
	 	
	 	if(cgspbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cgsp cgsp = (Cgsp)cgspbox.getSelectedItem().getValue();
	 		Messagebox.show("确定作废票据吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
	 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
	 		        if (evt.getName().equals("onOK")) {
//	 		        	Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 			 		Map<Object, Object> map = new HashMap<Object, Object>();
	 			 		map.put("id", cgsp.getId());
	 			 		map.put("state", Cgsp.STATE_INVALID);
	 			 		cgspService.changeState(map);
	 			 		onClick$queryButton();//刷新父窗口表格
//	 			 		onClick$queryCgspButton();
	 		        } else {
//	 		            alert("Save Canceled !");
	 		        }
	 		    }

	 		});
	 	    
	 	}
	 }
	 
	 public void onSelect$cgspbox(){
			Cgsp cgsp = (Cgsp)cgspbox.getSelectedItem().getValue();
			int state = cgsp.getState();
			if (state == Cgsp.STATE_VALID) {
				delButton.setVisible(true);
				editButton.setVisible(true);
			}else {
				delButton.setVisible(false);
				editButton.setVisible(false);
			}
		}
	 
	 public void onClick$queryCgspButton() throws ParseException{
		 
		 Map map = new HashMap<Object,Object>();
	    	User user = (User)session.getAttribute("user");
	    	
	    	map.put("companyid", user.getCompanyid());
	    	if(htbh.getValue() !=null && !"".equals(htbh.getValue().trim())){
	    		map.put("htbh", htbh.getValue().trim());
	    	}
	    	if(sprq1.getValue() !=null){
	    		map.put("sprq1", sprq1.getValue());
	    	}
	    	if(sprq2.getValue() !=null){
	    		map.put("sprq2", sprq2.getValue());
	    	}
	    	map.put("begin", pagingCount.getPageSize()*pagingCount.getActivePage());
			map.put("end", pagingCount.getPageSize());
			
			List<Cgsp> list = cgspService.countCgsp(map);
			
			
			ListModel listModelList =new ListModelList<Cgsp>();
	    	if(list!= null && list.size()>0){
	    		listModelList = new ListModelList(list);
	    		Double amounts = cgspService.selectAmounts(map);
	    		footercategory.setLabel("收票金额：" + amounts +" 元");
	    	}else{
	    		footercategory.setLabel("收票金额：0 元");
	    	}

	    	
	    	countcgspbox.setModel(listModelList);
	    	
	    	//初始化分页标签
	    	Integer a = Integer.MAX_VALUE;
	    	Integer a1 = Integer.MIN_VALUE;
	    	pagingCount.setTotalSize(cgspService.countCgsp_count(map));
	 }
	 
	 /**
	  * 分页标签
     * @throws ParseException 
	  */
	 public void onClick$pagingCount() throws ParseException{
		 System.out.println(pagingCount.getActivePage());
		 System.out.println(pagingCount.getPageSize());
		 int activepage = pagingCount.getActivePage();
		 
		 onClick$queryCgspButton();
		 if(activepage<=pagingCount.getPageCount()-1){
			 pagingCount.setActivePage(activepage);	 
		 }else{
			 pagingCount.setActivePage(0);
		 }
		 
	 }
	 
	 /**
	  * 清空查询条件
	  */
	 public void onClick$clearCgspButton(){
		htbh.setValue("");
		sprq1.setValue(null);
		sprq2.setValue(null);
	 }
}
