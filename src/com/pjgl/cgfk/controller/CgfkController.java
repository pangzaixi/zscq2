package com.pjgl.cgfk.controller;

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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cgsp.bean.Cgsp;
import com.pjgl.cgfk.bean.Cgfk;
import com.pjgl.cgfk.service.serviceimpl.CgfkService;
import com.yewu.zscq.bean.User;

@Controller("cgfkController")   
@Scope("prototype")
public class CgfkController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Textbox wjmc;//合同编号
	Button delButton;
	Button editButton;
	Datebox fkrq1;
	Datebox fkrq2;
	Textbox htbh;
	
	String cghtid;
	Date date1;
	Date date2;
	
	//××××××××××××数据源
	
	public Listbox cgfkbox;//页面遍历方式加载数据
	Paging paging;
	
	//获取service对象
//	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	CgfkService cgfkService = (CgfkService)SpringUtil.getBean("cgfkService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("cgfkController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		cghtid = (String)Executions.getCurrent().getArg().get("cghtid");
		date1 = (Date)Executions.getCurrent().getArg().get("sprq1");
		date2 = (Date)Executions.getCurrent().getArg().get("sprq2");
		onClick$queryButton();
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
//    	if(fkrq1.getValue() !=null){
//    		map.put("fkrq1", fkrq1.getValue());
//    	}
//    	if(fkrq2.getValue() !=null){
//    		map.put("fkrq2", fkrq2.getValue());
//    	}
    	map.put("cghtid", cghtid);
    	map.put("fkrq1", date1);
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
		
		List<Cgfk> list = cgfkService.selectCgfk(map);
		for (Cgfk cgfk : list) {
			String[] names = null;
			String[] paths = null;
			if (cgfk.getWjmc() != null && !cgfk.getWjmc().equals("")) {
				names = cgfk.getWjmc().split(";");
			}
			if (cgfk.getPath() !=null && !cgfk.getPath().equals("")) {
				paths = cgfk.getPath().split(";");
			}
			cgfk.setNames(names);
			cgfk.setPaths(paths);
		}
		
		ListModel listModelList =new ListModelList<Cgfk>();
    	if(list!= null && list.size()>0){
    		Cgfk tmp = new Cgfk();
    		for(int i=0;i<list.size();i++){
    			tmp = list.get(i);
    			if(tmp.getState() == Cgfk.STATE_INVALID){
    				tmp.setStyle("color:red");
    			}
    		}
    		listModelList = new ListModelList(list);
    	}

    	
    	cgfkbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(cgfkService.selectCgfk_count(map));
    	
    	CountCgfkController countCgfkController = (CountCgfkController)session.getAttribute("countCgfkController");
    	countCgfkController.onClick$queryCgfkButton();
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
//		fkrq1.setValue(null);
//		fkrq2.setValue(null);
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cgfk/selectCght.zul", null, null);
	     window.doModal();
	 }
	 
	 /**
	  * 编辑记录
	  * @throws ParseException 
	  */
	 public void onClick$editButton() throws ParseException{
	 	
	 	if(cgfkbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cgfk cgfk = (Cgfk)cgfkbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("cgfk", cgfk);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/cgfk/editCgfk.zul", null, arg1);
	 	    
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
	 	
	 	if(cgfkbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Cgfk cgfk = (Cgfk)cgfkbox.getSelectedItem().getValue();
	 		Messagebox.show("确定作废票据吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
	 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
	 		        if (evt.getName().equals("onOK")) {
//	 		        	Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
	 			 		Map<Object, Object> map = new HashMap<Object, Object>();
	 			 		map.put("id", cgfk.getId());
	 			 		map.put("state", Cgfk.STATE_INVALID);
	 			 		cgfkService.changeState(map);
	 			 		onClick$queryButton();//刷新父窗口表格
	 		        } else {
//	 		            alert("Save Canceled !");
	 		        }
	 		    }

	 		});
	 	    
	 	}
	 }
	 
	 public void onSelect$cgfkbox(){
			Cgfk cgfk = (Cgfk)cgfkbox.getSelectedItem().getValue();
			int state = cgfk.getState();
			if (state == Cgfk.STATE_VALID) {
				delButton.setVisible(true);
				editButton.setVisible(true);
			}else {
				delButton.setVisible(false);
				editButton.setVisible(false);
			}
		}
}
