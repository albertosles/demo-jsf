package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.dao.FactorDao;
import com.demo.jsf.model.ScoringFactor;

@Service(FactorService.SERVICE_ID)
@Transactional
public class FactorServiceImpl implements FactorService, Serializable {
	
	@Autowired
	private FactorDao factorDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createFactor(ScoringFactor factor) {
		factorDao.save(factor);
	}
	@Override
	public void delete(Long factorId) {
		factorDao.deleteById(factorId);
	}
	
	@Override
	public List<ScoringFactor> getFactorList() {
		return factorDao.getList();
	}
	
	@Override
	public List<ScoringFactor> getFactorList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		return factorDao.getList(first, pageSize, sortField, sortOrder, filters);
	}
	
	@Override
	public ScoringFactor getFactorById(Long id) {
		return factorDao.getById(id);
	}

	public boolean isNameExist(String name) {
		return factorDao.isNameExist(name);
	}
	public int count() {
		return factorDao.count();
	}

}