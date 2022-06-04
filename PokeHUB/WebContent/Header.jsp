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
  <a class="active" href="product"><img id="logo" src="Image/Logo_Immagine.png" alt=""></a>
  
  
  <div class="containerHeader">
    <form action="" method="get" class="formRicerca">
      
      <input type="text" placeholder="Cerca prodotti" class="search-field" />
      <button type="submit" class="search-button">
        <img src="Image/search.png">
      </button>
    </form>
  </div>

  <div id="navbarDestra">
  <a href="LoginPage.jsp"><img id="iconUser" src="Image/user_icon.png" alt=""></a>
  <a href="cart"><img id="iconCart" src="Image/cart_icon.png" alt=""></a>
  </div>

</div>

<div class="content"></div>


<script>
	window.onscroll = function() {myFunction()};
	
	var navbar = document.getElementById("navbar");
	var sticky = navbar.offsetTop;
	
	function myFunction() {
	  if(window.innerWidth > 600){
	        if (window.pageYOffset >= sticky) {
	        navbar.classList.add("sticky")
	      } else {
	        navbar.classList.remove("sticky");
	      }
	    }
	  
	}
	

</script>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


</body>
</html>
