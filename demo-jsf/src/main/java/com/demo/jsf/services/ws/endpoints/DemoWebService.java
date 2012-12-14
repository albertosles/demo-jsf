package com.demo.jsf.services.ws.endpoints;

import javax.jws.WebService;

import com.demo.jsf.dto.RequestDto;

@WebService
public interface DemoWebService {
	void sayHello();
	String getEmailById(long id);
	void processTransaction(RequestDto requestDto);
}
