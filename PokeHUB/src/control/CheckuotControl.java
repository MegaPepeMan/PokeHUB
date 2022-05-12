package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.CompositionBean;
import model.CompositionDAO;
import model.ProductBean;
import model.ProductDAO;

@WebServlet("/CheckuotControl")
public class CheckuotControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public CheckuotControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart carrello =(Cart) request.getSession().getAttribute("cart");
		
		if(carrello == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart");
			dispatcher.forward(request, response);
		}
		
		ProductDAO prodotti = new ProductDAO();
		CompositionDAO composizioni = new CompositionDAO();
		
		
		Iterator<Integer> iter = carrello.iterator();
		ProductBean prodotto;
		CompositionBean composizione;
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
