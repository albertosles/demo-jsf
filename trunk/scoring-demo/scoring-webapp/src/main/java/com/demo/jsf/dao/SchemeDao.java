package com.demo.jsf.dao;

import com.demo.jsf.model.ScoringScheme;

public interface SchemeDao extends AbstractDAO<ScoringScheme, Long> {
	ScoringScheme findByName(String name);
	boolean isNameExist(String name);
}
