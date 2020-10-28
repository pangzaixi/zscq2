package com.yewu.zscq.controller;

import java.awt.Desktop;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.SessionCookieConfig;


import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.system.utils.ApplicationBean;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.service.WenjianService;

import javafx.application.Application;

@Controller("loginController")  
@Scope("prototype") 
public class LoginController    extends GenericForwardComposer{

	Textbox usertb;
	Textbox pwdtb;
	
	public void onClick$logIn(){
		
		//获取service对象
	 WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
    	
    	Map map = new HashMap<Object,Object>();
    	
    	if(usertb != null && !"".equals(usertb)){
    		map.put("usertb", usertb.getValue());
    	}
		if(pwdtb != null && !"".equals(pwdtb)){
			map.put("pwdtb", pwdtb.getValue());		
		}
		
    	
    	
    	List<User> list = wenjianService.selectUser(map);
    	if(list == null){
    		Messagebox.show("账号或密码错误");
    	}if(list !=null && list.size() == 1){
    		User user = list.get(0);
    		if("1".equals(user.getCompanyid())){
    			user.setLevel("1");//总公司标志
    		}
    		String roles = getRoles(wenjianService,user.getLogin_name());
    		user.setRoles(roles);
    		session.setAttribute("user", user);
    		Executions.sendRedirect("./jsp/menu2/index.zul");
    	}else{
    		Messagebox.show("账号异常，请联系管理员");
    	}
    	System.out.println();
    	
//    	paging.setActivePage(0);
    	
         
    }
	public String getRoles(WenjianService wenjianService,String login_name){
		List<String> list = wenjianService.selectRoles(login_name);
		if(list ==null || list.size()==0){
			return "";
		}else{
			String roles = "";
			for(int i=0;i<list.size();i++){
				roles = roles+","+list.get(i);
			}
			return roles;
		}
		
	}
}
