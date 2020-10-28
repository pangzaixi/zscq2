package com.zscq2.zhangdan.bean;

import com.zscq2.jxqd.bean.Lazb;

public class ZdDetail extends Lazb  implements Comparable<ZdDetail> {
	int index;
	int zdid;
	int ajid;
	String remark;
	double gf;
	double dlf;
	double gf_yt;
	double dlfYt;
	String wtnr;
	
	@Override  
    public int compareTo(ZdDetail o) {  
//        int i = this.getAge() - o.getAge();//先按照年龄排序  
//        if(i == 0){  
//            return this.score - o.getScore();//如果年龄相等了再用分数进行排序  
//        }  
//        return i;
		if(Integer.parseInt(this.getAjh().substring(3))>Integer.parseInt(o.getAjh().substring(3))){
			return 1;
		}else{
			return -1;
		}
		
    }  

	public int getZdid() {
		return zdid;
	}
	public void setZdid(int zdid) {
		this.zdid = zdid;
	}
	public int getAjid() {
		return ajid;
	}
	public void setAjid(int ajid) {
		this.ajid = ajid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getWtnr() {
		return wtnr;
	}
	public void setWtnr(String wtnr) {
		this.wtnr = wtnr;
	}

	public double getGf() {
		return gf;
	}

	public void setGf(double gf) {
		this.gf = gf;
	}

	public double getDlf() {
		return dlf;
	}

	public void setDlf(double dlf) {
		this.dlf = dlf;
	}

	public double getGf_yt() {
		return gf_yt;
	}

	public void setGf_yt(double gf_yt) {
		this.gf_yt = gf_yt;
	}

	public double getDlfYt() {
		return dlfYt;
	}

	public void setDlfYt(double dlfYt) {
		this.dlfYt = dlfYt;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	

}
