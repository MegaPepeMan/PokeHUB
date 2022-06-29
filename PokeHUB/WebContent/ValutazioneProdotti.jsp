<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Valuta Prodotti</title>
</head>

<body>
<%@ include file="Header.jsp" %>
	
	<form action="RatingControl" method="post">
		<select name="prodotto" required>
		<%
			Map<Integer,ProductBean> prodottiRecensione = null;
			prodottiRecensione = (Map<Integer,ProductBean>) request.getSession().getAttribute("prodottiRecensione");
			Set<Integer> setProdotti =prodottiRecensione.keySet();
			Iterator<Integer> it = setProdotti.iterator();
			while(it.hasNext()) {
				int keyProdotto = it.next();
				ProductBean prodotto = prodottiRecensione.get(keyProdotto);
				%>
					<option value="<%=prodotto.getIdProdotto()%>"><%=prodotto.getNomeProdotto()%></option>
				<%
			}
		%>
		</select>
		
		<input type="radio" name="rating" value="1">
	  	<label>1</label>
	  	<input type="radio" name="rating" value="2">
	  	<label>2</label>
	  	<input type="radio" name="rating" value="3">
	 	<label>3</label>
		<input type="radio" name="rating" value="4">
	  	<label>4</label>
		<input type="radio" name="rating" value="5">
	  	<label>5</label><br>
	
		<button type="submit">Inserisci</button>
	</form>

</body>
</html>