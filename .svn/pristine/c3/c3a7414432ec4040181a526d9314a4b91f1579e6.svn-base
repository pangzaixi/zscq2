package com.yewu.zscq.usermanage.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yewu.zscq.dao.WenjianDao;
import com.yewu.zscq.service.WenjianService;
import com.yewu.zscq.usermanage.bean.User;
import com.yewu.zscq.usermanage.dao.UserDao;
import com.yewu.zscq.usermanage.service.UserService;

@Service("userService")
@Scope("prototype") 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> selectUser(Map<Object, Object> map) {
		List<User> list = userDao.selectUser(map);
		return list;
	}

	@Override
	public int selectUser_count(Map<Object, Object> map) {
		int result = userDao.selectUser_count(map);
		return result;
	}

	@Override
	public int addUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteWenjian(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}

}
