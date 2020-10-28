package com.pjgl.stock.service;

import java.util.List;
import java.util.Map;

import com.pjgl.stock.bean.Stock;
import com.yewu.jxqd.bean.Jxqd;


public interface StockService {
	public List<Stock> selectStock(Map<Object,Object> map);//获得库存列表
	public int selectStock_count(Map<Object,Object> map);//获得 库存总记录数
	public Double selectAmounts(Map<Object,Object> map);//获得总库存金额
	public int insertStock(Map<Object,Object> map);//新建库存记录
	public int editStock(Map<Object,Object> map);//编辑库存记录
	public Stock getByPj(Map<Object, Object> map);
	public String selectOrgMapping(String ordId);//根据合作社平台传来的ORGID查询对应本系统的公司ID，用来查询库存
	public List<Stock> selectStockForHZS(Map<Object,Object> map);//获得库存列表,用于合作社平台
}