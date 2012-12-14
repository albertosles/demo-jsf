package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.MappingSchemeRule;

public interface MappingSchemeRuleService {
	final String SERVICE_ID = "mappingRuleService";
	
	void create(MappingSchemeRule scheme);
	
	void update(MappingSchemeRule scheme);
	
	MappingSchemeRule load(Long mappingSchemeId);
	
	void delete(Long schemeId);
	
	List<MappingSchemeRule> getMappingRuleList(Long mappingSchemeId);
	
	MappingSchemeRule findByName(String name);
	
	boolean isNameExist(String name);
	
	long countBySchemeId(Long mapSchemeId);
	List<Long> getScoringRuleIds();
	
}
