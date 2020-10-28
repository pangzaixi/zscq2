package com.pjgl.stock.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.stock.bean.Stock;
import com.pjgl.stock.dao.StockDao;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.dao.JxqdDao;


@Service("stockService")
@Scope("prototype") 
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;
	
	@Override
	public List<Stock> selectStock(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockDao.selectStock(map);
	}

	@Override
	public int selectStock_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockDao.selectStock_count(map);
	}

	@Override
	public Double selectAmounts(Map<Object,Object> map){//获得总库存金额
		return stockDao.selectAmounts(map);
	}
	
	@Override
	public int insertStock(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockDao.insertStock(map);
	}

	@Override
	public int editStock(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockDao.editStock(map);
	}

	@Override
	public Stock getByPj(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockDao.getByPj(map);
	}
	@Override
	public String selectOrgMapping(String ordId){//根据合作社平台传来的ORGID查询对应本系统的公司ID，用来查询库存
		return stockDao.selectOrgMapping(ordId);
	}
	@Override
	public List<Stock> selectStockForHZS(Map<Object,Object> map){//获得库存列表,用于合作社平台
		return stockDao.selectStockForHZS(map);
	}
}
