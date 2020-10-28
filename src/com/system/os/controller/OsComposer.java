package com.system.os.controller;


import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.sys.EventListenerMap;
import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zkmax.zul.Navbar;
//import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treerow;

import com.system.tree.service.TreeService;
import com.system.utils.ApplicationBean;

import menu2.MenuNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Init;

import org.zkoss.zkplus.spring.SpringUtil;


/**
 * 该类是主框架对应的绑定类，主要负责左侧功能列表的功能项添加，
 * 以及对应的页面跳转、主显示区分标签显示
 * @author 庞在溪
 *
 */
@Controller
public class OsComposer extends GenericForwardComposer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5091932689941487700L;
	ListModelList menuModel = new ListModelList();
	Listbox menuListbox;
	MenuNodeSelectListener listener = new MenuNodeSelectListener();
	NormalMenuSelectListener listener2 = new NormalMenuSelectListener();
	TreeMenuSelectListener listener3 = new TreeMenuSelectListener();
//	NavbarMenuSelectListener listener4 = new NavbarMenuSelectListener();
	MenuNodeItemRenderer renderer = new MenuNodeItemRenderer();
	
	Tabbox tabbox;
	Menubar menubar;
	Button queryby_type;
	Tree tree;
//	Navbar navbar;
	
	
//	@Init
//	public void init() {
//		System.out.println();
//	}
	public OsComposer(){
		System.out.println();
	 
		
		menuModel.add(new MenuNode("设备查询","/jsp/os/sbcx.zul"));
		
		
		
	}
	@PostConstruct
    private void init(){
//		System.out.println("");
//        System.out.println(treeService.getName());
       
    }

	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
//		if(session.getAttribute("user")==null){
//			Executions.sendRedirect("../");
//			return;
//		}
		//初始化左侧List菜单=====================================
		menuListbox.setModel(menuModel);
		menuListbox.setItemRenderer(renderer);
		//menuListbox.addEventListener(Events.ON_SELECT,listener);
		menuListbox.addEventListener(Events.ON_SELECT,listener);
		
	}

	/**
	 * 按钮的点击事件
	 * TreeService dataSource = (TreeService)SpringUtil.getBean("treeService");
	 * org.zkoss.zkplus.spring.SpringUtil是一个实用类，它允许你使用简单的方式来获取 Spring-managed bean。
	 */
	public void onClick$queryby_type(){
		System.out.println();
		TreeService dataSource = (TreeService)SpringUtil.getBean("treeService");
		System.out.println(dataSource.getName());
	}

	public void onclickenent(){
		System.out.println("111");
	}
	/**
	 * 渲染方法
	 * @author thinker
	 *
	 */
	class MenuNodeItemRenderer implements ListitemRenderer{
		

		@Override
		public void render(Listitem item, Object data, int arg2) throws Exception {
			// TODO Auto-generated method stub
			MenuNode node = (MenuNode)data;
			if(node.getLabel().equals("注销")){
				item.setImage("/image/exit.gif");
			}else{
				item.setImage("/image/1.gif");
			}
			item.setLabel(node.getLabel());
			item.setValue(node);
			item.setHeight("30px");
		}
	}
		

	/**
	 * 左侧list监听事件
	 * @author thinker
	 *
	 */
	
	class MenuNodeSelectListener implements EventListener{
	
		public void onEvent(Event event) throws Exception {
			List<Component> list = tabbox.getChildren();
			Tabpanels tabpanels = (Tabpanels)list.get(1);
			Listitem item = menuListbox.getSelectedItem();

			menuListbox.setSelectedItem(null);
			if(item.getLabel().equals("注销")){
				session.removeAttribute("user");
				Executions.sendRedirect("../");
				return;
			}//
			if(item.getLabel().equals("返回监控界面")){
				
				Executions.sendRedirect("../monitor.zul");
				return;
			}
			Tabs tabs = (Tabs)list.get(0);
			List<Tab> list_tab = (List)tabs.getChildren();
			for(int i=0;i<list_tab.size();i++){
				Tab tab = list_tab.get(i);
				if(tab.getLabel().trim().equals(item.getLabel().trim())){
					System.out.println("该页面已经存在");
//					tabpanels.invalidate();
					tabbox.setSelectedIndex(i);//该行使得分页标签显示为新加的页，
					//也就是最后一页		
					return;
				}
			}
			
			Tab tab = new Tab();
			tab.setClosable(true);
			tabs.appendChild(tab);
			
			Tabpanel tabpanel = new Tabpanel();
			
			Div div = new Div();
			tabpanel.appendChild(div);
			tabpanels.appendChild(tabpanel);			
			
			//================
			MenuNode node = (MenuNode)item.getValue();
			Executions.createComponents(node.getLink(),div,null);
			tab.setLabel(String.valueOf(node.getLabel()));			
	
			//==================
			tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
			//也就是最后一页		
			tabpanels.invalidate();//该行使得新加页显示为当前页，必须加该行
			
		}		
	}
	/**
	 * 传统菜单监听事件
	 * @author thinker
	 *
	 */
	class NormalMenuSelectListener implements EventListener{
		public void onEvent(Event event) throws Exception {
			Menuitem menuitem;
			String url;
			try {//如果点击菜单栏的空白部分，该方法返回的是nemubar对象，无法赋值到menuitem对象，捕获异常跳出逻辑
				menuitem = (Menuitem)event.getTarget();
				url = (String)menuitem.getAttribute("url");
				System.out.println(url);
			} catch (Exception e) {
				return;
			}
			
			List<Component> list = tabbox.getChildren();
			Tabpanels tabpanels = (Tabpanels)list.get(1);
			
			Tabs tabs = (Tabs)list.get(0);
			List<Tab> list_tab = (List)tabs.getChildren();
			for(int i=0;i<list_tab.size();i++){
				Tab tab = list_tab.get(i);
				if(tab.getLabel().trim().equals(menuitem.getLabel().trim())){
					System.out.println("该页面已经存在");
//					tabpanels.invalidate();
					tabbox.setSelectedIndex(i);//该行使得分页标签显示为新加的页，
					//也就是最后一页		
					return;
				}
			}
			
			Tab tab = new Tab();
			tab.setClosable(true);
			tabs.appendChild(tab);
			
			Tabpanel tabpanel = new Tabpanel();
			
			Div div = new Div();
			tabpanel.appendChild(div);
			tabpanels.appendChild(tabpanel);			
			
			//================
			Executions.createComponents(url,div,null);
			tab.setLabel(String.valueOf(menuitem.getLabel()));			
	
			//==================
			tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
			//也就是最后一页		
			tabpanels.invalidate();//该行使得新加页显示为当前页，必须加该行
		}
	}
	
	/**
	 * 树形菜单监听事件
	 * @author thinker
	 *
	 */
	class TreeMenuSelectListener implements EventListener{
		public void onEvent(Event event) throws Exception {
			Treerow treerow;
			String url;
			try {//如果点击菜单栏的空白部分，该方法返回的是nemubar对象，无法赋值到menuitem对象，捕获异常跳出逻辑
				treerow = (Treerow)event.getTarget();
				url = (String)treerow.getAttribute("url");
				System.out.println(url);
			} catch (Exception e) {
				return;
			}
			
			List<Component> list = tabbox.getChildren();
			Tabpanels tabpanels = (Tabpanels)list.get(1);
			
			Tabs tabs = (Tabs)list.get(0);
			List<Tab> list_tab = (List)tabs.getChildren();
			for(int i=0;i<list_tab.size();i++){
				Tab tab = list_tab.get(i);
				if(tab.getLabel().trim().equals(treerow.getLabel().trim())){
					System.out.println("该页面已经存在");
//					tabpanels.invalidate();
					tabbox.setSelectedIndex(i);//该行使得分页标签显示为新加的页，
					//也就是最后一页		
					return;
				}
			}
			
			Tab tab = new Tab();
			tab.setClosable(true);
			tabs.appendChild(tab);
			
			Tabpanel tabpanel = new Tabpanel();
			
			Div div = new Div();
			tabpanel.appendChild(div);
			tabpanels.appendChild(tabpanel);			
			
			//================
			Executions.createComponents(url,div,null);
			tab.setLabel(String.valueOf(treerow.getLabel()));			
	
			//==================
			tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
			//也就是最后一页		
			tabpanels.invalidate();//该行使得新加页显示为当前页，必须加该行
		}
	}
	
	/**
	 * navbar菜单监听事件
	 * @author thinker
	 *
	 */
//	class NavbarMenuSelectListener implements EventListener{
//		public void onEvent(Event event) throws Exception {
//			Navitem navitem;
//			String url;
//			try {//如果点击菜单栏的空白部分，该方法返回的是nemubar对象，无法赋值到menuitem对象，捕获异常跳出逻辑
//				navitem = (Navitem)event.getTarget();
//				url = (String)navitem.getAttribute("url");
//				System.out.println(url);
//			} catch (Exception e) {
//				return;
//			}
//			
//			List<Component> list = tabbox.getChildren();
//			Tabpanels tabpanels = (Tabpanels)list.get(1);
//			
//			Tabs tabs = (Tabs)list.get(0);
//			List<Tab> list_tab = (List)tabs.getChildren();
//			for(int i=0;i<list_tab.size();i++){
//				Tab tab = list_tab.get(i);
//				if(tab.getLabel().trim().equals(navitem.getLabel().trim())){
//					System.out.println("该页面已经存在");
////					tabpanels.invalidate();
//					tabbox.setSelectedIndex(i);//该行使得分页标签显示为新加的页，
//					//也就是最后一页		
//					return;
//				}
//			}
//			
//			Tab tab = new Tab();
//			tab.setClosable(true);
//			tabs.appendChild(tab);
//			
//			Tabpanel tabpanel = new Tabpanel();
//			
//			Div div = new Div();
//			tabpanel.appendChild(div);
//			tabpanels.appendChild(tabpanel);			
//			
//			//================
//			Executions.createComponents(url,div,null);
//			tab.setLabel(String.valueOf(navitem.getLabel()));			
//	
//			//==================
//			tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
//			//也就是最后一页		
//			tabpanels.invalidate();//该行使得新加页显示为当前页，必须加该行
//		}
//	}
}