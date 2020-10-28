package com.pjgl.stockUse.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.instock.bean.InStock;
import com.pjgl.stockUse.bean.StockUse;
import com.pjgl.stockUse.dao.StockUseDao;

@Service("stockUseService")
@Scope("prototype") 
public class StockUseServiceImpl implements StockUseService {

	@Autowired
	private StockUseDao stockUseDao;
	@Override
	public int addStockUse(Map<Object, Object> map) {
		return stockUseDao.addStockUse(map);
	}

	@Override
	public List<StockUse> selectStockUse(Map<Object, Object> map) {
		return stockUseDao.selectStockUse(map);
	}

	@Override
	public Double selectAmounts(Map map) {
		return stockUseDao.selectAmounts(map);
	}

	@Override
	public int selectStockUse_count(Map map) {
		return stockUseDao.selectStockUse_count(map);
	}

	@Override
	public List<StockUse> selectStockUseDetails(Map map) {
		return stockUseDao.selectStockUseDetails(map);
	}

	@Override
	public Double selectAmountsDetails(Map map) {
		return stockUseDao.selectAmountsDetails(map);
	}

	@Override
	public int selectStockUseDetails_count(Map map) {
		return stockUseDao.selectStockUseDetails_count(map);
	}

	@Override
	public List<StockUse> selectAmountsByStock(Map<Object, Object> stockMap) {
		return stockUseDao.selectAmountsByStock(stockMap);
	}

}
