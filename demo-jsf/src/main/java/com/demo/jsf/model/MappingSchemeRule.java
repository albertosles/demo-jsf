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
@Table(name = "mapping_scheme_rule")
public class MappingSchemeRule implements EntityBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MappingSchemeRule")
	@SequenceGenerator(name = "MappingSchemeRule", sequenceName = "seq_mapping_scheme_rule", allocationSize = 1)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "scoring_rule_id")
	private ScoringRule rule;
	
	@ManyToOne
    @JoinColumn(name = "mapping_scheme_id")
	private MappingScheme mappingScheme;
	
	@OneToMany(mappedBy="mappingSchemeRule")
	private Set<MappingSchemeRuleCase> mappingRuleCases;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScoringRule getRule() {
		return rule;
	}

	public void setRule(ScoringRule rule) {
		this.rule = rule;
	}

	public MappingScheme getMappingScheme() {
		return mappingScheme;
	}

	public void setMappingScheme(MappingScheme mappingScheme) {
		this.mappingScheme = mappingScheme;
	}

	public Set<MappingSchemeRuleCase> getMappingRuleCases() {
		return mappingRuleCases;
	}

	public void setMappingRuleCases(Set<MappingSchemeRuleCase> mappingRuleCases) {
		this.mappingRuleCases = mappingRuleCases;
	}

}
