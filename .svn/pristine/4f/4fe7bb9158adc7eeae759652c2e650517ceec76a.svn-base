package com.pjgl.stockUse.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.car.bean.Car;
import com.pjgl.car.service.serviceimpl.CarService;
import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.controller.StockController;
import com.pjgl.stock.service.StockService;
import com.pjgl.stockUse.service.serviceimpl.StockUseService;
import com.yewu.zscq.bean.User;

@Controller("useStockController")
@Scope("prototype")
public class AddStockUseController extends GenericForwardComposer{

	Window useStockWin;
	
	//页面查询条件
	Textbox remarks;//备注区域，用于显示提示信息
	Decimalbox totalNum;//数量
	Decimalbox amounts;//金额
	Decimalbox averagePrice;//平均单价
	Textbox pjName;
	Textbox pjGG;
	Textbox pjdw;
	Combobox car;
	Textbox driver;
	Textbox mobile;
	Textbox plateNum;
	Decimalbox totalNumContrast;
	
	Stock stock;
	
	StockService stockService = (StockService)SpringUtil.getBean("stockService");
	//获取盘库service对象
	StockUseService stockUseService = (StockUseService)SpringUtil.getBean("stockUseService");
	CarService carService = (CarService)SpringUtil.getBean("carService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		stock = (Stock)Executions.getCurrent().getArg().get("stock");
		totalNum.setValue(BigDecimal.valueOf(stock.getTotalNum()));//数量
		amounts.setValue(BigDecimal.valueOf(0));//金额
		averagePrice.setValue(BigDecimal.valueOf(stock.getAgeragePrice()));//平均单价	
		pjName.setValue(stock.getPjname());
		pjGG.setValue(stock.getPjgg());
		pjdw.setValue(stock.getPjdw());
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		User user = (User)session.getAttribute("user");
		map.put("begin", 0);
		map.put("end", 1000);
		map.put("companyid", user.getCompanyid());
		List<Car> list = carService.selectCar(map);
		ListModel carModel = new ListModelList(list);
		car.setModel(carModel);
	}
	
	public void onClick$editButton() throws ParseException{
		if (car.getSelectedItem() != null || (driver.getValue() != null && !driver.getValue().trim().equals(""))) {
			User user = (User)session.getAttribute("user");
			Map<Object, Object> stockMap = new HashMap<Object, Object>();
			stockMap.put("pjid", stock.getPjid());
			stockMap.put("companyId", user.getCompanyid());
			synchronized (this) {
				Stock stock2 = stockService.getByPj(stockMap);
				if (stock2.getTotalNum() >= totalNumContrast.getValue().doubleValue()) {
					Double d = stock2.getTotalNum() - totalNumContrast.getValue().doubleValue();
					addStockUse(stock2);
					Map<Object, Object> map = new HashMap<Object, Object>();
					if (totalNumContrast.getValue().doubleValue() != 0) {
						map.put("id", stock2.getId());
						map.put("totalNum",stock2.getTotalNum() - totalNumContrast.getValue().doubleValue());
						if (d == 0) {
							map.put("ageragePrice",0);
							map.put("amounts",0);
						}else {
							map.put("ageragePrice",stock2.getAgeragePrice());
							map.put("amounts",stock2.getAmounts() - totalNumContrast.getValue().doubleValue() * stock2.getAgeragePrice());
						}
					    stockService.editStock(map);
					}
					useStockWin.detach();
					StockController stockController = (StockController)session.getAttribute("stockController");
					stockController.onClick$queryButton();
					Messagebox.show("领用成功，领取" + pjName.getValue() + totalNumContrast.getValue() + pjdw.getValue() +
							";" + "库存剩余" + d,"注意", Messagebox.OK, Messagebox.EXCLAMATION);
				}else{
					Messagebox.show("库存不足","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		}else{
			Messagebox.show("请选择车辆或填写领用人","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	private void addStockUse(Stock stock){
		Map<Object, Object> map = new HashMap<Object, Object>();
		User user = (User)session.getAttribute("user");
		map.put("creator", user.getUser_name());
		map.put("amountsDz",stock.getAgeragePrice() * totalNumContrast.getValue().doubleValue());
		map.put("totalNumDz",totalNumContrast.getValue().doubleValue());
		map.put("ageragePriceDz",stock.getAgeragePrice());
		map.put("remarks",remarks.getValue());
		
		map.put("stock", stock);
		map.put("stockId",stock.getId() );
		map.put("pjid",stock.getPjid() );
		map.put("amounts",stock.getAmounts() );
		map.put("totalNum",stock.getTotalNum() );
		map.put("ageragePrice",stock.getAgeragePrice() );
		map.put("companyId",user.getCompanyid());
		if (car.getSelectedItem() != null) {
			map.put("plateNum", car.getSelectedItem().getLabel());
		}
		map.put("driver", driver.getValue());
		map.put("mobile", mobile.getValue());
		
		stockUseService.addStockUse(map);
	}
	
	public void onSelect$car(){
		 mobile.setValue(car.getSelectedItem().getContext());
		 driver.setValue(car.getSelectedItem().getValue());
	 }
}
