package com.demo.jsf.dao;

import org.hibernate.Query;

import com.demo.jsf.model.Product;

public class ProductDaoImpl extends AbstractHibernateDAOImpl<Product, Long> implements
		ProductDao {

	@Override
	protected Class<Product> getDomainClass() {
		return Product.class;
	}
	
	public Product findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (Product) query.uniqueResult();

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
