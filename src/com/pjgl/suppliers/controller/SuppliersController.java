package com.pjgl.suppliers.controller;



import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.Null;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.pj.bean.Pj;
import com.pjgl.suppliers.bean.Suppliers;
import com.pjgl.suppliers.service.serviceImpl.SuppliersService;
import com.system.role.bean.Role;
import com.yewu.zscq.bean.User;



/**
 * 
* @ClassName: SuppliersController
* @Description: 供货商Controller
* @author 徐玲
* @date 2019年7月17日下午2:31:06
*
 */
@Controller("suppliersController")   
@Scope("prototype") 
public class SuppliersController extends GenericForwardComposer {
	Boolean pagingOnClick = false;
	//窗体对象
	Window supplierswin;
	//页面查询条件
	Textbox name;//供应商名称
	Textbox address ;//地址
	Combobox company;//公司
	
	ListModel companyModel = new ListModelList<Company>();//公司下拉菜单数据源
	
	//获取service对象
	SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	CghtService cghtService = (CghtService) SpringUtil.getBean("cghtService");
	
	//固定对象
	Listbox listbox;//页面数据
	Paging paging;//分页
	
	/**
	* @Title: doAfterCompose
	* @Description: 初始化方法
	* @author 徐玲
	* @Date 2019年7月17日下午4:49:16
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		session.setAttribute("suppliersController", this.getController());
		//给公司下拉框赋值
		Map map = new HashMap<Object,Object>();
		map.put("begin", 0);
		map.put("end", 100);
		List<Company> list = companyService.selectCompany(map);
		if (list !=null && list.size() >0) {
			companyModel = new ListModelList<>(list);
		}
		company.setModel(companyModel);
		//根据当前登录用户给下拉框附初始值
		User user = (User) session.getAttribute("user");
		if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
			Company companyQuery = companyService.getById(Integer.valueOf(user.getCompanyid()));
			if (companyQuery != null ) {
				if (companyQuery.getCompanyName() != null && !"".equals(companyQuery.getCompanyName())) {
					company.setValue(companyQuery.getCompanyName());
				}
			}
		}
	
		//调用查询方法
		onClick$queryButton();
			}
    /**
    * @Title: onClick$queryButton
    * @Description: 查询按钮
    * @return void    返回类型
    * @author 徐玲
    * @Date 2019年7月17日下午4:49:04
     */
	public void onClick$queryButton() throws ParseException{
		Map map = new HashMap<Object,Object>();
		param(map);
		
		List<Suppliers> list = suppliersService.selectSuppliers(map);
		ListModelList listModelList = new ListModelList<Suppliers>();
		listModelList = null ;
		if (list != null && list.size() >0) {
			listModelList = new ListModelList<>(list);
		}
		listbox.setModel(listModelList);
		
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		paging.setTotalSize(suppliersService.selectSuppliers_count(map));
	}
	
  /**
  * @Title: onClick$paging
  * @Description: 分页
  * @return void    返回类型
  * @author 徐玲
  * @Date 2019年7月17日下午4:54:48
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
	 * 
	* @Title: onClick$clearButton
	* @Description: 清空按钮
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月17日下午4:56:16
	 */
	 public void onClick$clearButton(){
	 	name.setValue("");
	 	address.setValue("");
	 	company.setValue(null);
	 }
	/**
	 * 
	* @Title: onClick$addButton
	* @Description: 点击新增按钮，弹出对话框
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月18日上午10:11:12
	 */
	 public void onClick$addButton() {
	     Window window = (Window)Executions.createComponents(
	             "/jsp/pjgl/suppliers/addSuppliers.zul", null, null);
	     window.doModal();
	 }
	 /**
	  * 
	 * @Title: onClick$editButton
	 * @Description: 点击编辑按钮，弹出对话框
	 * @return void    返回类型
	 * @author 徐玲
	 * @throws ParseException 
	 * @Date 2019年7月18日上午10:13:58
	  */
	 public void onClick$editButton() throws ParseException {
		 if(listbox.getSelectedItem() == null){
				Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}else{
				Suppliers suppliers = listbox.getSelectedItem().getValue();//获得选中的供货商信息
				String suppliersCompanyId = suppliers.getCompanyid();//选中供应商的公司ID
				//总公司不能编辑各个分公司的信息
				User user = (User)session.getAttribute("user");
				String userCompanyId = user.getCompanyid();//当前登录用户的公司ID
				if (userCompanyId != null && !"".equals(userCompanyId)&& suppliersCompanyId != null && !"".equals(suppliersCompanyId)) {
					if (!userCompanyId.equals(suppliersCompanyId)) {
						Messagebox.show("无编辑权限","注意", Messagebox.OK, Messagebox.EXCLAMATION);
						return;
					}
					Map<String, Object> arg1 = new HashMap<String, Object>();
					arg1.put("suppliers", suppliers);
		     Window window = (Window)Executions.createComponents(
		             "/jsp/pjgl/suppliers/editSuppliers.zul", null, arg1);
		     window.doModal();
				}
	     }
		 onClick$queryButton();
	 }
	 /**
	  * 
	 * @Title: onClick$delButton
	 * @Description: 删除供货商，如果该采购商已有采购合同，就不能删除了，总公司对分公司只有查询权限
	 * @return void    返回类型
	 * @author 徐玲
	 * @throws ParseException 
	 * @Date 2019年7月18日下午2:43:43
	  */
	 public void onClick$delButton() throws ParseException{
		 	if(listbox.getSelectedItem() == null){
		 		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 		return;
		 	}else{
		 	  	Suppliers suppliers = listbox.getSelectedItem().getValue();//选中的那条供应商的记录
	 		       User user = (User)session.getAttribute("user");
	 				String userCompanyId = user.getCompanyid() ;//当前登录用户的公司
	 			  	if (suppliers !=null) {
	 		       		String supplierCompanyId = suppliers.getCompanyid();
	 		       		if (userCompanyId != null && !userCompanyId.equals(supplierCompanyId)) {
							//供应商的公司和当前登录用户的公司不同，不能删除
	 		       		  Messagebox.show("无删除权限","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 		       		  return;
						}else {
							//有删除权限
		 		Messagebox.show("确定删除吗?", "注意", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener(){
		 		    public void onEvent(Event evt) throws  ParseException {
		 		        if (evt.getName().equals("onOK")) {
		 		        	//确认删除
		 		        	Map map = new HashMap<Object,Object>();
		 		         	Map map1 = new HashMap<Object,Object>();
								int supplierId = 	suppliers.getId();
			 		       		String gysid = String.valueOf(supplierId);
			 		       		map1.put("gysid",gysid );
			 		       		List<Cght> cghts = cghtService.selectCghtBySupplier(map1);
			 		       		if (cghts != null && cghts.size() >0) {
			 		       		  Messagebox.show("已有采购合同，不能删除","注意", Messagebox.OK, Messagebox.EXCLAMATION);
								}else {
									//可以删除
									map.put("id",supplierId );
									int result = suppliersService.deleteSuppliers(map);
							 	    onClick$queryButton();
							 	    Messagebox.show("删除成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
								}
							}
		 		       else {
	                         //点击取消按钮
			 		        }
		 		        } 
		 		});
		 		    }
		
	 			  	}
		 	}
		 }
	 /**
	  *
	 * @Title: param
	 * @Description: 查询条件的设置
	 * @return void    返回类型
	 * @author 徐玲
	 * @Date 2019年7月17日下午4:57:26
	  */
	 private void param(Map<Object,Object> map) throws ParseException{
		 if(name.getValue() != null && !"".equals(name.getValue())){
				map.put("name", name.getValue().trim());
			}
			if(address.getValue() != null && !"".equals(address.getValue())){
				map.put("address", address.getValue().trim());
			}
			//获得当前用户所在公司的级别，如果为1,查询条件是选中的公司，否则，只显示本公司的
			User user = (User)session.getAttribute("user");
			
			if (user.getCompanyid() != null && "1".equals(user.getCompanyid())) {
				//总公司的
				if (company.getValue() != null && !"".equals(company.getValue())) {
					//下拉框中的值不为空
					if(company.getSelectedItem() != null){
						//是选中的情况下，赋值选中的
						map.put("companyid", company.getSelectedItem().getValue());
					}else{
						//不是选中的情况下
						map.put("companyid",user.getCompanyid());
					}
				}else {
					//下拉框的值为空
					map.put("companyid", null);
				}
				
			}else {
				//分公司的
				map.put("companyid", user.getCompanyid());
			}
		
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
}

