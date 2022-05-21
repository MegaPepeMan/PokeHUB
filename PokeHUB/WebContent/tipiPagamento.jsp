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
<title>Metodi di pagamento</title>
</head>
<%@ include file="Header.jsp" %>
	
	<h1>Metodi di pagamento</h1>
		<%
			Collection<PaymentBean> pagamenti = (Collection<PaymentBean>) request.getSession().getAttribute("PaymentMethod");
			System.out.println(pagamenti);
			PaymentBean sistema;
			
				Iterator<PaymentBean> iter = pagamenti.iterator();
				int i = 0;
				while( iter.hasNext() ) {
					sistema = iter.next();
					i++;
			
				%>
					<h1>Pagamento <%=i%></h1> 
					<h2>Intestatario: <%=sistema.getIntestatarioCarta()%></h2> 
					<h2>Numero Carta: <%=sistema.getNumeroCarta() %></h2>
					<h2><a href="PaymentControl?action=delete&id=<%= sistema.getNumeroCarta()%>">Cancella</a></h2>
				<%
			
				}

				%>
				
		<h2>Inserisci carta:</h2>
				
		<form action="PaymentControl?action=insert" method="post">
		
		<label for="numeroCarta">Numero Carta:</label><br> 
		<input name="numeroCarta" type="text" maxlength="16" required placeholder="numero carta"><br> 
		
		<label for="intestatarioCarta">Intestario:</label><br> 
		<input name="intestatarioCarta" type="text" maxlength="50" required placeholder="nome intestatario"><br>
		
		<label for="cvv">CVV:</label><br> 
		<input name="cvv" type="text" maxlength="3" required placeholder="cvv"><br>
		
		<label for="mese">Mese:</label><br>
		<select name="mese">
 			<option value="1">Gennaio</option>
  			<option value="2">Febbraio</option>
  			<option value="3">Marzo</option>
  			<option value="4">Aprile</option>
  			<option value="5">Maggio</option>
  			<option value="6">Giugno</option>
  			<option value="7">Luglio</option>
  			<option value="8">Agosto</option>
  			<option value="9">Settembre</option>
  			<option value="10">Ottobre</option>
  			<option value="11">Novembre</option>
  			<option value="12">Dicembre</option>
		</select>
		
		<br>
		
		<label for="anno">Anno:</label><br> 
		<input name ="anno" type="number" min="2022" max="2099" step="1" value="2022" />
		
		<br><br>
		
		<input type="submit" value="Aggiungi carta">
				
		</form>
				
				<%@ include file="Footer.html" %>
				
	
	
</body>
</html>