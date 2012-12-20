package com.demo.jsf.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.jsf.model.EntityBean;

public abstract class AbstractHibernateDAOImpl<T extends EntityBean, KeyType extends Serializable>
		extends HibernateDaoSupport {

	protected Class<T> domainClass = getDomainClass();

	/**
	 * Method to return the class of the domain object
	 */
	protected abstract Class<T> getDomainClass();

	@Autowired
	public void setupSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public T load(KeyType id) {
		return (T) getHibernateTemplate().get(domainClass, id);
	}

	public T update(T t) {
		getHibernateTemplate().update(t);
		return t;
	}

	public T save(T t) {
		getHibernateTemplate().save(t);
		return t;
	}

	public T saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
		return t;
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList() {
		return (getHibernateTemplate().find("from " + domainClass.getName()
				+ " x"));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(int first, int pageSize) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(domainClass);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public void deleteById(KeyType id) {
		Object obj = load(id);
		getHibernateTemplate().delete(obj);
	}

	@SuppressWarnings("unchecked")
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlDelete = "delete " + domainClass.getName();
				int deletedEntities = session.createQuery(hqlDelete)
						.executeUpdate();
				return null;
			}

		});
	}

	public int count() {
		List list = getHibernateTemplate().find(
				"select count(*) from " + domainClass.getName() + " x");
		Long count = (Long) list.get(0);
		return count.intValue();
	}
}
