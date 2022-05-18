package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import model.OrderDAO;
import model.UserBean;

@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public OrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente=(UserBean) request.getSession().getAttribute("userID");
		
		
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		}
		else if(!utente.getCategoriaUtente().equalsIgnoreCase("amministratore")) {
			response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
		}
		
		OrderDAO ordini = new OrderDAO();
		
		try {
			Collection<OrderBean> totaleOrdini = ordini.doRetrieveAll();
			request.getSession().setAttribute("totaleOrdini", totaleOrdini);
		} catch (SQLException e) {
			System.out.println("Errore stringa SQL");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Errore di IO");
			e.printStackTrace();
		}
		
		
		/*
		try {
			Collection<OrderBean> totaleOrdini = ordini.doRetrieveByDate(request.getParameter("dataInizio"), request.getParameter("dataFine")) ;
		} catch (SQLException e) {
			System.out.println("Errore stringa SQL");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Errore di IO");
			e.printStackTrace();
		}
		*/
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
