package com.yewu.log.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.yewu.log.bean.Log;

@Repository
@Scope("prototype") 
public interface LogDao {
	public List<Log> selectLog(Map<Object,Object> map);//获得 日志历史
	public int selectLog_count(Map<Object,Object> map);//获得 日志列表总记录数
	public List<Log> getAllName(Map<Object,Object> map);//获得所有操作过系统的用户的姓名
	public int addLog(Map<Object,Object> map);//写入日志记录
}
