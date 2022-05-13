<% 
UserBean persona = (UserBean) session.getAttribute("userID");
if (persona == null)
{	
    response.sendRedirect("./LoginPage.jsp");
    return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,java.util.HashMap,java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
		<%
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			ProductBean prodotto;
			Integer quantita;
			Integer idProdotto;
			
			try {
				Iterator<Integer> iter = cart.iterator();
				while( iter.hasNext() ) {
					idProdotto = iter.next();
					prodotto = cart.findObject(idProdotto);
					quantita = cart.quantityObject(idProdotto);
			
			
				%>
					<h2>Prodotto : <%=prodotto.getNomeProdotto()%> Quantità: <%=quantita%> <a href="cart?addID=<%=prodotto.getIdProdotto()%>&quantity=0">Rimuovi</a></h2>
				<%
			
				}
				if(!cart.isEmpty()){
					%>
					<h2><a href="PreCheckuot">Acquista</a></h2>
					<%
				}
				
				
			} catch(Exception e) {
				%>
					<h2>Nessun prodotto disponibile</h2>
				<%
			}
			
		%>
	
	
</body>
</html>