package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ScoringRule;

public interface RuleService {
	final String SERVICE_ID = "ruleService";
	
	ScoringRule getById(Long ruleId);
	void createRule(ScoringRule rule);
	void updateRule(ScoringRule rule);
	void delete(Long schemeId);
	List<ScoringRule> getRuleList();
	List<ScoringRule> getRuleList(Long schemeId);
	List<ScoringRule> getRuleList(Long schemeId, int first, int pageSize);
	List<ScoringRule> getRuleList(Long schemeId,List<Long> excludeIds);
	int countBySchemeId(Long schemeId);
	int countByFactorId(Long factorId);
}
