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
<title>Indirizzi di spedizione</title>
</head>
<%@ include file="Header.jsp" %>
	
	<h1>Metodi di pagamento</h1>
		<%
			Collection<AddressBean> indirizzi = (Collection<AddressBean>) request.getSession().getAttribute("ShipmentMethod");
			System.out.println(indirizzi);
			AddressBean sistema;
			
				Iterator<AddressBean> iter = indirizzi.iterator();
				int i = 0;
				while( iter.hasNext() ) {
					sistema = iter.next();
					i++;
			
				%>
					<h1>Indirizzo <%=i%></h1> 
					<h2>Via: <%=sistema.getVia() %></h2> 
					<h2>Numero Civico: <%=sistema.getCivico() %></h2>
					<h2>CAP: <%=sistema.getCap()%></h2>
					<h2>Telefono: <%=sistema.getTelefono()%></h2>
					
					<h2><a href="ShipmentControl?action=delete&id=<%=sistema.getIdIndirizzo()%>">Cancella</a></h2>
				<%
			
				}

				%>
				
		<h2>Inserisci indirizzo:</h2>
				
		<form action="ShipmentControl?action=insert" method="post">
		
		<label for="via">Via/Piazza/Vicinale:</label><br> 
		<input name="via" type="text" maxlength="50" required placeholder="Via Roma"><br> 
		
		<label for="civico">Civico:</label><br> 
		<input name="civico" type="text" maxlength="50" required placeholder="123"><br>
		
		<label for="cap">CAP:</label><br> 
		<input name="cap" type="text" maxlength="50" required placeholder="00100"><br>
		
		<label for="telefono">Recapito telefonico:</label><br> 
		<input name="telefono" type="text" maxlength="50" required placeholder="1234567890"><br>
		
		<br><br>
		
		<input type="submit" value="Aggiungi indirizzo">
				
		</form>
				
				
				
	
	
</body>
</html>