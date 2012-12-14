package com.demo.jsf.web.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class ConverterDemoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private double doubleValue = 12345.12345;
	
	@PostConstruct
	public void init() {
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	
}