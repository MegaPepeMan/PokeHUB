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

<%
	DecimalFormat formatoPrezzo = new DecimalFormat();
	formatoPrezzo.setMaximumFractionDigits(2);
	formatoPrezzo.setMinimumFractionDigits(2);
	Collection<OrderBean> ordini= (Collection<OrderBean>)request.getSession().getAttribute("totaleOrdini");
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
	
	<div class="content"></div>
	<div class="content"></div>
	<h3 style="text-align: center;">Ricerca per Data & Utente</h3>
	
	
	<form action="OrderControl" method="post">
		<input type="hidden" name="action" value="search">
		
		<div id="ricercaDataUtente">
			
			<div class="input-container">
	            <i class="icon"><ion-icon name="calendar-clear-outline" size="large"></ion-icon></i>
	            <input class="input-field" type="date" placeholder="Data Inizio" name="datai">
	        </div>
	
			
			<div class="input-container">
	            <i class="icon"><ion-icon name="calendar-outline" size="large"></ion-icon></i>
	            <input class="input-field" type="date" placeholder="Data Inizio" name="dataf">
	        </div>
			
			
			<div class="input-container">
	            <i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
	            <input class="input-field" type="text" value="*" name="username">
	        </div>
	        
        </div>
        
		<input type="submit" value="Cerca" class="btnSearch">
	</form>
	
	<div class="content"></div>
	
	<hr>
	
	<h3 style="text-align: center;">Ordini trovati</h3>
	<table border="1" cellpadding="10" cellspacing="0" class="table">
	<tbody>
		<tr>
			<td class="idOrdine"><b><a class="linkTabella" href="OrderControl?sort=id_ordine&action=all">ID ordine</a></b></td>
			<td class="cliente"><b><a class="linkTabella" href="OrderControl?sort=mail_cliente&action=all">Cliente</a></b></td>
			<td class="codTrack"><b>Codice di tracking</b></td>
			<td class="dataOrdine"><b><a class="linkTabella" href="OrderControl?sort=data_ordine&action=all">Data ordine</a></b></td>
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
					<tr class="recordOrdini">
						<td class="idOrdine"><a class="linkTabella" href="PDFInvoiceControl?idOrdine=<%=ordine.getIdOrdine()%>"><%= ordine.getIdOrdine()  %></a>  </td>
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
					<tr><td colspan="9">Nessun ordine trovato</td></tr>
				<% 
				
			}
	
			%>
		
	
	</tbody>	
	</table>

	<div class="content"></div>
	<div class="content"></div>
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
</body>
</html>