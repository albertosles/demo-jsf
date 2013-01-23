package com.demo.jsf.dao;

import com.demo.jsf.model.Product;

public interface ProductDao extends AbstractDAO<Product, Long> {
	Product findByName(String name);
	boolean isNameExist(String name);
}
