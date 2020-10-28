package com.pjgl.lysq.service.serviceImpl;

import java.util.List;
import java.util.Map;

import com.pjgl.lysq.bean.Lysq;



public interface LysqService {

	public int selectLysq_count(Map<Object,Object> map);
	public List<Lysq> selectLysq(Map<Object,Object> map);
	public int insertLysq(Map<Object,Object> map);//
	public int editLysq(Map<Object,Object> map);//
	public int deleteLysq(Map<Object,Object> map);//
	public int editLysqState(Map<Object, Object> map);
}
