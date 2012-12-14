package com.demo.jsf.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mapping_scheme")
public class MappingScheme implements EntityBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MappingScheme")
	@SequenceGenerator(name = "MappingScheme", sequenceName = "seq_mapping_scheme", allocationSize = 1)
	private Long id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "app_spec_id")
	private ApplicationSpecification appSpec;
	
	@ManyToOne
    @JoinColumn(name = "scoring_scheme_id")
	private ScoringScheme scheme;
	
	@OneToMany(mappedBy="mappingScheme")
	private Set<MappingSchemeRule> mappingRules;
	

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

	public ApplicationSpecification getAppSpec() {
		return appSpec;
	}

	public void setAppSpec(ApplicationSpecification appSpec) {
		this.appSpec = appSpec;
	}

	public ScoringScheme getScheme() {
		return scheme;
	}

	public void setScheme(ScoringScheme scheme) {
		this.scheme = scheme;
	}

	public Set<MappingSchemeRule> getMappingRules() {
		return mappingRules;
	}

	public void setMappingRules(Set<MappingSchemeRule> mappingRules) {
		this.mappingRules = mappingRules;
	}

}
