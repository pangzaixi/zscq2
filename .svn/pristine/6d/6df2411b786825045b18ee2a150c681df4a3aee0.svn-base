package com.pjgl.stockQuery.controller;

import java.text.ParseException;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.stockQuery.bean.StockQuery;
import com.pjgl.stockQuery.service.StockQueryService;
import com.yewu.zscq.bean.User;

/**
 * 盘库查询
 * @author thinker
 *
 */
@Controller("stockQueryController")   
@Scope("prototype")
public class StockQuerykController extends GenericForwardComposer{
	Boolean pagingOnClick = false;//是否点击的分页标签
	Window stockQuerywin;
	Textbox pjName;//配件名称
	Datebox startDate;
	Datebox endDate;
	Combobox company;
	
	//获取库存service对象
	StockQueryService stockQueryService = (StockQueryService)SpringUtil.getBean("stockQueryService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	ListModel companyModel;//公司下拉选数据源
	String pjid;
	String companyid;	
	Grid stockQuerybox;
	Paging paging;
	private Footer footercategory;
	Calendar cale = Calendar.getInstance();  
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		session.setAttribute("stockQueryController", this.getController());
		if (footercategory != null) {
//			footercategory.setLabel("入库金额：0 元");
			
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
			}else{
				company.setSelectedText(1, 1, "总公司", true);
			}
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
		if(pjName.getValue() != null && !"".equals(pjName.getValue().trim())){
			System.out.println("pjName="+pjName.getValue());
			map.put("pjName", pjName.getValue().trim());
		}

		User user = (User) session.getAttribute("user");
		if("1".equals(user.getLevel())){//总公司，取公司列表中选择的项
			map.put("companyId", company.getSelectedItem().getContext());
		}else{//分公司，取当前登录人所属公司
			map.put("companyId", user.getCompanyid());
		}
//		map.put("begin", paging.getPageSize()*paging.getActivePage());
//		map.put("end", paging.getPageSize());
		if(pagingOnClick == true){
			pagingOnClick = false;
			map.put("begin", paging.getPageSize()*paging.getActivePage());
			map.put("end", paging.getPageSize());
		}else{
			paging.setActivePage(0);
			map.put("begin", 0);
			map.put("end", paging.getPageSize());
		}
		
		List<StockQuery> list = stockQueryService.selectStockQuery(map);
		ListModel listModel = null;
		if(list!= null && list.size()>0 ){
			listModel = new ListModelList(list);
		}		
		stockQuerybox.setModel(listModel);

		//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		paging.setTotalSize(stockQueryService.selectStock_count(map));
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
		pjName.setValue("");
		startDate.setValue(cale.getTime());
		endDate.setValue(new Date());
		company.setSelectedIndex(0);
		
	}
	
}
