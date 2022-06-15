<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.getSession().getAttribute("");
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
%>

<!DOCTYPE html>
<html>
<%@ include file="Header.jsp" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Catalogo.css" rel="stylesheet" type="text/css">
	<title>Catalogo DB</title>
</head>

<body class="catalogo">

	<%
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			formatoPrezzo.setMinimumFractionDigits(2) ;
			if (products != null && products.size() != 0) {
				
								
	%>

	<div class="cards">
        
        <%
        
	        Iterator<?> it = products.iterator();
			
			while (it.hasNext()) {
				ProductBean bean = (ProductBean) it.next();
				if(bean.isProdottoMostrato()){
					%>					
					<div class="card">
					<a href="object?id=<%=bean.getIdProdotto()%>">
					<% if(bean.getImmagineProdotto() == null) { 
						%>
							<img class="immagineProdotto" src="Image/noImage.png" alt="">
						<% 
					} else {
						%>
							<img class="immagineProdotto" src="data:image/png;base64,<%=bean.getImmagineProdotto()%>" alt="">
						<%
					}
					%>
			           <h1 class="nomeProdotto"><%=bean.getNomeProdotto()%></h1> 
			           <h1 class="prezzoProdotto">€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%></h1>
			        </a>
			        <%
			        	if(bean.getQuantita() >= 1) {
			        		%>
			        		<a href="cart?addID=<%=bean.getIdProdotto()%>&quantity=1">
				                <button class="aggiungiCarrello">
				                    <span>
				                        <ion-icon class="iconaCarrelloPulsante" name="cart-outline" size="large"></ion-icon>
				                    </span>
				                    
				                    <span class="testoAggiungiCarrello">
				                        AGGIUNGI AL CARRELLO
				                    </span>  
				                </button>
				           	</a>
			        		<%
			        	} else {
			        		%>
			        		
			        		<button class="prodottoEsaurito">
				                    <span>
				                        <ion-icon class="iconaProdottoEsaurito" name="close-outline" size="large"></ion-icon>
				                    </span>
				                    
				                    <span class="testoNonDisponibile">
				             			NON DISPONIBILE
				                    </span>
				            </button>
			        		
			        		<%
			        	}
			        %>
			           
			        </div>
					<%    
				}
			}
        %>
        
        
        
        
        
       
        
    </div>		

	<%
			}
	%>
	

	

	
	</body>
</html>