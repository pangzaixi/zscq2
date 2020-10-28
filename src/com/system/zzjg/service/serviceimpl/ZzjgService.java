package com.system.zzjg.service.serviceimpl;

import java.util.List;
import java.util.Map;

import com.system.zzjg.bean.Zzjg;

public interface ZzjgService {

	public List<Zzjg> selectZzjg(Map<Object,Object> map);//获得 组织机构列表
	public int selectZzjg_count(Map<Object,Object> map);//获得 组织机构列表总记录数
	public int insertZzjg(Map<Object,Object> map);//新建组织机构记录
	public int editZzjg(Map<Object,Object> map);//更新组织机构记录
}
