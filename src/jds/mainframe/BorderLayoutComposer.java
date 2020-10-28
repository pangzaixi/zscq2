package jds.mainframe;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;


/**
 * 该类是主框架对应的绑定类，主要负责左侧功能列表的功能项添加，
 * 以及对应的页面跳转、主显示区分标签显示
 * @author 庞在溪
 *
 */
public class BorderLayoutComposer extends GenericForwardComposer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5091932689941487700L;
	ListModelList menuModel = new ListModelList();
	Listbox menuListbox;
	MenuNodeSelectListener listener = new MenuNodeSelectListener();
	MenuNodeItemRenderer renderer = new MenuNodeItemRenderer();
	AnnotateDataBinder binder;
	Tabbox tabbox;

	
	public BorderLayoutComposer(){
		
		menuModel.add(new MenuNode("广告图片管理","picturemanage.zul"));
		menuModel.add(new MenuNode("新闻管理","newsmanage.zul"));
		menuModel.add(new MenuNode("分类管理","typemanage.zul"));
		menuModel.add(new MenuNode("资料上传","upload.zul"));
		menuModel.add(new MenuNode("密码管理","passwordedit.zul"));
		menuModel.add(new MenuNode("注销","注销"));
		menuModel.add(new MenuNode("测试1","test.zul"));
		menuModel.add(new MenuNode("测试2","/jsp/test/aaa.zul"));
		menuModel.add(new MenuNode("测试3","/jsp/test/bbb.zul"));
		
	}
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
//		if(session.getAttribute("user")==null){
//			Executions.sendRedirect("../");
//			return;
//		}
		//=====================================
		menuListbox.setModel(menuModel);
		menuListbox.setItemRenderer(renderer);
		//menuListbox.addEventListener(Events.ON_SELECT,listener);
		menuListbox.addEventListener(Events.ON_SELECT,listener);
		
		binder = new AnnotateDataBinder(comp);
		
		binder.loadAll();
	}

	class MenuNode {
		String label;
		String link;
		public MenuNode(String label,String link){
			this.label = label;
			this.link = link;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
	}
	/**
	 * 渲染方法
	 * @author thinker
	 *
	 */
	class MenuNodeItemRenderer implements ListitemRenderer{

		public void render(Listitem item, Object data) throws Exception {
			MenuNode node = (MenuNode)data;
			if(node.getLabel().equals("注销")){
				item.setImage("../image/exit.gif");
			}else{
				item.setImage("../image/1.gif");
			}
			item.setLabel(node.getLabel());
			item.setValue(node);
			item.setHeight("30px");
		}

		@Override
		public void render(Listitem item, Object data, int arg2) throws Exception {
			// TODO Auto-generated method stub
			MenuNode node = (MenuNode)data;
			if(node.getLabel().equals("注销")){
				item.setImage("../image/exit.gif");
			}else{
				item.setImage("../image/1.gif");
			}
			item.setLabel(node.getLabel());
			item.setValue(node);
			item.setHeight("30px");
		}
	}
	/**
	 * 监听事件
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
//			Iframe ifr = new Iframe();
//			tabpanel.appendChild(ifr);
			tabpanels.appendChild(tabpanel);			
			
			
			//================
			MenuNode node = (MenuNode)item.getValue();
			Executions.createComponents(node.getLink(),div,null);
//			ifr.setSrc(node.getLink());
			tab.setLabel(String.valueOf(node.getLabel()));			
	
			//==================
			tabbox.setSelectedIndex(tabs.getChildren().size()-1);//该行使得分页标签显示为新加的页，
			//也就是最后一页		
			tabpanels.invalidate();//该行使得新加页显示为当前页，必须加该行
			
		}		
	}
}
