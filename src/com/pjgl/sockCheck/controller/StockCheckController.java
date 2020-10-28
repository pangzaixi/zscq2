package com.pjgl.sockCheck.controller;

import org.eclipse.jdt.internal.compiler.ast.CompoundAssignment;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.ListModel;
	import org.zkoss.zul.ListModelList;
	import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
	import org.zkoss.zul.Paging;
	import org.zkoss.zul.Textbox;
	import org.zkoss.zul.Window;

	import com.pjgl.company.bean.Company;
	import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.pjgl.sockCheck.service.StockCheckService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.controller.StockController;
import com.pjgl.stock.service.StockService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
	
/**
 * 盘库
 * @author thinker
 *
 */
@Controller("stockCheckController")   
@Scope("prototype") 
public class StockCheckController      extends GenericForwardComposer{	
	//窗体对象
	Window stockcheckWin;
	
	//页面查询条件
	Button clearButton;
	Textbox erweima;//二维码录入
	Textbox remarks;//备注区域，用于显示提示信息
	Decimalbox totalNum;//数量
	Decimalbox amounts;//金额
	Decimalbox averagePrice;//平均单价
	Textbox pjName;
	Textbox pjGG;
	Textbox pkRemarks;//盘库备注
	//××××××××××××数据源
	
	int index = 0;//列表当前选定项
	public Listbox stockcheckListbox;//页面遍历方式加载数据
	ListModel listModelList =new ListModelList<Stock>();//列表数据源
	List<Stock> list_stock = new ArrayList<Stock>();
	Paging paging;
	
	//获取service对象
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	//获取service对象
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	//获取库存service对象
	StockService stockService = (StockService)SpringUtil.getBean("stockService");
	//获取盘库service对象
	StockCheckService stockCheckService = (StockCheckService)SpringUtil.getBean("stockCheckService");
	
	 
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("stockCheckController",this.getController());//用于从其他模块，
	}	
	
/**
 *  更新库存量	
 * @throws ParseException 
 */
public void onClick$sabeButton() throws ParseException{
	
	if(stockcheckListbox.getSelectedItem() != null){
		Listitem listitem = stockcheckListbox.getSelectedItem();
		Stock stock = (Stock)stockcheckListbox.getSelectedItem().getValue();
		index = stockcheckListbox.getSelectedIndex();
		for(Stock tmp:list_stock){
			if(tmp.getId() == stock.getId()){
				Map<Object,Object> map = new HashMap<Object,Object>();//
				addStockCheck(stock,map);//保存盘库记录
				tmp.setAgeragePrice(averagePrice.getValue().doubleValue());
				tmp.setAmounts(amounts.getValue().doubleValue());
				tmp.setTotalNum(totalNum.getValue().doubleValue());
				map.clear();
				map.put("id", tmp.getId());
				map.put("totalNum",tmp.getTotalNum());
				map.put("amounts",tmp.getAmounts());
				map.put("ageragePrice",tmp.getAgeragePrice());
				
				stockService.editStock(map);
				StockController stockController = (StockController)session.getAttribute("stockController");
				stockController.onClick$queryButton();
			}
		}
		listModelList = new ListModelList(list_stock);
		stockcheckListbox.setModel(listModelList);//刷新数据后会失去焦点,所以不能在这个方法里指定当前选定项,需要在onBlur失去焦点方法里重新指定当前选定项
		
		amounts.setValue(BigDecimal.ZERO);
		totalNum.setValue(BigDecimal.ZERO);
		averagePrice.setValue(BigDecimal.ZERO);
		remarks.setValue("");
		pjName.setValue("");
		pjGG.setValue("");
		pkRemarks.setValue("");
		//更新数据源中的数据
	}else{
		Messagebox.show("请选择库存","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
}
	
/**
 * 写入盘库记录
 * @return
 * @throws ParseException 
 */
private String addStockCheck(Stock stock,Map<Object,Object> map) throws ParseException{
	User user = (User)session.getAttribute("user");
	map.put("pkr", user.getUser_name());
	map.put("amountsDz",amounts.getValue().doubleValue());
	map.put("totalNumDz",totalNum.getValue().doubleValue());
	map.put("ageragePriceDz",averagePrice.getValue().doubleValue());
	map.put("remarks",pkRemarks.getValue());
	
	map.put("stock", stock);
	map.put("stockId",stock.getId() );
	map.put("pjid",stock.getPjid() );
	map.put("amounts",stock.getAmounts() );
	map.put("totalNum",stock.getTotalNum() );
	map.put("ageragePrice",stock.getAgeragePrice() );
	map.put("companyId",stock.getCompanyId());
	
	stockCheckService.addStockCheck(map);
	
	
	return "";
}
public void onCreate$stockcheckListbox(){
	System.out.println();
}
/**
 * 失去焦点方法
 */
public void onBlur$stockcheckListbox(){
	//stockcheckListbox.setSelectedIndex(2);
}

/**
 * 列表单击事件
 */
public void onClick$stockcheckListbox(){
	if(stockcheckListbox.getSelectedItem() != null){
		Stock stock = (Stock)stockcheckListbox.getSelectedItem().getValue();
		totalNum.setValue(BigDecimal.valueOf(stock.getTotalNum()));//数量
		amounts.setValue(BigDecimal.valueOf(stock.getAmounts()));//金额
		averagePrice.setValue(String.valueOf(stock.getAgeragePrice()));//平均单价	
		pjName.setValue(stock.getPjname());
		pjGG.setValue(stock.getPjgg());
		pkRemarks.setValue("");
	}
	
}
 /**
  * 清空listbox条件
  */
 public void onClick$clearButton(){
	list_stock.clear();
	amounts.setValue(BigDecimal.ZERO);
	
	totalNum.setValue(BigDecimal.ZERO);
	averagePrice.setValue(BigDecimal.ZERO);
	remarks.setValue("");
	pjName.setValue("");
	pjGG.setValue("");
	pkRemarks.setValue("");
	
	listModelList = new ListModelList(list_stock);
	stockcheckListbox.setModel(listModelList);
	erweima.focus();
    erweima.setValue("");
 }
 
 public void onChange$erweima(){
 	
	 Map map = new HashMap<Object,Object>();
	 User user = (User)session.getAttribute("user");
	 
	 erweima.focus();
	 if(erweima.getValue() != null  && erweima.getValue().length()==10){
		map.put("erweima", erweima.getValue());
		map.put("companyId", user.getCompanyid());
		map.put("begin", 0);
		map.put("end", 1000);
		List<Stock> list_stock_t = stockService.selectStock(map);
		if(list_stock_t ==null || list_stock_t.size()==0){
//				Messagebox.show("当前二维码异常，请检查配件管理模块对应配件的信息","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			if("".equals(remarks.getValue())){
				remarks.setValue("当前二维码"+erweima.getValue()+"无法查询到对应配件信息，请检查配件管理模块对应配件的信息");
			}else{
				remarks.setValue(remarks.getValue()+"\n"+"当前二维码"+erweima.getValue()+"无法查询到对应配件信息，请检查配件管理模块对应配件的信息");
			}
			
			erweima.focus();
	    	erweima.setValue("");
	    	return;
		}
		if(list_stock_t ==null || list_stock_t.size()>1){
//				Messagebox.show("当前二维码异常，存在二维码重复的配件信息，请联系管理员","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			if("".equals(remarks.getValue())){
				remarks.setValue("当前二维码"+erweima.getValue()+"无法查询到对应配件信息，请检查配件管理模块对应配件的信息");
			}else{
				remarks.setValue(remarks.getValue()+"\n"+"当前二维码"+erweima.getValue()+"无法查询到对应配件信息，请检查配件管理模块对应配件的信息");
			}
			remarks.setValue(remarks.getValue()+"/n"+"当前二维码"+erweima.getValue()+"异常，请检查配件管理模块对应配件的信息");
			erweima.focus();
	    	erweima.setValue("");
	    	return;
		}
		boolean exist = false;
		for(int i=0;i<list_stock.size();i++){
			if(list_stock_t.get(0).getId() == list_stock.get(i).getId()){
				exist = true;
				break;
			}
		}
		if(!exist){
			list_stock.add(list_stock_t.get(0));
		}
//			list_stock.addAll(list_stock_t);
 		System.out.println(erweima.getValue());	
 			listModelList = new ListModelList(list_stock);
 			stockcheckListbox.setModel(listModelList);
 		
 		
    	erweima.focus();
    	erweima.setValue("");
 	}else{//长度不到10，直接清空
 		erweima.focus();
    	erweima.setValue("");
    	return;
 	}
 }

 public void onChanging$erweima(){
//	 	System.out.println(pjName.getValue()+"!!!!!");
//	 	pjName.invalidate();
	 remarks.focus();
 }
}