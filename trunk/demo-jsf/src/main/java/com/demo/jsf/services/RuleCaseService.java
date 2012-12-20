package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ScoringRuleCase;

public interface RuleCaseService {
	final String SERVICE_ID = "ruleCaseService";

	ScoringRuleCase getById(Long ruleCaseId);
	void createRuleCase(ScoringRuleCase ruleCase);
	void updateRuleCase(ScoringRuleCase ruleCase);
	void delete(Long schemeId);
	List<ScoringRuleCase> getRuleCaseList(Long ruleId, List<Long> excludeIds);
	List<ScoringRuleCase> getRuleCaseList(Long ruleId, int first, int pageSize);
	List<ScoringRuleCase> getRuleCaseList();
	int countByRuleId(Long ruleId);
}
