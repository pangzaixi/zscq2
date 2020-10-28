package com.pjgl.pj.dao;

import java.util.List;
import java.util.Map;

import com.pjgl.pj.bean.Pj;

public interface PjDao {
	public List<Pj> selectPj(Map<Object,Object> map);//获得 公司列表
	public int selectPj_count(Map<Object,Object> map);//获得 公司列表总记录数
	public int insertPj(Map<Object,Object> map);//新建公司记录
	public int editPj(Map<Object,Object> map);//新建公司记录
	public int deletePj(int id);
	public Pj getById(int id);
	public int selectPjStatus(Map<Object,Object> map);//获得配件在采购合同和库存中的使用情况，如果存在对应的采购合同或者库存，则不允许删除
}
