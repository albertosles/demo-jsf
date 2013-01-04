<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript">
window.onload = function() {
	document.pay_form.submit();
}
</script>

</head>
<body>
	<form id="pay_form" name="pay_form" action="http://127.0.0.1:8080/UpopWeb/api/Pay.action" method="post">
		<input type="hidden" name="acqCode" id="acqCode" value="" />
		<input type="hidden" name="backEndUrl" id="backEndUrl" value="http://172.17.136.36:8080/UpopApi/payResServlet" />
		<input type="hidden" name="charset" id="charset" value="UTF-8" />
		<input type="hidden" name="commodityDiscount" id="commodityDiscount" value="0" />
		<input type="hidden" name="commodityName" id="commodityName" value="Article name such as short sleeve" />
		<input type="hidden" name="commodityQuantity" id="commodityQuantity" value="1" />
		<input type="hidden" name="commodityUnitPrice" id="commodityUnitPrice" value="10000" />
		<input type="hidden" name="commodityUrl" id="commodityUrl" value="http://218.80.192.2231/shop1/payment/quickpay/quickpay_result.php?payid=123456&dd=123" />
		<input type="hidden" name="customerIp" id="customerIp" value="127.0.0.1" />
		<input type="hidden" name="customerName" id="customerName" value="Zhang San" />
		<input type="hidden" name="defaultBankNumber" id="defaultBankNumber" value="" />
		<input type="hidden" name="defaultPayType" id="defaultPayType" value="" />
		<input type="hidden" name="frontEndUrl" id="frontEndUrl" value="http://172.17.136.36:8080/UpopApi/payResServlet" />
		<input type="hidden" name="merAbbr" id="merAbbr" value="Name of user shop" />
		<input type="hidden" name="merCode" id="merCode" value="" />
		<input type="hidden" name="merId" id="merId" value="105550149170027" />
		<input type="hidden" name="merReserved" id="merReserved" value="" />
		<input type="hidden" name="orderAmount" id="orderAmount" value="11000" />
		<input type="hidden" name="orderCurrency" id="orderCurrency" value="156" />
		<input type="hidden" name="orderNumber" id="orderNumber" value="234567" />
		<input type="hidden" name="orderTime" id="orderTime" value="20110223194505" />
		<input type="hidden" name="origQid" id="origQid" value="" />
		<input type="hidden" name="transTimeout" id="transTimeout" value="120000" />
		<input type="hidden" name="transType" id="transType" value="01" />
		<input type="hidden" name="transferFee" id="transferFee" value="1000" />
		<input type="hidden" name="version" id="version" value="1.0.0" />
		<input type="hidden" name="signature" id="signature" value="3df25f9617297984206cd75c4a47762d">
		<input type="hidden" name="signMethod" id="signMethod" value="MD5" />
	</form>

</body>
</html>