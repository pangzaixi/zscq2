package com.pjgl.stock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jcp.xml.dsig.internal.MacOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.instock.bean.InStock;
import com.pjgl.instock.service.serviceimpl.InStockService;
import com.pjgl.stock.service.StockService;
import com.pjgl.stockUse.bean.StockUse;
import com.pjgl.stockUse.service.serviceimpl.StockUseService;
import com.yewu.zscq.bean.User;

@Controller
public class Charts {

	@Autowired
	private InStockService inStockService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StockUseService stockUseService;
	
	
	@RequestMapping("/queryAmountsByZcg.do")
	public @ResponseBody Map<Object, Object> queryAmountsByZcg(HttpServletRequest request, @RequestParam Date date1, @RequestParam Date date2) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		Map<Object, Object> companyMap = new HashMap<Object, Object>();
		companyMap.put("begin", 0);
		companyMap.put("end", 1000);
		List<Company> companyList = companyService.selectCompany(companyMap);
		
		Map<Object, Object> instockMap = new HashMap<Object, Object>();
		instockMap.put("type", "自采购");
		instockMap.put("date1", date1);
		instockMap.put("date2", date2);
		Double amountsTotal = inStockService.selectAmounts(instockMap);
		List<String> nameList = new ArrayList<String>();
		List<Double> amountsList = new ArrayList<>();
		for (Company company : companyList) {
			nameList.add(company.getCompanyName());
			Map<Object, Object> stockMap = new HashMap<Object, Object>();
			stockMap.put("companyid", company.getId());
			stockMap.put("type", "自采购");
			stockMap.put("date1", date1);
			stockMap.put("date2", date2);
			Double amounts = inStockService.selectAmounts(stockMap);
			if (amounts != null) {
				amountsList.add(amounts);
			}else{
				amountsList.add(0.00);
			}
		}
		if (amountsTotal != null) {
			map.put("amountsTotal", amountsTotal);
		}else {
			map.put("amountsTotal", 0.00);
		}
//		map.put("amountsTotal", amountsTotal);
		map.put("nameList", nameList);
		map.put("amountsList", amountsList);
		return map;
	}
	
	@RequestMapping("/queryAmounts.do")
	public @ResponseBody Map<Object, Object> queryAmounts(HttpServletRequest request, @RequestParam Date date1, @RequestParam Date date2) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		Map<Object, Object> companyMap = new HashMap<Object, Object>();
		Double amountsTotal = stockService.selectAmounts(companyMap);
		companyMap.put("begin", 0);
		companyMap.put("end", 1000);
		List<Company> companyList = companyService.selectCompany(companyMap);
		List<String> nameList = new ArrayList<String>();
		List<Double> amountsList = new ArrayList<>();
		for (Company company : companyList) {
			nameList.add(company.getCompanyName());
			Map<Object, Object> stockMap = new HashMap<Object, Object>();
			stockMap.put("companyId", company.getId());
//			stockMap.put("type", "自采购");
			Double amounts = stockService.selectAmounts(stockMap);
			if (amounts != null) {
				amountsList.add(amounts);
			}else{
				amountsList.add(0.00);
			}
		}
		map.put("amountsTotal", amountsTotal);
		map.put("nameList", nameList);
		map.put("amountsList", amountsList);
		return map;
	}
	
	@RequestMapping("/queryInStock.do")
	public @ResponseBody Map<Object, Object> queryInStock(HttpServletRequest request, @RequestParam Date date1, @RequestParam Date date2) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		User user = (User)request.getSession().getAttribute("user");
		
		List<Company> companyList = new ArrayList<>();
		List<String> nameList = new ArrayList<String>();
		List<String> monthList = new ArrayList<>();
		monthList = getMonthList(date1, date2);
		List<Object> amountsList = new ArrayList<>();
//		for (int mon : months) {
//			monthList.add(mon + "月");
//		}
		
		if ("1".equals(user.getLevel())) {
			Map<Object, Object> companyMap = new HashMap<Object, Object>();
			companyMap.put("begin", 0);
			companyMap.put("end", 1000);
			companyList = companyService.selectCompany(companyMap);
			for (Company company : companyList) {
				nameList.add(company.getCompanyName());
				Map<Object, Object> stockMap = new HashMap<Object, Object>();
				stockMap.put("companyid", company.getId());
				stockMap.put("date1", date1);
				stockMap.put("date2", date2);
				List<InStock> stocks = inStockService.selectAmountsByStock(stockMap);
				List<Double> amountss = new ArrayList<>();
				for (String i : monthList) {
					Double amounts = 0.00;
					for (InStock inStock : stocks) {
						if (i.equals(inStock.getMonth())) {
							amounts = inStock.getAmounts();
							if (amounts == null) {
								amounts = 0.00;
							}
							break;
						}
					}
					amountss.add(amounts);
				}
				amountsList.add(amountss);
			}
		}else{
			Map<Object, Object> companyMap = new HashMap<Object, Object>();
			companyMap.put("begin", 0);
			companyMap.put("end", 1000);
			companyMap.put("id", user.getCompanyid());
			companyList = companyService.selectCompany(companyMap);
			for (Company company : companyList) {
				nameList.add(company.getCompanyName());
			}
			Map<Object, Object> stockMap = new HashMap<Object, Object>();
			stockMap.put("companyid", user.getCompanyid());
			stockMap.put("date1", date1);
			stockMap.put("date2", date2);
			List<InStock> stocks = inStockService.selectAmountsByStock(stockMap);
			for (String i : monthList) {
				Double amounts = 0.00;
				for (InStock inStock : stocks) {
					if (i.equals(inStock.getMonth())) {
						amounts = inStock.getAmounts();
						if (amounts == null) {
							amounts = 0.00;
						}
						break;
					}
				}
				amountsList.add(amounts);
			}
		}
		
		map.put("monthList", monthList);
		map.put("amountsList", amountsList);
		map.put("nameList", nameList);
		map.put("level", user.getLevel());
		return map;
	}
	
	@RequestMapping("/queryOutStock.do")
	public @ResponseBody Map<Object, Object> queryOutStock(HttpServletRequest request, @RequestParam Date date1, @RequestParam Date date2) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		User user = (User)request.getSession().getAttribute("user");
		
		List<Company> companyList = new ArrayList<>();
		List<String> nameList = new ArrayList<String>();
		List<String> monthList = new ArrayList<>();
		monthList = getMonthList(date1, date2);
		List<Object> amountsList = new ArrayList<>();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//		String s = format.format(date2);
//		String t = format.format(date1);
//		int month1 = Integer.valueOf(s);
//		int month2 = Integer.valueOf(t);
//		for (String mon : months) {
//			monthList.add(mon + "月");
//		}
		
		if ("1".equals(user.getLevel())) {
			Map<Object, Object> companyMap = new HashMap<Object, Object>();
			companyMap.put("begin", 0);
			companyMap.put("end", 1000);
			companyList = companyService.selectCompany(companyMap);
			for (Company company : companyList) {
				nameList.add(company.getCompanyName());
				Map<Object, Object> stockMap = new HashMap<Object, Object>();
				stockMap.put("companyid", company.getId());
				stockMap.put("date1", date1);
				stockMap.put("date2", date2);
				List<StockUse> stocks = stockUseService.selectAmountsByStock(stockMap);
				List<Double> amountss = new ArrayList<>();
				for (String i : monthList) {
					Double amounts = 0.00;
					for (StockUse stockUse : stocks) {
						if (i.equals(stockUse.getMonth())) {
							amounts = stockUse.getAmounts();
							if (amounts == null) {
								amounts = 0.00;
							}
							break;
						}
					}
					amountss.add(amounts);
				}
				amountsList.add(amountss);
			}
		}else{
			Map<Object, Object> companyMap = new HashMap<Object, Object>();
			companyMap.put("begin", 0);
			companyMap.put("end", 1000);
			companyMap.put("id", user.getCompanyid());
			companyList = companyService.selectCompany(companyMap);
			for (Company company : companyList) {
				nameList.add(company.getCompanyName());
			}
			Map<Object, Object> stockMap = new HashMap<Object, Object>();
			stockMap.put("companyid", user.getCompanyid());
			stockMap.put("date1", date1);
			stockMap.put("date2", date2);
			List<StockUse> stocks = stockUseService.selectAmountsByStock(stockMap);
			for (String i : monthList) {
				Double amounts = 0.00;
				for (StockUse stockUse : stocks) {
					if (i.equals(stockUse.getMonth())) {
						amounts = stockUse.getAmounts();
						if (amounts == null) {
							amounts = 0.00;
						}
						break;
					}
				}
				amountsList.add(amounts);
			}
		}
		
		map.put("monthList", monthList);
		map.put("amountsList", amountsList);
		map.put("nameList", nameList);
		map.put("level", user.getLevel());
		return map;
	}

	private List<String> getMonthList(Date date1, Date date2) {
		// TODO Auto-generated method stub
		
		Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        List<String> months = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        min.setTime(date1);
        max.setTime(date2);
        while(min.before(max)){
        	months.add(format.format(min.getTime()));//添加到集合中去
            min.add(Calendar.MONTH, 1);//加一个月
        }
//        months.add(max.get(Calendar.MONTH) + 1);
		return months;
	}
}
