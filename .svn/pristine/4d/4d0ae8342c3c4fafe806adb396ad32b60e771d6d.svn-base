package com.zscq2.ss.controller;

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
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.yewu.zscq.bean.User;
import com.zscq2.ss.bean.Ssaj;
import com.zscq2.ss.bean.Sslc;
import com.zscq2.ss.service.SsajService;


@Controller("sslcController")   
@Scope("prototype") 
public class SslcController extends GenericForwardComposer{
	//页面元素
	Groupbox groupid;
	Textbox context;
	Textbox remarks;
	Ssaj ssaj;
	
	
	Listbox listbox;//页面遍历方式加载数据
	//获取service对象
		SsajService ssajService = (SsajService)SpringUtil.getBean("ssajService");
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	ssaj = (Ssaj)Executions.getCurrent().getArg().get("ssaj");	
	initListbox();
}

/**
 * 为列表加载数据
 */
private void initListbox(){
		
	Map map = new HashMap<Object,Object>();
	map.put("pid", ssaj.getId());
	
	
	List<Sslc> list = ssajService.selectSslc(map);
	Date d = new Date();
	
	ListModel listModelList =new ListModelList<Ssaj>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	
	listbox.setModel(listModelList);
}
/**
 * 保存方法
 */
public void onClick$saveButton(){
	if(context.getValue() == null || "".equals(context.getValue().toString())){
		Messagebox.show("请输入流程内容","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
//	ajh,createdate,context,creater,qt,pid
	
	Map map = new HashMap<Object,Object>();
	map.put("ajh", ssaj.getAjh());
	map.put("context", context.getValue().trim());
	User user = (User)session.getAttribute("user");
	map.put("creater", user.getUser_name());
	map.put("qt", remarks.getValue());
	map.put("pid", ssaj.getId());
	
	ssajService.insertSsajLc(map);
	Messagebox.show("保存完成","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	groupid.setVisible(false);
	initListbox();
}
}
