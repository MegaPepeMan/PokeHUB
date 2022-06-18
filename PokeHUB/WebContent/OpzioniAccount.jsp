<%
	UserBean utente = (UserBean) request.getSession().getAttribute("userID");
	
	if(utente == null) {
		response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		return;
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="CSS/OpzioniAccount.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Opzioni Account</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	<h1>
		<%= utente.getNome() %> <button id="modificaNome">Modifica</button>
	</h1>
	
	<h1>
		<%= utente.getCognome() %> <button id="modificaCognome">Modifica</button>
	</h1>
	
	<h1>
		<%= utente.getMail() %> <button id="modificaMail">Modifica</button>
	</h1>
	
	<h1>
		<%= utente.getCellulare() %> <button id="modificaCellulare">Modifica</button>
	</h1>
	
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	
	
	<script src="JavaScript/SweetAlert2/sweetalert2.all.min.js" type="text/javascript"></script>
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>
	
	<script>
		$('#modificaNome').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuovo nome</h1><form action="AccountControl" method="post" id=""><div class="input-container"><i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getNome() %>" name="nuovoNome"></div><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaCognome').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuovo cognome</h1><form action="AccountControl" method="get" id=""><div class="input-container"><i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getCognome() %>" name="nuovoCognome"></div><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaMail').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuova mail</h1><form action="AccountControl" method="get" id=""><div class="input-container"><i class="icon"><ion-icon name="mail-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getMail() %>" name="nuovoMail"></div><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaCellulare').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci un nuovo numero</h1><form action="AccountControl" method="get" id=""><div class="input-container"><i class="icon"><ion-icon name="call-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getCellulare() %>" name="nuovoCellulare"></div><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
	</script>
	
</body>
</html>