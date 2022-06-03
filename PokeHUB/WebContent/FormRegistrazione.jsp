<%
UserBean utente = (UserBean) request.getSession().getAttribute("userID");
if(utente != null) {
	response.sendRedirect(request.getContextPath() + "/userLogged.jsp");
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<link href="CSS/FormRegistrazione.css" rel="stylesheet" type="text/css">
	<meta charset="UTF-8">
	<title>Registrati a PokeHUB</title>
</head>

<body>
	<%@ include file="Header.jsp" %>

	<form action="RegistrationControl" method="post" id="login" style="max-width:500px;margin:auto" onsubmit="event.preventDefault(); validate(this)">
        <h2 style="text-align:center"><img id="Logo_Testo" src="Image/Logo_Testo.png"></h2>
        
        <h3 style="text-align:center">Compila il form</h3>
      
        <div class="input-container">
            <i class="icon"><ion-icon name="at-circle-outline" size="large"></ion-icon></i>
            <input class="input-field" type="text" placeholder="Indirizzo E-Mail" name="userid" size="12">
            <div id="errorMail" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
              <p id="errorMessageMail">Errore formato mail</p>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="lock-closed-outline" size="large"></ion-icon></i>
            <input class="input-field" type="password" placeholder="La tua password" name="passid" size="12">
            <div id="errorPassword" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
        	<p id="errorMessagePassword">Errore formato password</p>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="lock-closed-outline" size="large"></ion-icon></i>
            <input class="input-field" type="password" placeholder="Ripeti la password" name="passid2" size="12">
            <div id="errorPassword2" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
        	<p id="errorMessagePassword2">Le password non coincidono</p>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="accessibility-outline" size="large"></ion-icon></i>
            <input class="input-field" type="text" placeholder="Nome" name="nome" size="50">
            <div id="errorName" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
        	<p id="errorMessageName">Il nome deve contenere solo lettere</p>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="accessibility-outline" size="large"></ion-icon></i>
            <input class="input-field" type="text" placeholder="Cognome" name="cognome" size="50">
            <div id="errorSurname" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
        	<p id="errorMessageSurname">Il cognome deve contenere solo lettere</p>
        
        <div class="input-container">
            <i class="icon"><ion-icon name="call-outline" size="large"></ion-icon></i>
            <input class="input-field" type="text" placeholder="Telefono" name="telefono" size="30">
            <div id="errorNumber" class="error2"><i> <ion-icon name="warning-outline"></ion-icon> </i></div>
        </div>
        
        
        
      
        <button type="submit" class="btnRegister">Registrati</button>
    </form>

	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    
    <script>

		function checkNamesurname(inputtxt) {
			var name = /^[A-Za-z]+$/;;
			if(inputtxt.value.match(name)) 
				return true
		
			return false;	
		}
		
		function checkEmail(inputtxt) {
			var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if(inputtxt.value.match(email)) 
				return true;
			
			return false;	
		}
		
		function checkPhonenumber(inputtxt) {
			var phoneno = /^([0-9]{3}-[0-9]{7})$/;
			if(inputtxt.value.match(phoneno)) 
				return true;
			
			return false;
		}
		
		
		function validate(obj) {	
			var valid = true;	
			
			var name = document.getElementsByName("nome")[0];
			if(!checkNamesurname(name)) {
				valid = false;
				document.getElementById("errorName").style.display = 'block';
				document.getElementById("errorMessageName").style.display = 'block';
			} else {
				document.getElementById("errorName").style.display = 'none';
				document.getElementById("errorMessageName").style.display = 'none';
			}
			
			var surname = document.getElementsByName("cognome")[0];
			if(!checkNamesurname(surname)) {
				valid = false;
				document.getElementById("errorSurname").style.display = 'block';
				document.getElementById("errorMessageSurname").style.display = 'block';
			} else {
				document.getElementById("errorSurname").style.display = 'none';
				document.getElementById("errorMessageSurname").style.display = 'none';
			}
			
			var email = document.getElementsByName("userid")[0];
			if(!checkEmail(email)) {
				valid = false;
				document.getElementById("errorMail").style.display = 'block';
				document.getElementById("errorMessageMail").style.display = 'block';
			} else {
				document.getElementById("errorMail").style.display = 'none';
				document.getElementById("errorMessageMail").style.display = 'none';
			}	
			
			var pass = document.getElementsByName("passid2")[0];
			if(pass != document.getElementsByName("passid")[0]) {
				document.getElementById("errorPassword2").style.display = 'none';
				document.getElementById("errorMessagePassword2").style.display = 'none';
			} else {
				valid = false;
				document.getElementById("errorPassword2").style.display = 'block';
				document.getElementById("errorMessagePassword2").style.display = 'block';
			}
			
			if(valid) obj.submit();
		}
	</script>
    
</body>
</html>