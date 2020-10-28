package com.zscq2.jxqd.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@Controller("uploadFileController")   
@Scope("prototype") 
public class UploadFileController extends GenericForwardComposer{
	Vlayout flist;
	Button uploadFile;
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	
	
	Listbox listbox;//页面遍历方式加载数据
	Paging paging;
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("uploadFileController",this.getController() );
	session.setAttribute("mediaList",null);//清空上传文件流
}
	
public void getLazb(String sbh){
	String[] t = sbh.split("-");
	System.out.println();
	Map map = new HashMap<Object,Object>();
	map.put("sbh", t[0]);
	map.put("begin", 0);
	map.put("end", 100);
	List<Lazb> list = lazbService.selectJXQD(map);
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	listbox.setModel(listModelList);
	
	//初始化分页标签
	Integer a = Integer.MAX_VALUE;
	Integer a1 = Integer.MIN_VALUE;
	paging.setTotalSize(lazbService.selectJXQD_count(map));
}

/**
 * 确认上传文件
 */
public void onClick$uploadFile(){
	int size = flist.getChildren().size();
//	Label a = (Label)flist.getFirstChild().getChildren().get(0);
//	System.out.println(a.getValue());
	Lazb lazb = listbox.getSelectedItem().getValue();
	
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
     filename_t = filename_t.substring(0,filename_t.length()-filetype.length()-1);
     
     
     String[] name_temp2 = filename_t.split("-");
     if(name_temp2.length != 4){
     	Messagebox.show("文件名不合法，请按照  商标号-类别-商标名称-官文命名 形式命名文件");
     	return;
     }
	 
	 
	 //我方案号-商标名称-类别-商标号-文件名称（自己）
	 String filename = lazb.getAjh()+"-"+lazb.getSbmc()+"-"+lazb.getLb()+"-"+lazb.getSbh()+"-"+name_temp2[3]+"-"+format.format(date)+ "."+media.getFormat();
//	 String path = "/D:/setup/apache-tomcat-9.0.10/wtpwebapps/zscq/WEB-INF/classes/";
//	 String path2=System.getProperty("catalina.home");//D:\setup\apache-tomcat-9.0.10
	 String path = "/"+System.getProperty("catalina.home")+"/webapps/zscq2/WEB-INF/classes/";
	 path.replace("\\", "/");
	 System.out.println("上传路径:"+path);  
	 
	 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/sb/"+lazb.getAjh()+"/";
	 FileUtil.putFile(aa, path, filename);//用绝对路径写入文件
	 //通过media.getByteData()获得文件流，将文件流写成文件上传FTP
//	 path = "/upload/sb/"+ajh.getValue().substring(3, 5)+"/"+ajh.getValue()+"/"+filename;//用相对路径下载文件
//	 insertFileRecord(path,filename); 
//	 addFile.detach();//关闭新增窗口
	 Messagebox.show("文件上传成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	 
	 LogManage logManage = new LogManage();
	 String remarks = "上传文件"+filename;
	 User user = (User)session.getAttribute("user");
	 logManage.addLog(remarks,user,"文件上传");
	
	//上传完成后，清空页面信息，避免重复上传，
	 ListModel listModelList =new ListModelList<Jxqd>();
	 listbox.setModel(listModelList);
	 uploadFile.setVisible(false);
	 
	 
	 
	
}
}
