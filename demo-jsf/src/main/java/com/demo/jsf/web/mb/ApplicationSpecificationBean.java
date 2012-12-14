package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.demo.jsf.model.ApplicationFieldSpecification;
import com.demo.jsf.model.ApplicationSpecification;
import com.demo.jsf.model.DataType;
import com.demo.jsf.model.ExternalSystem;
import com.demo.jsf.services.ApplicationFieldSpecificationService;
import com.demo.jsf.services.ApplicationSpecificationService;
import com.demo.jsf.services.DataTypeService;
import com.demo.jsf.services.ExternalSystemService;
import com.demo.jsf.services.MappingSchemeService;
import com.demo.jsf.services.RuleService;

public class ApplicationSpecificationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ApplicationSpecificationService appSpecsService;
	
	private ApplicationFieldSpecificationService appFieldSpecService;
	
	private DataTypeService dataTypeService;
	
	private ExternalSystemService extSystemService;
	
	private RuleService ruleService;
	
	private MappingSchemeService mappingSchemeService;
	
	private ApplicationSpecification appSpecs;
	
	private ApplicationSpecification selectedAppSpecs;
	
	private ApplicationFieldSpecification appFieldSpecs;
	
	private ApplicationFieldSpecification selectedField;
	
	private Long extSystemId;
	
	private Long dataTypeId;
	
	@PostConstruct
	public void init() {
		appSpecs = new ApplicationSpecification();
		appFieldSpecs = new ApplicationFieldSpecification();
	}
	
	public String saveAppSpecs() {
		
		if(appSpecs != null) {
			ExternalSystem externalSystem = extSystemService.load(extSystemId);
			appSpecs.setExternalSystem(externalSystem);
			if(appSpecs.getId() != null && appSpecs.getId() > 0) {
				appSpecsService.updateAppSpecs(appSpecs);
				FacesMessage msg = new FacesMessage("Successfull", "The Application Specification has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				if(appSpecsService.isNameExist(appSpecs.getName())) {
					FacesMessage msg = new FacesMessage("Error", "Application Specification Name is duplicate.");
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					appSpecsService.createAppSpecs(appSpecs);
					appSpecs = new ApplicationSpecification();
					extSystemId = null;
					FacesMessage msg = new FacesMessage("Successfull", "The Application Specification has been created successfully.");
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
		return null;
	}
	
	public String saveAppFieldSpecs() {
		
		if(appFieldSpecs != null && selectedAppSpecs != null) {
			
			DataType dataType = dataTypeService.load(dataTypeId);
			appFieldSpecs.setDataType(dataType);
			appFieldSpecs.setAppSpec(selectedAppSpecs);
			if(appFieldSpecs.getId() != null && appFieldSpecs.getId() > 0) {
				appFieldSpecService.update(appFieldSpecs);
				FacesMessage msg = new FacesMessage("Successfull", "The Application Field Specification has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				if(appFieldSpecService.isNameExist(appFieldSpecs.getName(), selectedAppSpecs.getId())) {
					FacesMessage msg = new FacesMessage("Error", "Application Field Specification Name is duplicate.");
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					appFieldSpecService.create(appFieldSpecs);
					appFieldSpecs = new ApplicationFieldSpecification();
					dataTypeId = null;
					FacesMessage msg = new FacesMessage("Successfull", "The Application Field Specification has been created successfully.");
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
		
		return null;
	}
	
	public void deleteAppSpecs() {

		if(selectedAppSpecs == null) return;
		
		long mapSchemeCount = mappingSchemeService.countByAppSpecsId(selectedAppSpecs.getId());
		if(mapSchemeCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Application Specification is not empty, Please remove all Mapping Scheme first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return;
		}
		long fieldCount = appFieldSpecService.countByAppSpecId(selectedAppSpecs.getId());
		if(fieldCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Application Field Specification is not empty, Please remove all Application Field Specification first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return;
		}
		appSpecsService.delete(selectedAppSpecs.getId());
		selectedAppSpecs = null;
		FacesMessage msg = new FacesMessage("Successfull", "The Application Specification has been deleted.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void deleteAppFieldSpecs() {
		if(selectedField == null) return;
		appFieldSpecService.delete(selectedField.getId());
		selectedField = null;
		FacesMessage msg = new FacesMessage("Successfull", "The Application Field Specification has been deleted.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void showCreateAppSpecs() {
		appSpecs = new ApplicationSpecification();
		extSystemId = null;
		
	}

	public void showEditAppSpecs() {
		appSpecs = selectedAppSpecs;
		extSystemId = appSpecs.getExternalSystem().getId();
	}
	
	public void showCreateField() {
		appFieldSpecs = new ApplicationFieldSpecification();
		dataTypeId = null;
	}
	
	public void showEditField() {
		if(selectedField != null) {
			appFieldSpecs = selectedField;
			dataTypeId = selectedField.getDataType().getId();
		}
	}
	public void showListOfFields() {
		
		
	}
	
	public List<ApplicationSpecification> getAppSpecsList() {
		return appSpecsService.getAppSpecsList();
	}
	
	public List<ExternalSystem> getExternalSystemList() {
		return extSystemService.getExtSystemList();
	}
	
	public List<ApplicationFieldSpecification> getAppFieldSpecsList() {
		if(selectedAppSpecs != null) {
			return appFieldSpecService.getAppFieldSpecList(selectedAppSpecs.getId());
		}
		return Collections.emptyList();
	}
	
	public List<DataType> getDataTypeList() {
		return dataTypeService.getDataTypeList();
	}
	/////------------/////
	public RuleService getRuleService() {
		return ruleService;
	}

	public MappingSchemeService getMappingSchemeService() {
		return mappingSchemeService;
	}

	public void setMappingSchemeService(MappingSchemeService mappingSchemeService) {
		this.mappingSchemeService = mappingSchemeService;
	}

	public ApplicationSpecificationService getAppSpecsService() {
		return appSpecsService;
	}

	public void setAppSpecsService(ApplicationSpecificationService appSpecsService) {
		this.appSpecsService = appSpecsService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	public ApplicationSpecification getAppSpecs() {
		return appSpecs;
	}

	public void setAppSpecs(ApplicationSpecification appSpecs) {
		this.appSpecs = appSpecs;
	}

	public ApplicationSpecification getSelectedAppSpecs() {
		return selectedAppSpecs;
	}

	public void setSelectedAppSpecs(ApplicationSpecification selectedAppSpecs) {
		this.selectedAppSpecs = selectedAppSpecs;
	}

	public ExternalSystemService getExtSystemService() {
		return extSystemService;
	}

	public void setExtSystemService(ExternalSystemService extSystemService) {
		this.extSystemService = extSystemService;
	}

	public ApplicationFieldSpecificationService getAppFieldSpecService() {
		return appFieldSpecService;
	}

	public void setAppFieldSpecService(
			ApplicationFieldSpecificationService appFieldSpecService) {
		this.appFieldSpecService = appFieldSpecService;
	}

	public DataTypeService getDataTypeService() {
		return dataTypeService;
	}

	public void setDataTypeService(DataTypeService dataTypeService) {
		this.dataTypeService = dataTypeService;
	}

	public Long getExtSystemId() {
		return extSystemId;
	}

	public void setExtSystemId(Long extSystemId) {
		this.extSystemId = extSystemId;
	}

	public ApplicationFieldSpecification getAppFieldSpecs() {
		return appFieldSpecs;
	}

	public void setAppFieldSpecs(ApplicationFieldSpecification appFieldSpecs) {
		this.appFieldSpecs = appFieldSpecs;
	}

	public ApplicationFieldSpecification getSelectedField() {
		return selectedField;
	}

	public void setSelectedField(ApplicationFieldSpecification selectedField) {
		this.selectedField = selectedField;
	}

	public Long getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

}
