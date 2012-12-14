package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.MappingSchemeRule;

public interface MappingSchemeRuleDao extends AbstractDAO<MappingSchemeRule, Long> {
	MappingSchemeRule findByName(String name);
	boolean isNameExist(String name);
	List<MappingSchemeRule> getMappingRuleList(Long mappingSchemeId);
	long countBySchemeId(Long mapSchemeId);
	List<Long> getScoringRuleIds();
}
