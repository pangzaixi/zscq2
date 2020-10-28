package com.pjgl.car.dao;

import java.util.List;
import java.util.Map;

import com.pjgl.car.bean.Car;


public interface CarDao {
	public List<Car> selectCar(Map<Object,Object> map);//获得 车辆列表
	public int selectCar_count(Map<Object,Object> map);//获得 车辆列表总记录数
	public int selectPlate_num(Map<Object, Object> map);//车牌号唯一
	public int insertCar(Map<Object,Object> map);//新建车辆记录
	public int editCar(Map<Object,Object> map);//新建车辆记录
	public int deleteCar(Map<Object,Object> map);//删除记录
	public Car getById(int id);
//	public List<String> selectDriver(@Param(value="company_id") String company_id);//查询驾驶员
//	public List<User> selectCarUser(@Param(value="company_id") String company_id);//根据company_id从zsxq_user中查询驾驶员姓名和联系方式
	public String selectDriverName(String driver);//根据driver查找驾驶员的名字
}
