package com.zscq2.updateDlr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zscq2.jxqd.bean.Lazb;



@Repository
@Scope("prototype") 
public interface UpdateDlrDao {
	public List<Lazb> selectJXQDForDlr(Map<Object,Object> map);//获得代理人为空的案件
	public int selectJXQDForDlr_count(Map<Object,Object> map);//获得代理人为空的案件记录总数
	public int updateDlr(Map<Object,Object> map);//更新代理人
}
