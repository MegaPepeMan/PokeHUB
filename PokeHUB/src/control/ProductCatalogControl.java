package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAO;
import model.RatingDAO;

/**
 * Servlet implementation class ProductCatalogControl
 */
@WebServlet("/object")
public class ProductCatalogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public ProductCatalogControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO prodotto = new ProductDAO();
		RatingDAO valutazione = new RatingDAO();
		
		try {
			ProductBean oggetto= prodotto.doRetrieveByKey(id);
			request.setAttribute("IDproduct", oggetto);
			
			if(oggetto.isProdottoMostrato()) {
				request.setAttribute("valutazione", valutazione.doAvarage(id));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paginaOggetto.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
				dispatcher.forward(request, response);
			}
			
			
			
		} 
		
		catch (SQLException e) {
			System.out.println("Prodotto non trovato nel DB");
			//Sarebbe l'errore 404
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			System.out.println("Errore di IO");
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
