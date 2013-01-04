package com.demo.jsf.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.ApplicationFieldSpecification;

@Transactional
public class ApplicationFieldSpecificationDaoImpl extends AbstractHibernateDAOImpl<ApplicationFieldSpecification, Long> implements
		ApplicationFieldSpecificationDao {

	@Override
	protected Class<ApplicationFieldSpecification> getDomainClass() {
		return ApplicationFieldSpecification.class;
	}
	
	public ApplicationFieldSpecification findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (ApplicationFieldSpecification) query.uniqueResult();

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
	
	public boolean isNameExist(String name, Long appSpecId) {

		String queryStr = "select count(*) from " + domainClass.getName() + " x " 
							+ "where x.name =:name AND x.appSpec.id =:appSpecId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		query.setParameter("appSpecId", appSpecId);
		Long count = (Long) query.list().get(0);
		if(count > 0)
			return true;
		return false;
	}
	
	public List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId) {

		String queryStr = "from " + domainClass.getName() + " x " 
							+ "where x.appSpec.id =:appSpecId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("appSpecId", appSpecId);
		@SuppressWarnings("unchecked")
		List<ApplicationFieldSpecification> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}
	
	public List<ApplicationFieldSpecification> getAppFieldSpecList(Long appSpecId, int first, int pageSize) {

		String queryStr = "from " + domainClass.getName() + " x " 
							+ "where x.appSpec.id =:appSpecId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("appSpecId", appSpecId);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<ApplicationFieldSpecification> list = query.list();
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}
	
	public int countByDataTypeId(Long dataTypeId) {
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.dataType.id =:dataTypeId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("dataTypeId", dataTypeId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		Long count = (Long)list.get(0);
		return count.intValue();
	}
	
	public int countByAppSpecId(Long appSpecId) {
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.appSpec.id =:appSpecId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("appSpecId", appSpecId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		Long count = (Long)list.get(0);
		return count.intValue();
	}

}
