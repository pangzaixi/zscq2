package com.pjgl.stockUse.controller;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.instock.bean.InStock;
import com.pjgl.stockUse.bean.StockUse;
import com.pjgl.stockUse.service.serviceimpl.StockUseService;
import com.yewu.zscq.bean.User;

@Controller("outStockController")   
@Scope("prototype")
public class OutStockController extends GenericForwardComposer{

	Boolean pagingOnClick = false;
	Datebox startDate;
	Datebox endDate;
	Textbox name;
	Combobox company;
	Textbox plateNum;
	Textbox driver;
	
	StockUseService outStockService = (StockUseService)SpringUtil.getBean("stockUseService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	ListModel companyModel;//公司下拉选数据源
	String pjid;
	String companyid;
	Date date1;
	Date date2;
	String plate;
	String driverName;
	
	Grid outstockbox;
	Grid detailsbox;
	Paging paging;
	Paging pagingDetails;
	private Footer footercategory;
	private Footer footerDetails;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		session.setAttribute("outStockController", this.getController());
		if (footercategory != null) {
			footercategory.setLabel("领用金额：0 元");
			Calendar cale = Calendar.getInstance();  
	        cale.add(Calendar.MONTH, 0);  
	        cale.set(Calendar.DAY_OF_YEAR, 1);
	        // 时
	        cale.set(Calendar.HOUR_OF_DAY, 0);
	    	// 分
	        cale.set(Calendar.MINUTE, 0);
	    	// 秒
	        cale.set(Calendar.SECOND, 0);
	        cale.set(Calendar.MILLISECOND, 0);

			startDate.setValue(cale.getTime());
			endDate.setValue(new Date());
			
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
//				company.setSelectedItem(companyModel.getElementAt(arg0));
			}else{
				company.setSelectedText(1, 1, "总公司", true);
			}
		}
		if (footerDetails != null) {
			footerDetails.setLabel("领用金额：0元");
			pjid = (String)Executions.getCurrent().getArg().get("pjid");
			companyid = (String)Executions.getCurrent().getArg().get("companyid");
			date1 = (Date)Executions.getCurrent().getArg().get("date1");
			date2 = (Date)Executions.getCurrent().getArg().get("date2");
			
			Calendar calendar = new GregorianCalendar();
    		calendar.setTime(date2); 
    		calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
    		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
	    	calendar.set(Calendar.MINUTE, 0);// 分
	    	calendar.set(Calendar.SECOND, 0);// 秒
    		calendar.set(Calendar.MILLISECOND, 0);//毫秒
    		date2 = calendar.getTime();
    		
			plate = (String)Executions.getCurrent().getArg().get("plateNum");
			driverName = (String)Executions.getCurrent().getArg().get("driver");
			onClick$queryDetailsButton();
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
		
		if (startDate.getValue() != null) {
			map.put("startDate", startDate.getValue());
		}
		if (endDate.getValue() != null) {
			Calendar calendar = new GregorianCalendar();
			Date date = endDate.getValue();
			calendar.setTime(date); 
			calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			map.put("endDate", f.format(date));
		}
		if (name.getValue() != null && !name.getValue().trim().equals("")) {
			map.put("name", name.getValue());
		}
		User user = (User) session.getAttribute("user");
		if("1".equals(user.getLevel())){//总公司，取公司列表中选择的项
			map.put("companyid", company.getSelectedItem().getContext());
		}else{//分公司，取当前登录人所属公司
			map.put("companyid", user.getCompanyid());
		}
		map.put("plateNum", plateNum.getValue());
		map.put("driver", driver.getValue());
		if(pagingOnClick == true){
			pagingOnClick = false;
			map.put("begin", paging.getPageSize()*paging.getActivePage());
			map.put("end", paging.getPageSize());
		}else{
			map.put("begin", 0);
			map.put("end", paging.getPageSize());
			paging.setActivePage(0);
		}
		
		List<StockUse> outStocks = outStockService.selectStockUse(map);
		ListModel listModel = null;
		if (outStocks != null && outStocks.size() > 0) {
			listModel = new ListModelList(outStocks);
			Double amounts  = outStockService.selectAmounts(map);
			footercategory.setLabel("领用金额： "+amounts+" 元");

		}else{
			footercategory.setLabel("领用金额：0 元");
		}
		outstockbox.setModel(listModel);
		
		//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		paging.setTotalSize(outStockService.selectStockUse_count(map));
//		paging.setTotalSize(10);
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
		name.setValue("");	
		Calendar cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_YEAR, 1);
        // 时
        cale.set(Calendar.HOUR_OF_DAY, 0);
    	// 分
        cale.set(Calendar.MINUTE, 0);
    	// 秒
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);

		startDate.setValue(cale.getTime());
		
		
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date()); 
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
    	calendar.set(Calendar.MINUTE, 0);// 分
    	calendar.set(Calendar.SECOND, 0);// 秒
		calendar.set(Calendar.MILLISECOND, 0);//毫秒
		
		endDate.setValue(calendar.getTime());
		
		
		plateNum.setValue("");
		driver.setValue("");
		company.setSelectedIndex(0);
	}
	
	public void onClick$queryDetailsButton() throws ParseException{
		Map map = new HashMap<Object,Object>();
		System.out.println();
		map.put("startDate", date1);
		map.put("endDate", date2);
		map.put("pjid", pjid);
		User user = (User) session.getAttribute("user");
		map.put("companyid", companyid);
		map.put("plateNum", plate);
		map.put("driver", driverName);
		map.put("begin", pagingDetails.getPageSize()*pagingDetails.getActivePage());
		map.put("end", pagingDetails.getPageSize());
		
		List<StockUse> outStocks = outStockService.selectStockUseDetails(map);
		ListModel listModel = null;
		if (outStocks != null && outStocks.size() > 0) {
			listModel = new ListModelList(outStocks);
			Double amounts  = outStockService.selectAmountsDetails(map);
			footerDetails.setLabel("领用金额： "+amounts+" 元");

		}else{
			footerDetails.setLabel("领用金额：0 元");
		}
		detailsbox.setModel(listModel);
		
		//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		pagingDetails.setTotalSize(outStockService.selectStockUseDetails_count(map));
//		paging.setTotalSize(10);
	}
	
	/**
	 * 分页标签
	 * @throws ParseException 
	 */
	public void onClick$pagingDetails() throws ParseException{
		int activepage = pagingDetails.getActivePage();
		 
		 onClick$queryDetailsButton();
		 if(activepage<=pagingDetails.getPageCount()-1){
			 pagingDetails.setActivePage(activepage);	 
		 }else{
			 pagingDetails.setActivePage(0);
		 }
		 
	}
}
