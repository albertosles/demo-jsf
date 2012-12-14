package com.demo.utils;

import java.sql.Timestamp;
import java.util.Date;

import com.demo.jsf.dto.RequestDto;
import com.demo.jsf.services.ws.endpoints.DemoWebService;

public class ClientBean {
	
	public static void main(String[] args) {
		
		BeanFactory beanFactory = BeanFactory.getInstnace();
		DemoWebService demoWebService = beanFactory.getDemoWebServices();
		
		RequestDto requestDto = new RequestDto();
		requestDto.setEmail("tins.trna@yaoo.com");
		Date myDate = new Date();
		requestDto.setDate(myDate);
		requestDto.setTimestamp(new Timestamp(myDate.getTime()));
		demoWebService.processTransaction(requestDto);
		
		
//		DemoWebService01 demoWebService = beanFactory.getDemoWebServices01();
//		demoWebService.sayHello();
//		String email = demoWebService.getEmailById(1);
//		System.out.println(email);
	}
}