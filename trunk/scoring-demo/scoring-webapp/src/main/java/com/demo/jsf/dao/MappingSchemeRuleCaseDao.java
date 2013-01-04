package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.MappingSchemeRuleCase;

public interface MappingSchemeRuleCaseDao extends AbstractDAO<MappingSchemeRuleCase, Long> {
	MappingSchemeRuleCase findByName(String name);
	boolean isNameExist(String name);
	List<MappingSchemeRuleCase> getMappingRuleCaseList(Long mappingRuleId);
	List<Long> getScoringRuleCaseIds();
	long countByMappingRuleId(Long mappingRuleId);
}
