package com.yaoli.common.obsoleted;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SysSpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SysSpringContextUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		Object object = applicationContext.getBean(name);
		return object;
	}

	public static SqlSessionFactory getSessionFactory(){
		DefaultSqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = (DefaultSqlSessionFactory) SysSpringContextUtil.getBean("sqlSessionFactory");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("没有找到相应的SqlSessionFactoryBean配置，请检查该静态方法下的bean参数是否与xml配置一致");
		}
		return sqlSessionFactory;
	}

}
