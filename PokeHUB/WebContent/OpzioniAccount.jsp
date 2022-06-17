<%
	UserBean utente = (UserBean) request.getSession().getAttribute("userID");
	
	if(utente == null) {
		response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		return;
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="CSS/OpzioniAccount.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Opzioni Account</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	<h1>
		<%= utente.getNome() %> <button id="modificaNome">Modifica</button>
	</h1>
	
	<h1>
		<%= utente.getCognome() %>
	</h1>
	
	<h1>
		<%= utente.getMail() %>
	</h1>
	
	<h1>
		<%= utente.getCellulare() %>
	</h1>
	
	<h1>
		<%= utente.getMail() %>
	</h1>
	
	
	<script src="JavaScript/SweetAlert2/sweetalert2.all.min.js" type="text/javascript"></script>
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>
	
	<script>
		$('#modificaNome').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Ciaone</h1> <form action="/" method="post" id=""><input type="text"><input type="submit" value="Aggiorna"> </form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})

			
			
			
			
		} )
	</script>
	
</body>
</html>