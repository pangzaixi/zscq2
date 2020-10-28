package com.yewu.jxqd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.bean.User_menu;

@Repository
@Scope("prototype") 
public interface JxqdDao {
	public List<Jxqd> selectJXQD(Map<Object,Object> map);//获得 绝限清单文件列表
	public int selectJXQD_count(Map<Object,Object> map);//获得 绝限清单文件列表总记录数
	public int insertJxqd(Map<Object,Object> map);//新建绝限清单记录
	public int editJxqd(Map<Object,Object> map);//编辑绝限清单记录
	
	public List<User_menu> selectUser_menu(Map<Object,Object> map);//根据账号查询权限，可操作哪些模块
	public int updatePWD(Map<Object,Object> map);//更新用户密码
}
