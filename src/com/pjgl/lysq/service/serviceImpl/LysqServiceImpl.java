package com.pjgl.lysq.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.dao.LysqDao;

@Service("lysqService")
@Scope("prototype")
public class LysqServiceImpl implements LysqService {
	@Autowired
	private LysqDao lysqDao;
	@Override
	public int selectLysq_count(Map<Object,Object> map) {
		// TODO Auto-generated method stub
		return lysqDao.selectLysq_count(map);
	}

	@Override
	public List<Lysq> selectLysq(Map<Object,Object> map) {
		// TODO Auto-generated method stub
		return lysqDao.selectLysq(map);
	}

	@Override
	public int insertLysq(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return lysqDao.insertLysq(map);
	}

	@Override
	public int editLysq(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return lysqDao.editLysq(map);
	}
	@Override
	public int deleteLysq(Map<Object,Object> map){//
		return lysqDao.deleteLysq(map);
	}
	@Override
	public int editLysqState(Map<Object, Object> map){
		return lysqDao.editLysqState(map);
	}
}
