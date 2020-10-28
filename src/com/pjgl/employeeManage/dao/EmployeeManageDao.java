package com.pjgl.employeeManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pjgl.employeeManage.bean.EmployeeManage;


@Repository
@Scope("prototype") 
public interface EmployeeManageDao {
	
	public List<EmployeeManage> selectEmployeeManage(Map<Object,Object> map);//获得 员工列表
	public int selectEmployeeManage_count(Map<Object,Object> map);//获得 员工列表总记录数
	public int insertEmployeeManage(Map<Object,Object> map);//新建员工记录
	public int updateEmployeeManage(Map<Object,Object> map);//修改员工记录
	public int statusSwitchEmployeeManage(Map<Object,Object> map);//修改员工状态记录
	public EmployeeManage getById(int id);
	public List<EmployeeManage> getByCompanyId(Map<Object,Object> map);
}
