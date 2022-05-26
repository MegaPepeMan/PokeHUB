<%
	UserBean utente = (UserBean) request.getSession().getAttribute("userID");

	

	if(utente == null) {
		response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		return;
	}
	
	OrderBean ordine = (OrderBean) request.getSession().getAttribute("ordine");
	Collection<CompositionBean> fattura = null;
	Collection<ProductBean> prodotti = null;

	fattura = (Collection<CompositionBean>) request.getSession().getAttribute("fattura");
	prodotti = (Collection<ProductBean>) request.getSession().getAttribute("prodotti");	

	if(fattura == null || prodotti == null) {
		System.out.println("Redirect dal primo userLogged.jsp");
		response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
		return;
	}
	
	Iterator<CompositionBean> iterControllo = fattura.iterator();
	CompositionBean controlloComposizione;
	while (iterControllo.hasNext()){
		controlloComposizione = iterControllo.next();
		Boolean valore = utente.getCategoriaUtente().equalsIgnoreCase("Amministratore");
		valore.toString();
		if ( !ordine.getMailCliente().equalsIgnoreCase(utente.getMail()) ){
			if(!utente.getCategoriaUtente().equalsIgnoreCase("Amministratore")){
				response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
				return;
			}
			
		}
	}
	
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<!DOCTYPE html>
<html>

<%@ include file="Header.jsp" %>

<style>
table, th, td {
  border:1px solid black;
}
</style>
<head>
<meta charset="UTF-8">
<title>Fattura Ordine</title>
</head>
<body>

<%
	double totaleFattura=0;
%>

<table>
	<tr>
	<th><h3><b>Immagine</b></h3></th>
	<th><h3><b>Identificativo ordine</b></h3></th>
	<th><h3><b>Identificativo Prodotto</b></h3>
	<th><h3><b>Iva Acquisto</b></h3>
	<th><h3><b>Prezzo Acquisto</b></h3>
	<th><h3><b>Quantità</b></h3>
	<th><h3><b>Prezzo dei pezzi</b></h3>
	
	</tr>
	
	
	<% if(fattura != null && fattura.size()>0){
		
		Iterator<ProductBean> itImmagini=prodotti.iterator();
		Iterator<CompositionBean> itComposizioni=fattura.iterator();
		
		
		CompositionBean composizione= new CompositionBean();
		ProductBean prodotto=new ProductBean();
		while(itImmagini.hasNext() && itComposizioni.hasNext()){
			prodotto=itImmagini.next();
			composizione=itComposizioni.next();
			%>
			<tr>
			<th>
			<img src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>" alt="immagine non presente"/ width="50px" height="50px">
			</th>
			<th>
			<h3><%= composizione.getIdentificativo_ordine() %></h3>
			</th>
			<th>
			<h3><%= composizione.getIdentificativo_prodotto() %></h3>
			</th>
			<th>
			<h3><%= composizione.getIva_acquisto() %></h3>
			</th>
			<th>
			<h3><%= composizione.getPrezzo_acquisto() %></h3>
			</th>
			<th>
			<h3><%= composizione.getQuantita() %></h3>
			</th>
			<th>
			<h3>€<%= composizione.getQuantita()*composizione.getPrezzo_acquisto()*composizione.getIva_acquisto() %></h3>
			</th>
			</tr>
			
			<%
			 totaleFattura += composizione.getQuantita()*composizione.getPrezzo_acquisto()*composizione.getIva_acquisto();
		}
		
	}
		
		%>
	<tr>
	<th><h2>Totale Fattura</h2></th>
	<th>
	<h2><b>€<%=totaleFattura %></b></h2>
	</th>
	</tr>
</table>	
</body>
</html>