package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddressBean;
import model.Cart;
import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.PaymentBean;
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

		
		AddressBean indirizzo = (AddressBean) request.getSession().getAttribute("indirizzoSpedizione");
		if(indirizzo == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PreCheckuot");
			dispatcher.forward(request, response);
		}
		
		PaymentBean pagamento = (PaymentBean) request.getSession().getAttribute("sistemaPagamento");
		if(pagamento == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PreCheckuot");
			dispatcher.forward(request, response);
		}
		
		
		ProductDAO prodotti = new ProductDAO();
		CompositionDAO composizioni = new CompositionDAO();
		
		
		Iterator<Integer> iter = carrello.iterator();
		ProductBean prodotto;
		ArrayList<Integer> prodottiDisponibili = new ArrayList<>();
		
		while(iter.hasNext()) {
			try {
				int id = iter.next();
				prodotto = prodotti.doRetrieveByKey(id);
				if(prodotto.getQuantita() >= carrello.quantityObject(id)) {
					//Se la quantità del prodotto nel database è maggiore della quantità inserita nel carrello
					prodottiDisponibili.add(id);
				} else {
					carrello.setQuantity(id, prodotto.getQuantita());
				}
			} catch (SQLException e) {
				System.out.println("Errore ricerca SQL del prodotto");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Errore IO");
				e.printStackTrace();
			}	
		}
		
		if(prodottiDisponibili.size() == carrello.getSizeCart()) {
			//L'acquisto ha avuto successo
			request.getSession().removeAttribute("cart");
			
			OrderBean ordine = new OrderBean();
			
			ordine.setCap(indirizzo.getCap());
			ordine.setCivico(indirizzo.getCivico());
			ordine.setDataOrdine(Date.valueOf(LocalDate.now()));
			ordine.setMailCliente(utente.getMail());
			ordine.setStato("IN CONSEGNA");
			ordine.setTelefono(indirizzo.getTelefono());
			ordine.setTrakingOrdine("XXX-XXX-XXX");
			ordine.setVia(indirizzo.getVia());
			
			CompositionBean composizione;
			Iterator<Integer> iter2 = carrello.iterator();
			
			while(iter2.hasNext()) {
				int idProdotto = iter2.next();
				composizione = new CompositionBean();
				
				try {
					prodotto = prodotti.doRetrieveByKey(idProdotto);
					
					composizione.setIdentificativo_prodotto(idProdotto);
					composizione.setIva_acquisto(prodotto.getIva());
					composizione.setPrezzo_acquisto(prodotto.getPrezzoVetrina());
					composizione.setQuantita(carrello.quantityObject(idProdotto));
					
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
			
			
		} else {
			request.getSession().removeAttribute("cart");
			request.getSession().setAttribute("cart", carrello );
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart");
			dispatcher.forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
