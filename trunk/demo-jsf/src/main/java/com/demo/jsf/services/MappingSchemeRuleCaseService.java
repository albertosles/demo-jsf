package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.MappingSchemeRuleCase;

public interface MappingSchemeRuleCaseService {
	final String SERVICE_ID = "mappingRuleCaseService";
	
	void createMappingRuleCase(MappingSchemeRuleCase scheme);
	void updateMappingRuleCase(MappingSchemeRuleCase scheme);
	MappingSchemeRuleCase load(Long mappingSchemeId);
	void delete(Long schemeId);
	List<MappingSchemeRuleCase> getMappingRuleCaseList(Long mappingRuleId);
	List<Long> getScoringRuleCaseIds();
	long countByMappingRuleId(Long mappingRuleId);
	
}
