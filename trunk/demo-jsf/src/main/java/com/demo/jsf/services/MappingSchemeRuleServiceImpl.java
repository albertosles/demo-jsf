package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.MappingSchemeRuleDao;
import com.demo.jsf.model.MappingSchemeRule;

@Service(MappingSchemeRuleService.SERVICE_ID)
public class MappingSchemeRuleServiceImpl implements MappingSchemeRuleService, Serializable {
	
	@Autowired
	private MappingSchemeRuleDao mappingSchemeRuleDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void create(MappingSchemeRule scheme) {
		mappingSchemeRuleDao.save(scheme);
	}
	
	@Override
	public void update(MappingSchemeRule scheme) {
		mappingSchemeRuleDao.update(scheme);
	}
	
	@Override
	public void delete(Long schemeId) {
		mappingSchemeRuleDao.deleteById(schemeId);
		
	}
	
	public MappingSchemeRule load(Long mappingSchemeId) {
		return mappingSchemeRuleDao.load(mappingSchemeId);
	}

	@Override
	public List<MappingSchemeRule> getMappingRuleList(Long mappingSchemeId) {
		return mappingSchemeRuleDao.getMappingRuleList(mappingSchemeId);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return mappingSchemeRuleDao.isNameExist(name);
	}

	@Override
	public MappingSchemeRule findByName(String name) {
		return mappingSchemeRuleDao.findByName(name);
	}
	
	@Override
	public long countBySchemeId(Long mapSchemeId) { 
		return mappingSchemeRuleDao.countBySchemeId(mapSchemeId);
	}

	@Override
	public List<Long> getScoringRuleIds() {
		return mappingSchemeRuleDao.getScoringRuleIds();
	}
}