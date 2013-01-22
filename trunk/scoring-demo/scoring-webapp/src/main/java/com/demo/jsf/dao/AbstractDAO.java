package com.demo.jsf.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

public interface AbstractDAO<DomainObject, KeyType> {

	public DomainObject load(KeyType id);
	public DomainObject update(DomainObject object);
	public DomainObject save(DomainObject object);
	public DomainObject saveOrUpdate(DomainObject object);
	public void delete(DomainObject object);
	public void deleteById(KeyType id);
	public List<DomainObject> getList();
	public List<DomainObject> getList(int first, int pageSize);
	List<DomainObject> getList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);
	List<DomainObject> getList(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters, Criteria criteria);
	public void deleteAll();
	public int count();
}