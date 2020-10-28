package com.system.role.service.serviceImpl;

import java.util.List;
import java.util.Map;

import com.system.role.bean.Role;
/**
 * 
* @ClassName: RoleManageService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 徐玲
* @date 2019年7月22日下午4:24:26
*
 */
public interface RoleManageService {
	public List<Role> selectRole(Map<Object,Object> map);//获得 用户角色列表
	public List<Role> selectRoleDistinct(Map<Object,Object> map);//获取角色和remarks记录
	public int selectRole_count(Map<Object,Object> map);//获得 用户角色列表总记录数
	public int insertRole(Map<Object,Object> map);//新建用户角色记录
	public int updateRole(Map<Object,Object> map);//修改用户角色记录
	public int deleteRole(Map<Object,Object> map);//删除用户角色记录
	public String  selectRemarksByRole(String role);//通过role获取remarks记录
	public Role getById(int id);
}
