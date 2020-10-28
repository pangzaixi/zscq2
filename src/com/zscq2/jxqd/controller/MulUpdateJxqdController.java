
package com.zscq2.jxqd.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.zkoss.util.media.Media;
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

import com.system.utils.LogManage;
import com.system.utils.Screen;
import com.system.utils.excel.Column;
import com.system.utils.excel.Excel;
import com.system.utils.excel.Row;
import com.system.utils.excel.Sheet;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.log.service.serviceimpl.LogService;
import com.yewu.zscq.bean.User;
import com.yewu.zscq.bean.WenJian;
import com.yewu.zscq.controller.WenjianContorller;
import com.yewu.zscq.service.WenjianService;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.jxqd.util.ReadExcelUtil;

import menu2.BorderLayoutComposer;
import menu2.MenuNode;

@Controller("mulUpdateJxqdController")   
@Scope("prototype") 
public class MulUpdateJxqdController    extends GenericForwardComposer{
	//案件类型检查
//	String ajlx = "商标异议申请,商标驳回复审,补正,全部驳回,商标驳回复审行政诉讼,商标注册申请,驳回复审决定,部分驳回,商标撤三申请,商标撤三复审,商标撤三答辩,商标驳回复审补正,商标驳回,商标补正,商标转让,商标异议答辩,商标异议补正,商标部分驳回,商标驳回决定,商标无效宣告,异议决定,商标地址变更,商标续展,商标变更,商标撤三决定,撤销复审答辩,诉讼,商标撤三补正,商标撤销复审,撤销复审决定,商标分割申请,证据交换,商标异议予以注册,异议决定准予注册,商标异议答辩通知,商标异议撤回,商标无效宣告撤回,商标异议答辩补正,异议决定不准予注册,撤三决定,分割申请,商标异议答辨,商标变更补正,商标撤回补正,商标无效宣告答辩,不予注册复审,商标行政诉讼,驳回复审,补正证据,异议补正,撤三复审,无效宣告行政诉讼,是否提无效宣告";
	String ajlx_str="";
	Window mulUpdatewin;
	Boolean pagingOnClick = false;//是否点击的分页标签
	Combobox pagesize;//页面记录数下拉选
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
	
	//获取service对象
	LogService logService = (LogService)SpringUtil.getBean("logService");
		
	Label jxlx;	
	Label recordCount;	
	
	
	Intbox no1;//案件编号1
	Intbox no2;//案件编号2
	Intbox ggq;//公告期
	Combobox ajlx;//案件类型
	Datebox wtrq;//申请日期
	//××××××××××××数据源
	
		Listbox listbox;//页面遍历方式加载数据
		Paging paging;

		int xuhao=1;//序号
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	session.setAttribute("mulUpdateJxqdController", this.getController());
	int screenWidth = (int)session.getAttribute("screenWidth");
	int screenHeight = (int)session.getAttribute("screenHeight");
    int[] t  =Screen.widthAndHeight("2",screenWidth,screenHeight);
        
    mulUpdatewin.setWidth(screenWidth-t[0]+"px");
    mulUpdatewin.setHeight(screenHeight-t[1]+"px");
    
	Map map = new HashMap<Object,Object>();
	List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
	for(Ajlx ajlx:list_ajlx){
		ajlx_str = ajlx_str+","+ajlx.getAjlx();
	}
	
	//案件类型
	ListModel comboboxModelList =new ListModelList<Ajlx>();
	comboboxModelList = null;
	comboboxModelList = new ListModelList(list_ajlx);
	ajlx.setModel(comboboxModelList);
	
	
	
	
}

/**
 * 查询按钮
 * @throws ParseException 
 */
public void onClick$queryButton() throws ParseException{
	
	
	Map map = new HashMap<Object,Object>();
	param(map);
	List<Lazb> list = lazbService.selectJXQD(map);
	for(Lazb tmp:list){
		Date date = new Date();
		if(tmp.getJxr() != null){
			double days = 0;
			if(tmp.getBcqx() != null){
				days = new Double(tmp.getBcqx().getTime() - date.getTime()) / new Double(1000*3600*24);
			}else{
				days = new Double(tmp.getJxr().getTime() - date.getTime()) / new Double(1000*3600*24);
			}
			
			
			if(days<=0 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:red");
			}else if(days>0 && days<=3 && "未完结".equals(tmp.getStatus())){	
				tmp.setStyle("color:orange");
			}else if(days>3 && days<=6 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:violet");
			}else if(days>6 && days<=10 && "未完结".equals(tmp.getStatus())){
				tmp.setStyle("color:blue");
			}
		}
		
	}
	
	ListModel listModelList =new ListModelList<Jxqd>();
	listModelList = null;
	if(list!= null && list.size()>0){
		listModelList = new ListModelList(list);
	}

	
	
	
	listbox.setModel(listModelList);
	
	//初始化分页标签
		Integer a = Integer.MAX_VALUE;
		Integer a1 = Integer.MIN_VALUE;
		int count = lazbService.selectJXQD_count(map);
		recordCount.setValue("记录总数："+count);
		paging.setTotalSize(count);
	
//	paging.setActivePage(0);
	

}

/**
 * 当页面记录数下拉选值发生变化时修改表格单页记录数
 * @throws Exception 
 */
public void onChange$pagesize() throws Exception{
	paging.setPageSize(Integer.parseInt(pagesize.getValue()));
	try {
		onClick$queryButton();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void param(Map<Object,Object> map) throws ParseException{
	
	if(ggq.getValue() != null && !"".equals(ggq.getValue())){
		map.put("ggq", ggq.getValue());
	}
	
	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getValue());
	}

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	if(wtrq.getValue()!=null){
		map.put("wtrq", f.format(wtrq.getValue()));
	}
	if(no1.getValue() != null && !"".equals(no1.getValue())){
		map.put("no1", no1.getValue());
	}
	if(no2.getValue() != null && !"".equals(no2.getValue())){
		map.put("no2", no2.getValue());
	}
	
	if(pagingOnClick == true){
		pagingOnClick = false;
		map.put("begin", paging.getPageSize()*paging.getActivePage());
		map.put("end", paging.getPageSize());
	}else{
		paging.setActivePage(0);
		map.put("begin", 0);
		map.put("end", paging.getPageSize());
	}
	
}
/**
 * 分页标签
 * @throws ParseException 
 */
public void onClick$paging() throws ParseException{
	 
	int activepage = paging.getActivePage();
	pagingOnClick = true;
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
	 
		 ggq.setValue(null);
		 
		 ajlx.setValue("");
		 
		 wtrq.setValue(null);;
		no1.setValue(null);
		no2.setValue(null);
}
/**
 * 公告期批量更新
 */
public void onClick$mulUpdateButton() {
    //create a window programmatically and use it as a modal dialog.
	Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("msg", "Hello ZK!");

    Window window = (Window)Executions.createComponents(
            "/jsp/zscq/jxqd/mulUpdateSubWin.zul", mulUpdatewin, arg1);
    window.doModal();
    
}

/**
 * 根据excel内容更新案件状态
 */
public void onClick$mulUpdateState(){
	Map<String, Object> arg1 = new HashMap<String, Object>();
	arg1.put("msg", "Hello ZK!");

    Window window = (Window)Executions.createComponents(
            "/jsp/zscq/jxqd/mulUpdateExcelWin.zul", mulUpdatewin, arg1);
    window.doModal();
}

/**
 * 批量更新
 * @param map 更新值
 * @param map_param 条件参数
 * {bctjrq=Tue Feb 18 00:00:00 CST 2020, scbsbh=2, sctjr=Mon Feb 17 00:00:00 CST 2020, ecbsbh=3}
 * {ggq=1659, no2=33, no1=22, ajlx=异议申请, end=50, wtrq=2019-11-04, begin=0}
 */
public void mulUpdate(Map map,Map map_param){
	if(map.size()==0){
		Messagebox.show("未输入更新信息，本次未作任何更新");
		return;
	}
	System.out.println();
	map_param.putAll(map);
	if(map_param.get("ggq") == null){
		Messagebox.show("请输入公告期，本次未作任何更新");
		return;
	}
	int count = lazbService.mulUpdateLazb(map_param);
	Messagebox.show("本次更新记录："+count+" 条");
}

/**
 * 导出
 * @throws FileNotFoundException
 */
public void onClick$bsqdButton() throws Exception{
	if(ggq.getValue() == null || "".equals(ggq.getValue())){
		Messagebox.show("请输入公告期号");
		return ;
	}
	//获取service对象
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		Map map = new HashMap<Object,Object>();
		param(map);
		map.put("end", 100000);//如果是导出，则导出当前条件下的所有数据，而不是当前页
		
		map.put("bsqd", true);//报送清单的排序
		
		List<Lazb> list = lazbService.selectJXQD(map);
		Sheet sheet = new Sheet();
		sheet.setName("报送清单");   
		FileSystemView fsv = FileSystemView.getFileSystemView();
		Date   dat=new   Date();     
        //dat.setTime(dat.getTime());                 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyyMMddHHmmddSSS");       
		String   timeStr   =   sdf.format(dat);
		File f=new File(fsv.getHomeDirectory()+File.separator+"报送清单"+timeStr+".xls"); 
		if(list != null){
			xuhao= 1;
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
        new Excel().write(list2,new FileOutputStream(f),"0:5,1:30,2:5,3:30,4:15,5:5,6:5,7:5,8:5,9:5,10:5,11:5,12:5,13:5,14:5,15:5,16:5,17:5,18:5,19:5,20:5,21:5,22:15,");//第三个参数设置列宽
        

//        byte[] content =  getBytes(fsv.getHomeDirectory()+"//"+timeStr+ ""+listbox.getSelectedItem().getLabel()+".xls");
        System.out.println(fsv.getHomeDirectory()+File.separator+f.getName());
        byte[] content =  getBytes(fsv.getHomeDirectory()+File.separator+f.getName());
//        Filedownload.save(content,null, fsv.getHomeDirectory()+"\\aaa"+f.getName());
        Filedownload.save(content,null, f.getName());
        f.delete();//下载完成后删除临时文件
         
        
      //记录操作日志
        
      //记录操作日志
      User user = (User)session.getAttribute("user");
      LogManage logManage = new LogManage();
      logManage.addLog("导出EXCEL，共"+list.size()+"条， 条件为："+ map.toString(),user,"导出案件");
		 	
 
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
private void addRow(Sheet sheet,Lazb lazb,boolean isHeader){
	Row row = new Row();  
    List columnList = new ArrayList();  
    int colNum=1;
    	
    Column column0 = new Column();
    if(isHeader){  
	      column0.setColumnLabel("序号");  
	      column0.setColumnNum(colNum);
    }else{
  	  column0.setColumnLabel(String.valueOf(xuhao));
  	  xuhao++;
	      column0.setColumnNum(colNum);
    }    
    colNum++;
	columnList.add(column0);
	
	
	 Column column1 = new Column();
     if(isHeader){  
	      column1.setColumnLabel("申请人名称");  
	      column1.setColumnNum(colNum);
     }else{
   	  column1.setColumnLabel(lazb.getWtkhmc());  
	      column1.setColumnNum(colNum);
     }    
     colNum++;
	  columnList.add(column1);
	  
	  Column column2 = new Column();
     if(isHeader){  
	      column2.setColumnLabel("国籍");  
	      column2.setColumnNum(colNum);
     }else{
   	  column2.setColumnLabel("中国");  
	      column2.setColumnNum(colNum);
     }    
     colNum++;
	  columnList.add(column2);
		  
		  
	  
	  Column column3 = new Column();
      if(isHeader){  
	      column3.setColumnLabel("商标名称");  
	      column3.setColumnNum(colNum);
      }else{
    	  column3.setColumnLabel(lazb.getSbmc());  
	      column3.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column3); 
	  
	  Column column5 = new Column();
      if(isHeader){  
	      column5.setColumnLabel("商标申请号/注册号");  
	      column5.setColumnNum(colNum);
      }else{
    	  column5.setColumnLabel(lazb.getSbh());  
	      column5.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column5);
	  
	  
	  Column column4 = new Column();
      if(isHeader){  
	      column4.setColumnLabel("类别");  
	      column4.setColumnNum(colNum);
      }else{
    	  column4.setColumnLabel(lazb.getLb());  
	      column4.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column4);
	  
	  Column column6 = new Column();
      if(isHeader){  
	      column6.setColumnLabel("异议");  
	      column6.setColumnNum(colNum);
      }else{
    	  column6.setColumnLabel("√");  
	      column6.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column6);
	  
	  Column column7 = new Column();
      if(isHeader){  
	      column7.setColumnLabel("补正");  
	      column7.setColumnNum(colNum);
      }else{
    	  column7.setColumnLabel("");  
	      column7.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column7);
	  
	  Column column8 = new Column();
      if(isHeader){  
	      column8.setColumnLabel("补充");  
	      column8.setColumnNum(colNum);
      }else{
    	  column8.setColumnLabel("");  
	      column8.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column8);
	  
	  Column column9 = new Column();
      if(isHeader){  
	      column9.setColumnLabel("答辩");  
	      column9.setColumnNum(colNum);
      }else{
    	  column9.setColumnLabel("");  
	      column9.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column9);
	  
	  Column column10 = new Column();
      if(isHeader){  
	      column10.setColumnLabel("答辩补正");  
	      column10.setColumnNum(colNum);
      }else{
    	  column10.setColumnLabel("");  
	      column10.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column10);
	  
	  Column column11 = new Column();
      if(isHeader){  
	      column11.setColumnLabel("答辩补充");  
	      column11.setColumnNum(colNum);
      }else{
    	  column11.setColumnLabel("");  
	      column11.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column11);
	  
	  Column column12 = new Column();
      if(isHeader){  
	      column12.setColumnLabel("撤回");  
	      column12.setColumnNum(colNum);
      }else{
    	  column12.setColumnLabel("");  
	      column12.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column12);
	  
	  Column column13 = new Column();
      if(isHeader){  
	      column13.setColumnLabel("异议");  
	      column13.setColumnNum(colNum);
      }else{
    	  column13.setColumnLabel("");  
	      column13.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column13);
	  
	  Column column14 = new Column();
      if(isHeader){  
	      column14.setColumnLabel("补正");  
	      column14.setColumnNum(colNum);
      }else{
    	  column14.setColumnLabel("");  
	      column14.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column14);
	  
	  Column column15 = new Column();
      if(isHeader){  
	      column15.setColumnLabel("补充");  
	      column15.setColumnNum(colNum);
      }else{
    	  column15.setColumnLabel("");  
	      column15.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column15);
	  
	  Column column16 = new Column();
      if(isHeader){  
	      column16.setColumnLabel("答辩");  
	      column16.setColumnNum(colNum);
      }else{
    	  column16.setColumnLabel("");  
	      column16.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column16);
	  
	  Column column17 = new Column();
      if(isHeader){  
	      column17.setColumnLabel("答辩补正");  
	      column17.setColumnNum(colNum);
      }else{
    	  column17.setColumnLabel("");  
	      column17.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column17);
	  
	  Column column18 = new Column();
      if(isHeader){  
	      column18.setColumnLabel("答辩补充");  
	      column18.setColumnNum(colNum);
      }else{
    	  column18.setColumnLabel("");  
	      column18.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column18);
	  
	  Column column19 = new Column();
      if(isHeader){  
	      column19.setColumnLabel("备案");  
	      column19.setColumnNum(colNum);
      }else{
    	  column19.setColumnLabel("");  
	      column19.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column19);
	  
	  Column column20 = new Column();
      if(isHeader){  
	      column20.setColumnLabel("补正");  
	      column20.setColumnNum(colNum);
      }else{
    	  column20.setColumnLabel("");  
	      column20.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column20);
	  
	  Column column21 = new Column();
      if(isHeader){  
	      column21.setColumnLabel("变更");  
	      column21.setColumnNum(colNum);
      }else{
    	  column21.setColumnLabel("");  
	      column21.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column21);
	  
	 
	  
	  
      Column column22 = new Column();
      if(isHeader){  
	      column22.setColumnLabel("备注");  
	      column22.setColumnNum(colNum);
      }else{
    	  column22.setColumnLabel(lazb.getAjh());  
	      column22.setColumnNum(colNum);
      }    
      colNum++;
	  columnList.add(column22);
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
    row.setColumnList(columnList);  
    sheet.appendRow(row);  
}
}
