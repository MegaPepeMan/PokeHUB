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
	
	Collection<?> categories = (Collection<?>) request.getAttribute("categories");
	
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
	<div class="content"></div>
	<div class="categorie">
		<a href="product" class="linkCategorie">Home </a>
		<p class="separatoreLink"> | </p>	
	<%
		int contatoreSeparatore = 0;
		Iterator<?> iterCategorie = categories.iterator();
		while(iterCategorie.hasNext() ) {
			CategoryBean categoria = (CategoryBean) iterCategorie.next();
			%>
			<a href="product?categoria=<%=categoria.getNomeCategoria()%>" class="linkCategorie"><%=categoria.getNomeCategoria()%></a>
			<%
			contatoreSeparatore++;
			if (contatoreSeparatore < categories.size()) {
				%>
				<p class="separatoreLink"> | </p>
				<%	
			}
		}
	%>
	</div>

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
				                <div class="aggiungiCarrello">
				                    <div>
				                        <ion-icon class="iconaCarrelloPulsante" name="cart-outline" size="large"></ion-icon>
				                    </div>
				                    
				                    <div class="testoAggiungiCarrello" style="">AGGIUNGI AL CARRELLO</div>
				                </div>
				           	</a>
			        		<%
			        	} else {
			        		%>
	                    
				                <div class="prodottoEsaurito" style="">
				                    <div>
				                        <ion-icon class="iconaCarrelloPulsante" name="close-outline" size="large"></ion-icon>
				                    </div>
				                    
				                    <div class="testoNonDisponibile" style=" ">
				                    	NON DISPONIBILE
				                    </div>
				                </div>
			        		
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
	
	<%@ include file="Footer.html" %>
</html>