package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.MappingScheme;
import com.demo.jsf.services.MappingSchemeService;

public class LazyMappingSchemeDataModel extends LazyDataModel<MappingScheme> {

	private static final long serialVersionUID = 1L;

	private MappingSchemeService mapSchemeService;

	private List<MappingScheme> dataSource = null;

	public LazyMappingSchemeDataModel(MappingSchemeService schemeService) {
		this.mapSchemeService = schemeService;
	}

	@Override
	public MappingScheme getRowData(String rowKey) {
		for (MappingScheme scheme : dataSource) {
			if (scheme.getId().equals(rowKey))
				return scheme;
		}
		return null;
	}

	@Override
	public Object getRowKey(MappingScheme scheme) {
		return scheme.getId();
	}

	@Override
	public List<MappingScheme> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = mapSchemeService.getSchemeList(first, pageSize, sortField, sortOrder.toString(), filters);
		int dataSize = mapSchemeService.count();
		this.setRowCount(dataSize);
		return dataSource;
	}
}