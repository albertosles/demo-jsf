package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.dao.SchemeDao;
import com.demo.jsf.model.ScoringScheme;

@Service(SchemeService.SERVICE_ID)
@Transactional
public class SchemeServiceImpl implements SchemeService, Serializable {
	
	@Autowired
	private SchemeDao schemeDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createScheme(ScoringScheme scheme) {
		schemeDao.save(scheme);
	}
	
	@Override
	public void updateScheme(ScoringScheme scheme) {
		schemeDao.update(scheme);
	}
	
	@Override
	public void delete(Long schemeId) {
		schemeDao.deleteById(schemeId);
		
	}

	@Override
	public List<ScoringScheme> getSchemeList() {
		return schemeDao.getList();
	}
	
	@Override
	public List<ScoringScheme> getSchemeList(int first, int pageSize) {
		return schemeDao.getList(first, pageSize);
	}
	
	@Override
	public ScoringScheme findByName(String name) {
		return schemeDao.findByName(name);
	}
	
	@Override
	public ScoringScheme load(Long id) {
		return schemeDao.load(id);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return schemeDao.isNameExist(name);
	}

	@Override
	public int count() {
		return schemeDao.count();
	}

}