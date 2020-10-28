package com.zscq2.zhangdan.conftroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class PoiExport {
	public static void main(String args[]) throws Exception {
		myWord();
	}
	public static void myWord() throws IOException {
		
	    XWPFDocument doc = new XWPFDocument();
	    
	    setHeaderAndFooter(doc);//设置页眉页脚
	    
	    XWPFParagraph para = doc.createParagraph();
	  //设置段落居中
	    para.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun run = para.createRun();
	    setForm(run, 1, "致：北京字节跳动科技有限公司", false, "000000",true);
	    
	    XWPFParagraph para1 = doc.createParagraph();
	    para1.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun run1 = para.createRun();
	    setForm(run1, 1, "一、设计依据", false, "000000",false);
	    setForm(run1, 1, "1、国家标准", false, "000000",false);
	    setForm(run1, 1, "    1).《绿色建筑评价标准》 GB/T 50378-2014", false,
	            "000000",false);
	    setForm(run1, 1, "    2).《建筑照明设计标准》 GB50034-2013", false, "000000",false);
	    setForm(run1, 1, "2、行业标准", false, "000000",false);
	    setForm(run1, 1, "    1).《民用建筑电气设计规范》 JGJ 16-2008", false,
	            "000000",false);
	    setForm(run1, 1, "3、地方标准", false, "000000",false);
	    setForm(run1, 1, "    1).《吉林省绿色建筑评价标准》 DB22/JT137—2015", false,
	            "000000",false);
	    

	    // 创建一个8行1列的表格
	    XWPFTable table = doc.createTable(8, 1);
	    List<XWPFTableRow> rows = table.getRows();
	    // 表格的行row
	    XWPFTableRow row;
	    List<XWPFTableCell> cells;
	    // 单个单元格Cell
	    XWPFTableCell cell = null;
	    for (int i = 0; i < 8; i++) {
	        // 获取第i行
	        row = rows.get(i);
	        // 设置行宽高
	        if (i == 0) {
	            row.setHeight(800);
	        } else {
	            row.setHeight(600);
	        }
	        // 获取第i行单元格
	        cells = row.getTableCells();
	        cell = cells.get(0);
	        setProperties(cell, 1);
	        switch (i) {
	        case 0:
	            cells.get(0).setText("评价指标");
	            break;
	        case 1:
	            cells.get(0).setText("总分值");
	            break;
	        case 2:
	            cells.get(0).setText("实际总分值");
	            break;
	        case 3:
	            cells.get(0).setText("自评得分");
	            break;
	        case 4:
	            cells.get(0).setText("换算得分");
	            break;
	        case 5:
	            cells.get(0).setText("权重系数");
	            break;
	        case 6:
	            cells.get(0).setText("权重得分");
	            break;
	        }

	        // 在第i行创建j个单元格
	        for (int j = 1; j < 7; j++) {

	            if (i < 7) {
	                cell = row.addNewTableCell();
	                // 定位i行，j列的单元格。
	                cell = cells.get(j);
	                // 单元格属性
	                setProperties(cell, 1);
	                // 设置单元格文本
	                if (i == 0) {
	                    if (j == 1) {
	                        cells.get(j).setText("节地与室外环境");
	                    }
	                    if (j == 2) {
	                        cells.get(j).setText("节能与能源利用");
	                    }
	                    if (j == 3) {
	                        cells.get(j).setText("节水与水资源利用");
	                    }
	                    if (j == 4) {
	                        cells.get(j).setText("节材与材料资源利用");
	                    }
	                    if (j == 5) {
	                        cells.get(j).setText("室内环境质量");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("提高与创新");
	                    }

	                }
	                if (i == 1) {

	                    if (j == 1 || j <= 5) {
	                        cells.get(j).setText("100");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("  ");
	                    }
	                }
	                if (i == 2) {

	                    if (j == 1 || j <= 5 && j != 0) {
	                        cells.get(j).setText("XX.XX");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("－");
	                    }
	                }
	                if (i == 3) {

	                    if (j == 1 || j <= 5 && j != 0) {
	                        cells.get(j).setText("XX.XX");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("XX");
	                    }
	                }
	                if (i == 4) {

	                    if (j == 1 || j <= 5 && j != 0) {
	                        cells.get(j).setText("XX.XX");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("XX");
	                    }
	                }
	                if (i == 5) {

	                    if (j == 1) {
	                        cells.get(j).setText("0.21");
	                    }
	                    if (j == 2) {
	                        cells.get(j).setText("0.24");
	                    }
	                    if (j == 3) {
	                        cells.get(j).setText("0.20");
	                    }
	                    if (j == 4) {
	                        cells.get(j).setText("0.17");
	                    }
	                    if (j == 5) {
	                        cells.get(j).setText("0.18");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("1");
	                    }
	                }
	                if (i == 6) {

	                    if (j == 1 || j <= 5 && j != 0) {
	                        cells.get(j).setText("XX.XX");
	                    }
	                    if (j == 6) {
	                        cells.get(j).setText("XX");
	                    }
	                }
	            }
	        }
	        if (i == 7) {
	            for (int j2 = 0; j2 < 2; j2++) {
	                if (j2 == 0) {
	                    cell = cells.get(j2);

	                    setProperties(cell, 1);

	                    cell.setText("总得分ΣQ");
	                }
	                if (j2 == 1) {
	                    cell = row.addNewTableCell();
	                    // 单元格属性

	                    setProperties(cell, 6);
	                    cell.setText("XX.XX");
	                }
	            }
	        }
	    }
	    XWPFParagraph para1111 = doc.createParagraph();
	    XWPFRun run2111 = para1111.createRun();
	    setForm(run2111, 5, " ", false, "000000",false);
	    setForm(run2111, 1, "5   节能与能源利用技术措施", false, "000000",false);
	    setForm(run2111, 1, "控制项", false, "000000",false);
	    setForm(run2111, 1, "5.1.3冷热源、输配系统和照明等各部分能耗应进行独立分项计量。", false,
	            "000000",false);
	   
	    OutputStream os = new FileOutputStream("d:\\绿色建筑专篇说明--20180305-电气.doc");
	    doc.write(os);
	    os.close();
	}

	public static void setProperties(XWPFTableCell cell, int Width) {
	    CTTcPr cellPr1 = cell.getCTTc().addNewTcPr();
	    CTVerticalJc va1 = cellPr1.addNewVAlign();
	    va1.setVal(STVerticalJc.CENTER);
	    cellPr1.addNewTcW().setW(BigInteger.valueOf(1250 * Width));
	}

	/**
	 * 
	 * @param run
	 * @param changeLine 是否换行
	 * @param content
	 * @param isUnderline
	 * @param color
	 * @param bolb  设置是否加粗
	 */
	public static void setForm(XWPFRun run, int changeLine, String content,Boolean isUnderline, String color,Boolean bolb) {
	    // 是否添加下划线
	    if (isUnderline) {
	        run.setUnderline(UnderlinePatterns.WORDS);
	    }
	    run.setText(content);// 设置run的文本
	    // 是否换行
	    if (changeLine != 0) {
	        // 换几行
	        for (int i = 0; i < changeLine; i++) {
	            run.addBreak();
	        }
	    }
	    if(bolb){
	    	run.setBold(true);
	    }
	    run.setColor(color);
	    run.setTextPosition(5);
	    run.setFontSize(10);
	    run.setFontFamily("宋体");
	}
	
	public static void setHeaderAndFooter(XWPFDocument document){
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
	    XWPFParagraph headerParagraph2 =new XWPFParagraph(ctpHeader2, document);
	    //设置为右对齐
	    headerParagraph1.setAlignment(ParagraphAlignment.RIGHT);
	    headerParagraph2.setAlignment(ParagraphAlignment.RIGHT);

	    
	    XWPFParagraph[] parsHeader = new XWPFParagraph[2];
	    parsHeader[0] = headerParagraph1;
	    parsHeader[1] = headerParagraph2;
	    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
	    
	  //run.setUnderline(UnderlinePatterns.WORDS);
	    
	    //添加页脚
	    CTP ctpFooter1 = CTP.Factory.newInstance();
	    CTR ctrFooter1 = ctpFooter1.addNewR();
	    CTText ctFooter1 = ctrFooter1.addNewT();
	    String footerText1 = "地址：北京市朝阳区东四环中路76号大成国际中心C座6层";
	    ctFooter1.setStringValue(footerText1);
	    XWPFParagraph footerParagraph1 = new XWPFParagraph(ctpFooter1, document);
	    
	    
	    CTP ctpFooter2 = CTP.Factory.newInstance();
	    CTR ctrFooter2 = ctpFooter2.addNewR();
	    CTText ctFooter2 = ctrFooter2.addNewT();
	    String footerText2 = "网址：www.yingkelawyer.com；电话：010-59626911；传真：010-59626918";
	    ctFooter2.setStringValue(footerText2);
	    XWPFParagraph footerParagraph2 = new XWPFParagraph(ctpFooter2, document);
	    //
	    
	    footerParagraph1.setAlignment(ParagraphAlignment.LEFT);
	    footerParagraph2.setAlignment(ParagraphAlignment.LEFT);
	    XWPFParagraph[] parsFooter = new XWPFParagraph[2];
	    parsFooter[0] = footerParagraph1;
	    parsFooter[1] = footerParagraph2;
	    policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
	    
	}
}
