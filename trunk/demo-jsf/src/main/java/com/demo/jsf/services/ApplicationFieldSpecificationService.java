package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ApplicationFieldSpecification;

public interface ApplicationFieldSpecificationService {
	
	final String SERVICE_ID = "appFieldSpecService";
	
	void create(ApplicationFieldSpecification appFieldSpecs);
	void update(ApplicationFieldSpecification appFieldSpecs);
	ApplicationFieldSpecification load(Long appFieldSpecsId);
	void delete(Long appFieldSpecsId);
	List<ApplicationFieldSpecification> getAppFieldSpecsList();
	ApplicationFieldSpecification findByName(String name);
	boolean isNameExist(String name);
	boolean isNameExist(String name, Long appSpecId);
	List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId);
	long countByDataTypeId(Long dataTypeId);
	long countByAppSpecId(Long appSpecId);
	
}
