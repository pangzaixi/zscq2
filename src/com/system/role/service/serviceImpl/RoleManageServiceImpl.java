package com.system.role.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.system.role.bean.Role;
import com.system.role.dao.RoleManageDao;

/**
 * 
* @ClassName: RoleManageServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 徐玲
* @date 2019年7月22日下午4:24:32
*
 */
@Service("roleManageService")
@Scope("prototype") 	
public class RoleManageServiceImpl implements RoleManageService {

	@Autowired
	private RoleManageDao roleManageDao;
	@Override
	public List<Role> selectRole(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.selectRole(map);
	}

	@Override
	public int selectRole_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.selectRole_count(map);
	}

	@Override
	public int insertRole(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.insertRole(map);
	}

	@Override
	public int updateRole(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.updateRole(map);
	}

	@Override
	public int deleteRole(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.deleteRole(map);
	}

	@Override
	public Role getById(int id) {
		// TODO Auto-generated method stub
		return roleManageDao.getById(id);
	}

	@Override
	public List<Role> selectRoleDistinct(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleManageDao.selectRoleDistinct(map);
	}

	@Override
	public String selectRemarksByRole(String role) {
		// TODO Auto-generated method stub
		return roleManageDao.selectRemarksByRole(role);
	}

}
