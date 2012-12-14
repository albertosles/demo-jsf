package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.DataType;

public interface DataTypeService {
	final String SERVICE_ID = "dataTypeService";
	
	void createFactor(DataType factor);
	void delete(Long factorId);
	DataType load(Long id);
	List<DataType> getDataTypeList();
	boolean isNameExist(String name);
	
}
