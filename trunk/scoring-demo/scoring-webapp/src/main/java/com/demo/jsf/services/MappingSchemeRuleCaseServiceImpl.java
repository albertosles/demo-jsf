package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.MappingSchemeRuleCaseDao;
import com.demo.jsf.model.MappingSchemeRuleCase;

@Service(MappingSchemeRuleCaseService.SERVICE_ID)
public class MappingSchemeRuleCaseServiceImpl implements MappingSchemeRuleCaseService, Serializable {
	
	@Autowired
	private MappingSchemeRuleCaseDao mappingRuleCaseDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createMappingRuleCase(MappingSchemeRuleCase scheme) {
		mappingRuleCaseDao.save(scheme);
	}
	
	@Override
	public void updateMappingRuleCase(MappingSchemeRuleCase scheme) {
		mappingRuleCaseDao.update(scheme);
	}
	
	@Override
	public void delete(Long schemeId) {
		mappingRuleCaseDao.deleteById(schemeId);
		
	}
	
	public MappingSchemeRuleCase load(Long mappingSchemeId) {
		return mappingRuleCaseDao.load(mappingSchemeId);
	}

	@Override
	public List<MappingSchemeRuleCase> getMappingRuleCaseList(Long mappingRuleId) {
		return mappingRuleCaseDao.getMappingRuleCaseList(mappingRuleId);
	}
	
	@Override
	public List<Long> getScoringRuleCaseIds() {
		return mappingRuleCaseDao.getScoringRuleCaseIds();
	}
	
	@Override
	public long countByMappingRuleId(Long mappingRuleId) {
		return mappingRuleCaseDao.countByMappingRuleId(mappingRuleId);
	}
	
}