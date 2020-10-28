package com.system.zzjg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.system.zzjg.bean.Zzjg;
import com.yewu.jxqd.bean.Jxqd;

@Repository
@Scope("prototype") 
public interface ZzjgDao {
	public List<Zzjg> selectZzjg(Map<Object,Object> map);//获得 组织机构列表
	public int selectZzjg_count(Map<Object,Object> map);//获得 组织机构列表总记录数
	public int insertZzjg(Map<Object,Object> map);//新建组织机构记录
	public int editZzjg(Map<Object,Object> map);//更新组织机构记录
}
