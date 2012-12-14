package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.jexl2.JexlException;

import com.demo.jsf.model.ApplicationFieldSpecification;
import com.demo.jsf.model.ApplicationSpecification;
import com.demo.jsf.model.ExternalSystem;
import com.demo.jsf.model.MappingScheme;
import com.demo.jsf.model.MappingSchemeRule;
import com.demo.jsf.model.MappingSchemeRuleCase;
import com.demo.jsf.model.ScoringRule;
import com.demo.jsf.model.ScoringRuleCase;
import com.demo.jsf.model.ScoringScheme;
import com.demo.jsf.services.ApplicationFieldSpecificationService;
import com.demo.jsf.services.ApplicationSpecificationService;
import com.demo.jsf.services.ExternalSystemService;
import com.demo.jsf.services.FactorService;
import com.demo.jsf.services.MappingSchemeRuleCaseService;
import com.demo.jsf.services.MappingSchemeRuleService;
import com.demo.jsf.services.MappingSchemeService;
import com.demo.jsf.services.RuleCaseService;
import com.demo.jsf.services.RuleService;
import com.demo.jsf.services.SchemeService;
import com.demo.utils.DataTypeConstants;
import com.demo.utils.ExpressionUtil;
import com.demo.utils.MathUtility;

public class ScoringEvaluationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private SchemeService schemeService;
	
	private RuleService ruleService;
	
	private RuleCaseService ruleCaseService;
	
	private FactorService factorService;
	
	private ExternalSystemService extSystemService;
	
	private MappingSchemeService mappingSchemeService;
	
	private MappingSchemeRuleService mappingRuleService;
	
	private MappingSchemeRuleCaseService mappingRuleCaseService;
	
	private ApplicationSpecificationService appSpecsService;
	
	private ApplicationFieldSpecificationService appFieldSpecService;
	
	private ScoringScheme scheme;
	
	private ScoringScheme selectedScheme;
	
	private ScoringRule rule;
	
	private ScoringRule selectedRule;
	
	private ScoringRuleCase ruleCase;
	
	private ScoringRuleCase selectedRuleCase;
	
	private Long schemeId;
	
	private Long appSpecId;
	
	private Long extSysId;
	
	private Long mapScheId;
	
	private double total = 0;
	
	List<Long> ruleCaseIds = new ArrayList<Long>();
	private Map<Long, String> selectedIds = new HashMap<Long, String>();
	
	private Map<Long, String> fieldValues = new HashMap<Long, String>();
	
	@PostConstruct
	public void init() {
		scheme = new ScoringScheme();
		rule = new ScoringRule();
		ruleCase = new ScoringRuleCase();
		extSysId = new Long(1);
		mapScheId = new Long(1);
	}
	
	public void mapCreditScore() {
		
		//Reset value of evaluation.
		total = 0;
		if(!mappingSchemeService.isValidInputData(extSysId, appSpecId, mapScheId)) {
			FacesMessage msg = new FacesMessage("Error", "Invalid input data for External System, Application Specification and Mapping Scheme, Please check again");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return;
		}
		Map <String,Object> fieldValuePairs = new HashMap<String, Object>();
		List<ApplicationFieldSpecification> appFieldSpecList = appFieldSpecService.getAppFieldSpecList(appSpecId);
		for (ApplicationFieldSpecification appFieldSpec : appFieldSpecList) {
			
			Long appFieldSpecId = appFieldSpec.getId();
			String fieldName = appFieldSpec.getName();
			String formatPattern = appFieldSpec.getFormatPattern();
			String dataType = appFieldSpec.getDataType().getCode();
			String fieldValue = fieldValues.get(appFieldSpecId);
			if(fieldValue != null && !"".equals(fieldValue)) {
				if(DataTypeConstants.INTEGER.equals(dataType)) {
					Integer intFieldValue;
					try {
						intFieldValue = Integer.parseInt(fieldValue);
					}
					catch(NumberFormatException nFE) {
						FacesMessage msg = new FacesMessage("Error", "Input value is not an integer number: " + fieldValue);
				        FacesContext.getCurrentInstance().addMessage(null, msg);
					    return;
					}
					fieldValuePairs.put(fieldName, intFieldValue);
				} else if(DataTypeConstants.INTEGER.equals(dataType)) {
					Double douFieldVal;
					try {
						douFieldVal = Double.parseDouble(fieldValue);
					}
					catch(NumberFormatException nFE) {
						FacesMessage msg = new FacesMessage("Error", "Input value is not a double number: " + fieldValue);
				        FacesContext.getCurrentInstance().addMessage(null, msg);
					    return;
					}
					fieldValuePairs.put(fieldName, douFieldVal);
				} else if(DataTypeConstants.DATETIME.equals(dataType)) {
					SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
			        Date dateFieldVal;
					try {
						dateFieldVal = formatter.parse(fieldValue);
					} catch (ParseException e) {
						FacesMessage msg = new FacesMessage("Error", "Input value is not a datetime: " + fieldValue);
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					fieldValuePairs.put(fieldName, dateFieldVal);
				}
			}
		}
		
		List<MappingSchemeRule> mappingRuleList = mappingRuleService.getMappingRuleList(mapScheId);
		for (MappingSchemeRule mappingRule : mappingRuleList) {
			Long mappingRuleId = mappingRule.getId();
			ScoringRule scoringRule = mappingRule.getRule();
			double weight = scoringRule.getWeight();
			List<MappingSchemeRuleCase> mappingRuleCases = mappingRuleCaseService.getMappingRuleCaseList(mappingRuleId);
			for (MappingSchemeRuleCase mappingRuleCase : mappingRuleCases) {
				
				String condExpression = mappingRuleCase.getConditionExpression();
				try {
					if(ExpressionUtil.expressionCheck(condExpression, fieldValuePairs)) {
						double score = mappingRuleCase.getScoringRuleCase().getScore();
						total += weight * score;
					}
				} catch (JexlException e) {
					total = 0;
					FacesMessage msg = new FacesMessage("Successfull", "There is an error with expression: " + condExpression);
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        return;
				}
			}
		}
		total = MathUtility.Round(total, 2);
	}
	
	public ExternalSystem getUsingExtSys() {
		return extSystemService.load(extSysId);
	}
	
	public MappingScheme getUsingMappingScheme() {
		return mappingSchemeService.load(mapScheId);
	}

	public List<ScoringScheme> getSchemeList() {
		return schemeService.getSchemeList();
	}
	
	public List<ScoringRule> getRuleList() {
		if(schemeId != null) {
			return ruleService.getRuleList(schemeId);
		}
		return Collections.emptyList();
	}
	
	public List<ScoringRuleCase> getRuleCaseList() {
		if(selectedRule != null) {
			List<Long> emptyList = Collections.emptyList();
			return ruleCaseService.getRuleCaseList(selectedRule.getId(), emptyList);
		}
		return Collections.emptyList();
	}
	
	public List<ApplicationSpecification> getAppSpecList() {
		return appSpecsService.getAppSpecsList();
	}
	
	public List<ApplicationFieldSpecification> getAppFieldSpecList() {
		if(appSpecId != null) {
			return appFieldSpecService.getAppFieldSpecList(appSpecId);
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

	public MappingSchemeRuleCaseService getMappingRuleCaseService() {
		return mappingRuleCaseService;
	}

	public void setMappingRuleCaseService(
			MappingSchemeRuleCaseService mappingRuleCaseService) {
		this.mappingRuleCaseService = mappingRuleCaseService;
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

	public ApplicationFieldSpecificationService getAppFieldSpecService() {
		return appFieldSpecService;
	}

	public void setAppFieldSpecService(
			ApplicationFieldSpecificationService appFieldSpecService) {
		this.appFieldSpecService = appFieldSpecService;
	}

	public ApplicationSpecificationService getAppSpecsService() {
		return appSpecsService;
	}

	public void setAppSpecsService(ApplicationSpecificationService appSpecsService) {
		this.appSpecsService = appSpecsService;
	}

	public ExternalSystemService getExtSystemService() {
		return extSystemService;
	}

	public void setExtSystemService(ExternalSystemService extSystemService) {
		this.extSystemService = extSystemService;
	}

	public Long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public List<Long> getRuleCaseIds() {
		return ruleCaseIds;
	}

	public void setRuleCaseIds(List<Long> ruleCaseIds) {
		this.ruleCaseIds = ruleCaseIds;
	}

	public Map<Long, String> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(Map<Long, String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public Map<Long, String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(Map<Long, String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getAppSpecId() {
		return appSpecId;
	}

	public void setAppSpecId(Long appSpecId) {
		this.appSpecId = appSpecId;
	}

	public Long getExtSysId() {
		return extSysId;
	}

	public void setExtSysId(Long extSysId) {
		this.extSysId = extSysId;
	}

	public Long getMapScheId() {
		return mapScheId;
	}

	public void setMapScheId(Long mapScheId) {
		this.mapScheId = mapScheId;
	}
}