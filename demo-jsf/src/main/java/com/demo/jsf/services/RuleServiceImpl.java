package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.RuleDao;
import com.demo.jsf.model.ScoringRule;

@Service(RuleService.SERVICE_ID)
public class RuleServiceImpl implements RuleService, Serializable {
	
	@Autowired
	private RuleDao ruleDao;
	
	private static final long serialVersionUID = 1L;
	
	public ScoringRule getById(Long ruleId) {
		return ruleDao.load(ruleId);
	}

	@Override
	public void createRule(ScoringRule rule) {
		ruleDao.save(rule);
	}
	
	@Override
	public void updateRule(ScoringRule rule) {
		ruleDao.update(rule);
	}

	public void delete(Long ruleId) {
		ruleDao.deleteById(ruleId);		
	}
	@Override
	public List<ScoringRule> getRuleList() {
		return ruleDao.getRuleList();
	}

	@Override
	public List<ScoringRule> getRuleList(Long schemeId) {
		return ruleDao.getList(schemeId, null);
	}
	
	@Override
	public List<ScoringRule> getRuleList(Long schemeId, int first, int pageSize) {
		return ruleDao.getList(schemeId, first, pageSize);
	}
	
	@Override
	public List<ScoringRule> getRuleList(Long schemeId, List<Long> excludeIds) {
		return ruleDao.getList(schemeId, excludeIds);
	}
	
	public int countBySchemeId(Long schemeId) { 
		return ruleDao.countBySchemeId(schemeId);
	}
	
	public int countByFactorId(Long factorId) { 
		return ruleDao.countByFactorId(factorId);
	}


}