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
	
	<h1 style="text-align: center">Modifica Account</h1>
	
	<table class="tabellaOpzioni">

    <tr>
        <th class="testoTabella">
            NOME
        </th>
    </tr>
    
    <tr>
        <th class="testoTabella testoValoriAccount">
            <%= utente.getNome() %>
        </th>
        <th>
            <button class="modificaElemento" id="modificaNome">
                    <div>
                        <ion-icon class="iconaModificaPulsante" name="create-outline" size="large"></ion-icon>
                    </div>
                    
                    <div class="testoModificaElemento">
                        MODIFICA
                    </div>  
                </button>
        </th>
    </tr>

    <tr>
        <th class="testoTabella">
            COGNOME
        </th>
    </tr>

    <tr>
        <th class="testoTabella testoValoriAccount">
            <%= utente.getCognome() %>
        </th>
        <th>
            <button class="modificaElemento" id="modificaCognome">
                <div>
                    <ion-icon class="iconaModificaPulsante" name="create-outline" size="large"></ion-icon>
                </div>
                
                <div class="testoModificaElemento">
                    MODIFICA
                </div>  
            </button>
        </th>
    </tr>

    <tr>
        <th class="testoTabella">
            CELLULARE
        </th>
    </tr>

    <tr>
        <th class="testoTabella testoValoriAccount">
            <%= utente.getCellulare() %>
        </th>
        <th>
            <button class="modificaElemento" id="modificaCellulare">
                <div>
                    <ion-icon class="iconaModificaPulsante" name="create-outline" size="large"></ion-icon>
                </div>
                
                <div class="testoModificaElemento">
                    MODIFICA
                </div>  
            </button>
        </th>
    </tr>

    <tr>
        <th class="testoTabella">
            MAIL
        </th>
    </tr>

    <tr>
        <th class="testoTabella testoValoriAccount" style="font-size: 15pt">
            <%= utente.getMail() %>
        </th>
        <th>
            <button class="modificaElemento" id="modificaMail">
                <div>
                    <ion-icon class="iconaModificaPulsante" name="create-outline" size="large"></ion-icon>
                </div>
                
                <div class="testoModificaElemento">
                    MODIFICA
                </div>  
            </button>
        </th>
    </tr>

    <tr>
        <th class="testoTabella">
            PASSWORD
        </th>
    </tr>

    <tr>
        <th class="testoTabella testoValoriAccount">
            ********
        </th>
        <th>
            <button class="modificaElemento" id="modificaPassword">
                <div>
                    <ion-icon class="iconaModificaPulsante" name="create-outline" size="large"></ion-icon>
                </div>
                
                <div class="testoModificaElemento">
                    MODIFICA
                </div>  
            </button>
        </th>
    </tr>


</table>
	
	<div class="content"></div>
	<div class="content"></div>
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	
	
	<script src="JavaScript/SweetAlert2/sweetalert2.all.min.js" type="text/javascript"></script>
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>
	<script>
		$('#modificaNome').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuovo nome</h1>  <form action="AccountControl" method="post" id="" onsubmit="event.preventDefault(); validateName(this)">  <div class="input-container"><i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getNome() %>" name="nome"><div id="errorName" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div></div>  <p id="errorMessageName">Errore formato nome</p>  <button type="submit" class="btnAggiorna">Aggiorna</button>  </form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaCognome').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuovo cognome</h1>  <form action="AccountControl" method="post" id="" onsubmit="event.preventDefault(); validateSurname(this)">  <div class="input-container"><i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getCognome() %>" name="cognome"><div id="errorSurname" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div></div>  <p id="errorMessageSurname">Errore formato cognome</p>  <button type="submit" class="btnAggiorna">Aggiorna</button>  </form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaMail').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci nuova mail</h1><form action="AccountControl" method="post" id="" onsubmit="event.preventDefault(); validateMail(this)"><div class="input-container"><i class="icon"><ion-icon name="mail-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getMail() %>" name="userid"><div id="errorMail" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div></div><p id="errorMessageMail">Errore formato mail</p><p id="errorMessageMailUsed">La mail &eacute; gi&agrave; utilizzata</p><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaCellulare').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Inserisci un nuovo numero</h1><form action="AccountControl" method="post" id="" onsubmit="event.preventDefault(); validatePhone(this)"><div class="input-container"><i class="icon"><ion-icon name="call-outline" size="large"></ion-icon></i><input class="input-field" type="text" placeholder="<%= utente.getCellulare() %>" name="nuovoCellulare"><div id="errorNumber" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div></div><p id="errorMessageNumber">Errore formato numero</p><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		$('#modificaPassword').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Aggiorna password</h1><form action="AccountControl" method="post" id="" onsubmit="event.preventDefault(); validatePassword(this)"><div class="input-container"><i class="icon"><ion-icon name="key-outline" size="large"></ion-icon></i><input class="input-field" type="password" placeholder="Nuova password" name="passid"><div id="errorPassword" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div>	</div><p id="errorMessagePassword">Errore formato password</p><div class="input-container"><i class="icon"><ion-icon name="key-outline" size="large"></ion-icon></i><input class="input-field" type="password" placeholder="Ripeti password" name="passid2"><div id="errorPassword2" class="error2"><i> <ion-icon name="warning-outline" size="large"></ion-icon> </i></div></div><p id="errorMessagePassword2">Le password non coincidono</p><p id="errorMessageMail">Errore formato mail</p><button type="submit" class="btnAggiorna">Aggiorna</button></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
	</script>
	
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
			var phoneno = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
			if(inputtxt.value.match(phoneno)) 
				return true;
			
			return false;
		}
		
		function checkPassword(inputtxt) {
			var name = /[^\s-]/;
			if(inputtxt.value.match(name)) 
				return true
		
			return false;	
		}
		
		
		function validateName(obj) {	
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
			
			if(valid) obj.submit();

		}
		
		function validateSurname(obj) {
			var valid = true;
			
			var surname = document.getElementsByName("cognome")[0];
			if(!checkNamesurname(surname)) {
				valid = false;
				document.getElementById("errorSurname").style.display = 'block';
				document.getElementById("errorMessageSurname").style.display = 'block';
			} else {
				document.getElementById("errorSurname").style.display = 'none';
				document.getElementById("errorMessageSurname").style.display = 'none';
			}
			if(valid) obj.submit();
		}
		
		function validateMail(obj) {
			var valid = true;
			
			var email = document.getElementsByName("userid")[0];
			if(!checkEmail(email)) {
				valid = false;
				document.getElementById("errorMail").style.display = 'block';
				document.getElementById("errorMessageMail").style.display = 'block';
			} else {
				document.getElementById("errorMail").style.display = 'none';
				document.getElementById("errorMessageMail").style.display = 'none';
		        
			}
			
			if(valid == true) {
				valid = false;
				//AJAX per testing della Mail
				var emailTestPass = document.getElementsByName("userid")[0].value;
				console.log(emailTestPass);
				$.ajax({  
					async: false,
		            //uri della servlet
		            url: "AjaxUserControl",  
		            //tipo di richiesta
		            method: "POST",
		            //dati inviati al server
		            data: "testMail=" + emailTestPass,
		            //tipo dato ricevuto dalla servlet
		            dataType: "json",          
		            success: function(data, textStatus, jqXHR) {  
		            	console.log("Tutto è andato bene. Lo username è libero: "+data);
		            	var contenutoJSON = JSON.parse(data);
		            	console.log("L'oggetto convertito in JS contiene: "+contenutoJSON)
		            	if(contenutoJSON){
		            		valid = true;
		            		console.log("L'username e' libero");
		            	} else {
		            		valid = false;
		            		console.log("L'username NON e' libero");
		            		document.getElementById("errorMail").style.display = 'block';
		            		document.getElementById("errorMessageMailUsed").style.display = 'block';
		            	}
		            },
		            error: function(jqXHR, textStatus, errorThrown){
		            	console.log(jqXHR);
		            } 
		        }); 
			}
			if(valid) obj.submit();
		}
		
		function validatePhone(obj) {
			var valid = true;
			
			var phone = document.getElementsByName("nuovoCellulare")[0];
			if(!checkPhonenumber(phone)) {
				valid = false;
				document.getElementById("errorNumber").style.display = 'block';
				document.getElementById("errorMessageNumber").style.display = 'block';
			} else {
				document.getElementById("errorNumber").style.display = 'none';
				document.getElementById("errorMessageNumber").style.display = 'none';
			}
			if(valid) obj.submit();
		}
		
		
		
		function validatePassword(obj) {
			var valid = true;
			
			var pass1 = document.getElementsByName("passid")[0];
			var pass2 = document.getElementsByName("passid2")[0];
			
			if( checkPassword(pass1) ){
				document.getElementById("errorPassword2").style.display = 'none';
				document.getElementById("errorMessagePassword2").style.display = 'none';
			} else {
				valid = false;
				document.getElementById("errorPassword").style.display = 'block';
				document.getElementById("errorMessagePassword").style.display = 'block';
			}

			if( pass1.value === pass2.value ) {
				console.log('Le password coincidono');
				document.getElementById("errorPassword2").style.display = 'none';
				document.getElementById("errorMessagePassword2").style.display = 'none';
			} else {
				valid = false;
				console.log('Le password non coincidono');
				document.getElementById("errorPassword2").style.display = 'block';
				document.getElementById("errorMessagePassword2").style.display = 'block';
			}	
			
			if(valid) obj.submit();
		}
	</script>
	
</body>
</html>