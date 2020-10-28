package com.pjgl.cgfk.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.cgfk.bean.Cgfk;
import com.pjgl.cgfk.dao.CgfkDao;

@Service("cgfkService")
@Scope("prototype")
public class CgfkServiceImpl implements CgfkService {

	@Autowired
	private CgfkDao cgfkDao;
	
	@Override
	public List<Cgfk> selectCgfk(Map<Object, Object> map) {
		return cgfkDao.selectCgfk(map);
	}

	@Override
	public int selectCgfk_count(Map<Object, Object> map) {
		return cgfkDao.selectCgfk_count(map);
	}

	@Override
	public int insertCgfk(Map<Object, Object> map) {
		return cgfkDao.insertCgfk(map);
	}

	@Override
	public int editCgfk(Map<Object, Object> map) {
		return cgfkDao.editCgfk(map);
	}

	@Override
	public int deleteCgfk(int id) {
		return cgfkDao.deleteCgfk(id);
	}

	@Override
	public void changeState(Map<Object, Object> map) {
		cgfkDao.changeState(map);
	}

	@Override
	public int countCgfk_count(Map map) {
		return cgfkDao.countCgfk_count(map);
	}

	@Override
	public List<Cgfk> countCgfk(Map map) {
		return cgfkDao.countCgfk(map);
	}

	@Override
	public Double selectAmounts(Map map) {
		return cgfkDao.selectAmounts(map);
	}

}
