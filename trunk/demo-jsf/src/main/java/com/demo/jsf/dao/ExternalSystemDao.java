package com.demo.jsf.dao;

import com.demo.jsf.model.ExternalSystem;

public interface ExternalSystemDao extends AbstractDAO<ExternalSystem, Long> {
	ExternalSystem findByName(String name);
	boolean isNameExist(String name);
}
