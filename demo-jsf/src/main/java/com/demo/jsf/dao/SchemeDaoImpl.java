package com.demo.jsf.dao;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ScoringScheme;

@Transactional
public class SchemeDaoImpl extends AbstractHibernateDAOImpl<ScoringScheme, Long> implements
		SchemeDao {

	@Override
	protected Class<ScoringScheme> getDomainClass() {
		return ScoringScheme.class;
	}
	
	public ScoringScheme findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (ScoringScheme) query.uniqueResult();

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

}
