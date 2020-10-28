package com.system.os.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.system.os.bean.OsSbcx;
import com.system.os.dao.OsServiceDao;
import com.system.os.service.Osservice;

@Service("Osservice")
@Scope("prototype") 
public class OsserviceImpl implements Osservice {


	@Autowired
	private OsServiceDao osServiceDao;
	
	@Override
	public List<OsSbcx> selectCaigouHeTongMain(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return osServiceDao.selectCaigouHeTongMain(map);
	}
	@Override
	public int selectCaigouHeTongMain_count(Map<Object,Object> map){
		return osServiceDao.selectCaigouHeTongMain_count(map);
	}
	
}
