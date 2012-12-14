package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.MappingScheme;

public interface MappingSchemeService {
	final String SERVICE_ID = "mappingSchemeService";
	
	void createScheme(MappingScheme scheme);
	void updateScheme(MappingScheme scheme);
	MappingScheme load(Long mappingSchemeId);
	void delete(Long schemeId);
	List<MappingScheme> getSchemeList();
	MappingScheme findByName(String name);
	boolean isNameExist(String name);
	MappingScheme getById(Long id);
	long countByAppSpecsId(Long appSpecsId);
	boolean isValidInputData(Long extSysId, Long appSpecId, Long mapSchemeId);
	
}
