package com.zscq2.zhangdan.word;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.zkoss.zul.Filedownload;

import com.zscq2.zhangdan.bean.WfCompany;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;

/**
 * 异议申请导出
 * @author thinker
 *
 */
public class WordForYysq {

	
	/**
	 * 
	 * @param zdBean账单主记录
	 * @param list，账单明细集合，子表
	 * @throws IOException
	 */
	public void yysq(ZdBean zdBean,List<ZdDetail> list,WfCompany wfCompanyBean,boolean zJexport) throws IOException{
		XWPFDocument document= new XWPFDocument();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		Date   dat=new   Date();     
        //dat.setTime(dat.getTime());                 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyyMMdd-HHmmddSSS");       
		String   timeStr   =   sdf.format(dat);
		File f=new File(fsv.getHomeDirectory()+File.separator+zdBean.getAjlx()+"账单"+timeStr+".docx"); 
		
		
		 
		//Write the Document in file system
	    //FileOutputStream out = new FileOutputStream(new File("d:\\create_table.docx"));
		FileOutputStream out = new FileOutputStream(f);
	    addTable(document,zdBean,list,wfCompanyBean,zJexport);//上部无边框表格
	    //添加标题
	    XWPFParagraph titleParagraph = document.createParagraph();
	    //设置段落居中
	    titleParagraph.setAlignment(ParagraphAlignment.CENTER);
	    XWPFRun titleParagraphRun = titleParagraph.createRun();
	    titleParagraphRun.setText("账        单");
	    titleParagraphRun.setColor("000000");
	    titleParagraphRun.setFontSize(16);
	    titleParagraphRun.setBold(true);//加粗
	    titleParagraphRun.setFontFamily("仿宋");

	    addTable2(document,zdBean,list);//中间无边框表格
	    
	    //换行
	    XWPFParagraph paragraph1 = document.createParagraph();
	    XWPFRun paragraphRun1 = paragraph1.createRun();
	    paragraphRun1.setText("\r");

	    //表格个性化设计
	    
	    if("异议申请".equals(zdBean.getAjlx())){
	    	TableForYysq a = new TableForYysq();
	    	a.yysq(document,zdBean,list,wfCompanyBean,zJexport);
	    }else if("撤三申请".equals(zdBean.getAjlx())){
	    	TableCssq a = new TableCssq();
	    	a.cssq(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("撤三复审申请".equals(zdBean.getAjlx())){
	    	TableCsfssq a = new TableCsfssq();
	    	a.csfssq(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("注册申请".equals(zdBean.getAjlx())){
	    	TableZcsq a = new TableZcsq();
	    	a.zcsq(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("驳回复审".equals(zdBean.getAjlx())){
	    	TableForSbbhfssq a = new TableForSbbhfssq();
	    	a.sbbhfs(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("商标转让".equals(zdBean.getAjlx())){
	    	TableForSbzr a = new TableForSbzr();
	    	a.sbzr(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("无效申请".equals(zdBean.getAjlx())){
	    	TableForWxsq a = new TableForWxsq();
	    	a.wxsq(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("无效答辩".equals(zdBean.getAjlx())){
	    	TableForWxdb a = new TableForWxdb();
	    	a.wxdb(document, zdBean, list,wfCompanyBean,zJexport);
	    }else if("异议答辩".equals(zdBean.getAjlx())){
	    	TableForYydb a = new TableForYydb();
	    	a.yydb(document, zdBean, list,wfCompanyBean,zJexport);
	    }
	    
	    
	  //换行
	    XWPFParagraph paragraph2 = document.createParagraph();
	    XWPFRun paragraphRun2 = paragraph2.createRun();
	    paragraphRun2.setText("\r");
	    
	    //表格下信息
	    XWPFParagraph paragraph3 = document.createParagraph();
	    paragraph3.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun3 = paragraph3.createRun();
	    paragraphRun3.setText("付款注意事項：");paragraphRun3.setBold(true);paragraphRun3.setUnderline(UnderlinePatterns.SINGLE);
	    paragraphRun3.setColor("000000");paragraphRun3.setFontFamily("仿宋");
	   
	    //
	    XWPFParagraph paragraph4 = document.createParagraph();
	    paragraph4.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun4 = paragraph4.createRun();
	    paragraphRun4.setText("1、我司财务收款账户如下：");paragraphRun4.setFontFamily("仿宋");
	    
	    XWPFParagraph paragraph5 = document.createParagraph();
	    paragraph5.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun5 = paragraph5.createRun();
	    if(zJexport){
	    	paragraphRun5.setText("  帐户名：北京盈天科地知识产权代理有限公司；");
	    }else{
	    	paragraphRun5.setText("  帐户名："+wfCompanyBean.getWfName()+";");
	    }
	    
	    paragraphRun5.setFontFamily("仿宋");
	    
	    XWPFParagraph paragraph6 = document.createParagraph();
	    paragraph6.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun6 = paragraph6.createRun();
	    if(zJexport){
	    	paragraphRun6.setText("  开户行：招商银行北京分行东四环支行；");
	    }else{
	    	paragraphRun6.setText("  开户行："+wfCompanyBean.getBankName()+"；");
	    }
	    
	    paragraphRun6.setFontFamily("仿宋");
	    
	    XWPFParagraph paragraph7 = document.createParagraph();
	    paragraph7.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun7 = paragraph7.createRun();
	    if(zJexport){
	    	paragraphRun7.setText("  帐  号：110906556910902；");
	    }else{
	    	paragraphRun7.setText("  帐  号："+wfCompanyBean.getAccount()+"；");
	    }
	    
	    paragraphRun7.setFontFamily("仿宋");
	    
	    XWPFParagraph paragraph8 = document.createParagraph();
	    paragraph8.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun paragraphRun8 = paragraph8.createRun();
	    paragraphRun8.setText("2、付款后烦请通知我方接口人员，以便于及时查收。");paragraphRun8.setFontFamily("仿宋");
	    

	    
	    
	    //页眉页脚部分
	    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
	    XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

	    //添加页眉
	    CTP ctpHeader1 = CTP.Factory.newInstance();
	    CTR ctrHeader1 = ctpHeader1.addNewR();
	    CTText ctHeader1 = ctrHeader1.addNewT();
	    String headerText1 = "北京市盈科律师事务所";
	    ctHeader1.setStringValue(headerText1);
	    
	    CTP ctpHeader2 = CTP.Factory.newInstance();
	    CTR ctrHeader2 = ctpHeader2.addNewR();
	    CTText ctHeader2 = ctrHeader2.addNewT();
	    String headerText2 = "北京盈天科地知识产权";
	    ctHeader2.setStringValue(headerText2);
	    
	    XWPFParagraph headerParagraph1 = new XWPFParagraph(ctpHeader1, document);
	    XWPFParagraph headerParagraph2 = new XWPFParagraph(ctpHeader2, document);
//	    headerParagraph2.getRuns().get(0).setUnderline(UnderlinePatterns.DASH_LONG);
	    //设置为右对齐
	    headerParagraph1.setAlignment(ParagraphAlignment.RIGHT);
	    headerParagraph2.setAlignment(ParagraphAlignment.RIGHT);
	    XWPFParagraph[] parsHeader = new XWPFParagraph[2];
	    parsHeader[0] = headerParagraph1;
	    parsHeader[1] = headerParagraph2;
	    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
	    
	    //添加页脚
	    CTP ctpFooter1 = CTP.Factory.newInstance();
	    CTR ctrFooter1 = ctpFooter1.addNewR();
	    CTText ctFooter1 = ctrFooter1.addNewT();
	    String footerText1 = "地址：北京市朝阳区东四环中路76号大成国际中心C座6层";
	    ctFooter1.setStringValue(footerText1);
	    XWPFParagraph footerParagraph1 = new XWPFParagraph(ctpFooter1, document);
	    footerParagraph1.setAlignment(ParagraphAlignment.LEFT);
	    
	    CTP ctpFooter2 = CTP.Factory.newInstance();
	    CTR ctrFooter2 = ctpFooter2.addNewR();
	    CTText ctFooter2 = ctrFooter2.addNewT();
	    String footerText2 = "网址：www.yingkelawyer.com；电话：010-59626911；传真：010-59626918";
	    ctFooter2.setStringValue(footerText2);
	    XWPFParagraph footerParagraph2 = new XWPFParagraph(ctpFooter2, document);
	    footerParagraph2.setAlignment(ParagraphAlignment.LEFT);
	    
	    
	    XWPFParagraph[] parsFooter = new XWPFParagraph[2];
	    parsFooter[0] = footerParagraph1;
	    parsFooter[1] = footerParagraph2;
	    policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

	    
//	    地址：北京市朝阳区东四环中路76号大成国际中心C座6层   
//	    网址：www.yingkelawyer.com；电话：010-59626911；传真：010-59626918

	    document.write(out);
	    out.close();
	    
	    byte[] content =  getBytes(fsv.getHomeDirectory()+File.separator+f.getName());
	    Filedownload.save(content,null, f.getName());
        f.delete();//下载完成后删除临时文件
	}

	/**
	 * 致： 北京字节跳动科技有限公司  					账单号：ZD19TNS2968XX
	 *											日期:2019年07月10日  
	 * @param document
	 */
	public void addTable(XWPFDocument document,ZdBean zdBean,List<ZdDetail> list,WfCompany wfCompanyBean,boolean zJexport){
		//基本信息表格
	    XWPFTable infoTable = document.createTable();
	    //去表格边框
	    infoTable.getCTTbl().getTblPr().unsetTblBorders();

	    //列宽自动分割
	    CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
	    infoTableWidth.setType(STTblWidth.DXA);
	    infoTableWidth.setW(BigInteger.valueOf(9072));

	    //表格第一行
	    XWPFTableRow infoTableRowOne = infoTable.getRow(0);
//	    infoTableRowOne.getCell(0).setText("致：北京字节跳动科技有限公司");
	    XWPFRun XWPFRunOne = infoTableRowOne.getCell(0).getParagraphs().get(0).createRun(); 
	    XWPFRunOne.setBold(true);
	    
	    if(zJexport){
	    	XWPFRunOne.setText("致：北京字节跳动科技有限公司");
	    }else{
	    	XWPFRunOne.setText("致：北京盈天科地知识产权代理有限公司");
	    }
	    
	    XWPFRunOne.setFontFamily("仿宋");
	    infoTableRowOne.getCell(0).setWidth("70%");
	//    
	    XWPFTableCell  cell2 =infoTableRowOne.addNewTableCell();
	    XWPFRun xWPFRunOne = cell2.getParagraphs().get(0).createRun();
	    xWPFRunOne.setFontFamily("仿宋");
	    String zdNum = zdBean.getZdnum();
	    if(zJexport){
	    	xWPFRunOne.setText("账单号："+zdBean.getZdnum());
	    }else{
	    	String ccc = zdNum.replaceFirst( "Z",wfCompanyBean.getFirstWord());
	    	xWPFRunOne.setText("账单号："+ccc);
	    		
	    }
	    
	    
	    
	    //表格第二行
	    XWPFTableRow infoTableRowTwo = infoTable.createRow();
	    infoTableRowTwo.getCell(0).setText("");
	    XWPFRun xWPFRunTwo = infoTableRowTwo.getCell(1).getParagraphs().get(0).createRun();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	    xWPFRunTwo.setFontFamily("仿宋");
	    xWPFRunTwo.setText("日期:"+format.format(zdBean.getZddate()));
	    
	}
	/**
	 * 致： 北京字节跳动科技有限公司  					账单号：ZD19TNS2968XX
	 *											日期:2019年07月10日  
	 * @param document
	 */
	public void addTable2(XWPFDocument document,ZdBean zdBean,List<ZdDetail> list){
		//基本信息表格
	    XWPFTable infoTable = document.createTable();
	    //去表格边框
	    infoTable.getCTTbl().getTblPr().unsetTblBorders();

	    //列宽自动分割
	    CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
	    infoTableWidth.setType(STTblWidth.DXA);
	    infoTableWidth.setW(BigInteger.valueOf(9072));

	    //表格第一行
	    XWPFTableRow infoTableRowOne = infoTable.getRow(0);
	 
	    XWPFRun runOne = infoTableRowOne.getCell(0).getParagraphs().get(0).createRun();
//	    infoTableRowOne.getCell(0).setText("案件:");
	    runOne.setBold(true);
	    runOne.setText("案件：");runOne.setFontFamily("仿宋");
	    infoTableRowOne.getCell(0).setWidth("10%");
	    
	    XWPFRun xWPFRunOne = infoTableRowOne.addNewTableCell().getParagraphs().get(0).createRun();
	    xWPFRunOne.setFontFamily("仿宋");
	    if("异议申请".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标异议申请");
	    }else if("撤三申请".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标撤三申请");
	    }else if("撤三复审申请".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标撤三复审申请");
	    }else if("注册申请".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标申请");
	    }else if("驳回复审".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标驳回复审申请");
	    }else if("商标转让".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标转让申请");
	    }else if("无效申请".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标无效宣告申请");
	    }else if("无效答辩".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标无效答辩");
	    }else if("异议答辩".equals(zdBean.getAjlx())){
	    	xWPFRunOne.setText("商标异议答辩");
	    }else{
	    	xWPFRunOne.setText("请确认案件类型");
	    }
	    
	    
	    
	    

	    //表格第二行
	    XWPFTableRow infoTableRowTwo = infoTable.createRow();
	    infoTableRowTwo.getCell(0).setText("");
	    infoTableRowTwo.getCell(0).getParagraphs().get(0).createRun().setFontFamily("仿宋");
	    
	    XWPFRun xWPFRunTwo = infoTableRowTwo.getCell(1).getParagraphs().get(0).createRun();
	    xWPFRunTwo.setText("委托人: 北京字节跳动科技有限公司 ");
	    xWPFRunTwo.setFontFamily("仿宋");
	  //表格第三行
	    XWPFTableRow infoTableRowThree = infoTable.createRow();
	    infoTableRowThree.getCell(0).setText("");
	    XWPFRun xWPFRunThree = infoTableRowThree.getCell(1).getParagraphs().get(0).createRun();
	    xWPFRunThree.setFontFamily("仿宋");
	    //xWPFRunThree.setText("委托日期：     年    月    日");
	    SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
		if(zdBean.getWtrqZd() == null){
			xWPFRunThree.setText("委托日期：     年    月    日");
		}else{
			xWPFRunThree.setText("委托日期："+f.format(zdBean.getWtrqZd()));
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
