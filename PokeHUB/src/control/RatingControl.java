package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

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
import model.UserBean;

/**
 * Servlet implementation class RatingControl
 */
@WebServlet("/RatingControl")
public class RatingControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		} else {
			
			OrderDAO ordini = new OrderDAO();
			CompositionDAO composizioni = new CompositionDAO();
			ProductDAO prodotti = new ProductDAO();
			
			Collection<OrderBean> ordiniUtente = null;
			try {
				ordiniUtente = ordini.doRetrieveByUser(utente.getMail());
			} catch (SQLException | IOException e) {
				System.out.println("Errore ritrovamento ordini");
				e.printStackTrace();
			}
			
			Collection<ProductBean>  prodottiRecensione;
			Iterator<OrderBean> itOrdiniUtente = ordiniUtente.iterator();
			
			while(itOrdiniUtente.hasNext()) {
				OrderBean ordine = itOrdiniUtente.next();
				try {
					Collection<CompositionBean> composizioniOrdine = composizioni.doRetrieveByOrder(ordine.getIdOrdine(), null);
					
					Iterator<CompositionBean> itComposizioneOrdine = composizioniOrdine.iterator();
					
					while(itComposizioneOrdine.hasNext()) {
						CompositionBean prodottoComposizione = itComposizioneOrdine.next();
						ProductBean prodotto = prodotti.doRetrieveByKey(prodottoComposizione.getIdentificativo_prodotto());
					}
					
					
					
				} catch (SQLException e) {
					System.out.println("Errore ritrovamento composizione dell'ordine");
					e.printStackTrace();
				}
			}
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaValutazione.jsp");
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
