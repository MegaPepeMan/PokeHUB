<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Header.css" rel="stylesheet" type="text/css">
	</head>

<ul>
<li class="sinistra"><form action="search" method="get">
<label for="search" class="structural">PokeHub</label>
<input type="search" id="search" name="search"  placeholder="cerca"size="20"></form></li>
  <li class="sinistra"> <form action="PaginaCatalogo.jsp"  method="post">
  <button class="header" type="submit">Catalogo</button>
    </form></li>
  <%
UserBean utenteHeader = (UserBean) session.getAttribute("userID");
if ( (utenteHeader != null) ) {



    if( utenteHeader.getCategoriaUtente().equalsIgnoreCase("amministratore") ){
    	%>
    	<li class="sinistra"><form action="admin"  method="post"><button class="header" type="submit">Pagina Admin</button></form></li>
    	<%
    }
    %>
   		<li class="sinistra"><form action="cart"  method="post"><button class="header" type="submit">Carrello</button></form></li>
   		  <li class="right"><form action="userLogged.jsp" method="post"><button class="header"  type="submit"><img  class ="fufu" src="https://www.agenziadiecommerce.it/wp-content/uploads/2015/03/Utente.png"> </button></form></li>
<%    
} else {
%>
	<li class="sinistra"><form action="LoginPage.jsp" method="post"><button class="header" type="submit">Login</button></form></li>
<%
}
%>
</ul>

