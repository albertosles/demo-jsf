package com.demo.jsf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "data_type")
public class DataType implements EntityBean {
	
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DataType")
	@SequenceGenerator(name = "DataType", sequenceName = "seq_data_type", allocationSize = 1)
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "code")
	private String code;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}