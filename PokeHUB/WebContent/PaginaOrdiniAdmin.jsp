<%
UserBean utente = (UserBean) request.getSession().getAttribute("userID");

if(utente == null) {
	response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	return;
}
if(!utente.getCategoriaUtente().equalsIgnoreCase("Amministratore")){
	response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
	return;
}
%>

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
	<link href="CSS/OrdiniAdmin.css" rel="stylesheet" type="text/css">
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
	
	<table border="1" cellpadding="10" cellspacing="0" class="table">
		<tr>
			<td class="idOrdine"><b><a href="OrderControl?sort=id_ordine&action=all">ID ordine</a></b></td>
			<td class="cliente"><b><a href="OrderControl?sort=mail_cliente&action=all">Cliente</a></b></td>
			<td class="codTrack"><b>Codice di tracking</b></td>
			<td class="dataOrdine"><b><a href="OrderControl?sort=data_ordine&action=all">Data ordine</a></b></td>
			<td class="statusOrdine"><b>Status consegna</b></td>
			<td class="indirizzoSped"><b>Indirizzo di spedizione</b></td>
			<td class="numCivico"><b> Numero civico </b></td>
			<td class="cap"><b> Cap </b> </td>
			<td class="numTelefono"> <b> Numero di telefono</b></td>	
		</tr>
		
		
			<%
			if (ordini != null && ordini.size() != 0) {
				OrderBean ordine;
				Iterator<OrderBean> it = ordini.iterator();
				
				while(it.hasNext()){
					ordine=it.next();
					%>
					<tr>
					<td class="idOrdine"><a href="OrderUserControl?idOrdine=<%=ordine.getIdOrdine()%>"><%= ordine.getIdOrdine()  %></a>  </td>
					<td class="cliente"> <%= ordine.getMailCliente() %> </td>
					<td class="codTrack"> <%= ordine.getTrakingOrdine() %> </td>
					<td class="dataOrdine"> <%= ordine.getDataOrdine() %> </td>
					<td class="statusOrdine"> <%= ordine.getStato() %> </td>
					<td class="indirizzoSped"> <%= ordine.getVia() %> </td>
					<td class="numCivico"> <%= ordine.getCivico() %> </td>
					<td class="cap"> <%= ordine.getCap() %> </td>
					<td class="numTelefono"> <%= ordine.getTelefono() %> </td>
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

	<div class="content"></div>
	<div class="content"></div>
		
</body>
</html>