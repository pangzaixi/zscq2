package com.pjgl.pj.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import com.pjgl.pj.bean.Pj;
import com.pjgl.pj.servcie.serviceimpl.PjService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.system.zzjg.bean.Zzjg;

public class EditPjController extends GenericForwardComposer{

	Textbox name; // '配件名称',
	Textbox spec; // '规格',
	Textbox unit; //'单位，件、个、只、公斤等',
	Textbox brand; //'品牌，约翰迪尔、凯斯、通用等',
	Textbox remarks; //'备注',
	Combobox company; //'所属公司ID',
	Intbox upper_limit; //'库存提醒上限',
	Intbox lower_limit; //'库存提醒下限',
	Textbox code;//条形码
	
	Pj pj;
	
	Window editPjWin;
	
	//获取service对象
	PjService pjService = (PjService)SpringUtil.getBean("pjService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		pj = (Pj)Executions.getCurrent().getArg().get("pj");
		name.setValue(pj.getName());
		spec.setValue(pj.getSpec());
		unit.setValue(pj.getUnit());
		brand.setValue(pj.getBrand());
		remarks.setValue(pj.getRemarks());
		upper_limit.setValue(pj.getUpper_limit());
		lower_limit.setValue(pj.getLower_limit());
		code.setValue(pj.getCode());
		
	}
	
	/**
	 * 增加配件记录
	 */
	public void onClick$addBtn() throws ParseException{
		
		Map<Object,Object> map = new HashMap<>();
		
		map.put("name", name.getValue().trim());
		map.put("spec", spec.getValue().trim());
		map.put("unit", unit.getValue().trim());
		map.put("brand", brand.getValue().trim());
//		map.put("companyid", company.getSelectedItem().getValue());
		map.put("remarks", remarks.getValue().trim());
		map.put("upper_limit", upper_limit.getValue());
		map.put("lower_limit", lower_limit.getValue());
		map.put("id", pj.getId());
		
//		getInfoFromPage(map);//获取输入参数
		try {
			int result = pjService.editPj(map);
			System.out.println();
			editPjWin.detach();//关闭窗口
			PjController pjController = (PjController)session.getAttribute("pjController");
			pjController.onClick$queryButton();
			Messagebox.show("修改成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			Messagebox.show("配件已存在，请重新输入！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
}
