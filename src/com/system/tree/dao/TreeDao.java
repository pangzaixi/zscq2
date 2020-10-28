package com.system.tree.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.system.tree.bean.Menu;
import com.system.utils.MybatisRepository;

@MybatisRepository
public interface TreeDao {

	public String getName();
	public List<Menu> getFirstLevelMenus();//���һ���˵�����
	public List<Menu> getSecondLevelMenus();//��ö����˵�����
	public List<Menu> getThirdLevelMenus();//��������˵�����
	public List<Menu> getMenuById(String id);//���ݶ����˵�ID��ö����˵����Ӽ��ϣ�Ҳ���������˵�������Ҷ�Ӳ˵�
	public List<Menu> getMenusForNavbar(Map<Object,Object> level);//���navbar�Ĳ˵���service�в�ֳ�һ�������˵�
}
