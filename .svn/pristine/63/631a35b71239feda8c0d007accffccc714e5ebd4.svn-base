package com.pjgl.cgsp.controller;

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

import com.pjgl.cgsp.bean.Cgsp;
import com.pjgl.cgsp.service.serviceimpl.CgspService;
import com.yewu.zscq.bean.User;

@Controller("countCgspController")   
@Scope("prototype")
public class CountCgspController extends GenericForwardComposer{

	Boolean pagingOnClick = false;//是否点击的分页标签
	Datebox sprq1;
	Datebox sprq2;
	Textbox htbh;
	
	//××××××××××××数据源
	
	Grid countcgspbox;
	private Footer footercategory;
	Paging pagingCount;
//	private Footer footerDetails;
	
	//获取service对象
//	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	CgspService cgspService = (CgspService)SpringUtil.getBean("cgspService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("countCgspController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		if (footercategory != null) {
			footercategory.setLabel("收票金额：0 元");
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
	    	if(pagingOnClick == true){
	    		pagingOnClick = false;
	    		map.put("begin", pagingCount.getPageSize()*pagingCount.getActivePage());
	    		map.put("end", pagingCount.getPageSize());
	    	}else{
	    		map.put("begin", 0);
	    		map.put("end", pagingCount.getPageSize());
	    		pagingCount.setActivePage(0);
	    	}
			
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
		 int activepage = pagingCount.getActivePage();
		 pagingOnClick = true;
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
	 
	 
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/cgsp/selectCght.zul", null, null);
	     window.doModal();
	 }
}
