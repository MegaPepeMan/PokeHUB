<% 
UserBean persona = (UserBean) session.getAttribute("userID");
if (persona == null)
{	
    response.sendRedirect("./LoginPage.jsp");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Pagina Utente</title>
	<link href="CSS/PaginaUtente.css" rel="stylesheet" type="text/css">
</head>

<body id="userLogged">
	<%@ include file="Header.jsp" %>

	<h2 class="welcome">Bentornato/a,</h2>
	<h2 class="welcome"><%=persona.getNome()%> <%=persona.getCognome()%></h2>
	
	<div class="cards">
	
		<a href="UserInvoiceControl">
			<div class="card">
				<ion-icon class="iconFunction" name="document-text-outline" size="large"></ion-icon><p>Ordini</p>
			</div>
		</a>
			
		<a href="ShipmentControl">	
			<div class="card">
				<ion-icon class="iconFunction" name="paper-plane-outline" size="large"></ion-icon><p>Indirizzi</p>
			</div>
		</a>
		
		<a href="PaymentControl">
			<div class="card">
				<ion-icon class="iconFunction" name="card-outline" size="large"></ion-icon><p>Pagamenti</p>
			</div>
		</a>
		<% if(persona.getCategoriaUtente().equalsIgnoreCase("amministratore") ){
		
			%>
		<a href="OrderControl">
			<div class="card">
				<ion-icon class="iconFunction" name="documents-outline" size="large"></ion-icon><p>Gestione Ordini</p>
			</div>
		</a>
		
		<a href="admin">
			<div class="card">
				<ion-icon class="iconFunction" name="grid-outline" size="large"></ion-icon><p>Gestione Catalogo</p>
			</div>
		</a>
		
		<a href="AdminUserControl">
			<div class="card">
				<ion-icon class="iconFunction" name="people-outline" size="large"></ion-icon><p>Gestione Utenti</p>
			</div>
		</a>
		
		<% 
			}
		%>
		
		<a href="/">
			<div class="card">
				<ion-icon name="star-half-outline" size="large"></ion-icon><p>Valutazione Prodotti</p>
			</div>
		</a>
		
		<a href="/">
			<div class="card">
				<ion-icon class="iconFunction" name="cog-outline" size="large"></ion-icon><p>Impostazioni Account</p>
			</div>
		</a>
		
		<a href="Logout">
			<div class="cardLogout">
				<ion-icon class="iconFunction" name="log-out-outline" size="large"></ion-icon><p>Esci</p>
			</div>
		</a>
	
	
	</div>
	
	<div class="content"></div>
	<div class="content"></div>
	<div class="content"></div>

	
	
	
	
	

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>