package control;

import model.*;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet che inserisce i prodotti sulla homepage
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	static ProductDAO prodotto = new ProductDAO();
	static CategoryDAO categoria = new CategoryDAO();
	
	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", prodotto.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error products:" + e.getMessage());
		}
		
		try {
			request.removeAttribute("categories");
			request.setAttribute("categories", categoria.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error categories:" + e.getMessage());
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaCatalogo.jsp");
		dispatcher.forward(request, response);
		


		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
