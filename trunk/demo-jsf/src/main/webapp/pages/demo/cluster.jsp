<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clustering Test</title>
</head>
<body>
	<% 
		Integer sessionValue = (Integer)session.getAttribute("sessionValue");
		if(sessionValue == null){
			sessionValue = new Integer(1);
		} else {
			sessionValue = new Integer(sessionValue.intValue() + 1);
		}
		session.setAttribute("sessionValue", sessionValue);
	%>
	<h2>Session Value: <%=sessionValue.intValue()%></h2>
	<h2>Session ID: <%=session.getId()%></h2>
</body>
</html>