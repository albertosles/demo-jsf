package com.demo.ws.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "applicationDoc")
public class ApplicationDocument {
	
	@XmlElement
	private int id;
	
	@XmlElement
	private List<AppField> fields;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AppField> getFields() {
		return fields;
	}

	public void setFields(List<AppField> fields) {
		this.fields = fields;
	}
	
}
