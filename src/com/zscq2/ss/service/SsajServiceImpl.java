package com.zscq2.ss.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zscq2.jxqd.dao.LazbDao;
import com.zscq2.ss.bean.Ssaj;
import com.zscq2.ss.bean.Sslc;
import com.zscq2.ss.dao.SsDao;


@Service("ssajService")
@Scope("prototype") 
public class SsajServiceImpl implements SsajService {
	@Autowired
	private SsDao ssDao;
	
	
	
	@Override
	public List<Ssaj> selectSsaj(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return ssDao.selectSsaj(map);
	}

	@Override
	public int selectSsaj_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return ssDao.selectSsaj_count(map);
	}
	@Override
	public int insertSsaj(Map<Object,Object> map){//写入诉讼案件
		return ssDao.insertSsaj(map);
	}
	@Override
	public int maxAjh(Map<Object,Object> map){//获得当前最大案件号排序
		return ssDao.maxAjh(map);
	}
	@Override
	public int updateSsaj(Map<Object,Object> map){//更新诉讼案件
		return ssDao.updateSsaj(map);
	}
	@Override
	public int deleteSsaj(Map<Object,Object> map){//删除诉讼案件
		return ssDao.deleteSsaj(map);
	}
	@Override
	public List<Sslc> selectSslc(Map<Object,Object> map){//获得 诉讼案件的流程记录
		return ssDao.selectSslc(map);
	}
	@Override
	public int insertSsajLc(Map<Object,Object> map){//写入诉讼案件流程
		return ssDao.insertSsajLc(map);
	}
}
