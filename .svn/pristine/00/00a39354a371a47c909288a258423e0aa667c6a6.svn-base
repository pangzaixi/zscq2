package com.pjgl.lysqSub.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import com.pjgl.cght.sub.bean.CghtSub;
import com.pjgl.cght.sub.service.serviceimpl.CghtSubService;
import com.pjgl.lysq.bean.Lysq;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.yewu.zscq.bean.User;



@Controller("selectPjController")   
@Scope("prototype") 
public class SelectPjController extends GenericForwardComposer{

	Textbox name;//配件名称
	Textbox unit;//单位
	Textbox pjName;
	Button selectPjStock;
	
	//××××××××××××数据源
	
	public Listbox pjbox;//页面遍历方式加载数据
	Paging paging;
	Window selectPjWin;
	Lysq lysq;
	
	//获取service对象
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	CghtSubService cghtSubService = (CghtSubService)SpringUtil.getBean("cghtSubService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("pjController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		lysq = (Lysq)Executions.getCurrent().getArg().get("lysq");
		
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
	
	 /**
	  * 分页标签
	  */
	 public void onClick$paging(){
		 System.out.println(paging.getActivePage());
		 System.out.println(paging.getPageSize());
		 int activepage = paging.getActivePage();
		 
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
	 	    if(useCount == 0){
	 	    	int result = pjService.deletePj(pj.getId());
		 		onClick$queryButton();//刷新父窗口表格
		 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 	    }else{
	 	    	Messagebox.show("该配件存在对应采购合同或入库记录，禁止删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
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
	 		arg1.put("lysq", lysq);
	 		Window window = (Window)Executions.createComponents(
		             "/jsp/pjgl/lysqSub/addLysqSub.zul", null, arg1);
	 		window.doModal();
	 		selectPjWin.detach();
	 	}
	 }
	 
	
	
}