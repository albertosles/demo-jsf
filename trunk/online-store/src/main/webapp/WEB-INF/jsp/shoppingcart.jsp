<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pay through PayPal: www.TestAccount.com</title>
</head>
<body>
	<h1>This is a shopping cart</h1>
	<form name="paypalForm" action="<c:url value='/payment/do-payment.do'/>" method="post">
		
		
		
		<c:if test="${not empty errorList}">
			<c:forEach items="${errorList}" var="error">
				<table border="1">
					<tr>
						<td>${error.errorCode}</td>
						<td>${error.shortMessage}</td>
						<td>${error.longMessage}</td>
	          		</tr>
	          	</table>
	         </c:forEach>
		</c:if>
		
		<table border="1">
			<tr>
				<th>No</th>
				<th>Item Name</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Total</th>
				<th>X</th>
			</tr>
			<c:forEach items = "${cartList01}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.description}</td>
					<td>${item.quantity}</td>
					<td>${item.price}</td>
					<td>${item.price * item.quantity}</td>
					<td><a href="<c:url value='/shoppingcart/remove-item.do'/>?id=${item.id}">REMOVE</a></td>
          		</tr>
          	</c:forEach>
		</table>
		<br>
		<table border="1">
			<tr>
				<td>Buyer Email</td>
				<td>
					<input type="text" name="buyerEmail" />
				</td>
          	</tr>
			<tr>
				<td>Payment Action</td>
				<td>
					<select name="paymentAction">
						<option value="None">None</option>
						<option value="Sale">Sale</option>
						<option value="Order">Order</option>
						<option value="Authorization">Authorization</option>
					</select>
				</td>
          	</tr>
          	<tr>
				<td>Currency</td>
				<td>
					<select name="currencyCode">
						<option value="USD">USD</option>
						<option value="EUR">EURO</option>
					</select>
				</td>
          	</tr>
		</table>
		<br>
		<input type="submit" value="Check out" />
		<br><br>
		<a href="<c:url value='/product/browse.do'/>">Continue Shopping</a>
		
	</form>
	
	
	
</body>
</html>