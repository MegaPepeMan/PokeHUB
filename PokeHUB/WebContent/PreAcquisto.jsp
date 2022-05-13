<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conferma dati</title>
</head>
<body>
<%@ include file="Header.jsp" %>
	<form action="">
		<label for="creditCard">Carta:</label><br>
		<select name="creditCard" required>
  			
  		
  			<%
  			Collection<?> pagamenti = (Collection<?>) request.getSession().getAttribute("pagamentiUtente");
			if (pagamenti != null && pagamenti.size() != 0) {
				Iterator<?> it = pagamenti.iterator();
				
				while (it.hasNext()) {
					PaymentBean bean = (PaymentBean) it.next();
					
		%>
		<option value="<%=bean.getNumeroCarta()%>"><%=bean.getNumeroCarta() %></option>
		<%
				}
			}
		%>
		</select><br>
	</form>
	
	
	
</body>
</html>