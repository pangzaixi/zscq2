package com.yewu.zscq.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import com.google.gson.Gson;
import com.pjgl.stock.service.StockService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.service.WenjianService;

@Controller
public class LoginController1 {

	@Autowired 
	public WenjianService wenjianService;
	
	@RequestMapping(value="/lonin.do",method={RequestMethod.POST})
	@ResponseBody
	public void pushInfo(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		
		String usertb = request.getParameter("usertb");
		String pwdtb = request.getParameter("pwdtb");
		int screenWidth = Integer.parseInt(request.getParameter("screenWidth"));
		int screenHeight = Integer.parseInt(request.getParameter("screenHeight"));
		request.getSession().setAttribute("screenWidth", screenWidth);
		request.getSession().setAttribute("screenHeight", screenHeight);	
    	Map map = new HashMap<Object,Object>();
    	
    	if(usertb != null && !"".equals(usertb)){
    		map.put("usertb", usertb);
    	}
		if(pwdtb != null && !"".equals(pwdtb)){
			map.put("pwdtb", pwdtb);		
		}
		
		response.setContentType("text/html;charset=UTF-8");
    	
    	List<User> list = wenjianService.selectUser(map);
    	if(list == null){
//    		return "账号或密码错误";
    		
    		response.getWriter().print("账号或密码错误");
    	}if(list !=null && list.size() == 1){
    		User user = list.get(0);
    		if("1".equals(user.getCompanyid())){
    			user.setLevel("1");//总公司标志
    		}
    		String roles = getRoles(wenjianService,user.getLogin_name());
    		user.setRoles(roles);
    		request.getSession().setAttribute("user", user);

//    		return "success";
    		response.getWriter().print("success");
    	}else{
//    		return "账号异常，请联系管理员";
    		response.getWriter().print("账号异常，请联系管理员");
    	}
	    	
	    	
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
