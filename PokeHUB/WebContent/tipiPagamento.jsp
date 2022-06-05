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
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Pagina Pagamenti</title>

	<link rel="stylesheet" href="CSS/Pagamenti.css"/>
    <link rel="stylesheet" href="CSS/Slick/slick.css"/>

   
</head>
<body>

<%@ include file="Header.jsp" %>

<h3 style="text-align:center">Gestisci pagamenti</h3>

<div class="container-slider">
	
	<div class="slider-for">
	
	<%
		Collection<PaymentBean> pagamenti = (Collection<PaymentBean>) request.getSession().getAttribute("PaymentMethod");
		System.out.println(pagamenti);
		PaymentBean sistema;
		
			Iterator<PaymentBean> iter = pagamenti.iterator();
				int i = 0;
				while( iter.hasNext() ) {
					sistema = iter.next();
					i++;
			
				%>
					
					<div class="">
						<div class="containerinterno">
							<a href="PaymentControl?action=delete&id=<%= sistema.getNumeroCarta()%>"><ion-icon class="elimina" name="close-outline" size="large"></ion-icon></a>
							<ion-icon class="modifica" name="create-outline" size="large"></ion-icon>
							<p class="paragrafoIndirizzo">Carta che termina con <%=sistema.getNumeroCarta().substring(sistema.getNumeroCarta().length()-4, sistema.getNumeroCarta().length()) %></p>
							<p class="paragrafoIndirizzo">Scadenza carta: <%= sistema.getScadenza().substring(5, 7) %>/<%= sistema.getScadenza().substring(0, 4) %></p>
							<p class="paragrafoNumero"><%=sistema.getIntestatarioCarta() %></p>     
						</div>  
        			</div>
					
				<%
			
				}

				%>
		
	</div>

</div>


<div class="content"></div>


<form action="PaymentControl?action=insert" method="post" style="max-width:500px;margin:auto">

	<h3 style="text-align:center">Aggiungi un nuovo pagamento</h3>
  
	<div class="input-container">
		<i class="icon"><ion-icon name="card-outline" size="large"></ion-icon></ion-icon></i>
		<input class="input-field" name="numeroCarta" type="text" maxlength="16" placeholder="Numero carta">
	</div>
	
	<div class="input-container">
		<i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
		<input class="input-field" name="intestatarioCarta" type="text" maxlength="50" placeholder="Intestatario">
	</div>
		
	
	<div class="input-container">
		<i class="icon"><ion-icon name="keypad-outline" size="large"></ion-icon></i>
		<input class="input-field" name="cvv" type="text" maxlength="3" placeholder="CVV">
	</div>
		
	<div class="input-container-month">
		<i class="icon"><ion-icon name="calendar-number-outline" size="large"></ion-icon></i>
		<input class="input-field" name ="mese" type="number" min="1" max="12" step="1" placeholder="Mese scadenza">
	</div>
	
	<div class="input-container-year">
		<i class="icon"><ion-icon name="calendar-number-outline" size="large"></ion-icon></i>
		<input class="input-field" name ="anno" type="number" min="2022" max="2099" step="1" placeholder="Anno scadenza">
	</div>
		
	
	
	<button type="submit" class="btnRegister">Aggiungi pagamento</button>
	
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
				centerMode: true,
				centerPadding: '40px',
				slidesToShow: 1
			}
			}
		]
	});
	 
});
</script>
</body>
</html>