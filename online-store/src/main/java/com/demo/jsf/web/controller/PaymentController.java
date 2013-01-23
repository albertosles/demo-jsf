package com.demo.jsf.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.BillingAgreementDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.demo.utils.ShoppingCartData;
import com.demo.utils.ShoppingCartUtils;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@RequestMapping(value="/return-payment", method = RequestMethod.GET)
	public String returnPayment(HttpServletRequest request, Model model) {
		
		System.out.println("return payment");
		PayPalAPIInterfaceServiceService service = null;
		try {
			service = new PayPalAPIInterfaceServiceService(this.getClass().getResourceAsStream("/sdk_config.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		GetExpressCheckoutDetailsReq req = new GetExpressCheckoutDetailsReq();
		GetExpressCheckoutDetailsRequestType reqType = new GetExpressCheckoutDetailsRequestType(request.getParameter("token"));
		req.setGetExpressCheckoutDetailsRequest(reqType);
		GetExpressCheckoutDetailsResponseType resp = null;
		try {
			resp = service.getExpressCheckoutDetails(req);
		} catch (SSLConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			e.printStackTrace();
		} catch (HttpErrorException e) {
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		if (resp != null) {
			if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				model.addAttribute("Ack", resp.getAck());
				model.addAttribute("token", resp.getGetExpressCheckoutDetailsResponseDetails().getToken());
				model.addAttribute("payerID", resp.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
				model.addAttribute("cartList", ShoppingCartUtils.getCartList(request.getSession()));
			} else {
				model.addAttribute("Error", resp.getErrors());
			}
		}
		return "paypalReturn";
	}
	
	@RequestMapping(value="/cancel-payment", method = RequestMethod.GET)
	public String cancelPayment(@RequestParam MultiValueMap<String, String> params, HttpServletResponse response) {
		System.out.println("Cancel payment");
		return "paypalCancel";
	}
	
	@RequestMapping(value="/do-payment", method = RequestMethod.POST)
	public String doPayment(@RequestParam MultiValueMap<String, String> params, HttpServletRequest request, HttpServletResponse response, Model model) {

		String returnURL = "http://localhost:8080/online-store/payment/return-payment.do";
		String cancelURL = "http://localhost:8080/online-store/payment/cancel-payment.do";
		String notifyURL = "";
		String paymentAction = params.getFirst("paymentAction");
		String currencyCode = params.getFirst("currencyCode");
		String buyerEmail = params.getFirst("buyerEmail");
		
		request.getSession().setAttribute("paymentAction", paymentAction);
		request.getSession().setAttribute("currencyCode", currencyCode);
		request.getSession().setAttribute("notifyURL", notifyURL);
		
		
		PayPalAPIInterfaceServiceService service = null;
		try {
			service = new PayPalAPIInterfaceServiceService(this.getClass().getResourceAsStream("/sdk_config.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
			
			//Step 01: SetExpressCheckout
			SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
			SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

			details.setReturnURL(returnURL);
			details.setCancelURL(cancelURL);
			details.setBuyerEmail(buyerEmail);

			double itemTotal = 0.00;
			double orderTotal = 0.00;
			
			List<PaymentDetailsItemType> lineItems = ShoppingCartUtils.getCartListForPayment(request.getSession());
			for (PaymentDetailsItemType item : lineItems) {
				int qtyItems = item.getQuantity();
				String amountItems = item.getAmount().getValue();
				itemTotal += qtyItems * Double.parseDouble(amountItems);
			}
			orderTotal += itemTotal;
			List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
			PaymentDetailsType paydtl = new PaymentDetailsType();
			//Payment Type value
			paydtl.setPaymentAction(PaymentActionCodeType.fromValue(paymentAction));
			
			//Shipping Fee
			String shippingFee = "0";
			BasicAmountType shippingTotal = new BasicAmountType();
			shippingTotal.setValue(shippingFee);
			shippingTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCode));
			orderTotal += Double.parseDouble(shippingFee);
			paydtl.setShippingTotal(shippingTotal);
			
			//Insurance Fee
//			String insuranceFee = "0";
//			paydtl.setInsuranceTotal(new BasicAmountType(
//					CurrencyCodeType.USD, insuranceFee));
//			paydtl.setInsuranceOptionOffered("true");
//			orderTotal += Double.parseDouble(insuranceFee);
			
			//Handling Fee
			String handlingFee = "0";
				paydtl.setHandlingTotal(new BasicAmountType(
						CurrencyCodeType.fromValue(currencyCode), handlingFee));
				orderTotal += Double.parseDouble(handlingFee);
			
			//Tax Fee
			String taxFee = "0";
			if (!"".equals(taxFee) && !"0".equals(taxFee)) {
				paydtl.setTaxTotal(new BasicAmountType(CurrencyCodeType
						.fromValue(currencyCode),
						taxFee));
				orderTotal += Double.parseDouble(taxFee);
			}
			
			String orderDescription = "this is order descrition";
			paydtl.setOrderDescription(orderDescription);
			
			BasicAmountType itemsTotal = new BasicAmountType();
			itemsTotal.setValue(Double.toString(itemTotal));
			itemsTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCode));
			paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType
					.fromValue(currencyCode),
					Double.toString(orderTotal)));
			paydtl.setPaymentDetailsItem(lineItems);

			paydtl.setItemTotal(itemsTotal);
			paydtl.setNotifyURL(notifyURL);
			payDetails.add(paydtl);
			details.setPaymentDetails(payDetails);
			//Billing Type
			String billingType = "None";
			String billingAgreementText = "this is billing text";
			BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType(
					BillingCodeType.fromValue(billingType));
			billingAgreement.setBillingAgreementDescription(billingAgreementText);
			List<BillingAgreementDetailsType> billList = new ArrayList<BillingAgreementDetailsType>();
			billList.add(billingAgreement);
			details.setBillingAgreementDetails(billList);
			
//			//shipping address
//			details.setReqConfirmShipping(request.getParameter("reqConfirmShipping"));
//			details.setAddressOverride(request.getParameter("addressoverride"));
//			AddressType shipToAddress=new AddressType();
//			shipToAddress.setName(request.getParameter("name"));
//			shipToAddress.setStreet1(request.getParameter("street1"));
//			shipToAddress.setStreet2(request.getParameter("street2"));
//			shipToAddress.setCityName(request.getParameter("city"));
//			shipToAddress.setStateOrProvince(request.getParameter("state"));
//			shipToAddress.setPostalCode(request.getParameter("postalCode"));
//			shipToAddress.setCountry(CountryCodeType.fromValue(request.getParameter("countryCode")));
//			details.setAddress(shipToAddress);
//			
//			// shipping display options
//			details.setNoShipping(request.getParameter("noShipping"));
//			
//			// PayPal page styling attributes
//			details.setBrandName(request.getParameter("brandName"));
//			details.setCustom(request.getParameter("pageStyle"));
//			details.setCppHeaderImage(request.getParameter("cppheaderimage"));
//			details.setCppHeaderBorderColor(request.getParameter("cppheaderbordercolor"));
//			details.setCppHeaderBackColor(request.getParameter("cppheaderbackcolor"));
//			details.setCppPayflowColor(request.getParameter("cpppayflowcolor"));
//			details.setAllowNote(request.getParameter("allowNote"));

			setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);

			SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
			expressCheckoutReq
					.setSetExpressCheckoutRequest(setExpressCheckoutReq);

			SetExpressCheckoutResponseType setExpressCheckoutResponse = null;
			try {
				setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
			} catch (SSLConfigurationException e1) {
				e1.printStackTrace();
			} catch (InvalidCredentialException e1) {
				e1.printStackTrace();
			} catch (HttpErrorException e1) {
				e1.printStackTrace();
			} catch (InvalidResponseDataException e1) {
				e1.printStackTrace();
			} catch (ClientActionRequiredException e1) {
				e1.printStackTrace();
			} catch (MissingCredentialException e1) {
				e1.printStackTrace();
			} catch (OAuthException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}

			if (setExpressCheckoutResponse != null) {
				System.out.println("lastReq: " + service.getLastRequest());
				System.out.println("lastResp: " + service.getLastResponse());
				//02: Redirect to Paypal after SetExpressCheckout success
				if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					try {
						response.sendRedirect("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
							+ setExpressCheckoutResponse.getToken());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					//Show Errors in UI 
					model.addAttribute("errorList", setExpressCheckoutResponse.getErrors());
				}
			}
		return "shoppingcart";
	}
	
	@RequestMapping(value="/confirm-payment", method = RequestMethod.POST)
	public String confirmPayment(HttpServletRequest request, Model model) {
		
		System.out.println("confirm payment");
		PayPalAPIInterfaceServiceService service = null;
		try {
			service = new PayPalAPIInterfaceServiceService(this.getClass().getResourceAsStream("/sdk_config.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		String token = request.getParameter("token");
		String payerID = request.getParameter("payerID");
		String paymentAction = request.getParameter("paymentAction");
		String currencyCode = request.getParameter("currencyCode");
		String notifyURL = request.getParameter("notifyURL");
		
		DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
		DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
		details.setToken(token);
		details.setPayerID(payerID);
		details.setPaymentAction(PaymentActionCodeType.fromValue(paymentAction));
		
		double itemTotalAmt = 0.00;
		double orderTotalAmt = 0.00;
		
		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
		lineItems = ShoppingCartUtils.getCartListForPayment(request.getSession());
		for (PaymentDetailsItemType item : lineItems) {
			int qtyItems = item.getQuantity();
			String amountItems = item.getAmount().getValue();
			itemTotalAmt += qtyItems * Double.parseDouble(amountItems);
			orderTotalAmt += itemTotalAmt;
		}
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(Double.toString(orderTotalAmt));
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCode));
		paymentDetails.setOrderTotal(orderTotal);

		BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.setValue(Double.toString(itemTotalAmt));
		itemTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCode));
		paymentDetails.setItemTotal(itemTotal);

		paymentDetails.setPaymentDetailsItem(lineItems);
		paymentDetails.setNotifyURL(notifyURL);
		
		List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
		payDetailType.add(paymentDetails);
		details.setPaymentDetails(payDetailType);

		doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
		DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
		doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);
		DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = null;
		try {
			doCheckoutPaymentResponseType = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
		} catch (SSLConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			e.printStackTrace();
		} catch (HttpErrorException e) {
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		if (doCheckoutPaymentResponseType != null) {
			if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				model.addAttribute("Ack", doCheckoutPaymentResponseType.getAck());
				Iterator<PaymentInfoType> iterator = doCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails()
														.getPaymentInfo().iterator();
				int index = 1;
				while (iterator.hasNext()) {
					PaymentInfoType result = (PaymentInfoType) iterator.next();
					model.addAttribute("Transaction ID" + index, result.getTransactionID());
					index++;
				}
				model.addAttribute("Billing Agreement ID",doCheckoutPaymentResponseType
															.getDoExpressCheckoutPaymentResponseDetails()
															.getBillingAgreementID());
			} else {
				model.addAttribute("Error", doCheckoutPaymentResponseType.getErrors());
			}
		}
		model.addAttribute("cartList", ShoppingCartUtils.getCartList(request.getSession()));
		ShoppingCartUtils.clearShoppingCart(request.getSession());
		return "confirmation";
	}
}
