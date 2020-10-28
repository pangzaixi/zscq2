package com.pjgl.cght.sub.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.cght.sub.bean.CghtSub;
import com.pjgl.cght.sub.dao.CghtSubDao;

@Service("cghtSubService")
@Scope("prototype")
public class CghtSubServiceImpl implements CghtSubService {

	@Autowired
	private CghtSubDao cghtSubDao;
	
	@Override
	public List<CghtSub> selectCghtSub(Map<Object, Object> map) {
		return cghtSubDao.selectCghtSub(map);
	}

	@Override
	public int selectCghtSub_count(Map<Object, Object> map) {
		return cghtSubDao.selectCghtSub_count(map);
	}

	@Override
	public int insertCghtSub(Map<Object, Object> map) {
		return cghtSubDao.insertCghtSub(map);
	}

	@Override
	public int editCghtSub(Map<Object, Object> map) {
		return cghtSubDao.editCghtSub(map);
	}

	@Override
	public int deleteCghtSub(int id) {
		return cghtSubDao.deleteCghtSub(id);
	}

	@Override
	public int deleteSubByCght(int cghtid) {
		return cghtSubDao.deleteSubByCght(cghtid);
	}

}
