package com.pjgl.suppliers.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.suppliers.bean.Suppliers;
import com.pjgl.suppliers.dao.SuppliersDao;
/**
 * 
* @ClassName: SuppliersServiceImpl
* @Description: 供货商service实现类
* @author 徐玲
* @date 2019年7月17日下午2:42:56
*
 */
@Service("suppliersService")
@Scope("prototype") 	
public class SuppliersServiceImpl implements SuppliersService {

	@Autowired
	private SuppliersDao suppliersDao;
	@Override
	public List<Suppliers> selectSuppliers(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return suppliersDao.selectSuppliers(map);
	}

	@Override
	public int selectSuppliers_count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return suppliersDao.selectSuppliers_count(map);
	}

	@Override
	public int insertSuppliers(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return suppliersDao.insertSuppliers(map);
	}

	@Override
	public int updateSuppliers(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return suppliersDao.updateSuppliers(map);
	}

	@Override
	public int deleteSuppliers(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return suppliersDao.deleteSuppliers(map);
	}

	@Override
	public Suppliers getById(int id) {
		// TODO Auto-generated method stub
		return suppliersDao.getById(id);
	}

}
