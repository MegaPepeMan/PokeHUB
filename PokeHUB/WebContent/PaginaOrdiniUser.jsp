<%
UserBean utente = (UserBean) request.getSession().getAttribute("userID");

if(utente == null) {
	response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	Collection<OrderBean> ordini= (Collection<OrderBean>)request.getSession().getAttribute("totaleOrdini");
	if(ordini == null)
	{
		response.sendRedirect("./OrderControl?action=all");
		return;
	}
	
	Map<Integer, Collection<ProductBean> > mappaProdotti = (Map<Integer, Collection<ProductBean> >)request.getSession().getAttribute("dettagliProdotti");
	Map<Integer, Double > mappaTotalePrezzi = (Map<Integer, Double>)request.getSession().getAttribute("totaleFattura");
%> 
		
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ordini Utente</title>
	<link href="CSS/ordiniUtente.css" rel="stylesheet" type="text/css">
</head>

<body>
	<%@ include file="Header.jsp" %>
	
	<form action="UserInvoiceControl" method="post">
		<input type="hidden" name="action" value="search">
		<h3>Data Inizio</h3>
		<input type="date" name="datai">
		<br>
		<h3>Data Fine</h3>
		<input type="date" name="dataf">
		<br><br>
		<input type="submit" value="Invia">
	</form>
	
		<%
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			if (ordini != null && ordini.size() != 0) {
				OrderBean ordine;
				Iterator<OrderBean> it = ordini.iterator();
				
				while(it.hasNext()){
					ordine=it.next();
					%>
					
					
					<div class="ordine">
				        <div class="identificativo">
				            <h3>Identificativo ordine: <a href="OrderUserControl?idOrdine=<%=ordine.getIdOrdine()%>"><%= ordine.getIdOrdine()  %></a></h3>
				        </div>
				
				        <div class="tracking">
				            <h3>Tracking: <%= ordine.getTrakingOrdine() %></h3>
				        </div>
				
				        <div class="dataOrdine">
				            <h3 class="dataOrdineEIcona"><i><ion-icon class="iconaPDF" name="document-outline" size="large"></ion-icon></i><%= ordine.getDataOrdine() %></h3>
				        </div>
				        
				        <div class="totaleOrdine">
				        	<div class="testoTotale">
				            	<h3 >Totale €<%= formatoPrezzo.format(mappaTotalePrezzi.get(ordine.getIdOrdine()) )%></h3>
				        	</div>
				        </div>
				
				
				        <div class="immaginiProdotti">
				            <%
								Collection<ProductBean> prodotti = mappaProdotti.get(ordine.getIdOrdine());
								System.out.println("Per l'ordine "+ ordine.getIdOrdine() +" i prodotti sono:" + prodotti);
								Iterator<ProductBean> foto = prodotti.iterator();
								ProductBean prodotto;
								int contatore = 0;
								while ( foto.hasNext() && contatore < 7) {
									prodotto = foto.next();
									%>
									<img class="immagineProdotto" src="data:image/png;base64,<%= prodotto.getImmagineProdotto() %>" alt="">
									<%
									contatore++;
									if (contatore > 6) {
										%>
											<img class="iconaPlus" alt="icona-piu" src="Image/plus-icon.png">
										<%
									}
								}
							%>
				            
				        </div>
				    
				    </div>
					
					
					
					
					
					
					
					
					
					
					
					<% 
					
				}
			}else {
				%>
				<tr>
					<td colspan="6">Nessun ordine disponibile</td>
				</tr>
				<% 
				
			}
	
			%>
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script> 
		
</body>
</html>