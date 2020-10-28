package com.system.role.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.system.role.bean.Role;


@Repository
@Scope("prototype") 
public interface RoleManageDao {

	public List<Role> selectRole(Map<Object,Object> map);//获得 用户角色列表
	public List<Role> selectRoleDistinct(Map<Object,Object> map);//获取角色和remarks记录
	public int selectRole_count(Map<Object,Object> map);//获得 用户角色列表总记录数
	public int insertRole(Map<Object,Object> map);//新建用户角色记录
	public int updateRole(Map<Object,Object> map);//修改用户角色记录
	public int deleteRole(Map<Object,Object> map);//删除用户角色记录
	public String  selectRemarksByRole(String role);//通过role获取remarks记录
	public Role getById(int id);
}
