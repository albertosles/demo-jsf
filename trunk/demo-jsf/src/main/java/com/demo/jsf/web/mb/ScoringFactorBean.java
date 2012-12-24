package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.demo.jsf.dto.LazyFactorDataModel;
import com.demo.jsf.model.ScoringFactor;
import com.demo.jsf.services.FactorService;
import com.demo.jsf.services.RuleService;

public class ScoringFactorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private FactorService factorService;
	
	private RuleService ruleService;
	
	private ScoringFactor factor;
	
	private ScoringFactor selectedFactor;
	
	private LazyFactorDataModel factorDataModel;
	
	@PostConstruct
	public void init() {
		factor = new ScoringFactor();
		factorDataModel = new LazyFactorDataModel(factorService);
	}
	
	public String saveFactor() {
		
		if(factorService.isNameExist(factor.getName())) {
			FacesMessage msg = new FacesMessage("Error", "Factor Name is duplicate.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			factorService.createFactor(factor);
			factor.setName("");
			FacesMessage msg = new FacesMessage("Successfull", "The Factor has been created successfully.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return null;
	}
	
	public void deleteFactor() {

		if(selectedFactor == null) return;
		
		long factorCount = ruleService.countByFactorId(selectedFactor.getId());
		if(factorCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Factor is not empty, Please remove all rules first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			factorService.delete(selectedFactor.getId());
			selectedFactor = null;
			FacesMessage msg = new FacesMessage("Successfull", "The Factor has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public LazyFactorDataModel getFactorDataModel() {
		return factorDataModel;
	}

	public List<ScoringFactor> getFactorList() {
		return factorService.getFactorList();
	}
	
	/////------------/////
	public FactorService getFactorService() {
		return factorService;
	}

	public void setFactorService(FactorService factorService) {
		this.factorService = factorService;
	}

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	public ScoringFactor getFactor() {
		return factor;
	}

	public void setFactor(ScoringFactor factor) {
		this.factor = factor;
	}

	public ScoringFactor getSelectedFactor() {
		return selectedFactor;
	}

	public void setSelectedFactor(ScoringFactor selectedFactor) {
		this.selectedFactor = selectedFactor;
	}

}
