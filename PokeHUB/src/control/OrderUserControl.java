package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CompositionBean;
import model.CompositionDAO;
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
		Integer id = null;
		try {
			id = Integer.valueOf(request.getParameter("idOrdine"));
		}
		catch (Exception e) {
			System.out.println("Parsing non riuscito del valore Integer");
		}
		
		
		CompositionDAO fatture = new CompositionDAO();
		
		Collection<CompositionBean> fattura = null;
		try {
			System.out.println("Ricerca composizione dell'ordine");
			fattura = fatture.doRetrieveByOrder(15, null);
			fattura.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getSession().setAttribute("fattura",fattura);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
