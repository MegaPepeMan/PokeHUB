<%
UserBean utente = null;
Boolean tentativoLoginErrato = false ;
if (session.getAttribute("invalidAttempt") != null ) {
	tentativoLoginErrato = (Boolean)request.getSession().getAttribute("invalidAttempt");
} else 

System.out.println("Lo userID e: "+ session.getAttribute("userID") );
System.out.println("Il tentativo e: "+ session.getAttribute("invalidAttempt") );
if (session.getAttribute("userID") != null ) {
		utente = (UserBean)request.getSession().getAttribute("userID");
		response.sendRedirect(request.getContextPath() + "/userLogged.jsp");
}
%>
<!DOCTYPE HTML>
<html>
<head>
    <link href="CSS/Login.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pagina di login</title>
</head>

<body>
<%@ include file="Header.jsp" %>
    <form action="Login" method="post" id="login" style="max-width:500px;margin:auto">
        <h2 style="text-align:center"><img id="Logo_Testo" src="Image/Logo_Testo.png"></h2>
      
        <div class="input-container">
            <i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
            <input class="input-field" type="text" placeholder="Indirizzo E-Mail" name="username" required>
        </div>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="lock-closed-outline" size="large"></ion-icon></i>
            <input class="input-field" type="password" placeholder="La tua password" name="password" required>
        </div>
        
        <%
        	if( tentativoLoginErrato ) {
        		%>
        			<h4 class="invalidAttempt">L'indirizzo e-mail e la password non sono stati immessi correttamente.</h4>
        		<%
        	}
        %>
      
        <button type="submit" class="btnLogin">Accedi</button>
    </form>

    <hr>

    <h2 style="text-align:center; color:#F1C744;"></h2>    
    
    <div style="max-width:500px;margin:auto">
       	<a href="FormRegistrazione.jsp"><button class="btnRegister">Registrati</button></a>
    </div>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>


</html>




