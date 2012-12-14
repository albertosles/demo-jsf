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
@Table(name = "scoring_factor")
public class ScoringFactor implements EntityBean {
	
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Factor")
	@SequenceGenerator(name = "Factor", sequenceName = "seq_factor", allocationSize = 1)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="factor")
	private Set<ScoringRule> rules;
	
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

	public Set<ScoringRule> getRules() {
		return rules;
	}

	public void setRules(Set<ScoringRule> rules) {
		this.rules = rules;
	}

}