package com.pjgl.cgsp.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.cgsp.bean.Cgsp;
import com.pjgl.cgsp.dao.CgspDao;

@Service("cgspService")
@Scope("prototype")
public class CgspServiceImpl implements CgspService {

	@Autowired
	private CgspDao cgspDao;
	
	@Override
	public List<Cgsp> selectCgsp(Map<Object, Object> map) {
		return cgspDao.selectCgsp(map);
	}

	@Override
	public int selectCgsp_count(Map<Object, Object> map) {
		return cgspDao.selectCgsp_count(map);
	}

	@Override
	public int insertCgsp(Map<Object, Object> map) {
		return cgspDao.insertCgsp(map);
	}

	@Override
	public int editCgsp(Map<Object, Object> map) {
		return cgspDao.editCgsp(map);
	}

	@Override
	public int deleteCgsp(int id) {
		return cgspDao.deleteCgsp(id);
	}

	@Override
	public void changeState(Map<Object, Object> map) {
		cgspDao.changeState(map);
	}

	@Override
	public List<Cgsp> countCgsp(Map map) {
		// TODO Auto-generated method stub
		return cgspDao.countCgsp(map);
	}

	@Override
	public Double selectAmounts(Map map) {
		// TODO Auto-generated method stub
		return cgspDao.selectAmounts(map);
	}

	@Override
	public int countCgsp_count(Map map) {
		// TODO Auto-generated method stub
		return cgspDao.countCgsp_count(map);
	}

}
