


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ordini Admin</title>
</head>

<body>
	<%@ include file="Header.jsp" %>
	
	<table>
		<tr>
			<th><h2><a href="OrderControl?sort=id_ordine">ID ordine</a></h2></th>
			<th><h2><a href="OrderControl?sort=mail_cliente">Cliente</a></h2></th>
			<th><h2>Codice di tracking</h2></th>
			<th><h2><a href="OrderControl?sort=data_ordine">Data ordine</a></h2></th>
			<th><h2>Status consegna</h2></th>
			<th><h2>Indirizzo di spedizione</h2></th>
		</tr>
		
		<tr>
		
		
		
		</tr>
		
		
		
		
		<tr>
			<td colspan="6">Nessun ordine disponibile</td>
		</tr>
		
	</table>
	
	
		
</body>
</html>