package com.demo.jsf.services;

import java.util.List;

import com.demo.jsf.model.Product;



public interface ProductService {
	final String SERVICE_ID = "productService";
	Product load(Long id);
	List<Product> getList();
}
