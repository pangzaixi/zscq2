package com.zscq2.zhangdan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.ss.bean.Ssaj;
import com.zscq2.ss.bean.Sslc;
import com.zscq2.zhangdan.bean.WfCompany;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;

/**
 * 账单
 * @author pang
 *
 */
@Repository
@Scope("prototype") 
public interface ZdDao {

	public List<ZdBean> selectZd(Map<Object,Object> map);//获得账单主表记录
	public int selectZd_count(Map<Object,Object> map);//获得 账单主表记录总数
	public List<ZdDetail> selectZd_sub(Map<Object,Object> map);//获得账单子表记录
	public Map<Object,Object> getSumJe(Map<Object,Object> map);//获得金额合计，包括总代理费，总官费，总金额
	public int insertZd(ZdBean zdBean);//写入诉讼案件
	public int insertZd_sub(ZdDetail zdDetail);//写入诉讼案件
	public int deleteZd(Map<Object,Object> map);//删除账单主表记录
	public int deleteZd_sub(Map<Object,Object> map);//删除账单子表记录
	public int updateZd(ZdBean zdBean);//更新账单总官费、代理费
	public int updateZdRemarks(ZdBean zdBean);//更新账单
	public int updateZd_sub(Map<Object,Object> map);//更新账单子记录官费代理费
	//获得立案记录
	public List<Lazb> selectAllWtkhmc(Map<Object,Object> map);//获得所有委托客户列表
	public List<Lazb> selectJXQD(Map<Object,Object> map);//获得 绝限清单文件列表
	public int selectJXQD_count(Map<Object,Object> map);//获得 绝限清单文件列表总记录数
	
	
	public List<WfCompany> selectWfCompany();//查询我方主体公司
}

