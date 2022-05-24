<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% Collection<OrderBean> ordini= (Collection<OrderBean>)request.getSession().getAttribute("totaleOrdini");
	if(ordini == null)
	{
		response.sendRedirect("./OrderControl?action=all");
		return;
	}
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
	
	<form action="OrderControl" method="post">
		<input type="hidden" name="action" value="search">
		<h3>Data Inizio</h3>
		<input type="date" name="datai">
		<br>
		<h3>Data Fine</h3>
		<input type="date" name="dataf">
		<h3>Utente</h3>
		<input type="text" name="username" value ="*">
		<br><br>
		<input type="submit" value="Invia">
	</form>
	
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<td><b><a href="OrderControl?sort=id_ordine&action=all">ID ordine</a></b></td>
			
			<td><b><a href="OrderControl?sort=mail_cliente&action=all">Cliente</a></b></td>
			<td><b>Codice di tracking</b></td>
			<td><b><a href="OrderControl?sort=data_ordine&action=all">Data ordine</a></b></td>
			<td><b>Status consegna</b></td>
			<td><b>Indirizzo di spedizione</b></td>
			<td><b> Numero civico </b></td>
			<td><b> Cap </b> </td>
			<td> <b> Numero di telefono</b></td>
			
			
			
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
	
	
	<%@ include file="Footer.html" %>
	
		
</body>
</html>