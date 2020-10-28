package com.zscq2.ss.controller;


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
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Vlayout;

import com.system.utils.FileUtil;
import com.system.utils.LogManage;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.ss.bean.Ssaj;

/**
 * 诉讼案件文件上传
 * @author thinker
 *
 */
@Controller("uploadSsajController")   
@Scope("prototype") 
public class UploadSsajController extends GenericForwardComposer{
	Vlayout flist;
	Button uploadFile;
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	
	
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
	//后台全局变量
	List<Ssaj> list_ssaj = new ArrayList<Ssaj>();
	ListModel listModelList =new ListModelList<Ssaj>();
	Ssaj ssaj;
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("uploadFileController",this.getController() );
	session.setAttribute("mediaList",null);//清空上传文件流
	
	ssaj = (Ssaj)Executions.getCurrent().getArg().get("ssaj");	
	
	fileList();
	
}
/**
 * 获取文件列表
 */
private void fileList(){
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	 System.out.println("上传路径:"+path);  
	 
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/ss/"+ssaj.getAjh()+"/";
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
	 list_ssaj.clear();
	if(fileList.size()>0){
		
		for(File file:fileList){
			Ssaj ssaj_t = new Ssaj();
			ssaj_t.setAjh(ssaj.getAjh());
			ssaj_t.setAjlx(ssaj.getAjlx());
			ssaj_t.setYg(ssaj.getYg());
			ssaj_t.setBg(ssaj.getBg());
			ssaj_t.setDlr(ssaj.getDlr());
			ssaj_t.setRemarks(file.getName());
			list_ssaj.add(ssaj_t);
		}
	}
	
	
	
	listModelList = null;
	if(list_ssaj!= null && list_ssaj.size()>0){
		listModelList = new ListModelList(list_ssaj);
	}

	listbox.setModel(listModelList);
}

/**
 * 确认上传文件
 */
public void onClick$uploadFile(){
	int size = flist.getChildren().size();

	
	Media media;
	 media = (Media)session.getAttribute("media");
	 //Messagebox.show(media.getName());
	 byte[] aa ;
	 if(media.isBinary()){//如果是二进制文件，就用getByteData，否则用getStringData
		 aa = media.getByteData();
	 }else{
		 String re = media.getStringData();
		 aa = re.getBytes();
	 }
	 

	 Calendar cale = null;  
	 cale = Calendar.getInstance();  
	 int year = cale.get(Calendar.YEAR); 
	 SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
	 Date date = new Date();

	 String filename_t = media.getName();
     
     String filetype = media.getFormat();
     
	
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	 System.out.println("上传路径:"+path);  
	 
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/ss/"+ssaj.getAjh()+"/";
	 FileUtil.putFile(aa, path, filename_t);//用绝对路径写入文件

	 Messagebox.show("文件上传成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 
	
	//上传完成后，清空页面信息，避免重复上传，
//	 ListModel listModelList =new ListModelList<Jxqd>();
//	 listbox.setModel(listModelList);
	 uploadFile.setVisible(false);
	 
	 
	 fileList();//更新文件列表
	
}
}
