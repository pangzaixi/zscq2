package com.pjgl.sockCheck.service;

import java.util.List;
import java.util.Map;

import com.pjgl.sockCheck.bean.StockCheck;
import com.yewu.zscq.usermanage.bean.User;

public interface StockCheckService {

	public int addStockCheck(Map<Object,Object> map);//新增盘库记录
	public List<StockCheck> selectStockCheck(Map<Object,Object> map);//查询盘库记录
}
