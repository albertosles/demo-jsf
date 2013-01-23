package com.demo.jsf.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.jsf.model.Product;
import com.demo.jsf.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/browse", method = RequestMethod.GET)
	public String shoppingCart(Model model) {
		
		List<Product> productList = productService.getList();
		model.addAttribute("productList", productList);
		return "product";
	}
	
}
