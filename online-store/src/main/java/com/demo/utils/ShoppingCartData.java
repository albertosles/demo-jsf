package com.demo.utils;

import java.util.LinkedList;
import java.util.List;

import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ItemCategoryType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;

public class ShoppingCartData {

	public static List<PaymentDetailsItemType> shopppingCartList;
	
	static {
		
		shopppingCartList = new LinkedList<PaymentDetailsItemType>();
		
		String currency = "USD";
		String amountItems = "20";
		String qtyItems = "1";
		String names = "Motorola 1234";
		String description = "this is a motorola phone";
		String categoryType = "Physical";
		String taxAmount = "0";
		
		PaymentDetailsItemType item = new PaymentDetailsItemType();
		BasicAmountType amt = new BasicAmountType();
		amt.setCurrencyID(CurrencyCodeType.fromValue(currency));
		amt.setValue(amountItems);
		item.setQuantity(new Integer(qtyItems));
		item.setName(names);
		item.setAmount(amt);
		item.setItemCategory(ItemCategoryType.fromValue(categoryType));
		item.setDescription(description);
		item.setTax(new BasicAmountType(CurrencyCodeType.fromValue(currency), taxAmount));
		shopppingCartList.add(item);
		
		currency = "USD";
		amountItems = "30";
		qtyItems = "1";
		names = "Sony Ericsion XYZ";
		description = "this is a sony phone";
		categoryType = "Physical";
		taxAmount = "0";
		
		item = new PaymentDetailsItemType();
		amt = new BasicAmountType();
		amt.setCurrencyID(CurrencyCodeType.fromValue(currency));
		amt.setValue(amountItems);
		item.setQuantity(new Integer(qtyItems));
		item.setName(names);
		item.setAmount(amt);
		item.setItemCategory(ItemCategoryType.fromValue(categoryType));
		item.setDescription(description);
		item.setTax(new BasicAmountType(CurrencyCodeType.fromValue(currency), taxAmount));
		shopppingCartList.add(item);
		
	}
}
