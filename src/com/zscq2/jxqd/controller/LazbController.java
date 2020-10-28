package com.zscq2.jxqd.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.filechooser.FileSystemView;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.lowagie.text.PageSize;
import com.system.base.Qjbl;
import com.system.utils.LogManage;
import com.system.utils.Screen;
import com.system.utils.excel.Column;
import com.system.utils.excel.Excel;
import com.system.utils.excel.Row;
import com.system.utils.excel.Sheet;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.controller.WenjianContorller;
import com.yewu.zscq.service.WenjianService;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.jxqd.util.ReadExcelUtil;

import menu2.BorderLayoutComposer;
import menu2.MenuNode;

@Controller("lazbController")   
@Scope("prototype") 
public class LazbController    extends GenericForwardComposer{
	//案件类型检查
//	String ajlx = "商标异议申请,商标驳回复审,补正,全部驳回,商标驳回复审行政诉讼,商标注册申请,驳回复审决定,部分驳回,商标撤三申请,商标撤三复审,商标撤三答辩,商标驳回复审补正,商标驳回,商标补正,商标转让,商标异议答辩,商标异议补正,商标部分驳回,商标驳回决定,商标无效宣告,异议决定,商标地址变更,商标续展,商标变更,商标撤三决定,撤销复审答辩,诉讼,商标撤三补正,商标撤销复审,撤销复审决定,商标分割申请,证据交换,商标异议予以注册,异议决定准予注册,商标异议答辩通知,商标异议撤回,商标无效宣告撤回,商标异议答辩补正,异议决定不准予注册,撤三决定,分割申请,商标异议答辨,商标变更补正,商标撤回补正,商标无效宣告答辩,不予注册复审,商标行政诉讼,驳回复审,补正证据,异议补正,撤三复审,无效宣告行政诉讼,是否提无效宣告";
	String ajlx_str="";
	Window jxqdwin;
	Combobox pagesize;//页面记录数下拉选
	Boolean pagingOnClick = false;//是否点击的分页标签
	
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
		
	Label jxlx;	
	Label recordCount;	
	Textbox ajh;//案件号
	Textbox sbmc;//商标名称
	Textbox bsqr;//被申请人
	Textbox sqr;//申请人
	
	Intbox sblb;//商标类别
	Textbox sbh;//商标号
	Intbox ggq;//公告期
	Combobox dlr;//代理人
	Combobox dlr2;//代理人
	Combobox ajlx;//案件类型
	Datebox sqrq_q1;//申请日期
	Datebox sqrq_q2;//申请日期
	Datebox jxrq_q1;//绝限日期
	Datebox jxrq_q2;//绝限日期
	Radio ra1;//最近一个月的绝限案件
	Radio ra2;//最近二个月的绝限案件
	//××××××××××××数据源
	
		Listbox listbox;//页面遍历方式加载数据
		Paging paging;

public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("lazbController", this.getController());
	
	int screenWidth = (int)session.getAttribute("screenWidth");
	int screenHeight = (int)session.getAttribute("screenHeight");
    int[] t  =Screen.widthAndHeight("2",screenWidth,screenHeight);
        
    jxqdwin.setWidth(screenWidth-t[0]+"px");
    jxqdwin.setHeight(screenHeight-t[1]+"px");
    
    
	Map map = new HashMap<Object,Object>();
	List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
	for(Ajlx ajlx:list_ajlx){
		ajlx_str = ajlx_str+","+ajlx.getAjlx();
	}
	
	//案件类型
	ListModel comboboxModelList =new ListModelList<Ajlx>();
	comboboxModelList = null;
	comboboxModelList = new ListModelList(list_ajlx);
	ajlx.setModel(comboboxModelList);
	
	
	//代理人1
	List<Lazb> list_dlr1 = lazbService.selectDlr1();
	dlr.setModel(new ListModelList(list_dlr1));
	
	
	
	//代理人2
	List<Lazb> list_dlr2 = lazbService.selectDlr2();
	dlr2.setModel(new ListModelList(list_dlr2));
	
}

/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Lazb> list = lazbService.selectJXQD(map);
	for(Lazb tmp:list){
		Date date = new Date();
		if(tmp.getJxr() != null){
			double days = 0;
			if(tmp.getBcqx() != null){
				days = new Double(tmp.getBcqx().getTime() - date.getTime()) / new Double(1000*3600*24);
			}else{
				days = new Double(tmp.getJxr().getTime() - date.getTime()) / new Double(1000*3600*24);
			}
			
			
			if(days<=0 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:red");
			}else if(days>0 && days<=3 && "未完结".equals(tmp.getStatus())){	
				tmp.setStyle("color:orange");
			}else if(days>3 && days<=6 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:violet");
			}else if(days>6 && days<=10 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:blue");
			}
		}
		
	}
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	
	listbox.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	int count = lazbService.selectJXQD_count(map);
	recordCount.setValue("记录总数："+count);
	paging.setTotalSize(count);
	
//	paging.setActivePage(0);
	

}
private void param(Map<Object,Object> map) throws ParseException{
	if(ajh.getValue() != null && !"".equals(ajh.getValue())){
		map.put("ajh", ajh.getValue());
	}
	if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
		map.put("sbmc", sbmc.getValue());
	}
	if(bsqr.getValue() != null && !"".equals(bsqr.getValue())){
		map.put("bsqr", bsqr.getValue());
	}
	if(sqr.getValue() != null && !"".equals(sqr.getValue())){
		map.put("sqr", sqr.getValue());
	}
	if(sblb.getValue() != null && !"".equals(sblb.getValue())){
		map.put("sblb", sblb.getValue());
	}
	if(sbh.getValue() != null && !"".equals(sbh.getValue())){
		map.put("sbh", sbh.getValue());
	}
	if(ggq.getValue() != null && !"".equals(ggq.getValue())){
		map.put("ggq", ggq.getValue());
	}
	if(dlr.getValue() != null && !"".equals(dlr.getValue())){
		map.put("dlr", dlr.getValue());
	}
	if(dlr2.getValue() != null && !"".equals(dlr2.getValue())){
		map.put("dlr2", dlr2.getValue());
	}
	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getValue());
	}
//	User user = (User)session.getAttribute("user");
//	if("2".equals(user.getLevel())){
//		map.put("user_name", user.getUser_name());
//	}
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	if(sqrq_q1.getValue()!=null){
		map.put("sqrq_q1", f.format(sqrq_q1.getValue()));
	}
	if(sqrq_q2.getValue()!=null){
		Calendar calendar = new GregorianCalendar();
		Date date = sqrq_q2.getValue();
		calendar.setTime(date); 
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		map.put("sqrq_q2", f.format(date));
	}
	if(jxrq_q1.getValue()!=null){
		map.put("jxrq_q1", f.format(jxrq_q1.getValue()));
	}
	if(jxrq_q2.getValue()!=null){
		Calendar calendar = new GregorianCalendar();
		Date date = jxrq_q2.getValue();
		calendar.setTime(date); 
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		map.put("jxrq_q2", f.format(date));
	}
	if(ra1.isSelected()){
		map.put("ra", true);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date()); 
		calendar.add(calendar.MONTH,1);//把日期往后增加一个月.整数往后推,负数往前移动
		Date date = calendar.getTime();
		map.put("jxrq_q2", f.format(date));
		
		
//		map.put("jxrq_q1", f.format(new Date())); //如果选择单选，则将已超过绝限日的记录也查出来
		map.put("jxrq_q2", f.format(date));
		
		map.put("status", "未完结");
		
	}
	if(ra2.isSelected()){
		map.put("ra", true);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date()); 
		calendar.add(calendar.MONTH,2);//把日期往后增加二个月.整数往后推,负数往前移动
		Date date = calendar.getTime();
		map.put("jxrq_q2", f.format(date));
		
		
//		map.put("jxrq_q1", f.format(new Date())); //如果选择单选，则将已超过绝限日的记录也查出来
		map.put("jxrq_q2", f.format(date));
		
		map.put("status", "未完结");
	}
	if("绝限日期".equals(jxlx.getValue())){
		map.put("jxlx", '1');//内部绝限
	}else{
		map.put("jxlx", '2');//官方绝限
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
	 ajh.setValue("");
		 sbmc.setValue("");
		 bsqr.setValue("");
		 sqr.setValue("");
		 sblb.setValue(null);
		 sbh.setValue(null);
		 ggq.setValue(null);
		 dlr.setValue("");
		 dlr2.setValue("");
		 ajlx.setValue("");
		 ajh.setValue("");
		 sqrq_q1.setValue(null);;
		 sqrq_q2.setValue(null);
		 jxrq_q1.setValue(null);;
		 jxrq_q2.setValue(null);
		 ra1.setSelected(false);
		 ra2.setSelected(false);
}
/**
 * 新增按钮,弹出编辑窗口
 */
public void onClick$addButton() {
    //create a window programmatically and use it as a modal dialog.
	Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("msg", "Hello ZK!");

    Window window = (Window)Executions.createComponents(
            "/jsp/zscq/jxqd/addJxqd.zul", jxqdwin, arg1);
    window.doModal();
    
}
/**
 * 编辑绝限记录
 * @throws ParseException 
 */
public void onClick$editButton() throws ParseException{
	
	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}else{
		Lazb lazb = (Lazb)listbox.getSelectedItem().getValue();
		Map<String, Object> arg1 = new HashMap<String, Object>();
		arg1.put("lazb", lazb);
		
		Window window = (Window)Executions.createComponents(
	            "/jsp/zscq/jxqd/editJxqd.zul", null, arg1);
	    
	    window.doModal();//模式弹窗
//	    window.doPopup();//非模式弹窗
	    
	    onClick$queryButton();//刷新父窗口表格
	}
}

/**
 * 上传文件
 */
public void onClick$uploadFile(){
	Window window = (Window)Executions.createComponents(
            "/jsp/zscq/jxqd/uploadFile.zul", null, null);
    
    window.doModal();//模式弹窗
}

/**
 * 删除立案记录
 * @throws ParseException 
 */
public void onClick$deleteButton() throws ParseException{
	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}else{
		Lazb lazb = (Lazb)listbox.getSelectedItem().getValue();
		
		
		System.out.println(lazb.getAllInfo());
		Messagebox.show("确认删除? \n案件号："+lazb.getAjh()+"\n 案件类型："+lazb.getAjlx()+"\n 商标名称："+ lazb.getSbmc()+"\n 类别"+ lazb.getLb(), 
				"请确认", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(org.zkoss.zk.ui.event.Event evt) throws java.lang.InterruptedException, ParseException {
		        if (evt.getName().equals("onOK")) {
		        	Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("id", lazb.getId());
					
					lazbService.deleteLazb(map);
					onClick$queryButton();//刷新父窗口表格
					
					//记录操作日志
					User user = (User)session.getAttribute("user");
					LogManage logManage = new LogManage();
					logManage.addLog("删除案件："+lazb.getAjh(),user,"案件删除");
					
		        } 
		    }
		});
	    
	    
	}
}


/**
 * excel数据导入
 * @throws Exception 
 */
public void onClick$excelImport() {
	Media media;
	 
	 media = (Media)session.getAttribute("excelImport");
	 //Messagebox.show(media.getName());
	 List<List<String>> list = new ArrayList<List<String>>();
	 if(media != null){
		 String filetype = media.getFormat();
		 System.out.println(filetype.indexOf("xls"));
		 
		 byte[] aa = media.getByteData();
		 InputStream is =    new ByteArrayInputStream(aa);
		 
		 if(filetype.indexOf("xls") == 0){
			 try {
				list = ReadExcelUtil.readExcelInfo(media.getStreamData(), filetype);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 Messagebox.show("导入失败 \n 请检查excel文件格式，例如最后一行不能有空行等","注意", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
			}
		 }else{
			 Messagebox.show("请选择excel文件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
		 }
	 }
	//循环处理读取内容
	 if(list.size()>1){
		 if(list.get(0).size()!=29){
			 Messagebox.show("数据内容异常，请检查模板","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
		 }
		 
		 List<Lazb> list_lazb = changeToLazb(list);//将读取数据转换为立案记录集合
		 if(list_lazb == null){
			 return;
		 }
		 
		 String check_res = checkData(list_lazb);//检查excel中数据内容是否合法
		 if(check_res != null){
			Messagebox.show("数据内容异常: \n"+check_res,"注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		 }
		 
		 //结果集正确，则将结果展示在弹出页中
		 Map<String, Object> arg1 = new HashMap<String, Object>();
			arg1.put("list_lazb", list_lazb);
			
			Window window = (Window)Executions.createComponents(
		            "/jsp/zscq/jxqd/import.zul", null, arg1);
		    
		    window.doModal();//模式弹窗
	 }
	 
	
	
}
/**
 * 检查excel中数据内容是否合法
 * @param list
 * @return
 */
private String checkData(List<Lazb> list){

	for(Lazb lazb:list){
		
		if(lazb.getAjlx() == null){
			return " 案件类型不能为空";
		}
		if(!ajlx_str.contains(lazb.getAjlx())){
			return " 案件类型不合法:\n\n"+lazb.getAjlx()+"\n\n合法案件类型如下：\n"+ajlx_str;
		}
		
	}
	return null;
}

/**
 * 将excel读出内容转为lazb记录
 * @param list
 * @return
 */
public List<Lazb> changeToLazb(List<List<String>> list){
	List<Lazb> list_res = new ArrayList<Lazb>();
	try {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	for(int i =1;i<list.size();i++){
		 List<String> t = list.get(i);
		 System.out.println(t.toString());
		 Lazb l = new Lazb();
		 l.setAjlx(t.get(1));//案件类型
		 l.setSbmc(t.get(2));//商标名称
		 l.setLb(t.get(3));//类别
		 l.setSbh(t.get(4));//商标号
		 l.setAjzt(t.get(5));//案件状态
		 l.setKhwtrq(t.get(6) == null ? null: formatter.parse(t.get(6)));//客户委托日期
		 l.setSqrq(t.get(7) == null ? null: formatter.parse(t.get(7)));//申请日期
		 l.setSwrq(t.get(8) == null ? null: formatter.parse(t.get(8)));//收文日期
		 l.setCsrq(t.get(9) == null ? null: formatter.parse(t.get(9)));//初审日期
		 l.setGgq(t.get(10));//公告期
		 l.setJxr(t.get(11) == null ? null: formatter.parse(t.get(11)));//绝限日
		 l.setSctjr(t.get(12) == null ? null: formatter.parse(t.get(12)));//首次提交日
		 l.setScbsbh(t.get(13));//首次报送编号
		 l.setBcqx(t.get(14) == null ? null: formatter.parse(t.get(14)));//补充期限
		 l.setBctjrq(t.get(15) == null ? null: formatter.parse(t.get(15)));//补充提交日期
		 l.setEcbsbh(t.get(16));//二次报送编号
		 l.setWtkhmc(t.get(17));//委托客户名称
		 l.setByyr(t.get(18));//被异议人
		 l.setYysqr(t.get(19));//异议申请人
		 l.setGfyj(t.get(20));//官方意见
		 l.setGfyjswr(t.get(21) == null ? null: formatter.parse(t.get(21)));//官方意见收文日期
		 l.setGfyjjxr(t.get(22) == null ? null: formatter.parse(t.get(22)));//官方意见收文日期
		 l.setDlr(t.get(23));//代理人
		 l.setDlr2(t.get(24));//代理人2
		 l.setKhfk(t.get(25));//客户付款
		 l.setYtfk(t.get(26));//盈天付款
		 l.setAyr(t.get(27));//案源人
		 l.setDlgs(t.get(28));//代理公司
		 list_res.add(l);
	 }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 Messagebox.show("数据不合法，请检查文件中的日期内容是否符合yyyy/MM/dd格式","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return null;
	}
	return list_res;
}

/**
 * 下载文件
 */
public void onClick$downloadFile(){

	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	Lazb lazb = listbox.getSelectedItem().getValue();
	 Map<String, Object> arg1 = new HashMap<String, Object>();
		arg1.put("lazb", lazb);
	Window window = (Window)Executions.createComponents(
            "/jsp/zscq/jxqd/downLoadFile.zul", null, arg1);
    
    window.doModal();//模式弹窗
}
/**
 * 将当前案件跳转到商标页面
 */
public void onClick$changeButton(){

	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}else{
		
		Tabbox tabbox = (Tabbox)session.getAttribute("tabbox");
		List<Component> list = tabbox.getChildren();
		Tabs tabs = (Tabs)list.get(0);
		Tabpanels tabpanels = (Tabpanels)list.get(1);
		List<Tab> list_tab = (List)tabs.getChildren();
		for(int i=0;i<list_tab.size();i++){
			Tab tab = list_tab.get(i);
			if(tab.getLabel().trim().equals("商标文件查询")){
				System.out.println("该页面已经存在");
	//			tabpanels.invalidate();
				tabbox.setSelectedIndex(i);//该行使得分页标签显示为当前的页，
				//也就是最后一页		
				WenjianContorller wenjianContorller =(WenjianContorller)session.getAttribute("wenjianContorller");//用于从其他模块，例如绝限清单控制商标文件页面
				Jxqd jxqd = (Jxqd)listbox.getSelectedItem().getValue();
				wenjianContorller.ajh.setValue(jxqd.getAjh());
				wenjianContorller.onClick$queryButton();
				return;
			}
		}
		//如果上面循环中判断已存在页面，会触发return，跳出方法，不会执行下面的添加新tab页的动作
		BorderLayoutComposer borderLayoutComposer =(BorderLayoutComposer)session.getAttribute("borderLayoutComposer");
		borderLayoutComposer.getTabbox();
		
		Tab tab1 = new Tab();
		tab1.setClosable(true);
		tabs.appendChild(tab1);
		
		Tabpanel tabpanel = new Tabpanel();
		
		Div div = new Div();
		tabpanel.appendChild(div);
		tabpanels.appendChild(tabpanel);			
		
		//================
		
		Executions.createComponents("/jsp/zscq/wenjian/sb.zul",div,null);
		tab1.setLabel(String.valueOf("商标文件查询"));			

		//==================
		tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
		
		
		//向新家的商标文件传递参数		
		WenjianContorller wenjianContorller =(WenjianContorller)session.getAttribute("wenjianContorller");//用于从其他模块，例如绝限清单控制商标文件页面
		Jxqd jxqd = (Jxqd)listbox.getSelectedItem().getValue();
		wenjianContorller.ajh.setValue(jxqd.getAjh());
		wenjianContorller.onClick$queryButton();
	}
	
	
}

/**
 * 修改状态
 * @throws ParseException 
 */
public void onClick$changeStatus() throws ParseException{
	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}else{
		Lazb lazb = (Lazb)listbox.getSelectedItem().getValue();
		
		//获取service对象
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		Map<Object,Object> map = new HashMap<>();
		map.put("id", lazb.getId());
		map.put("status", "已完结");
		map.put("updatedate", new Date());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		lazbService.changeStatus(map);
		
		
		onClick$queryButton();
		
		
		//记录操作日志
		 LogManage logManage = new LogManage();
		 logManage.addLog("案件完结："+lazb.getAjh(),user,"案件完结");
		 Messagebox.show("案件"+lazb.getAjh()+"已完结","注意", Messagebox.OK, Messagebox.EXCLAMATION); 
	}	
	
	
}

/**
 * 当页面记录数下拉选值发生变化时修改表格单页记录数
 */
public void onChange$pagesize(){
	paging.setPageSize(Integer.parseInt(pagesize.getValue()));
	try {
		onClick$queryButton();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void onClick$ggqChange() throws ParseException{
	if(ggq.getValue() != null && !"".equals(ggq.getValue())){
		Map<Object,Object> map = new HashMap<>();
		map.put("ggq", ggq.getValue());
		map.put("status", "已完结");
		map.put("updatedate", new Date());
		User user = (User)session.getAttribute("user");
		map.put("updater", user.getUser_name());
		lazbService.changeStatus(map);
		
		
		onClick$queryButton();
		
		
		//记录操作日志
		 LogManage logManage = new LogManage();
		 logManage.addLog("按公告期 案件完结："+ggq.getValue(),user,"公告期完结");
		 Messagebox.show("公告期"+ggq.getValue()+" 对应所有案件已完结","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}else{
		 Messagebox.show("请输入公告期号","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
}

/**
 * 导出
 * @throws FileNotFoundException
 */
public void onClick$downButton() throws Exception{
	
	//获取service对象
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		Map map = new HashMap<Object,Object>();
		param(map);
		map.put("end", 100000);//如果是导出，则导出当前条件下的所有数据，而不是当前页
		List<Lazb> list = lazbService.selectJXQD(map);
		Sheet sheet = new Sheet();
		sheet.setName("立案总表");   
		FileSystemView fsv = FileSystemView.getFileSystemView();
		Date   dat=new   Date();     
        //dat.setTime(dat.getTime());                 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyyMMddHHmmddSSS");       
		String   timeStr   =   sdf.format(dat);
		File f=new File(fsv.getHomeDirectory()+File.separator+"立案总表"+timeStr+".xls"); 
		if(list != null){
			addRow(sheet,null,true);//增加表头
			for(int i=0;i<list.size();i++){
				addRow(sheet,list.get(i),false);
//				Row row = new Row();  
//	            List columnList = new ArrayList();  
//                Column column = new Column();  
//                column.setColumnLabel(list.get(i).getAjh());  
//                column.setColumnNum(i);  
//                columnList.add(column);  
//	              
//	            row.setColumnList(columnList);  
//	            sheet.appendRow(row);  
			}
		}
		List list2 = new ArrayList();  
        list2.add(sheet);  
        new Excel().write(list2,new FileOutputStream(f),null);//第三个参数设置列宽
        

//        byte[] content =  getBytes(fsv.getHomeDirectory()+"//"+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");
        System.out.println(fsv.getHomeDirectory()+File.separator+f.getName());
        byte[] content =  getBytes(fsv.getHomeDirectory()+File.separator+f.getName());
//        Filedownload.save(content,null, fsv.getHomeDirectory()+"\\aaa"+f.getName());
        Filedownload.save(content,null, f.getName());
        f.delete();//下载完成后删除临时文件
         
        
      //记录操作日志
        
      //记录操作日志
      User user = (User)session.getAttribute("user");
      LogManage logManage = new LogManage();
      logManage.addLog("导出EXCEL，共"+list.size()+"条， 条件为："+ map.toString(),user,"导出案件");
		 	
 
	    }  

public static byte[] getBytes(String filePath){  
    byte[] buffer = null;  
    try {  
        File file = new File(filePath);  
        FileInputStream fis = new FileInputStream(file);  
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
        byte[] b = new byte[1000];  
        int n;  
        while ((n = fis.read(b)) != -1) {  
            bos.write(b, 0, n);  
        }  
        fis.close();  
        bos.close();  
        buffer = bos.toByteArray();  
    } catch (FileNotFoundException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
    return buffer;  
} 
private void addRow(Sheet sheet,Lazb lazb,boolean isHeader){
	Row row = new Row();  
    List columnList = new ArrayList();  
    int colNum=1;
    
      Column column1 = new Column();
      if(isHeader){  
	      column1.setColumnLabel("案件号");  
	      column1.setColumnNum(colNum);
      }else{
    	  column1.setColumnLabel(lazb.getAjh());  
	      column1.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column1);
	  
	  Column column2 = new Column();
      if(isHeader){  
	      column2.setColumnLabel("案件类型");  
	      column2.setColumnNum(colNum);
      }else{
    	  column2.setColumnLabel(lazb.getAjlx());  
	      column2.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column2);
	  
	  Column column3 = new Column();
      if(isHeader){  
	      column3.setColumnLabel("商标名称");  
	      column3.setColumnNum(colNum);
      }else{
    	  column3.setColumnLabel(lazb.getSbmc());  
	      column3.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column3);
	  
	  Column column4 = new Column();
      if(isHeader){  
	      column4.setColumnLabel("类别");  
	      column4.setColumnNum(colNum);
      }else{
    	  column4.setColumnLabel(lazb.getLb());  
	      column4.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column4);
    
	  Column column5 = new Column();
      if(isHeader){  
	      column5.setColumnLabel("商标号");  
	      column5.setColumnNum(colNum);
      }else{
    	  column5.setColumnLabel(lazb.getSbh());  
	      column5.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column5);
	  
	  Column column5_1 = new Column();
      if(isHeader){  
	      column5_1.setColumnLabel("案件状态");  
	      column5_1.setColumnNum(colNum);
      }else{
    	  column5_1.setColumnLabel(lazb.getAjzt());  
	      column5_1.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column5_1);
	  
	  Column column6 = new Column();
      if(isHeader){  
	      column6.setColumnLabel("客户委托日期");  
	      column6.setColumnNum(colNum);
      }else{
    	  column6.setColumnLabel(lazb.getKhwtrq_str());  
	      column6.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column6);
	  
	  Column column7 = new Column();
      if(isHeader){  
	      column7.setColumnLabel("申请日");  
	      column7.setColumnNum(colNum);
      }else{
    	  column7.setColumnLabel(lazb.getSqrq_str());  
	      column7.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column7);
	  
	  Column column8 = new Column();
      if(isHeader){  
	      column8.setColumnLabel("收文日期");  
	      column8.setColumnNum(colNum);
      }else{
    	  column8.setColumnLabel(lazb.getSwrq_str());  
	      column8.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column8);
	  
	  
	  Column column9 = new Column();
      if(isHeader){  
	      column9.setColumnLabel("初审日期");  
	      column9.setColumnNum(colNum);
      }else{
    	  column9.setColumnLabel(lazb.getCsrq_str());  
	      column9.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column9);
	  
	  Column column10 = new Column();
      if(isHeader){  
	      column10.setColumnLabel("公告期");  
	      column10.setColumnNum(colNum);
      }else{
    	  column10.setColumnLabel(lazb.getGgq());  
	      column10.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column10);
	  
	  Column column11 = new Column();
      if(isHeader){  
	      column11.setColumnLabel("绝限日");  
	      column11.setColumnNum(colNum);
      }else{
    	  column11.setColumnLabel(lazb.getJxr_str());  
	      column11.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column11);
	  
	  Column column12 = new Column();
      if(isHeader){  
	      column12.setColumnLabel("首次提交日");  
	      column12.setColumnNum(colNum);
      }else{
    	  column12.setColumnLabel(lazb.getSctjr_str());  
	      column12.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column12);
	  
	  Column column13 = new Column();
      if(isHeader){  
	      column13.setColumnLabel("补充期限");  
	      column13.setColumnNum(colNum);
      }else{
    	  column13.setColumnLabel(lazb.getBcqx_str());  
	      column13.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column13);
	  
	  Column column14 = new Column();
      if(isHeader){  
	      column14.setColumnLabel("补充提交日期");  
	      column14.setColumnNum(colNum);
      }else{
    	  column14.setColumnLabel(lazb.getBctjrq_str());  
	      column14.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column14);
	  
	  Column column15 = new Column();
      if(isHeader){  
	      column15.setColumnLabel("首次报送编号");  
	      column15.setColumnNum(colNum);
      }else{
    	  column15.setColumnLabel(lazb.getScbsbh());  
	      column15.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column15);
	  
	  Column column16 = new Column();
      if(isHeader){  
	      column16.setColumnLabel("二次报送编号");  
	      column16.setColumnNum(colNum);
      }else{
    	  column16.setColumnLabel(lazb.getEcbsbh());  
	      column16.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column16);
	  
	  Column column17 = new Column();
      if(isHeader){  
	      column17.setColumnLabel("委托客户名称");  
	      column17.setColumnNum(colNum);
      }else{
    	  column17.setColumnLabel(lazb.getWtkhmc());  
	      column17.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column17);
	  
	  Column column = new Column();
      if(isHeader){  
	      column.setColumnLabel("被申请人");  
	      column.setColumnNum(colNum);
      }else{
    	  column.setColumnLabel(lazb.getByyr());  
	      column.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column);
	  
	  
	  
	  Column column19 = new Column();
      if(isHeader){  
	      column19.setColumnLabel("官方意见");  
	      column19.setColumnNum(colNum);
      }else{
    	  column19.setColumnLabel(lazb.getGfyj());  
	      column19.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column19);
	  
	  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	  
	  Column column19_1 = new Column();
      if(isHeader){  
	      column19_1.setColumnLabel("官方意见收文日");  
	      column19_1.setColumnNum(colNum);
      }else{
    	  column19_1.setColumnLabel(lazb.getGfyjswr() == null ?"":f.format(lazb.getGfyjswr()));  
	      column19_1.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column19_1);
	  
	  Column column19_2 = new Column();
      if(isHeader){  
	      column19_2.setColumnLabel("官方意见绝限日");  
	      column19_2.setColumnNum(colNum);
      }else{
    	  column19_2.setColumnLabel(lazb.getGfyjjxr() == null ?"":f.format(lazb.getGfyjjxr()));  
	      column19_2.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column19_2);
	  
	  Column column20 = new Column();
      if(isHeader){  
	      column20.setColumnLabel("代理人");  
	      column20.setColumnNum(colNum);
      }else{
    	  column20.setColumnLabel(lazb.getDlr());  
	      column20.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column20);
	  
	  Column column21 = new Column();
      if(isHeader){  
	      column21.setColumnLabel("代理人2");  
	      column21.setColumnNum(colNum);
      }else{
    	  column21.setColumnLabel(lazb.getDlr2());  
	      column21.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column21);
	  
	  Column column211 = new Column();
      if(isHeader){  
	      column211.setColumnLabel("申请人");  
	      column211.setColumnNum(colNum);
      }else{
    	  column211.setColumnLabel(lazb.getYysqr());  
	      column211.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column211);
	  
	  Column column212 = new Column();
      if(isHeader){  
	      column212.setColumnLabel("客户付款");  
	      column212.setColumnNum(colNum);
      }else{
    	  column212.setColumnLabel(lazb.getKhfk());  
	      column212.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column212);
	  
	  Column column213 = new Column();
      if(isHeader){  
	      column213.setColumnLabel("盈天付款");  
	      column213.setColumnNum(colNum);
      }else{
    	  column213.setColumnLabel(lazb.getYtfk());  
	      column213.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column213);
	  
	  Column column22 = new Column();
      if(isHeader){  
	      column22.setColumnLabel("案源人");  
	      column22.setColumnNum(colNum);
      }else{
    	  column22.setColumnLabel(lazb.getAyr());  
	      column.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column22);
	  
	  Column column23 = new Column();
      if(isHeader){  
	      column23.setColumnLabel("代理公司");  
	      column23.setColumnNum(colNum);
      }else{
    	  column23.setColumnLabel(lazb.getDlgs());  
	      column23.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column23);
	  
	  Column column24 = new Column();
      if(isHeader){  
	      column24.setColumnLabel("录入人");  
	      column24.setColumnNum(colNum);
      }else{
    	  column24.setColumnLabel(lazb.getCreater());  
	      column24.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column24);
	  
	  User user_login = (User)session.getAttribute("user");
		
		String roles  = user_login.getRoles();
		Boolean p1;//费用管理权限
		if(roles != null && roles.contains("费用管理")){//如果登录人有费用管理角色，则可以看到官费、代理费等费用
			p1=true;
		}else{
			p1 = false;
		}
	
	  Column column32 = new Column();
      if(isHeader){  
	      column32.setColumnLabel("备注");  
	      column32.setColumnNum(colNum);
      }else{
    	  column32.setColumnLabel(lazb.getRemarks());  
	      column32.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column32);
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
    row.setColumnList(columnList);  
    sheet.appendRow(row);  
}

public void onClick$qiehuan(){
	if("绝限日期".equals(jxlx.getValue())){
		jxlx.setValue("官方绝限日期");
	}else{
		jxlx.setValue("绝限日期");
	}
}

public void onClick$exportWord() throws IOException{
	XWPFDocument document= new XWPFDocument();
	
	//Write the Document in file system
    FileOutputStream out = new FileOutputStream(new File("d:\\create_table.docx"));

    //添加标题
    XWPFParagraph titleParagraph = document.createParagraph();
    //设置段落居中
    titleParagraph.setAlignment(ParagraphAlignment.CENTER);

    XWPFRun titleParagraphRun = titleParagraph.createRun();

    titleParagraphRun.setText("Java PoI");
    titleParagraphRun.setColor("000000");
    titleParagraphRun.setFontSize(20);

    //段落
    XWPFParagraph firstParagraph = document.createParagraph();
    XWPFRun run = firstParagraph.createRun();
    run.setText("Java POI 生成word文件。");
    run.setColor("696969");
    run.setFontSize(16);

    //设置段落背景颜色
    CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
//    cTShd.setVal(STShd.CLEAR);
//    cTShd.setFill("97FFFF");

    //换行
    XWPFParagraph paragraph1 = document.createParagraph();
    XWPFRun paragraphRun1 = paragraph1.createRun();
    paragraphRun1.setText("\r");

    //基本信息表格
    XWPFTable infoTable = document.createTable();
    //去表格边框
    infoTable.getCTTbl().getTblPr().unsetTblBorders();

    //列宽自动分割
    CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
    infoTableWidth.setType(STTblWidth.DXA);
    infoTableWidth.setW(BigInteger.valueOf(9072));

    //表格第一行
    XWPFTableRow infoTableRowOne = infoTable.getRow(0);
    infoTableRowOne.getCell(0).setText("职位");
    infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");

    //表格第二行
    XWPFTableRow infoTableRowTwo = infoTable.createRow();
    infoTableRowTwo.getCell(0).setText("姓名");
    infoTableRowTwo.getCell(1).setText(": seawater");

    //表格第三行
    XWPFTableRow infoTableRowThree = infoTable.createRow();
    infoTableRowThree.getCell(0).setText("生日");
    infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

    //表格第四行
    XWPFTableRow infoTableRowFour = infoTable.createRow();
    infoTableRowFour.getCell(0).setText("性别");
    infoTableRowFour.getCell(1).setText(": 男");

    //表格第五行
    XWPFTableRow infoTableRowFive = infoTable.createRow();
    infoTableRowFive.getCell(0).setText("现居地");
    infoTableRowFive.getCell(1).setText(": xx");
    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

    //添加页眉
    CTP ctpHeader = CTP.Factory.newInstance();
    CTR ctrHeader = ctpHeader.addNewR();
    CTText ctHeader = ctrHeader.addNewT();
    String headerText = "ctpHeader";
    
//    ctHeader.setStringValue(headerText);
    
    XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
    //设置为右对齐
    headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
    XWPFParagraph[] parsHeader = new XWPFParagraph[1];
    parsHeader[0] = headerParagraph;
    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

    //添加页脚
    CTP ctpFooter = CTP.Factory.newInstance();
    CTR ctrFooter = ctpFooter.addNewR();
    CTText ctFooter = ctrFooter.addNewT();
    String footerText = "ctpFooter";
//    ctFooter.setStringValue(footerText);
    XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
    headerParagraph.setAlignment(ParagraphAlignment.CENTER);
    XWPFParagraph[] parsFooter = new XWPFParagraph[1];
    parsFooter[0] = footerParagraph;
    policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

    document.write(out);
    out.close();
}
public void onClick$test(){
	
//	int aa = Qjbl.getMaxAjh();
//	if(aa == 0){
//		int no = lazbService.maxAjh(null);
//		Qjbl.setMaxAjh(++no);
//		aa = no;
//	}
}
}