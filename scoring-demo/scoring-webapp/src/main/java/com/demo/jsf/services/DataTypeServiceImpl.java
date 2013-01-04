package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.DataTypeDao;
import com.demo.jsf.model.DataType;

@Service(DataTypeService.SERVICE_ID)
public class DataTypeServiceImpl implements DataTypeService, Serializable {
	
	@Autowired
	private DataTypeDao dataTypeDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createFactor(DataType factor) {
		dataTypeDao.save(factor);
	}
	@Override
	public void delete(Long factorId) {
		dataTypeDao.deleteById(factorId);
	}
	
	@Override
	public List<DataType> getDataTypeList() {
		return dataTypeDao.getList();
	}

	@Override
	public DataType load(Long id) {
		return dataTypeDao.getById(id);
	}

	public boolean isNameExist(String name) {
		return dataTypeDao.isNameExist(name);
	}

}