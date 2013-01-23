package com.demo.jsf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.dao.ProductDao;
import com.demo.jsf.model.Product;

@Service(ProductService.SERVICE_ID)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Product load(Long id) {
		return productDao.load(id);
	}
	public List<Product> getList() {
		return productDao.getList();
	}
	
}