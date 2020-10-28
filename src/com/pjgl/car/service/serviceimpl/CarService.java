package com.pjgl.car.service.serviceimpl;

import java.util.List;
import java.util.Map;
import com.pjgl.car.bean.Car;


public interface CarService {
	public List<Car> selectCar(Map<Object,Object> map);//获得 公司列表
	public int selectCar_count(Map<Object,Object> map);//获得 车辆列表总记录数
	public int insertCar(Map<Object,Object> map);//新建车辆记录
	public int editCar(Map<Object,Object> map);//新建车辆记录
	public Car getById(int id);
	public int deleteCar(Map<Object,Object> map);//删除公司记录
	public int selectPlate_num(Map<Object,Object> plateNum);//车牌号唯一
//	public List<String> selectDriver(String company_id);//查询驾驶员
//	public List<User> selectCarUser(String company_id);//根据company_id从zsxq_user中查询驾驶员姓名和联系方式
}
