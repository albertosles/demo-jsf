package com.demo.jsf.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.MappingSchemeRuleCase;

@Transactional
public class MappingSchemeRuleCaseDaoImpl extends AbstractHibernateDAOImpl<MappingSchemeRuleCase, Long> implements
		MappingSchemeRuleCaseDao {

	@Override
	protected Class<MappingSchemeRuleCase> getDomainClass() {
		return MappingSchemeRuleCase.class;
	}
	
	public MappingSchemeRuleCase findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (MappingSchemeRuleCase) query.uniqueResult();
	}
	
	public boolean isNameExist(String name) {

		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		Long count = (Long) query.list().get(0);
		if(count > 0)
			return true;
		return false;
	}
	
	public List<MappingSchemeRuleCase> getMappingRuleCaseList(Long mappingRuleId) {

		String queryStr = "from " + domainClass.getName() + " x " 
							+ "where x.mappingSchemeRule.id =:mappingRuleId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("mappingRuleId", mappingRuleId);
		@SuppressWarnings("unchecked")
		List<MappingSchemeRuleCase> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}
	
	public List<Long> getScoringRuleCaseIds() {

		String queryStr = "select x.scoringRuleCase.id from " + domainClass.getName() + " x ";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<Long> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}

	public long countByMappingRuleId(Long mappingRuleId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.mappingSchemeRule.id =:mappingRuleId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("mappingRuleId", mappingRuleId);
		@SuppressWarnings("unchecked")
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}
}
