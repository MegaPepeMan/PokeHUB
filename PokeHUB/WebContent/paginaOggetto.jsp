<% 
ProductBean prodotto = (ProductBean) request.getAttribute("IDproduct");
double valutazione = (double) request.getAttribute("valutazione");
if (prodotto == null)
{	
    response.sendRedirect("./product");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
<%@ include file="Header.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="CSS/oggetto.css" rel="stylesheet" type="text/css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title><%=prodotto.getNomeProdotto()%></title>
</head>


<body>

<%
	DecimalFormat formatoPrezzo = new DecimalFormat();
	formatoPrezzo.setMaximumFractionDigits(2);
	formatoPrezzo.setMinimumFractionDigits(2);
%>
	<div class="content"></div>

 	<div class="containerDettagli">
        <div class="sezioneFoto">
        	<%
	            if(prodotto.getImmagineProdotto() == null) {
	            	%>
	            		<img src="Image/noImage.png" alt="" class="fotoProdottoAssente">
	            	<%	
	            } else {
	            	%>
	        			<img src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>" alt="" class="fotoProdotto">
	            	<%
	            }
            %>
            
        </div>
        
        <div class="sezioneTesto">
            <div class="nomeProdotto"><%=prodotto.getNomeProdotto()%></div>
            <div class="categoriaProdotto">Categoria: <%=prodotto.getCategoriaProdotto()%></div>
            <div class="prezzoProdotto">€<%=formatoPrezzo.format((prodotto.getPrezzoVetrina()/100)*prodotto.getIva() + prodotto.getPrezzoVetrina())%></div>
            <div class="sezioneStelleRecensioni">
            <%
            	if(valutazione == 0) {
           		%>
           		<div class="nessunaValutazione">Nessuna valutazione</div>
           		<%
            	}
            %>
            	
                <img src="Image/stars.png" alt="" class="immagineStelle">
                <div class="sfondoStelle"></div>
            </div>
            <div class="descrizioneProdotto">Descrizione:<br><br><%=prodotto.getDescrizione()%></div>
            
            <div class="sezioneFormPulsante">
            <%
	            if(prodotto.getQuantita() == 0) {
	            	%>
	            		<button class="pulsanteNonDisponibile"><ion-icon class="iconaPulsanteCarrello" name="close-outline" size="large"></ion-icon>NON DISPONIBILE</button>
	            	<%	
	            } else {
	            	%>
            		<form action="cart" method="post">
			        	<input type="hidden" name="addID" value="<%=prodotto.getIdProdotto()%>">
			        	<input name="quantity" class="quantitaProdotto" type="number" value="1" min="1"  max="<%=prodotto.getQuantita()%>" required>
			        	<button type="submit" class="pulsanteCarrello"><ion-icon class="iconaPulsanteCarrello" name="cart-outline" size="large"></ion-icon>AGGIUNGI AL CARRELLO</button>  
			        </form>
	            	<%
	            }
            %>
            
		        
		        
		        
			</div>
        </div>
        
    </div>
	
	<div class="content"></div>
	
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>
	<script>
		var valutazioneDB = <%=valutazione%>;
		// valutazioneDB : 5 = y px : 200px
		var pixelColorati = (valutazioneDB * 200) / 5;
		$(".sfondoStelle").css("width", pixelColorati+"px");
	</script>
	
</body>
</html>