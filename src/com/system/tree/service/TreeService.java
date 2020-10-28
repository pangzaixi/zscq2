package com.system.tree.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.system.tree.bean.Menu;


public interface TreeService {
	public String getName();
	public List<Menu> getFirstLevelMenus();//获得一级菜单集合
	public List<Menu> getSecondLevelMenus();//获得二级菜单集合
	public List<Menu> getThirdLevelMenus();//获取三级菜单集合，也是叶子菜单集合
	public List<Menu> getMenuById(String id);//根据菜单ID获得该菜单的子菜单集合
	
	public List<Menu> getMenusForNavbar(Map<Object,Object> level);//获得navbar的菜单，service中拆分成一级二级菜单,参数level=1一级菜单，level=2,二级菜单
}
