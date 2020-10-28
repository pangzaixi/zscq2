package com.yewu.zscq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.dao.JxqdDao;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.service.WenjianService;


@Controller("queryJxqd")  
@Scope("prototype") 
public class QueryJxqd   extends GenericForwardComposer{
	
	Window queryJxqd;
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	System.out.println();
	//String sbh = (String)Executions.getCurrent().getArg().get("sbh");
	String sbh = (String)session.getAttribute("sbh");
	JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
	Map map = new HashMap<Object,Object>();
	map.put("sbh", sbh); 
	map.put("begin", 0);
	map.put("end", 100);
	 List<Jxqd> list = jxqdService.selectJXQD(map);
 	ListModel listModelList =new ListModelList<Jxqd>();
 	listModelList = null;
 	if(list!= null && list.size()>0){
 		listModelList = new ListModelList(list);
 	}

 	
 	
 	listbox.setModel(listModelList);
}
public void onClick$selectedJxqd(){
	Listitem listitem = listbox.getSelectedItem();
	if(listitem == null){
		Messagebox.show("请选择案件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
	}else{
		AddWenJianController addWenJianController =(AddWenJianController)session.getAttribute("addWenJianController");//
		Jxqd jxqd = (Jxqd)listbox.getSelectedItem().getValue();
		addWenJianController.ajh.setValue(jxqd.getAjh());
		addWenJianController.sbmc.setValue(jxqd.getSbmc());
		addWenJianController.sblx.setValue(jxqd.getLb());
		addWenJianController.sbh.setValue(jxqd.getSbh());
		queryJxqd.detach();//关闭选择窗口
	}
	
}
}