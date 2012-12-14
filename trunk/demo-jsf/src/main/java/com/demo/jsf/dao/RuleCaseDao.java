package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.ScoringRuleCase;

public interface RuleCaseDao extends AbstractDAO<ScoringRuleCase, Long> {
	
	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, List<Long> excludeIds);
	long countByRuleId(Long ruleId);

}