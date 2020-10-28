package com.pjgl.stock.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.sockCheck.service.StockCheckService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.service.StockService;
import com.yewu.zscq.bean.User;

/**
 * 编辑库存，手动方式盘库
 * @author thinker
 *
 */
@Controller("editStockController")   
@Scope("prototype") 
public class EditStockController  extends GenericForwardComposer{
	Window editStockWin;
	Textbox pjname;//配件名称
	Textbox  pjgg;//配件规格
	Textbox  pjdw;//配件单位
	Decimalbox amounts;// '库存金额',
	Decimalbox totalNum;// '总数量',
	Decimalbox ageragePrice;// '平均单价',
	Textbox remarks;//盘库备注
	
	
	
	Stock stock =  new Stock();
	//获取盘库service对象
	StockCheckService stockCheckService = (StockCheckService)SpringUtil.getBean("stockCheckService");
	//获取库存service对象
	StockService stockService = (StockService)SpringUtil.getBean("stockService");	
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1 = (Map<String, Object>)Executions.getCurrent().getArg();//获 的map参数，从map中获得参数对象
	stock = (Stock)arg1.get("stock");
	System.out.println();
	pjname.setValue(stock.getPjname());
	pjgg.setValue(stock.getPjgg());
	pjdw.setValue(stock.getPjdw());
	amounts.setValue(BigDecimal.valueOf(stock.getAmounts()));
	totalNum.setValue(BigDecimal.valueOf(stock.getTotalNum()));
	ageragePrice.setValue(BigDecimal.valueOf(stock.getAgeragePrice()));
	
}	
	
/**
 * 保存盘库
 * @throws ParseException 
 */
public void onClick$editBtn() throws ParseException{
	
	Map<Object,Object> map = new HashMap<Object,Object>();
	map.put("stock", stock);
	map.put("stockId",stock.getId() );
	map.put("pjid",stock.getPjid() );
	map.put("amounts",stock.getAmounts() );
	map.put("totalNum",stock.getTotalNum() );
	map.put("ageragePrice",stock.getAgeragePrice() );
	map.put("companyId",stock.getCompanyId());
	
	User user = (User)session.getAttribute("user");
	map.put("pkr", user.getUser_name());
	map.put("amountsDz",amounts.getValue().doubleValue());
	map.put("totalNumDz",totalNum.getValue().doubleValue());
	map.put("ageragePriceDz",ageragePrice.getValue().doubleValue());
	map.put("remarks",remarks.getValue());
	
	stockCheckService.addStockCheck(map);
	
	
	map.clear();
	map.put("id", stock.getId());
	map.put("totalNum",totalNum.getValue().doubleValue());
	map.put("amounts",amounts.getValue().doubleValue());
	map.put("ageragePrice",ageragePrice.getValue().doubleValue());
	
	stockService.editStock(map);
	Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	editStockWin.detach();
	//刷新父窗口
	StockController stockController = (StockController)session.getAttribute("stockController");
	stockController.onClick$queryButton();
}
}
