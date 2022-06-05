<% 
UserBean persona = (UserBean) session.getAttribute("userID");
if (persona == null)
{	
    response.sendRedirect("./LoginPage.jsp");
    return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pagina Indirizzi</title>

	<link rel="stylesheet" href="CSS/Indirizzi.css"/>
    <link rel="stylesheet" href="CSS/Slick/slick.css"/>

   
</head>
<body>

<%@ include file="Header.jsp" %>

<h3 style="text-align:center">Gestisci indirizzi</h3>

<div class="container-slider">
	
	<div class="slider-for">
	
	<%
			Collection<AddressBean> indirizzi = (Collection<AddressBean>) request.getSession().getAttribute("ShipmentMethod");
			System.out.println(indirizzi);
			AddressBean sistema;
			
				Iterator<AddressBean> iter = indirizzi.iterator();
				int i = 0;
				while( iter.hasNext() ) {
					sistema = iter.next();
					i++;
			
				%>
					
					<div class="">
						<div class="containerinterno">
							<a href="ShipmentControl?action=delete&id=<%=sistema.getIdIndirizzo()%>"><ion-icon class="elimina" name="close-outline" size="large"></ion-icon></a>
							<ion-icon class="modifica" name="create-outline" size="large"></ion-icon>
							<p class="paragrafoIndirizzo"><%=sistema.getVia()%>, <%=sistema.getCivico()%> <%=sistema.getCap()%></p>
							<p class="paragrafoNumero"><%=sistema.getTelefono()%></p>     
						</div>  
        			</div>
					
				<%
			
				}

				%>
		
	</div>

</div>


<div class="content"></div>


<form action="ShipmentControl?action=insert" method="post" style="max-width:500px;margin:auto">

	<h3 style="text-align:center">Aggiungi un nuovo indirizzo</h3>
  
	<div class="input-container">
		<i class="icon"><ion-icon name="map-outline" size="large"></ion-icon></i>
		<input class="input-field" name="via" type="text" maxlength="50" placeholder="Via/Piazza/Vicinale">
	</div>
	
	<div class="input-container">
		<i class="icon"><ion-icon name="home-outline" size="large"></ion-icon></i>
		<input class="input-field" name="civico" type="text" maxlength="50" placeholder="Numero civico">
	</div>
		
	
	<div class="input-container">
		<i class="icon"><ion-icon name="earth-outline" size="large"></ion-icon></i>
		<input class="input-field" name="cap" type="text" maxlength="50" placeholder="CAP">
	</div>
		
	
	<div class="input-container">
		<i class="icon"><ion-icon name="call-outline" size="large"></ion-icon></i>
		<input class="input-field" name="telefono" type="text" maxlength="50" placeholder="Telefono">	
	</div>
	
	<button type="submit" class="btnRegister">Aggiungi indirizzo</button>
	
</form>



<div class="content"></div>


















<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="JavaScript/slick.min.js"></script>

<script>
$(document).ready(function(){
    
	$('.slider-for').slick({
		centerMode: true,
  		centerPadding: '60px',
		slidesToShow: 3,
		dots: true,
		
		prevArrow:"<span class='iconSliderLeft'><ion-icon class='iconLeft' name='arrow-back-circle-outline' size='large'></ion-icon></span>",
		nextArrow:"<span class='iconSliderRight'><ion-icon class='iconRight' name='arrow-forward-circle-outline' size='large'></ion-icon></span>",
		responsive: [
			{
			breakpoint: 1050,
			settings: {
				arrows: true,
				centerMode: true,
				centerPadding: '40px',
				slidesToShow: 2
			}
			},
			{
			breakpoint: 700,
			settings: {
				arrows: true,
				centerMode: false,
				centerPadding: '40px',
				slidesToShow: 1,
			}
			}
		]
	});
	 
});
</script>
</body>
</html>