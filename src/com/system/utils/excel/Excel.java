package com.system.utils.excel;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class Excel{  
    
	/**
	 * 
	 * @param sheetList
	 * @param os
	 * @param colWidth  "0:15,2:20,4:15,5:20,6:20,7:20,8:20,10:20,11:20,13:20,16:20,17:20,18:20,20:20,21:20,22:20,23:20,32:40"
	 * @throws Exception
	 */
    public static void write(List<Sheet>sheetList,OutputStream os,String colWidth) throws Exception{  
        jxl.write.WritableWorkbook wwb;  
//        jxl.write.WritableFont wf1=new jxl.write.WritableFont(WritableFont.ARIAL,20,WritableFont.BOLD,false,UnderlineStyle.DOUBLE_ACCOUNTING,jxl.format.Colour.BRIGHT_GREEN);
        jxl.write.WritableFont wf1=new jxl.write.WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD ,false,UnderlineStyle.NO_UNDERLINE);
        
        

        jxl.write.WritableCellFormat wff1=new jxl.write.WritableCellFormat(wf1);
        wff1.setWrap(true);//自动换行
        wwb = Workbook.createWorkbook(os);  
        try {  
//          wff1.setBackground(jxl.format.Colour.VIOLET2);  
//          wff1.setBackground(jxl.format.Colour.VIOLET2);  
            
            int i = 0;  
            for(Iterator<Sheet>itr = sheetList.iterator();itr.hasNext();){  
                Sheet sheet = itr.next();  
               int rowNum =0;  
                WritableSheet ws = wwb.createSheet(sheet.getName(),i);  
                if(colWidth != null){
                	setColWidth(ws,colWidth);//设置列宽
                }
                
                for(Iterator<Row>sheetItr = sheet.getRowList().iterator();sheetItr.hasNext();){  
                    Row row = sheetItr.next();  
                    int colunNum = 0;  
                    for(Iterator<Column>rowIterator=row.getColumnList().iterator();rowIterator.hasNext();){  
                        Column column = rowIterator.next();  
                        ws.addCell(new jxl.write.Label(colunNum,rowNum,column.getColumnLabel(),wff1));  
                        colunNum++;  
                    }  
                    rowNum++;  
                }  
                i++;  
            }  
            //写入Exel工作表  
            wwb.write();  
           
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {
        	 //关闭Excel工作薄对象  
            wwb.close();  
            os.close();
		}  
    } 
    /**
     * 设置列宽
     * @param ws
     * @param param  格式1:10,2:30,5:30
     */
    private static void setColWidth(WritableSheet ws,String param){
    	String[] colnum = param.split(",");
    	for(int i=0;i<colnum.length;i++){
    		String[] t = colnum[i].split(":");
    		ws.setColumnView(Integer.valueOf(t[0]).intValue(), Integer.valueOf(t[1]).intValue());
    	}	
    }
}  