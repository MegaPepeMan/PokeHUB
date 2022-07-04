package control;

import java.io.IOException;
import java.sql.Date;
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
		
		UserBean utente = (UserBean) request.getSession().getAttribute("userID");
		
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		AddressDAO indirizzi = new AddressDAO();
		PaymentDAO pagamenti = new PaymentDAO();
		
		
		
		//TODO
		String action = request.getParameter("action");
		
		System.out.println("L'azione è: "+action);

		if (action != null) {
			 if (action.equalsIgnoreCase("insertPayment")) {
				
				//inserisci nuovo pagamento
				try {
					Integer mese = Integer.parseInt(request.getParameter("mese"));
					System.out.println(mese);
					Integer anno = Integer.parseInt(request.getParameter("anno"));
					System.out.println(anno);
					String numeroCarta = request.getParameter("numeroCarta");
					System.out.println(numeroCarta);
					String cvv = request.getParameter("cvv");
					System.out.println(cvv);
					String intestatarioCarta = request.getParameter("intestatarioCarta");
					System.out.println(intestatarioCarta);
					System.out.println("Ho parsato i paramentri");
					
					PaymentBean nuova_carta = new PaymentBean();
					
					nuova_carta.setCVV(cvv);
					nuova_carta.setIntestatarioCarta(intestatarioCarta);
					nuova_carta.setMailCliente(utente.getMail());
					nuova_carta.setNumeroCarta(numeroCarta);
					nuova_carta.setScadenza(anno+"-"+mese+"-1");

					pagamenti.doSave(nuova_carta);
				} catch(Exception e) {
					System.out.println("Errore inserimento carta di credito");
				}
				
				
				
			} else if (action.equalsIgnoreCase("insertAddress")) {
				
				//inserisci nuovo indirizzo
				
				try {
					AddressBean nuovo_indirizzo = new AddressBean();
					
					nuovo_indirizzo.setCap(request.getParameter("cap"));
					nuovo_indirizzo.setCivico(request.getParameter("civico"));
					nuovo_indirizzo.setMailCliente(utente.getMail());
					nuovo_indirizzo.setTelefono(request.getParameter("telefono"));
					nuovo_indirizzo.setVia(request.getParameter("via"));
					
					indirizzi.doSave(nuovo_indirizzo);
				} catch (SQLException e) {
					System.out.println("Errore inserimento indirizzo di spedizione");
					e.printStackTrace();
				}
			}
			 
			 
			 
		}
		
		
		Collection<AddressBean> indirizziUtente;
		try {
			indirizziUtente = indirizzi.doRetrieveAllByUser(utente.getMail() , new String() );
			request.getSession().setAttribute("indirizziUtente", indirizziUtente);
		} catch (SQLException e) {
			System.out.println("Errore nel fetching degli inidrizzi");
			e.printStackTrace();
		}
		
		Collection<PaymentBean> pagamentiUtente;
		try {
			pagamentiUtente = pagamenti.doRetrieveAllByUser(utente.getMail() , new String() );
			request.getSession().setAttribute("pagamentiUtente", pagamentiUtente);
			
		} catch (SQLException e) {
			System.out.println("Errore nel fetching dei pagamenti");
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PreAcquisto.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
