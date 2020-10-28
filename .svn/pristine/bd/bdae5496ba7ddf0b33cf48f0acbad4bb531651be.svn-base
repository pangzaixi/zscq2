package com.pjgl.cgsp.controller;

import java.io.File;
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
import org.zkoss.zk.fn.ZkFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pjgl.cght.bean.Cght;
import com.pjgl.cght.service.serviceimpl.CghtService;
import com.pjgl.cgsp.bean.Cgsp;
import com.pjgl.cgsp.service.serviceimpl.CgspService;
import com.pjgl.employeeManage.bean.EmployeeManage;
import com.pjgl.employeeManage.service.serviceImpl.EmployeeManageService;
import com.sun.org.apache.regexp.internal.recompile;
import com.system.utils.FileUtil;
import com.yewu.zscq.bean.User;

@Controller("editCgspController")   
@Scope("prototype") 
public class EditCgspController extends GenericForwardComposer{

	Textbox htbh; //'合同编号',
	Doublebox zje;
	Combobox jbr;
	Doublebox spje;
	Datebox sprq;
	Textbox remarks;
	Textbox wjmc;
	Textbox userMobile;
	Textbox path;
	String[] names;
	Textbox filesindex;
//	Textbox wj;

	Cgsp cgsp = (Cgsp)Executions.getCurrent().getArg().get("cgsp");
	Window editCgspWin;
	
	CgspService cgspService = (CgspService)SpringUtil.getBean("cgspService");
	CghtService cghtService = (CghtService)SpringUtil.getBean("cghtService");
	EmployeeManageService employeeManageService = (EmployeeManageService)SpringUtil.getBean("employeeManageService");
	
	public String[] getNames() {
		if (cgsp.getWjmc() != null && !cgsp.getWjmc().equals("")) {
			return cgsp.getWjmc().split(";");
		}else {
			return names;
		}
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
//		cgsp = (Cgsp)Executions.getCurrent().getArg().get("cgsp");
		if (cgsp.getJbrid() != null && !cgsp.getJbrid().equals("")) {
			EmployeeManage employeeManage = employeeManageService.getById(Integer.parseInt(cgsp.getJbrid()));
			if (employeeManage != null) {
				jbr.setValue(employeeManage.getUser_name());
				userMobile.setValue(employeeManage.getMobile());
			}
		}
		Cght cght = cghtService.getById(cgsp.getCghtid());
		if (cght != null) {
			htbh.setValue(cght.getHtbh());
			zje.setValue(cght.getZje());
		}
		spje.setValue(cgsp.getSpje());
		sprq.setValue(cgsp.getSprq());
		remarks.setValue(cgsp.getRemarks());
		if (cgsp.getWjmc() != null && !cgsp.getWjmc().equals("")) {
			names = cgsp.getWjmc().split(";");
		}
		cgsp.setNames(names);
//		String[] filepaths = cgsp.getPath().split(";");
//		session.setAttribute("names", names);
//		wjmc.setValue(cgsp.getWjmc());
//		path.setValue(cgsp.getPath());
		
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
			Map<Object, Object> map = new HashMap<>();
			String paths = "";
			if (wjmc.getValue() != null && !wjmc.getValue().trim().equals("")) {
				paths =  uploadFiles();
			}
			String index = filesindex.getValue();
			String[] findex = null;
			if (index != null && !index.equals("")) {
				findex = index.split(";");
			}
			String filenames = "";
			String[] fpaths = null;
			if (cgsp.getPath() != null && !cgsp.getPath().equals("")) {
				fpaths =cgsp.getPath().split(";");
			}
			String filepaths = "";
			if (index != null && !index.equals("")) {
				for (int i = 0; i < names.length; i++) {
					boolean flag = true;
					for (int j = 0; j < findex.length; j++) {
						if (i == Integer.parseInt(findex[j])) {
							flag = false;
						}
					}
					if (flag) {
						filenames += names[i] + ";";
						if (fpaths.length > i) {
							filepaths += fpaths[i] + ";";
						}
					}
				}
				deleteFiles();
				map.put("wjmc", filenames + wjmc.getValue());
				map.put("path", filepaths + paths);
			}else {
				map.put("wjmc", cgsp.getWjmc() + wjmc.getValue());
				map.put("path", cgsp.getPath() + paths);
			}
			
			if (jbr.getSelectedItem() != null) {
				map.put("jbrid", jbr.getSelectedItem().getValue());
			}
			 map.put("sprq", sprq.getValue());
			 if (spje.getValue() != null) {
				 map.put("spje", spje.getValue());
			}
			 map.put("remarks", remarks.getValue().trim());
			 map.put("id", cgsp.getId());
			 int result = cgspService.editCgsp(map);
			 
			 System.out.println();
			 editCgspWin.detach();//关闭新增窗口
			 CgspController cgspController = (CgspController)session.getAttribute("cgspController");
			 cgspController.onClick$queryButton();
//			 cgspController.onClick$queryCgspButton();
			 Messagebox.show("修改成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (Exception e) {
			if (spje.getValue() == null) {
				Messagebox.show("收票金额为空！","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}else {
				Messagebox.show("文件上传失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			}
			e.printStackTrace();
		}
	}

	private void deleteFiles() {
		// TODO Auto-generated method stub
		String[] findex = filesindex.getValue().split(";");
		String[] fpaths =cgsp.getPath().split(";");
		String path = "/"+System.getProperty("catalina.home")+"/webapps/pjgl/WEB-INF/classes/";
		for (int i = 0; i < findex.length; i++) {
			path = path.substring(0, path.indexOf("WEB-INF"))+fpaths[Integer.parseInt(findex[i])] ;
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}
//		path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/cgsp/"+filenames[i]+"/";
	}

	private String uploadFiles() {
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
					 path = path.substring(0, path.indexOf("WEB-INF"))+"/upload/cgsp/"+filenames[i]+"/";
					 FileUtil.putFile(aa, path, filename);//用绝对路径写入文件
					 //通过media.getByteData()获得文件流，将文件流写成文件上传FTP
					 path = "/upload/cgsp/"+filenames[i]+"/"+filename;//用相对路径下载文件
					 paths += path + ";";
					 
				}
			}
			 return paths;
	}
	
	public void onSelect$jbr() throws ParseException{
		 if (jbr.getSelectedItem() != null) {
			 String mobile = jbr.getSelectedItem().getContext();
			 userMobile.setValue(mobile);
		}
	 }
}
