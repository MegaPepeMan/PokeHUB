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
			            		<img src="Image/noImage.png" alt="" class="immagineProdotto">
			            	<%	
			            } else {
			            	%>
			            		<img class="immagineProdottoAssente" src="data:image/png;base64,<%=prodotto.getImmagineProdotto()%>">
			            	<%
			            }
					%>
						</div>
						
						<div class="sezioneTesto">
							<p class="testoCard"><%=prodotto.getNomeProdotto()%></p>
							<div class="tastoValuta" id="<%=prodotto.getIdProdotto()%>"><ion-icon name="star-half-outline"></ion-icon>Valuta prodotto</div>
						</div>
					</div>
					<script>
						$('#<%=prodotto.getIdProdotto()%>').on('click', function() {
							
							Swal.fire({
								  showConfirmButton: false,
								  html: '<form action="RatingControl" method="get"><input type="hidden" name="prodotto" value="<%=prodotto.getIdProdotto()%>"><input type="radio" name="rating" value="1"><label>1</label><input type="radio" name="rating" value="2"><label>2</label><input type="radio" name="rating" value="3"><label>3</label><input type="radio" name="rating" value="4"><label>4</label><input type="radio" name="rating" value="5"><label>5</label><br><button type="submit">Inserisci</button></form>',
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