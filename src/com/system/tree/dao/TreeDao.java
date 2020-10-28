package com.system.tree.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.system.tree.bean.Menu;
import com.system.utils.MybatisRepository;

@MybatisRepository
public interface TreeDao {

	public String getName();
	public List<Menu> getFirstLevelMenus();//获得一级菜单集合
	public List<Menu> getSecondLevelMenus();//获得二级菜单集合
	public List<Menu> getThirdLevelMenus();//获得三级菜单集合
	public List<Menu> getMenuById(String id);//根据二级菜单ID获得二级菜单的子集合，也就是三级菜单或者是叶子菜单
	public List<Menu> getMenusForNavbar(Map<Object,Object> level);//获得navbar的菜单，service中拆分成一级二级菜单
}
