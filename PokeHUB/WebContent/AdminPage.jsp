<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	Collection<?> categories = (Collection<?>) request.getAttribute("categories");
	if(products == null) {
		response.sendRedirect("./admin");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
%>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Catalogo Admin</title>
</head>

<body>
<%@ include file="Header.jsp" %>
	<br>
	<br>
	<a href="PaginaOrdiniAdmin.jsp"><input type="button" value="Ordini Admin"></a>
	<h2>Prodotti:</h2>
	<a href="admin">List</a>
	<table border="1">
		<tr>
			<th>Nome Prodotto</th>
			<th>Immagine</th>
			<th>Prezzo con IVA</th>
			<th>Azioni</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					
		%>
		<tr>
			<td><a href="object?id=<%=bean.getIdProdotto()%>"><%=bean.getNomeProdotto()%></a></td>
			<td><%=bean.getIdProdotto()%></td>
			<%
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			%>
			<td>€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%></td>
			<td><a href="admin?action=delete&id=<%=bean.getIdProdotto()%>">Cancella</a><br>
				<a href="admin?action=read&id=<%=bean.getIdProdotto()%>">Dettagli</a><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
		
	</table>
	
	<% 
		ProductBean oggettoDettaglio = (ProductBean) request.getAttribute("IDproduct");	
		if (oggettoDettaglio != null) {
		
			System.out.println("Oggetto trovato, caricamento" + oggettoDettaglio.getNomeProdotto() + "dettaglio in corso...");
	
	%>
	
	<h2>Dettagli</h2>
	<table border="1">
		<tr>
			<th>IdProdotto</th>
			<th>Nome</th>
			<th>Prezzo</th>
			<th>Descrizione</th>
			<th>IVA</th>
			<th>ProdottoMostrato</th>
			<th>CategoriaProdotto</th>
			<th>Quantità</th>
			
		</tr>
		<tr>
			<td><%=oggettoDettaglio.getIdProdotto()%></td>
			<td><%=oggettoDettaglio.getNomeProdotto()%></td>
			<td><%=oggettoDettaglio.getPrezzoVetrina()%></td>
			<td><%=oggettoDettaglio.getDescrizione() %></td>
			<td><%=oggettoDettaglio.getIva()%></td>
			<td><%=oggettoDettaglio.isProdottoMostrato()%></td>
			<td><%=oggettoDettaglio.getCategoriaProdotto()%></td>
			<td><%=oggettoDettaglio.getQuantita()%></td>
		</tr>
	</table>
	
	<h2>Modifica prodotto:</h2>
	<form action="admin?action=update&id=<%=oggettoDettaglio.getIdProdotto()%>" method="post">
		
		<label for="name">Nome Prodotto:</label><br> 
		<input name="name" type="text" maxlength="50" required value="<%=oggettoDettaglio.getNomeProdotto()%>"><br> 
		
		<label for="price">Prezzo senza IVA:</label><br> 
		<input name="price" type="number" min="1" step="any" required value="<%=oggettoDettaglio.getPrezzoVetrina()%>"><br>
		
		<label for="iva">% IVA:</label><br> 
		<input name="iva" type="number" min="1" max="100" step="any" required value="<%=oggettoDettaglio.getIva()%>"><br>
		
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="2000" rows="3" required><%=oggettoDettaglio.getDescrizione()%></textarea><br>

		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="<%=oggettoDettaglio.getQuantita()%>" required><br>
		
		<label for="showing" >Prodotto mostrato:</label><br> 
		<select name="showing" required>
  			<option value="true">Si</option>
  			<option value="false">No</option>
		</select><br>
		
		<label for="category">Categoria:</label><br>
		<select name="category" required>
  			
  		
  			<%
			if (categories != null && categories.size() != 0) {
				Iterator<?> it = categories.iterator();
				
				while (it.hasNext()) {
					CategoryBean bean = (CategoryBean) it.next();
					
		%>
		<option value="<%=bean.getNomeCategoria()%>"><%=bean.getNomeCategoria()%></option>
		<%
				}
			}
		%>
		</select><br>
		<br><br>
		<input type="submit" value="Aggiorna"><input type="reset" value="Reset">
		
	</form>
	
	
	
	<%
			
		}
		else {
			System.out.println("Oggetto non trovato");
		}
	%>
	
	<h2>Inserisci prodotto:</h2>
	
	<form action="admin" method="post" enctype='multipart/form-data'>
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Nome Prodotto:</label><br> 
		<input name="name" type="text" maxlength="50" required placeholder="inserisci nome"><br> 
		
		<label for="price">Prezzo senza IVA:</label><br> 
		<input name="price" type="number" min="1" step="any" required><br>
		
		<label for="iva">% IVA:</label><br> 
		<input name="iva" type="number" min="1" max="100" step="any"><br>
		
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="2000" rows="3" required placeholder="inserisci descrizione"></textarea><br>

		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		
		<label for="showing">Prodotto mostrato:</label><br> 
		<select name="showing" required>
  			<option value="true">Si</option>
  			<option value="false">No</option>
		</select><br>
		
		<label for="category">Categoria:</label><br>
		<select name="category" required>
  			
  		
  			<%
			if (categories != null && categories.size() != 0) {
				Iterator<?> it = categories.iterator();
				
				while (it.hasNext()) {
					CategoryBean bean = (CategoryBean) it.next();
					
		%>
		<option value="<%=bean.getNomeCategoria()%>"><%=bean.getNomeCategoria()%></option>
		<%
				}
			}
		%>
		</select><br>
		
		<label>Immagine prodotto: </label> 
     	<input type="file" name="photo" accept="image/jpeg" />
		
		
		<br><br>
		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">
		
	</form>
	<%@ include file="Footer.html" %>
	</body>
</html>