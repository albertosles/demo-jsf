package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ApplicationSpecification;
import com.demo.jsf.services.ApplicationSpecificationService;

public class LazyAppSpecDataModel extends LazyDataModel<ApplicationSpecification> {

	private static final long serialVersionUID = 1L;

	private ApplicationSpecificationService appSpecService;

	private List<ApplicationSpecification> dataSource = null;

	public LazyAppSpecDataModel(ApplicationSpecificationService appSpecService) {
		this.appSpecService = appSpecService;
	}

	@Override
	public ApplicationSpecification getRowData(String rowKey) {
		for (ApplicationSpecification appSpec : dataSource) {
			if (appSpec.getId().equals(rowKey))
				return appSpec;
		}
		return null;
	}

	@Override
	public Object getRowKey(ApplicationSpecification appSpec) {
		return appSpec.getId();
	}

	@Override
	public List<ApplicationSpecification> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = appSpecService.getAppSpecsList(first, pageSize, sortField, sortOrder.toString(), filters);;
		int dataSize = appSpecService.count();
		this.setRowCount(dataSize);
		return dataSource;
	}
}