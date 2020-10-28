package com.pjgl.employeeManage.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.dao.EmployeeManageDao;

@Service("employeeManageService")
@Scope("prototype") 	
public class EmployeeManageServiceImpl implements EmployeeManageService {

	@Autowired
	private EmployeeManageDao employeeManageDao;
	@Override
	public List<EmployeeManage> selectEmployeeManage(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.selectEmployeeManage(map);
	}

	@Override
	public int selectEmployeeManage_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.selectEmployeeManage_count(map);
	}

	@Override
	public int insertEmployeeManage(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.insertEmployeeManage(map);
	}

	@Override
	public int updateEmployeeManage(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.updateEmployeeManage(map);
	}

	

	@Override
	public EmployeeManage getById(int id) {
		// TODO Auto-generated method stub
		return employeeManageDao.getById(id);
	}

	@Override
	public List<EmployeeManage> getByCompanyId(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.getByCompanyId(map);
	}

	@Override
	public int statusSwitchEmployeeManage(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return employeeManageDao.statusSwitchEmployeeManage(map);
	}

	

	

}
