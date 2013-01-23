package com.demo.utils;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ItemCategoryType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;

import com.demo.jsf.model.Product;

public class ShoppingCartUtils {
	
	private static final String CURR_SHOPPING_CART = "currShoppingCart";
	
	private static final String TAX_PER_ITEM = "0";
	
	private static final String CURRENCY = "USD";
	
	private static final String CATEGORY_TYPE = "Physical";
	
	@SuppressWarnings("unchecked")
	public static void addToCart(Product item, HttpSession session) {
		
		if(item == null) return;
		
		Object cartObj = session.getAttribute(CURR_SHOPPING_CART);
		List<Product> cartList = null;
		if(cartObj != null) {
			cartList = (List<Product>) cartObj;
			for (Product product : cartList) {
				if(product.getId().equals(item.getId())) {
					int quantity = product.getQuantity() + 1;
					product.setQuantity(quantity);
//					cartList.add(product);
					session.setAttribute(CURR_SHOPPING_CART, cartList);
					return;
				}
			}
		} else {
			cartList = new LinkedList<Product>();
		}
		cartList.add(item);
		session.setAttribute(CURR_SHOPPING_CART, cartList);
	}
	
	@SuppressWarnings("unchecked")
	public static void removeFromCart(Long id, HttpSession session) {
		if (id == null || id < 1) return;
		if (session.getAttribute(CURR_SHOPPING_CART) == null) return;
		List<Product> cartList = (List<Product>)session.getAttribute(CURR_SHOPPING_CART);
		for (Product product : cartList) {
			if(product.getId().equals(id)) {
				cartList.remove(product);
				return;
			}
		}
	}
	
	public static void clearShoppingCart(HttpSession session) {
		session.setAttribute(CURR_SHOPPING_CART, null);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Product> getCartList(HttpSession session) {
		return (List<Product>)session.getAttribute(CURR_SHOPPING_CART);
	}
	
	@SuppressWarnings("unchecked")
	public static List<PaymentDetailsItemType> getCartListForPayment(HttpSession session) {
		
		if(session.getAttribute(CURR_SHOPPING_CART) == null) return null;
		
		List<PaymentDetailsItemType> paymentCartList = new LinkedList<PaymentDetailsItemType>();
		List<Product> productList = (List<Product>) session.getAttribute(CURR_SHOPPING_CART);
		for (Product product : productList) {
			
			PaymentDetailsItemType item = new PaymentDetailsItemType();
			BasicAmountType amt = new BasicAmountType();
			amt.setCurrencyID(CurrencyCodeType.fromValue(CURRENCY));
			amt.setValue(product.getPrice().toString());
			item.setQuantity(product.getQuantity());
			item.setName(product.getName());
			item.setAmount(amt);
			item.setItemCategory(ItemCategoryType.fromValue(CATEGORY_TYPE));
			item.setDescription(product.getDescription());
			item.setTax(new BasicAmountType(CurrencyCodeType.fromValue(CURRENCY), TAX_PER_ITEM));
			paymentCartList.add(item);
		}
		return paymentCartList;
	}
}
