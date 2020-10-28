package com.pjgl.stock.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.google.gson.Gson;
import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.service.StockService;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;

/**
 * 库存管理
 * @author thinker
 *
 */
@Controller("stockController")   
@Scope("prototype") 
public class StockController     extends GenericForwardComposer{
	Boolean pagingOnClick = false;//是否点击的分页标签
	Window stockwin;
	Textbox pjName;//配件名称
	Combobox company;//公司名称
	Button inStock;//入库
	Button outStock;//出库
	Button checkStock;//盘库
	Button editStock;//编辑库存
	Checkbox yjbox;//是否预警
	//获取库存service对象
	StockService stockService = (StockService)SpringUtil.getBean("stockService");
	//公司service对象
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	ListModel companyModel;//公司下拉选数据源
	
	Grid grid;//库存表格
	Paging paging;
	private Footer footercategory;
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("stockController", this.getController());
	
	footercategory.setLabel("库存金额：0 元");
	
	
	//初始化公司下拉选
	User user = (User)session.getAttribute("user");
	
	
	Map<Object,Object> map = new HashMap<Object,Object>();
	if(!"1".equals(user.getLevel())){
		map.put("id", user.getCompanyid());
	}
	map.put("begin", 0);
	map.put("end", 1000);
	List<Company> list = companyService.selectCompany(map);
	companyModel = new ListModelList(list);
	company.setModel(companyModel);
	
	if(!"1".equals(user.getLevel())){
		company.setSelectedText(1, 1, list.get(0).getCompanyName(), true);
	}else{
		company.setSelectedText(1, 1, "总公司", true);

	}
	
		
	
	
	
}	



/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	if(company.isVisible() == true && company.getSelectedItem() == null  ) {
		company.setSelectedIndex(0);
	}
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Stock> list = stockService.selectStock(map);
	if(list!= null && list.size()>0){
		Stock tmp = new Stock();
		for(int i=0;i<list.size();i++){
			tmp = list.get(i);
			if(tmp.getTotalNum()>tmp.getUpperLimit() || tmp.getTotalNum()<tmp.getLowerLimit()){
				tmp.setStyle("color:red");
			}
		}
	}else{
		footercategory.setLabel("库存金额：0 元");
//		return;
	}
	
	
	ListModel listModelList =new ListModelList<Stock>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}
	grid.setModel(listModelList);
	
	Double amounts  = stockService.selectAmounts(map);
	if(amounts == null){
		amounts=0D;
	}
	footercategory.setLabel("库存金额： "+amounts+" 元");
	//初始化分页标签
	paging.setTotalSize(stockService.selectStock_count(map));
	

}

private void param(Map<Object,Object> map) throws ParseException{
	if(pjName.getValue() != null && !"".equals(pjName.getValue().trim())){
		map.put("pjName", pjName.getValue().trim());
	}
	
	
	
	User user = (User) session.getAttribute("user");
	if("1".equals(user.getLevel())){//总公司，取公司列表中选择的项
		map.put("companyId", company.getSelectedItem().getContext());
	}else{//分公司，取当前登录人所属公司
		map.put("companyId", user.getCompanyid());
	}
	if(yjbox.isChecked()){
		map.put("yj", true);
	}
			
	
	
//	map.put("begin", paging.getPageSize()*paging.getActivePage());
//	map.put("end", paging.getPageSize());
	
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
public void onClick$paging() throws ParseException{
	int activepage = paging.getActivePage();
	pagingOnClick = true;
	 onClick$queryButton();
	 if(activepage<=paging.getPageCount()-1){
		 paging.setActivePage(activepage);	 
	 }else{
		 paging.setActivePage(0);
	 }
	 
}
/**
 * 清空查询条件
 */
public void onClick$clearButton(){
	pjName.setValue("");	
	yjbox.setChecked(false);
	company.setSelectedIndex(0);
}
/**
 * 接口调用
 * @throws UnsupportedEncodingException
 */
public void onClick$test() throws UnsupportedEncodingException{
	HttpPost httpPost = new HttpPost("http://172.20.15.97:8080/pjgl/pushInfo.do");// 创建httpPost
	httpPost.setHeader("Content-Type", "text/plain;charset=UTF-8");
	httpPost.setHeader("type","post"); 
	httpPost.setHeader("dataType","json");
	String charSet = "UTF-8";
	
	StringEntity entity;		
	
	 Map<String,Object> map = new HashMap<String,Object>();
	 
	 map.put("platenum", "11");
	 map.put("workdate", "22");
	 List<Object[]> list = new ArrayList<Object[]>();
	 for(int i=0;i<10;i++){
		 if(i==3){
			 Object[] b = {i,"2"};
			 list.add(b);
		 }else{
			 Object[] b = {String.valueOf(i),"2"};
			 list.add(b);
		 }
		  
	 }
	 
	 map.put("zb", list);
	 
	 Gson gson = new Gson();
	 String aaa= gson.toJson(map);

	entity = new StringEntity(aaa, charSet);
	entity.setContentType("text/json");
	entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
	httpPost.setEntity(entity);
	
  CloseableHttpResponse response = null;
   
  try {
  	CloseableHttpClient httpclient = HttpClients.createDefault();
  	response = httpclient.execute(httpPost);
      StatusLine status = response.getStatusLine();
      int state = status.getStatusCode();
      if (state == HttpStatus.SC_OK) {
      	HttpEntity responseEntity = response.getEntity();
      	String jsonString = EntityUtils.toString(responseEntity);
//      	System.out.println(params);
//      	System.out.println(jsonString);
      	
      	if(jsonString.contains("500")){
      	
      	}
      	
      }
      else{
      	HttpEntity responseEntity = response.getEntity();
      	String jsonString = EntityUtils.toString(responseEntity);
      	System.out.println(jsonString);
      	
		}
  }catch (Exception e) {
//		// TODO Auto-generated catch block
		e.printStackTrace();
  }	
  finally {
      if (response != null) {
          try {
              response.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
}

/**
 * 入库
 */
public void onClick$inStock(){
	 Window window = (Window)Executions.createComponents(
             "/jsp/pjgl/stock/instock/selectPj.zul", null, null);
     window.doModal();
}

/**
 * 盘库
 */
public void onClick$checkStock(){
	 Window window = (Window)Executions.createComponents(
             "/jsp/pjgl/stock/stockCheck/stockCheck.zul", null, null);
     window.doModal();
}
/**
 * 编辑库存
 */
public void editStock(String id){
	Map<String, Object> arg1 = new HashMap<String, Object>();
	 
	List<Stock> list =  (List<Stock>)grid.getModel();
	for(Stock tmp:list){
	 if(tmp.getId() == Integer.valueOf(id).parseInt(id)){
		 arg1.put("stock", tmp);
		 Window window = (Window)Executions.createComponents("/jsp/pjgl/stock/editStock.zul", null, arg1);
		    window.doModal();
		 break;   
	 }
	}
	
}
/**
 * 出库
 */
public void onChange$pjName(){
//	System.out.println(pjName.getValue());
//	pjName.focus();
//	if(pjName.getValue() != null  && pjName.getValue().length()==10){
//		System.out.println(pjName.getValue());	
//		pjName.setValue("");
//	}
}

public void onChanging$pjName(){
//	System.out.println(pjName.getValue()+"!!!!!");
//	pjName.invalidate();
//	inStock.focus();
}

/**
 * 领用
 */
public void onClick$outStock(){
	 Window window = (Window)Executions.createComponents(
             "/jsp/pjgl/stock/stockUse/stockUse.zul", null, null);
     window.doModal();
}

/**
 * 库存领用
 */
public void useStock(String id){
	Map<String, Object> arg1 = new HashMap<String, Object>();
	 
	List<Stock> list =  (List<Stock>)grid.getModel();
	for(Stock tmp:list){
	 if(tmp.getId() == Integer.valueOf(id).parseInt(id)){
		 arg1.put("stock", tmp);
		 Window window = (Window)Executions.createComponents("/jsp/pjgl/stock/stockUse/useStock.zul", null, arg1);
		    window.doModal();
		 break;   
	 }
	}
	
}
	
}
