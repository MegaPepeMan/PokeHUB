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
            <input class="input-field" type="text" placeholder="Indirizzo E-Mail" name="username">
        </div>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="lock-closed-outline" size="large"></ion-icon></i>
            <input class="input-field" type="password" placeholder="La tua password" name="password">
        </div>
      
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




