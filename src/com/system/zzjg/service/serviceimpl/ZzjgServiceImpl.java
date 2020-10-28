package com.system.zzjg.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.dao.ZzjgDao;
import com.yewu.jxqd.dao.JxqdDao;

@Service("zzjgService")
@Scope("prototype") 
public class ZzjgServiceImpl implements ZzjgService {

	@Autowired
	private ZzjgDao zzjgDao;
	
	@Override
	public List<Zzjg> selectZzjg(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zzjgDao.selectZzjg(map);
	}

	@Override
	public int selectZzjg_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zzjgDao.selectZzjg_count(map);
	}

	@Override
	public int insertZzjg(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zzjgDao.insertZzjg(map);
	}

	@Override
	public int editZzjg(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zzjgDao.editZzjg(map);
	}

}
