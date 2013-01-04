package com.demo.utils;

import java.util.List;

import com.demo.ws.endpoints.AppField;
import com.demo.ws.endpoints.ApplicationDocument;
import com.demo.ws.endpoints.ScoringWebService;

public class ClientBean {
	
	public static void main(String[] args) {
		
		scoringDemo();
		
	}

	public static void scoringDemo() {
		
		BeanFactory beanFactory = BeanFactory.getInstnace();
		ScoringWebService scoringWebService = beanFactory.getScoringWebService();
		Long extSysId = new Long(1);
		Long appSpecId = new Long(1);
		Long mapScheId = new Long(1);
		ApplicationDocument appDoc = new ApplicationDocument();
		appDoc.setId(10);
		List<AppField> fields = appDoc.getFields();
		AppField field = new AppField();
		field.setName("Age");
		field.setValue("26");
		fields.add(field);
		field.setName("Gender");
		field.setValue("Male");
		fields.add(field);
		field.setName("Marital");
		field.setValue("Married");
		fields.add(field);
		double total = scoringWebService.calculateScoring(extSysId, appSpecId, mapScheId, appDoc);
		System.out.println(total);
		
	}
}