<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Header.css" rel="stylesheet" type="text/css">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="bodyNavbar">



<div id="navbar">
  <a href="product" style="float: left;"><img id="logoImmagine" src="Image/Logo_Immagine.png" alt=""></a>

	
	<div id="barraRicerca">
		<input type="text" placeholder="Cerca prodotti" class="campoRicerca" id="queryRicerca" onkeyup="funzioneRicerca()"/>
		<button type="submit" class="bottoneRicerca">
			<ion-icon name="search-outline" class="" id=""></ion-icon>
		</button>
		<div id="risultati"></div>
	</div>



  <div id="navbarDestra">
	
	<div class="iconaRicerca">
		<ion-icon name="search-outline" class="icona" id="pulsanteRicercaResponsive"></ion-icon>
	</div>
  	
	<div class="iconaUtenteDIV">
		<a href="LoginPage.jsp" class="iconaUtente">
			<ion-icon name="person-outline" class="icona"></ion-icon>
		</a>
	</div>
  	
	<div class="iconaCarrelloDIV">
		<a href="cart" class="iconaCarrello">
			<ion-icon name="cart-outline" class="icona"></ion-icon>
		</a>
	</div>

  </div>

</div>

<div class="content"></div>

	<script src="JavaScript/SweetAlert2/sweetalert2.all.min.js" type="text/javascript"></script>
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>	


	<script>
		$('#pulsanteRicercaResponsive').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Ricerca</h1><div id="barraRicercaResponsive"><input type="text" placeholder="Cerca prodotti" class="campoRicercaResponsive" id="queryRicercaResponsive" onkeyup="funzioneRicerca()"/><button type="submit" class="bottoneRicercaResponsive"><ion-icon name="search-outline" class="" id=""></ion-icon></button><div id="risultatiResponsive"></div></div>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		
		function funzioneRicerca() {
			
			$("#risultati").empty();
        	$("#risultatiResponsive").empty();
        	$("#risultati").removeClass( "DivRisultati" );
    		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
        	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
			
			console.log("Inizia la funzione di ricerca")
			var stringaParziale;

			
			
			if(document.getElementById("queryRicerca").value === ""){
				stringaParziale = document.getElementById("queryRicercaResponsive").value;
				console.log("Ricerca query responsive");
			} else {
				stringaParziale = document.getElementById("queryRicerca").value;
				console.log("Ricerca query normale");
			}
			
			$.ajax({  
				async: true,
	            //uri della servlet
	            url: "AjaxSuggestControl",  
	            //tipo di richiesta
	            method: "POST",
	            //dati inviati al server
	            data: "stringaRicerca=" + stringaParziale,
	            //tipo dato ricevuto dalla servlet
	            dataType: "json",          
	            success: function(data, textStatus, jqXHR) {
	            	
	            	
	            	
	            	$("#risultati").empty();
	            	$("#risultatiResponsive").empty();
	            	
	            	if( data.length >=1) {
	            		$("#risultatiResponsive").empty();
	            		$("#risultati").empty();
		            	$("#barraRicerca").css({"border-bottom-left-radius":"0px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"0px"});
		            	$("#risultati").addClass( "DivRisultati" );
		            	for (const i in data) {
		            		$( "#risultati" ).append('<div id=""><a href="object?id='+data[i].idProdotto+'">'+data[i].nomeProdotto+'</a></div>');
		            		$( "#risultatiResponsive" ).append('<div id=""><a href="object?id='+data[i].idProdotto+'">'+data[i].nomeProdotto+'</a></div>');
						}
		            	
		            	
		            	
	            	} else {
	            		$("#risultatiResponsive").empty();
	            		$("#risultati").empty();
	            		$("#risultati").removeClass( "DivRisultati" );
	            		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
	            	}
	            	
	            	if(stringaParziale === ""){
	            		
	            		$("#risultati").removeClass( "DivRisultati" );
	            		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
	            	}
	            	
	            	
	            },
	            error: function(jqXHR, textStatus, errorThrown){
	            	console.log(jqXHR);
	            } 
	        });
		}
		
	</script>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


</body>
</html>
