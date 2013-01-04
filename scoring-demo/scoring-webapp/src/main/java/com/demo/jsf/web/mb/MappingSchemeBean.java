package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.demo.jsf.model.ApplicationSpecification;
import com.demo.jsf.model.MappingScheme;
import com.demo.jsf.model.MappingSchemeRule;
import com.demo.jsf.model.MappingSchemeRuleCase;
import com.demo.jsf.model.ScoringRule;
import com.demo.jsf.model.ScoringRuleCase;
import com.demo.jsf.model.ScoringScheme;
import com.demo.jsf.services.ApplicationSpecificationService;
import com.demo.jsf.services.MappingSchemeRuleCaseService;
import com.demo.jsf.services.MappingSchemeRuleService;
import com.demo.jsf.services.MappingSchemeService;
import com.demo.jsf.services.RuleCaseService;
import com.demo.jsf.services.RuleService;
import com.demo.jsf.services.SchemeService;

public class MappingSchemeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private MappingSchemeService mappingSchemeService;
	
	private MappingSchemeRuleService mappingRuleService;
	
	private MappingSchemeRuleCaseService mappingRuleCaseService;
	
	private SchemeService schemeService;
	
	private RuleService ruleService;
	
	private RuleCaseService ruleCaseService;
	
	private ApplicationSpecificationService appSpecsService;
	
	private MappingScheme mappingScheme;
	
	private MappingScheme selectedMapScheme;
	
	private MappingSchemeRule mappingRule;
	
	private MappingSchemeRule selectedMapRule;
	
	private MappingSchemeRuleCase mapRuleCase;
	
	private MappingSchemeRuleCase selectedMapRuleCase;
	
	
	private Long scoringSchemeId;
	
	private Long appSpecsId;
	
	private Long scoringRuleId;
	
	private Long scoringRuleCaseId;
	
	private boolean isEdit = false;
	
	@PostConstruct
	public void init() {
		mappingScheme = new MappingScheme();
	}
	
	public String saveMappingScheme() {
		
		if(mappingSchemeService.isNameExist(mappingScheme.getName())) {
			FacesMessage msg = new FacesMessage("Error", "Mapping Scheme Name is duplicate.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			ScoringScheme scoringScheme = schemeService.load(scoringSchemeId);
			ApplicationSpecification appSpecs = appSpecsService.load(appSpecsId);
			mappingScheme.setScheme(scoringScheme);
			mappingScheme.setAppSpec(appSpecs);
			mappingSchemeService.createScheme(mappingScheme);
			mappingScheme.setName("");
			mappingScheme.setAppSpec(null);
			mappingScheme.setScheme(null);
			FacesMessage msg = new FacesMessage("Successfull", "The Mapping Scheme has been created successfully.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return null;
	}
	
	public String updateMappingScheme() {

		ScoringScheme scoringScheme = schemeService.load(scoringSchemeId);
		ApplicationSpecification appSpecs = appSpecsService.load(appSpecsId);
		selectedMapScheme.setScheme(scoringScheme);
		selectedMapScheme.setAppSpec(appSpecs);
		mappingSchemeService.updateScheme(selectedMapScheme);
		FacesMessage msg = new FacesMessage("Successfull", "Mapping Scheme has been updated successfully.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	public void deleteMappingScheme() {

		if(selectedMapScheme == null) return;
		
		long appSpecsCount = mappingRuleService.countBySchemeId(selectedMapScheme.getId());
		if(appSpecsCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Mapping Scheme is not empty, Please remove all Mapping Scheme Rule first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			mappingSchemeService.delete(selectedMapScheme.getId());
			selectedMapScheme = null;
			FacesMessage msg = new FacesMessage("Successfull", "The Mapping Scheme has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deleteMappingRule() {

		if(selectedMapRule == null) return;
		
		long ruleCaseCount = mappingRuleCaseService.countByMappingRuleId(selectedMapRule.getId());
		if(ruleCaseCount > 0) {
			FacesMessage msg = new FacesMessage("Error", "The Mapping Rule is not empty, Please remove all rule case first.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			mappingRuleService.delete(selectedMapRule.getId());
			selectedMapRule = null;
			FacesMessage msg = new FacesMessage("Successfull", "The Mapping Rule has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void deleteMappingRuleCase() {

		if(selectedMapRuleCase == null) return;
		mappingRuleCaseService.delete(selectedMapRuleCase.getId());
		selectedMapRuleCase = null;
		FacesMessage msg = new FacesMessage("Successfull", "The Mapping Rule Case has been deleted.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public String saveMappingRule() {
		
		if(selectedMapScheme != null && mappingRule != null) {
			
			ScoringRule scoringRule = ruleService.getById(scoringRuleId);
			mappingRule.setRule(scoringRule);
			
			if(mappingRule.getId() != null && mappingRule.getId() > 0) {
				//updating rule
				mappingRuleService.update(mappingRule);
				FacesMessage msg = new FacesMessage("Successfull", "The Mapping Rule has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				//creating rule
				mappingRule.setMappingScheme(selectedMapScheme);
				mappingRuleService.create(mappingRule);
				scoringRuleId = null;
				mappingRule = new MappingSchemeRule();
				FacesMessage msg = new FacesMessage("Successfull", "The Rule has been created successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		} else {
			System.out.println("CAN NOT SAVE MAPPING RULE");
		}
		return null;
	}
	
	public String saveMappingRuleCase() {
		
		if(selectedMapRule != null && mapRuleCase != null) {
			mapRuleCase.setMappingSchemeRule(selectedMapRule);
			
			ScoringRuleCase scoringRuleCase = ruleCaseService.getById(scoringRuleCaseId);
			mapRuleCase.setScoringRuleCase(scoringRuleCase);
			if(mapRuleCase.getId() != null && mapRuleCase.getId() > 0) {
				mappingRuleCaseService.updateMappingRuleCase(mapRuleCase);
				FacesMessage msg = new FacesMessage("Successfull", "The Mapping Rule Case has been updated successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				mappingRuleCaseService.createMappingRuleCase(mapRuleCase);
				scoringRuleCaseId = null;
				mapRuleCase = new MappingSchemeRuleCase();
				FacesMessage msg = new FacesMessage("Successfull", "The Mapping Rule Case has been created successfully.");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} else {
			System.out.println("CAN NOT SAVE MAPPING RULE CASE");
		}
		return null;
	}
	
	public void showEditMappingScheme() {
		if(selectedMapScheme != null) {
			scoringSchemeId = selectedMapScheme.getScheme().getId();
			appSpecsId = selectedMapScheme.getAppSpec().getId();
		}
		isEdit = true;
	}
	
	public void showCreateMappingRule() {
		mappingRule = new MappingSchemeRule();
		scoringRuleId = null;
		isEdit = false;
	}
	
	public void showEditMappingRule() {
		
		if(selectedMapRule != null) {
			scoringRuleId = selectedMapRule.getRule().getId();
			mappingRule = selectedMapRule;
			isEdit = true;
		}
	}
	
	public void showEditMappingRuleCase() {
		if(selectedMapRuleCase != null) {
			mapRuleCase = selectedMapRuleCase;
			scoringRuleCaseId = mapRuleCase.getScoringRuleCase().getId();
			isEdit = true;
		}
	}
	
	public void showCreateMappingRuleCase() {
		mapRuleCase = new MappingSchemeRuleCase();
		isEdit = false;
	}
	
	public List<MappingScheme> getMappingSchemeList() {
		return mappingSchemeService.getSchemeList();
	}
	
	public List<ScoringScheme> getSchemeList() {
		return schemeService.getSchemeList();
	}
	
	public List<ApplicationSpecification> getAppSpecsList() {
		return appSpecsService.getAppSpecsList();
	}
	
	public List<MappingSchemeRule> getMappingRuleList() {
		if(selectedMapScheme != null) {
			return mappingRuleService.getMappingRuleList(selectedMapScheme.getId());
		}
		return Collections.emptyList();
	}
	
	public List<MappingSchemeRuleCase> getMappingRuleCaseList() {
		if(selectedMapRule != null) {
			
			return mappingRuleCaseService.getMappingRuleCaseList(selectedMapRule.getId());
		}
		return Collections.emptyList();
	}


	public List<ScoringRule> getScoringRuleList() {
		if(selectedMapScheme != null) {
			List<Long> excludeIds = mappingRuleService.getScoringRuleIds();
			if(isEdit) excludeIds.remove(selectedMapRule.getRule().getId());
			return ruleService.getRuleList(selectedMapScheme.getScheme().getId(), excludeIds);
		}
		return Collections.emptyList();
	}
	
	public List<ScoringRuleCase> getScoringRuleCaseList() {
		if(selectedMapRule != null) {
			List<Long> excludeIds = mappingRuleCaseService.getScoringRuleCaseIds();
			if(isEdit) excludeIds.remove(selectedMapRuleCase.getScoringRuleCase().getId());
			return ruleCaseService.getRuleCaseList(selectedMapRule.getRule().getId(), excludeIds);
		}
		return Collections.emptyList();
	}

	public MappingSchemeService getMappingSchemeService() {
		return mappingSchemeService;
	}

	public void setMappingSchemeService(MappingSchemeService mappingSchemeService) {
		this.mappingSchemeService = mappingSchemeService;
	}

	public MappingSchemeRuleService getMappingRuleService() {
		return mappingRuleService;
	}

	public void setMappingRuleService(MappingSchemeRuleService mappingRuleService) {
		this.mappingRuleService = mappingRuleService;
	}

	public SchemeService getSchemeService() {
		return schemeService;
	}

	public void setSchemeService(SchemeService schemeService) {
		this.schemeService = schemeService;
	}

	public ApplicationSpecificationService getAppSpecsService() {
		return appSpecsService;
	}

	public void setAppSpecsService(ApplicationSpecificationService appSpecsService) {
		this.appSpecsService = appSpecsService;
	}

	public MappingScheme getMappingScheme() {
		return mappingScheme;
	}

	public void setMappingScheme(MappingScheme mappingScheme) {
		this.mappingScheme = mappingScheme;
	}

	public MappingScheme getSelectedMapScheme() {
		return selectedMapScheme;
	}

	public void setSelectedMapScheme(MappingScheme selectedMapScheme) {
		this.selectedMapScheme = selectedMapScheme;
	}

	public Long getScoringSchemeId() {
		return scoringSchemeId;
	}

	public void setScoringSchemeId(Long scoringSchemeId) {
		this.scoringSchemeId = scoringSchemeId;
	}

	public Long getAppSpecsId() {
		return appSpecsId;
	}

	public void setAppSpecsId(Long appSpecsId) {
		this.appSpecsId = appSpecsId;
	}

	public MappingSchemeRule getMappingRule() {
		return mappingRule;
	}

	public void setMappingRule(MappingSchemeRule mappingRule) {
		this.mappingRule = mappingRule;
	}

	public MappingSchemeRule getSelectedMapRule() {
		return selectedMapRule;
	}

	public void setSelectedMapRule(MappingSchemeRule selectedMapRule) {
		this.selectedMapRule = selectedMapRule;
	}

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	public MappingSchemeRuleCaseService getMappingRuleCaseService() {
		return mappingRuleCaseService;
	}

	public void setMappingRuleCaseService(
			MappingSchemeRuleCaseService mappingRuleCaseService) {
		this.mappingRuleCaseService = mappingRuleCaseService;
	}

	public Long getScoringRuleId() {
		return scoringRuleId;
	}

	public void setScoringRuleId(Long scoringRuleId) {
		this.scoringRuleId = scoringRuleId;
	}

	public MappingSchemeRuleCase getMapRuleCase() {
		return mapRuleCase;
	}

	public void setMapRuleCase(MappingSchemeRuleCase mapRuleCase) {
		this.mapRuleCase = mapRuleCase;
	}

	public MappingSchemeRuleCase getSelectedMapRuleCase() {
		return selectedMapRuleCase;
	}

	public void setSelectedMapRuleCase(MappingSchemeRuleCase selectedMapRuleCase) {
		this.selectedMapRuleCase = selectedMapRuleCase;
	}

	public Long getScoringRuleCaseId() {
		return scoringRuleCaseId;
	}

	public void setScoringRuleCaseId(Long scoringRuleCaseId) {
		this.scoringRuleCaseId = scoringRuleCaseId;
	}

	public RuleCaseService getRuleCaseService() {
		return ruleCaseService;
	}

	public void setRuleCaseService(RuleCaseService ruleCaseService) {
		this.ruleCaseService = ruleCaseService;
	}

}
