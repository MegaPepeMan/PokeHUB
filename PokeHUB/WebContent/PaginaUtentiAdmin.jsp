<%
UserBean utenteAdmin = (UserBean) request.getSession().getAttribute("userID");

if(utenteAdmin == null) {
	response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	return;
}
if(!utenteAdmin.getCategoriaUtente().equalsIgnoreCase("Amministratore")){
	response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
	return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% Collection<UserBean> utenti = (Collection<UserBean>)request.getSession().getAttribute("utenti");
	if(utenti == null)
	{
		response.sendRedirect("./AdminUserControl");
		return;
	}
%> 
		
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat,java.time.LocalDate"%>
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Gestione Utenti</title>
	<link href="CSS/OrdiniAdmin.css" rel="stylesheet" type="text/css">
</head>

<body>
	<%@ include file="Header.jsp" %>
	
	<div class="content"></div>
	<div class="content"></div>
	<h3 style="text-align: center;">Ricerca per Utente</h3>
	
	
	<form action="AdminUserControl" method="get">
		<input type="hidden" name="action" value="search">
		
		<div id="ricercaDataUtente">
			
			<div class="input-container">
	            <i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
	            <input class="input-field" type="text" value="*" name="cercaUtente">
	        </div>
	        
        </div>
        
		<input type="submit" value="Cerca" class="btnSearch">
	</form>
	
	<div class="content"></div>
	
	<hr>
	
	<h3 style="text-align: center;">Utenti trovati</h3>
	<table border="1" cellpadding="10" cellspacing="0" class="table">
	<tbody>
		<tr>
			<td class="idOrdine"><b>Mail</b></td>
			<td class="cliente"><b>Cellulare</b></td>
			<td class="codTrack"><b>Nome</b></td>
			<td class="dataOrdine"><b>Cognome</b></td>
			<td class="statusOrdine"><b>Categoria Utente</b></td>	
		</tr>
		
		
			<%
			if (utenti != null && utenti.size() != 0) {
				System.out.println("Utenti dalla JSP: "+utenti.toString());
				UserBean utente;
				Iterator<UserBean> it = utenti.iterator();
				
				while(it.hasNext()){
					utente=it.next();
					%>
					<tr class="recordOrdini">
						<td class="idOrdine"><a class="linkTabella" href="OrderControl?action=search&datai=1990-1-1&dataf=<%= LocalDate.now() %>&username=<%= utente.getMail() %>"><%= utente.getMail() %></a></td>
						<td class="cliente"> <%= utente.getCellulare() %> </td>
						<td class="codTrack"> <%= utente.getNome() %> </td>
						<td class="dataOrdine"> <%= utente.getCognome() %> </td>
						
						<td class="statusOrdine">
						<form action="AdminUserControl" method="get">
							<input type="hidden" name="utente" value="<%= utente.getMail() %>">
							<select class="" name="categoria">
								<option value="<%= utente.getCategoriaUtente() %>">
									<%= utente.getCategoriaUtente() %>
								</option>
								<%
										if(utente.getCategoriaUtente().equalsIgnoreCase("Cliente") ) {
											%>
												<option value="Amministratore">Amministratore</option>
											<%
										} else {
											%>
												<option value="Cliente">Cliente</option>
											<%
										}
									%>
							</select>
							<input type="submit" value="Aggiorna">
						</form>
						</td>
						
					</tr> 
					<% 
					
				}
			}else {
				%>
					<tr><td colspan="5">Nessun utente trovato</td></tr>
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