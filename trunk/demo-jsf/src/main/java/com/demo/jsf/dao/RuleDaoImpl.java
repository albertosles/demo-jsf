package com.demo.jsf.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ScoringRule;

@Transactional
public class RuleDaoImpl extends AbstractHibernateDAOImpl<ScoringRule, Long>
		implements RuleDao {

	@Override
	protected Class<ScoringRule> getDomainClass() {
		return ScoringRule.class;
	}

	@SuppressWarnings("unchecked")
	public List<ScoringRule> getRuleList() {
		return (getHibernateTemplate().find("from " + domainClass.getName()
				+ " x"));
	}
	
	public List<ScoringRule> getList(Long schemeId, List<Long> excludeIds) {

		if(excludeIds == null) {
			List<Long> emptyList = Collections.emptyList();
			excludeIds = emptyList;
		}
		String queryStr = "from " + domainClass.getName() + " x " 
							+ "where x.scheme.id =:schemeId AND x.id NOT IN (:excludeIds)";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("schemeId", schemeId);
		query.setParameterList("excludeIds", excludeIds);
		@SuppressWarnings("unchecked")
		List<ScoringRule> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}
	
	
	public long countBySchemeId(Long schemeId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.scheme.id =:schemeId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("schemeId", schemeId);
		@SuppressWarnings("unchecked")
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}
	
	public long countByFactorId(Long factorId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.factor.id =:factorId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("factorId", factorId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}

}
