package com.demo.jsf.services;

import java.util.List;
import java.util.Map;

import com.demo.jsf.model.ExternalSystem;

public interface ExternalSystemService {
	
	final String SERVICE_ID = "extSystemService";
	
	void createExtSystem(ExternalSystem extSystem);
	
	void updateExtSystem(ExternalSystem extSystem);
	
	ExternalSystem load(Long extSystemId);
	
	void delete(Long extSystemId);
	
	List<ExternalSystem> getExtSystemList();
	
	List<ExternalSystem> getExtSystemList(int first, int pageSize);
	
	List<ExternalSystem> getExtSystemList(int first, int pageSize, String sortField,
							String sortOrder, Map<String, String> filters);
	
	ExternalSystem findByName(String name);
	
	boolean isNameExist(String name);
	int count();
	
}
