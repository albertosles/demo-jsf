package com.demo.jsf.dao;

import com.demo.jsf.model.ScoringFactor;

public interface FactorDao extends AbstractDAO<ScoringFactor, Long> {
	
	public ScoringFactor getById(Long id);
	boolean isNameExist(String name);
}
