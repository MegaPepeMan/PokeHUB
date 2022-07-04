package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderDAO;
import model.UserBean;

//Servlet che fa accedere ad una JSP usa-e-getta qunado l'ordine e' completo
@WebServlet("/OrderComplete")
public class OrderComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean persona = (UserBean) request.getSession().getAttribute("userID");
		if (persona == null) {	
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		    return;
		} else {
			if(request.getSession().getAttribute("ordineConfermato") == null ) {
				response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
			    return;
			}
			boolean ordineCompletato = (boolean) request.getSession().getAttribute("ordineConfermato");
			if (!ordineCompletato) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
				dispatcher.forward(request, response);
			} else {
				
				OrderDAO ordini = new OrderDAO();
				try {
					request.getSession().setAttribute("ultimoOrdine", ordini.doRetrieveLastInsert(persona.getMail()));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdineConfermato.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
