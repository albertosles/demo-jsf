package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.ApplicationSpecificationDao;
import com.demo.jsf.model.ApplicationSpecification;

@Service(ApplicationSpecificationService.SERVICE_ID)
public class ApplicationSpecificationServiceImpl implements ApplicationSpecificationService, Serializable {
	
	@Autowired
	private ApplicationSpecificationDao appSpecsDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createAppSpecs(ApplicationSpecification appSpecs) {
		appSpecsDao.save(appSpecs);
	}
	
	@Override
	public void updateAppSpecs(ApplicationSpecification appSpecs) {
		appSpecsDao.update(appSpecs);
	}
	
	@Override
	public void delete(Long extSystemId) {
		appSpecsDao.deleteById(extSystemId);
		
	}
	
	public ApplicationSpecification load(Long extSystemId) {
		return appSpecsDao.load(extSystemId);
	}

	@Override
	public List<ApplicationSpecification> getAppSpecsList() {
		return appSpecsDao.getList();
	}
	
	@Override
	public ApplicationSpecification findByName(String name) {
		return appSpecsDao.findByName(name);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return appSpecsDao.isNameExist(name);
	}
	
	@Override
	public long countByExtSysId(Long extSysId) {
		return appSpecsDao.countByExtSysId(extSysId);
	}

}