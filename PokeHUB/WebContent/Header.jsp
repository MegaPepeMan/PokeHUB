<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
<div align="right">
<h2><a href="PaginaCatalogo.jsp">Catalogo</a></h2>
<%
UserBean utenteHeader = (UserBean) session.getAttribute("userID");
if ( (utenteHeader != null) ) {
%>
    <h2><a href="userLogged.jsp"><%=utenteHeader.getNome() + " " + utenteHeader.getCognome()%></a></h2>
    <%
    if( utenteHeader.getCategoriaUtente().equalsIgnoreCase("amministratore") ){
    	%>
    	<h2><a href="admin">Pagina Admin</a></h2>
    	<%
    }
    %>
   		<h2><a href="cart">Carrello</a></h2>
<%    
} else {
%>
	<h2><a href="LoginPage.jsp">Login</a></h2>
<%
}
%>
<hr size="3" noshade color="black" width="100%">
</div>