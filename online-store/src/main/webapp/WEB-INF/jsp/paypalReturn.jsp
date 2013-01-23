<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>paypal payment confirmation</title>
</head>
<body>
	<h1>PAYPAL PAYMENT CONFIRMATION</h1>
	
	<form name="paypalForm" action="<c:url value='/payment/confirm-payment.do'/>" method="post">
		
		<input type="hidden" name="token" value="${token}" />
		<input type="hidden" name="payerID" value="${payerID}" />
		<input type="hidden" name="paymentAction" value="${paymentAction}" />
		<input type="hidden" name="currencyCode" value="${currencyCode}" />
		<input type="hidden" name="notifyURL" value="${notifyURL}" />
		
		<table border="1">
			<tr>
				<th>No</th>
				<th>Item Name</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
			<c:forEach items = "${cartList}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.description}</td>
					<td>${item.quantity}</td>
					<td>${item.amount.value}</td>
          		</tr>
          	</c:forEach>
		</table>
		
		<input type="submit" value="Confirm Payment" />
	</form>
</body>
</html>