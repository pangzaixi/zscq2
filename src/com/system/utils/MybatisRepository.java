package com.system.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author fuguangli
 * @description 前沿mybatis扫描注解，此注解用于org.mybatis.spring.mapper.MapperScannerConfigurer扫描
 * 用在配置文件的dao层扫描配置处
 * @Create date:    
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MybatisRepository { 
    String value() default "";
}