package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.ScoringRuleCase;

public interface RuleCaseDao extends AbstractDAO<ScoringRuleCase, Long> {
	
	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, List<Long> excludeIds);
	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, int first, int pageSize);
	int countByRuleId(Long ruleId);

}