package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddressBean;
import model.AddressDAO;
import model.Cart;
import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.OrderDAO;
import model.PaymentBean;
import model.PaymentDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;

@WebServlet("/CheckuotControl")
public class CheckuotControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public CheckuotControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		}
		
		Cart carrello = (Cart) request.getSession().getAttribute("cart");
		if(carrello.isEmpty()) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart");
			dispatcher.forward(request, response);
		}

		
		String indirizzoSpedizione = request.getParameter("shipmentAddress");
		String cartaPagamento = request.getParameter("creditCard");
		
		if(indirizzoSpedizione == null || cartaPagamento == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PreCheckuot");
			dispatcher.forward(request, response);
		}
		
		AddressDAO indirizzi = new AddressDAO();
		PaymentDAO pagamenti = new PaymentDAO();
		
		AddressBean indirizzo = null;
		PaymentBean pagamento = null;
		try {
			indirizzo = indirizzi.doRetrieveByKey(utente.getMail(),indirizzoSpedizione);
			pagamento = pagamenti.doRetrieveByKey(cartaPagamento);
		} catch (SQLException e1) {
			System.out.println("Errore nella ricerca dell'indirizzo/pagamento");
			e1.printStackTrace();
		}
		
		
		
		
		ProductDAO prodotti = new ProductDAO();
		CompositionDAO composizioni = new CompositionDAO();
		OrderDAO ordini = new OrderDAO();
		OrderBean ordine = new OrderBean();
		ProductBean prodotto;
		CompositionBean composizione;
		
		if(true) {
			//L'acquisto ha avuto successo
			request.getSession().removeAttribute("cart");
			
			
			
			ordine.setCap(indirizzo.getCap());
			ordine.setCivico(indirizzo.getCivico());
			ordine.setDataOrdine(Date.valueOf(LocalDate.now()));
			ordine.setMailCliente(utente.getMail());
			ordine.setStato("IN CONSEGNA");
			ordine.setTelefono(indirizzo.getTelefono());
			ordine.setTrakingOrdine("XXX-XXX-XXX");
			ordine.setVia(indirizzo.getVia());
			
			try {
				ordini.doSave(ordine);
			} catch (SQLException e1) {
				System.out.println("Errore salvataggio ordine");
				e1.printStackTrace();
			}
			
			
			Iterator<Integer> iter = carrello.iterator();
			
			while(iter.hasNext()) {
				int idProdotto = iter.next();
				composizione = new CompositionBean();
				
				try {
					prodotto = prodotti.doRetrieveByKey(idProdotto);
					ordine = ordini.doRetrieveLastInsert(utente.getMail());
					
					composizione.setIdentificativo_ordine(ordine.getIdOrdine());
					composizione.setIdentificativo_prodotto(idProdotto);
					composizione.setIva_acquisto(prodotto.getIva());
					composizione.setPrezzo_acquisto(prodotto.getPrezzoVetrina());
					composizione.setQuantita(carrello.quantityObject(idProdotto));
					composizioni.doSave(composizione);
					
					prodotto.setQuantita(prodotto.getQuantita()-carrello.quantityObject(idProdotto));
					prodotti.doUpdate(prodotto);
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			
			carrello = new Cart();
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
