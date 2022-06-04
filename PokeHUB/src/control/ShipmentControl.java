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
import model.UserBean;


@WebServlet("/ShipmentControl")
public class ShipmentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShipmentControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean utente = (UserBean) request.getSession().getAttribute("userID");
		
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		}
		
		AddressDAO spedizione = new AddressDAO();
		
		String action = request.getParameter("action");
		System.out.println("L'azione è: "+action);

		try {
			if (action != null) {
				 if (action.equalsIgnoreCase("delete")) {
					int idSpedizione = Integer.parseInt(request.getParameter("id"));
					spedizione.doDelete(utente.getMail(), idSpedizione);
					
					//operazione di cancellazione
					
				} else if (action.equalsIgnoreCase("insert")) {
					
					
					
					AddressBean nuovo_indirizzo = new AddressBean();
					
					nuovo_indirizzo.setCap(request.getParameter("cap"));
					nuovo_indirizzo.setCivico(request.getParameter("civico"));
					nuovo_indirizzo.setMailCliente(utente.getMail());
					nuovo_indirizzo.setTelefono(request.getParameter("telefono"));
					nuovo_indirizzo.setVia(request.getParameter("via"));
					
					
					spedizione.doSave(nuovo_indirizzo);
				} 
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		//trova i metodi di pagamento di un cliente
		try {
			Collection<AddressBean> indirizziCliente = spedizione.doRetrieveAllByUser(utente.getMail(), new String());
			request.getSession().setAttribute("ShipmentMethod", indirizziCliente );
		} catch (SQLException e) {
			System.out.println("Errore ricerca dei metodi di pagamento del cliente");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/indirizziCliente.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
