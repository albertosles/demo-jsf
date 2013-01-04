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
	List<ApplicationSpecification> getAppSpecsList(int first, int pageSize);
	
	ApplicationSpecification findByName(String name);
	
	boolean isNameExist(String name);
	int count();
	long countByExtSysId(Long extSysId);
	
}
