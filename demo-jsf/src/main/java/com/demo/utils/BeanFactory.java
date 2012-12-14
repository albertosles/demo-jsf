package com.demo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.jsf.services.ws.endpoints.DemoWebService;

public class BeanFactory {
	private ApplicationContext context;

	private static BeanFactory instnace;

	private BeanFactory() {
		context = new ClassPathXmlApplicationContext(new String[] { "spring-client.xml" });
	}

	public static BeanFactory getInstnace() {
		if (instnace == null) {
			instnace = new BeanFactory();
		}
		return instnace;
	}

	public <T> T getBean(String beanName, Class<T> cl) {
		return context.getBean(beanName, cl);
	}

	public DemoWebService getDemoWebServices() {
		return getBean("demoWebService", DemoWebService.class);
	}
	
	public DemoWebService getDemoWebServices01() {
		return getBean("demoWebService01", DemoWebService.class);
	}
	

}
