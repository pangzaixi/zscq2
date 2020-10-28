package com.yewu.jxqd.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.system.utils.excel.Column;
import com.system.utils.excel.Excel;
import com.system.utils.excel.Row;
import com.system.utils.excel.Sheet;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.controller.WenjianContorller;
import com.yewu.zscq.service.WenjianService;

import menu2.BorderLayoutComposer;
import menu2.MenuNode;

@Controller("jxqdController")   
@Scope("prototype") 
public class JxqdController    extends GenericForwardComposer{
	Window jxqdwin;
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	//获取service对象
		JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
	Label jxlx;	
		
	Textbox ajh;//案件号
	Textbox sbmc;//商标名称
	Intbox sblb;//商标类别
	Intbox sbh;//商标号
	Combobox dlr;//代理人
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
	
	session.setAttribute("jxqdController", this.getController());
	
	System.out.println();
	String msg = (String)Executions.getCurrent().getArg().get("msg");
	System.out.println();
}

/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Jxqd> list = jxqdService.selectJXQD(map);
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	listbox.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	paging.setTotalSize(jxqdService.selectJXQD_count(map));
	
//	paging.setActivePage(0);
	

}
private void param(Map<Object,Object> map) throws ParseException{
	if(ajh.getValue() != null && !"".equals(ajh.getValue())){
		map.put("ajh", ajh.getValue());
	}
	if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
		map.put("sbmc", sbmc.getValue());
	}
	if(sblb.getValue() != null && !"".equals(sblb.getValue())){
		map.put("sblb", sblb.getValue());
	}
	if(sbh.getValue() != null && !"".equals(sbh.getValue())){
		map.put("sbh", sbh.getValue());
	}
	if(dlr.getValue() != null && !"".equals(dlr.getValue())){
		map.put("dlr", dlr.getValue());
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
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date()); 
		calendar.add(calendar.MONTH,1);//把日期往后增加一个月.整数往后推,负数往前移动
		Date date = calendar.getTime();
		map.put("jxrq_q2", f.format(date));
		
		
		map.put("jxrq_q1", f.format(new Date()));
		map.put("jxrq_q2", f.format(date));
		
	}
	if(ra2.isSelected()){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date()); 
		calendar.add(calendar.MONTH,2);//把日期往后增加二个月.整数往后推,负数往前移动
		Date date = calendar.getTime();
		map.put("jxrq_q2", f.format(date));
		
		
		map.put("jxrq_q1", f.format(new Date()));
		map.put("jxrq_q2", f.format(date));
		
	}
	if("绝限日期".equals(jxlx.getValue())){
		map.put("jxlx", '1');//内部绝限
	}else{
		map.put("jxlx", '2');//官方绝限
	}
	map.put("begin", paging.getPageSize()*paging.getActivePage());
	map.put("end", paging.getPageSize());
}
/**
 * 分页标签
 * @throws ParseException 
 */
public void onClick$paging() throws ParseException{
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
	 ajh.setValue("");
		 sbmc.setValue("");
		 sblb.setValue(null);
		 sbh.setValue(null);
		 dlr.setValue("");
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
		Jxqd jxqd = (Jxqd)listbox.getSelectedItem().getValue();
		Map<String, Object> arg1 = new HashMap<String, Object>();
		arg1.put("jxqd", jxqd);
		
		Window window = (Window)Executions.createComponents(
	            "/jsp/zscq/jxqd/editJxqd.zul", null, arg1);
	    
	    window.doModal();//模式弹窗
//	    window.doPopup();//非模式弹窗
	    
	    onClick$queryButton();//刷新父窗口表格
	}
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
 * 导出
 * @throws FileNotFoundException
 */
public void onClick$downButton() throws Exception{
	
	//获取service对象
		JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
		Map map = new HashMap<Object,Object>();
		param(map);
		List<Jxqd> list = jxqdService.selectJXQD(map);
		Sheet sheet = new Sheet();
		sheet.setName("绝限清单");   
		FileSystemView fsv = FileSystemView.getFileSystemView();
		Date   dat=new   Date();     
        //dat.setTime(dat.getTime());                 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyyMMddHHmmddSSS");       
		String   timeStr   =   sdf.format(dat);
		File f=new File(fsv.getHomeDirectory()+File.separator+"绝限清单"+timeStr+".xls"); 
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
        new Excel().write(list2,new FileOutputStream(f),null);
        

//        byte[] content =  getBytes(fsv.getHomeDirectory()+"//"+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");
        System.out.println(fsv.getHomeDirectory()+File.separator+f.getName());
        byte[] content =  getBytes(fsv.getHomeDirectory()+File.separator+f.getName());
//        Filedownload.save(content,null, fsv.getHomeDirectory()+"\\aaa"+f.getName());
        Filedownload.save(content,null, f.getName());
        f.delete();//下载完成后删除临时文件
         
        
//        Messagebox.show("导出excel成功", "提示信息", Messagebox.OK, Messagebox.INFORMATION);  
		
 
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
private void addRow(Sheet sheet,Jxqd jxqd,boolean isHeader){
	Row row = new Row();  
    List columnList = new ArrayList();  
    int colNum=1;
    
      Column column1 = new Column();
      if(isHeader){  
	      column1.setColumnLabel("案件号");  
	      column1.setColumnNum(colNum);
      }else{
    	  column1.setColumnLabel(jxqd.getAjh());  
	      column1.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column1);
	  
	  Column column2 = new Column();
      if(isHeader){  
	      column2.setColumnLabel("案件类型");  
	      column2.setColumnNum(colNum);
      }else{
    	  column2.setColumnLabel(jxqd.getAjlx());  
	      column2.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column2);
	  
	  Column column3 = new Column();
      if(isHeader){  
	      column3.setColumnLabel("商标名称");  
	      column3.setColumnNum(colNum);
      }else{
    	  column3.setColumnLabel(jxqd.getSbmc());  
	      column3.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column3);
	  
	  Column column4 = new Column();
      if(isHeader){  
	      column4.setColumnLabel("类别");  
	      column4.setColumnNum(colNum);
      }else{
    	  column4.setColumnLabel(jxqd.getLb());  
	      column4.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column4);
    
	  Column column5 = new Column();
      if(isHeader){  
	      column5.setColumnLabel("商标号");  
	      column5.setColumnNum(colNum);
      }else{
    	  column5.setColumnLabel(jxqd.getSbh());  
	      column5.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column5);
	  
	  Column column6 = new Column();
      if(isHeader){  
	      column6.setColumnLabel("客户委托日期");  
	      column6.setColumnNum(colNum);
      }else{
    	  column6.setColumnLabel(jxqd.getKhwtrq_str());  
	      column6.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column6);
	  
	  Column column7 = new Column();
      if(isHeader){  
	      column7.setColumnLabel("申请日");  
	      column7.setColumnNum(colNum);
      }else{
    	  column7.setColumnLabel(jxqd.getSqr_str());  
	      column7.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column7);
	  
	  Column column8 = new Column();
      if(isHeader){  
	      column8.setColumnLabel("收文日期");  
	      column8.setColumnNum(colNum);
      }else{
    	  column8.setColumnLabel(jxqd.getSwrq_str());  
	      column8.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column8);
	  
	  
	  Column column9 = new Column();
      if(isHeader){  
	      column9.setColumnLabel("初审日期");  
	      column9.setColumnNum(colNum);
      }else{
    	  column9.setColumnLabel(jxqd.getCsrq_str());  
	      column9.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column9);
	  
	  Column column10 = new Column();
      if(isHeader){  
	      column10.setColumnLabel("公告期");  
	      column10.setColumnNum(colNum);
      }else{
    	  column10.setColumnLabel(jxqd.getGgq());  
	      column10.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column10);
	  
	  Column column11 = new Column();
      if(isHeader){  
	      column11.setColumnLabel("绝限日");  
	      column11.setColumnNum(colNum);
      }else{
    	  column11.setColumnLabel(jxqd.getJxr_str());  
	      column11.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column11);
	  
	  Column column12 = new Column();
      if(isHeader){  
	      column12.setColumnLabel("首次提交日");  
	      column12.setColumnNum(colNum);
      }else{
    	  column12.setColumnLabel(jxqd.getSctjr_str());  
	      column12.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column12);
	  
	  Column column13 = new Column();
      if(isHeader){  
	      column13.setColumnLabel("补充期限");  
	      column13.setColumnNum(colNum);
      }else{
    	  column13.setColumnLabel(jxqd.getBcqx_str());  
	      column13.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column13);
	  
	  Column column14 = new Column();
      if(isHeader){  
	      column14.setColumnLabel("补充提交日期");  
	      column14.setColumnNum(colNum);
      }else{
    	  column14.setColumnLabel(jxqd.getBctjrq_str());  
	      column14.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column14);
	  
	  Column column15 = new Column();
      if(isHeader){  
	      column15.setColumnLabel("首次报送编号");  
	      column15.setColumnNum(colNum);
      }else{
    	  column15.setColumnLabel(jxqd.getScbsbh());  
	      column15.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column15);
	  
	  Column column16 = new Column();
      if(isHeader){  
	      column16.setColumnLabel("二次报送编号");  
	      column16.setColumnNum(colNum);
      }else{
    	  column16.setColumnLabel(jxqd.getEcbsbh());  
	      column16.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column16);
	  
	  Column column17 = new Column();
      if(isHeader){  
	      column17.setColumnLabel("委托客户名称");  
	      column17.setColumnNum(colNum);
      }else{
    	  column17.setColumnLabel(jxqd.getWtkhmc());  
	      column17.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column17);
	  
	  Column column = new Column();
      if(isHeader){  
	      column.setColumnLabel("被请求人/商标权人/对方相对人");  
	      column.setColumnNum(colNum);
      }else{
    	  column.setColumnLabel(jxqd.getBqqr());  
	      column.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column);
	  
	  Column column18 = new Column();
      if(isHeader){  
	      column18.setColumnLabel("申请人/请求人/我方当事人");  
	      column18.setColumnNum(colNum);
      }else{
    	  column18.setColumnLabel(jxqd.getWfdsr());  
	      column18.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column18);
	  
	  Column column19 = new Column();
      if(isHeader){  
	      column19.setColumnLabel("官方结果");  
	      column19.setColumnNum(colNum);
      }else{
    	  column19.setColumnLabel(jxqd.getGfjg());  
	      column19.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column19);
	  
	  Column column191 = new Column();
      if(isHeader){  
	      column191.setColumnLabel("官方结果收文日期");  
	      column191.setColumnNum(colNum);
      }else{
    	  column191.setColumnLabel(jxqd.getGfjgswrq_str());  
	      column191.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column191);
	  
	  Column column192 = new Column();
      if(isHeader){  
	      column192.setColumnLabel("官方结果绝限日期");  
	      column192.setColumnNum(colNum);
      }else{
    	  column192.setColumnLabel(jxqd.getGfjgjxrq_str());  
	      column192.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column192);
	  
	  Column column20 = new Column();
      if(isHeader){  
	      column20.setColumnLabel("代理人");  
	      column20.setColumnNum(colNum);
      }else{
    	  column20.setColumnLabel(jxqd.getDlr());  
	      column20.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column20);
	  
	  Column column21 = new Column();
      if(isHeader){  
	      column21.setColumnLabel("代理人2");  
	      column21.setColumnNum(colNum);
      }else{
    	  column21.setColumnLabel(jxqd.getDlr2());  
	      column21.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column21);
	  
	  Column column22 = new Column();
      if(isHeader){  
	      column22.setColumnLabel("案源人");  
	      column22.setColumnNum(colNum);
      }else{
    	  column22.setColumnLabel(jxqd.getAyr());  
	      column.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column22);
	  
	  Column column23 = new Column();
      if(isHeader){  
	      column23.setColumnLabel("代理公司");  
	      column23.setColumnNum(colNum);
      }else{
    	  column23.setColumnLabel(jxqd.getDlgs());  
	      column23.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column23);
	  
	  User user_login = (User)session.getAttribute("user");
		
		String roles  = user_login.getRoles();
		Boolean p1;//费用管理权限
		if(roles != null && roles.contains("费用管理")){//如果登录人有费用管理角色，则可以看到官费、代理费等费用
			p1=true;
		}else{
			p1 = false;
		}
	if(p1){	
	  Column column24 = new Column();
      if(isHeader){  
	      column24.setColumnLabel("官费");  
	      column24.setColumnNum(colNum);
      }else{
    	  column24.setColumnLabel(jxqd.getGf()==null?"":String.valueOf(jxqd.getGf()));  
	      column24.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column24);
	  
	  Column column25 = new Column();
      if(isHeader){  
	      column25.setColumnLabel("代理费");  
	      column25.setColumnNum(colNum);
      }else{
    	  column25.setColumnLabel(jxqd.getDlf()==null?"":String.valueOf(jxqd.getDlf()));  
	      column25.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column25);
	  
	  Column column26 = new Column();
      if(isHeader){  
	      column26.setColumnLabel("客户付款");  
	      column26.setColumnNum(colNum);
      }else{
    	  column26.setColumnLabel(jxqd.getKhfk()==null?"":String.valueOf(jxqd.getKhfk()));  
	      column26.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column26);
	  
	  Column column27 = new Column();
      if(isHeader){  
	      column27.setColumnLabel("已给客户开票");  
	      column27.setColumnNum(colNum);
      }else{
    	  column27.setColumnLabel(jxqd.getYgkhkp()==null?"":String.valueOf(jxqd.getYgkhkp()));  
	      column27.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column27);
	  
	  Column column28 = new Column();
      if(isHeader){  
	      column28.setColumnLabel("代交公司付款");  
	      column28.setColumnNum(colNum);
      }else{
    	  column28.setColumnLabel(jxqd.getDjgsfk()==null?"":String.valueOf(jxqd.getDjgsfk()));  
	      column28.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column28);
	  
	  Column column29 = new Column();
      if(isHeader){  
	      column29.setColumnLabel("已给代交公司开票");  
	      column29.setColumnNum(colNum);
      }else{
    	  column29.setColumnLabel(jxqd.getYgdjgskp()==null?"":String.valueOf(jxqd.getYgdjgskp()));  
	      column29.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column29);
	  
	  Column column30 = new Column();
      if(isHeader){  
	      column30.setColumnLabel("代理人已支付提成费");  
	      column30.setColumnNum(colNum);
      }else{
    	  column30.setColumnLabel(jxqd.getDlryzftcf()==null?"":String.valueOf(jxqd.getDlryzftcf()));  
	      column30.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column30);
	  
	  Column column31 = new Column();
      if(isHeader){  
	      column31.setColumnLabel("案源人已支付提成费");  
	      column31.setColumnNum(colNum);
      }else{
    	  column31.setColumnLabel(jxqd.getAyryzftcf()==null?"":String.valueOf(jxqd.getAyryzftcf()));  
	      column31.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column31);
	}
	  Column column32 = new Column();
      if(isHeader){  
	      column32.setColumnLabel("备注");  
	      column32.setColumnNum(colNum);
      }else{
    	  column32.setColumnLabel(jxqd.getRemarks());  
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

}
