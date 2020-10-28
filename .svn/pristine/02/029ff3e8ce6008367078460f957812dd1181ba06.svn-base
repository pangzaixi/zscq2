package com.pjgl.instock.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.instock.bean.InStock;
import com.pjgl.instock.dao.InStockDao;


@Service("inStockService")
@Scope("prototype")
public class InStockServiceImpl implements InStockService {

	@Autowired
	private InStockDao inStockDao;
	
	@Override
	public List<InStock> selectInstock(Map<Object, Object> map) {
		return inStockDao.selectInstock(map);
	}

	@Override
	public int selectInstock_count(Map<Object, Object> map) {
		return inStockDao.selectInstock_count(map);
	}

	@Override
	public int insertInstock(Map<Object, Object> map) {
		return inStockDao.insertInstock(map);
	}

	@Override
	public int editInstock(Map<Object, Object> map) {
		return inStockDao.editInstock(map);
	}

	@Override
	public int deleteInstock(int id) {
		return inStockDao.deleteInstock(id);
	}

	@Override
	public Double selectAmounts(Map map) {
		return inStockDao.selectAmounts(map);
	}

	@Override
	public List<InStock> selectInstockDetails(Map map) {
		// TODO Auto-generated method stub
		return inStockDao.selectInstockDetails(map);
	}

	@Override
	public int selectInstockDetails_count(Map map) {
		// TODO Auto-generated method stub
		return inStockDao.selectInstockDetails_count(map);
	}

	@Override
	public Double selectAmountsDetails(Map map) {
		// TODO Auto-generated method stub
		return inStockDao.selectAmountsDetails(map);
	}


	@Override
	public List<InStock> selectAmountsByStock(Map<Object, Object> stockMap) {
		// TODO Auto-generated method stub
		return inStockDao.selectAmountsByStock(stockMap);
	}

}
