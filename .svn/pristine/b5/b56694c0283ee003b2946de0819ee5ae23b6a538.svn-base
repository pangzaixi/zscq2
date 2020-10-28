package com.pjgl.sockCheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.sockCheck.bean.StockCheck;
import com.pjgl.sockCheck.dao.StockCheckDao;
import com.pjgl.stock.dao.StockDao;
import com.pjgl.stock.service.StockService;

@Service("stockCheckService")
@Scope("prototype") 
public class StockCheckServiceImpl implements StockCheckService {

	@Autowired
	private StockCheckDao stockCheckDao;
	
	@Override
	public int addStockCheck(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockCheckDao.addStockCheck(map);
	}
	@Override
	public List<StockCheck> selectStockCheck(Map<Object,Object> map){//查询盘库记录
		return stockCheckDao.selectStockCheck(map);
	}
}
