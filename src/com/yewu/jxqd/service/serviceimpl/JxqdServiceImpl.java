package com.yewu.jxqd.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.bean.User_menu;
import com.yewu.jxqd.dao.JxqdDao;
import com.yewu.zscq.dao.WenjianDao;

@Service("jxqdService")
@Scope("prototype") 
public class JxqdServiceImpl implements JxqdService {


	@Autowired
	private JxqdDao jxqdDao;
	
	
	@Override
	public List<Jxqd> selectJXQD(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return jxqdDao.selectJXQD(map);
	}
	@Override
	public int selectJXQD_count(Map<Object,Object> map){//获得 绝限清单文件列表总记录数
		
		return jxqdDao.selectJXQD_count(map);
	}
	@Override
	public int insertJxqd(Map<Object,Object> map){//新建绝限清单记录
		return jxqdDao.insertJxqd(map);
	}
	@Override
	public int editJxqd(Map<Object,Object> map){//编辑绝限清单记录
		return jxqdDao.editJxqd(map);
	}
	@Override
	public List<User_menu> selectUser_menu(Map<Object,Object> map){//根据账号查询权限，可操作哪些模块
		return jxqdDao.selectUser_menu(map);
	}
	@Override
	public int updatePWD(Map<Object,Object> map){//更新用户密码
		return jxqdDao.updatePWD(map);
	}
}
