package com.pjgl.lysqSub.controller;

import java.text.ParseException;
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
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.controller.LysqController;
import com.pjgl.lysq.service.serviceImpl.LysqService;
import com.pjgl.lysqSub.bean.LysqSub;
import com.pjgl.lysqSub.service.serviceImpl.LysqSubService;


@Controller("lysqSubController")
@Scope("prototype")
public class LysqSubController extends GenericForwardComposer {
	Textbox pjName;
	Textbox companyName;
	Datebox lyrq;

	Button editSubButton;
	Button delSubButton;
	//××××××××××××数据源
	
	public Listbox lysqsubbox;//页面遍历方式加载数据
	Paging subpaging;
	
	Lysq lysq;
	
	//获取Service
	LysqSubService lysqSubService = (LysqSubService)SpringUtil.getBean("lysqSubService");
	LysqService lysqService = (LysqService)SpringUtil.getBean("lysqService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("lysqSubController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		
		lysq = (Lysq)Executions.getCurrent().getArg().get("lysq");		
		lyrq.setValue(lysq.getLyrq());
		companyName.setValue(lysq.getCompanyName());
		
		LysqSubController lysqSubController = (LysqSubController)session.getAttribute("lysqSubController");
		lysqSubController.onClick$querySubButton();
		
		////////////////
		if(lysq.getState() ==1 || lysq.getState()==2){
			editSubButton.setVisible(false);
			delSubButton.setVisible(false);
		}
		
	}
	
	public void onClick$querySubButton() throws ParseException{
    	
    	Map map = new HashMap<Object,Object>();
    	
    	
    	
    	
    	map.put("lysqid", lysq.getId());
    	if(pjName.getValue() !=null && !"".equals(pjName.getValue().trim())){
    		map.put("pjName", pjName.getValue().trim());
    	}
    	map.put("begin", subpaging.getPageSize()*subpaging.getActivePage());
		map.put("end", subpaging.getPageSize());
		
		List<LysqSub> list = lysqSubService.selectLysqSub(map);
		
		ListModel listModelList =new ListModelList<LysqSub>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}
    	
    	lysqsubbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	subpaging.setTotalSize(lysqSubService.selectLysqSub_count(map));
    	LysqController lysqController = (LysqController)session.getAttribute("lysqController");
    	lysqController.onClick$queryButton();
	}
	
	/**
	  * 分页标签
    * @throws ParseException 
	  */
	 public void onClick$subpaging() throws ParseException{
		 System.out.println(subpaging.getActivePage());
		 System.out.println(subpaging.getPageSize());
		 int activepage = subpaging.getActivePage();
		 
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
	 	
	 	if(lysqsubbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		LysqSub lysqSub = (LysqSub)lysqsubbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("lysqSub", lysqSub);
	 		arg1.put("lysq",lysq);
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/lysqSub/editLysqSub.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
	 	    onClick$querySubButton();//刷新父窗口表格
	 	}
	 }
	 
	 public void onClick$delSubButton() throws ParseException{
		 	
		 	if(lysqsubbox.getSelectedItem() == null){
		 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		return;
		 	}else{
		 		LysqSub lysqSub = (LysqSub)lysqsubbox.getSelectedItem().getValue();
				Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
		 		        if (evt.getName().equals("onOK")) {
		 			 		lysqSubService.deleteLysqSub(lysqSub.getId());
		 			 		Map<Object,Object> lysqMap = new HashMap<>();
		 					lysqMap.put("id", lysq.getId());
		 					
//		 					lysqService.editLysq(lysqMap);
		 					
		 			 		onClick$querySubButton();//刷新父窗口表格
		 		        } else {
//			 		            alert("Save Canceled !");
		 		        }
		 		    }

		 		});
			}
		 	    
		 }
}
