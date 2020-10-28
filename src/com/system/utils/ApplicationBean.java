package com.system.utils;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.zkoss.zkplus.spring.SpringUtil;

import com.system.tree.service.TreeService;

/**
 * ���������ַ�ʽ���������Ķ��󣬵���zk�ṩ�˷�װ��ͳһ���ù�����
 * TreeService dataSource = (TreeService)SpringUtil.getBean("treeService");
 * ֻҪserviceʵ�ִ���@Service("treeService")Ϊ��serviceָ�����Ƽ���
 * @author thinker
 *
 */
public  class ApplicationBean {
	private static AbstractApplicationContext aalc =new ClassPathXmlApplicationContext("applicationContext.xml");
	public static AbstractApplicationContext getApplication(){
		return aalc;
	}

}
