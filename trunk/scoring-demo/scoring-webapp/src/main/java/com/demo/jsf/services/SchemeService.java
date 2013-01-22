package com.demo.jsf.services;

import java.util.List;
import java.util.Map;

import com.demo.jsf.model.ScoringScheme;

public interface SchemeService {
	final String SERVICE_ID = "schemeService";
	
	void createScheme(ScoringScheme scheme);
	void updateScheme(ScoringScheme scheme);
	void delete(Long schemeId);
	List<ScoringScheme> getSchemeList();
	List<ScoringScheme> getSchemeList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);
	int count();
	ScoringScheme findByName(String name);
	ScoringScheme load(Long id);
	boolean isNameExist(String name);
	
}
