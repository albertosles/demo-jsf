package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.dao.RuleCaseDao;
import com.demo.jsf.model.ScoringRuleCase;

@Transactional
@Service(RuleCaseService.SERVICE_ID)
public class RuleCaseServiceImpl implements RuleCaseService, Serializable {
	
	@Autowired
	private RuleCaseDao ruleCaseDao;
	
	private static final long serialVersionUID = 1L;
	
	public ScoringRuleCase getById(Long ruleCaseId) {
		return ruleCaseDao.load(ruleCaseId);
	}

	@Override
	public void createRuleCase(ScoringRuleCase rule) {
		ruleCaseDao.save(rule);
	}
	
	@Override
	public void updateRuleCase(ScoringRuleCase rule) {
		ruleCaseDao.update(rule);
	}

	public void delete(Long ruleCaseId) {
		ruleCaseDao.deleteById(ruleCaseId);		
	}
	
	@Override
	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, List<Long> excludeIds) {
		return ruleCaseDao.getRuleCaseList(ruleId, excludeIds);
	}
	@Override
	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, int first, int pageSize) {
		return ruleCaseDao.getRuleCaseList(ruleId, first, pageSize);
	}
	@Override
	public List<ScoringRuleCase> getRuleCaseList() {
		return ruleCaseDao.getList();
	}
	
	@Override
	public int countByRuleId(Long ruleId) {
		return ruleCaseDao.countByRuleId(ruleId);
	}
	


}