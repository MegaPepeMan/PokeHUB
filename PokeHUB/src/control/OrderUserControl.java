package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CompositionBean;
import model.CompositionDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;

/**
 * Servlet implementation class OrderUserControl
 */
@WebServlet("/OrderUserControl")
public class OrderUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrderUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean) request.getSession().getAttribute("userID");
		
		
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
			return;
		}
		
		request.getSession().removeAttribute("fattura");
		
		Integer id = null;
		try {
			id = Integer.valueOf(request.getParameter("idOrdine"));
		}
		catch (Exception e) {
			System.out.println("Parsing non riuscito del valore Integer");
		}
		
		
		CompositionDAO fatture = new CompositionDAO();
		ProductDAO fotoProdotti = new ProductDAO();
		
		Collection<CompositionBean> fattura = null;
		try {
			System.out.println("Ricerca composizione dell'ordine");
			fattura = fatture.doRetrieveByOrder(id, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<CompositionBean> it = fattura.iterator();
		ProductBean fotoProdotto;
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();
		while(it.hasNext()) {
			try {
				prodotti.add( fotoProdotti.doRetrieveByKey(  it.next().getIdentificativo_prodotto()  ) );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		request.getSession().setAttribute("prodotti",prodotti);
		request.getSession().setAttribute("fattura",fattura);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaFattura.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
