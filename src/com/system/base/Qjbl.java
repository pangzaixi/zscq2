package com.system.base;

/**
 * 全局变量
 * @author pang
 *
 */
public class Qjbl {
	//商标
	public static int maxAjh=0;
	//诉讼
	public static int maxAjhSs=0;

	public static int getMaxAjh() {
		if(maxAjh==0){
			return maxAjh;
		}else{
			return ++maxAjh;
		}
		
	}

	public static void setMaxAjh(int maxAjh) {
		Qjbl.maxAjh = maxAjh;
	}

	public static int getMaxAjhSs() {
		if(maxAjhSs==0){
			return maxAjhSs;
		}else{
			return ++maxAjhSs;
		}
	}

	public static void setMaxAjhSs(int maxAjhSs) {
		Qjbl.maxAjhSs = maxAjhSs;
	}
	

}
