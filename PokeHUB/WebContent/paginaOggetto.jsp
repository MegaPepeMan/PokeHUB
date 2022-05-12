<% 
ProductBean prodotto = (ProductBean) request.getAttribute("IDproduct");
if (prodotto == null)
{	
    response.sendRedirect("./product");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
<head>
<meta charset="UTF-8">
<title><%=prodotto.getNomeProdotto()%></title>
</head>
<body>
<%@ include file="Header.jsp" %>
	<h2 align="center"><%=prodotto.getNomeProdotto()%></h2>
	<h2 align="center"><%=prodotto.getDescrizione()%></h2>
	<h2 align="center"><%=prodotto.getIdProdotto()%></h2>
	<h2 align="center"><%=prodotto.getNomeProdotto()%></h2>
	<%
	DecimalFormat formatoPrezzo = new DecimalFormat();
	formatoPrezzo.setMaximumFractionDigits(2);
	%>
	<h2 align="center">€<%=formatoPrezzo.format((prodotto.getPrezzoVetrina()/100)*prodotto.getIva() + prodotto.getPrezzoVetrina())%></h2>
	<h2 align="center"><%=prodotto.getCategoriaProdotto()%></h2>
	<%
	if(prodotto.getImmagineProdotto() != null){
	%>
		<h2 align="center">Immagine:</h2>
		<img src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>"/>
	<%		
	}
	%>
	<br><br><br>
	
	<form action="cart" method="post">
		<input type="hidden" name="addID" value="<%=prodotto.getIdProdotto()%>">
		
		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		<input type="submit">
	</form>	
	
</body>
</html>