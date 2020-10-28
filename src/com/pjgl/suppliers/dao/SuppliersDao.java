package com.pjgl.suppliers.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pjgl.suppliers.bean.Suppliers;



/**
* @ClassName: SuppliersDao
* @Description: 供货商Dao
* @author 徐玲
* @date 2019年7月17日下午2:29:33
 */
@Repository
@Scope("prototype") 
public interface SuppliersDao {
	
	public List<Suppliers> selectSuppliers(Map<Object,Object> map);//获得 供货商列表
	public int selectSuppliers_count(Map<Object,Object> map);//获得 供货商列表总记录数
	public int insertSuppliers(Map<Object,Object> map);//新建供货商记录
	public int updateSuppliers(Map<Object,Object> map);//修改供货商记录
	public int deleteSuppliers(Map<Object,Object> map);//删除供货商记录
	public Suppliers getById(int id);

}
