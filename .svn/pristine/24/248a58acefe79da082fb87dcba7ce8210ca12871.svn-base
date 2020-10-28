package com.zscq2.jxqd.controller;

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

/**
 * 绝限案件提醒弹出页面控制类
 * @author thinker
 *
 */
@Controller("remindController")   
@Scope("prototype") 
public class RemindController    extends GenericForwardComposer{

	Window jxqdRemindWin;
	Combobox pagesize;//页面记录数下拉选
	Boolean pagingOnClick = false;//是否点击的分页标签
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
		
	Textbox sbmc;//商标名称
	Textbox ggq;//公告期
	Intbox lb;//案件类别
	Combobox ajlx;//  '案件类型',
	Label remarks;
	Intbox stepNum;//自定义间隔日期
	
	int currentButton=0;//当前点击的按钮是哪个：官方收文日1，绝限日2，超期案件3
	//××××××××××××数据源
	
	Listbox listbox1;//页面遍历方式加载数据
	Label recordCount;//记录总数
	Paging paging;	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("remindController", this.getController());
	
	int screenWidth = (int)session.getAttribute("screenWidth");
	int screenHeight = (int)session.getAttribute("screenHeight");
    int[] t  =Screen.widthAndHeight("2",screenWidth,screenHeight);
        
    jxqdRemindWin.setWidth(screenWidth-t[0]+"px");
    jxqdRemindWin.setHeight(screenHeight-t[1]+"px");
    
	Map map = new HashMap<Object,Object>();
	List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
	ListModel ajlxModelList =new ListModelList<Ajlx>();
	ajlxModelList = null;
	if(list_ajlx!= null && list_ajlx.size()>0){
		ajlxModelList = new ListModelList(list_ajlx);
	}
	ajlx.setModel(ajlxModelList);
	
	listbox1.setCheckmark(true);
	 listbox1.setMultiple(true);
}	
/**
 * 收文日查询
 * @throws ParseException 
 */
public void onClick$swrQueryButton() throws Exception{
	if(stepNum.getValue() == null || "".equals(stepNum.getValue())){
		Messagebox.show("请输入未来天数","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return ;
	}
	currentButton=1;
	remarks.setValue("当前结果为   收文日期   查询结果");
	Map map = new HashMap<Object,Object>();
	param(map);
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	calendar.setTime(date); 
	date = calendar.getTime();
	map.put("swrq_q1", formatter.format(date));
	
	calendar.add(calendar.DATE,stepNum.getValue());//把日期往后增加一天.整数往后推,负数往前移动
	date = calendar.getTime();
	map.put("swrq_q2", formatter.format(date));
	
	List<Lazb> list = lazbService.selectJXQDForRemind(map);
	
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
//	
	listbox1.setModel(listModelList);
	boolean t = listbox1.isMultiple();
	System.out.println(t);

	listbox1.invalidate();	
	listbox1.focus();
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	int count = lazbService.selectJXQDForRemind_count(map);
	recordCount.setValue(String.valueOf(count));
	paging.setTotalSize(count);
	
}

/**
 * 绝限日查询
 * @throws ParseException
 * @throws Exception 
 */
public void onClick$jxrButton() throws ParseException, Exception{
	if(stepNum.getValue() == null || "".equals(stepNum.getValue())){
		Messagebox.show("请输入未来天数","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return ;
	}
	remarks.setValue("当前结果为   绝限   查询结果");
	currentButton=2;
	Map map = new HashMap<Object,Object>();
	param(map);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	calendar.setTime(date);
	calendar.add(calendar.DATE,stepNum.getValue());//把日期往后增加一天.整数往后推,负数往前移动
	date = calendar.getTime();
	map.put("jxrq", formatter.format(date));
	map.put("status", "未完结");
	List<Lazb> list = lazbService.selectJXQDForRemind(map);
	
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	
	listbox1.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	int count = lazbService.selectJXQDForRemind_count(map);
	recordCount.setValue(String.valueOf(count));
	paging.setTotalSize(count);
	
	listbox1.invalidate();	
	listbox1.focus();
}

/**
 * 绝限日超期查询
 * @throws ParseException
 */
public void onClick$cqButton() throws Exception{
	currentButton=3;
	remarks.setValue("当前结果为   绝限超期   查询结果");
	Map map = new HashMap<Object,Object>();
	param(map);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	calendar.setTime(date);
	
	date = calendar.getTime();
	map.put("jxrq", formatter.format(date));
	map.put("status", "未完结");
	List<Lazb> list = lazbService.selectJXQDForRemind(map);
	
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	
	listbox1.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	int count = lazbService.selectJXQDForRemind_count(map);
	recordCount.setValue(String.valueOf(count));
	paging.setTotalSize(count);
	
	listbox1.invalidate();	
	listbox1.focus();
}

/**
 * 当页面记录数下拉选值发生变化时修改表格单页记录数
 * @throws Exception 
 */
public void onChange$pagesize() throws Exception{
	paging.setPageSize(Integer.parseInt(pagesize.getValue()));
	try {
		pagingOnClick = true;
		if(currentButton==1){
			onClick$swrQueryButton();
		}else if(currentButton==2){
			onClick$jxrButton();
		}else if(currentButton==3){
			onClick$cqButton();
		}else{
			return;
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 案件批量完结功能
 * @throws Exception 
 */
public void onClick$wjButton() throws Exception{
	if(currentButton==1){
		Messagebox.show("请执行  绝限日查询  或者  超期查询","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	Set<Listitem> set = listbox1.getSelectedItems();
	Object[] lazb_sz = set.toArray();
    for(int i=0;i<lazb_sz.length;i++){
    	Listitem o = (Listitem)lazb_sz[i];
		Lazb lazb = (Lazb)o.getValue();
		System.out.println("");
		
		
		Map<Object,Object> map = new HashMap<>();
		map.put("id", lazb.getId());
		map.put("status", "已完结");
		map.put("updatedate", new Date());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		lazbService.changeStatus(map);
		
		
		
		//记录操作日志
		 LogManage logManage = new LogManage();
		 logManage.addLog("案件完结："+lazb.getAjh(),user,"案件完结");
		 
	}
    

	if(currentButton==1){
		onClick$swrQueryButton();
	}else if(currentButton==2){
		onClick$jxrButton();
	}else if(currentButton==3){
		onClick$cqButton();
	}else{
		return;
	}
	
    Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("message", "批量结案完成");//要弹窗的内容
    Window window = (Window)Executions.createComponents(
            "/jsp/system/base/feizuduan.zul", null, arg1);
    
    window.doOverlapped();//非阻断弹窗
//	Messagebox.show("所选案件已设置为完结完结","注意", Messagebox.OK, Messagebox.EXCLAMATION); 	
	listbox1.invalidate();
	listbox1.focus();
}

/**
 * 清空查询条件
 * @throws Exception 
 */
public void onClick$clearButton() throws Exception{
	
	stepNum.setValue(null);
	sbmc.setValue("");
	ggq.setValue("");
	ajlx.setValue(null);
	lb.setValue(null);
	
}
private void param(Map<Object,Object> map) throws Exception{
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
	if(currentButton==1){
		onClick$swrQueryButton();
	}else if(currentButton==2){
		onClick$jxrButton();
	}else if(currentButton==3){
		onClick$cqButton();
	}else{
		return;
	}
	
	if(activepage<=paging.getPageCount()-1){
	 paging.setActivePage(activepage);	 
	}else{
	 paging.setActivePage(0);
	}
	 
}
}