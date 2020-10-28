package com.system.qxgl.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.system.qxgl.bean.Qxgl;
import com.system.qxgl.dao.QxglDao;
import com.system.qxgl.service.QxglService;
import com.system.zzjg.dao.ZzjgDao;
import com.system.zzjg.service.serviceimpl.ZzjgService;


@Service("qxglService")
@Scope("prototype") 	
public class QxglServiceImpl implements QxglService {
	@Autowired
	private QxglDao qxglDao;
	
	@Override
	public List<Qxgl> selectQxgl(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return qxglDao.selectQxgl(map);
	}

	@Override
	public int selectQxgl_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return qxglDao.selectQxgl_count(map);
	}

	@Override
	public int insertQxgl(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return qxglDao.insertQxgl(map);
	}
	@Override
	public int insertAllQx(Map<Object,Object> map){//新增公司是，自动创建公司管理员，并为该管理员赋予所有权限
		return qxglDao.insertAllQx(map);
	}
	@Override
	public int deleteQxgl(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return qxglDao.deleteQxgl(map);
	}
	@Override
	public List<String> selectQxTitle(){//获得 权限列表中的所有模块名称，排重
		return qxglDao.selectQxTitle();
	}

	@Override
	public List<Qxgl> selectQxglDistinct(Map<Object, Object> map) {
	
		return qxglDao.selectQxglDistinct(map);
	}
}
