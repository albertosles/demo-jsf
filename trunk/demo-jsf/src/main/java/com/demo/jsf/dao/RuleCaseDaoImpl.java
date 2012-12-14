package com.demo.jsf.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ScoringRuleCase;

@Transactional
public class RuleCaseDaoImpl extends AbstractHibernateDAOImpl<ScoringRuleCase, Long>
		implements RuleCaseDao {

	@Override
	protected Class<ScoringRuleCase> getDomainClass() {
		return ScoringRuleCase.class;
	}

	public List<ScoringRuleCase> getRuleCaseList(Long ruleId, List<Long> excludeIds) {

		String queryStr = "from " + domainClass.getName() + " x " 
							+ "where x.rule.id =:ruleId AND x.id NOT IN (:excludeIds)";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("ruleId", ruleId);
		query.setParameterList("excludeIds", excludeIds);
		@SuppressWarnings("unchecked")
		List<ScoringRuleCase> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;

	}
	
	public long countByRuleId(Long ruleId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.rule.id =:ruleId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("ruleId", ruleId);
		@SuppressWarnings("unchecked")
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}

}
