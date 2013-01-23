<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pay through PayPal: www.TestAccount.com</title>
</head>
<body>
	<form name="paypalForm"
		action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
		<input type="hidden" name="cmd" value="_xclick" />
		<input type="hidden" name="business" value="seller_1357811932_biz_api1.yahoo.com" />
		<input type="hidden" name="password" value="1357811947" />
		<input type="hidden" name="custom" value="1123" />
		<input type="hidden" name="item_name" value="Computer-Laptop" />
		<input type="hidden" name="amount" value="30" />
		<input type="hidden" name="rm" value="1" />
		<input type="hidden" name="return" value="http://localhost:8080/scoring-webapp/pages/store/paypalResponse.jsp" />
		<input type="hidden" name="cancel_return" value="http://localhost:8080/scoring-webapp/pages/store/paypalResponseCancel.jsp" />
		<input type="hidden" name="cert_id" value="A86Qmg2Ug.XhqD.OHR8V48ytaT5sAz.AURuwVtvClQhwU.egXSAVnxWy" />
		<input type="submit" value="Check out" />
	</form>
	
	====================================
	
	<h2>Button Payment</h2>
	<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
		<input type="hidden" name="cmd" value="_s-xclick">
		<input type="hidden" name="hosted_button_id" value="JLH85QDX9AVDE">
		<input type="image" src="https://www.sandbox.paypal.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
		<img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
	</form>

	====================================
	
	<h2>TEST PAYPAL API CALL</h2>
	
	<form name="paypalForm"
		action="https://api-3t.sandbox.paypal.com/nvp" method="post">
		<input type="hidden" name="USER" value="seller_1357811932_biz_api1.yahoo.com" />
		<input type="hidden" name="PWD" value="1357811947" />
		<input type="hidden" name="SIGNATURE" value="A86Qmg2Ug.XhqD.OHR8V48ytaT5sAz.AURuwVtvClQhwU.egXSAVnxWy" />
		<input type="hidden" name="METHOD" value="SetExpressCheckout" />
		<input type="hidden" name="VERSION" value="78" />
		<input type="hidden" name="AMT" value="10" />
		<input type="hidden" name="cancelUrl" value="http://localhost:8080/scoring-webapp/pages/store/paypalResponseCancel.jsp" />
		<input type="hidden" name="returnUrl" value="http://localhost:8080/scoring-webapp/pages/store/paypalResponse.jsp" />
		<input type="submit" value="Check api call" />
	</form>
	
</body>
</html>