package com.demo.jsf.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jexl2.JexlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.model.ApplicationFieldSpecification;
import com.demo.jsf.model.MappingSchemeRule;
import com.demo.jsf.model.MappingSchemeRuleCase;
import com.demo.jsf.model.ScoringRule;
import com.demo.utils.Constants;
import com.demo.utils.DataTypeConstants;
import com.demo.utils.ExpressionUtil;
import com.demo.utils.MathUtility;
import com.demo.ws.dto.AppField;
import com.demo.ws.dto.ApplicationDocument;

@Service(ScoringService.SERVICE_ID)
public class ScoringServiceImpl implements ScoringService {
	
	@Autowired
	private MappingSchemeService mappingSchemeService;
	
	@Autowired
	private MappingSchemeRuleService mappingRuleService;
	
	@Autowired
	private MappingSchemeRuleCaseService mappingRuleCaseService;

	@Autowired
	private ApplicationFieldSpecificationService appFieldSpecService;

	public double calculateScoring(Long extSysId, Long appSpecId, Long mapScheId, ApplicationDocument appDoc) {
		
		//Reset value of evaluation.
		double total = 0;
		if(!mappingSchemeService.isValidInputData(extSysId, appSpecId, mapScheId)) {
	        return Constants.CODE_INVALID_PARAMETERS;
		}
		Map <String,Object> fieldValuePairs = new HashMap<String, Object>();
		for (AppField field : appDoc.getFields()) {
			fieldValuePairs.put(field.getName(), field.getValue());
		}
		
		List<ApplicationFieldSpecification> appFieldSpecList = appFieldSpecService.getAppFieldSpecList(appSpecId);
		for (ApplicationFieldSpecification appFieldSpec : appFieldSpecList) {
			
//			Long appFieldSpecId = appFieldSpec.getId();
			String fieldName = appFieldSpec.getName();
			String formatPattern = appFieldSpec.getFormatPattern();
			String dataType = appFieldSpec.getDataType().getCode();
			
			String fieldValue = (String)fieldValuePairs.get(fieldName);
			if(fieldValue == null || "".equals(fieldValue)) {
				return Constants.CODE_INVALID_PARAMETERS;
			}
			if(DataTypeConstants.INTEGER.equals(dataType)) {
				Integer intFieldValue;
				try {
					intFieldValue = Integer.parseInt(fieldValue);
				}
				catch(NumberFormatException nFE) {
			        return Constants.CODE_INVALID_PARAMETERS;
				}
				fieldValuePairs.put(fieldName, intFieldValue);
			} else if(DataTypeConstants.DOUBLE.equals(dataType)) {
				Double douFieldVal;
				try {
					douFieldVal = Double.parseDouble(fieldValue);
				}
				catch(NumberFormatException nFE) {
			        return Constants.CODE_INVALID_PARAMETERS;
				}
				fieldValuePairs.put(fieldName, douFieldVal);
			} else if(DataTypeConstants.DATETIME.equals(dataType)) {
				SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		        Date dateFieldVal;
				try {
					dateFieldVal = formatter.parse(fieldValue);
				} catch (ParseException e) {
			        return Constants.CODE_INVALID_PARAMETERS;
				}
				fieldValuePairs.put(fieldName, dateFieldVal);
			} else if(DataTypeConstants.STRING.equals(dataType)) {
				fieldValuePairs.put(fieldName, fieldValue);
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
			        return Constants.CODE_INVALID_CONDITION_EXPRESSION;
				}
			}
		}
		return MathUtility.Round(total, 2);
	}

}