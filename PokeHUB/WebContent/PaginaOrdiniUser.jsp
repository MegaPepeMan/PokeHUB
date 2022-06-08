<%
UserBean utente = (UserBean) request.getSession().getAttribute("userID");

if(utente == null) {
	response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	Collection<OrderBean> ordini= (Collection<OrderBean>)request.getSession().getAttribute("totaleOrdini");
	if(ordini == null)
	{
		response.sendRedirect("./OrderControl?action=all");
		return;
	}
	
	Map<Integer, Collection<ProductBean> > mappaProdotti = (Map<Integer, Collection<ProductBean> >)request.getSession().getAttribute("dettagliProdotti");
%> 
		
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ordini Admin</title>
</head>

<body>
	<%@ include file="Header.jsp" %>
	
	<form action="UserInvoiceControl" method="post">
		<input type="hidden" name="action" value="search">
		<h3>Data Inizio</h3>
		<input type="date" name="datai">
		<br>
		<h3>Data Fine</h3>
		<input type="date" name="dataf">
		<br><br>
		<input type="submit" value="Invia">
	</form>
	
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<td><b>ID ordine</b></td>
			
			<td><b>Cliente</b></td>
			<td><b>Codice di tracking</b></td>
			<td><b>Data ordine</b></td>
			<td><b>Status consegna</b></td>
			<td><b>Indirizzo di spedizione</b></td>
			<td><b> Numero civico </b></td>
			<td><b> Cap </b> </td>
			<td><b> Numero di telefono</b></td>
			
			
			
		</tr>
		
		
			<%
			if (ordini != null && ordini.size() != 0) {
				OrderBean ordine;
				Iterator<OrderBean> it = ordini.iterator();
				
				while(it.hasNext()){
					ordine=it.next();
					%>
					<tr>
					<td><a href="OrderUserControl?idOrdine=<%=ordine.getIdOrdine()%>"><%= ordine.getIdOrdine()  %></a>  </td>
					<td> <%= ordine.getMailCliente() %> </td>
					<td> <%= ordine.getTrakingOrdine() %> </td>
					<td> <%= ordine.getDataOrdine() %> </td>
					<td> <%= ordine.getStato() %> </td>
					<td> <%= ordine.getVia() %> </td>
					<td> <%= ordine.getCivico() %> </td>
					<td> <%= ordine.getCap() %> </td>
					<td> <%= ordine.getTelefono() %> </td>
					<%
					Collection<ProductBean> prodotti = mappaProdotti.get(ordine.getIdOrdine());
					System.out.println("Per l'ordine "+ ordine.getIdOrdine() +" i prodotti sono:" + prodotti);
					Iterator<ProductBean> foto = prodotti.iterator();
					ProductBean prodotto;
					while ( foto.hasNext() ) {
						prodotto = foto.next();
						%>
						<td> <img src="data:image/png;base64,<%= prodotto.getImmagineProdotto() %>" alt="immagine non presente"/ width="20px" height="20px"> </td>
						<%
					}
					%>
					
					
					</tr> 
					<% 
					
				}
			}else {
				%>
				<tr>
					<td colspan="6">Nessun ordine disponibile</td>
				</tr>
				<% 
				
			}
	
			%>
		
	
		
	</table>
	
	
		
</body>
</html>