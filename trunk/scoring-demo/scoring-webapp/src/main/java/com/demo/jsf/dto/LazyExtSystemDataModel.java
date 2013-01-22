package com.demo.jsf.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ExternalSystem;
import com.demo.jsf.services.ExternalSystemService;

public class LazyExtSystemDataModel extends LazyDataModel<ExternalSystem> {

	private static final long serialVersionUID = 1L;

	private ExternalSystemService extSystemService;

	private List<ExternalSystem> dataSource = null;

	public LazyExtSystemDataModel(ExternalSystemService extSystemService) {
		this.extSystemService = extSystemService;
	}

	@Override
	public ExternalSystem getRowData(String rowKey) {
		for (ExternalSystem factor : dataSource) {
			if (factor.getId().equals(rowKey))
				return factor;
		}
		return null;
	}

	@Override
	public Object getRowKey(ExternalSystem extSystem) {
		return extSystem.getId();
	}

	@Override
	public List<ExternalSystem> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		dataSource = extSystemService.getExtSystemList(first, pageSize, sortField, sortOrder.toString(), filters);
		int dataSize = extSystemService.count();
		this.setRowCount(dataSize);
		return dataSource;
	}
}