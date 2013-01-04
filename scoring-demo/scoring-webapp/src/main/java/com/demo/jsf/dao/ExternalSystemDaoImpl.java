package com.demo.jsf.dao;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ExternalSystem;

@Transactional
public class ExternalSystemDaoImpl extends AbstractHibernateDAOImpl<ExternalSystem, Long> implements
		ExternalSystemDao {

	@Override
	protected Class<ExternalSystem> getDomainClass() {
		return ExternalSystem.class;
	}
	
	public ExternalSystem findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (ExternalSystem) query.uniqueResult();

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
