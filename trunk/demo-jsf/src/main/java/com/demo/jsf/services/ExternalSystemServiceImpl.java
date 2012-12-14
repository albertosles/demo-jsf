package com.demo.jsf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.ExternalSystemDao;
import com.demo.jsf.model.ExternalSystem;

@Service(ExternalSystemService.SERVICE_ID)
public class ExternalSystemServiceImpl implements ExternalSystemService, Serializable {
	
	@Autowired
	private ExternalSystemDao externalSystemDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createExtSystem(ExternalSystem extSystem) {
		externalSystemDao.save(extSystem);
	}
	
	@Override
	public void updateExtSystem(ExternalSystem extSystem) {
		externalSystemDao.update(extSystem);
	}
	
	@Override
	public void delete(Long extSystemId) {
		externalSystemDao.deleteById(extSystemId);
		
	}
	
	public ExternalSystem load(Long extSystemId) {
		return externalSystemDao.load(extSystemId);
	}

	@Override
	public List<ExternalSystem> getExtSystemList() {
		return externalSystemDao.getList();
	}
	
	@Override
	public ExternalSystem findByName(String name) {
		return externalSystemDao.findByName(name);
	}
	
	@Override
	public boolean isNameExist(String name) {
		return externalSystemDao.isNameExist(name);
	}

}