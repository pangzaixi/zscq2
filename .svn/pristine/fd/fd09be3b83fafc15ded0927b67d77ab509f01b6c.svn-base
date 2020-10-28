package com.system.utils;

import java.util.Properties;

/**
 * 加密类,
 * 当PRODUCTION_MODE=true时是生产环境，需要对加密密码进行加密，开发环境下不用加密
 * @author thinker
 *
 */
public class C3P0DatasourcePropertiesFactory {
	private static final String PRODUCTION_MODE = "true";
	private static final String PROP_PASSWORD = "password";

	public static Properties getProperties(String pwd, String production) throws Exception {
		Properties p = new Properties();
 
		if (PRODUCTION_MODE.equals(production)) {
			try {
				//p.setProperty(PROP_PASSWORD, CryptoUtil.decode(pwd));//解密操作
				p.setProperty(PROP_PASSWORD, pwd);
			}catch (Exception e) {
				
				throw e;
			}
		}else{
			p.setProperty(PROP_PASSWORD, pwd);
		}
		
		return p;
	
	}
	/**
	 * 加密内部类，用于防治反编译
	 * @author thinker
	 *
	 */
	private class Invalid{  
        private void aaa(){
        	System.out.println();
        }
    }  
}