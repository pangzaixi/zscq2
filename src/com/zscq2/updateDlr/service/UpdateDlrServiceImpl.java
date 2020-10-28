package com.zscq2.updateDlr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.dao.LazbDao;
import com.zscq2.jxqd.dao.RemindDao;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.updateDlr.dao.UpdateDlrDao;


@Service("updateDlrService")
@Scope("prototype") 
public class UpdateDlrServiceImpl implements UpdateDlrService {
	@Autowired
	private LazbDao lazbDao;
	@Autowired
	private UpdateDlrDao updateDlrDao; 
	
	
	@Override
	public List<Lazb> selectJXQDForDlr(Map<Object,Object> map){//获得代理人为空的案件
		return updateDlrDao.selectJXQDForDlr(map);
	}
	
	@Override
	public int selectJXQDForDlr_count(Map<Object,Object> map){//获得代理人为空的案件记录总数
		return updateDlrDao.selectJXQDForDlr_count(map);
	}
	@Override
	public int updateDlr(Map<Object,Object> map){//更新代理人
		return updateDlrDao.updateDlr(map);
	}
}