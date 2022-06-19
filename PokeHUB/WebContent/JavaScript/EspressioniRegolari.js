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