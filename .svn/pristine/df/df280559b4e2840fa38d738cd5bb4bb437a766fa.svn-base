package menu2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.yewu.jxqd.service.serviceimpl.JxqdService;
import com.yewu.zscq.bean.User;


@Controller("updatePWDComposer")  
@Scope("prototype") 
public class UpdatePWDComposer extends GenericForwardComposer{
	Window updatePWDWin;
	Textbox pwd1;
	Textbox pwd2;
	
	
	//获取service对象
	JxqdService jxqdService = (JxqdService)SpringUtil.getBean("jxqdService");
/**
 * 	
 */
public void onClick$updatePWDBtn() {
	if(pwd1 != null && !"".equals(pwd1.getValue().trim()) && pwd2 != null && !"".equals(pwd2.getValue().trim())
			&&pwd1.getValue().trim().equals(pwd2.getValue().trim()) && pwd1.getValue().trim().length()>=6){
		
	}else{
		Messagebox.show("两次输入密码不一致，或者长度不足6位","注意", Messagebox.OK, Messagebox.EXCLAMATION);
		return;
	}
	User user = (User)session.getAttribute("user");
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("login_name", user.getLogin_name());
	map.put("login_pwd", pwd1.getValue().trim());
	jxqdService.updatePWD(map);
	Messagebox.show("密码更新成功","注意", Messagebox.OK, Messagebox.EXCLAMATION);
	updatePWDWin.detach();
}		
}
