package com.yewu.zscq.service;

import java.util.List;
import java.util.Map;

import com.yewu.zscq.bean.Dlr;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;

public interface WenjianService {
	public List<WenJian> selectWenjian(Map<Object,Object> map);//获得 文件列表
	public int selectWenjian_count(Map<Object,Object> map);//获得 文件列表总记录数
	
	public int addWenjian(Map<Object,Object> map);//写入文件记录
	
	public List<User> selectUser(Map<Object,Object> map);//查询操作用户
	public int addLog(Map<Object,Object> map);//写入日志记录
	public int deleteWenjian(String id);//删除文件
	public List<String> selectDlr(Map<Object,Object> map);//查询代理人
	public List<String> selectRoles(String login_name);//查询当前登录人的角色
}
