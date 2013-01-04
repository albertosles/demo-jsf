package com.demo.jsf.dao;

import com.demo.jsf.model.MappingScheme;

public interface MappingSchemeDao extends AbstractDAO<MappingScheme, Long> {
	MappingScheme findByName(String name);
	boolean isNameExist(String name);
	long countByAppSpecsId(Long apSpecsId);
	boolean isValidInputData(Long extSysId, Long appSpecId, Long mapSchemeId);
	
}
