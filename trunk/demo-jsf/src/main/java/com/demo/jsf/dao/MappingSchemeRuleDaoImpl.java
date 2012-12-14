package com.demo.jsf.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.MappingSchemeRule;

@Transactional
public class MappingSchemeRuleDaoImpl extends AbstractHibernateDAOImpl<MappingSchemeRule, Long> implements
		MappingSchemeRuleDao {

	@Override
	protected Class<MappingSchemeRule> getDomainClass() {
		return MappingSchemeRule.class;
	}
	
	public MappingSchemeRule findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (MappingSchemeRule) query.uniqueResult();
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
	
	public List<MappingSchemeRule> getMappingRuleList(Long mappingSchemeId) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.mappingScheme.id =:mappingSchemeId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("mappingSchemeId", mappingSchemeId);
		@SuppressWarnings("unchecked")
		List<MappingSchemeRule> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}
	
	public long countBySchemeId(Long mapSchemeId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.mappingScheme.id =:mapSchemeId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("mapSchemeId", mapSchemeId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}
	
	public List<Long> getScoringRuleIds() {
		
		String queryStr = "select x.rule.id from " + domainClass.getName() + " x ";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<Long> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
		
	}

}
