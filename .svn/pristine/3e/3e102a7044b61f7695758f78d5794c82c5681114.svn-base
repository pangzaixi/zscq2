package com.pjgl.instock.dao;

import java.util.List;
import java.util.Map;

import com.pjgl.instock.bean.InStock;


public interface InStockDao {

	public List<InStock> selectInstock(Map<Object,Object> map);//获得 合同列表
	public int selectInstock_count(Map<Object,Object> map);//获得 合同列表总记录数
	public int insertInstock(Map<Object,Object> map);//新建合同记录
	public int editInstock(Map<Object,Object> map);//新建合同记录
	public int deleteInstock(int id);
	public Double selectAmounts(Map map);
	public List<InStock> selectInstockDetails(Map map);
	public int selectInstockDetails_count(Map map);
	public Double selectAmountsDetails(Map map);
	public List<InStock> selectAmountsByStock(Map<Object, Object> stockMap);
}
