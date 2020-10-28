package com.pjgl.pj.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cght.sub.bean.CghtSub;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yewu.zscq.bean.User;

@Controller("pjController")   
@Scope("prototype") 
public class PjController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Textbox name;//配件名称
	Textbox unit;//单位
	Textbox pjName;
	Button selectPjStock;
	
	//××××××××××××数据源
	
	public Listbox pjbox;//页面遍历方式加载数据
	Paging paging;
	Window selectPjWin;
	Cght cght;
	
	//获取service对象
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("pjController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		cght = (Cght)Executions.getCurrent().getArg().get("cght");
		if (cght != null) {
			onClick$queryPj();
		}
		if (selectPjStock != null) {
			onClick$queryButton();
		}
	}
	
	public void onClick$queryButton(){
    	
    	Map map = new HashMap<Object,Object>();
    	User user = (User)session.getAttribute("user");
    	
    	map.put("companyid", user.getCompanyid());
    	if(name.getValue() !=null && !"".equals(name.getValue().trim())){
    		map.put("name", name.getValue().trim());
    	}
    	if(unit.getValue() !=null && !"".equals(unit.getValue().trim())){
    		map.put("unit", unit.getValue().trim());
    	}
    	if(pagingOnClick == true){
    		pagingOnClick = false;
    		map.put("begin", paging.getPageSize()*paging.getActivePage());
    		map.put("end", paging.getPageSize());
    	}else{
    		map.put("begin", 0);
    		map.put("end", paging.getPageSize());
    		paging.setActivePage(0);
    	}
		
		List<Pj> list = pjService.selectPj(map);
		ListModel listModelList =new ListModelList<Pj>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}

    	
    	pjbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(pjService.selectPj_count(map));
	}
	
	 /**
	  * 分页标签
	  */
	 public void onClick$paging(){
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
		name.setValue("");;//配件名称
		unit.setValue("");
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/pj/addPj.zul", null, null);
	     window.doModal();
	 }
	 
	 /**
	  * 编辑配件记录
	  * @throws ParseException 
	  */
	 public void onClick$editButton() throws ParseException{
	 	
	 	if(pjbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Pj pj = (Pj)pjbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("pj", pj);
	 		
	 		Window window = (Window)Executions.createComponents(
	 	            "/jsp/pjgl/pj/editPj.zul", null, arg1);
	 	    
	 	    window.doModal();//模式弹窗
//	 	    window.doPopup();//非模式弹窗
	 	    
	 	    onClick$queryButton();//刷新父窗口表格
	 	}
	 }
	 
	 /**
	  * 删除配件记录
	  * @throws ParseException 
	  */
	 public void onClick$delButton(){
	 	
	 	if(pjbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Pj pj = (Pj)pjbox.getSelectedItem().getValue();
	 		
	 		Map<Object,Object> map = new HashMap<Object,Object>();
	 		map.put("pjid", pj.getId());
	 	    int useCount = pjService.selectPjStatus(map);
	 	    if(useCount != 0){
	 	    	Messagebox.show("该配件存在对应采购合同或入库记录，禁止删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 	    }else{
	 	    	Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
		 		        if (evt.getName().equals("onOK")) {
//		 		        	Cght cght = (Cght)cghtbox.getSelectedItem().getValue();
		 		        	int result = pjService.deletePj(pj.getId());
		 			 		onClick$queryButton();//刷新父窗口表格
		 			 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		        } else {
//		 		            alert("Save Canceled !");
		 		        }
		 		    }

		 		});
	 	    }
	 		
	 	}
	 }
	 
	 /**
	  * 选择配件记录
	  * @throws ParseException 
	  */
	 public void onClick$selectPj() throws ParseException{
	 	
	 	if(pjbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
//	 		Cght cght = (Cght)Executions.getCurrent().getArg().get("cght");
	 		Pj pj = (Pj)pjbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("pj", pj);
	 		arg1.put("cght", cght);
	 		Window window = (Window)Executions.createComponents(
		             "/jsp/pjgl/cght/sub/addCghtSub.zul", null, arg1);
	 		window.doModal();
	 		selectPjWin.detach();
	 	}
	 }
	 
	 /**
	  * 选择配件记录入库
	  * @throws ParseException 
	  */
	 public void onClick$selectPjStock() throws ParseException{
	 	
	 	if(pjbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
//	 		Cght cght = (Cght)Executions.getCurrent().getArg().get("cght");
	 		Pj pj = (Pj)pjbox.getSelectedItem().getValue();
	 		Map<String, Object> arg1 = new HashMap<String, Object>();
	 		arg1.put("pj", pj);
	 		Window window = (Window)Executions.createComponents(
		             "/jsp/pjgl/stock/instock/addInStock.zul", null, arg1);
	 		window.doModal();
	 		selectPjWin.detach();
	 	}
	 }
	 
	 public void onClick$queryPj(){
		 
	    	Map map = new HashMap<Object,Object>();
	    	User user = (User)session.getAttribute("user");
	    	
	    	Map<Object, Object> cghtMap = new HashMap<Object, Object>();
			cghtMap.put("companyid", user.getCompanyid());
			cghtMap.put("cghtid", cght.getId());
			List<CghtSub> cghtSubs = cghtSubService.selectCghtSub(cghtMap);
			List<Integer> pjids = new ArrayList<Integer>();
			for (CghtSub cghtSub : cghtSubs) {
				pjids.add(cghtSub.getPjid());
			}
	    	
			map.put("pjids", pjids);
	    	map.put("companyid", user.getCompanyid());
	    	if(name.getValue() !=null && !"".equals(name.getValue().trim())){
	    		map.put("name", name.getValue().trim());
	    	}
	    	if(unit.getValue() !=null && !"".equals(unit.getValue().trim())){
	    		map.put("unit", unit.getValue().trim());
	    	}
	    	map.put("begin", paging.getPageSize()*paging.getActivePage());
			map.put("end", paging.getPageSize());
			
			List<Pj> list = pjService.selectPj(map);
			ListModel listModelList =new ListModelList<Pj>();
	    	listModelList = null;
	    	if(list!= null && list.size()>0){
	    		listModelList = new ListModelList(list);
	    	}

	    	
	    	pjbox.setModel(listModelList);
	    	
	    	//初始化分页标签
	    	Integer a = Integer.MAX_VALUE;
	    	Integer a1 = Integer.MIN_VALUE;
	    	paging.setTotalSize(pjService.selectPj_count(map));
		}
}
