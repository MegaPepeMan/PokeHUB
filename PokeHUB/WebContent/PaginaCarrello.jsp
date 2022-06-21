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

        <table class="sezioneCarrello">
            
            <tr>
                <th>CARRELLO</th>
            </tr>
		<%
		
		double totaleFattura=0;
	
		DecimalFormat formatoPrezzo = new DecimalFormat();
		formatoPrezzo.setMaximumFractionDigits(2);
		
		Iterator<Integer> iter = cart.iterator();
		while( iter.hasNext() ) {
			idProdotto = iter.next();
			prodotto = cart.findObject(idProdotto);
			quantita = cart.quantityObject(idProdotto);
			%>
			<tr>
                <th>
                    <table class="sezioneProdotto">
                        <tr>
                            <th class="cellaTabellaImmagine">
                                <img src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>" class="immagineProdotto" alt="">
                            </th>
                            <th>
                                <table class="sezioneDettaglioProdotto">
                                    <tr>
                                        <th><%=prodotto.getNomeProdotto()%></th>
                                    </tr>
                                    <tr>
                                        <th>Quantità: 
                                        <form action="cart" method="get">
                                        	<input type="hidden" name="valueID" value="<%=prodotto.getIdProdotto()%>">
	                                        <select class="selezionaQuantita" name="quantity" id="" min="1" value="1" onchange="this.form.submit()">
	                                            <option value="0">0</option>
	                                            <%for(int i = 1 ; i <= prodotto.getQuantita(); i++) {
		                                            	if(i ==  quantita.intValue()){ 
		                                            		%>
		                                            			<option selected="selected" value="<%=quantita %>"><%=quantita %></option>
		                                            		<%
	                                            		} else if(prodotto.getQuantita() ==  quantita.intValue()){
	                                            			i++;
	                                            			%>
	                                            				<option selected="selected" value="<%=quantita %>"><%=quantita %></option>
	                                            			<%
	                                            		} else {
	                                            			%>
                                            					<option value="<%=i%>"><%=i%></option>
                                            				<%
	                                            		}
	                                            	}
	                                            %>
	                                        </select>
                                        </form>
                                        </th>
                                    </tr>
                                    <tr>
                                    <%
										double percentualeIVA = prodotto.getIva()/100;
										double prezzoConIVA = (percentualeIVA * prodotto.getPrezzoVetrina() ) + prodotto.getPrezzoVetrina();
										double totaleProdotti = prezzoConIVA * quantita;
										totaleFattura += totaleProdotti;
									%>
                                        <th>Prezzo con IVA: €<%=formatoPrezzo.format (totaleProdotti)%></th>
                                    </tr>
                                </table>
                            </th>
                        </tr>
                    </table>
                </th>
            </tr>
			<%
		}
		%>
		
	
            



            
        </table>

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
		
		
		<%
		}
   	 	%>
    
    
        
    
    
            

        

        

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script> 


</body>
</html>