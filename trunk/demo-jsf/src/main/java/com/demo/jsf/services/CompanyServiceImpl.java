package com.demo.jsf.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service(CompanyService.SERVICE_ID)
public class CompanyServiceImpl implements CompanyService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCompany() {
		return "test service";
	}

}