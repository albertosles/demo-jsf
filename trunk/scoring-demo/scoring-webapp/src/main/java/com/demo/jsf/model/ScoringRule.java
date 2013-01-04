package com.demo.jsf.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "scoring_rule")
public class ScoringRule implements EntityBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rule")
	@SequenceGenerator(name = "Rule", sequenceName = "seq_rule", allocationSize = 1)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "scheme_id")
    public ScoringScheme scheme; //unidirectional
	
	@ManyToOne
    @JoinColumn(name = "factor_id")
    public ScoringFactor factor; //unidirectional
	
	@OneToMany(mappedBy="rule",fetch = FetchType.EAGER)
	private Set<ScoringRuleCase> ruleCases;
	
	private double weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScoringScheme getScheme() {
		return scheme;
	}

	public void setScheme(ScoringScheme scheme) {
		this.scheme = scheme;
	}

	public ScoringFactor getFactor() {
		return factor;
	}

	public void setFactor(ScoringFactor factor) {
		this.factor = factor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Set<ScoringRuleCase> getRuleCases() {
		return ruleCases;
	}

	public void setRuleCases(Set<ScoringRuleCase> ruleCases) {
		this.ruleCases = ruleCases;
	}
	
}