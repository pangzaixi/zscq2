package com.pjgl.instock.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.instock.bean.InStock;
import com.pjgl.instock.service.serviceimpl.InStockService;
import com.yewu.zscq.bean.User;


/**
 * 入库统计
 * @author thinker
 *
 */
@Controller("inStockController")   
@Scope("prototype")
public class InStockController extends GenericForwardComposer{

	Boolean pagingOnClick = false;
	Datebox startDate;
	Datebox endDate;
	Textbox name;
	Combobox company;
	Radio type1;
	Radio type2;
	
	InStockService inStockService = (InStockService)SpringUtil.getBean("inStockService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	ListModel companyModel;//公司下拉选数据源
	String pjid;
	String companyid;
	Date date1;
	Date date2;
	String type;
	
	Grid instockbox;
	Grid detailsbox;
	Paging paging;
	Paging pagingDetails;
	private Footer footercategory;
	private Footer footerDetails;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		session.setAttribute("inStockController", this.getController());
		if (footercategory != null) {
			footercategory.setLabel("入库金额：0 元");
			Calendar cale = Calendar.getInstance();  
	        cale.add(Calendar.MONTH, 0);  
	        cale.set(Calendar.DAY_OF_YEAR, 1);
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
			footerDetails.setLabel("入库金额：0元");
			pjid = (String)Executions.getCurrent().getArg().get("pjid");
			companyid = (String)Executions.getCurrent().getArg().get("companyid");
			date1 = (Date)Executions.getCurrent().getArg().get("date1");
			date2 = (Date)Executions.getCurrent().getArg().get("date2");
			type = (String)Executions.getCurrent().getArg().get("type");
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
			map.put("endDate", endDate.getValue());
		}
		if (name.getValue() != null && !name.getValue().trim().equals("")) {
			map.put("name", name.getValue());
		}
		if (type1.isSelected()) {
			map.put("type", type1.getValue());
		}
		if (type2.isSelected()) {
			map.put("type", type2.getValue());
		}
		User user = (User) session.getAttribute("user");
		if("1".equals(user.getLevel())){//总公司，取公司列表中选择的项
			map.put("companyid", company.getSelectedItem().getContext());
		}else{//分公司，取当前登录人所属公司
			map.put("companyid", user.getCompanyid());
		}
		if(pagingOnClick == true){
			pagingOnClick = false;
			map.put("begin", paging.getPageSize()*paging.getActivePage());
			map.put("end", paging.getPageSize());
		}else{
			map.put("begin", 0);
			map.put("end", paging.getPageSize());
			paging.setActivePage(0);
		}
		
		List<InStock> inStocks = inStockService.selectInstock(map);
		ListModel listModel = null;
		if (inStocks != null && inStocks.size() > 0) {
			listModel = new ListModelList(inStocks);
			Double amounts  = inStockService.selectAmounts(map);
			footercategory.setLabel("入库金额： "+amounts+" 元");

		}else{
			footercategory.setLabel("入库金额：0 元");
		}
		instockbox.setModel(listModel);
		
		//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		paging.setTotalSize(inStockService.selectInstock_count(map));
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
		startDate.setValue(cale.getTime());
		endDate.setValue(new Date());
		type1.setSelected(false);
		type2.setSelected(false);
		company.setSelectedIndex(0);
	}
	
	public void onClick$queryDetailsButton() throws ParseException{
		Map map = new HashMap<Object,Object>();
		
		map.put("startDate", date1);
		map.put("endDate", date2);
		map.put("pjid", pjid);
		User user = (User) session.getAttribute("user");
		map.put("companyid", companyid);
		map.put("begin", pagingDetails.getPageSize()*pagingDetails.getActivePage());
		map.put("end", pagingDetails.getPageSize());
		map.put("type", type);
		
		List<InStock> inStocks = inStockService.selectInstockDetails(map);
		ListModel listModel = null;
		if (inStocks != null && inStocks.size() > 0) {
			listModel = new ListModelList(inStocks);
			Double amounts  = inStockService.selectAmountsDetails(map);
			footerDetails.setLabel("入库金额： "+amounts+" 元");

		}else{
			footerDetails.setLabel("入库金额：0 元");
		}
		detailsbox.setModel(listModel);
		
		//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		pagingDetails.setTotalSize(inStockService.selectInstockDetails_count(map));
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
