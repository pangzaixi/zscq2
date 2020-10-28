package com.pjgl.company.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.company.bean.Company;
import com.pjgl.company.dao.CompanyDao;

 

@Service("companyService")
@Scope("prototype")
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
		
	@Override
	public List<Company> selectCompany(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return companyDao.selectCompany(map);
	}

	@Override
	public int selectCompany_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return companyDao.selectCompany_count(map);
	}

	@Override
	public int insertCompany(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return companyDao.insertCompany(map);
	}

	@Override
	public int editCompany(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return companyDao.editCompany(map);
	}

	@Override
	public Company getById(int id) {
		// TODO Auto-generated method stub
		return companyDao.getById(id);
	}
	@Override
	public Company getByName(String companyName){
		return companyDao.getByName(companyName);
	}
	@Override
	public int deleteCompany(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return companyDao.deleteCompany(map);
	}

	@Override
	public int selectCompany_employee(String companyid) {
		// TODO Auto-generated method stub
		return companyDao.selectCompany_employee(companyid);
	}
	
}
