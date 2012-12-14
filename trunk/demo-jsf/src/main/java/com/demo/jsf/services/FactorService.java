package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.ScoringFactor;

public interface FactorService {
	final String SERVICE_ID = "factorService";
	
	void createFactor(ScoringFactor factor);
	void delete(Long factorId);
	ScoringFactor getFactorById(Long id);
	List<ScoringFactor> getFactorList();
	boolean isNameExist(String name);
	
}
