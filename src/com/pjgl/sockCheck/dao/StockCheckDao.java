package com.pjgl.sockCheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pjgl.sockCheck.bean.StockCheck;

@Repository
@Scope("prototype") 
public interface StockCheckDao {

	public int addStockCheck(Map<Object,Object> map);//新增盘库记录
	public List<StockCheck> selectStockCheck(Map<Object,Object> map);//查询盘库记录 
}
