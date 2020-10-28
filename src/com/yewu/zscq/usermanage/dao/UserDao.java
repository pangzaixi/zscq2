package com.yewu.zscq.usermanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.yewu.zscq.usermanage.bean.User;


@Repository
@Scope("prototype") 
public interface UserDao {
	public List<User> selectUser(Map<Object,Object> map);//获得 用户列表
	public int selectUser_count(Map<Object,Object> map);//获得 用户总记录数
	
	public int addUser(Map<Object,Object> map);//新增用户
	public int updateUser(Map<Object,Object> map);//更新用户
	public int deleteWenjian(String id);//删除文件
	public User getById(int id);
}
