package com.system.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;

import com.zscq2.jxqd.service.serviceimpl.LazbService;


/**
 * 定时器，启动内蒙传数任务
 * @author thinker
 *
 */
@Controller("initListener")   
@Scope("prototype") 
public class InitListener   extends GenericForwardComposer implements ServletContextListener{  
	//获取service对象
//	LazbService lazbService = (LazbService)SpringUtil.getBean("lazbService");
//	@Autowired
//    private LazbService lazbService;

	
	
    @Override  
    public void contextDestroyed(ServletContextEvent context) {  
          
    }  
    
    @Override
    public void  contextInitialized(ServletContextEvent sce) {  
//
//    	LazbService lazbService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(LazbService.class);
//
//    	Map map = new HashMap<Object,Object>();
//        int no = lazbService.maxAjh(map);
//    	System.out.println("################"+no);

    }
//    @Override  
//    public void contextInitialized(ServletContextEvent context){  
//    	// 1、创建调度器Scheduler
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = null;
//    try {
//			scheduler = schedulerFactory.getScheduler();
//		
//        // 每天发送一次，作业日报、全天轨迹、图片
//        JobDetail jobDetail = JobBuilder.newJob(BusinessJob.class)
//                                        .withIdentity("job1", "group1").build();  
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                .startNow()//立即生效
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 45 10 * * ?")) 
//                .build();//0 0/1 23 * * ?      0 1 1 * * ?
//
//        //4、执行,开发时用页面按钮触发，上线时开放下三行代码，用定时器触发
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//        System.out.println("--------scheduler start ! ------------");
//        scheduler.start();//最后一行只启动一次即可
//        
//        List<String> a = scheduler.getTriggerGroupNames();
//        
//        
//        
//        //每10分钟发送一次   实时轨迹信息
//        JobDetail jobDetail2 = JobBuilder.newJob(BusinessJob.class)
//                                        .withIdentity("job2", "group2").build();  
//        CronTrigger cronTrigger2 = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                .startNow()//立即生效
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) 
//                .build();//0 0/1 23 * * ?      0 1 1 * * ?
//        scheduler.scheduleJob(jobDetail2, cronTrigger2);
////        
////        
////      //每小时发送一次   车辆信息
////        JobDetail jobDetail3 = JobBuilder.newJob(ShebeiJob3.class)
////                                        .withIdentity("job3", "group3").build();  
////        CronTrigger cronTrigger3 = TriggerBuilder.newTrigger().withIdentity("trigger3", "triggerGroup3")
////                .usingJobData("trigger3", "这是jobDetail3的trigger3")
////                .startNow()//立即生效
////                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?")) 
////                .build();//0 0/1 23 * * ?      0 1 1 * * ?
////        scheduler.scheduleJob(jobDetail3, cronTrigger3);
////        
////        
////        
////        System.out.println("--------scheduler start ! ------------");
////        scheduler.start();
//        
//        
//    } catch (SchedulerException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  
//    }  
}
