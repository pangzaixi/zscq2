package com.system.tree.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.system.tree.bean.Menu;


public interface TreeService {
	public String getName();
	public List<Menu> getFirstLevelMenus();//���һ���˵�����
	public List<Menu> getSecondLevelMenus();//��ö����˵�����
	public List<Menu> getThirdLevelMenus();//��ȡ�����˵����ϣ�Ҳ��Ҷ�Ӳ˵�����
	public List<Menu> getMenuById(String id);//���ݲ˵�ID��øò˵����Ӳ˵�����
	
	public List<Menu> getMenusForNavbar(Map<Object,Object> level);//���navbar�Ĳ˵���service�в�ֳ�һ�������˵�,����level=1һ���˵���level=2,�����˵�
}
