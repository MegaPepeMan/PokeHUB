<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fattura Ordine</title>
</head>
<body>
	<%
		Collection<CompositionBean> fattura = (Collection<CompositionBean>) request.getSession().getAttribute("fattura");
		fattura.toString();
	%>
	
	<%=fattura.toString()%>
</body>
</html>