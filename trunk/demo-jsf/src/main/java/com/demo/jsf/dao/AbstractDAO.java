package com.demo.jsf.dao;

import java.util.List;

public interface AbstractDAO<DomainObject, KeyType> {

	public DomainObject load(KeyType id);

	public DomainObject update(DomainObject object);

	public DomainObject save(DomainObject object);

	public DomainObject saveOrUpdate(DomainObject object);

	public void delete(DomainObject object);

	public void deleteById(KeyType id);

	public List<DomainObject> getList();

	public void deleteAll();

	public int count();

}
