package com.pjgl.company.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.yewu.zscq.bean.User;

@Controller("editCompanyController")   
@Scope("prototype") 
public class EditCompanyController extends GenericForwardComposer{
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	Window editCompanyWin;
	
	private Company company;//接收要编辑的记录
	//输入框的对象
	Textbox companyName;//公司名称
	Textbox address;//公司地址
	Textbox contact;//联系人
	Textbox mobile;//联系方式
	Textbox email;//
	Textbox remarks;//公司的备注
	Textbox fax_1;//公司的传真
	
	//获取service对象
	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	@Override	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
//		Company company = commanybox.getSelectedItem().getValue();
		company = (Company)Executions.getCurrent().getArg().get("company");
		 companyName.setValue(company.getCompanyName());
		 address.setValue(company.getAddress());
		 contact.setValue(company.getContact());
		 mobile.setValue(company.getMobile());
		 email.setValue(company.getEmail());
		 remarks.setValue(company.getRemarks());
		 fax_1.setValue(company.getFax_1());
//		 companyName.setReadonly(true);
//		 System.out.println("company.getId()==="+company.getId());
		 if(company.getId() == 1){
		       companyName.setReadonly(true);
		     }
	}
	/**
	 * 更新记录
	 * @throws ParseException 
	 */
	public void onClick$editBtn() throws ParseException{
		Map<Object,Object> map = new HashMap<>();
		getInfoFromPage(map);
		map.put("id", company.getId());
 			try{
 				int result = companyService.editCompany(map);
 				System.out.println();
 				editCompanyWin.detach();//关闭窗口
 				CompanyController companyController = (CompanyController)session.getAttribute("companyController");
 				companyController.onClick$queryButton();
 				Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
 			} catch (Exception e) {			
 					Messagebox.show("该公司已经存在","注意", Messagebox.OK, Messagebox.EXCLAMATION);				
 			}
				
	}
	private void getInfoFromPage(Map<Object,Object> map){
		if(companyName.getValue() != null && !"".equals(companyName.getValue())){
			map.put("companyName", companyName.getValue().trim());
		}

		if(address.getValue() != null && !"".equals(address.getValue())){
			map.put("address", address.getValue().trim());
		}
		if(contact.getValue() != null && !"".equals(contact.getValue())){
			map.put("contact",contact.getValue().trim() );
		}          
		
		if(mobile.getValue() != null && !"".equals(mobile.getValue())){
			map.put("mobile",mobile.getValue().trim() );
		}if(email.getValue() != null && !"".equals(email.getValue())){
			map.put("email",email.getValue().trim() );
		}
		if(remarks.getValue() != null && !"".equals(remarks.getValue())){
			map.put("remarks",remarks.getValue().trim() );
		}
		if(fax_1.getValue() != null && !"".equals(fax_1.getValue())){
			map.put("fax_1",fax_1.getValue().trim() );
		}
	}

}
