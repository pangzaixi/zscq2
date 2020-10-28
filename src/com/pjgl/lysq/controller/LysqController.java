package com.pjgl.lysq.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.lysq.bean.Lysq;
import com.pjgl.lysq.service.serviceImpl.LysqService;
import com.pjgl.lysqSub.service.serviceImpl.LysqSubService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
@Controller("lysqController")   
@Scope("prototype") 
public class LysqController extends GenericForwardComposer {	
	Combobox company;//公司名称
	Combobox state;
	Button delButton;
	Button checkButton;
	Intbox role;
	Datebox lyrq1;
	Datebox lyrq2;
	Button editButton;
	
	//××××××××××××数据源
	
	public Listbox lysqbox;//页面遍历方式加载数据
	Paging paging;
	Window selectLysqWin;
	
	ListModel companyModel;//公司下拉选数据源
	
	//获取service对象
	LysqService lysqService = (LysqService)SpringUtil.getBean("lysqService");
	LysqSubService lysqSubService = (LysqSubService)SpringUtil.getBean("lysqSubService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("lysqController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		
		User user = (User)session.getAttribute("user");
		Map<Object,Object> map = new HashMap<Object,Object>();
		if(!"1".equals(user.getLevel())){
			map.put("id", user.getCompanyid());
		}
		map.put("begin", 0);
		map.put("end", 1000);
		List<Company> list = new ArrayList<Company>();
		Company company_t = new Company();
		company_t.setCompanyName("全部");
		company_t.setId(0);
		if("1".equals(user.getLevel())){
			list.add(company_t);
		}
		
		List<Company> list1 = companyService.selectCompany(map);
		list.addAll(list1);
		
		
		companyModel = new ListModelList(list);
		company.setModel(companyModel);
		
		if(!"1".equals(user.getLevel())){
			company.setSelectedText(1, 1, list.get(0).getCompanyName(), true);
		}else{
			company.setSelectedText(1, 1, "全部", true);

		}
	}	
	
	public void onClick$queryButton(){
		if(company.isVisible() == true && company.getSelectedItem() == null  ) {
			company.setSelectedIndex(0);
		}
	    	
    	Map map = new HashMap<Object,Object>();
        User user = (User)session.getAttribute("user");
    	
        System.out.println(company.getSelectedItem().getContext());
    	if("1".equals(user.getLevel())){//总公司，取公司列表中选择的项
    		if(!"0".equals(company.getSelectedItem().getContext())){
    			map.put("companyid", company.getSelectedItem().getContext());
    		}
    	}else{//分公司，取当前登录人所属公司
    		map.put("companyid", user.getCompanyid());
    	}
        
    	String state_t = (String)state.getSelectedItem().getValue();
    	if (state.getSelectedItem() != null &&  !"all".equals(state_t)) {
			map.put("state", state.getSelectedItem().getValue());
		}
    	if(lyrq1.getValue() !=null){
    		map.put("lyrq1", lyrq1.getValue());
    	}
    	if(lyrq2.getValue() !=null){
    		map.put("lyrq2", lyrq2.getValue());
    	}
    	
    	map.put("begin", paging.getPageSize()*paging.getActivePage());
		map.put("end", paging.getPageSize());
		
		List<Lysq> list = lysqService.selectLysq(map);
    	for(Lysq lysq :list){
    		if(lysq.getCompanyid()!=null&& ("").equals(lysq.getCompanyid())){
    			Company company = companyService.getById(Integer.parseInt(lysq.getCompanyid()));
    			lysq.setCompanyName(company.getCompanyName());
    		}
    	}
		ListModel listModelList =new ListModelList<WenJian>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}	
    	lysqbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(lysqService.selectLysq_count(map));
	}
	
	 /**
	  * 分页标签
	  */
	 public void onClick$paging(){
		 System.out.println(paging.getActivePage());
		 System.out.println(paging.getPageSize());
		 int activepage = paging.getActivePage();
		 
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
		 company.setSelectedIndex(0);
		state.setSelectedIndex(0);
		lyrq1.setValue(null);
		lyrq2.setValue(null);//领用时间
		
	 }
	 
	 /**
	  * 新增按钮,弹出编辑窗口
	  */
	 public void onClick$addButton() {
	     //create a window programmatically and use it as a modal dialog.
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/lysq/addLysq.zul", null, null);
	     window.doModal();
	 }
	 /**
	   *编辑公司记录
	   */
	 public void onClick$editButton() throws ParseException{
		 	
		 	if(lysqbox.getSelectedItem() == null){
		 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		return;
		 	}else{
		 		Lysq lysq = (Lysq)lysqbox.getSelectedItem().getValue();
		 		if(lysq.getState() == 1 || lysq.getState() == 2){
		 			Messagebox.show("只有待审批申请可编辑","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 		return;
		 		}
		 		Map<String, Object> arg1 = new HashMap<String, Object>();
		 		arg1.put("lysq", lysq);
		 		
		 		Window window = (Window)Executions.createComponents(
		 	            "/jsp/pjgl/lysq/editLysq.zul", null, arg1);
		 	    
		 	    window.doModal();//模式弹窗
//		 	    window.doPopup();//非模式弹窗
		 	    
		 	    onClick$queryButton();//刷新父窗口表格
		 	}
		 }
	 /**
	  * 删除合同记录
	  * @throws ParseException 
	  */
	 public void onClick$delButton() throws ParseException{
	 	
	 	if(lysqbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Lysq lysq = (Lysq)lysqbox.getSelectedItem().getValue();
	 		if (lysq.getState() == 1 || lysq.getState() == 2) {
	 			Messagebox.show("该记录已审批或已拒绝，不可删除！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}else {
				Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
		 		        if (evt.getName().equals("onOK")) {
		 			 		Map<Object, Object> map = new HashMap<Object, Object>();
		 			 		map.put("id", lysq.getId());
		 			 		map.put("state", Lysq.status_refuse);
		 			 		lysqService.deleteLysq(map);
		 			 		lysqSubService.deleteSubByLysq(lysq.getId());
		 			 		onClick$queryButton();//刷新父窗口表格
		 		        } else {
//		 		            alert("Save Canceled !");
		 		        }
		 		    }

		 		});
			}
	 	}
	 }
	 /**
	  * 审核
	  * @throws ParseException 
	  */
	 public void onClick$checkButton() throws ParseException{
	 	
	 	if(lysqbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	 	}else{
	 		Lysq lysq = (Lysq)lysqbox.getSelectedItem().getValue();
	 		Messagebox.show("确定通过审批吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
	 		    public void onEvent(Event evt) throws InterruptedException, ParseException {
	 		        if (evt.getName().equals("onOK")) {
	 			 		Map<Object, Object> map = new HashMap<Object, Object>();
	 			 		map.put("id", lysq.getId());
	 			 		map.put("state", Lysq.status_psss);
	 			 		onClick$queryButton();//刷新父窗口表格
	 		        } else {
//	 		            alert("Save Canceled !");
	 		        }
	 		    }

	 		});
	 	    
	 	}
	 }
	 
 /**
  * 审核
  * @throws ParseException 
  */
 public void onClick$checkButton2() throws ParseException{
	if(lysqbox.getSelectedItem() == null){
	 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		return;
	}
	Lysq lysq = (Lysq)lysqbox.getSelectedItem().getValue();
	if (lysq.getState() == 1 || lysq.getState() == 2) {
		Messagebox.show("该记录已审批或已拒绝，不可再次审批！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	 
	 Map<String, Object> arg1 = new HashMap<String, Object>();
 		arg1.put("lysq", lysq);
 		
 		Window window = (Window)Executions.createComponents(
 	            "/jsp/pjgl/lysq/sp.zul", null, arg1);
 	    
 	    window.doModal();//模式弹窗
	
 }
}
