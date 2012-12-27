package com.demo.ws.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dto.RequestDto;
import com.demo.jsf.services.HelloWorldServiceImpl;

@Service("demoWebServiceEndpoint")
@WebService(serviceName = "ws/DemoWebService", targetNamespace = "http://endpoints.ws.demo.com/")
public class DemoWebServiceEndpoint implements DemoWebService {

	@Autowired
	private HelloWorldServiceImpl helloWorldService;
	
	@WebMethod
	public void sayHello() {
		helloWorldService.sayHello();
	}
	
	@WebMethod
	public String getEmailById(long id) {
		if (id == 1) {
			return "tin.tran@yahoo.com";
		} else if (id == 2) {
			return "trungtinspkt@gmail.com";
		}
		return "NA";
	}
	
	@WebMethod
	public void processTransaction(RequestDto requestDto) {
		System.out.println("My Email: " + requestDto.getEmail());
		System.out.println("My Date: " + requestDto.getDate());
		System.out.println("My Timestamp: " + requestDto.getTimestamp());
	}

}