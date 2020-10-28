package com.zscq2.zhangdan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.zscq2.zhangdan.dao.ZdDao;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.zhangdan.bean.WfCompany;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;

@Service("zdService")
@Scope("prototype") 
public class ZdServiceImpl implements ZdService {
	@Autowired
	private ZdDao zdDao;
	
	@Override
	public List<ZdBean> selectZd(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zdDao.selectZd(map);
	}

	@Override
	public int selectZd_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zdDao.selectZd_count(map);
	}

	@Override
	public List<ZdDetail> selectZd_sub(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zdDao.selectZd_sub(map);
	}
	@Override
	public Map<Object,Object> getSumJe(Map<Object,Object> map){//获得金额合计，包括总代理费，总官费，总金额
		return zdDao.getSumJe(map);
	}
	@Override
	public int insertZd(ZdBean zdBean) {
		// TODO Auto-generated method stub
		return zdDao.insertZd(zdBean);
	}

	@Override
	public int insertZd_sub(ZdDetail zdDetail) {
		// TODO Auto-generated method stub
		return zdDao.insertZd_sub(zdDetail);
	}

	@Override
	public int deleteZd(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zdDao.deleteZd(map);
	}
	@Override
	public int deleteZd_sub(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return zdDao.deleteZd_sub(map);
	}
	
	@Override
	public int updateZd(ZdBean zdBean){//更新账单总官费、代理费
		return zdDao.updateZd(zdBean);
	}
	@Override
	public int updateZdRemarks(ZdBean zdBean){//更新账单
		return zdDao.updateZdRemarks(zdBean);
	}
	@Override
	public int updateZd_sub(Map<Object,Object> map){//更新账单子记录官费代理费
		return zdDao.updateZd_sub(map);
	}
	
	//获得立案记录
	@Override
	public List<Lazb> selectAllWtkhmc(Map<Object,Object> map){//获得所有委托客户列表
		return zdDao.selectAllWtkhmc(map);
	}
	
	@Override
	public List<Lazb> selectJXQD(Map<Object,Object> map){//获得 绝限清单文件列表
		return zdDao.selectJXQD(map);
	}
	
	@Override
	public int selectJXQD_count(Map<Object,Object> map){//获得 绝限清单文件列表总记录数
		return zdDao.selectJXQD_count(map);
	}
	@Override
	public List<WfCompany> selectWfCompany(){//查询我方主体公司
		return zdDao.selectWfCompany();
	}
	
}
