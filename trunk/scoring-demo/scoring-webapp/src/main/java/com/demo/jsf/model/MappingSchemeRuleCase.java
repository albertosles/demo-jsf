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
@Table(name = "mapping_scheme_rule_case")
public class MappingSchemeRuleCase implements EntityBean {
	
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MappingSchemeRuleCase")
	@SequenceGenerator(name = "MappingSchemeRuleCase", sequenceName = "seq_mapping_scheme_rule_case", allocationSize = 1)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "mapping_rule_id")
    private MappingSchemeRule mappingSchemeRule; //unidirectional
	
	@ManyToOne
    @JoinColumn(name = "scoring_rule_case_id")
    private ScoringRuleCase scoringRuleCase; //unidirectional
	
	@Column(name = "condition_expression")
	private String conditionExpression;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MappingSchemeRule getMappingSchemeRule() {
		return mappingSchemeRule;
	}

	public void setMappingSchemeRule(MappingSchemeRule mappingSchemeRule) {
		this.mappingSchemeRule = mappingSchemeRule;
	}

	public ScoringRuleCase getScoringRuleCase() {
		return scoringRuleCase;
	}

	public void setScoringRuleCase(ScoringRuleCase scoringRuleCase) {
		this.scoringRuleCase = scoringRuleCase;
	}

	public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

}
