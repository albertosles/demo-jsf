package com.demo.jsf.dao;

import java.util.List;

import com.demo.jsf.model.ApplicationFieldSpecification;

public interface ApplicationFieldSpecificationDao extends AbstractDAO<ApplicationFieldSpecification, Long> {
	ApplicationFieldSpecification findByName(String name);
	boolean isNameExist(String name);
	boolean isNameExist(String name, Long appSpecId);
	List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId);
	List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId, int first, int pageSize);
	int countByDataTypeId(Long dataTypeId);
	int countByAppSpecId(Long appSpecId);
}
