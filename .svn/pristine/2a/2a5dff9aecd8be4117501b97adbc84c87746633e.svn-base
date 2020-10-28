package com.pjgl.company.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pjgl.company.bean.Company;

public interface CompanyDao {
	public List<Company> selectCompany(Map<Object,Object> map);//获得 公司列表
	public int selectCompany_count(Map<Object,Object> map);//获得 公司列表总记录数
	public int insertCompany(Map<Object,Object> map);//新建公司记录
	public int editCompany(Map<Object,Object> map);//新建公司记录
	public int deleteCompany(Map<Object,Object> map);//删除记录
	public Company getById(int id);
	public Company getByName(String companyName);
	public int selectCompany_employee(@Param(value="companyid") String companyid);//根据companyid查询公司下的员工数量
}
