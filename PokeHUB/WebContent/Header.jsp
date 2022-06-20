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

	<form action="" method="get" class="">
		<div id="barraRicerca">
			<input type="text" placeholder="Cerca prodotti" class="campoRicerca" />
			<button type="submit" class="bottoneRicerca">
				<ion-icon name="search-outline" class="" id=""></ion-icon>
			</button>
		</div>
	</form>


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
				  html: '<h1>Ricerca</h1><form action="" method="get" class=""><div id="barraRicercaResponsive"><input type="text" placeholder="Cerca prodotti" class="campoRicercaResponsive" /><button type="submit" class="bottoneRicercaResponsive"><ion-icon name="search-outline" class="" id=""></ion-icon></button></div></form>',
				  customClass: { popup: 'borderBoxPopUp'},
				})
		} )
		
		
	</script>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


</body>
</html>
