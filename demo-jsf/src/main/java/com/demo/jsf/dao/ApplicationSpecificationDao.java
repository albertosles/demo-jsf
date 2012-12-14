package com.demo.jsf.dao;

import com.demo.jsf.model.ApplicationSpecification;

public interface ApplicationSpecificationDao extends AbstractDAO<ApplicationSpecification, Long> {
	ApplicationSpecification findByName(String name);
	boolean isNameExist(String name);
	long countByExtSysId(Long extSysId);
}
