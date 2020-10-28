package com.yewu.log.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yewu.log.bean.Log;
import com.yewu.log.dao.LogDao;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.dao.WenjianDao;
import com.yewu.zscq.service.WenjianService;

@Service("logService")
@Scope("prototype") 
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;
	
	@Override
	public List<Log> selectLog(Map<Object,Object> map){//获得 日志历史
		List<Log>  list = logDao.selectLog(map);
		return list;
	}
	@Override
	public int selectLog_count(Map<Object,Object> map){//获得 日志列表总记录数
		int result =  logDao.selectLog_count(map);
		return result;
	}
	@Override
	public List<Log> getAllName(Map<Object,Object> map){//获得所有操作过系统的用户的姓名
		List<Log>  list = logDao.getAllName(map);
		return list;
	}
	@Override
	public int addLog(Map<Object,Object> map){//写入日志记录
		return logDao.addLog(map);
	}
}
