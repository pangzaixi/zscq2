package com.system.qxgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.system.qxgl.bean.Qxgl;
import com.system.zzjg.bean.Zzjg;


@Repository
@Scope("prototype") 
public interface QxglDao {
	public List<Qxgl> selectQxgl(Map<Object,Object> map);//获得 权限列表
	public int selectQxgl_count(Map<Object,Object> map);//获得 权限列表总记录数
	public int insertQxgl(Map<Object,Object> map);//新建权限记录
	public int deleteQxgl(Map<Object,Object> map);//删除权限记录
	public int insertAllQx(Map<Object,Object> map);//新增公司是，自动创建公司管理员，并为该管理员赋予所有权限
	public List<String> selectQxTitle();//获得 权限列表中的所有模块名称，排重
	public List<Qxgl> selectQxglDistinct(Map<Object,Object> map);//获取权限列表，排重
}
