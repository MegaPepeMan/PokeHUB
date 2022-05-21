<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ include file="Header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Conferma dati</title>
</head>

<body>


		<label for="creditCard">Carta:</label><br>
		
  			
  		
  			
		<h2>Inserisci carta:</h2>
				
		
		<form action="PreCheckuot?action=insertPayment" method="post">
		
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
			
		<br><br><br><br>
		
		
		
		
		<label for="shipmentAddress">Indirizzo:</label><br>
  			
  		
  			
		
		<h2>Inserisci indirizzo:</h2>
				
		<form action="PreCheckuot?action=insertAddress" method="post">
		
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
		
		<br><br><br><br>
	
		<form action="CheckuotControl" method="post">
		<%
	  			Collection<?> pagamenti = (Collection<?>) request.getSession().getAttribute("pagamentiUtente");
				if (pagamenti != null && pagamenti.size() != 0) {
					%>
					<select name="creditCard" required>
					<%
					Iterator<?> it = pagamenti.iterator();
					
					while (it.hasNext()) {
						PaymentBean bean = (PaymentBean) it.next();
						String carta = "Carta che termina con "+bean.getNumeroCarta().substring( bean.getNumeroCarta().length()-4 , bean.getNumeroCarta().length() );
						
			%>
			<option value="<%=bean.getNumeroCarta()%>"><%=carta%></option>
			<%
					}
					%>
					</select><br>
					<%
				}
			%>
	
		
		<%
  			Collection<?> indirizzi = (Collection<?>) request.getSession().getAttribute("indirizziUtente");
			if (indirizzi != null && indirizzi.size() != 0) {
				%>
				<select name="shipmentAddress" required>
				<%
				Iterator<?> it = indirizzi.iterator();
				
				while (it.hasNext()) {
					AddressBean bean = (AddressBean) it.next();
					
		%>
		<option value="<%=bean.getIdIndirizzo()%>"><%=bean.getVia()%> <%=bean.getCivico()%> <%=bean.getCap()%></option>
		<%
				}
				%>
				</select><br>
				<%
			}
		%>
		<input type="submit" value="Paga ora">
		</form>
		<%@ include file="Footer.html" %>
</body>
</html>