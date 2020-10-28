package com.yewu.zscq.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.utils.LogManage;
import com.system.utils.excel.Column;
import com.system.utils.excel.Excel;
import com.system.utils.excel.Row;
import com.system.utils.excel.Sheet;
import com.yewu.jxqd.controller.JxqdController;
import com.yewu.zscq.bean.Dlr;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.service.WenjianService;


@Controller("wenjianContorller")   
@Scope("prototype") 
public class WenjianContorller   extends GenericForwardComposer{

	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	
	
	public Textbox ajh;
	Textbox sbmc;
	Intbox sblx;
	Intbox sbh;
	Textbox wjmc;
//	Textbox dlr;
	Combobox dlr;
	Datebox lrrq_q1;
	Datebox lrrq_q2;
	
	//××××××××××××数据源
	
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	ListModel dlrModel =new ListModelList<String>();
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("wenjianContorller",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
		//获取service对象
		 WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
		 List<String> list = wenjianService.selectDlr(null);
//		List<String> list2 = new ArrayList<String>();
//    	list2.add("代理人一");
//    	list2.add("代理人二");
//    	list2.add("代理人三");
    	dlrModel= new ListModelList(list);
    	dlr.setModel(dlrModel);
    }	
	/**
	 * 查询按钮
	 */
 public void onClick$queryButton(){
    	//获取service对象
	 WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
    	
    	Map map = new HashMap<Object,Object>();
    	
    	if(ajh.getValue() != null && !"".equals(ajh.getValue())){
    		map.put("ajh", ajh.getValue());
    	}
		if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
			map.put("sbmc", sbmc.getValue());		
		    	}
		if(sblx.getValue() != null && !"".equals(sblx.getValue())){
			map.put("sblx", sblx.getValue());
		}
		if(sbh.getValue() != null && !"".equals(sbh.getValue())){
			map.put("sbh", sbh.getValue());
		}
		if(wjmc.getValue() != null && !"".equals(wjmc.getValue())){
			map.put("wjmc", wjmc.getValue());
		}
		if(dlr.getValue() != null && !"".equals(dlr.getValue())){
			map.put("dlr", dlr.getValue());
		}
		User user = (User)session.getAttribute("user");
		if("2".equals(user.getLevel())){
			map.put("user_name", user.getUser_name());
		}
		
		
		map.put("begin", paging.getPageSize()*paging.getActivePage());
		map.put("end", paging.getPageSize());
		
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	
    	if(lrrq_q1.getValue()!=null){
    		map.put("lrrq_q1", f.format(lrrq_q1.getValue()));
    	}
    	if(lrrq_q2.getValue()!=null){
    		Calendar calendar = new GregorianCalendar();
    		Date date = lrrq_q2.getValue();
    		calendar.setTime(date); 
    		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
    		date = calendar.getTime();
    		map.put("lrrq_q2", f.format(date));
    	}
    	
    	
    	
    	List<WenJian> list = wenjianService.selectWenjian(map);
    	ListModel listModelList =new ListModelList<WenJian>();
    	listModelList = null;
    	if(list!= null && list.size()>0){
    		listModelList = new ListModelList(list);
    	}

    	
    	
    	listbox.setModel(listModelList);
    	
    	//初始化分页标签
    	Integer a = Integer.MAX_VALUE;
    	Integer a1 = Integer.MIN_VALUE;
    	paging.setTotalSize(wenjianService.selectWenjian_count(map));
    	
//    	paging.setActivePage(0);
    	
         
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
	 ajh.setValue("");
		 sbmc.setValue("");
		 sblx.setValue(null);
		 sbh.setValue(null);
		 wjmc.setValue("");
		 dlr.setValue("");
		 lrrq_q1.setValue(null);;
		 lrrq_q2.setValue(null);
 }
 /**
  * 新增按钮,弹出编辑窗口
  */
 public void onClick$addButton() {
     //create a window programmatically and use it as a modal dialog.
     Window window = (Window)Executions.createComponents(
             "/jsp/zscq/wenjian/addsb.zul", null, null);
     window.doModal();
 }
 /**
  * 测试按钮
  */
 public void onClick$testButton(){
	 System.out.println(this.getClass().getClassLoader().getResource("").getPath());
	 String path = "/D:/setup/apache-tomcat-9.0.10/wtpwebapps/zscq/WEB-INF/classes/";
	 System.out.println(path);
	 path = path.substring(0, path.indexOf("WEB-INF"));
	 System.out.println(path);
	 Listitem a = listbox.getSelectedItem();
	 System.out.println();
 }
 public void onDoubleClick$listitem(){
	 try {
		Listitem a = listbox.getSelectedItem();
		 System.out.println();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 /**
  * 删除文件
  */
 public void onClick$delButton(){
	 System.out.println();
	 Listitem listitem = listbox.getSelectedItem();
	 
	 if(listitem == null){
		 Messagebox.show("请选择记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 }else{
		 Messagebox.show("确认删除当前记录?", "注意", Messagebox.OK | Messagebox.CANCEL  , Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
			    public void onEvent(Event evt) throws InterruptedException {
			        if (evt.getName().equals("onOK")) {
			        	String id = listitem.getId();
			    		//获取service对象
			    		WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
			    		int result = wenjianService.deleteWenjian(id);
			    		onClick$queryButton();//重新查询，刷新列表
			    		
			    		 WenJian wenjian = listitem.getValue();
			    		 LogManage logManage = new LogManage();
						 String remarks = "删除文件:"+wenjian.getFilename();
						 User user = (User)session.getAttribute("user");
						 logManage.addLog(remarks,user,"删除文件");
			        } else if (evt.getName().equals("onIgnore")) {
//			            Messagebox.show("Ignore Save", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			        } 
			    }
			});
		 
		
	 }
	 
 }
 
 public void onClick$downButton(){
	 try {
		Filedownload.save("D://upload//aaa.txt",".txt");  
		 System.out.println("12121");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
 
 public void onClick$export() throws Exception{
	 Excel excel=new Excel();  
	    if(listbox.getSelectedItem()==null){  
	        Messagebox.show("请选择要导出报表的内容！", "提示信息", Messagebox.OK, Messagebox.INFORMATION);  
	    }  
	    else{  
	     Date   dat=new   Date();     
	        //dat.setTime(dat.getTime());                 
	      SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");       
	      String   timeStr   =   sdf.format(dat);    
	      FileSystemView fsv = FileSystemView.getFileSystemView();   
	      fsv.getHomeDirectory();  
	      //C:\Users\thinker\Desktop\2019-03-3065.xls
	     File f=new File(fsv.getHomeDirectory()+File.separator+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");  
	        Sheet sheet = new Sheet();  
	        listbox.getSelectedItem().getValue();  
	        System.out.println(listbox.getSelectedItem().getLabel());  
	        sheet.setName(listbox.getSelectedItem().getLabel()+"的费用报表");     
	        Set set = listbox.getSelectedItems();  
	        for(Iterator iter= set.iterator();iter.hasNext();)  
	        {  
	            Row row = new Row();  
	            Listitem listitem = (Listitem) iter.next();  
	            List columnList = new ArrayList();  
	            for(int i = 0 ; i <listitem.getChildren().size();i++)  
	            {  
	                Column column = new Column();  
	                column.setColumnLabel(((Listcell)listitem.getChildren().get(i)).getLabel());  
	                column.setColumnNum(i);  
	                columnList.add(column);  
	            }  
	            row.setColumnList(columnList);  
	            sheet.appendRow(row);  
	        }  
	        List list = new ArrayList();  
	        list.add(sheet);  
	        new Excel().write(list,new FileOutputStream(f),null);
	        
//	        byte[] content =  getBytes(fsv.getHomeDirectory()+"//"+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");
//	        
//	        Filedownload.save(content,null, fsv.getHomeDirectory()+"//"+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");
	        
	        		
	        Messagebox.show("导出excel成功", "提示信息", Messagebox.OK, Messagebox.INFORMATION);  
	    }  
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

}	