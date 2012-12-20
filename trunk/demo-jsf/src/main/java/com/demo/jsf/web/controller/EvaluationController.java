package com.demo.jsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.jexl2.JexlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.jsf.dto.Result;
import com.demo.jsf.model.ApplicationFieldSpecification;
import com.demo.jsf.model.MappingSchemeRule;
import com.demo.jsf.model.MappingSchemeRuleCase;
import com.demo.jsf.model.ScoringRule;
import com.demo.jsf.services.ApplicationFieldSpecificationService;
import com.demo.jsf.services.MappingSchemeRuleCaseService;
import com.demo.jsf.services.MappingSchemeRuleService;
import com.demo.jsf.services.MappingSchemeService;
import com.demo.utils.Constants;
import com.demo.utils.DataTypeConstants;
import com.demo.utils.ExpressionUtil;
import com.demo.utils.MathUtility;

@Controller
@RequestMapping("/evaluate-scoring")
public class EvaluationController {
	
	@Autowired
	private MappingSchemeService mappingSchemeService;
	
	@Autowired
	private MappingSchemeRuleService mappingRuleService;
	
	@Autowired
	private MappingSchemeRuleCaseService mappingRuleCaseService;

	@Autowired
	private ApplicationFieldSpecificationService appFieldSpecService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Result evaludateScoring(@RequestParam MultiValueMap<String, String> params) {

		Long extSysId = params.getFirst("extSysId") != null ? Long.parseLong(params.getFirst("extSysId")):null;
		Long appSpecId = params.getFirst("appSpecId") != null ? Long.parseLong(params.getFirst("appSpecId")):null;
		Long mapScheId = params.getFirst("mapScheId") != null ? Long.parseLong(params.getFirst("mapScheId")):null;
		
		System.out.println(extSysId);
		System.out.println(appSpecId);
		System.out.println(mapScheId);
		
		//Reset value of evaluation.
		double total = 0;
		if(!mappingSchemeService.isValidInputData(extSysId, appSpecId, mapScheId)) {
	        return new Result(Constants.CODE_INVALID_PARAMETERS, "Invalid input data for External System, Application Specification and Mapping Scheme, Please check again");
		}
		Map <String,Object> fieldValuePairs = new HashMap<String, Object>();
		List<ApplicationFieldSpecification> appFieldSpecList = appFieldSpecService.getAppFieldSpecList(appSpecId);
		for (ApplicationFieldSpecification appFieldSpec : appFieldSpecList) {
			
//			Long appFieldSpecId = appFieldSpec.getId();
			String fieldName = appFieldSpec.getName();
			String formatPattern = appFieldSpec.getFormatPattern();
			String dataType = appFieldSpec.getDataType().getCode();
			
			String fieldValue = params.getFirst(fieldName);
			if(fieldValue == null || "".equals(fieldValue)) {
				return new Result(Constants.CODE_INVALID_PARAMETERS, "Invalid input data: Field value is empty.");
			}
			if(DataTypeConstants.INTEGER.equals(dataType)) {
				Integer intFieldValue;
				try {
					intFieldValue = Integer.parseInt(fieldValue);
				}
				catch(NumberFormatException nFE) {
			        return new Result(Constants.CODE_INVALID_PARAMETERS, "Invalid input data: Input value is not an integer number: "+ fieldValue);
				}
				fieldValuePairs.put(fieldName, intFieldValue);
			} else if(DataTypeConstants.DOUBLE.equals(dataType)) {
				Double douFieldVal;
				try {
					douFieldVal = Double.parseDouble(fieldValue);
				}
				catch(NumberFormatException nFE) {
			        return new Result(Constants.CODE_INVALID_PARAMETERS, "Invalid input data: Input value is not a double number: "+ fieldValue);
				}
				fieldValuePairs.put(fieldName, douFieldVal);
			} else if(DataTypeConstants.DATETIME.equals(dataType)) {
				SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		        Date dateFieldVal;
				try {
					dateFieldVal = formatter.parse(fieldValue);
				} catch (ParseException e) {
			        return new Result(Constants.CODE_INVALID_PARAMETERS, "Invalid input data: Input value is not a datetime value: " + fieldValue);
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
			        return new Result(Constants.CODE_INVALID_CONDITION_EXPRESSION, "Invalid condition expression: The exression is not valid: "+ condExpression);
				}
			}
		}
		total = MathUtility.Round(total, 2);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("score", total);
		return new Result(Constants.CODE_SUCCESS, "Successfully", content);
	}
}
