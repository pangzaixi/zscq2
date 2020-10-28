package com.zscq2.jxqd.service.serviceimpl;

import java.util.List;
import java.util.Map;

import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.bean.User_menu2;


public interface LazbService {
	
	//LazbDao
	public List<Lazb> selectJXQD(Map<Object,Object> map);//获得 绝限清单文件列表
	public int selectJXQD_count(Map<Object,Object> map);//获得 绝限清单文件列表总记录数
	public int insertLazb(Map<Object,Object> map);//新建绝限清单记录
	public int editLazb(Map<Object,Object> map);//编辑绝限清单记录
	public int changeStatus(Map<Object,Object> map);//变更状态
	public int changeAjzt(Map<Object,Object> map);//变更案件状态
	public int deleteLazb(Map<Object,Object> map);//删除立案记录
	public int maxAjh(Map<Object,Object> map);//获得当前最大案件号排序
	public List<User_menu2> selectUser_menu(String login_name);//根据账号查询权限，可操作哪些模块
	public List<Ajlx> selectAjlx(Map<Object,Object> map);//获得案件类型列表
	public List<Lazb> selectDlr1();//查询代理人1
	public List<Lazb> selectDlr2();//查询代理人2
	public int mulUpdateLazb(Map<Object,Object> map);//批量更新立案记录
	public List<Ajzt> selectAjzt(Map<Object,Object> map);//查询案件状态
	public List<Lazb> selectJXQDByAjhs(Map<Object,Object> map);//根据案件号集合字符串查询案件集合
	
	//RemindDao
	public List<Lazb> selectJXQDForRemind(Map<Object,Object> map);//获得 登录弹窗中的提醒记录
	public int selectJXQDForRemind_count(Map<Object,Object> map);//获得 登录弹窗中的提醒记录总数
	
}
