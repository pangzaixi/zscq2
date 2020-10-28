package com.system.os.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.os.bean.OsSbcx;
import com.system.os.service.Osservice;

public class SbcxController   extends GenericForwardComposer{


	Window gangtiecaigouhetong;
	//××××××××××××页面元素
	Button queryButton;
	Listbox resultList2;//页面遍历方式加载数据
	Paging paging;
	String value = "121";
	//查询条件
	Combobox sblx_q;
	Textbox sbh_q;
	Textbox cjh_q;
	Textbox cph_q;
	//××××××××××××数据源
	ListModel listModelList =new ListModelList<OsSbcx>();
	ListModel sblxModel =new ListModelList<String>();
    public SbcxController(){
     	System.out.println();
    	
    }
    public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		List<String> list2 = new ArrayList<String>();
    	list2.add("毛胚");
    	list2.add("板材");
    	list2.add("螺纹钢");
    	sblxModel= new ListModelList(list2);
    	sblx_q.setModel(sblxModel);
    }	
    public void onClick$queryButton(){
    	//获取service对象
    	Osservice Osservice = (Osservice)SpringUtil.getBean("Osservice");
    	
    	Map map = new HashMap<Object,Object>();
    	map.put("sbh_q", sbh_q.getValue());
    	map.put("cjh_q", cjh_q.getValue());
    	map.put("cph_q", cph_q.getValue());
    	resultList2.getRows();
    	map.put("begin", 0);
    	map.put("end", resultList2.getRows());
    	System.out.println(sblx_q.getText());
    	System.out.println(sblx_q.getName());
    	System.out.println(sblx_q.getContext());
    	List<OsSbcx> list = Osservice.selectCaigouHeTongMain(map); 
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}
//    	SimpleGroupsModel a = new SimpleGroupsModel()
    	//resultList.LOADING_MODEL;
    	
    	resultList2.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(Osservice.selectCaigouHeTongMain_count(map));
    	
    	paging.setActivePage(0);
    	
         
    }
    public void onClick$clearButton(){
    	listModelList = null;
    	resultList2.setModel(listModelList);
    	
    	sbh_q.setValue("");;
    	cjh_q.setValue("");;
    	cph_q.setValue("");;

    }
    public void onClick$paging(){
    	System.out.println(paging.getAction());
    	System.out.println(paging.getActivePage());
    	System.out.println(paging.getPageCount());
    	System.out.println(paging.getPageSize());
    	//获取service对象
    	Osservice gangtiecaigouhetongService = (Osservice)SpringUtil.getBean("Osservice");
    	
    	Map map = new HashMap<Object,Object>();
    	map.put("sbh_q", sbh_q.getValue());
    	map.put("cjh_q", cjh_q.getValue());
    	map.put("cph_q", cph_q.getValue());
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	
    	
    	map.put("begin", paging.getActivePage()*paging.getPageSize());
    	map.put("end", resultList2.getRows());
    	
    	List<OsSbcx> list = gangtiecaigouhetongService.selectCaigouHeTongMain(map);
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}
//    	SimpleGroupsModel a = new SimpleGroupsModel()
    	//resultList.LOADING_MODEL;
    	
    	resultList2.setModel(listModelList);
    }
    
    
    
   
    
	public Window getGangtiecaigouhetong() {
		return gangtiecaigouhetong;
	}
	public void setGangtiecaigouhetong(Window gangtiecaigouhetong) {
		this.gangtiecaigouhetong = gangtiecaigouhetong;
	}
	
	public ListModel getListModelList() {
		return listModelList;
	}
	public void setListModelList(ListModel listModelList) {
		this.listModelList = listModelList;
	}
	
	
}
