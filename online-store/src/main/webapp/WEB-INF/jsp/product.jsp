<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Online Store Demo</title>
</head>
<body>
	<h1>Product List</h1>
	<form name="paypalForm" action="<c:url value='/payment/do-payment.do'/>" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Item Name</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>x</th>
			</tr>
			<c:forEach items = "${productList}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.description}</td>
					<td>${item.quantity}</td>
					<td>${item.price}</td>
					<td><a href="<c:url value='/shoppingcart/add-item.do'/>?id=${item.id}">ADD TO CART</a></td>
          		</tr>
          	</c:forEach>
		</table>
		
		<br><br>
		
		<a href="<c:url value="/shoppingcart/view.do" />">Go to Shopping Cart</a>
	</form>
	
</body>
</html>