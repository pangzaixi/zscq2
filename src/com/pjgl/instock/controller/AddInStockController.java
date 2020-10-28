package com.pjgl.instock.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.google.protobuf.Internal.DoubleList;
import com.pjgl.instock.service.serviceimpl.InStockService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.controller.StockController;
import com.pjgl.stock.service.StockService;
import com.yewu.zscq.bean.User;

@Controller("addInStockController")   
@Scope("prototype") 
public class AddInStockController extends GenericForwardComposer{

	Textbox name; // '配件名称',
	Textbox spec; // '规格',
	Textbox unit; //'单位，件、个、只、公斤等',
	Doublebox totalNum;
	Doublebox amounts;
	Doublebox averagePrice;
	Datebox createDate;
	Textbox remarks;
	Radio type1;
	Radio type2;
	
	Window addInStockWin;
	Pj pj;
	
	InStockService inStockService = (InStockService)SpringUtil.getBean("inStockService");
	StockService stockService = (StockService)SpringUtil.getBean("stockService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		pj = (Pj)Executions.getCurrent().getArg().get("pj");
		if (pj != null) {
			name.setValue(pj.getName());
			spec.setValue(pj.getSpec());
			unit.setValue(pj.getUnit());
		}
		createDate.setValue(new Date());
		type1.setSelected(true);
	}
	
	/**
	 * 增加入库记录
	 */
	public void onClick$addBtn() throws ParseException{
		Map<Object,Object> map = new HashMap<>();
		User user = (User)session.getAttribute("user");
		
		map.put("pjid", pj.getId());
		map.put("totalNum", totalNum.getValue());
		map.put("amounts", amounts.getValue());
		map.put("averagePrice", averagePrice.getValue());
		map.put("companyid", user.getCompanyid());
		map.put("creator", user.getId());
		map.put("createDate", createDate.getValue());
		if (type1.isSelected()) {
			map.put("type", type1.getValue());
		}else {
			map.put("type", type2.getValue());
		}
		map.put("remarks", remarks.getValue());
		
		int result = inStockService.insertInstock(map);
		
		synchronized(this) {
			Map<Object, Object> stockMap = new HashMap<>();
			stockMap.put("pjid", pj.getId());
			stockMap.put("companyId", user.getCompanyid());
			Stock stock = stockService.getByPj(stockMap);
			if (stock != null) {
				stockMap.put("amounts", stock.getAmounts() + amounts.getValue());
				stockMap.put("totalNum", stock.getTotalNum() + totalNum.getValue());
				Double d = (stock.getAmounts() + amounts.getValue()) / (stock.getTotalNum() + totalNum.getValue());
				stockMap.put("ageragePrice", d);
				stockMap.put("id", stock.getId());
				stockService.editStock(stockMap);
			}else {
				stockMap.put("amounts", amounts.getValue());
				stockMap.put("totalNum",totalNum.getValue());
				stockMap.put("ageragePrice", averagePrice.getValue());
				stockMap.put("pjid", pj.getId());
				stockMap.put("companyId", user.getCompanyid());
				stockService.insertStock(stockMap);
			}
			System.out.println();
			addInStockWin.detach();
			StockController stockController = (StockController)session.getAttribute("stockController");
			stockController.onClick$queryButton();
			
			Messagebox.show("入库成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	}
}
