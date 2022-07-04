package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.OrderDAO;
import model.ProductBean;
import model.ProductDAO;
import model.RatingBean;
import model.RatingDAO;
import model.UserBean;

//Servlet della valutazione
@WebServlet("/RatingControl")
public class RatingControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RatingControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		} else {
			
			request.getSession().removeAttribute("prodottiRecensione");
			
			OrderDAO ordini = new OrderDAO();
			CompositionDAO composizioni = new CompositionDAO();
			ProductDAO prodotti = new ProductDAO();
			RatingDAO recensioni = new RatingDAO();
			Map<Integer,ProductBean> prodottiDaRecensire = new HashMap<Integer,ProductBean>();
			
			Collection<OrderBean> ordiniUtente = null;
			
			//Gestione form valutazione
			Integer idNuovoProdotto = null;
			Integer valutazioneNuovoProdotto = null;
			
			try {
				idNuovoProdotto = Integer.parseInt( request.getParameter("prodotto") );
				valutazioneNuovoProdotto = Integer.parseInt( request.getParameter("rating") );
				System.out.println("ID Prodotto: "+idNuovoProdotto);
				System.out.println("Rating: "+valutazioneNuovoProdotto);
				
				RatingBean valutazione = new RatingBean();
				
				valutazione.setDescrizione("");
				valutazione.setIdProdotto(idNuovoProdotto.intValue());
				valutazione.setMailCliente( utente.getMail() );
				valutazione.setPunteggio(valutazioneNuovoProdotto.doubleValue());
				
				System.out.println("Creato con successo il Bean: "+ valutazione.getMailCliente() + " " + valutazione.getPunteggio() + " " + valutazione.getIdProdotto() + " " + valutazione.getDescrizione() );
				
				recensioni.doSave(valutazione);
				
				
				
			} catch (Exception e) {
				System.out.println("Nessun valore passato");
			}
			
			
			
			
			try {
				ordiniUtente = ordini.doRetrieveByUser(utente.getMail());
			} catch (SQLException | IOException e) {
				System.out.println("Errore ritrovamento ordini");
				e.printStackTrace();
			}
			

			Iterator<OrderBean> itOrdiniUtente = ordiniUtente.iterator();
			
			while(itOrdiniUtente.hasNext()) {
				OrderBean ordine = itOrdiniUtente.next();
				try {
					Collection<CompositionBean> composizioniOrdine = composizioni.doRetrieveByOrder(ordine.getIdOrdine(), null);
					
					Iterator<CompositionBean> itComposizioneOrdine = composizioniOrdine.iterator();
					
					while(itComposizioneOrdine.hasNext()) {
						CompositionBean prodottoComposizione = itComposizioneOrdine.next();
						ProductBean prodotto = prodotti.doRetrieveByKey(prodottoComposizione.getIdentificativo_prodotto());
						RatingBean valutazione = recensioni.doRetrieveByKey(utente.getMail(), prodottoComposizione.getIdentificativo_prodotto());
						if(valutazione.getIdProdotto() == null) {
							prodottiDaRecensire.put(prodotto.getIdProdotto(), prodotto);
						}
					}
					
					
					
				} catch (SQLException e) {
					System.out.println("Errore ritrovamento composizione dell'ordine");
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
			request.getSession().setAttribute("prodottiRecensione", prodottiDaRecensire);
			System.out.println("I prodotti da recensire sono: "+ prodottiDaRecensire.toString() );
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ValutazioneProdotti.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
