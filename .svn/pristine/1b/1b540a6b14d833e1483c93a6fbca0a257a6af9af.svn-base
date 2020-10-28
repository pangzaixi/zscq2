package com.pjgl.cght.dao;

import java.util.List;
import java.util.Map;

import com.pjgl.cght.bean.Cght;


public interface CghtDao {

	public List<Cght> selectCght(Map<Object,Object> map);//获得 合同列表
	public int selectCght_count(Map<Object,Object> map);//获得 合同列表总记录数
	public int insertCght(Map<Object,Object> map);//新建合同记录
	public int editCght(Map<Object,Object> map);//新建合同记录
	public int deleteCght(int id);
	public Cght getById(int id);
	public List<Cght> selectCghtBySupplier(Map<Object,Object> map);//通过供应商ID查找合同
	public void changeZje(Map<Object, Object> cghtMap);
	public List<Cght> selectCghtByJbr(Map<Object,Object> map);//通过经办人查找合同
	public int selectCount(Map<Object, Object> map);
}
