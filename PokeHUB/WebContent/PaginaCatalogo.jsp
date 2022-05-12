<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.getSession().getAttribute("");
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Catalogo DB</title>
</head>

<body>
<%@ include file="Header.jsp" %>
	<h2>Prodotti:</h2>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Nome Prodotto <a href="product?sort=nome_prodotto">Ordina</a></th>
			<th>Immagine</th>
			<th>Prezzo con IVA <a href="product?sort=categoria_prodotto">Ordina</a></th>
		</tr>
		<%
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					if(bean.isProdottoMostrato()){
		%>
		<tr>
			<td><a href="object?id=<%=bean.getIdProdotto()%>"><%=bean.getNomeProdotto()%></a></td>
			<td><img src="data:image/png;base64,<%=bean.getImmagineProdotto()%>" width="500px" height="500px"/></td>
			<td>€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%></td>
		</tr>
		<%
					}
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	</body>
</html>