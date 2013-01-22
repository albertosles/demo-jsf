package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ScoringFactor;
import com.demo.jsf.services.FactorService;

public class LazyFactorDataModel extends LazyDataModel<ScoringFactor> {

	private static final long serialVersionUID = 1L;

	private FactorService factorService;

	private List<ScoringFactor> dataSource = null;

	public LazyFactorDataModel(FactorService factorService) {
		this.factorService = factorService;
	}

	@Override
	public ScoringFactor getRowData(String rowKey) {
		for (ScoringFactor factor : dataSource) {
			if (factor.getId().equals(rowKey))
				return factor;
		}
		return null;
	}

	@Override
	public Object getRowKey(ScoringFactor scheme) {
		return scheme.getId();
	}

	@Override
	public List<ScoringFactor> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = factorService.getFactorList(first, pageSize, sortField, sortOrder.toString(), filters);
		int dataSize = factorService.count();
		this.setRowCount(dataSize);
		return dataSource;
	}
}