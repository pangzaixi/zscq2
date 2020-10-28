package com.pjgl.stockQuery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.stockQuery.bean.StockQuery;
import com.pjgl.stockQuery.dao.StockQueryDao;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.dao.JxqdDao;


@Service("stockQueryService")
@Scope("prototype") 
public class StockQueryServiceImpl implements StockQueryService {

	@Autowired
	private StockQueryDao stockQueryDao;

	@Override
	public int selectStock_count(Map<Object, Object> map) {
		return stockQueryDao.selectStock_count(map);
	}

	@Override
	public StockQuery getByPj(Map<Object, Object> map) {
		return stockQueryDao.getByPj(map);
	}

	@Override
	public List<StockQuery> selectStockQuery(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stockQueryDao.selectStockQuery(map);
	}
	

}
