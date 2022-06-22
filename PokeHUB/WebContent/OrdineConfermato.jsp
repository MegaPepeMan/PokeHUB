<%
	boolean ordineCompletato = (boolean) request.getSession().getAttribute("ordineConfermato");
	if(!ordineCompletato) {
		response.sendRedirect(request.getContextPath()+"/product");
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordine confermato</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%
	request.getSession().setAttribute("ordineConfermato",false);
	OrderBean ordine = (OrderBean) request.getSession().getAttribute("ultimoOrdine");
	UserBean persona = (UserBean) request.getSession().getAttribute("userID");
%>
	<h1 style="text-align: center;">Ordine confermato</h1>
	<h3 style="text-align: center;">L'ordine <%=ordine.getIdOrdine()%> &eacute; stato confermato</h3>
	<h3 style="text-align: center;">L'ordine verr&aacute; spedito a <%=persona.getNome() %> <%=persona.getCognome() %> <%=ordine.getVia()%> <%=ordine.getCivico() %> <%=ordine.getCap() %></h3>
	<h3 style="text-align: center;">Clicca <a href="product">qui</a> per tornare alla Home</h3>
</body>
</html>