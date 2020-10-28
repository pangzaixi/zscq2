package menu2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
//import org.zkoss.zkmax.zul.Nav;
//import org.zkoss.zkmax.zul.Navbar;
//import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.system.tree.service.TreeService;

//import menu2.BorderLayoutComposer.NavbarMenuSelectListener;
import menu2.BorderLayoutComposer.NormalMenuSelectListener;
import menu2.BorderLayoutComposer.TreeMenuSelectListener;

class method {
	
	
	
	
public List<com.system.tree.bean.Menu> getMenusbyPid(com.system.tree.bean.Menu menus,String pid){
	
	return null;
}
/**
 * 传统菜单初始化
 * @param menubar
 */
public void initMenuBar(Menubar menubar,NormalMenuSelectListener listener){
	TreeService treeService = (TreeService)SpringUtil.getBean("treeService");
	List<com.system.tree.bean.Menu> firstLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getFirstLevelMenus();
	List<com.system.tree.bean.Menu> secondLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getSecondLevelMenus();
	List<com.system.tree.bean.Menu> thirdLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getThirdLevelMenus();
	
	//将一级菜单加入菜单条
	for(com.system.tree.bean.Menu menu_t:firstLevelMenus){
		Menu menu = new Menu();
		menu.setLabel(menu_t.getText());
		menu.setAttribute("id", menu_t.getId());//将数据库菜单项的字段信息写入zk的菜单的参数中
		System.out.println();
		menubar.appendChild(menu);
	}	
	
	//根据menubar对象获得以及菜单集合
	List<Menu> firstMenus = menubar.getChildren();
	//遍历所有一级菜单和二级菜单，把耳机菜单挂到对应一级菜单下
	for(Menu menu:firstMenus){
		System.out.println(menu.getLabel());
		Object id = menu.getAttribute("id");
		Menupopup menupopup = new Menupopup();
		menu.appendChild(menupopup);
		for(com.system.tree.bean.Menu menu_2:secondLevelMenus){
			if(id.equals(menu_2.getPid())){//如果二级菜单的PID等于一级菜单的id,则需要将该二级菜单加入到该以及菜单下
				if(menu_2.getIsleaf() !=null && menu_2.getIsleaf()==1){//叶子菜单
					Menuitem menuitem = new Menuitem();
					menuitem.setLabel(menu_2.getText());
					menuitem.setAttribute("url", menu_2.getUrl());
					menuitem.addEventListener(Events.ON_CLICK, listener);
					menupopup.appendChild(menuitem);
				}else{//非叶子菜单
					Menu menu_t = new Menu();
					menu_t.setLabel(menu_2.getText());
					menu_t.setAttribute("id", menu_2.getId());
					menupopup.appendChild(menu_t);
				}
			}
		}
	}
	//遍历一二级菜单，将三级菜单加到对应二级菜单下
	for(Menu menu:firstMenus){
		Menupopup menupopup = (Menupopup)menu.getFirstChild();
		List<Component> list = menupopup.getChildren();//获得以及菜单包含的二级菜单
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){//	menupopup包含子项的类型class org.zkoss.zul.Menuitem,class org.zkoss.zul.Menu
				System.out.println(menu.getLabel() +list.get(i).getClass().getTypeName().trim());
				if("org.zkoss.zul.Menu".equals(list.get(i).getClass().getTypeName().trim())){//如果当前二级菜单是子菜单，不是菜单项，则遍历该二级菜单的三级菜单，并加载
					Menu secondLevelMenu = (Menu)list.get(i);
					Object id = secondLevelMenu.getAttribute("id");
					Menupopup menupopup3 = new Menupopup();
					list.get(i).appendChild(menupopup3);
					for(com.system.tree.bean.Menu menu_3:thirdLevelMenus){//三级菜单都是叶子菜单
						if(id.equals(menu_3.getPid())){
							Menuitem menuitem = new Menuitem();
							menuitem.setLabel(menu_3.getText());
							menuitem.setAttribute("url", menu_3.getUrl());
							menuitem.addEventListener(Events.ON_CLICK, listener);
							menupopup3.appendChild(menuitem);
						}	
					}
				}
			}
			
		}
		
		//class org.zkoss.zul.Menuitem
		//System.out.println(list.get(0).getAttribute("id"));
	}
	System.out.println();
}
/**
 * 初始化左侧菜单树
 * @param menubar
 * @param listener
 */
public void initMenuTree(Tree tree,TreeMenuSelectListener listener){
	
//	Treecell treecell;
//	treecell.addEventListener(Events.ON_CLICK, listener);
	
	TreeService treeService = (TreeService)SpringUtil.getBean("treeService");
	List<com.system.tree.bean.Menu> firstLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getFirstLevelMenus();
	List<com.system.tree.bean.Menu> secondLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getSecondLevelMenus();
	List<com.system.tree.bean.Menu> thirdLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getThirdLevelMenus();
	
	Treechildren treechildren = new Treechildren();
	tree.appendChild(treechildren);
	
	
	//添加一级菜单
	for(com.system.tree.bean.Menu menu_t:firstLevelMenus){
		Treeitem treeitem = new Treeitem();
		Treerow treerow = new Treerow();
		Treechildren treechildren_t = new Treechildren();
		treerow.setLabel(menu_t.getText());
		treerow.setAttribute("id", menu_t.getId());
		treerow.setAttribute("isleaf", menu_t.getIsleaf());
		treeitem.appendChild(treerow);
		treeitem.appendChild(treechildren_t);
		treechildren.appendChild(treeitem);
		
	}
	
	List<Treeitem> list_first = tree.getChildren().get(1).getChildren();//获得一级菜单的
	
	//添加二级菜单
	for(Treeitem treeitem:list_first){
		System.out.println(treeitem.getTreechildren());
		System.out.println(treeitem.getTreerow().getAttribute("id"));
		Object id = treeitem.getTreerow().getAttribute("id");//一级菜单的ID
		Treechildren treechildren_t = treeitem.getTreechildren();
		//System.out.println(treeitem.getTreerow().getAttribute("isleaf"));
		//添加二级菜单
		for(com.system.tree.bean.Menu menu_t:secondLevelMenus){
			if(id.equals(menu_t.getPid())){
					Treeitem treeitem_t = new Treeitem();
					Treerow treerow = new Treerow();
					treerow.setLabel(menu_t.getText());
					treerow.setAttribute("url", menu_t.getUrl());
					treerow.setAttribute("id", menu_t.getId());
					treerow.setAttribute("isleaf", menu_t.getIsleaf());
					
					treeitem_t.appendChild(treerow);
					treechildren_t.appendChild(treeitem_t);
					
					if(menu_t.getIsleaf() != null && 1==menu_t.getIsleaf()){//叶子菜单加监听器
						treerow.addEventListener(Events.ON_CLICK, listener);
						treeitem_t.setImage("/image/exit.gif");
					}
			}
		}
	}
	
	//添加三级菜单
	
	for(Treeitem treeitem:list_first){
		Treechildren treechildren_t = treeitem.getTreechildren();
		//System.out.println(treeitem.getTreerow().getAttribute("isleaf"));
		//二级菜单
		List<Treeitem> treeitem_ts = treechildren_t.getChildren();
		for(Treeitem treeitem_1:treeitem_ts){
			Treerow tr = (Treerow)treeitem_1.getChildren().get(0);
			System.out.println(tr.getLabel());
			System.out.println(tr.getAttribute("isleaf"));
			System.out.println(tr.getAttribute("id"));
			Object id = tr.getAttribute("id");
			if(tr.getAttribute("isleaf") == null){//二级菜单中的有子菜单的菜单
				Treechildren treechildren_3 = new Treechildren();
				
				treeitem_1.appendChild(treechildren_3);
				for(com.system.tree.bean.Menu menu_t:thirdLevelMenus){
					if(id.equals(menu_t.getPid())){
						Treeitem ti = new Treeitem();
						treechildren_3.appendChild(ti);
						
						Treerow tr1 = new Treerow();
						tr1.setLabel(menu_t.getText());
						tr1.setAttribute("url", menu_t.getUrl());
						tr1.setAttribute("id", menu_t.getId());
						tr1.setAttribute("isleaf", menu_t.getIsleaf());
						tr1.addEventListener(Events.ON_CLICK, listener);
						ti.appendChild(tr1);
						ti.setImage("/image/exit.gif");
					}
				}
			}
		}
		System.out.println();
		
	}
	
}



/**
 * 初始化navbar菜单
 * @param navbar
 * @param listener
 */
//public void initNavbar(Navbar navbar,NavbarMenuSelectListener listener){
//	TreeService treeService = (TreeService)SpringUtil.getBean("treeService");
//	Map<Object,Object> map = new HashMap<Object,Object>();
//	map.put("level", 1);
//	List<com.system.tree.bean.Menu> firstLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getMenusForNavbar(map);
//	map.put("level", 2);
//	List<com.system.tree.bean.Menu> secondLevelMenus = (List<com.system.tree.bean.Menu>)treeService.getMenusForNavbar(map);
//	if(firstLevelMenus != null){
//		for(com.system.tree.bean.Menu menu_t1:firstLevelMenus){
//			Nav nav = new Nav();
//			nav.setIconSclass("z-icon-star");//设定图标
//			nav.setLabel(menu_t1.getText());
//			navbar.appendChild(nav);
//			Object id = menu_t1.getId();
//			for(com.system.tree.bean.Menu menu_t2:secondLevelMenus){
//				Navitem navitem = new Navitem();
//				if(id.equals(menu_t2.getPid())){
//					navitem.setLabel(menu_t2.getText());
//					navitem.setAttribute("url", menu_t2.getUrl());
//					navitem.addEventListener(Events.ON_CLICK, listener);
//					nav.appendChild(navitem);
//				}
//				
//			}
//			
//		}
//	}
//	
//	
//	
//}
}
