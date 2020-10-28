package com.yewu.zscq.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.system.utils.FileUtil;
import com.system.utils.LogManage;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.service.WenjianService;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;


/**
 * 新增文件用的
 * @author thinker
 *
 */
@Controller("addWenJianController")  
@Scope("prototype") 
public class AddWenJianController   extends GenericForwardComposer{
	
	Button  addBtn;
	Vlayout flist;
	Textbox ajh;
	Textbox sbmc;
	Textbox sblx;
	Textbox sbh;
	Textbox wjmc;
//	Textbox dlr;
	Combobox dlr;
	Textbox remarks;
	Window addFile;
	
	ListModel dlrModel =new ListModelList<String>();
	
	public AddWenJianController(){
		System.out.println();
	}
	 public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);//该行必须存在，否则异常
			session.setAttribute("addWenJianController",this.getController());//用于从其他模块，例如绝限清单控制商标文件页面
			WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
			 List<String> list = wenjianService.selectDlr(null);
	    	dlrModel= new ListModelList(list);
	    	dlr.setModel(dlrModel);
	    	User user_login = (User)session.getAttribute("user");
	    	if(list.contains(user_login.getUser_name())){
	    		dlr.setValue(user_login.getUser_name());
	    	}
	    	
	    }
	 
/**
  * 保存新增商标
  */
 public void onClick$addBtn(){
	 if(ajh.getValue().trim().length() !=9 ){
		 Messagebox.show("案件号由3位字符，6位数字组成，首字母为T,当前案件号不合法请修改","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
	 }else {
//		 ^[a-zA-Z]{1} 表示第一个字符要求是字母。
		 System.out.println(ajh.getValue().matches("[a-zA-Z]{3}[0-9]{6}"));
		 Pattern pattern = Pattern.compile("[0-9]*");
		Boolean temp = pattern.matcher(ajh.getValue().substring(3, 5)).matches();
		 if(temp == false){
			 Messagebox.show("案件号第3，4位为年份信息，当前案件号不合法请修改","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 return;
		 }
		 System.out.println();
		 if(!"T".endsWith(ajh.getValue().substring(0, 1))){
			 Messagebox.show("档案号第一位必须为  T","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 return;
		 }
	 }
	 if(sblx.getValue().trim().length() != 2 ){
		 Messagebox.show("商标类别长度为2","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
	 }
	 if(sbh.getValue().trim().length() == 0 || sbh.getValue().length() >9){
		 Messagebox.show("商标号长度为1到9 ","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
	 }
	 if(wjmc.getValue().trim().length() ==0){
		 Messagebox.show("文件名称不能为空","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
	 }
//	 if(dlr.getValue().trim().length() ==0){
//		 Messagebox.show("请选择代理人","注意", Messagebox.OK, Messagebox.EXCLAMATION);
//		 return;
//	 }
	 System.out.println();
	 //Messagebox.show(String.valueOf(flist.getChildren().size())); //选择文件的数量
	 if(flist.getChildren().size()==0){
		 Messagebox.show("未选择文件","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		 return;
		 //Messagebox.show(media.getName());
	 }else{
		 try {
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
			 
			 String filename = ajh.getValue().trim()+"-"+sbmc.getValue().trim()+"-"+sblx.getValue().trim()+"-"+sbh.getValue().trim()+"-"+wjmc.getValue().trim()+"-"+format.format(date)+
			 "."+media.getFormat();
//			 String path = "/D:/setup/apache-tomcat-9.0.10/wtpwebapps/zscq/WEB-INF/classes/";
//			 String path2=System.getProperty("catalina.home");//D:\setup\apache-tomcat-9.0.10
			 String path = "/"+System.getProperty("catalina.home")+"/wtpwebapps/zscq/WEB-INF/classes/";
			 path.replace("\\", "/");
			 System.out.println("上传路径:"+path);  
			 System.out.println(path);
			 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/sb/"+ajh.getValue().substring(3, 5)+"/"+ajh.getValue()+"/";
			 FileUtil.putFile(aa, path, filename);//用绝对路径写入文件
			 //通过media.getByteData()获得文件流，将文件流写成文件上传FTP
			 path = "/upload/sb/"+ajh.getValue().substring(3, 5)+"/"+ajh.getValue()+"/"+filename;//用相对路径下载文件
			 insertFileRecord(path,filename); 
			 addFile.detach();//关闭新增窗口
			 Messagebox.show("文件上传成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 
			 LogManage logManage = new LogManage();
			 String remarks = "上传文件"+filename;
			 User user = (User)session.getAttribute("user");
			 logManage.addLog(remarks,user,"文件上传");
		} catch (WrongValueException e) {
			// TODO Auto-generated catch block
			Messagebox.show("文件上传失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			e.printStackTrace();
		}
	 }
 }
 /**
  * 将文件信息写入数据库
  */
 private void insertFileRecord(String path,String filename){
	//获取service对象
	 WenjianService wenjianService = (WenjianService)SpringUtil.getBean("wenjianService");
	 Map<Object,Object> map = new HashMap<Object,Object>();
//	 ajh, sbmc,sblx,sbh,wjmc,dlr,creater,path,filename,remarks
	 String file_info[] = filename.split("-");
	 map.put("ajh",file_info[0] );
	 map.put("sbmc",file_info[1] );
	 map.put("sblx",file_info[2] );
	 map.put("sbh",file_info[3] );
	 map.put("wjmc",file_info[4] );
	 map.put("dlr",dlr.getValue().trim() );
	 map.put("remarks",remarks.getValue().trim() );
	 User user = (User)session.getAttribute("user");
	 map.put("creater",user.getUser_name());//从session中获取
	 map.put("inputdate", new Date());
	 map.put("path",path );
	 map.put("filename",filename);
	 
	 
	 int result = wenjianService.addWenjian(map);
	 System.out.println(result);
	 
	 
 }

	
}