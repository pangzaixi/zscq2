package com.system.base;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

import com.zscq2.jxqd.bean.Lazb;


@Controller("feizuduanController")   
@Scope("prototype") 
public class FeizuduanController  extends GenericForwardComposer{	

Label message;	
	
public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);//该行必须存在，否则异常
	System.out.println();
	String message_str = (String)Executions.getCurrent().getArg().get("message");
	message.setValue(message_str);
}	
}
