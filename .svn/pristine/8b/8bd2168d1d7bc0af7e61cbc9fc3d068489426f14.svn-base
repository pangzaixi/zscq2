package com.zscq2.ss.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.system.base.Qjbl;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Ajzt;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;
import com.zscq2.jxqd.util.LazbJxr;
import com.zscq2.ss.service.SsajService;


/**
 * 新增诉讼
 * @author thinker
 *
 */
@Controller("addSsController")   
@Scope("prototype") 
public class AddSsController extends GenericForwardComposer{
	//案件类型检查
//		String ajlx_s = "商标异议申请,商标驳回复审,补正,全部驳回,商标驳回复审行政诉讼,商标注册申请,驳回复审决定,部分驳回,商标撤三申请,商标撤三复审,商标撤三答辩,商标驳回复审补正,商标驳回,商标补正,商标转让,商标异议答辩,商标异议补正,商标部分驳回,商标驳回决定,商标无效宣告,异议决定,商标地址变更,商标续展,商标变更,商标撤三决定,撤销复审答辩,诉讼,商标撤三补正,商标撤销复审,撤销复审决定,商标分割申请,证据交换,商标异议予以注册,异议决定准予注册,商标异议答辩通知,商标异议撤回,商标无效宣告撤回,商标异议答辩补正,异议决定不准予注册,撤三决定,分割申请,商标异议答辨,商标变更补正,商标撤回补正,商标无效宣告答辩,不予注册复审,商标行政诉讼,驳回复审,补正证据,异议补正,撤三复审,无效宣告行政诉讼,是否提无效宣告";
	String ajlx_str="";
	
	//获取service对象
	SsajService ssajService = (SsajService)SpringUtil.getBean("ssajService");
	
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
		Window addSs;//新增窗口ID
	  Textbox ajh;// '案件号',
	  Combobox ajlx;//  '案件类型',
	  Textbox hth;//合同号
	  Combobox ay;//案由
	  Combobox ajzt;//案件状态
	  Combobox sj;//审级
	  Textbox yg;//  '原告
	  Textbox bg;// 被告
	  Textbox dsr;//第三人
	  Textbox gxfy;//管辖法院
	  Textbox szbd;//诉争标地
	  Datebox wtrq;//  委托日期',
	  Datebox larq;// 立案日期
	  Datebox dbrq;// 答辩日期
	  Datebox ktrq;// '开庭日期',
	  Datebox dtjclrq;//待提交材料日期
	  Datebox pjsdrq;//判决收到日期
	  Datebox ssqjxr;//上诉期绝限日
	  
	  Textbox dlr;// 代理人
	  Textbox lhbar;// 联合办案人
	  Textbox sjydh;//书记员电话
	  Textbox cbfg;//承办法官
	  Doublebox jcdlf;//基础代理费
	  Doublebox fxdlf;//风险代理费
	  Checkbox zf;//是否支付
	  Checkbox kjfp;//是否开具发票
	  Textbox remarks;//备注
	
	  
	  //后台全局变量
	//案件类型
	public List<Ajlx> list_ajlx = new ArrayList<Ajlx>();

/**
 * 新增
 * @throws ParseException 
 */
public void onClick$addBtn() throws ParseException{
	
	String aaa = ajlx.getValue();

	if(ajlx.getValue() == null || "".equals(ajlx.getValue())){
		Messagebox.show("请选择案件类型: \n","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	
	
	Map<Object,Object> map = new HashMap<>();

	map.clear();
	getInfoFromPage(map);//获取输入参数
	

//	 String check_res = checkData(map);//检查录入数据内容是否合法
//	 if(check_res != null){
//			Messagebox.show("数据内容异常: \n"+check_res,"注意", Messagebox.OK, Messagebox.EXCLAMATION);
//			return;
//	}
	 String ajh =  getAjhByAjlx((String)map.get("ajlx"));
	 map.put("ajh", ajh);
	
	try {
		
		int result = ssajService.insertSsaj(map);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Messagebox.show("保存失败，一般是某些信息长度超出限制，请复制以下信息提交给管理员\n"+map.toString(),"注意", Messagebox.OK, Messagebox.ERROR);
		return;
	}
	System.out.println();
	addSs.detach();//关闭窗口
	SsController ssController = (SsController)session.getAttribute("ssController");
	ssController.ajh.setValue(ajh);
	ssController.onClick$queryButton();
	Messagebox.show("新增保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
}
/**
* 检查数据内容是否合法
* @param list
* @return
*/
private String checkData(Map<Object,Object> map){
	
		if(map.get("ajlx") == null){
			return "\n 案件类型不能为空";
		}
		//检查案件类型是否合法
		
		if(!ajlx_str.contains((String)map.get("ajlx"))){
			return map.get("ajlx") +"  \n该案件类型不合法\n合法案件类型如下：\n"+ajlx_str;
		}
	
	return null;
}
/**
 * 根据案件类型，获得合适的案件号
 * @param lazbService
 * @param lazb
 * @return
 */
private String getAjhByAjlx(String ajlx){
	Map<Object,Object> map = new HashMap<>();
	String ajh="";
	
	int maxajh = Qjbl.getMaxAjhSs();
	if(maxajh == 0 ){
		int maxajh1 = ssajService.maxAjh(null);
		Qjbl.setMaxAjhSs(++maxajh1);
		maxajh = maxajh1;
	}
	
	for(Ajlx ajlx_t:list_ajlx){
//		ajlx_str = ajlx_str+","+ajlx.getAjlx();
		if((ajlx_t.getAjlx()).endsWith(ajlx)){
			ajh = ajlx_t.getAjh();
			break;
		}
	}
	
	
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR)-2000;
	if(maxajh<10){
		return ajh+year+"000"+maxajh;
	}else if(maxajh<100){
		return ajh+year+"00"+maxajh;
	}else if(maxajh<1000){
		return ajh+year+"0"+maxajh;
	}else{
		return ajh+year+maxajh;
	}
	
}


/**
 * 将页面传递来的信息存入MAP
 * @return
 */
private void getInfoFromPage(Map<Object,Object> map){
	
	
	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getValue());
	}//  '案件类型',ay
	if(hth.getValue() != null && !"".equals(hth.getValue())){
		map.put("hth", hth.getValue());
	}//  '案件类型',ay
	if(ay.getValue() != null && !"".equals(ay.getValue())){
		map.put("ay",ay.getValue() );
	}//  案由                    
	if(ajzt.getValue() != null && !"".equals(ajzt.getValue())){
		map.put("ajzt",ajzt.getValue() );
	}// , 案件状态
	if(yg.getValue() != null && !"".equals(yg.getValue())){
		map.put("yg",yg.getValue() );
	}//  原告
	if(bg.getValue() != null && !"".equals(bg.getValue())){
		map.put("bg",bg.getValue() );
	}//  被告
	if(dsr.getValue() != null && !"".equals(dsr.getValue())){
		map.put("dsr",dsr.getValue() );
	}//  第三人
	
	if(gxfy.getValue() != null && !"".equals(gxfy.getValue())){
		map.put("gxfy",gxfy.getValue() );
	}// 管辖法院
	
	if(szbd.getValue() != null && !"".equals(szbd.getValue())){
		map.put("szbd",szbd.getValue() );
	}// 诉争标地
	
	if(sj.getValue() != null && !"".equals(sj.getValue())){
		map.put("sj",sj.getValue() );
	}// 审级
	if(wtrq.getValue() != null && !"".equals(wtrq.getValue())){
		map.put("wtrq",wtrq.getValue() );
	}// 委托日期
	if(larq.getValue() != null && !"".equals(larq.getValue())){
		map.put("larq",larq.getValue() );
	}// 立案日期
	
	if(dbrq.getValue() != null && !"".equals(dbrq.getValue())){
		map.put("dbrq",dbrq.getValue() );
	}// 答辩日期
	
	if(ktrq.getValue() != null && !"".equals(ktrq.getValue())){
		map.put("ktrq",ktrq.getValue() );
	}//开庭日期
	if(dtjclrq.getValue() != null && !"".equals(dtjclrq.getValue())){
		map.put("dtjclrq",dtjclrq.getValue() );
	}// 待提交材料日期
	
	if(pjsdrq.getValue() != null && !"".equals(pjsdrq.getValue())){
		map.put("pjsdrq",pjsdrq.getValue() );
	}// 判决收到日期
	if(ssqjxr.getValue() != null && !"".equals(ssqjxr.getValue())){
		map.put("ssqjxr",ssqjxr.getValue() );
	}// 上诉期绝限日
	if(dlr.getValue() != null && !"".equals(dlr.getValue())){
		map.put("dlr",dlr.getValue() );
	}// 代理人
	if(lhbar.getValue() != null && !"".equals(lhbar.getValue())){
		map.put("lhbar",lhbar.getValue() );
	}// 联合办案人
	
	if(sjydh.getValue() != null && !"".equals(sjydh.getValue())){
		map.put("sjydh",sjydh.getValue() );
	}// 书记员电话
	if(cbfg.getValue() != null && !"".equals(cbfg.getValue())){
		map.put("cbfg",cbfg.getValue() );
	}// 承办法官
	if(jcdlf.getValue() != null && !"".equals(jcdlf.getValue())){
		map.put("jcdlf",jcdlf.getValue() );
	}// 基础代理费
	if(fxdlf.getValue() != null && !"".equals(fxdlf.getValue())){
		map.put("fxdlf",fxdlf.getValue() );
	}// 风险代理费
	if(zf.isChecked()){
		map.put("zf",1);
	}else{
		map.put("zf",0);// 是否支付
	}
	
	if(kjfp.isChecked()){
		map.put("kjfp",1);
	}else{
		map.put("kjfp",0);// 是否开具发票
	}
	
	
	if(remarks.getValue() != null && !"".equals(remarks.getValue())){
		map.put("remarks",remarks.getValue() );
	}//备注
	User user = (User)session.getAttribute("user");
	map.put("creater", user.getUser_name());
	
}
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	
	
	
	Ajlx ajlx1 = new Ajlx();
	ajlx1.setAjh("LMY");
	ajlx1.setAjlx("民事一审");
	list_ajlx.add(ajlx1);
	
	Ajlx ajlx2 = new Ajlx();
	ajlx2.setAjh("LME");
	ajlx2.setAjlx("民事二审");
	list_ajlx.add(ajlx2);
	
	
	Ajlx ajlx2_1 = new Ajlx();
	ajlx2_1.setAjh(" LMZ");
	ajlx2_1.setAjlx("民事再审");
	list_ajlx.add(ajlx2_1);
	
	Ajlx ajlx3 = new Ajlx();
	ajlx3.setAjh("LXY");
	ajlx3.setAjlx("行政一审");
	list_ajlx.add(ajlx3);
	
	Ajlx ajlx4 = new Ajlx();
	ajlx4.setAjh("LXE");
	ajlx4.setAjlx("行政二审");
	list_ajlx.add(ajlx4);
	
	//
	Ajlx ajlx4_1 = new Ajlx();
	ajlx4_1.setAjh("LXZ");
	ajlx4_1.setAjlx("行政再审");
	list_ajlx.add(ajlx4_1);
	
	Ajlx ajlx5 = new Ajlx();
	ajlx5.setAjh("LSY");
	ajlx5.setAjlx("刑事一审");
	list_ajlx.add(ajlx5);
	
	Ajlx ajlx6 = new Ajlx();
	ajlx6.setAjh("LSE");
	ajlx6.setAjlx("刑事二审");
	list_ajlx.add(ajlx6);
	
	Ajlx ajlx7 = new Ajlx();
	ajlx7.setAjh("LLZ");
	ajlx7.setAjlx("劳动仲裁");
	list_ajlx.add(ajlx7);
	
	Ajlx ajlx8 = new Ajlx();
	ajlx8.setAjh("LMZ");
	ajlx8.setAjlx("商事仲裁");
	list_ajlx.add(ajlx8);
	
	Ajlx ajlx9 = new Ajlx();
	ajlx9.setAjh("LGW");
	ajlx9.setAjlx("顾问咨询");
	list_ajlx.add(ajlx9);
	
	Ajlx ajlx10 = new Ajlx();
	ajlx10.setAjh("LCH");
	ajlx10.setAjlx("出具律师函");
	list_ajlx.add(ajlx10);
	
	Ajlx ajlx11 = new Ajlx();
	ajlx11.setAjh("LQT");
	ajlx11.setAjlx("法律其它");
	list_ajlx.add(ajlx11);
	
	ListModel ajlxModelList =new ListModelList<Ajlx>();
	ajlxModelList = new ListModelList(list_ajlx);
	ajlx.setModel(ajlxModelList);
	
	//案由
	List<Ajlx> list_ay = new ArrayList<Ajlx>();
	Ajlx ay1 = new Ajlx();
	ay1.setAjlx("合同纠纷");
	list_ay.add(ay1);
	
	Ajlx ay2 = new Ajlx();
	ay2.setAjlx("劳动纠纷");
	list_ay.add(ay2);
	
	Ajlx ay3 = new Ajlx();
	ay3.setAjlx("离婚纠纷");
	list_ay.add(ay3);
	
	ListModel ayModelList =new ListModelList<Ajlx>();
	ayModelList = new ListModelList(list_ay);
	
	ay.setModel(ayModelList);
	
	
	//案件状态
	List<Ajlx> list_ajzt = new ArrayList<Ajlx>();
	Ajlx ajzt1 = new Ajlx();
	ajzt1.setAjlx("待立案");
	list_ajzt.add(ajzt1);
	
	Ajlx ajzt2 = new Ajlx();
	ajzt2.setAjlx("已立案");
	list_ajzt.add(ajzt2);
	
	Ajlx ajzt3 = new Ajlx();
	ajzt3.setAjlx("管辖权异议");
	list_ajzt.add(ajzt3);
	
	Ajlx ajzt4 = new Ajlx();
	ajzt4.setAjlx("待开庭");
	list_ajzt.add(ajzt4);

	Ajlx ajzt5 = new Ajlx();
	ajzt5.setAjlx("已开庭");
	list_ajzt.add(ajzt5);

	Ajlx ajzt6 = new Ajlx();
	ajzt6.setAjlx("待第N次开庭");
	list_ajzt.add(ajzt6);

	Ajlx ajzt7 = new Ajlx();
	ajzt7.setAjlx("待提交材料");
	list_ajzt.add(ajzt7);
	

	Ajlx ajzt8 = new Ajlx();
	ajzt8.setAjlx("庭审结束");
	list_ajzt.add(ajzt8);

	Ajlx ajzt9 = new Ajlx();
	ajzt9.setAjlx("撤诉");
	list_ajzt.add(ajzt9);

	Ajlx ajzt10 = new Ajlx();
	ajzt10.setAjlx("调解结案");
	list_ajzt.add(ajzt10);

	Ajlx ajzt11 = new Ajlx();
	ajzt11.setAjlx("判决结案");
	list_ajzt.add(ajzt11);

	Ajlx ajzt12 = new Ajlx();
	ajzt12.setAjlx("中止审理");
	list_ajzt.add(ajzt12);

	Ajlx ajzt13 = new Ajlx();
	ajzt13.setAjlx("移送其院");
	list_ajzt.add(ajzt13);

	
	ListModel ajztModelList =new ListModelList<Ajlx>();
	ajztModelList = new ListModelList(list_ajzt);
	
	ajzt.setModel(ajztModelList);
	
	//审级
	List<Ajlx> list_sj = new ArrayList<Ajlx>();
	Ajlx sj1 = new Ajlx();
	sj1.setAjlx("一审");
	list_sj.add(sj1);
	
	Ajlx sj2 = new Ajlx();
	sj2.setAjlx("二审");
	list_sj.add(sj2);
	
	Ajlx sj3 = new Ajlx();
	sj3.setAjlx("再审");
	list_sj.add(sj3);
	
	Ajlx sj4 = new Ajlx();
	sj4.setAjlx("仲裁");
	list_sj.add(sj4);
	
	Ajlx sj5 = new Ajlx();
	sj5.setAjlx("非诉审理");
	list_sj.add(sj5);
	
	ListModel sjModelList =new ListModelList<Ajlx>();
	sjModelList = new ListModelList(list_sj);
	
	sj.setModel(sjModelList);
}
	
}
