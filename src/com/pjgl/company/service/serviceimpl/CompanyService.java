package com.pjgl.company.service.serviceimpl;

import java.util.List;
import java.util.Map;

import com.pjgl.company.bean.Company;

public interface CompanyService {
	public List<Company> selectCompany(Map<Object,Object> map);//获得 公司列表
	public int selectCompany_count(Map<Object,Object> map);//获得 公司列表总记录数
	public int insertCompany(Map<Object,Object> map);//新建公司记录
	public int editCompany(Map<Object,Object> map);//新建公司记录
	public Company getById(int id);
	public Company getByName(String companyName);
	public int deleteCompany(Map<Object,Object> map);//删除公司记录
	public int selectCompany_employee(String companyid);//根据companyid查询公司下的员工数量
}
