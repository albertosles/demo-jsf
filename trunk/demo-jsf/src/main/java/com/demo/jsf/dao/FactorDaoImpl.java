package com.demo.jsf.dao;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ScoringFactor;

@Transactional
public class FactorDaoImpl extends AbstractHibernateDAOImpl<ScoringFactor, Long> implements
		FactorDao {

	@Override
	protected Class<ScoringFactor> getDomainClass() {
		return ScoringFactor.class;
	}
	
	public ScoringFactor getById(Long id) {
		return (ScoringFactor) getHibernateTemplate().load(domainClass, id);
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
