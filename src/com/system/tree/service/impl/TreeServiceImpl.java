package com.system.tree.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.tree.bean.Menu;
import com.system.tree.dao.TreeDao;
import com.system.tree.service.TreeService;

@Service("treeService")
public class TreeServiceImpl implements TreeService {
    @Autowired
	private TreeDao treeDao;
	
    
    @Override
	public String getName(){
    	String name = treeDao.getName();
    	System.out.println(name);
		return "aaa";
	}
    @Override
    public List<Menu> getFirstLevelMenus(){//���һ���˵�����
    	return treeDao.getFirstLevelMenus();
    }
    @Override
	public List<Menu> getSecondLevelMenus(){//��ö����˵�����
    	return treeDao.getSecondLevelMenus();
    }
    @Override
    public List<Menu> getThirdLevelMenus(){//��ȡ�����˵����ϣ�Ҳ��Ҷ�Ӳ˵�����
    	return treeDao.getThirdLevelMenus();
    }
    @Override
    public List<Menu> getMenuById(String id){
    	
    	return treeDao.getMenuById(id);
    }
    @Override
    public List<Menu> getMenusForNavbar(Map<Object,Object> level){//���navbar�Ĳ˵���service�в�ֳ�һ�������˵�
    	return treeDao.getMenusForNavbar(level);	
    }
}
