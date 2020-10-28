package com.pjgl.stockQuery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pjgl.stockQuery.bean.StockQuery;


@Repository
@Scope("prototype") 
public interface StockQueryDao {
	public List<StockQuery> selectStockQuery(Map<Object,Object> map);//获得库存列表
	public int selectStock_count(Map<Object,Object> map);//获得 库存总记录数
	
	public StockQuery getByPj(Map<Object, Object> map);
	
}