package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.demo.jsf.model.ScoringFactor;
import com.demo.jsf.model.ScoringRule;
import com.demo.jsf.model.ScoringRuleCase;
import com.demo.jsf.model.ScoringScheme;
import com.demo.jsf.services.FactorService;
import com.demo.jsf.services.RuleCaseService;
import com.demo.jsf.services.RuleService;
import com.demo.jsf.services.SchemeService;

public class ScoringSchemeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String HOMEPAGE = "homepage";

	private static final String LOGIN = "login";
	
	private SchemeService schemeService;
	
	private RuleService ruleService;
	
	private RuleCaseService ruleCaseService;
	
	private FactorService factorService;
	
	private ScoringScheme scheme;
	
	private ScoringScheme selectedScheme;
	
	private ScoringRule rule;
	
	private ScoringRule selectedRule;
	
	private ScoringRuleCase ruleCase;
	
	private ScoringRuleCase selectedRuleCase;
	
	private Long factorId;
	
	@PostConstruct
	public void init() {
		scheme = new ScoringScheme();
		rule = new ScoringRule();
		ruleCase = new ScoringRuleCase();
	}
	
	public String saveScheme() {

		if(schemeService.isNameExist(scheme.getName())) {
			FacesMessage msg = new FacesMessage("Error", "Scheme Name is duplicate.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			schemeService.createScheme(scheme);
			scheme.setName("");
			FacesMessage msg = new FacesMessage("Successfull", "Scheme has been created successfully.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return null;
	}
	
	public String updateScheme() {

		schemeService.updateScheme(selectedScheme);
		FacesMessage msg = new FacesMessage("Successfull", "Scheme has been updated successfully.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	public void showEditScheme() {
		selectedRule = null;
		selectedRuleCase = null;
	}

	public void showEditRule() {
		
		if(selectedRule != null) {
			factorId = selectedRule.getFactor().getId();
			rule = selectedRule;
		}
	}
	
	public void showCreateRule() {
		rule = new ScoringRule();
	}
	
	public void showEditRuleCase() {
		
		if(selectedRuleCase != null) {
			ruleCase = selectedRuleCase;
		}
	}
	
	public void showCreateRuleCase() {
		ruleCase = new ScoringRuleCase();
	}
	
	public String saveRule() {
		
		if(selectedScheme != null && rule != null) {
			ScoringFactor factor = factorService.getFactorById(factorId);
			rule.setFactor(factor);
			
			if(rule.getId() > 0) {
				//updating rule
				ruleService.updateRule(rule);
				FacesMessage msg = new FacesMessage("Successfull", "The Rule has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				//creating rule
				rule.setScheme(selectedScheme);
				ruleService.createRule(rule);
				rule.setWeight(0);
				factorId = null;
				FacesMessage msg = new FacesMessage("Successfull", "The Rule has been created successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		} else {
			System.out.println("CAN NOT SAVE RULE");
		}
		return null;
	}
	
	public String saveRuleCase() {
		
		if(selectedRule != null && ruleCase != null) {
			ruleCase.setRule(selectedRule);
			if(ruleCase.getId() > 0) {
				ruleCaseService.updateRuleCase(ruleCase);
				FacesMessage msg = new FacesMessage("Successfull", "The Rule Case has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				ruleCaseService.createRuleCase(ruleCase);
				ruleCase.setFactorChoice("");
				ruleCase.setScore(0);
				FacesMessage msg = new FacesMessage("Successfull", "The Rule Case has been created successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} else {
			System.out.println("CAN NOT SAVE RULE CASE");
		}
		return null;
	}

	public void deleteScheme() {

		if(selectedScheme == null) return;
		
		long ruleCount = ruleService.countBySchemeId(selectedScheme.getId());
		if(ruleCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Scheme is not empty, Please remove all rule first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			schemeService.delete(selectedScheme.getId());
			selectedScheme = null;
			FacesMessage msg = new FacesMessage("Successfull", "The Scheme has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void deleteRule() {

		if(selectedRule == null) return;
		
		long ruleCaseCount = ruleCaseService.countByRuleId(selectedRule.getId());
		if(ruleCaseCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Rule is not empty, Please remove all rule case first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			ruleService.delete(selectedRule.getId());
			selectedRule = null;
			FacesMessage msg = new FacesMessage("Successfull", "The Rule has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void deleteRuleCase() {

		if(selectedRuleCase == null) return;
		
		ruleCaseService.delete(selectedRuleCase.getId());
		selectedRuleCase = null;
		FacesMessage msg = new FacesMessage("Successfull", "The Rule Case has been deleted.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	
	public int getEvaluatedCreditScore() {
		
		return 10;
	}
	
	public List<ScoringScheme> getSchemeList() {
		return schemeService.getSchemeList();
	}
	
	public List<ScoringRule> getRuleList() {
		if(selectedScheme != null) {
			return ruleService.getRuleList(selectedScheme.getId());
		}
//		return ruleService.getRuleList();
		return Collections.emptyList();
		
	}
	
	public List<ScoringRuleCase> getRuleCaseList() {
		if(selectedRule != null) {
			List<Long> emtyList = Collections.emptyList();
			return ruleCaseService.getRuleCaseList(selectedRule.getId(), emtyList);
		}
		return Collections.emptyList();
	}
	
	public SchemeService getSchemeService() {
		return schemeService;
	}

	public void setSchemeService(SchemeService schemeService) {
		this.schemeService = schemeService;
	}

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	public RuleCaseService getRuleCaseService() {
		return ruleCaseService;
	}

	public void setRuleCaseService(RuleCaseService ruleCaseService) {
		this.ruleCaseService = ruleCaseService;
	}

	public ScoringScheme getScheme() {
		return scheme;
	}

	public void setScheme(ScoringScheme scheme) {
		this.scheme = scheme;
	}

	public ScoringScheme getSelectedScheme() {
		return selectedScheme;
	}

	public void setSelectedScheme(ScoringScheme selectedScheme) {
		this.selectedScheme = selectedScheme;
	}

	public ScoringRule getRule() {
		return rule;
	}

	public void setRule(ScoringRule rule) {
		this.rule = rule;
	}

	public ScoringRule getSelectedRule() {
		return selectedRule;
	}

	public void setSelectedRule(ScoringRule selectedRule) {
		this.selectedRule = selectedRule;
	}

	public ScoringRuleCase getRuleCase() {
		return ruleCase;
	}

	public void setRuleCase(ScoringRuleCase ruleCase) {
		this.ruleCase = ruleCase;
	}

	public ScoringRuleCase getSelectedRuleCase() {
		return selectedRuleCase;
	}

	public void setSelectedRuleCase(ScoringRuleCase selectedRuleCase) {
		this.selectedRuleCase = selectedRuleCase;
	}

	public FactorService getFactorService() {
		return factorService;
	}

	public void setFactorService(FactorService factorService) {
		this.factorService = factorService;
	}

	public Long getFactorId() {
		return factorId;
	}

	public void setFactorId(Long factorId) {
		this.factorId = factorId;
	}

	public String gotoLogin() {
		return LOGIN;
	}
	
	public String gotoHomepage() {
		return HOMEPAGE;
	}
	
}
