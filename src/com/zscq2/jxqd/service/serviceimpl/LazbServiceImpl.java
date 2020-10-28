package com.zscq2.jxqd.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.bean.User_menu;
import com.yewu.jxqd.dao.JxqdDao;
import com.yewu.zscq.dao.WenjianDao;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.bean.User_menu2;
import com.zscq2.jxqd.dao.LazbDao;
import com.zscq2.jxqd.dao.RemindDao;

@Service("lazbService")
@Scope("prototype") 
public class LazbServiceImpl implements LazbService {


	@Autowired
	private LazbDao lazbDao;
	@Autowired
	private RemindDao remindDao;
	
	@Override
	public List<Lazb> selectJXQD(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return lazbDao.selectJXQD(map);
	}
	@Override
	public int selectJXQD_count(Map<Object,Object> map){//获得 绝限清单文件列表总记录数
		
		return lazbDao.selectJXQD_count(map);
	}
	@Override
	public int insertLazb(Map<Object,Object> map){//新建绝限清单记录
		return lazbDao.insertLazb(map);
	}
	@Override
	public int editLazb(Map<Object,Object> map){//编辑绝限清单记录
		return lazbDao.editLazb(map);
	}
	@Override
	public int changeStatus(Map<Object,Object> map){//变更状态
		return lazbDao.changeStatus(map);
	}
	@Override
	public int changeAjzt(Map<Object,Object> map){//变更案件状态
		return lazbDao.changeAjzt(map);
	}
	@Override
	public int deleteLazb(Map<Object,Object> map){//删除立案记录
		return lazbDao.deleteLazb(map);
	}
	@Override
	public int maxAjh(Map<Object,Object> map){//获得当前最大案件号排序
		return lazbDao.maxAjh(map);
	}
	
	@Override
	public List<User_menu2> selectUser_menu(String login_name){//根据账号查询权限，可操作哪些模块
		return lazbDao.selectUser_menu(login_name);
	}
	@Override
	public List<Ajlx> selectAjlx(Map<Object,Object> map){//获得案件类型列表
		return lazbDao.selectAjlx(map);
	}
	@Override
	public List<Lazb> selectDlr1(){//查询代理人1
		return lazbDao.selectDlr1();
	}
	@Override
	public List<Lazb> selectDlr2(){//查询代理人2
		return lazbDao.selectDlr2();
	}
	@Override
	public int mulUpdateLazb(Map<Object,Object> map){//批量更新立案记录
		return lazbDao.mulUpdateLazb(map);
	}
	@Override
	public List<Ajzt> selectAjzt(Map<Object,Object> map){//查询案件状态
		return lazbDao.selectAjzt(map);
	}
	@Override
	public List<Lazb> selectJXQDByAjhs(Map<Object,Object> map){//根据案件号集合字符串查询案件集合
		return lazbDao.selectJXQDByAjhs(map);
	}
	//----------------------------
	@Override
	public List<Lazb> selectJXQDForRemind(Map<Object,Object> map){//获得 登录弹窗中的提醒记录
		return remindDao.selectJXQDForRemind(map);
	}
	@Override
	public int selectJXQDForRemind_count(Map<Object,Object> map){//获得 登录弹窗中的提醒记录总数
		return remindDao.selectJXQDForRemind_count(map);
	}
	
}
