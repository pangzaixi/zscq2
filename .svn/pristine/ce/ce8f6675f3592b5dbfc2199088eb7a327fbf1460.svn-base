package com.pjgl.lysqSub.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysqSub.bean.LysqSub;
import com.pjgl.lysqSub.dao.LysqSubDao;


@Service("lysqSubService")
@Scope("prototype")
public class LysqSubServiceImpl implements LysqSubService {
	@Autowired
	private LysqSubDao lysqSubDao;
	@Override
	public int selectLysqSub_count(Map<Object,Object> map) {
		// TODO Auto-generated method stub
		return lysqSubDao.selectLysqSub_count(map);
	}

	@Override
	public List<LysqSub> selectLysqSub(Map<Object,Object> map) {
		// TODO Auto-generated method stub
		return lysqSubDao.selectLysqSub(map);
	}

	@Override
	public int insertLysqSub(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return lysqSubDao.insertLysqSub(map);
	}

	@Override
	public int editLysqSub(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return lysqSubDao.editLysqSub(map);
	}

	@Override
	public int deleteLysqSub(int id) {
		// TODO Auto-generated method stub
		return lysqSubDao.deleteLysqSub(id);
	}

	@Override
	public int deleteSubByLysq(int lysqid) {
		// TODO Auto-generated method stub
		return lysqSubDao.deleteSubByLysq(lysqid);
	}

}
