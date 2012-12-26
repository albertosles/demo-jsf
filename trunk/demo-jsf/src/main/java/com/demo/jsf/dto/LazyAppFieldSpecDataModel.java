package com.demo.jsf.dto;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.jsf.model.ApplicationFieldSpecification;
import com.demo.jsf.services.ApplicationFieldSpecificationService;

public class LazyAppFieldSpecDataModel extends LazyDataModel<ApplicationFieldSpecification> {

	private static final long serialVersionUID = 1L;

	private ApplicationFieldSpecificationService appFieldSpecService;

	private List<ApplicationFieldSpecification> dataSource = null;
	
	private Long appSpecId;

	public LazyAppFieldSpecDataModel(ApplicationFieldSpecificationService appFieldSpecService) {
		this.appFieldSpecService = appFieldSpecService;
	}

	@Override
	public ApplicationFieldSpecification getRowData(String rowKey) {
		for (ApplicationFieldSpecification appFieldSpec : dataSource) {
			if (appFieldSpec.getId().equals(rowKey))
				return appFieldSpec;
		}
		return null;
	}

	@Override
	public Object getRowKey(ApplicationFieldSpecification scheme) {
		return scheme.getId();
	}

	@Override
	public List<ApplicationFieldSpecification> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		dataSource = appFieldSpecService.getAppFieldSpecList(appSpecId, first, pageSize);
		int dataSize = appFieldSpecService.countByAppSpecId(appSpecId);
		this.setRowCount(dataSize);
		return dataSource;
	}

	public void setAppSpecId(Long appSpecId) {
		this.appSpecId = appSpecId;
	}
	
}