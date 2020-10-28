package com.pjgl.cght.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.dao.CghtDao;

@Service("cghtService")
@Scope("prototype")
public class CghtServiceImpl implements CghtService {

	@Autowired
	private CghtDao cghtDao;
	
	@Override
	public List<Cght> selectCght(Map<Object, Object> map) {
		return cghtDao.selectCght(map);
	}

	@Override
	public int selectCght_count(Map<Object, Object> map) {
		return cghtDao.selectCght_count(map);
	}

	@Override
	public int insertCght(Map<Object, Object> map) {
		return cghtDao.insertCght(map);
	}

	@Override
	public int editCght(Map<Object, Object> map) {
		return cghtDao.editCght(map);
	}

	@Override
	public int deleteCght(int id) {
		return cghtDao.deleteCght(id);
	}

	@Override
	public Cght getById(int id) {
		return cghtDao.getById(id);
	}

	@Override
	public List<Cght> selectCghtBySupplier(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return cghtDao.selectCghtBySupplier(map);
	}

	@Override
	public void changeZje(Map<Object, Object> cghtMap) {
		// TODO Auto-generated method stub
		cghtDao.changeZje(cghtMap);
	}

	@Override
	public List<Cght> selectCghtByJbr(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return cghtDao.selectCghtByJbr(map);
	}

	@Override
	public int selectCount(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return cghtDao.selectCount(map);
	}

}
