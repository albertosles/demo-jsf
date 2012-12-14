package com.demo.jsf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "application_field_specification")
public class ApplicationFieldSpecification implements EntityBean {
	
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ApplicationFieldSpecification")
	@SequenceGenerator(name = "ApplicationFieldSpecification", sequenceName = "seq_application_field_specification", allocationSize = 1)
	private Long id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "data_type_id")
	private DataType dataType;
	
	@Column(name = "format_pattern")
	private String formatPattern;
	
	@ManyToOne
    @JoinColumn(name = "app_spec_id")
	private ApplicationSpecification appSpec;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getFormatPattern() {
		return formatPattern;
	}

	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}

	public ApplicationSpecification getAppSpec() {
		return appSpec;
	}

	public void setAppSpec(ApplicationSpecification appSpec) {
		this.appSpec = appSpec;
	}

}
