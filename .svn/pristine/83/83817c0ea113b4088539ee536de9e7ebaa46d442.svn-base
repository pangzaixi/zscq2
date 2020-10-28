package com.system.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;

import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.service.WenjianService;

 
public class LogManage    extends GenericForwardComposer{
//获取service对象
WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
LogService logService = (LogService)SpringUtil.getBean("logService");
/**
 * 写入log		 
 * @param remarks:日志信息,fl:业务分类，用于区分那个模块，哪个功能
 * @return
 */
public int addLog(String remarks,User user,String fl){
	 Map<Object,Object> map = new HashMap<Object,Object>();
	
	 //operator, operator_name,operate_time,remarks
	 map.put("operator", user.getLogin_name());
	 map.put("operator_name", user.getUser_name());
	 map.put("operate_time", new Date());
	 map.put("remarks", remarks);
	 map.put("fl", fl);
	int result = logService.addLog(map);
	return result;
}
}
