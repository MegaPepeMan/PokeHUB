<% 
UserBean persona = (UserBean) session.getAttribute("userID");
if (persona == null)
{	
    response.sendRedirect("/LoginPage.jsp");
    return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.text.DecimalFormat"%>

<!DOCTYPE html>
<html>

	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Pagina Carrello</title>
	<link href="CSS/paginaCarrello.css" rel="stylesheet" type="text/css">
</head>

<body>
	
      

     <%@ include file="Header.jsp" %> 


	 <%
	    Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		ProductBean prodotto;
		Integer quantita;
		Integer idProdotto;
	
		if ( !cart.isEmpty() ){
		
	 %>
     <div class="contenitoreGrande">
		
		
        <div class="sezioneCarrello">
            <h1>Carrello</h1>

			<%
		
			double totaleFattura=0;
	
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			formatoPrezzo.setMinimumFractionDigits(2);
		
			Iterator<Integer> iter = cart.iterator();
			while( iter.hasNext() ) {
				idProdotto = iter.next();
				prodotto = cart.findObject(idProdotto);
				quantita = cart.quantityObject(idProdotto);
			%>
            <div class="sezioneProdotto">
                <div class="sezioneImmagine">
                    <%
                    	if(prodotto.getImmagineProdotto() == null) {
					%>
                	<a href="object?id=<%=prodotto.getIdProdotto()%>">
						<img class="immagineProdotto" src="Image/noImage.png" alt="">
					</a>
					<%
						} else {
					%>
					<a href="object?id=<%=prodotto.getIdProdotto()%>">
						<img src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>" class="immagineProdotto" alt="">
					</a>
                            		
					<%
						}
					%>    
                </div>

                    
                <div class="sezioneDettaglioProdotto">
                    <p class="testoSezioneDettaglio"><a href="object?id=<%=prodotto.getIdProdotto()%>"><%=prodotto.getNomeProdotto()%></a></p>
                    
                    
                    <form action="cart" method="get" class="formSelect">
                        
						<input type="hidden" name="valueID" value="<%=prodotto.getIdProdotto()%>">
						<p class="testoSezioneDettaglio">Quantità:
                        <select name="quantity" class="selectQuantita" onchange="this.form.submit()">
                            <option value="0">0 (Rimuovi)</option>
	                        <%
	                            for(int i = 1 ; i <= prodotto.getQuantita(); i++) {
		                        	if(i ==  quantita.intValue()){ 
		                    %>
		           			<option selected="selected" value="<%=quantita %>"><%=quantita %></option>
		            		<%
									}  else {
							%>
							<option value="<%=i%>"><%=i%></option>
							<%
	                                }
								}
							%>
						</select>
						</p>
					</form>
					<p class="testoSezioneDettaglio"><button class="bottoneRimuovi"><a href="cart?valueID=<%=prodotto.getIdProdotto()%>&quantity=0"><ion-icon name="trash-outline" class="iconaSpazzatura"></ion-icon> Rimuovi</a></button></p>
                    <p class="testoSezioneDettaglio"><i>Prezzo con IVA:</i></p>
                    <%
						double percentualeIVA = prodotto.getIva()/100;
						double prezzoConIVA = (percentualeIVA * prodotto.getPrezzoVetrina() ) + prodotto.getPrezzoVetrina();
						double totaleProdotti = prezzoConIVA * quantita;
						totaleFattura += totaleProdotti;
					%>
                    <p class="testoSezioneDettaglio" style="font-weight: 800;">€<%=formatoPrezzo.format (totaleProdotti)%></p>

                </div>
            </div>
            
            <%
			}
            %>
        </div>
		
	






		<a href="PreCheckuot">
	        <div id="sezioneTotale">
	            <div>
	                <h1 class="testoSezioneTotale" style="text-align: center;">Totale da pagare: €<%=formatoPrezzo.format (totaleFattura)%></h1>
	            </div>
	
	            <hr class="divisorioTotaleOrdine">
	
	            <div>    
	                <h1 id="testoProcediPagamento" style="text-align: center; font-size: 20pt;">PROCEDI CON IL PAGAMENTO</h1>
	            </div>
	        </div>
		</a>
		

    </div>
    <div class="content"></div>
    <div class="content"></div>
       <%
		}
       %>   

	
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script> 


</body>
</html>