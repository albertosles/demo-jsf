package com.demo.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.jsf.dto.RequestDto;
import com.demo.ws.dto.AppField;
import com.demo.ws.dto.ApplicationDocument;
import com.demo.ws.endpoints.DemoWebService;
import com.demo.ws.endpoints.ScoringWebService;

public class ClientBean {
	
	public static void main(String[] args) {
		
		scoringDemo();
		
	}
	
	public static void webServiceDemo() {
		
		BeanFactory beanFactory = BeanFactory.getInstnace();
		DemoWebService demoWebService = beanFactory.getDemoWebServices();
		
		RequestDto requestDto = new RequestDto();
		requestDto.setEmail("tins.trna@yaoo.com");
		Date myDate = new Date();
		requestDto.setDate(myDate);
		requestDto.setTimestamp(new Timestamp(myDate.getTime()));
		demoWebService.processTransaction(requestDto);
	}
	
	public static void scoringDemo() {
		
		BeanFactory beanFactory = BeanFactory.getInstnace();
		ScoringWebService scoringWebService = beanFactory.getScoringWebService();
		Long extSysId = new Long(1);
		Long appSpecId = new Long(1);
		Long mapScheId = new Long(1);
		ApplicationDocument appDoc = new ApplicationDocument();
		appDoc.setId(10);
		List<AppField> fields = new ArrayList<AppField>();
		fields.add(new AppField("Age", "26"));
		fields.add(new AppField("Gender", "Male"));
		fields.add(new AppField("Marital", "Married"));
		appDoc.setFields(fields);
		double total = scoringWebService.calculateScoring(extSysId, appSpecId, mapScheId, appDoc);
		System.out.println(total);
		
	}
}