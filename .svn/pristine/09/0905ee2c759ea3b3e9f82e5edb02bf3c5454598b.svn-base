package com.pjgl.cgfk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.controller.CghtController;
import com.pjgl.cgfk.bean.Cgfk;
import com.pjgl.cgfk.service.serviceimpl.CgfkService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.system.utils.FileUtil;
import com.system.utils.LogManage;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.service.WenjianService;

@Controller("addCgfkController")   
@Scope("prototype")
public class AddCgfkController extends GenericForwardComposer{

	Textbox htbh; //'合同编号',
	Datebox qdrq; //'合同签订日期',
	Doublebox zje; //'总金额',
	Combobox jbr;
	Doublebox fkje;
	Datebox fkrq;
	Textbox remarks;
	Textbox wjmc;
	Textbox userMobile;
	
	Cght cght;
	Window addCgfkWin;
	
	CgfkService cgfkService = (CgfkService)SpringUtil.getBean("cgfkService");
	EmployeeManageService employeeManageService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		cght = (Cght)Executions.getCurrent().getArg().get("cght");		
		htbh.setValue(cght.getHtbh());
		qdrq.setValue(cght.getQdrq());
		zje.setValue(cght.getZje());
		
		User user = (User)session.getAttribute("user");
		Map map = new HashMap<Object,Object>();
		map.put("begin", 0);
		map.put("end", 1000);
		map.put("companyid", user.getCompanyid());
		List<EmployeeManage> list = employeeManageService.selectEmployeeManage(map);
		ListModel userModel = new ListModelList(list);
		jbr.setModel(userModel);
	}
	
	public void onClick$addBtn() throws ParseException{
		
		try {
			Media media;
			 media = (Media)session.getAttribute("media");
			 //Messagebox.show(media.getName());
			 byte[] aa ;
			 Calendar cale = null;  
			 cale = Calendar.getInstance();  
			 int year = cale.get(Calendar.YEAR); 
			 SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
			 Date date = new Date();
			 String[] filenames = wjmc.getValue().split(";");
			 String paths = "";
			 String fileNames = "";
			 if (media != null) {
				 if(media.isBinary()){//如果是二进制文件，就用getByteData，否则用getStringData
					 aa = media.getByteData();
				 }else{
					 String re = media.getStringData();
					 aa = re.getBytes();
				 }
				 
				 for (int i = 0; i < filenames.length; i++) {
					 String[] nameAndType = filenames[i].split("\\.");
					 String filename = nameAndType[0]+"-"+format.format(date)+
					 "."+nameAndType[1];
					 fileNames += filename + ";";
					 String path = "/"+System.getProperty("catalina.home")+"/webapps/pjgl/WEB-INF/classes/";
					 path.replace("\\", "/");
					 System.out.println("上传路径:"+path);  
					 System.out.println(path);
					 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/cgfk/"+filenames[i]+"/";
					 FileUtil.putFile(aa, path, filename);//用绝对路径写入文件
					 //通过media.getByteData()获得文件流，将文件流写成文件上传FTP
					 path = "/upload/cgfk/"+filenames[i]+"/"+filename;//用相对路径下载文件
					 paths += path + ";";
					 
				}
			}
			 insertCgfkRecord(paths,fileNames); 
			 addCgfkWin.detach();//关闭新增窗口
			 CountCgfkController cgfkController = (CountCgfkController)session.getAttribute("countCgfkController");
			 cgfkController.onClick$queryCgfkButton();;
			 Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			 
//			 LogManage logManage = new LogManage();
//			 String remarks = "上传文件"+filename;
//			 User user = (User)session.getAttribute("user");
//			 logManage.addLog(remarks,user);
		} catch (WrongValueException e) {
			// TODO Auto-generated catch block
			if (fkje.getValue() == null) {
				Messagebox.show("收票金额为空！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}else {
				Messagebox.show("文件上传失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}
			e.printStackTrace();
		}
	}
	
	 /**
	  * 将文件信息写入数据库
	  */
	 private void insertCgfkRecord(String path,String filename){
		 Map<Object, Object> map = new HashMap<>();
		 map.put("cghtid", cght.getId());
		 if (jbr.getSelectedItem() != null) {
			 map.put("jbrid", jbr.getSelectedItem().getValue());
		}
		 if (fkje.getValue() != null) {
			 map.put("fkje", fkje.getValue());
		}
		 map.put("fkrq", fkrq.getValue());
		 map.put("remarks", remarks.getValue().trim());
		 map.put("wjmc", wjmc.getValue());
		 map.put("companyid", cght.getCompanyid());
		 map.put("path", path);
		 map.put("state", Cgfk.STATE_VALID);
		 int result = cgfkService.insertCgfk(map);
		 System.out.println();
	}
	
	 public void onSelect$jbr() throws ParseException{
		 String mobile = jbr.getSelectedItem().getContext();
		 userMobile.setValue(mobile);
	 }
	 
}
