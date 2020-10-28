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
    public List<Menu> getFirstLevelMenus(){//获得一级菜单集合
    	return treeDao.getFirstLevelMenus();
    }
    @Override
	public List<Menu> getSecondLevelMenus(){//获得二级菜单集合
    	return treeDao.getSecondLevelMenus();
    }
    @Override
    public List<Menu> getThirdLevelMenus(){//获取三级菜单集合，也是叶子菜单集合
    	return treeDao.getThirdLevelMenus();
    }
    @Override
    public List<Menu> getMenuById(String id){
    	
    	return treeDao.getMenuById(id);
    }
    @Override
    public List<Menu> getMenusForNavbar(Map<Object,Object> level){//获得navbar的菜单，service中拆分成一级二级菜单
    	return treeDao.getMenusForNavbar(level);	
    }
}
