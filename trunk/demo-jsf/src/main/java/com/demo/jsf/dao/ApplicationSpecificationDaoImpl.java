package com.demo.jsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ApplicationSpecification;

@Transactional
public class ApplicationSpecificationDaoImpl extends AbstractHibernateDAOImpl<ApplicationSpecification, Long> implements
		ApplicationSpecificationDao {

	@Override
	protected Class<ApplicationSpecification> getDomainClass() {
		return ApplicationSpecification.class;
	}
	
	public ApplicationSpecification findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (ApplicationSpecification) query.uniqueResult();
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
	
	public long countByExtSysId(Long extSysId) {
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.externalSystem.id =:extSysId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("extSysId", extSysId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}
	
}
