package com.demo.jsf.dao;

import com.demo.jsf.model.DataType;

public interface DataTypeDao extends AbstractDAO<DataType, Long> {
	
	public DataType getById(Long id);
	boolean isNameExist(String name);
}
