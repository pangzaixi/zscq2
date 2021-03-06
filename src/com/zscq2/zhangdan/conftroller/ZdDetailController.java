package com.zscq2.zhangdan.conftroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;
import com.zscq2.zhangdan.service.ZdService;


@Controller("zdDetailController")   
@Scope("prototype") 
public class ZdDetailController    extends GenericForwardComposer{
	
	
Window zdDetail;
Listbox listbox;//页面遍历方式加载数据	
Label detailCount;
Textbox remark;


ZdBean zdBean;	
	
//获取service对象
ZdService zdService = (ZdService)SpringUtil.getBean("zdService");


public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("zdDetailController", this.getController());
	
	zdBean = (ZdBean)Executions.getCurrent().getArg().get("zdBean");	
	
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("zdid", zdBean.getId());
	List<ZdDetail> list = zdService.selectZd_sub(map);
	System.out.println();
	

	ListModel listModelList =new ListModelList<ZdDetail>();
	listModelList = null;
	if(list!= null && list.size()>0){
		for(int i=0;i<list.size();i++){
			list.get(i).setIndex(i+1);
		}
		listModelList = new ListModelList(list);
	}

	listbox.setModel(listModelList);
	detailCount.setValue("当前案件包含："+list.size()+"条案件");
	remark.setValue(zdBean.getRemark());
}

public void onClick$save(){
	zdBean.setRemark(remark.getValue());
	zdService.updateZdRemarks(zdBean);
	
	ZahngdanController zahngdanController = (ZahngdanController)session.getAttribute("zahngdanController");
	zahngdanController.onClick$queryButton();
}

}
	