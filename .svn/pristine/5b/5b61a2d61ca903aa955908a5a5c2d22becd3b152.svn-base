package com.pjgl.pj.servcie.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.dao.PjDao;

@Service("pjService")
@Scope("prototype")
public class PjServiceImpl implements PjService {

	@Autowired
	private PjDao pjDao;
	
	@Override
	public List<Pj> selectPj(Map<Object, Object> map) {
		return pjDao.selectPj(map);
	}

	@Override
	public int selectPj_count(Map<Object, Object> map) {
		return pjDao.selectPj_count(map);
	}

	@Override
	public int insertPj(Map<Object, Object> map) {
		return pjDao.insertPj(map);
	}

	@Override
	public int editPj(Map<Object, Object> map) {
		return pjDao.editPj(map);
	}

	@Override
	public int deletePj(int id) {
		return pjDao.deletePj(id);
	}

	@Override
	public Pj getById(int id) {
		return pjDao.getById(id);
	}
	@Override
	public int selectPjStatus(Map<Object,Object> map){//获得配件在采购合同和库存中的使用情况，如果存在对应的采购合同或者库存，则不允许删除
		return pjDao.selectPjStatus(map);
	}
}
