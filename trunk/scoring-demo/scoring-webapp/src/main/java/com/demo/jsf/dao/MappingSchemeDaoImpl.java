package com.demo.jsf.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.jsf.model.MappingScheme;

@Transactional
public class MappingSchemeDaoImpl extends AbstractHibernateDAOImpl<MappingScheme, Long> implements
		MappingSchemeDao {

	@Override
	protected Class<MappingScheme> getDomainClass() {
		return MappingScheme.class;
	}
	
	public MappingScheme findByName(String name) {

		String queryStr = "from " + domainClass.getName() + " x " + "where x.name =:name";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("name", name);
		return (MappingScheme) query.uniqueResult();

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
	
	public long countByAppSpecsId(Long apSpecsId) {
		
		String queryStr = "select count(*) from " + domainClass.getName() + " x " + "where x.appSpec.id =:apSpecsId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("apSpecsId", apSpecsId);
		List list = query.list();
		if (list == null) {
			return 0;
		}
		return (Long)list.get(0);
	}
	
	public boolean isValidInputData(Long extSysId, Long appSpecId, Long mapSchemeId) {
		String queryStr = "select count(*) from " + domainClass.getName() + " x " 
							+ "where x.id =:mapSchemeId AND x.appSpec.id =:appSpecId " +
									"AND x.appSpec.externalSystem.id =:extSysId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryStr);
		query.setParameter("mapSchemeId", mapSchemeId);
		query.setParameter("appSpecId", appSpecId);
		query.setParameter("extSysId", extSysId);
		Long count = (Long) query.list().get(0);
		if(count > 0)
			return true;
		return false;
	}
	
	public List<MappingScheme> getList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		
		Criteria criteria = this.getSessionFactory().
								getCurrentSession().createCriteria(domainClass);
		criteria.createAlias("appSpec", "appSpec");
		criteria.createAlias("scheme", "scheme");
		return getList(first, pageSize, sortField, sortOrder, filters, criteria);
	}

}
