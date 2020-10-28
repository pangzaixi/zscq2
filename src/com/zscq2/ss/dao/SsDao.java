package com.zscq2.ss.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.ss.bean.Ssaj;
import com.zscq2.ss.bean.Sslc;

@Repository
@Scope("prototype") 
public interface SsDao {

	public List<Ssaj> selectSsaj(Map<Object,Object> map);//获得 诉讼案件记录
	public int selectSsaj_count(Map<Object,Object> map);//获得 诉讼案件记录总数
	public int insertSsaj(Map<Object,Object> map);//写入诉讼案件
	public int maxAjh(Map<Object,Object> map);//获得当前最大案件号排序
	public int updateSsaj(Map<Object,Object> map);//更新诉讼案件
	public int deleteSsaj(Map<Object,Object> map);//删除诉讼案件
	public List<Sslc> selectSslc(Map<Object,Object> map);//获得 诉讼案件的流程记录
	public int insertSsajLc(Map<Object,Object> map);//写入诉讼案件流程
}
