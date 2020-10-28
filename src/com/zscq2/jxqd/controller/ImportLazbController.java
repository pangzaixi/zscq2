package com.zscq2.jxqd.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.system.base.Qjbl;
import com.yewu.jxqd.bean.Jxqd;
import com.yewu.zscq.bean.User;
import com.zscq2.jxqd.bean.Ajlx;
import com.zscq2.jxqd.bean.Lazb;
import com.zscq2.jxqd.service.serviceimpl.LazbService;

@Controller("importLazbController")   
@Scope("prototype") 
public class ImportLazbController extends GenericForwardComposer{
	//案件类型检查
//		String ajlx = "商标异议申请,商标驳回复审,补正,全部驳回,商标驳回复审行政诉讼,商标注册申请,驳回复审决定,部分驳回,商标撤三申请,商标撤三复审,商标撤三答辩,商标驳回复审补正,商标驳回,商标补正,商标转让,商标异议答辩,商标异议补正,商标部分驳回,商标驳回决定,商标无效宣告,异议决定,商标地址变更,商标续展,商标变更,商标撤三决定,撤销复审答辩,诉讼,商标撤三补正,商标撤销复审,撤销复审决定,商标分割申请,证据交换,商标异议予以注册,异议决定准予注册,商标异议答辩通知,商标异议撤回,商标无效宣告撤回,商标异议答辩补正,异议决定不准予注册,撤三决定,分割申请,商标异议答辨,商标变更补正,商标撤回补正,商标无效宣告答辩,不予注册复审,商标行政诉讼,驳回复审,补正证据,异议补正,撤三复审,无效宣告行政诉讼,是否提无效宣告";
	String ajlx_str="";
	Window importLazb;
	Button importData;
	//获取service对象
	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		
	Listbox listbox;//页面遍历方式加载数据
	List<Lazb> list_lazb =  new ArrayList<Lazb>();
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);//该行必须存在，否则异常
		
		Map map = new HashMap<Object,Object>();
		List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
		for(Ajlx ajlx:list_ajlx){
			ajlx_str = ajlx_str+","+ajlx.getAjlx();
		}
		
		System.out.println();
		String msg = (String)Executions.getCurrent().getArg().get("msg");
		System.out.println();
		list_lazb =  (List<Lazb>)Executions.getCurrent().getArg().get("list_lazb");
		
		
		ListModel listModelList =new ListModelList<Jxqd>();
		listModelList = null;
		if(list_lazb!= null && list_lazb.size()>0){
			listModelList = new ListModelList(list_lazb);
		}

		
		
		
		listbox.setModel(listModelList);
	}
	public void onClick$importData() throws ParseException{
//		importData.setVisible(false);
		User user = (User)session.getAttribute("user");
		LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
		Map<Object,Object> map = new HashMap<>();
//		for(Lazb lazb:list_lazb){
		
		//检查案件类型是否合法
		for(int i = list_lazb.size()-1;i>=0;i--){
			if(!ajlx_str.contains(list_lazb.get(i).getAjlx())){
				Messagebox.show("案件类型不合法\n"+list_lazb.get(i).getAjlx(),"注意", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
		}
//		for(int i = list_lazb.size()-1;i>=0;i--){
		for(int i = 0;i < list_lazb.size();i++){	
			Lazb lazb = list_lazb.get(i);
			map.clear();
			
			String ajh = getAjhByAjlx(lazbService,lazb);
			
			map.clear();
			map = lazb.getMap();
			map.put("ajh", ajh);
			map.put("creater", user.getUser_name()+" excel导入");
			try {
				int result = lazbService.insertLazb(map);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Messagebox.show("数据导入异常，如果本次导入案件被部分导入系统，请检查立案表，删除被导入的案件记录，问题一般是因为导入信息长度过长\n\n"
						+ "请将下属信息提交给管理员\n"+map.toString(),"注意", Messagebox.OK, Messagebox.ERROR);
				return;
			}	
		}
		//刷新父窗口
		LazbController lazbController = (LazbController)session.getAttribute("lazbController");
		lazbController.onClick$queryButton();
		importLazb.detach();
		Messagebox.show("数据导入成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	/**
	 * 根据案件类型，获得合适的案件号
	 * @param lazbService
	 * @param lazb
	 * @return
	 */
	private String getAjhByAjlx(LazbService lazbService,Lazb lazb){
		Map<Object,Object> map = new HashMap<>();
		String ajh="";
		

		
		List<Ajlx> list_ajlx = lazbService.selectAjlx(map);
		for(Ajlx ajlx:list_ajlx){
//			ajlx_str = ajlx_str+","+ajlx.getAjlx();
			if((ajlx.getAjlx()).endsWith(lazb.getAjlx())){
				ajh = ajlx.getAjh();
				break;
			}
		}
		
		
		
		map.clear();
		map.put("ajh", ajh);
//		int no = lazbService.maxAjh(map);
//		no++;
		
		int no = Qjbl.getMaxAjh();
		if(no == 0){
			int no1 = lazbService.maxAjh(null);
			Qjbl.setMaxAjh(++no1);
			no = no1;
		}
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR)-2000;
		return ajh+year+no;
	}
	
}
