package com.yaoli.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.yaoli.quartzjob.SendMessageTask;

public class InitSendMessageTask implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof SendMessageTask){
			new Thread((SendMessageTask)bean).start();
		}
		return bean;
	}

}
