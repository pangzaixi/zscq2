package com.zscq2.zhangdan.conftroller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.East;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;
import com.zscq2.zhangdan.service.ZdService;


@Controller("addZdController")   
@Scope("prototype") 
public class AddZdController    extends GenericForwardComposer{
Window addZdWin;
West west;
Center center;
East east;
Listbox listbox1;	
Listbox listbox2;

Combobox ajlx;//案件类型
Combobox wtkhmc;
Textbox ggq;
Textbox byyr;
Textbox sbmc;
Datebox khwtrq;
Label remind;
Textbox  wtkhmc2;
Datebox wtrq1;
Datebox wtrq2;
Datebox wtrq_zd;
Textbox zdnum;
Textbox zdid;
Intbox gf;
Intbox dlf;
Textbox wtnr;
Button saveButton;
Button clearButton;
Label l_sumgf;
Label l_sumdlf;
Label l_sumgf_yt;
Label l_sumdlf_yt;
Textbox sbh;
Doublebox perc_yt;

ZdBean zdBean = new ZdBean();//账单主表对象
//获取service对象
ZdService zdService = (ZdService)SpringUtil.getBean("zdService");
LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");

List listbox2_list = new ArrayList<ZdDetail>();
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("addZdController", this.getController());
	
	
	//委托客户
	List<Lazb> list_wtkh = zdService.selectAllWtkhmc(null);
	wtkhmc.setModel(new ListModelList(list_wtkh));
	
	
	//案件类型
    List<Ajlx> list_ajlx = lazbService.selectAjlx(null);
	ListModel comboboxModelList =new ListModelList<Ajlx>();
	comboboxModelList = null;
	comboboxModelList = new ListModelList(list_ajlx);
	ajlx.setModel(comboboxModelList);
	
}	
/**
 * 查询方法
 */
public void onClick$queryButton(){

	Map map = new HashMap<Object,Object>();
	if(wtkhmc.getValue() != null && !"".equals(wtkhmc.getValue())){
		map.put("wtkhmc", wtkhmc.getValue());
	}else{
		Messagebox.show("请选择委托客户");
		return;
	}
	if(ajlx.getValue() != null  && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getSelectedItem().getLabel());
	}else{
		Messagebox.show("请选择案件类型");
		return;
	}
	if(ggq.getValue() != null  && !"".equals(ggq.getValue())){
		map.put("ggq", ggq.getValue());
	}
	if(byyr.getValue() != null  && !"".equals(byyr.getValue())){
		map.put("byyr", byyr.getValue());
	}
	if(sbmc.getValue() != null  && !"".equals(sbmc.getValue())){
		map.put("sbmc", sbmc.getValue());
	}
	
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	if(wtrq1.getValue()!=null){
		map.put("wtrq1", f.format(wtrq1.getValue()));
	}
	if(wtrq2.getValue()!=null){
		Calendar calendar = new GregorianCalendar();
		Date date = wtrq2.getValue();
		calendar.setTime(date); 
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		map.put("wtrq2", f.format(date));
	}
	if(sbh.getValue() != null  && !"".equals(sbh.getValue())){
		map.put("sbh", sbh.getValue().trim());
	}
	ListModel list_t =new ListModelList<ZdDetail>();
	list_t = listbox2.getModel();
	if(list_t != null && list_t.getSize()>0){
		String ids="";
		for(int i=0;i<list_t.getSize();i++){
			ZdDetail a = (ZdDetail)list_t.getElementAt(i);
			if(i==list_t.getSize()-1){
				ids = ids+a.getAjid();
			}else{
				ids = ids+a.getAjid()+",";
			}
			
		}
		map.put("ids", ids);
	}
	
	
	List<Lazb> list = zdService.selectJXQD(map);
	int count =zdService.selectJXQD_count(map);
	if(count>300){
		remind.setValue("当前查询条件下，符合条件的记录大于300条，只显示前300条");
	}else{
		remind.setValue("");
	}
	ListModel listModelList =new ListModelList<Lazb>();
	listModelList = null;
	listModelList = new ListModelList(list);
	listbox1.setModel(listModelList);
	listbox1.invalidate();	
	listbox1.focus();
}
/**
 * 双击选中的左侧列表记录，添加到右侧列表
 */
public void onDoubleClick$listbox1(){
//	if(listbox1.getSelectedItem() == null){
//		return;
//	}else{
//		addSelectItem(listbox1.getSelectedItem().getValue());
//	}
//	
//	 Collections.sort(listbox2_list);  
//	ListModel listModelList =new ListModelList(listbox2_list);
//	
//	listbox2.setModel(listModelList);
//	
//	onClick$queryButton();//重新查询，不显示已经被选中的案子
}

public void onClick$clearButton(){
	listbox2_list.clear();
	ListModel listModelList =new ListModelList(listbox2_list);
	
	listbox2.setModel(listModelList);
}
public void onClick$addAllButton(){
//	ListModel listModelList =new ListModelList<Lazb>();
//	listModelList = listbox1.getModel();
//	if(listModelList != null && listModelList.getSize()>0){
//		for(int i=0;i<listModelList.getSize();i++)	{
//			if(listModelList.getElementAt(i) == null){
//				continue;
//			}
//			addSelectItem((Lazb)listModelList.getElementAt(i));
//		}
//	}
//	System.out.println("00000:"+listModelList.getSize());
//	System.out.println("11111:"+listbox2_list.size());
//	 Collections.sort(listbox2_list);  
//	 
//	System.out.println("222222:"+listbox2_list.size());
//	ListModel listModelList3 =new ListModelList(listbox2_list);
//	
//	listbox2.setModel(listModelList3);
//	
//	onClick$queryButton();//重新查询，不显示已经被选中的案子
	////////////////////////////////////////////////////////////////////
	Set<Listitem> set = listbox1.getSelectedItems();
	Object[] lazb_sz = set.toArray();
	if(lazb_sz.length==0){
		Messagebox.show("请选择案件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
    for(int i=0;i<lazb_sz.length;i++){
    	Listitem o = (Listitem)lazb_sz[i];
		Lazb lazb = (Lazb)o.getValue();
		addSelectItem(lazb); 
    }	
    
    Collections.sort(listbox2_list);
    ListModel listModelList3 =new ListModelList(listbox2_list);
	listbox2.setModel(listModelList3);
	onClick$queryButton();//重新查询，不显示已经被选中的案子
}
/**
 * 将选中的案件加入右侧列表
 */
private void addSelectItem(Lazb lazb){
	System.out.println();
	
	for(int a = 0;a<listbox2_list.size();a++){
		ZdDetail l1 = (ZdDetail)listbox2_list.get(a);
		if(l1.getAjh().equals(lazb.getAjh())){
			return;
		}
	}
	ZdDetail a = new ZdDetail();
	a.setAjh(lazb.getAjh());
	a.setAjid(Integer.parseInt(lazb.getId()));
	a.setSbh(lazb.getSbh());
	a.setSbmc(lazb.getSbmc());
	a.setAjlx(lazb.getAjlx());
	a.setLb(lazb.getLb());
	a.setGgq(lazb.getGgq());
	listbox2_list.add(a);

	
	
}
/**
 * 更新选中的委托客户，并清空已选择的案件
 */
public void onSelect$wtkhmc(){
	System.out.println(wtkhmc.getValue());
	wtkhmc2.setValue(wtkhmc.getValue());
	
	listbox2_list.clear();
	ListModel listModelList =new ListModelList(listbox2_list);
	listModelList = null;
	listbox2.setModel(listModelList);
}
/**
 * 选中案件类型，清空已选择的案件
 */
public void onSelect$ajlx(){
	onSelect$wtkhmc();
}
/**
 * 保存或更新账单生成账单
 */
public void o1nClick$saveButton(){
	
	
//	west.setVisible(false);//点击保存后不存续新增
	if(listbox2_list.size()==0){
		Messagebox.show("请选择案件");
		return;
	}
	east.setVisible(true);
	

	west.setSize("0%");
	west.setSplittable(false);
	east.setSize("40%");
	//账单号：ZD19TNS2968XX
	String zd = "ZD";
	Calendar date = Calendar.getInstance();
    String year = String.valueOf(date.get(Calendar.YEAR));
    zd=zd+year.substring(2);
    zd=zd+ajlx.getSelectedItem().getValue();
    
    
    
	ListModel listModelList =new ListModelList<Lazb>();
	if(listbox2_list.size()==1){
		ZdDetail la_begin = (ZdDetail)listbox2_list.get(0);
		zd=zd+la_begin.getAjh().substring(5)+"XX";
	}else if(listbox2_list.size()>1){
		ZdDetail la_begin = (ZdDetail)listbox2_list.get(0);
		ZdDetail la_end = (ZdDetail)listbox2_list.get(listbox2_list.size()-1);
		zd=zd+la_begin.getAjh().substring(5)+la_end.getAjh().substring(7);
	}
    
	zdnum.setValue(zd);
	
	Map map = new HashMap<Object,Object>();
	if(zd != null && !"".equals(zd)){//未保存，调用insert
		
		map.put("zdnum", zdnum.getValue());
		map.put("wtkh", wtkhmc.getValue());
		map.put("ajlx", ajlx.getSelectedItem().getLabel());
		map.put("sumgf", 1);
		map.put("sumdlf", 2);
		
		
		zdBean.setZdnum(zdnum.getValue());
		zdBean.setWtkh(wtkhmc.getValue());
		zdBean.setAjlx(ajlx.getSelectedItem().getLabel());
		

		zdService.insertZd(zdBean);
		if(listbox2_list.size()>=1){
			for(int i=0;i<listbox2_list.size();i++){//升序排列
				ZdDetail zdDetail = (ZdDetail)listbox2_list.get(i);
				zdDetail.setZdid(zdBean.getId());
				
				
				zdService.insertZd_sub(zdDetail);
				
			}
		}
//		
		System.out.println();
	}else{//已经保存过，调用update
		
	}
	Messagebox.show("操作完成");
}
/**
 * 更新所有选中案件的官费代理费，官费代理费不一定所有选中的案件都一样
 */
public void onClick$updateAll(){
	if(gf.getValue() == null || dlf.getValue() == null){
		Messagebox.show("请输入官费代理费");
		return;
	}
	if(perc_yt.getValue() == null || perc_yt.getValue() == 0 ){
		Messagebox.show("请输入盈天代理费百分比");
		return;
	}
	String ids="";
	Map map = new HashMap<Object,Object>();
	for(int i=0;i<listbox2_list.size();i++){//升序排列
		ZdDetail z = (ZdDetail)listbox2_list.get(i);
		z.setGf(gf.getValue());
		z.setDlf(dlf.getValue());
//		z.setGf_yt(gf.getValue()*perc_yt.getValue()/100);
		z.setGf_yt(0);
		z.setDlfYt(dlf.getValue()*perc_yt.getValue()/100);
		z.setWtnr(wtnr.getValue());
		if(i==listbox2_list.size()-1){
			ids = ids+z.getId();
		}else{
			ids = ids+z.getId()+",";
		}
		System.out.println();
	}
	
	map.put("ids", ids);
	map.put("gf", gf.getValue());
	map.put("dlf", dlf.getValue());
	map.put("wtnr", wtnr.getValue());
//	map.put("gfYt", gf.getValue()*perc_yt.getValue()/100);
	map.put("gfYt", 0);
	map.put("dlfYt", dlf.getValue()*perc_yt.getValue()/100);
	
	System.out.println();
	zdService.updateZd_sub(map);//更新子表官费代理费
	DecimalFormat df = new DecimalFormat("0.##");
	
	int sumgf = gf.getValue()*listbox2_list.size();
	int sumdlf = dlf.getValue()*listbox2_list.size();
	
	zdBean.setSumgf(sumgf);
	zdBean.setSumdlf(sumdlf);
//	zdBean.setSumgfYt(sumgf*perc_yt.getValue()/100);
	zdBean.setSumgfYt(0);
	zdBean.setSumdlfYt(Double.valueOf(df.format(sumdlf*perc_yt.getValue()/100)));
	zdBean.setWtrqZd(wtrq_zd.getValue());
	l_sumgf.setValue("总官费："+sumgf);
	l_sumdlf.setValue("总代理费："+sumdlf);
//	l_sumgf_yt.setValue("盈天总官费："+sumgf*perc_yt.getValue()/100);
	l_sumdlf_yt.setValue("盈天总代理费："+df.format(sumdlf*perc_yt.getValue()/100));
	zdService.updateZd(zdBean);
	Messagebox.show("全体更新成功");
	

	//更新列表
	ListModel listModelList =new ListModelList(listbox2_list);
	listbox2.setModel(listModelList);
}

/**
 * 更新当前选中记录的官费代理费，官费代理费不一定所有选中的案件都一样
 */
public void onClick$updateCurrent(){
	if(gf.getValue() == null || dlf.getValue() == null){
		Messagebox.show("请输入官费代理费");
		return;
	}
	if(listbox2.getSelectedItem() == null){
		Messagebox.show("请选择案件");
		return;
	}
	if(perc_yt.getValue() == null || perc_yt.getValue() == 0 ){
		Messagebox.show("请输入盈天代理费百分比");
		return;
	}
	ZdDetail zdDetail = listbox2.getSelectedItem().getValue();
	zdDetail.setGf(gf.getValue());
	zdDetail.setDlf(dlf.getValue());
	zdDetail.setWtnr(wtnr.getValue());
	Map map = new HashMap<Object,Object>();
	map.put("ids", zdDetail.getId());
	map.put("gf", gf.getValue());
	map.put("dlf", dlf.getValue());
	map.put("wtnr", wtnr.getValue());
//	map.put("gfYt", gf.getValue()*perc_yt.getValue()/100);
	map.put("dlfYt", dlf.getValue()*perc_yt.getValue()/100);
	zdService.updateZd_sub(map);//更新子表官费代理费
	DecimalFormat df = new DecimalFormat("0.##");
	double sumgf =0;
	double sumdlf = 0;
	double sumgfYt =0;
	double sumdlfYt = 0;
	ZdDetail t = listbox2.getSelectedItem().getValue();
	for(int i=0;i<listbox2_list.size();i++){//升序排列
		ZdDetail z = (ZdDetail)listbox2_list.get(i);
		if(t.getId().trim().equals(z.getId().trim())){
			z.setGf(gf.getValue());
			z.setDlf(dlf.getValue());
//			z.setGf_yt(gf.getValue()*perc_yt.getValue()/100);
			z.setDlfYt(dlf.getValue()*perc_yt.getValue()/100);
			z.setWtnr(wtnr.getValue());
		}
		sumgf = sumgf+z.getGf();
		sumdlf = sumdlf+z.getDlf();
//		sumgfYt = sumgfYt+z.getGf_yt();
		sumdlfYt = sumdlfYt+z.getDlfYt();
	}	
	zdBean.setSumgf(sumgf);
	zdBean.setSumdlf(sumdlf);
	zdBean.setWtrqZd(wtrq_zd.getValue());
	zdBean.setSumdlfYt(Double.valueOf(df.format(sumdlfYt)));
	l_sumgf.setValue("总官费："+sumgf);
	l_sumdlf.setValue("总代理费："+sumdlf);
//	l_sumgf_yt.setValue("盈天总官费："+sumgfYt);
	l_sumdlf_yt.setValue("盈天总代理费："+df.format(sumdlfYt));
	zdService.updateZd(zdBean);
	Messagebox.show("当前记录更新成功");
	
	//更新列表
		ListModel listModelList =new ListModelList(listbox2_list);
		listbox2.setModel(listModelList);
}
}