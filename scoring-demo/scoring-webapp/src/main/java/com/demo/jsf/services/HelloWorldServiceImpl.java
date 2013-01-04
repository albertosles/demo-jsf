package com.demo.jsf.services;

import org.springframework.stereotype.Service;

@Service(HelloWorldService.SERVICE_ID)
public class HelloWorldServiceImpl implements HelloWorldService {

	public void sayHello() {
		System.out.println("Hello, Tin Tran");
	}

}