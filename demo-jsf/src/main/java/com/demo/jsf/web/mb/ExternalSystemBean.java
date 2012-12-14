package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.demo.jsf.model.ExternalSystem;
import com.demo.jsf.services.ApplicationSpecificationService;
import com.demo.jsf.services.ExternalSystemService;

public class ExternalSystemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ExternalSystemService extSystemService;
	
	private ApplicationSpecificationService appSpecsService;
	
	private ExternalSystem extSystem;
	
	private ExternalSystem selectedExtSys;
	
	@PostConstruct
	public void init() {
		extSystem = new ExternalSystem();
	}
	
	public String saveExtSystem() {
		
		if(extSystem.getId() != null && extSystem.getId() > 0) {
			extSystemService.updateExtSystem(extSystem);
			FacesMessage msg = new FacesMessage("Successfull", "The External System has been updated successfully.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			if(extSystemService.isNameExist(extSystem.getName())) {
				FacesMessage msg = new FacesMessage("Error", "External System Name is duplicate.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				extSystemService.createExtSystem(extSystem);
				extSystem = new ExternalSystem();
				FacesMessage msg = new FacesMessage("Successfull", "The External System has been created successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		return null;
	}
	
	public void deleteExtSystem() {

		if(selectedExtSys == null) return;
		
		long appSpecsCount = appSpecsService.countByExtSysId(selectedExtSys.getId());
		if(appSpecsCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The External System is not empty, Please remove all Application Specification first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			extSystemService.delete(selectedExtSys.getId());
			selectedExtSys = null;
			FacesMessage msg = new FacesMessage("Successfull", "The External System has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void showCreateExtSystem() {
		extSystem = new ExternalSystem();
	}
	
	public void showEditExtSystem() {
		extSystem = selectedExtSys;
	}
	
	public List<ExternalSystem> getExtSystemList() {
		return extSystemService.getExtSystemList();
	}
	
	/////------------/////
	public ExternalSystemService getExtSystemService() {
		return extSystemService;
	}

	public void setExtSystemService(ExternalSystemService extSystemService) {
		this.extSystemService = extSystemService;
	}

	public ApplicationSpecificationService getAppSpecsService() {
		return appSpecsService;
	}

	public void setAppSpecsService(ApplicationSpecificationService appSpecsService) {
		this.appSpecsService = appSpecsService;
	}

	public ExternalSystem getExternalSystem() {
		return extSystem;
	}

	public void setExternalSystem(ExternalSystem externalSystem) {
		this.extSystem = externalSystem;
	}

	public ExternalSystem getSelectedExtSys() {
		return selectedExtSys;
	}

	public void setSelectedExtSys(ExternalSystem selectedExtSys) {
		this.selectedExtSys = selectedExtSys;
	}

}
