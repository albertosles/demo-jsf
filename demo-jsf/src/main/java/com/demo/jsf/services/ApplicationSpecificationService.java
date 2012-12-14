package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ApplicationSpecification;

public interface ApplicationSpecificationService {
	
	final String SERVICE_ID = "appSpecsService";
	
	void createAppSpecs(ApplicationSpecification appSpecs);
	
	void updateAppSpecs(ApplicationSpecification appSpecs);
	
	ApplicationSpecification load(Long appSpecsId);
	
	void delete(Long appSpecsId);
	
	List<ApplicationSpecification> getAppSpecsList();
	
	ApplicationSpecification findByName(String name);
	
	boolean isNameExist(String name);
	
	long countByExtSysId(Long extSysId);
	
}
