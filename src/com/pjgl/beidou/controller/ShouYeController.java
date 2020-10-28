package com.pjgl.beidou.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;

import com.yewu.zscq.bean.User;

public class ShouYeController extends GenericForwardComposer{

	Datebox qdrq1;
	Datebox qdrq2;
	Iframe iframe1;
	Iframe iframe2;
	Iframe iframe3;
	Iframe iframe4;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		User user = (User)session.getAttribute("user");
		if(!"1".equals(user.getLevel())){
			iframe3.setWidth("1000px");
			iframe4.setWidth("1000px");
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 0);
        cal.set(Calendar.DAY_OF_YEAR, 1);
		qdrq1.setValue(cal.getTime());
		Calendar cale = Calendar.getInstance();
//		cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		qdrq2.setValue(cale.getTime());
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		iframe1.setSrc("/jsp/menu2/pie-stock.html?date1="+qdrq1.getValue()+";date2="+qdrq2.getValue());
		iframe2.setSrc("/jsp/menu2/pie-stock-amounts.html?date1="+qdrq1.getValue()+";date2="+qdrq2.getValue());
		iframe3.setSrc("/jsp/menu2/in-stock.html?date1="+qdrq1.getValue()+";date2="+qdrq2.getValue());
		iframe4.setSrc("/jsp/menu2/out-stock.html?date1="+qdrq1.getValue()+";date2="+qdrq2.getValue());
	}
}
