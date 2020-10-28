package com.zscq2.zhangdan.word;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.zscq2.zhangdan.bean.WfCompany;
import com.zscq2.zhangdan.bean.ZdBean;
import com.zscq2.zhangdan.bean.ZdDetail;

/**
 * 异议申请表格
 * @author thinker
 *
 */
public class TableZcsq {

public void zcsq(XWPFDocument document,ZdBean zdBean,List<ZdDetail> list,WfCompany wfCompanyBean,boolean zJexport){
	//基本信息表格
    XWPFTable infoTable = document.createTable();
    //去表格边框
//    infoTable.getCTTbl().getTblPr().unsetTblBorders();

    //列宽自动分割
    CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
    infoTableWidth.setType(STTblWidth.DXA);
    infoTableWidth.setW(BigInteger.valueOf(9072));

    //表格第一行
    XWPFTableRow infoTableRowOne = infoTable.getRow(0);
 
    infoTableRowOne.getCell(0).setText("序号");
    infoTableRowOne.addNewTableCell().setText("我方案号");
    infoTableRowOne.addNewTableCell().setText("商标名称");
    infoTableRowOne.addNewTableCell().setText("类别");
    infoTableRowOne.addNewTableCell().setText("商标申请人");
    infoTableRowOne.addNewTableCell().setText("官费");
    infoTableRowOne.addNewTableCell().setText("代理费");
    infoTableRowOne.getCell(0).setColor("D3D3D3");
    infoTableRowOne.getCell(1).setColor("D3D3D3");
    infoTableRowOne.getCell(2).setColor("D3D3D3");
    infoTableRowOne.getCell(3).setColor("D3D3D3");
    infoTableRowOne.getCell(4).setColor("D3D3D3");
    infoTableRowOne.getCell(5).setColor("D3D3D3");
    infoTableRowOne.getCell(6).setColor("D3D3D3");
    //表格第二行
    DecimalFormat df = new DecimalFormat("0.##");
    for(int i=0;i<list.size();i++){
    	XWPFTableRow infoTableRowTwo = infoTable.createRow();
    	XWPFRun run0 = infoTableRowTwo.getCell(0).getParagraphs().get(0).createRun();run0.setFontFamily("仿宋");run0.setText(String.valueOf(i+1));
    	XWPFRun run1 = infoTableRowTwo.getCell(1).getParagraphs().get(0).createRun();run1.setFontFamily("仿宋");run1.setText(list.get(i).getAjh());
    	XWPFRun run2 = infoTableRowTwo.getCell(2).getParagraphs().get(0).createRun();run2.setFontFamily("仿宋");run2.setText(list.get(i).getSbmc());
    	XWPFRun run3 = infoTableRowTwo.getCell(3).getParagraphs().get(0).createRun();run3.setFontFamily("仿宋");run3.setText(list.get(i).getLb());
    	XWPFRun run4 = infoTableRowTwo.getCell(4).getParagraphs().get(0).createRun();run4.setFontFamily("仿宋");run4.setText(list.get(i).getWtkhmc());
    	XWPFRun run5 = infoTableRowTwo.getCell(5).getParagraphs().get(0).createRun();run5.setFontFamily("仿宋");
    	if(zJexport){run5.setText(String.valueOf(df.format(list.get(i).getGf())));}else{run5.setText(String.valueOf(df.format(list.get(i).getGf_yt())));}
    	XWPFRun run6 = infoTableRowTwo.getCell(6).getParagraphs().get(0).createRun();run6.setFontFamily("仿宋");
    	if(zJexport){run6.setText(String.valueOf(df.format(list.get(i).getDlf())));}else{run6.setText(String.valueOf(df.format(list.get(i).getDlfYt())));}
    	
	    
    }
    XWPFTableRow infoTableRowLast1 = infoTable.createRow();
    infoTableRowLast1.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
    //	    合并单元格
    infoTableRowLast1.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
    infoTableRowLast1.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
    infoTableRowLast1.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    infoTableRowLast1.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    infoTableRowLast1.getCell(4).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    
    XWPFRun run9 = infoTableRowLast1.getCell(0).getParagraphs().get(0).createRun();
    run9.setText("小计（人民币）:");
    run9.setFontFamily("仿宋");
    
    if(zJexport){
    	infoTableRowLast1.getCell(5).setText(String.valueOf(df.format(zdBean.getSumgf())));
    	infoTableRowLast1.getCell(6).setText(String.valueOf(df.format(zdBean.getSumdlf())));
    }else{
    	infoTableRowLast1.getCell(5).setText(String.valueOf(df.format(zdBean.getSumgfYt())));
    	infoTableRowLast1.getCell(6).setText(String.valueOf(df.format(zdBean.getSumdlfYt())));
    }
    
    XWPFTableRow infoTableRowLast2 = infoTable.createRow();
    infoTableRowLast2.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
    //	    合并单元格
    infoTableRowLast2.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
    infoTableRowLast2.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
    infoTableRowLast2.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    infoTableRowLast2.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    infoTableRowLast2.getCell(4).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    
    XWPFRun run10 = infoTableRowLast2.getCell(0).getParagraphs().get(0).createRun();
    run10.setText("合计（人民币）:");
    run10.setFontFamily("仿宋");
    
    if(zJexport){
    	infoTableRowLast2.getCell(5).setText(String.valueOf(df.format(zdBean.getSumgf()+zdBean.getSumdlf())));
    }else{
    	infoTableRowLast2.getCell(5).setText(String.valueOf(df.format(zdBean.getSumgfYt()+zdBean.getSumdlfYt())));
    }

    infoTableRowLast2.getCell(5).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
    infoTableRowLast2.getCell(6).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
    infoTableRowLast2.getCell(6).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);//设置剧中
}
}
