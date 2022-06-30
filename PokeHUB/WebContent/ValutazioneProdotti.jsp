<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Valuta Prodotti</title>
	<link href="CSS/Valutazione.css" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="Header.jsp" %>
	
	<div class="content"></div>

	<div class="cards">
	<%
				Map<Integer,ProductBean> prodottiRecensione = null;
				prodottiRecensione = (Map<Integer,ProductBean>) request.getSession().getAttribute("prodottiRecensione");
				Set<Integer> setProdotti =prodottiRecensione.keySet();
				
				Iterator<Integer> it = setProdotti.iterator();
				%>
					
				<%
				while(it.hasNext()) {
					int keyProdotto = it.next();
					ProductBean prodotto = prodottiRecensione.get(keyProdotto);
					%>
					<div class="card">
						<div class="sezioneImmagine">
					<%
						if(prodotto.getImmagineProdotto() == null) {
			            	%>
			            		<img src="Image/noImage.png" alt="" class="immagineProdottoAssente">
			            	<%	
			            } else {
			            	%>
			            		<img class="immagineProdotto" src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>">
			            	<%
			            }
					%>
						</div>
						
						<div class="sezioneTesto">
							<p class="testoCard"><%=prodotto.getNomeProdotto()%></p>
							<div class="tastoValuta" id="<%=prodotto.getIdProdotto()%>"><ion-icon name="star-half-outline"></ion-icon> Valuta prodotto</div>
						</div>
					</div>
					<script>
						$('#<%=prodotto.getIdProdotto()%>').on('click', function() {
							
							Swal.fire({
								  showConfirmButton: false,
								  html: '<form action="RatingControl" method="get"><h1 class="testoValutaProdottoPopUp">Valuta "<%=prodotto.getNomeProdotto()%>"</h1><div class="containerValutazione"><input type="hidden" name="prodotto" value="<%=prodotto.getIdProdotto()%>"><input type="radio" id="radio1" name="rating" value="1"><label for="radio1"><ion-icon name="star" size="large"></ion-icon></label><input type="radio" id="radio2" name="rating" value="2"><label for="radio2"><ion-icon name="star" size="large"></ion-icon></label><input type="radio" id="radio3" name="rating" value="3"><label for="radio3"><ion-icon name="star" size="large"></ion-icon></label><input type="radio" id="radio4" name="rating" value="4"><label for="radio4"><ion-icon name="star" size="large"></ion-icon></label><input type="radio" id="radio5" name="rating" value="5"><label for="radio5"><ion-icon name="star" size="large"></ion-icon></label></div> <button type="submit" class="bottoneInserisci">Inserisci</button></form>',
								  customClass: { popup: 'borderBoxPopUp'},
								})
						} )
					</script>
					<%
				}
				
				%>
					
				<%
	%>

	</div>

	<script src="JavaScript/SweetAlert2/sweetalert2.all.min.js" type="text/javascript"></script>
	<script src="JavaScript/jquery-3.6.0.min.js" type="text/javascript"></script>
	
	

</body>
</html>