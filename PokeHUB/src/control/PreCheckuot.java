package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddressBean;
import model.AddressDAO;
import model.PaymentBean;
import model.PaymentDAO;
import model.UserBean;



@WebServlet("/PreCheckuot")
public class PreCheckuot extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	
	public PreCheckuot() {
        super();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean) request.getAttribute("userID");
		
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		}
		
		AddressDAO indirizzi = new AddressDAO();
		PaymentDAO pagamenti = new PaymentDAO();
		
		
		
		Collection<PaymentBean> pagamentiUtente;
		try {
			pagamentiUtente = pagamenti.doRetrieveAllByUser(utente.getMail() , "" );
			request.getSession().setAttribute("pagamentiUtente", pagamentiUtente);
			
		} catch (SQLException e) {
			System.out.println("Errore nel fetching dei pagamenti");
			e.printStackTrace();
		}
		
		Collection<AddressBean> indirizziUtente;
		try {
			indirizziUtente = indirizzi.doRetrieveAllByUser(utente.getMail() , "" );
			request.getSession().setAttribute("indirizziUtente", indirizziUtente);
		} catch (SQLException e) {
			System.out.println("Errore nel fetching degli inidrizzi");
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PreAcquisto.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
