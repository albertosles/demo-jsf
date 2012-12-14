package com.demo.jsf.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "external_system")
public class ExternalSystem implements EntityBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExternalSystem")
	@SequenceGenerator(name = "ExternalSystem", sequenceName = "seq_external_system", allocationSize = 1)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="externalSystem")
	private Set<ApplicationSpecification> applicationSpecs;
	

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

	public Set<ApplicationSpecification> getApplicationSpecs() {
		return applicationSpecs;
	}

	public void setApplicationSpecs(Set<ApplicationSpecification> applicationSpecs) {
		this.applicationSpecs = applicationSpecs;
	}

}