package com.system.os.service;

import java.util.List;
import java.util.Map;

import com.system.os.bean.OsSbcx;

public interface Osservice {

	public List<OsSbcx> selectCaigouHeTongMain(Map<Object,Object> map);//获得采购合同主表记录
	public int selectCaigouHeTongMain_count(Map<Object,Object> map);//获得采购合同主表记录总数
}
