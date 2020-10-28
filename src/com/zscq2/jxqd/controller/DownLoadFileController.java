package com.zscq2.jxqd.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;

import com.system.utils.LogManage;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Lazb;


@Controller("downLoadFileController")   
@Scope("prototype") 
public class DownLoadFileController extends GenericForwardComposer{
	
	Lazb lazb;
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	String deleteFilePath="";
	List<Lazb> list = new ArrayList<Lazb>();
	ListModel listModelList =new ListModelList<Jxqd>();
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	lazb = (Lazb)Executions.getCurrent().getArg().get("lazb");
	
	
	
	
	
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	 System.out.println("上传路径:"+path);  
	 
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/sb/"+lazb.getAjh()+"/";
	 List<File> fileList = new ArrayList<File>();
	 try {
			File file = new File(path);
			if(file.isDirectory()){
				File []files = file.listFiles();
				for(File fileIndex:files){
					//如果这个文件是目录，则进行递归搜索
					if(fileIndex.isDirectory()){
//						getFiles(fileList,fileIndex.getPath());
						continue;
					}else {
						//如果文件是普通文件，则将文件句柄放入集合中
						fileList.add(fileIndex);
					}
				}
			}
		}catch (Exception e){

		}
	if(fileList.size()>0){
		
		for(File file:fileList){
			Lazb lazb_t = new Lazb();
			lazb_t.setAjh(lazb.getAjh());
			lazb_t.setAjlx(lazb.getAjlx());
			lazb_t.setSbmc(lazb.getSbmc());
			lazb_t.setLb(lazb.getLb());
			lazb_t.setSbh(lazb.getSbh());
			lazb_t.setRemarks(file.getName());
			list.add(lazb_t);
		}
	}
	
	
	
	
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	listbox.setModel(listModelList);
	
	
}
/**
 * 删除文件
 */
public void onClick$deleteFileButton(){

	if(listbox.getSelectedItem() == null){
		Messagebox.show("请选择文件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	Lazb l = listbox.getSelectedItem().getValue();
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	   
	 
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/sb/"+l.getAjh()+"/"+l.getRemarks();
	 System.out.println("删除文件:"+path);
	 File file = new File(path);
	 deleteFilePath = path;
     if (!file.exists()) {
    	 Messagebox.show("删除文件失败:" +  "不存在！");
        return;
     } else {
    	 Messagebox.show("确认删除? ","请确认", 
    			 Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
 		    public void onEvent(org.zkoss.zk.ui.event.Event evt) throws java.lang.InterruptedException, ParseException {
 		        if (evt.getName().equals("onOK")) {
 		        	String re = deleteFile(deleteFilePath);
 		        	list.remove(listbox.getSelectedItem().getValue());	
	 		       	
	 		       	listModelList = new ListModelList(list);
	 		       	
	 		       	listbox.setModel(listModelList);

	 		       	Messagebox.show(re);
	 		       	
	 		     //记录操作日志
					User user = (User)session.getAttribute("user");
					LogManage logManage = new LogManage();
					logManage.addLog("删除文件："+deleteFilePath,user,"删除文件");
 		        } 
 		    }
 		});
              
         
     }
}
/**
 * 删除单个文件
 *
 * @param fileName
 *            要删除的文件的文件名
 * @return 单个文件删除成功返回true，否则返回false
 */
public  String deleteFile(String deleteFilePath) {
	System.out.println(deleteFilePath);
    File file = new File(deleteFilePath);
    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    String res="";
    if (file.exists() && file.isFile()) {
        if (file.delete()) {
            res = "删除单个文件" + file.getName() + "成功！";
        } else {
        	res = "删除单个文件" + file.getName() + "失败！";
        }
    } else {
    	res = "删除单个文件失败：" + file.getName() + "不存在！";
    }
    return res;
}

}
