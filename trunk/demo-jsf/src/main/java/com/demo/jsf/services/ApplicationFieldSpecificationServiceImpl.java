package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.dao.ApplicationFieldSpecificationDao;
import com.demo.jsf.model.ApplicationFieldSpecification;

@Service(ApplicationFieldSpecificationService.SERVICE_ID)
@Transactional
public class ApplicationFieldSpecificationServiceImpl implements ApplicationFieldSpecificationService, Serializable {
	
	@Autowired
	private ApplicationFieldSpecificationDao appFieldSpecsDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void create(ApplicationFieldSpecification appFieldSpecs) {
		appFieldSpecsDao.save(appFieldSpecs);
	}
	
	@Override
	public void update(ApplicationFieldSpecification appFieldSpecs) {
		appFieldSpecsDao.update(appFieldSpecs);
	}
	
	@Override
	public void delete(Long extSystemId) {
		appFieldSpecsDao.deleteById(extSystemId);
		
	}
	
	public ApplicationFieldSpecification load(Long extSystemId) {
		return appFieldSpecsDao.load(extSystemId);
	}

	@Override
	public List<ApplicationFieldSpecification> getAppFieldSpecsList() {
		return appFieldSpecsDao.getList();
	}
	
	@Override
	public ApplicationFieldSpecification findByName(String name) {
		return appFieldSpecsDao.findByName(name);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return appFieldSpecsDao.isNameExist(name);
	}
	
	@Override
	public boolean isNameExist(String name, Long appSpecId) {
		return appFieldSpecsDao.isNameExist(name, appSpecId);
	}
	
	@Override
	public List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId) {
		return appFieldSpecsDao.getAppFieldSpecList(appSpecId);
	}
	
	@Override
	public List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId, int first, int pageSize) {
		return appFieldSpecsDao.getAppFieldSpecList(appSpecId, first, pageSize);
	}
	
	@Override
	public int countByDataTypeId(Long dataTypeId) {
		return appFieldSpecsDao.countByDataTypeId(dataTypeId);
	}
	
	@Override
	public int countByAppSpecId(Long appSpecId) {
		return appFieldSpecsDao.countByAppSpecId(appSpecId);
	}
}