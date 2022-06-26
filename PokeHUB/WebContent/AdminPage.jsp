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

	<%
		DecimalFormat formatoPrezzo = new DecimalFormat();
		formatoPrezzo.setMaximumFractionDigits(2);
		formatoPrezzo.setMinimumFractionDigits(2);
	%>


	<h2 class="testoPaginaCatalogo">Inserisci prodotto:</h2>
	
	<form action="admin" method="post" enctype='multipart/form-data'>
	
		<input type="hidden" name="action" value="insert">
		
		<div id="formProdotti">
		
			<h2 class="labelFormField">Nome prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="bag-add-outline" size="large"></ion-icon></i>
				<input class="input-field" name="name" type="text" maxlength="50" required placeholder="Nome prodotto">
			</div>
			
			<h2 class="labelFormField">Prezzo senza IVA:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="cash-outline" size="large"></ion-icon></i>
				<input class="input-field" name="price" type="number" step="any" required placeholder="Prezzo senza IVA">
			</div>
			
			<h2 class="labelFormField">Percentuale IVA:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="calculator-outline" size="large"></ion-icon></i>
				<input class="input-field" name="iva" type="number" min="0" max="100" step="any" required placeholder="% IVA">
			</div>
			
			<h2 class="labelFormField">Descrizione prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="text-outline" size="large"></ion-icon></i>
				<textarea class="input-field" name="description" maxlength="2000" rows="3" required placeholder="Inserisci descrizione"></textarea>
			</div>
			
			<h2 class="labelFormField">Quantit&agrave; disponibile:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="apps-outline" size="large"></ion-icon></i>
				<input class="input-field" name="quantity" type="number" min="0" required placeholder="Quantit&agrave;">
			</div>
			
			<h2 class="labelFormField">Prodotto in vendita:</h2>
			<div class="input-container">
				<i class="icon showProductIcon"><ion-icon name="checkbox-outline" size="large"></ion-icon></i>
				<select class="input-field showProduct" name="showing" required onchange="changeIcon()">
	  				<option value="" disabled selected>Vendi prodotto</option> 
	  				<option value="true">Il prodotto &egrave; in vendita</option>
	  				<option value="false">Il prodotto NON &egrave; in vendita</option>
				</select>
			</div>
			
			<h2 class="labelFormField">Categoria prodotto:</h2>
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

			<h2 class="labelFormField">Foto prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="image-outline" size="large"></ion-icon></i>
				<input class="input-field" type="file" name="photo" accept="image/*" required/>
			</div>
			
			
			
			<input type="submit" value="Aggiungi" class="btnSearch">
		</div>
		
	</form>
	
	<hr>

	
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
			
			<td>€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%></td>
			<td>
			<%
				if(bean.isProdottoMostrato()){
					%>
					<a href="admin?action=delete&id=<%=bean.getIdProdotto()%>">Nascondi</a><br>
					<%
				} else {
					%>
					<a href="admin?action=show&id=<%=bean.getIdProdotto()%>">Mostra</a><br>
					<%
				}
			%>
				
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
	
	<hr>
	
	<h2 id="dettagliProdotto" class="testoPaginaCatalogo">Dettagli</h2>
	<table border="1" cellpadding="10" cellspacing="0" class="table">
		<tr>
			<th>IdProdotto</th>
			<th>Nome</th>
			<th>Prezzo</th>
			<th class="codTrack">Descrizione</th>
			<th class="statusOrdine">IVA</th>
			<th class="indirizzoSped">Prodotto in vendita</th>
			<th class="numCivico">CategoriaProdotto</th>
			<th class="cap">Quantità</th>
		</tr>
		<tr>
			<td class=" "><%=oggettoDettaglio.getIdProdotto()%></td>
			<td class=" "><%=oggettoDettaglio.getNomeProdotto()%></td>
			<td class=" ">€<%= formatoPrezzo.format(oggettoDettaglio.getPrezzoVetrina()) %></td>
			<td class="codTrack testoPaginaCatalogoDescrizione"><%=oggettoDettaglio.getDescrizione() %></td>
			<td class="statusOrdine"><%=oggettoDettaglio.getIva()%>%</td>
			<td class="indirizzoSped"><%=oggettoDettaglio.isProdottoMostrato()%></td>
			<td class="numCivico"><%=oggettoDettaglio.getCategoriaProdotto()%></td>
			<td class="cap"><%=oggettoDettaglio.getQuantita()%></td>
		</tr>
	</table>
	
	<hr>
	
	<h2 class="testoPaginaCatalogo">Modifica prodotto:</h2>
	<form action="admin?action=update&id=<%=oggettoDettaglio.getIdProdotto()%>" method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="action" value="insert">
		
		<div id="formProdotti">
		
			<h2 class="labelFormField">Nome prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="bag-add-outline" size="large"></ion-icon></i>
				<input class="input-field" name="name" type="text" maxlength="50" required value="<%=oggettoDettaglio.getNomeProdotto()%>">
			</div>
			
			<h2 class="labelFormField">Prezzo senza IVA:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="cash-outline" size="large"></ion-icon></i>
				<input class="input-field" name="price" type="number" step="any" required value="<%=oggettoDettaglio.getPrezzoVetrina()%>">
			</div>
			
			<h2 class="labelFormField">Percentuale IVA:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
				<input class="input-field" name="iva" type="number" min="1" max="100" step="any" required value="<%=oggettoDettaglio.getIva()%>">
			</div>
			
			<h2 class="labelFormField">Descrizione prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="text-outline" size="large"></ion-icon></i>
				<textarea class="input-field" name="description" maxlength="2000" rows="3" required placeholder="Inserisci descrizione"><%=oggettoDettaglio.getDescrizione()%></textarea>
			</div>
			
			<h2 class="labelFormField">Quantit&agrave; disponibile:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="apps-outline" size="large"></ion-icon></i>
				<input class="input-field" name="quantity" type="number" min="0" required placeholder="Quantit&agrave;" value="<%=oggettoDettaglio.getQuantita()%>">
			</div>
			
			<h2 class="labelFormField">Prodotto in vendita:</h2>
			<div class="input-container">
				<i class="icon" id="showProductIconFormUpdate"><ion-icon name="checkbox-outline" size="large"></ion-icon></i>
				<select class="input-field" id="showProductUpdateForm" name="showing" required onchange="changeIconUpdateForm()">
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
				<script>
					var falseTrue = $('#showProductUpdateForm option').filter(':selected').val();
					var isTrueSet = (falseTrue === 'true');
					
					
					if ( isTrueSet ) {
						$( "#showProductIconFormUpdate" ).html('<ion-icon name="eye-outline" size="large"></ion-icon>');
					} else {
						$( "#showProductIconFormUpdate" ).html('<ion-icon name="eye-off-outline" size="large"></ion-icon>');
					}
				</script>
				</select>
			</div>
			
			<h2 class="labelFormField">Categoria prodotto:</h2>
			<div class="input-container">
				<i class="icon"><ion-icon name="pricetag-outline" size="large"></ion-icon></i>
				<select class="input-field" name="category" required>
					<option value="" disabled>Seleziona categoria</option> 
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

			<h2 class="labelFormField">Foto prodotto:</h2>
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
	
	<script>
		
		function changeIcon() {
			var falseTrue = $('.showProduct option').filter(':selected').val();
			var isTrueSet = (falseTrue === 'true');
			
			
			if ( isTrueSet ) {
				$( ".showProductIcon" ).html('<ion-icon name="eye-outline" size="large"></ion-icon>');
			} else {
				$( ".showProductIcon" ).html('<ion-icon name="eye-off-outline" size="large"></ion-icon>');
			}
		}
		
		
		
		function changeIconUpdateForm() {
			var falseTrue = $('#showProductUpdateForm option').filter(':selected').val();
			var isTrueSet = (falseTrue === 'true');
			
			
			if ( isTrueSet ) {
				$( "#showProductIconFormUpdate" ).html('<ion-icon name="eye-outline" size="large"></ion-icon>');
			} else {
				$( "#showProductIconFormUpdate" ).html('<ion-icon name="eye-off-outline" size="large"></ion-icon>');
			}
		}
		
		
		
		
	</script>
	
	</body>
</html>