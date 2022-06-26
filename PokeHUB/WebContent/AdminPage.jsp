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
	<link href="CSS/OrdiniAdmin.css" rel="stylesheet" type="text/css">
	<title>Catalogo Admin</title>
</head>

<body>
<%@ include file="Header.jsp" %>


	<h2 class="testoPaginaCatalogo">Inserisci prodotto:</h2>
	
	<form action="admin" method="post" enctype='multipart/form-data'>
	
		<input type="hidden" name="action" value="insert">
		
		<div id="formProdotti">
		
			<div class="input-container">
				<i class="icon"><ion-icon name="bag-add-outline" size="large"></ion-icon></i>
				<input class="input-field" name="name" type="text" maxlength="50" required placeholder="Nome prodotto">
			</div>
			
			
			<div class="input-container">
				<i class="icon"><ion-icon name="cash-outline" size="large"></ion-icon></i>
				<input class="input-field" name="price" type="number" step="any" required placeholder="Prezzo senza IVA">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
				<input class="input-field" name="iva" type="number" min="1" max="100" step="any" required placeholder="% IVA">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="text-outline" size="large"></ion-icon></i>
				<textarea class="input-field" name="description" maxlength="2000" rows="3" required placeholder="Inserisci descrizione"></textarea>
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="apps-outline" size="large"></ion-icon></i>
				<input class="input-field" name="quantity" type="number" min="0" required placeholder="Quantit&agrave;">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="checkbox-outline" size="large"></ion-icon></i>
				<select class="input-field" name="showing" required>
	  				<option value="true">Il prodotto &egrave; in vendita</option>
	  				<option value="false">Il prodotto NON &egrave; in vendita</option>
				</select>
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="pricetag-outline" size="large"></ion-icon></i>
				<select class="input-field" name="category" required>
					<option value="" disabled selected>Seleziona categoria</option> 
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
				</select>
			</div>


			<div class="input-container">
				<i class="icon"><ion-icon name="image-outline" size="large"></ion-icon></i>
				<input class="input-field" type="file" name="photo" accept="image/*" required/>
			</div>
			
			
			
			<input type="submit" value="Aggiungi" class="btnSearch">
		</div>
		
	</form>

	
	<h2 class="testoPaginaCatalogo">Prodotti:</h2>
	<table border="1" cellpadding="10" cellspacing="0" class="table">
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
			<td><a href="admin?action=delete&id=<%=bean.getIdProdotto()%>">Nascondi</a><br>
				<a href="admin?action=read&id=<%=bean.getIdProdotto()%>#dettagliProdotto" id="aggiornaProdotto">Dettagli</a><br>
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
	
	<h2 id="dettagliProdotto" class="testoPaginaCatalogo">Dettagli</h2>
	<table border="1" cellpadding="10" cellspacing="0" class="table">
		<tr>
			<th>IdProdotto</th>
			<th>Nome</th>
			<th>Prezzo</th>
			<th class="codTrack">Descrizione</th>
			<th class="statusOrdine">IVA</th>
			<th class="indirizzoSped">ProdottoMostrato</th>
			<th class="numCivico">CategoriaProdotto</th>
			<th class="cap">Quantità</th>
		</tr>
		<tr>
			<td class=" "><%=oggettoDettaglio.getIdProdotto()%></td>
			<td class=" "><%=oggettoDettaglio.getNomeProdotto()%></td>
			<td class=" "><%=oggettoDettaglio.getPrezzoVetrina()%></td>
			<td class="codTrack testoPaginaCatalogoDescrizione"><%=oggettoDettaglio.getDescrizione() %></td>
			<td class="statusOrdine"><%=oggettoDettaglio.getIva()%></td>
			<td class="indirizzoSped"><%=oggettoDettaglio.isProdottoMostrato()%></td>
			<td class="numCivico"><%=oggettoDettaglio.getCategoriaProdotto()%></td>
			<td class="cap"><%=oggettoDettaglio.getQuantita()%></td>
		</tr>
	</table>
	
	<h2 class="testoPaginaCatalogo">Modifica prodotto:</h2>
	<form action="admin?action=update&id=<%=oggettoDettaglio.getIdProdotto()%>" method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="action" value="insert">
		
		<div id="formProdotti">
		
			<div class="input-container">
				<i class="icon"><ion-icon name="bag-add-outline" size="large"></ion-icon></i>
				<input class="input-field" name="name" type="text" maxlength="50" required value="<%=oggettoDettaglio.getNomeProdotto()%>">
			</div>
			
			
			<div class="input-container">
				<i class="icon"><ion-icon name="cash-outline" size="large"></ion-icon></i>
				<input class="input-field" name="price" type="number" step="any" required value="<%=oggettoDettaglio.getPrezzoVetrina()%>">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
				<input class="input-field" name="iva" type="number" min="1" max="100" step="any" required value="<%=oggettoDettaglio.getIva()%>">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="text-outline" size="large"></ion-icon></i>
				<textarea class="input-field" name="description" maxlength="2000" rows="3" required placeholder="Inserisci descrizione"><%=oggettoDettaglio.getDescrizione()%></textarea>
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="apps-outline" size="large"></ion-icon></i>
				<input class="input-field" name="quantity" type="number" min="0" required placeholder="Quantit&agrave;" value="<%=oggettoDettaglio.getQuantita()%>">
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="checkbox-outline" size="large"></ion-icon></i>
				<select class="input-field" name="showing" required>
				<%
				if( oggettoDettaglio.isProdottoMostrato() ) {
					%>
					<option value="true" selected>Il prodotto &egrave; in vendita</option>
	  				<option value="false">Il prodotto NON &egrave; in vendita</option>	
					<%
				} else {
					%>
					<option value="true">Il prodotto &egrave; in vendita</option>
	  				<option value="false" selected>Il prodotto NON &egrave; in vendita</option>	
					<%
				}
				%>
				</select>
			</div>
			
			<div class="input-container">
				<i class="icon"><ion-icon name="pricetag-outline" size="large"></ion-icon></i>
				<select class="input-field" name="category" required>
					<option value="" disabled selected>Seleziona categoria</option> 
	  				<%
						if (categories != null && categories.size() != 0) {
						Iterator<?> it = categories.iterator();
							
						while (it.hasNext()) {
							CategoryBean bean = (CategoryBean) it.next();
							if( bean.getNomeCategoria().equalsIgnoreCase( oggettoDettaglio.getCategoriaProdotto() ) ) {
									%>
									<option value="<%=bean.getNomeCategoria()%>" selected><%=bean.getNomeCategoria()%></option>
									<%			
								} else {
									%>
									<option value="<%=bean.getNomeCategoria()%>"><%=bean.getNomeCategoria()%></option>
									<%
								}
					
							}
						}
					%>
				</select>
			</div>


			<div class="input-container">
				<i class="icon"><ion-icon name="image-outline" size="large"></ion-icon></i>
				<input class="input-field" type="file" name="photo" accept="image/*" />
			</div>
			
			
			
			<input type="submit" value="Aggiorna" class="btnSearch">
		</div>		
	</form>
	
	
	
	<%
			
		}
		else {
			System.out.println("Oggetto non trovato");
		}
	%>
	<div class="content"></div>
	</body>
</html>