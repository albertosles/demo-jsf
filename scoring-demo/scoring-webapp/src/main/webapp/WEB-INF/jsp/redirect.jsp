<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- just for  redirect testing --%>
<% response.sendRedirect("http://www.google.com"); %>
<%-- 
	<% response.sendRedirect("http://UpopServername:8080/upopPayment/..."); %>
--%> 	
	

