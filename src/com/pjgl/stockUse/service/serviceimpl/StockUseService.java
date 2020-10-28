package com.pjgl.stockUse.service.serviceimpl;

import java.util.List;
import java.util.Map;

import com.pjgl.instock.bean.InStock;
import com.pjgl.stockUse.bean.StockUse;

public interface StockUseService {

	public int addStockUse(Map<Object,Object> map);//新增领用记录
	public List<StockUse> selectStockUse(Map<Object,Object> map);//查询领用记录 
	public Double selectAmounts(Map map);
	public int selectStockUse_count(Map map);
	public List<StockUse> selectStockUseDetails(Map map);
	public Double selectAmountsDetails(Map map);
	public int selectStockUseDetails_count(Map map);
	public List<StockUse> selectAmountsByStock(Map<Object, Object> stockMap);
}
