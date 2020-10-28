package com.system.qxgl.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.grid.bean.FoodData;
import com.system.qxgl.bean.Qxgl;
import com.system.qxgl.service.QxglService;
import com.system.role.bean.Role;
import com.system.zzjg.bean.Zzjg;
import com.system.zzjg.service.serviceimpl.ZzjgService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.service.WenjianService;


@Controller("qxglController")   
@Scope("prototype") 
public class QxglController    extends GenericForwardComposer{
	Boolean pagingOnClick = false;
//窗体对象	
	Window qxglwin;
//页面查询条件对象
Textbox login_name;//登录账号
Textbox user_name;//用户姓名
Combobox title;//模块名称
	
	
ListModel qxModel =new ListModelList<String>();//权限下拉选数据源

//获取service对象
	QxglService qxglService = (QxglService)SpringUtil.getBean("qxglService");
//固定对象
	private 	Listbox  listbox;//页面遍历方式加载数据
	@Wire
	private Footer footercategory;
	Paging paging;
	
/**
 * 初始化方法	
 */
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("qxglController", this.getController());
	
	List<String> list = qxglService.selectQxTitle();

	qxModel= new ListModelList(list);
	title.setModel(qxModel);	

}

/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Qxgl> list = qxglService.selectQxgl(map);
	ListModel listModelList =new ListModelList<Qxgl>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}
	listbox.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	paging.setTotalSize(qxglService.selectQxgl_count(map));
}
/**
 * 参数整理
 * @param map
 * @throws ParseException
 */
private void param(Map<Object,Object> map) throws ParseException{
	if(login_name.getValue() != null && !"".equals(login_name.getValue())){
		map.put("login_name", login_name.getValue().trim());
	}
	if(user_name.getValue() != null && !"".equals(user_name.getValue())){
		map.put("user_name", user_name.getValue().trim());
	}
	if(title.getValue() != null && !"".equals(title.getValue())){
		map.put("title", title.getValue().trim());
	}
	User user_login = (User)session.getAttribute("user");
	map.put("companyid",user_login.getCompanyid() );
	
	if(pagingOnClick == true){
	    pagingOnClick = false;
	    map.put("begin", paging.getPageSize()*paging.getActivePage());
	    map.put("end", paging.getPageSize());
	  }else{
		paging.setActivePage(0);
	    map.put("begin", 0);
	    map.put("end", paging.getPageSize());
	  }
}

/**
 * 分页标签
 * @throws ParseException 
 */
public void onClick$paging() throws ParseException{
	int activepage = paging.getActivePage();
	pagingOnClick = true;
	 onClick$queryButton();
	 if(activepage<=paging.getPageCount()-1){
		 paging.setActivePage(activepage);	 
	 }else{
		 paging.setActivePage(0);
	 }
	 
}
/**
 * 清空查询条件
 */
public void onClick$clearButton(){
	login_name.setValue("");
	user_name.setValue("");
	title.setValue(null);
}
/**
 * 
* @Title: onClick$addButton
* @Description: 点击分配权限按钮，跳转到新增页面
* @return void    返回类型
* @author 徐玲
* @Date 2019年8月8日上午8:59:51
 */
public void onClick$addButton(){
	Window window = (Window)Executions.createComponents(
            "/jsp/system/qxgl/addQxgl.zul", null, null);
    window.doModal();
}
/**
 * 
* @Title: onClick$delButton
* @Description: 删除某分配权限，superadmin不能删除
* @return void    返回类型
* @author 徐玲
* @Date 2019年8月8日上午9:11:52
 */
public void onClick$delButton() throws ParseException{
 	
 	if(listbox.getSelectedItem() == null){
 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
 		return;
 	}else{
 		Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
 		    public void onEvent(Event evt) throws  ParseException {
 		        if (evt.getName().equals("onOK")) {
		        //确认删除
 		      Qxgl qxglGet = listbox.getSelectedItem().getValue();
 		       	String login_name = qxglGet.getLogin_name();
		 		Map map = new HashMap<Object,Object>();
		 		map.put("id", qxglGet.getId());
		 	    try {
		 	    	if (!"superadmin".equals(login_name)) {
		 	    		//不是superadmin的账户可以删除
		 	    		int result = qxglService.deleteQxgl(map);
				 	    onClick$queryButton();
				 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
					}else {
						  Messagebox.show("该账户不允许删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					   Messagebox.show("删除失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				}
 		        } else {
                 //点击取消按钮
 		        }
 		    }
 		});
 	
 	}
 }

}