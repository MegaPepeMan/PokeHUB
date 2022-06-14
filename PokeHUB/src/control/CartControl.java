package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.ProductDAO;
import model.UserBean;


@WebServlet("/cart")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Integer id = null;
		Integer qty = null;
		ProductDAO prodotto = new ProductDAO();
		
		System.out.println("La richiesta per la pagina del carrello e' stata ricevuta");
		//try n.1
		try {
				//Ottengo gli attributi di sessione e inizializzo id e qty
				UserBean utente = (UserBean) request.getSession().getAttribute("userID");

				
				System.out.println("L'utente "+ utente.getNome() +" "+ utente.getCognome()+" sta accedendo al carrello");

			
				//Caso in cui l'utente e' gia' loggato
				Cart cart = (Cart) request.getSession().getAttribute("cart"); 		//qui potrebbe generarsi il throw
				
				if(cart == null) {
					cart = new Cart();
					System.out.println("L'utente e' loggato ma non ha un carrello");
					request.getSession().setAttribute("cart", cart );
				}
					
				System.out.println(cart);
				System.out.println("L'utente e' presente per accedere al carrello");
				//try n.2
				try {
						//Caso in cui l'utente sta aggiungendo/rimuovendo prodotti dal carrello
						id = Integer.parseInt(request.getParameter("addID"));
						qty = Integer.parseInt(request.getParameter("quantity"));
						
						System.out.println("L'ID e': "+id+" , la quantita' e': "+qty+" del prodotto da aggiungere");
						
						if( id != null && qty.intValue()>0 ) {
							try {
								System.out.println("Aggiungo prodotti al carrello");
								
								
								if(cart.findObject(id) == null) {
									System.out.println("Non è mai stato aggiunto questo prodotto");
									cart.addProduct(id,qty);
								}
								else if ( cart.quantityObject(id) + qty.intValue() <= prodotto.doRetrieveByKey(id).getQuantita() ) {
									System.out.println("La quantità massima non è stata raggiunta");
									cart.addProduct(id,qty);
								}
								else if( cart.quantityObject(id) + qty.intValue() > prodotto.doRetrieveByKey(id).getQuantita() ) {
									System.out.println("La quantità massima è stata raggiunta");
									System.out.println("Prodotti rimanenti: "+ (prodotto.doRetrieveByKey(id).getQuantita() - cart.quantityObject(id)));
									qty = prodotto.doRetrieveByKey(id).getQuantita() - cart.quantityObject(id) - qty.intValue();
									if (qty.intValue()<0) {
										qty = prodotto.doRetrieveByKey(id).getQuantita() - cart.quantityObject(id);
									}
									System.out.println("Aggiungo questi prodotti: "+ qty.intValue());
									cart.addProduct(id, qty);
								}
								
								
								
								System.out.println("Ho aggiunto i prodotti al carrello");
								
								cart.toString();
								
								request.getSession().removeAttribute("cart");
								request.getSession().setAttribute("cart", cart );
								
								
								
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaCarrello.jsp");
								dispatcher.forward(request, response);
								
							} catch (SQLException | IOException e) {
								System.out.println("Eccezione SQL/IO");
								e.printStackTrace();
							}
						} 
						
						else if(id != null && qty.intValue()==0){
							System.out.println("Rimuovo prodotti dal carrello");
							cart.remove(id);
							
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaCarrello.jsp");
							dispatcher.forward(request, response);
						}
					} catch(Exception e) {
						//Il carrello esiste, si sta cercando di accedervi
						System.out.println("Il carrello esiste, accesso in corso");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaCarrello.jsp");
						dispatcher.forward(request, response);
						
					}
			
		} catch(Exception e) {
			System.out.println("L'utente non e' loggato");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		}
	}
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
