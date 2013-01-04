package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.MappingSchemeDao;
import com.demo.jsf.model.MappingScheme;

@Service(MappingSchemeService.SERVICE_ID)
public class MappingSchemeServiceImpl implements MappingSchemeService, Serializable {
	
	@Autowired
	private MappingSchemeDao mappingSchemeDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createScheme(MappingScheme scheme) {
		mappingSchemeDao.save(scheme);
	}
	
	@Override
	public void updateScheme(MappingScheme scheme) {
		mappingSchemeDao.update(scheme);
	}
	
	@Override
	public void delete(Long schemeId) {
		mappingSchemeDao.deleteById(schemeId);
		
	}
	
	public MappingScheme load(Long mappingSchemeId) {
		return mappingSchemeDao.load(mappingSchemeId);
	}

	@Override
	public List<MappingScheme> getSchemeList() {
		return mappingSchemeDao.getList();
	}
	
	@Override
	public MappingScheme findByName(String name) {
		return mappingSchemeDao.findByName(name);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return mappingSchemeDao.isNameExist(name);
	}

	@Override
	public MappingScheme getById(Long id) {
		return mappingSchemeDao.load(id);
		
	}
	
	@Override
	public long countByAppSpecsId(Long appSpecsId) { 
		return mappingSchemeDao.countByAppSpecsId(appSpecsId);
	}
	
	@Override
	public boolean isValidInputData(Long extSysId, Long appSpecId, Long mapSchemeId) {
		return mappingSchemeDao.isValidInputData(extSysId, appSpecId, mapSchemeId);
	}
}