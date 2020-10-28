package com.pjgl.car.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pjgl.car.bean.Car;
import com.pjgl.car.dao.CarDao;

@Service("carService")
@Scope("prototype")
public class CarServiceImpl implements CarService{

	@Autowired
	private CarDao carDao;
	@Override
	public List<Car> selectCar(Map<Object, Object> map) {
		return carDao.selectCar(map);
	}

	@Override
	public int selectCar_count(Map<Object, Object> map) {
		return carDao.selectCar_count(map);
	}

	@Override
	public int insertCar(Map<Object, Object> map) {
		return carDao.insertCar(map);
	}

	@Override
	public int editCar(Map<Object, Object> map) {
		return carDao.editCar(map);
	}

	@Override
	public Car getById(int id) {
		return carDao.getById(id);
	}

	@Override
	public int deleteCar(Map<Object, Object> map) {
		return carDao.deleteCar(map);
	}

	@Override
	public int selectPlate_num(Map<Object, Object> map) {
		return carDao.selectPlate_num(map);
	}

	

}
