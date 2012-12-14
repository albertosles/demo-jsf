package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.ScoringRule;

public interface RuleDao extends AbstractDAO<ScoringRule, Long> {
	
	
	List<ScoringRule> getList(Long schemeId, List<Long> excludeIds);
	
	List<ScoringRule> getRuleList();
	
	long countBySchemeId(Long schemeId);
	
	long countByFactorId(Long schemeId);
	
}