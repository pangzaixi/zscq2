package com.zscq2.jxqd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zscq2.jxqd.bean.Lazb;

@Repository
@Scope("prototype") 
public interface RemindDao {
	public List<Lazb> selectJXQDForRemind(Map<Object,Object> map);//获得 登录弹窗中的提醒记录
	public int selectJXQDForRemind_count(Map<Object,Object> map);//获得 登录弹窗中的提醒记录总数
}
