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
@Table(name = "scoring_rule_case")
public class ScoringRuleCase implements EntityBean {
	
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RuleCase")
	@SequenceGenerator(name = "RuleCase", sequenceName = "seq_rule_case", allocationSize = 1)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "rule_id")
    public ScoringRule rule; //unidirectional
	
	
	@Column(name = "factor_choice")
	private String factorChoice;
	
	private int score;
	
	@OneToMany(mappedBy="scoringRuleCase")
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

	public String getFactorChoice() {
		return factorChoice;
	}

	public void setFactorChoice(String factorChoice) {
		this.factorChoice = factorChoice;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Set<MappingSchemeRuleCase> getMappingRuleCases() {
		return mappingRuleCases;
	}

	public void setMappingRuleCases(Set<MappingSchemeRuleCase> mappingRuleCases) {
		this.mappingRuleCases = mappingRuleCases;
	}
}
