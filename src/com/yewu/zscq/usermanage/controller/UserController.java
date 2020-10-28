package com.yewu.zscq.usermanage.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.service.WenjianService;
import com.yewu.zscq.usermanage.bean.User;
import com.yewu.zscq.usermanage.service.UserService;


@Controller("userController")  
@Scope("prototype") 
public class UserController   extends GenericForwardComposer{

	Textbox ajh;
	Textbox sbmc;
	Intbox sblx;
	Intbox sbh;
	Textbox wjmc;
	Textbox dlr;
	Datebox lrrq_q1;
	Datebox lrrq_q2;
	
	//××××××××××××数据源
	
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	
	
	/**
	 * 查询按钮
	 */
 public void onClick$queryButton(){
    	//获取service对象
	 UserService userService = (UserService)SpringUtil.getBean("userService");
    	
    	Map map = new HashMap<Object,Object>();
    	
    	if(wjmc.getValue() != null && !"".equals(wjmc.getValue())){
			map.put("wjmc", wjmc.getValue());
		}
		
		
		map.put("begin", paging.getPageSize()*paging.getActivePage());
		map.put("end", paging.getPageSize());
		
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	
    	if(lrrq_q1.getValue()!=null){
    		map.put("lrrq_q1", f.format(lrrq_q1.getValue()));
    	}
    	if(lrrq_q2.getValue()!=null){
    		Calendar calendar = new GregorianCalendar();
    		Date date = lrrq_q2.getValue();
    		calendar.setTime(date); 
    		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
    		date = calendar.getTime();
    		map.put("lrrq_q2", f.format(date));
    	}
    	
    	
    	
    	List<User> list = userService.selectUser(map);
    	ListModel listModelList =new ListModelList<User>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		for(User user:list){
    			if("1".equals(user.getLevel())){
    				user.setLevel("管理者");
    			}else if("2".equals(user.getLevel())){
    				user.setLevel("操作者");
    			}else if("3".equals(user.getLevel())){
    				user.setLevel("浏览者");
    			}
    			if("0".equals(user.getStatus())){
    				user.setStatus("禁用");
    			}else if("1".equals(user.getStatus())){
    				user.setStatus("启用");
    			}
    		}
    		
    		listModelList = new ListModelList(list);
    	}

    	
    	
    	listbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(userService.selectUser_count(map));
    	
//    	paging.setActivePage(0);
    	
         
    }
}