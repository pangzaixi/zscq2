package com.zscq2.jxqd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;

@Controller("mulUpdateSubWinController")   
@Scope("prototype") 
public class MulUpdateSubWinController    extends GenericForwardComposer{

	//获取service对象
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		
	Combobox ajzt;	
		
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		session.setAttribute("lazbController", this.getController());
		
		List<Ajzt> list_ajzt = new ArrayList<Ajzt>();
		
		
		Ajzt ajzt0 = new Ajzt();
		ajzt0.setAjzt("待提交");
		list_ajzt.add(ajzt0);
		Ajzt ajzt1 = new Ajzt();
		ajzt1.setAjzt("受理");
		list_ajzt.add(ajzt1);
		Ajzt ajzt2 = new Ajzt();
		ajzt2.setAjzt("待补正");
		list_ajzt.add(ajzt2);
		Ajzt ajzt3 = new Ajzt();
		ajzt3.setAjzt("已提交补正");
		list_ajzt.add(ajzt3);
		Ajzt ajzt4 = new Ajzt();
		ajzt4.setAjzt("不予受理");
		list_ajzt.add(ajzt4);
		Ajzt ajzt5 = new Ajzt();
		ajzt5.setAjzt("权利终止结案");
		list_ajzt.add(ajzt5);
		//案件状态
		 MulUpdateJxqdController mulUpdateJxqdController = (MulUpdateJxqdController)session.getAttribute("mulUpdateJxqdController");
		 Map map_param = new HashMap();
		 mulUpdateJxqdController.param(map_param);//得到父窗口的参数，判断是否选择了案件类型
		 if(map_param.get("ajlx") != null){
			 Map map = new HashMap<Object,Object>();
			 map.put("ajlx", map_param.get("ajlx"));
				List<Ajzt> list_ajzt2 = lazbService.selectAjzt(map);
				list_ajzt.addAll(list_ajzt2);
				
		 }
		 ajzt.setModel(new ListModelList(list_ajzt));
		
	}
}
