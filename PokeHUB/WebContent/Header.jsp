<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Header.css" rel="stylesheet" type="text/css">
	</head>

<ul class="header2">
<li class="left"><form class="header2" action="search" method="get">
<label for="search" class="header3">PokeHub</label>
<input type="search" id="search" name="search"  placeholder="cerca"size="20"></form></li>
  <li class="left"> <form  class="header2"action="PaginaCatalogo.jsp"  method="post">
  <button class="header" type="submit">CATALOGO</button>
    </form></li>
  <%
UserBean utenteHeader = (UserBean) session.getAttribute("userID");
if ( (utenteHeader != null) ) {



    if( utenteHeader.getCategoriaUtente().equalsIgnoreCase("amministratore") ){
    	%>
    	<li class="left"><form action="admin" class="header2" method="post"><button class="header" type="submit">PAGINA ADMIN</button></form></li>
    	
    	<%
    }
    %>
   		<li class="left"><form action="cart" class="header2" method="post"><button class="header" type="submit">CARRELLO</button></form></li>
   		  <li class="destra"><form action="userLogged.jsp" class="header2" method="post"><input type="image" class="riduci" src="https://www.agenziadiecommerce.it/wp-content/uploads/2015/03/Utente.png" alt="profilo"></form></li>
<%    
} else {
%>
	<li class="left"><form action="LoginPage.jsp" class="header2" method="post"><button class="header" type="submit">LOGIN</button></form></li>
<%
}
%>
</ul>

