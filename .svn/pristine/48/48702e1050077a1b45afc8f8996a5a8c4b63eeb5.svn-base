package com.system.utils;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.zkoss.zkplus.spring.SpringUtil;

import com.system.tree.service.TreeService;

/**
 * 可以用这种方式引入上下文对象，但是zk提供了封装的统一调用工具类
 * TreeService dataSource = (TreeService)SpringUtil.getBean("treeService");
 * 只要service实现处用@Service("treeService")为该service指定名称即可
 * @author thinker
 *
 */
public  class ApplicationBean {
	private static AbstractApplicationContext aalc =new ClassPathXmlApplicationContext("applicationContext.xml");
	public static AbstractApplicationContext getApplication(){
		return aalc;
	}

}
