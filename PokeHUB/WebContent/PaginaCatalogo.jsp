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
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Catalogo.css" rel="stylesheet" type="text/css">
	<title>Catalogo DB</title>
</head>

<body>

	<h2>Prodotti:</h2>
	<a href="product">List</a>
	<table>
		<tr>
			<th><h2>Nome Prodotto <a href="product?sort=nome_prodotto"></h2>Ordina</a></th>
			<th ><h2>Immagine</h2></th>
			<th><h2>Prezzo con IVA </h2><a href="product?sort=categoria_prodotto">Ordina</a></th>
		</tr>
		<%
			DecimalFormat formatoPrezzo = new DecimalFormat();
			formatoPrezzo.setMaximumFractionDigits(2);
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				int i=-1;
				while (it.hasNext()) {
					
					ProductBean bean = (ProductBean) it.next();
					if(bean.isProdottoMostrato()){
						i++;
						System.out.println(i);
						if(i%3==0)
						{
									
		%>
		</tr>
		<tr><td class="alta"><button><a href="object?id=<%=bean.getIdProdotto()%>"><%=bean.getNomeProdotto()%></a></button>
			<img src="data:image/png;base64,<%=bean.getImmagineProdotto()%>" alt="immagine non presente"/>
			<b>€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%></b></td>
		<%} else{ %>
		    <td class="alta"> <button> <a href="object?id=<%=bean.getIdProdotto()%>"><%=bean.getNomeProdotto()%></a> </button>
			<img src="data:image/png;base64,<%=bean.getImmagineProdotto()%>" alt="immagine non presente"/>
			<b>€<%=formatoPrezzo.format((bean.getPrezzoVetrina()/100)*bean.getIva() + bean.getPrezzoVetrina())%>
			<%} %></b></td>
		<%
					}
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
	
	
	
	<footer>
		
		<div class="footer">
  		<div class="contain">
  			
  			
  		<div class="col">
    		<h1>Company</h1>
    			<ul>
      				<li>Cazzo</li>
      			</ul>
 		</div>
  		
  		<div class="col">
    		<h1>Products</h1>
    			<ul>
    					
    			</ul>
  		</div>
  		
  		
 		<div class="col social">
    		<h1>Social</h1>
   		 		<ul>
      				<li><img src="https://svgshare.com/i/5fq.svg" width="32" style="width: 32px;"></li>
      				<li><img src="https://svgshare.com/i/5eA.svg" width="32" style="width: 32px;"></li>
     				<li><img src="https://svgshare.com/i/5f_.svg" width="32" style="width: 32px;"></li>
    			</ul>
  		</div>
		
		
		<div class="clearfix">
		</div>
		</div>
		</div>
		
	</footer>	
	
	</body>
</html>