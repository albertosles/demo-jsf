package com.demo.jsf.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.jsf.model.Product;
import com.demo.jsf.services.ProductService;
import com.demo.utils.ShoppingCartData;
import com.demo.utils.ShoppingCartUtils;
import com.demo.utils.TextUtil;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	private static final String PROD_ID = "id";
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String viewCart(HttpServletRequest request, Model model) {
		model.addAttribute("cartList", ShoppingCartUtils.getCartList(request.getSession()));
		return "shoppingcart";
	}
	
	@RequestMapping(value="/add-item", method = RequestMethod.GET)
	public String addItemToCart(HttpServletRequest request, Model model) {
		
		String id = request.getParameter(PROD_ID);
		if(id != null && !"".equals(id)) {
			Product product = productService.load(Long.valueOf(id));
			if(product != null) {
				ShoppingCartUtils.addToCart(product, request.getSession());
			}
		}
		model.addAttribute("cartList", ShoppingCartData.shopppingCartList);
		return "redirect:/product/browse.do";
	}
	
	@RequestMapping(value="remove-item", method = RequestMethod.GET)
	public String removeItemFromCart(HttpServletRequest request, Model model) {
		String id = request.getParameter(PROD_ID);
		if(!TextUtil.isEmpty(id)) {
			ShoppingCartUtils.removeFromCart(Long.valueOf(id), request.getSession());
		}
		return "redirect:/shoppingcart/view.do";
	}
	
	
}
