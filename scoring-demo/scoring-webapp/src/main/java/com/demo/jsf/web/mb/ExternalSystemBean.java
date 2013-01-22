package com.demo.jsf.web.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.demo.jsf.dto.LazyExtSystemDataModel;
import com.demo.jsf.model.ExternalSystem;
import com.demo.jsf.services.ApplicationSpecificationService;
import com.demo.jsf.services.ExternalSystemService;

public class ExternalSystemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ExternalSystemService extSystemService;
	
	private ApplicationSpecificationService appSpecsService;
	
	private ExternalSystem extSystem;
	
	private ExternalSystem selectedExtSys;
	
	private LazyExtSystemDataModel extSystemDataModel;
	
	@PostConstruct
	public void init() {
		extSystem = new ExternalSystem();
		extSystemDataModel = new LazyExtSystemDataModel(extSystemService);
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
	
	public void onEdit(RowEditEvent event) {
		
		extSystemService.updateExtSystem((ExternalSystem)event.getObject());
		FacesMessage msg = new FacesMessage("Successfull", "The External System has been updated successfully.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
    	
        FacesMessage msg = new FacesMessage("ExternalSystem Cancelled", ((ExternalSystem) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public LazyExtSystemDataModel getExtSystemDataModel() {
		return extSystemDataModel;
	}

}