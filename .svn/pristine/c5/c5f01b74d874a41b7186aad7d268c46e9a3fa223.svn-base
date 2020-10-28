package com.zscq2.jxqd.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.system.utils.LogManage;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.bean.LazbAjzt;
import com.zscq2.jxqd.bean.LazbTmp;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.jxqd.util.ReadExcelUtil;

@Controller("mulUpdateExcelWinController")   
@Scope("prototype") 
public class MulUpdateExcelWinController extends GenericForwardComposer{
//页面元素
Listbox listbox;	
Window mulUpdateExcelWin;
//获取service对象
LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
private static Logger logger = Logger.getLogger(HttpClientUtils.class);
List<LazbTmp> list_lazb  = new ArrayList<LazbTmp>();
boolean ok = true;
		 
		 
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		session.setAttribute("mulUpdateExcelWinController", this.getController());
		
		
	}
	
	
	/**
	 * excel数据导入
	 * @throws Exception 
	 */
	public void onClick$excelImport() {
		list_lazb = null;
		listbox.setMold(null);
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
					ListModel listModelList =new ListModelList<Jxqd>();
					 listbox.setModel(listModelList);
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
			 list_lazb = changeToLazb(list);//将读取数据转换为立案记录集合
			 if(list_lazb == null){
				 return;
			 }
			 List<String> res = checkDate(list_lazb);//检查excel中案件号是否有空值
			 if( null == res){
//				 Messagebox.show("请注意，excel中，案件号："+res+" 在系统中不存在，请核实excel中案件号信息","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				 Messagebox.show("请注意，excel中不能包含案件号为空的记录","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				 ListModel listModelList =new ListModelList<Jxqd>();
				 listbox.setModel(listModelList);
				 return;
			 }
			 
			 //以excel案件号为条件查询库中案件记录
			 Map<Object,Object> map = new HashMap<Object,Object>();
			 map.put("ajhs", res);
			 List<Lazb> list_select =lazbService.selectJXQDByAjhs(map);
			 if(list_select == null || list_select.size()==0){
				 Messagebox.show("请注意，excel中编辑的案件号在系统中不存在，请核实","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				 return;
			 }
			 //定义变量，记录excel中记录是否在数据库中有匹配记录，如果有一条记录没有匹配，则弹窗提示，并整体不予更新
			 ok = true;
			 for(int i=0;i<list_lazb.size();i++){
				 LazbTmp a = list_lazb.get(i);
				 for(int j=0;j<list_select.size();j++){
					 Lazb b = list_select.get(j);
					 if(a.getAjh().equals(b.getAjh())){
						 a.setId(b.getId());
						 a.setAjh1(b.getAjh());
						 a.setAjlx1(b.getAjlx());
						 a.setAjzt1(b.getAjzt());
						 a.setSbh1(b.getSbh());
						 a.setSbmc1(b.getSbmc());
						 a.setSwrq1(b.getSwrq());
						 a.setGfyj1(b.getGfyj());
						 a.setGfyjswr1(b.getGfyjswr());
						 a.setYtfk1(b.getYtfk());
						 a.setKhfk1(b.getKhfk());
						 break;
					 }
				 }
				 if(a.getAjh1() == null || "".equals(a.getAjh1())){
					 a.setStyle("background:yellow");
					 ok = false;
					 break;
				 }
			 }
			
			 
			 
			 //页面加载数据
			 ListModel listModelList =new ListModelList<Jxqd>();
			if(list_lazb!= null && list_lazb.size()>0){
				listModelList = new ListModelList(list_lazb);
			}
			listbox.setModel(listModelList);
			if(ok == false){				
				 Messagebox.show("请注意，excel中编辑的案件号在系统中不存在，请核实红色记录信息\n 本次操作未作任何更新","注意", Messagebox.OK, Messagebox.EXCLAMATION);
				 return;
			 }
			String ajztCheck = checkAjzt(list_lazb);//查询所选案件状态是否合法
			 if(ajztCheck != null){
				 ok = false;
				 Messagebox.show(ajztCheck,"注意", Messagebox.OK, Messagebox.EXCLAMATION);
				 return ;
			 }
			
		 }
		 
		
		
	}	
	/**
	 * 更新案件状态方法
	 * @throws ParseException 
	 */
	public void onClick$updateBtn() throws ParseException{
		if(list_lazb == null || list_lazb.size() ==0 || ok == false){
//			ListModel listModelList =new ListModelList<Jxqd>();
//			 listbox.setModel(listModelList);
			Messagebox.show("请从excel导入数据，并检查合法性，当前导入内容为空或者不合法","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		Messagebox.show("确认保存？", 
				"请确认", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(org.zkoss.zk.ui.event.Event evt) throws java.lang.InterruptedException, ParseException {
		        if (evt.getName().equals("onOK")) {
		        	Map<Object,Object> map = new HashMap<Object,Object>();
		        	//
		            User user = (User)session.getAttribute("user");
		    		for(int i=0;i<list_lazb.size();i++){
		    			LazbTmp a = list_lazb.get(i);
		    			map.clear();
		    			 map.put("id", a.getId());
		    			 map.put("ajh", a.getAjh());
		    			 if(a.getAjzt() != null && !"".equals(a.getAjzt().trim())){
		    				 map.put("ajzt", a.getAjzt().trim()); 
		    			 }
		    			 if(a.getSwrq() != null && !"".equals(a.getSwrq())){
		    				 map.put("swrq", a.getSwrq()); 
		    			 }	 
		    			 if(a.getGfyj() != null && !"".equals(a.getGfyj().trim())){
		    				 map.put("gfyj", a.getGfyj().trim()); 
		    			 }
		    			 if(a.getGfyjswr() != null && !"".equals(a.getGfyjswr())){
		    				 map.put("gfyjswr", a.getGfyjswr()); 
		    			 }
		    			 if(a.getKhfk() != null && !"".equals(a.getKhfk().trim())){
		    				 map.put("khfk", a.getKhfk().trim()); 
		    			 }
		    			 if(a.getYtfk() != null && !"".equals(a.getYtfk().trim())){
		    				 map.put("ytfk", a.getYtfk().trim()); 
		    			 }
		    			 if(map.size()>2){//当更新信息为空的时候，不调用更新方法
		    				 lazbService.changeAjzt(map);
			    			 LogManage logManage = new LogManage();
							logManage.addLog("批量更新案件："+map.toString(),user,"批量更新案件");
		    			 }
		    			 
		    		}
		    		Messagebox.show("案件状态更新成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		    		MulUpdateJxqdController mulUpdateJxqdController = (MulUpdateJxqdController)session.getAttribute("mulUpdateJxqdController");
		    		mulUpdateJxqdController.onClick$queryButton();
		    		mulUpdateExcelWin.detach();
		        	
		        }
		    }
		});
		
		
	}
/**
 * 将excel读出内容转为lazb记录
 * @param list
 * @return 
 */
public List<LazbTmp> changeToLazb(List<List<String>> list){
	List<LazbTmp> list_res = new ArrayList<LazbTmp>();
	try {
	for(int i =1;i<list.size();i++){
		 List<String> t = list.get(i);
		 System.out.println(t.toString());
		 LazbTmp l = new LazbTmp();
//		 if(t.get(0) == null || "".equals(t.get(0).trim())){
//			Messagebox.show("案件号不能为空，未作更新","注意", Messagebox.OK, Messagebox.EXCLAMATION);
//			return null;
//		 }
		 l.setAjlx(t.get(0));
		 l.setAjh(t.get(7));//以更新人属性记录excel中的案件号 
		 l.setAjzt(t.get(1));//以备注属性记录excel中的案件状态
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 if(t.get(2) != null && !"".equals(t.get(2).trim())){
			 l.setSwrq(format.parse(t.get(2)));
		 }
		 l.setGfyj(t.get(3));
		 if(t.get(4) != null && !"".equals(t.get(4).trim())){
			 l.setGfyjswr(format.parse(t.get(4)));
		 }
		 l.setKhfk(t.get(5));
		 l.setYtfk(t.get(6));
		 list_res.add(l);
	 }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 ListModel listModelList =new ListModelList<Jxqd>();
		 listbox.setModel(listModelList);
		 Messagebox.show("数据读取异常，原因包括：案件号不能为空，或者日期格式错误，格式必须是YYYY-MM-DD格式,","注意", Messagebox.OK, Messagebox.EXCLAMATION);
			return null;
	}
	return list_res;
}
/**
 * 检查excel数据中，案件号是否有空值，
 *  @return error：数据不争取，否则返回所有案件号集合，以逗号分隔，作为参数供后续查询
 */
public List<String>  checkDate(List<LazbTmp> list){
	List<String> res = new ArrayList<String>();
	for(LazbTmp lazb:list){
		if(lazb.getAjh() == null || "".equals(lazb.getAjh().trim())){
			return null;
		}else{
			lazb.setAjh(lazb.getAjh().trim());
		}
		res.add(lazb.getAjh().trim());
		
	}
	return res;
}
	
/**
 * 判断所选案件状态是否正确，一个案件的案件类型永久不变，所以案件状态可选值就是一个固定集合，如果操作者所选状态不对，需要提示
 * @param list
 * @return null 状态正常，返回字符串：字符串内容为异常提示信息
 */
public String checkAjzt(List<LazbTmp> list){
	String normalAjzt="待提交,受理,待补正,已提交补正,不予受理,";
	Map<Object,Object> map = new HashMap<Object,Object>();
	List<Ajzt> list_ajzt= new ArrayList<Ajzt>();
	
	for(LazbTmp lazb:list){
		if(lazb.getAjzt() != null && !"".equals(lazb.getAjzt())){
			map.clear();
			map.put("ajlx",lazb.getAjlx1());
			list_ajzt = lazbService.selectAjzt(map);
			if(normalAjzt.indexOf(lazb.getAjzt())>=0){
				continue;//结束本次循环，继续下次循环
			}
			if(list_ajzt != null){
				boolean exit = false;
				for(Ajzt ajzt:list_ajzt){
					if(lazb.getAjzt().equals(ajzt.getAjzt())){
						exit = true;
						break;
					}
				}
				if(exit == false){
					lazb.setStyle("background:#00FFFF");
					return  "案件："+lazb.getAjh()+" ，所选案件状态不合法，请检查是否选错案件类型或案件状态";
				}
				
			}else{
				lazb.setStyle("background:#00FFFF");
				return "案件："+lazb.getAjh()+" ，所选案件状态不合法，请检查是否选错案件类型或案件状态";
			}	
		}
		
	}
	return null;
}
/**
 * 下载模板
 * @throws FileNotFoundException 
 */
public void onClick$download() throws FileNotFoundException{
	String path  = "upload/案件状态更新.xlsx";
	 path.replace("/", "\\");
	
	Filedownload.save(path, null);
}
}
