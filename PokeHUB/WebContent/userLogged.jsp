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
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*"%>

<head>
<meta charset="UTF-8">
<title>Utente loggato</title>
</head>

<body>
<%@ include file="Header.jsp" %>

	
	<%
	String nome = persona.getNome();
	String cognome = persona.getCognome();
	%>
	
	<h1>Sei un utente loggato</h1>
	<h2>Bentornato <%=nome%> <%=cognome%></h2>
	<h2>Sei amministratore?</h2>
	<% 
	if(persona.getCategoriaUtente().equalsIgnoreCase("amministratore") ){
		%>
		<h2>Si</h2>
		<a href="OrderControl">Pagina degli ordini</a>
		<%
	} else {
		%>
		<h2>No</h2>
		<%	
	}
	%>
	<a href="PaymentControl">Metodi di pagamento</a>
	<a href="ShipmentControl">Indirizzi di spedizione</a>
	<form action="Logout" method="get" > 
    <input type="submit" value="Logout"/>
    </form>
   
    <%@ include file="Footer.html" %>
</body>
</html>