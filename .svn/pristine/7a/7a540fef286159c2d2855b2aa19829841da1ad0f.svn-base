package com.pjgl.suppliers.controller;

import java.text.ParseException;
import java.util.Date;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.suppliers.service.serviceImpl.SuppliersService;
import com.yewu.zscq.bean.User;

/**
* @ClassName: AddSuppliersController
* @Description: 新增供货商Controller
* @author 徐玲
* @date 2019年7月18日上午9:32:55
*
 */
@Controller("addSuppliersController")   
@Scope("prototype") 
public class AddSuppliersController  extends GenericForwardComposer {
	//窗体对象
	Window addSuppliersWin;
	
	//页面输入组件
	Textbox name;//名称
	Textbox address;//地址
	Textbox companyName;//公司名称
	Textbox contact;//联系人
	Textbox mobile;//联系方式
	Textbox remarks;//备注
	

	
	//获取service对象
	SuppliersService suppliersService = (SuppliersService)SpringUtil.getBean("suppliersService");
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	/**
	 * 
	* @Title: doAfterCompose
	* @Description: 初始化方法
	* @param 
	* @author 徐玲
	* @Date 2019年7月18日上午9:56:42
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		session.setAttribute("addSuppliersController", this.getController());
		
		User user = (User)session.getAttribute("user");
		String userCompanyId = user.getCompanyid() ;
		if (userCompanyId != null && !"".equals(userCompanyId)) {
			Company company = companyService.getById(Integer.valueOf(userCompanyId));
			if (company != null ) {
				String getCompanyName = company.getCompanyName();
				if (getCompanyName != null && !"".equals(getCompanyName)) {
					companyName.setValue(getCompanyName);
				}
			}
		}
		
	}
	/**
	 * 
	* @Title: onClick$addBtn
	* @Description: 增加供应商记录
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月18日上午9:58:08
	 */
	public void onClick$addBtn() throws ParseException{
		Map map = new HashMap<Object,Object>();
		getInfoFromPage(map);
		try {
			int result = suppliersService.insertSuppliers(map);
			addSuppliersWin.detach();
			SuppliersController suppliersController =(SuppliersController) session.getAttribute("suppliersController");
			suppliersController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			Messagebox.show("供货商名称重复","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	
	}
	/**
	* @Title: getInfoFromPage
	* @Description: 获取页面输入数据
	* @return void    返回类型
	* @author 徐玲
	* @Date 2019年7月18日上午10:00:17
	 */
	private void getInfoFromPage(Map<Object,Object> map){
		if(name.getValue() != null && !"".equals(name.getValue())){
			map.put("name", name.getValue().trim());
		}
		User user = (User)session.getAttribute("user");
		if (user.getCompanyid() != null && !"".equals(user.getCompanyid())) {
			map.put("companyid", user.getCompanyid());
		}
		if(address.getValue() != null && !"".equals(address.getValue())){
			map.put("address", address.getValue().trim());
		}
		if(contact.getValue() != null && !"".equals(contact.getValue())){
			map.put("contact",contact.getValue().trim() );
		}          
		
		if(mobile.getValue() != null && !"".equals(mobile.getValue())){
			map.put("mobile",mobile.getValue().trim() );
		}
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue().trim() );
		}
	}
	
	
}
