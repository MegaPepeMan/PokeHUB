


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<% Collection<OrderBean> ordini= (Collection<OrderBean>)request.getSession().getAttribute("totaleOrdini");
			if(ordini == null)
			{
				response.sendRedirect("./OrderControl?action=visualizza");
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
	
	<form action="OrderControl">
	
	<input type="hidden" name="action" value="data">
	<h3>Data Inizio</h3>
	<input type="date" name="datai">
	<br>
	<h3>Data Fine</h3>
	<input type="date" name="dataf">
	<br><br>
	<center><input type="submit" value="Invia"></center>
	</form>
	
	<table>
		<tr>
			<th><h2><a href="OrderControl?sort=id_ordine&action=visualizza">ID ordine</a></h2></th>
			
			<th><h2><a href="OrderControl?sort=mail_cliente&action=visualizza">Cliente</a></h2></th>
			<th><h2>Codice di tracking</h2></th>
			<th><h2><a href="OrderControl?sort=data_ordine&action=visualizza">Data ordine</a></h2></th>
			<th><h2>Status consegna</h2></th>
			<th><h2>Indirizzo di spedizione</h2></th>
			
			
		</tr>
		
		
			<%
			if (ordini != null && ordini.size() != 0) {
				OrderBean ordine;
				Iterator<OrderBean> it = ordini.iterator();
				
				while(it.hasNext()){
					ordine=it.next();
					%>
					<tr>
					<td> <%= ordine.getIdOrdine()  %> </td>
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
			}
			%>
			 
			
	
		
		
		
		
		<tr>
			<td colspan="6">Nessun ordine disponibile</td>
		</tr>
		
	</table>
	
	
		
</body>
</html>