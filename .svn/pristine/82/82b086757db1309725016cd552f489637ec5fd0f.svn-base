package com.yewu.log.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.yewu.log.bean.Log;
import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.service.WenjianService;


@Controller("logController")  
@Scope("prototype") 
public class LogController   extends GenericForwardComposer{
//页面元素
Textbox operator_name;
Textbox fl;
Datebox rq_q1;//
Datebox rq_q2;//	
Boolean pagingOnClick = false;//是否点击的分页标签	
	//××××××××××××数据源
	
		Listbox listbox;//页面遍历方式加载数据
		Paging paging;
		
/**
 * 初始化		
 */
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);
	System.out.println();
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
	    	
	
}

		
/**
 * 查询日志
 */
 public void onClick$queryButton(){
	//获取service对象
	 LogService logService = (LogService)SpringUtil.getBean("logService");
	    	
	    	Map map = new HashMap<Object,Object>();
	    	
	    	if(operator_name != null && !"".equals(operator_name)){
	    		map.put("operator_name", operator_name.getValue());
	    	}
	    	if(fl != null && !"".equals(fl)){
	    		map.put("fl", fl.getValue());
	    	}
	    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    	if(rq_q1.getValue()!=null){
	    		map.put("rq_q1", f.format(rq_q1.getValue()));
	    	}
	    	if(rq_q2.getValue()!=null){
	    		Calendar calendar = new GregorianCalendar();
	    		Date date = rq_q2.getValue();
	    		calendar.setTime(date); 
	    		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
	    		date = calendar.getTime();
	    		map.put("rq_q2", f.format(date));
	    	}
			
			map.put("begin", paging.getPageSize()*paging.getActivePage());
			map.put("end", paging.getPageSize());
			
	    	
	    	
	    	
	    	
	    	
	    	List<Log> list = logService.selectLog(map);
	    	ListModel listModelList =new ListModelList<Log>();
	    	listModelList = null;
	    	if(list!= null && list.size()>0){
	    		listModelList = new ListModelList(list);
	    	}

	    	
	    	
	    	listbox.setModel(listModelList);
	    	
	    	
	    	//初始化分页标签
	    	Integer a = Integer.MAX_VALUE;
	    	Integer a1 = Integer.MIN_VALUE;
	    	paging.setTotalSize(logService.selectLog_count(map));
	    	
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
	 operator_name.setValue("");
	 fl.setValue("");
	 rq_q1.setValue(null);//
	 rq_q2.setValue(null);//	
 }
}