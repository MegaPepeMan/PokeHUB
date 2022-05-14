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
<%@ include file="Header.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="oggetto.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<title><%=prodotto.getNomeProdotto()%></title>
</head>
<body>

<ul class="testi">
<li class="left"><h3><%=prodotto.getNomeProdotto()%></h3><%
	if(prodotto.getImmagineProdotto() != null){
	%>
		
		<img class="riduci img-rounded" src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>"/>
	<%		
	}
	%></li>
	<li class="left">   <h3 align="center"><%=prodotto.getNomeProdotto()%></h3><br>
	<h3><%=prodotto.getDescrizione()%></h3>
	<h3><%=prodotto.getIdProdotto()%></h3>
	
	<%
	DecimalFormat formatoPrezzo = new DecimalFormat();
	formatoPrezzo.setMaximumFractionDigits(2);
	%>
	<h3>€<%=formatoPrezzo.format((prodotto.getPrezzoVetrina()/100)*prodotto.getIva() + prodotto.getPrezzoVetrina())%></h3>
	<h3><%=prodotto.getCategoriaProdotto()%></h3>  </li>
</ul>
	
	
	<br><br><br>
	
	<form action="cart" method="post">
		<input type="hidden" name="addID" value="<%=prodotto.getIdProdotto()%>">
		
		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		<input type="submit" value="Aggiungi">
	</form>	

<%@ include file="footer.html" %>
	
</body>
</html>