package com.yewu.zscq.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yewu.zscq.bean.Dlr;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.dao.WenjianDao;
import com.yewu.zscq.service.WenjianService;

/**
 * 获取文件
 * @author thinker
 *
 */
@Service("wenjianService")
@Scope("prototype") 
public class WenjianServiceImpl implements WenjianService {

	@Autowired
	private WenjianDao wenjianDao;
	
	@Override
	public List<WenJian> selectWenjian(Map<Object, Object> map) {
		List<WenJian>  list = wenjianDao.selectWenjian(map);
		return list;
	}

	@Override
	public int selectWenjian_count(Map<Object,Object> map){//获得 文件列表总记录数
		int count = wenjianDao.selectWenjian_count(map);
		return count;
	}
	@Override
	public int addWenjian(Map<Object,Object> map){//写入文件记录
		int result = wenjianDao.addWenjian(map);
		return result; 
	}
	@Override
	public List<User> selectUser(Map<Object,Object> map){//查询操作用户
		List<User> list = wenjianDao.selectUser(map);
		return list;
	}
	@Override
	public int addLog(Map<Object,Object> map){//写入日志记录
		int result = wenjianDao.addLog(map);
		return result; 
	}
	@Override
	public int deleteWenjian(String id){//删除文件
		int result = wenjianDao.deleteWenjian(id);
		return result;
	}
	@Override
	public List<String> selectDlr(Map<Object,Object> map){//查询代理人
		List<String> list = wenjianDao.selectDlr(map);
		return list;
	}
	@Override
	public List<String> selectRoles(String login_name){//查询当前登录人的角色
		return wenjianDao.selectRoles(login_name);
	}
}
