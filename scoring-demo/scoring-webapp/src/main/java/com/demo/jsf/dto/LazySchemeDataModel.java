package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ScoringScheme;
import com.demo.jsf.services.SchemeService;

public class LazySchemeDataModel extends LazyDataModel<ScoringScheme> {

	private static final long serialVersionUID = 1L;

	private SchemeService schemeService;

	private List<ScoringScheme> dataSource = null;

	public LazySchemeDataModel(SchemeService schemeService) {
		this.schemeService = schemeService;
	}

	@Override
	public ScoringScheme getRowData(String rowKey) {
		for (ScoringScheme scheme : dataSource) {
			if (scheme.getId().equals(rowKey))
				return scheme;
		}
		return null;
	}

	@Override
	public Object getRowKey(ScoringScheme scheme) {
		return scheme.getId();
	}

	@Override
	public List<ScoringScheme> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = schemeService.getSchemeList(first, pageSize, sortField, sortOrder.toString(), filters);
		int dataSize = schemeService.count();
		this.setRowCount(dataSize);
		return dataSource;
	}
}