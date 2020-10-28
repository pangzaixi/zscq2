package com.zscq2.jxqd.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.system.utils.LogManage;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Lazb;


@Controller("downLoadFileControllerTemp")   
@Scope("prototype") 
public class DownLoadFileControllerTemp extends GenericForwardComposer{
	
	Lazb lazb;
	Textbox fileName;
	Datebox d1;
	Datebox d2;
	Button queryButton;
	
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	String deleteFilePath="";
	List<Lazb> list = new ArrayList<Lazb>();
	ListModel listModelList =new ListModelList<Jxqd>();
	int fileNums = 0;
	String[] fileNames;
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
		
	
}
public void onClick$queryButton(){
	if(!(fileName.getValue() != null && !"".endsWith(fileName.getValue()))){
		Messagebox.show("请输入查询条件");
		return;
	}
	fileNames = fileName.getValue().split(" ");
	
	fileNums=0;
	list.clear();
	
	
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	 System.out.println("上传路径:"+path);  
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/ls/";
	 List<File> fileList = new ArrayList<File>();
	 
	 getFiles(path,fileList);
	
	 if(fileNums>200){
		 Messagebox.show("查询结果超过200条，显示前200条");
	 }
	 if(fileList.size()>0){
			
			for(File file:fileList){
				Lazb lazb_t = new Lazb();
//				lazb_t.setAjh(lazb.getAjh());
//				lazb_t.setAjlx(lazb.getAjlx());
//				lazb_t.setSbmc(lazb.getSbmc());
//				lazb_t.setLb(lazb.getLb());
//				lazb_t.setSbh(lazb.getSbh());
				lazb_t.setRemarks(file.getPath().substring(file.getPath().indexOf("zscq2\\upload\\ls\\")+15));
				list.add(lazb_t);
				
				
			}
		}
	
	
		listModelList = new ListModelList(list);
	

	listbox.setModel(listModelList);
	
}

private void getFiles(String path,List<File> fileList){
	
	if(fileNums>200){
		return;
	}
	 try {
		 	
			File file = new File(path);
			if(file.isDirectory()){
				File []files = file.listFiles();
				
				for(File fileIndex:files){
					if(fileNums>200){
						break;
					}
					//如果这个文件是目录，则进行递归搜索
					if(fileIndex.isDirectory()){
						getFiles(fileIndex.getPath(),fileList);
						continue;
					}else {
						boolean e = true;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						Date d_t1;
						Date d_t2;
						for(int t=0;t<fileNames.length;t++){
							if(fileNames[t] == null || "".endsWith(fileNames[t].trim())){
								continue;
							}
							System.out.println(file.getName());
							if(!fileIndex.getName().contains(fileNames[t])){
								e = false;
							}
						}
						if(d1.getValue() !=null){
							if(d1.getValue().after(new Date(Long.valueOf(fileIndex.lastModified())))){
								e = false;
							}
						}
						if(d2.getValue() !=null){
							Calendar calendar = new GregorianCalendar();
							Date date = d2.getValue();
							calendar.setTime(date); 
							calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
							date = calendar.getTime();
							
							if(date.before(new Date(Long.valueOf(fileIndex.lastModified())))){
								e = false;
							}
						}
				        String aaa = sdf.format(new Date(Long.valueOf(fileIndex.lastModified())));  
						System.out.println(aaa);
						if(e){//只有多个条件都满足时才显示文件，
							fileNums++;
							fileList.add(fileIndex);
						}
						
						
					//如果文件是普通文件，则将文件句柄放入集合中	
					}
				}
			}
		}catch (Exception e){

		}
	
}




}
