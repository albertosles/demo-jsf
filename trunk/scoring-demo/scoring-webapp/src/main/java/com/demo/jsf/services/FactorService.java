package com.demo.jsf.services;

import java.util.List;
import java.util.Map;

import com.demo.jsf.model.ScoringFactor;

public interface FactorService {
	final String SERVICE_ID = "factorService";
	
	void createFactor(ScoringFactor factor);
	void delete(Long factorId);
	ScoringFactor getFactorById(Long id);
	List<ScoringFactor> getFactorList();
	List<ScoringFactor> getFactorList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);
	boolean isNameExist(String name);
	int count();
}
