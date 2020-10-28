package com.yewu.jxqd.controller;

import java.text.ParseException;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.yewu.jxqd.bean.Jxqd;
import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;


@Controller("addJxqdController")   
@Scope("prototype") 
public class AddJxqdController extends GenericForwardComposer{

	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
		Window addJxqd;//新增窗口ID
	  Textbox ajh;// '案件号',
	  Textbox ajlx;//  '案件类型',
	  Textbox sbmc;//  '商标名称',
	  Textbox lb;// , '类别',
	  Textbox sbh;//  '商标号',
	  Datebox khwtrq;//  '客户委托日期',
	  Textbox khwtrq_str;//转换字段
	  Datebox sqr;// '申请日',
	  Textbox sqr_str;// '申请日',
	  Datebox swrq;// '收文日期',
	  Textbox swrq_str;// '收文日期',
	  Datebox csrq;// '初审日期',
	  Textbox csrq_str;// '初审日期',
	  Textbox ggq;// '公告期',
	  Datebox jxr;// '绝限日',
	  Textbox jxr_str;// '绝限日',
	  Datebox sctjr;// '首次提交日',
	  Textbox sctjr_str;// '首次提交日',
	  Datebox bcqx;// '补充期限',
	  Datebox bctjrq;// '补充提交日期',
	  Textbox bctjrq_str;// '补充提交日期',
	  Textbox scbsbh;// '首次报送编号',
	  Textbox ecbsbh;// '二次报送编号',
	  Textbox wtkhmc;// '委托客户名称',
	  Textbox bqqr;// '被请求人/商标权人/对方相对人',
	  Textbox wfdsr;// '申请人/请求人/我方当事人',
	  Textbox gfjg;// '官方结果',
	  Textbox dlr;// '代理人',
	  Textbox dlr2;// '代理人2',
	  Textbox ayr;// '案源人',
	  Textbox dlgs;// '代理公司',
	  Doublebox gf;// '官费',
	  Doublebox dlf;// '代理费',
	  Doublebox khfk;// '客户付款',
	  Doublebox ygkhkp;// '已给客户开票',
	  Doublebox djgsfk;// '代交公司付款',
	  Doublebox ygdjgskp;// '已给代交公司开票',
	  Doublebox dlryzftcf;// '代理人已支付提成费',
	  Doublebox ayryzftcf;// '案源人已支付提成费',
	  Textbox remarks;//备注
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	System.out.println();
	String msg = (String)Executions.getCurrent().getArg().get("msg");
	System.out.println();
}
	
/**
 * 新增
 * @throws ParseException 
 */
public void onClick$addBtn() throws ParseException{
	if(ajlx.getValue() == null || "".equals(ajlx.getValue().trim())
			|| sbmc.getValue() == null || "".equals(sbmc.getValue().trim())
			|| lb.getValue() == null || "".equals(lb.getValue().trim())
			|| sbh.getValue() == null || "".equals(sbh.getValue().trim())){
		
		
		Messagebox.show("案件类型、商标名称、类别、商标号不能为空","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	//获取service对象
	JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
	Map<Object,Object> map = new HashMap<>();
	map.put("ajh", ajh.getValue());
	map.put("begin", 0);
	map.put("end", 10);
	List<Jxqd> list = jxqdService.selectJXQD(map);
	if(list !=null && list.size()>=1){
		Messagebox.show("案件号重复，保存失败","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	map.clear();
	getInfoFromPage(map);//获取输入参数
	int result = jxqdService.insertJxqd(map);
	System.out.println();
	addJxqd.detach();//关闭窗口
	JxqdController jxqdController = (JxqdController)session.getAttribute("jxqdController");
	jxqdController.onClick$queryButton();
	Messagebox.show("保存成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
}

/**
 * 将页面传递来的信息存入MAP
 * @return
 */
private void getInfoFromPage(Map<Object,Object> map){
	if(ajh.getValue() != null && !"".equals(ajh.getValue().trim())){
		map.put("ajh", ajh.getValue());
	}// '案件号',
	if(ajlx.getValue() != null && !"".equals(ajlx.getValue())){
		map.put("ajlx", ajlx.getValue());
	}//  '案件类型',
	if(sbmc.getValue() != null && !"".equals(sbmc.getValue())){
		map.put("sbmc",sbmc.getValue() );
	}//  '商标名称',                    
	if(lb.getValue() != null && !"".equals(lb.getValue())){
		map.put("lb",lb.getValue() );
	}// , '类别',
	if(sbh.getValue() != null && !"".equals(sbh.getValue())){
		map.put("sbh",sbh.getValue() );
	}//  '商标号',
	if(khwtrq.getValue() != null && !"".equals(khwtrq.getValue())){
		map.put("khwtrq",khwtrq.getValue() );
	}//  '客户委托日期',
	
	if(sqr.getValue() != null && !"".equals(sqr.getValue())){
		map.put("sqr",sqr.getValue() );
	}// '申请日',
	
	if(swrq.getValue() != null && !"".equals(swrq.getValue())){
		map.put("swrq",swrq.getValue() );
	}// '收文日期',
	
	if(csrq.getValue() != null && !"".equals(csrq.getValue())){
		map.put("csrq",csrq.getValue() );
	}// '初审日期',
	if(ggq.getValue() != null && !"".equals(ggq.getValue())){
		map.put("ggq",ggq.getValue() );
	}// '公告期',
	if(jxr.getValue() != null && !"".equals(jxr.getValue())){
		map.put("jxr",jxr.getValue() );
	}// '绝限日',
	
	if(sctjr.getValue() != null && !"".equals(sctjr.getValue())){
		map.put("sctjr",sctjr.getValue() );
	}// '首次提交日',
	
	if(bcqx.getValue() != null && !"".equals(bcqx.getValue())){
		map.put("bcqx",bcqx.getValue() );
	}// '补充期限',
	if(bctjrq.getValue() != null && !"".equals(bctjrq.getValue())){
		map.put("bctjrq",bctjrq.getValue() );
	}// '补充提交日期',
	
	if(scbsbh.getValue() != null && !"".equals(scbsbh.getValue())){
		map.put("scbsbh",scbsbh.getValue() );
	}// '首次报送编号',
	if(ecbsbh.getValue() != null && !"".equals(ecbsbh.getValue())){
		map.put("ecbsbh",ecbsbh.getValue() );
	}// '二次报送编号',
	if(wtkhmc.getValue() != null && !"".equals(wtkhmc.getValue())){
		map.put("wtkhmc",wtkhmc.getValue() );
	}// '委托客户名称',
	if(bqqr.getValue() != null && !"".equals(bqqr.getValue())){
		map.put("bqqr",bqqr.getValue() );
	}// '被请求人/商标权人/对方相对人',
	if(wfdsr.getValue() != null && !"".equals(wfdsr.getValue())){
		map.put("wfdsr",wfdsr.getValue() );
	}// '申请人/请求人/我方当事人',
	if(gfjg.getValue() != null && !"".equals(gfjg.getValue())){
		map.put("gfjg",gfjg.getValue() );
	}// '官方结果',
	if(dlr.getValue() != null && !"".equals(dlr.getValue())){
		map.put("dlr",dlr.getValue() );
	}// '代理人',
	if(dlr2.getValue() != null && !"".equals(dlr2.getValue())){
		map.put("dlr2",dlr2.getValue() );
	}// '代理人2',
	if(ayr.getValue() != null && !"".equals(ayr.getValue())){
		map.put("ayr",ayr.getValue() );
	}// '案源人',
	if(dlgs.getValue() != null && !"".equals(dlgs.getValue())){
		map.put("dlgs",dlgs.getValue() );
	}// '代理公司',
	if(gf.getValue() != null && !"".equals(gf.getValue())){
		map.put("gf",gf.getValue() );
	}// '官费',
	if(dlf.getValue() != null && !"".equals(dlf.getValue())){
		map.put("dlf",dlf.getValue() );
	}// '代理费',
	if(khfk.getValue() != null && !"".equals(khfk.getValue())){
		map.put("khfk",khfk.getValue() );
	}// '客户付款',
	if(ygkhkp.getValue() != null && !"".equals(ygkhkp.getValue())){
		map.put("ygkhkp",ygkhkp.getValue() );
	}// '已给客户开票',
	if(djgsfk.getValue() != null && !"".equals(djgsfk.getValue())){
		map.put("djgsfk",djgsfk.getValue() );
	}// '代交公司付款',
	if(ygdjgskp.getValue() != null && !"".equals(ygdjgskp.getValue())){
		map.put("ygdjgskp",ygdjgskp.getValue() );
	}// '已给代交公司开票',
	if(dlryzftcf.getValue() != null && !"".equals(dlryzftcf.getValue())){
		map.put("dlryzftcf",dlryzftcf.getValue() );
	}// '代理人已支付提成费',
	if(ayryzftcf.getValue() != null && !"".equals(ayryzftcf.getValue())){
		map.put("ayryzftcf",ayryzftcf.getValue() );
	}// '案源人已支付提成费',
	if(remarks.getValue() != null && !"".equals(remarks.getValue())){
		map.put("remarks",remarks.getValue() );
	}//备注
	map.put("inputdate", new Date());//录入日期
	User user = (User)session.getAttribute("user");
	map.put("creater", user.getUser_name());
	
}

}
