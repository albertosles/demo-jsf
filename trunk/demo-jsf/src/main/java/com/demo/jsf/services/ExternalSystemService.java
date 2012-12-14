package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ExternalSystem;

public interface ExternalSystemService {
	
	final String SERVICE_ID = "extSystemService";
	
	void createExtSystem(ExternalSystem extSystem);
	
	void updateExtSystem(ExternalSystem extSystem);
	
	ExternalSystem load(Long extSystemId);
	
	void delete(Long extSystemId);
	
	List<ExternalSystem> getExtSystemList();
	
	ExternalSystem findByName(String name);
	
	boolean isNameExist(String name);
	
}
