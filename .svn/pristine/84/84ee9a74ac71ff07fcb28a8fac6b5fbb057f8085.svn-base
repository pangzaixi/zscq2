package com.zscq2.updateDlr.controller;



import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.utils.LogManage;
import com.system.utils.Screen;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.updateDlr.service.UpdateDlrService;

/**
 * 绝限案件提醒弹出页面控制类
 * @author thinker
 *
 */
@Controller("updateDlrController")   
@Scope("prototype") 
public class UpdateDlrController    extends GenericForwardComposer{

	Window updateDlr;
	Boolean pagingOnClick = false;//是否点击的分页标签
	Combobox pagesize;//页面记录数下拉选
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	//获取service对象
	UpdateDlrService updateDlrService = (UpdateDlrService)SpringUtil.getBean("updateDlrService");
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
		
	Datebox date1;
	Datebox date2;
	Combobox dlr;//代理人
	Textbox sbmc;//商标名称
	Textbox ggq;//公告期
	Intbox lb;//案件类别
	Combobox ajlx;//  '案件类型',
	Label remarks;
	
	//××××××××××××数据源
	
	Listbox listbox;//页面遍历方式加载数据
	Label recordCount;//记录总数
	Paging paging;	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("remindController", this.getController());
	
	int screenWidth = (int)session.getAttribute("screenWidth");
	int screenHeight = (int)session.getAttribute("screenHeight");
    int[] t  =Screen.widthAndHeight("2",screenWidth,screenHeight);
    
    screenWidth = screenWidth-t[0];
    screenHeight = screenHeight-t[1];
    
    updateDlr.setWidth(screenWidth+"px");
    updateDlr.setHeight(screenHeight+"px");
    
	Map map = new HashMap<Object,Object>();
	List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
	ListModel ajlxModelList =new ListModelList<Ajlx>();
	ajlxModelList = null;
	if(list_ajlx!= null && list_ajlx.size()>0){
		ajlxModelList = new ListModelList(list_ajlx);
	}
	ajlx.setModel(ajlxModelList);
	
	
	//代理人1
	List<Lazb> list_dlr1 = lazbService.selectDlr1();
	dlr.setModel(new ListModelList(list_dlr1));
}	
/**
 * 案件查询
 * @throws ParseException 
 */
public void onClick$queryButton() throws Exception{
	
//	remarks.setValue("当前结果为   收文日期   查询结果");
	Map map = new HashMap<Object,Object>();
	param(map);
	map.put("status", "未完结");
	
	
	List<Lazb> list = updateDlrService.selectJXQDForDlr(map);
	
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
//	
	listbox.setModel(listModelList);
	boolean t = listbox.isMultiple();
	System.out.println(t);

	listbox.invalidate();	
	listbox.focus();
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	int count = updateDlrService.selectJXQDForDlr_count(map);
	recordCount.setValue(String.valueOf(count));
	paging.setTotalSize(count);
	
}


public void onClick$clearButton(){
	date1.setValue(null);
	date2.setValue(null);
	sbmc.setValue("");
	ggq.setValue("");
	ajlx.setValue(null);
	lb.setValue(null);
}
/**
 * 当页面记录数下拉选值发生变化时修改表格单页记录数
 * @throws Exception 
 */
public void onChange$pagesize() throws Exception{
	paging.setPageSize(Integer.parseInt(pagesize.getValue()));
	try {
		onClick$queryButton();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 批量更新代理人功能
 * @throws Exception 
 */
public void onClick$updateDlrButton() throws Exception{
	if(dlr.getValue()==null || "".equals(dlr.getValue())){
		Messagebox.show("请选择代理人","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	Set<Listitem> set = listbox.getSelectedItems();
	Object[] lazb_sz = set.toArray();
	if(lazb_sz.length==0){
		Messagebox.show("请选择案件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
    for(int i=0;i<lazb_sz.length;i++){
    	Listitem o = (Listitem)lazb_sz[i];
		Lazb lazb = (Lazb)o.getValue();
		System.out.println("");
		
		
		Map<Object,Object> map = new HashMap<>();
		map.put("id", lazb.getId());
		map.put("dlr", dlr.getValue());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		updateDlrService.updateDlr(map);
		
		
		
		//记录操作日志
//		 LogManage logManage = new LogManage();
//		 logManage.addLog("案件完结："+lazb.getAjh(),user);
		 
	}
    

    onClick$queryButton();
	
	
    Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("message", "批量更新代理人完成");//要弹窗的内容
    Window window = (Window)Executions.createComponents(
            "/jsp/system/base/feizuduan.zul", null, arg1);
    
    window.doOverlapped();//非阻断弹窗
//	Messagebox.show("所选案件已设置为完结完结","注意", Messagebox.OK, Messagebox.EXCLAMATION); 	
    dlr.setValue(null);
	listbox.invalidate();
	listbox.focus();
}
private void param(Map<Object,Object> map) throws Exception{
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = new GregorianCalendar();
	if(date1.getValue() != null){
		calendar.setTime(date1.getValue()); 
		map.put("date1", formatter.format(calendar.getTime()));
	}
	
	if(date2.getValue() != null){
		calendar.setTime(date2.getValue()); 
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		map.put("date2", formatter.format(calendar.getTime()));
	}
	
	
	if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
		map.put("sbmc", sbmc.getValue());
	}//  '商标名称',
	if(ggq.getValue() != null && !"".equals(ggq.getValue())){
		map.put("ggq", ggq.getValue());
	}//  公告期
	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getValue());
	}//  '案件类型',
	if(lb.getValue() != null && !"".equals(lb.getValue())){
		map.put("lb", lb.getValue());
	}//  '类别',
	if(pagingOnClick == true){
		pagingOnClick = false;
		map.put("begin", paging.getPageSize()*paging.getActivePage());
		map.put("end", paging.getPageSize());
	}else{
		paging.setActivePage(0);
		map.put("begin", 0);
		map.put("end", paging.getPageSize());
	}
	
}
/**
 * 分页标签
 * @throws ParseException 
 */
public void onClick$paging() throws Exception{
	 
	int activepage = paging.getActivePage();
	pagingOnClick = true;
	onClick$queryButton();
	
	if(activepage<=paging.getPageCount()-1){
	 paging.setActivePage(activepage);	 
	}else{
	 paging.setActivePage(0);
	}
	 
}
}