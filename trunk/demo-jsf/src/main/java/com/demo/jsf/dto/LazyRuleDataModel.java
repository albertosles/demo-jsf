package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ScoringRule;
import com.demo.jsf.services.RuleService;

public class LazyRuleDataModel extends LazyDataModel<ScoringRule> {

	private static final long serialVersionUID = 1L;

	private RuleService ruleService;

	private List<ScoringRule> dataSource = null;
	
	private Long schemeId;

	public LazyRuleDataModel(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@Override
	public ScoringRule getRowData(String rowKey) {
		for (ScoringRule scheme : dataSource) {
			if (scheme.getId().equals(rowKey))
				return scheme;
		}
		return null;
	}

	@Override
	public Object getRowKey(ScoringRule scheme) {
		return scheme.getId();
	}

	@Override
	public List<ScoringRule> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = ruleService.getRuleList(schemeId, first, pageSize);
		int dataSize = ruleService.countBySchemeId(schemeId);
		this.setRowCount(dataSize);
		return dataSource;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	
}