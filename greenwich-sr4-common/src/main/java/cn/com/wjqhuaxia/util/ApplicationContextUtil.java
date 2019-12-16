package cn.com.wjqhuaxia.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring应用上下文：<br>
 * 	applicationContext
 * @author wjqhuaxia
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	/**
	 * 获取bean 方法1
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}

	/**
	 * 获取bean 方法2
	 * @param c
	 * @return
	 */
	public static <T> T getBean(Class<T> c) {
		return (T) applicationContext.getBean(c);
	}

}
