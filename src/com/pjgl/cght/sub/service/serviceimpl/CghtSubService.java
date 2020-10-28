package com.pjgl.cght.sub.service.serviceimpl;

import java.util.List;
import java.util.Map;

import com.pjgl.cght.sub.bean.CghtSub;

public interface CghtSubService {

	public List<CghtSub> selectCghtSub(Map<Object,Object> map);//获得 合同列表
	public int selectCghtSub_count(Map<Object,Object> map);//获得 合同列表总记录数
	public int insertCghtSub(Map<Object,Object> map);//新建合同记录
	public int editCghtSub(Map<Object,Object> map);//新建合同记录
	public int deleteCghtSub(int id);
	public int deleteSubByCght(int cghtid);
	
}
