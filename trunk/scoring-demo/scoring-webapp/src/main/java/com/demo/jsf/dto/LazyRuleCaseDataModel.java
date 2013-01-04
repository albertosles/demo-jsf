package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ScoringRuleCase;
import com.demo.jsf.services.RuleCaseService;

public class LazyRuleCaseDataModel extends LazyDataModel<ScoringRuleCase> {

	private static final long serialVersionUID = 1L;

	private RuleCaseService ruleCaseService;

	private List<ScoringRuleCase> dataSource = null;
	
	private Long ruleId;

	public LazyRuleCaseDataModel(RuleCaseService ruleCaseService) {
		this.ruleCaseService = ruleCaseService;
	}

	@Override
	public ScoringRuleCase getRowData(String rowKey) {
		for (ScoringRuleCase ruleCase : dataSource) {
			if (ruleCase.getId().equals(rowKey))
				return ruleCase;
		}
		return null;
	}

	@Override
	public Object getRowKey(ScoringRuleCase ruleCase) {
		return ruleCase.getId();
	}

	@Override
	public List<ScoringRuleCase> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = ruleCaseService.getRuleCaseList(ruleId, first, pageSize);
		int dataSize = ruleCaseService.countByRuleId(ruleId);
		this.setRowCount(dataSize);
		return dataSource;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	
}