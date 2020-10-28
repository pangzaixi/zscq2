package com.system.utils;

public class Screen
{
    public static void main(String[] args)
    {
    	//width 100 -2560   height 1000-2560
           int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
           int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
           System.out.println(screenWidth+"   "+screenHeight);
     }
    /**
     * 
     * @param type 1 父窗口，2子窗口
     * @return
     */
    public static int[] widthAndHeight(String type,int screenWidth,int screenHeight){
    	 
//    	0.21;
//	    0.31;
//    	int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
//        int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
    	
    	int widthPercent_parend = 0;
    	int heightPercent_parend = 0;
    	if(screenWidth>=2000){
    		widthPercent_parend=30;
    		heightPercent_parend=0;
    	}else if(screenWidth>=1800){
    		widthPercent_parend=40;
    		heightPercent_parend=0;
    	}else if(screenWidth>=1600){
    		widthPercent_parend=50;
    		heightPercent_parend=0;
    	}else if(screenWidth>=1400){
    		widthPercent_parend=60;
    		heightPercent_parend=0;	
    	}else if(screenWidth>=1200){
    		widthPercent_parend=70;
    		heightPercent_parend=0;
    	}else{
    		widthPercent_parend=80;
    		heightPercent_parend=140;
    	}
    	int[] t_parend = {widthPercent_parend,heightPercent_parend};//父窗口适应比例
    	
    	
    	int widthPercent_sub = 0;
    	int heightPercent_sub = 0;
    	if(screenWidth>=2000){
    		widthPercent_sub=180;
    		heightPercent_sub=350;
    	}else if(screenWidth>=1800){
    		widthPercent_sub=190;
    		heightPercent_sub=350;
    	}else if(screenWidth>=1600){
    		widthPercent_sub=200;
    		heightPercent_sub=350;
    	}else if(screenWidth>=1400){
    		widthPercent_sub=210;
    		heightPercent_sub=350;
    	}else if(screenWidth>=1200){
    		widthPercent_sub=220;
    		heightPercent_sub=350;
    	}else{
    		widthPercent_parend=250;
    		heightPercent_parend=350;
    	}
    	int[] t_sub = {widthPercent_sub,heightPercent_sub};//子窗口适应比例
    	if("1".equals(type)){
    		return t_parend;
    	}else{
    		return t_sub;
    	}
    	
    }
}
