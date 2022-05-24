<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fattura Ordine</title>
</head>
<body>
	<%
	Collection<CompositionBean> fattura = (Collection<CompositionBean>) request.getSession().getAttribute("fattura");
	Collection<ProductBean> prodotti = (Collection<ProductBean>) request.getSession().getAttribute("prodotti");
	if (fattura != null && fattura.size() != 0) {
	
		ProductBean pezzo = new ProductBean();
		Iterator<ProductBean> it = prodotti.iterator();
			while (it.hasNext()) {
				pezzo = it.next();
				%>
				
				<img src="data:image/png;base64,<%=pezzo.getImmagineProdotto()%>" alt="immagine non presente"/>
				
				<%
			}
		}
	%>
	
	<%=fattura.toString()%>
</body>
</html>