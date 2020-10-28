package com.system.zzjg.controller;

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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;


@Controller("zzjgController")   
@Scope("prototype") 
public class ZzjgController    extends GenericForwardComposer{
//窗体对象	
	Window zzjgwin;
//页面查询条件对象
	Textbox department;//机构名称
	
	
	
//获取service对象
	ZzjgService zzjgService = (ZzjgService)SpringUtil.getBean("zzjgService");
//固定对象
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
/**
 * 初始化方法	
 */
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("zzjgController", this.getController());
		
}

/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Zzjg> list = zzjgService.selectZzjg(map);
	ListModel listModelList =new ListModelList<Zzjg>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}
	listbox.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	paging.setTotalSize(zzjgService.selectZzjg_count(map));
	

}

/**
 * 清空查询条件
 */
public void onClick$clearButton(){
	department.setValue("");
//	sblb.setValue(null);
//	ra2.setSelected(false);
}

/**
 * 新增部门
 */
public void onClick$addButton(){
	Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("msg", "Hello ZK!");

    Window window = (Window)Executions.createComponents(
            "/jsp/system/zzjg/addZzjg.zul", zzjgwin, arg1);
    window.doModal();
}

/**
 * 编辑部门记录
 * @throws ParseException 
 */
public void onClick$editButton() throws ParseException{
	
	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}else{
		Zzjg zzjg = (Zzjg)listbox.getSelectedItem().getValue();
		Map<String, Object> arg1 = new HashMap<String, Object>();
		arg1.put("zzjg", zzjg);
		
		Window window = (Window)Executions.createComponents(
	            "/jsp/system/zzjg/editZzjg.zul", null, arg1);
	    
	    window.doModal();//模式弹窗
//	    window.doPopup();//非模式弹窗
	    
	    onClick$queryButton();//刷新父窗口表格
	}
}

/**
 * 参数整理
 * @param map
 * @throws ParseException
 */
private void param(Map<Object,Object> map) throws ParseException{
	if(department.getValue() != null && !"".equals(department.getValue())){
		map.put("department", department.getValue());
	}
	
	User user = (User)session.getAttribute("user");
	map.put("companyid", user.getCompanyid());
	map.put("begin", paging.getPageSize()*paging.getActivePage());
	map.put("end", paging.getPageSize());
}
/**
 * 分页标签
 * @throws ParseException 
 */
public void onClick$paging() throws ParseException{
	int activepage = paging.getActivePage();
	 
	 onClick$queryButton();
	 if(activepage<=paging.getPageCount()-1){
		 paging.setActivePage(activepage);	 
	 }else{
		 paging.setActivePage(0);
	 }
	 
}
}