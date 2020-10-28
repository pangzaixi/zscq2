package com.pjgl.cgfk.controller;

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
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cgfk.bean.Cgfk;
import com.pjgl.cgfk.service.serviceimpl.CgfkService;
import com.yewu.zscq.bean.User;

@Controller("countCgfkController")   
@Scope("prototype")
public class CountCgfkController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Datebox fkrq1;
	Datebox fkrq2;
	Textbox htbh;
	
	//××××××××××××数据源
	
	Grid countcgfkbox;
	private Footer footercategory;
	Paging pagingCount;
//	private Footer footerDetails;
	
	//获取service对象
//	CghtService cghtService = (CghtService)fkringUtil.getBean("cghtService");
	CgfkService cgfkService = (CgfkService)SpringUtil.getBean("cgfkService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("countCgfkController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		if (footercategory != null) {
			footercategory.setLabel("收票金额：0 元");
		}
	}
	
	public void onClick$queryCgfkButton() throws ParseException{
		 
		 Map map = new HashMap<Object,Object>();
	    	User user = (User)session.getAttribute("user");
	    	
	    	map.put("companyid", user.getCompanyid());
	    	if(htbh.getValue() !=null && !"".equals(htbh.getValue().trim())){
	    		map.put("htbh", htbh.getValue().trim());
	    	}
	    	if(fkrq1.getValue() !=null){
	    		map.put("fkrq1", fkrq1.getValue());
	    	}
	    	if(fkrq2.getValue() !=null){
	    		map.put("fkrq2", fkrq2.getValue());
	    	}
	    	if(pagingOnClick == true){
	    		pagingOnClick = false;
	    		map.put("begin", pagingCount.getPageSize()*pagingCount.getActivePage());
	    		map.put("end", pagingCount.getPageSize());
	    	}else{
	    		map.put("begin", 0);
	    		map.put("end", pagingCount.getPageSize());
	    		pagingCount.setActivePage(0);
	    	}
			
			List<Cgfk> list = cgfkService.countCgfk(map);
			
			
			ListModel listModelList =new ListModelList<Cgfk>();
	    	if(list!= null && list.size()>0){
	    		listModelList = new ListModelList(list);
	    		Double amounts = cgfkService.selectAmounts(map);
	    		footercategory.setLabel("收票金额：" + amounts +" 元");
	    	}else{
	    		footercategory.setLabel("收票金额：0 元");
	    	}

	    	
	    	countcgfkbox.setModel(listModelList);
	    	
	    	//初始化分页标签
	    	Integer a = Integer.MAX_VALUE;
	    	Integer a1 = Integer.MIN_VALUE;
	    	pagingCount.setTotalSize(cgfkService.countCgfk_count(map));
	 }
	 
	 /**
	  * 分页标签
    * @throws ParseException 
	  */
	 public void onClick$pagingCount() throws ParseException{
		 int activepage = pagingCount.getActivePage();
		 pagingOnClick = true;
		 onClick$queryCgfkButton();
		 if(activepage<=pagingCount.getPageCount()-1){
			 pagingCount.setActivePage(activepage);	 
		 }else{
			 pagingCount.setActivePage(0);
		 }
		 
	 }
	 
	 /**
	  * 清空查询条件
	  */
	 public void onClick$clearCgfkButton(){
		htbh.setValue("");
		fkrq1.setValue(null);
		fkrq2.setValue(null);
	 }
	 
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cgfk/selectCght.zul", null, null);
	     window.doModal();
	 }
}
