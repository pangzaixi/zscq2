package com.pjgl.pj.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.company.bean.Company;
import com.pjgl.company.controller.CompanyController;
import com.pjgl.company.service.serviceimpl.CompanyService;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.system.zzjg.bean.Zzjg;
import com.yewu.zscq.bean.User;

public class AddPjController extends GenericForwardComposer{

	Textbox name; // '配件名称',
	Textbox spec; // '规格',
	Textbox unit; //'单位，件、个、只、公斤等',
	Textbox brand; //'品牌，约翰迪尔、凯斯、通用等',
	Textbox remarks; //'备注',
	Combobox company; //'所属公司ID',
	Intbox upper_limit; //'库存提醒上限',
	Intbox lower_limit; //'库存提醒下限',
	
	Window addPjWin;
	
	//获取service对象
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
//	CompanyService companyService = (CompanyService)SpringUtil.getBean("companyService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
//		Map map = new HashMap<Object,Object>();
//		map.put("begin", 0);
//		map.put("end", 100);
//		List<Company> list = companyService.selectCompany(map);
//		ListModel companyModel = new ListModelList(list);
//		company.setModel(companyModel);
	}
	
	/**
	 * 增加配件记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		
		
		Map<Object,Object> map = new HashMap<>();
		User user = (User)session.getAttribute("user");
		long timeStampSec = System.currentTimeMillis()/1000;
        String code = String.format("%010d", timeStampSec);
		
		map.put("companyid", user.getCompanyid());
		map.put("name", name.getValue().trim());
		map.put("spec", spec.getValue().trim());
		map.put("unit", unit.getValue().trim());
		map.put("brand", brand.getValue().trim());
//		map.put("companyid", company.getSelectedItem().getValue());
		map.put("remarks", remarks.getValue().trim());
		if (upper_limit.getValue() != null) {
			map.put("upper_limit", upper_limit.getValue());
		}else {
			map.put("upper_limit", 0);
		}
		if (lower_limit.getValue() != null) {
			map.put("lower_limit", lower_limit.getValue());
		}else {
			map.put("lower_limit", 0);
		}
		map.put("code", code);
		
//		getInfoFromPage(map);//获取输入参数
		try {
			int result = pjService.insertPj(map);
			System.out.println();
			addPjWin.detach();//关闭窗口
			PjController pjController = (PjController)session.getAttribute("pjController");
			pjController.onClick$queryButton();
			Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			Messagebox.show("配件已存在，请重新输入！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
}
