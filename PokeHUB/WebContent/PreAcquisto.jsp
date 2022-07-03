<%
	UserBean persona = (UserBean) session.getAttribute("userID");
	if (persona == null)
	{	
		response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	    return;
	}

	Cart cart = (Cart) request.getSession().getAttribute("cart");
	
	if (cart == null) {
		response.sendRedirect(request.getContextPath()+"/product");
	    return;
	}
	if (cart.isEmpty()) {
		response.sendRedirect(request.getContextPath()+"/product");
	    return;
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ include file="Header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Conferma dati</title>
	<link href="CSS/PreAcquisto.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="contenitoreGrande">

        <div class="sezioneForm">
            <div class="divPagamento">
                <form action="PreCheckuot?action=insertPayment" method="post" style="width:100%;margin: 0 auto">

                    <h3>Aggiungi un nuovo pagamento</h3>
                  
                    <div class="input-container">
                        <i class="icon"><ion-icon name="card-outline" size="large"></ion-icon></ion-icon></i>
                        <input class="input-field" name="numeroCarta" type="text" maxlength="16" required placeholder="Numero carta">
                    </div>
                    
                    <div class="input-container">
                        <i class="icon"><ion-icon name="person-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="intestatarioCarta" type="text" maxlength="50" required placeholder="Intestatario">
                    </div>
                        
                    
                    <div class="input-container">
                        <i class="icon"><ion-icon name="keypad-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="cvv" type="text" maxlength="3" required placeholder="CVV">
                    </div>
                        
                    <div class="input-container-month">
                        <i class="icon"><ion-icon name="calendar-number-outline" size="large"></ion-icon></i>
                        <input class="input-field" name ="mese" type="number" min="1" max="12" step="1" required placeholder="Mese scadenza">
                    </div>
                    
                    <div class="input-container-year">
                        <i class="icon"><ion-icon name="calendar-number-outline" size="large"></ion-icon></i>
                        <input class="input-field" name ="anno" type="number" min="2022" max="2099" step="1" required placeholder="Anno scadenza">
                    </div>
                        
                    
                    
                    <button type="submit" class="btnAggiungi">Aggiungi pagamento</button>
                    
                </form>
            </div>
            

            <div class="divIndirizzo">
                <form action="PreCheckuot?action=insertAddress" method="post" style="width:100%;margin: 0 auto">
    
                    <h3>Aggiungi un nuovo indirizzo</h3>
                  
                    <div class="input-container">
                        <i class="icon"><ion-icon name="map-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="via" type="text" maxlength="50" required placeholder="Via/Piazza/Vicinale">
                    </div>
                    
                    <div class="input-container">
                        <i class="icon"><ion-icon name="home-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="civico" type="text" maxlength="50" required placeholder="Numero civico">
                    </div>
                        
                    
                    <div class="input-container">
                        <i class="icon"><ion-icon name="earth-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="cap" type="text" maxlength="50" required placeholder="CAP">
                    </div>
                        
                    
                    <div class="input-container">
                        <i class="icon"><ion-icon name="call-outline" size="large"></ion-icon></i>
                        <input class="input-field" name="telefono" type="text" maxlength="50" required placeholder="Telefono">	
                    </div>
                    
                    <button type="submit" class="btnAggiungi">Aggiungi indirizzo</button>
                    
                </form>
            </div>
           
    

        </div>
	
		
        <form action="CheckuotControl" method="post">

            <div id="sezioneAcquistaOra">
                        <div class="divSelect">
                            <h1 class="testoSezioneAcquistaOra">Paga con:</h1>
		                            
								<%
									boolean pagamentiPresenti = false;
									boolean indirizziPresenti = false;
									
						  			Collection<?> pagamenti = (Collection<?>) request.getSession().getAttribute("pagamentiUtente");
									if (pagamenti != null && pagamenti.size() != 0) {
										pagamentiPresenti = true;
										%>
										<select name="creditCard" required id="selectPagamenti">
										<%
										Iterator<?> it = pagamenti.iterator();
										
										while (it.hasNext()) {
											PaymentBean bean = (PaymentBean) it.next();
											String carta = "Carta che termina con "+bean.getNumeroCarta().substring( bean.getNumeroCarta().length()-4 , bean.getNumeroCarta().length() );
											
								%>
								<option value="<%=bean.getNumeroCarta()%>"><%=carta%></option>
								<%
										}
										%>
										</select>
										<%
									} else {
										%>
											<h1 class="testoSezioneAcquistaOra">NESSUNA CARTA AGGIUNTA</h1>
										<%
									}
								%>

                        </div>
            
                        <div class="divSelect">
                            <h1 class="testoSezioneAcquistaOra">Invia a:</h1>
                            <%
					  			Collection<?> indirizzi = (Collection<?>) request.getSession().getAttribute("indirizziUtente");
								if (indirizzi != null && indirizzi.size() != 0) {
									indirizziPresenti = true;
									%>
									<select name="shipmentAddress" required id="selectIndirizzi">
									<%
									Iterator<?> it = indirizzi.iterator();
									
									while (it.hasNext()) {
										AddressBean bean = (AddressBean) it.next();
										
							%>
							<option value="<%=bean.getIdIndirizzo()%>"><%=bean.getVia()%> <%=bean.getCivico()%> <%=bean.getCap()%></option>
							<%
									}
									%>
									</select>
									<%
								} else {
									%>
										<h1 class="testoSezioneAcquistaOra">NESSUN INDIRIZZO AGGIUNTO</h1>
									<%
								}
							%>
                        </div>
                        
                        <% 
                        if(pagamentiPresenti && indirizziPresenti){
                        	%>
                        		<button type="submit" class="bottoneProcediPagamento">
		                            <div class="procediPagamento">
		                                <h1 id="testoProcediPagamento" style="text-align: center; font-size: 20pt;">ACQUISTA ORA</h1>
		                            </div>
		                        </button>
                        	<%
                        } else {
                        	%>
	                    		<div class="bottoneProcediPagamento">
		                            <div class="procediPagamento">
		                                <h1 id="testoProcediPagamento" style="text-align: center; font-size: 20pt;">Aggiungi informazioni per completare l'ordine</h1>
		                            </div>
		                        </div>
                    		<%
                        }
                        %>
            
                        
                    </div>
            
        </form>


    </div>
    
    <div class="content"></div>
    
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    
</body>
</html>