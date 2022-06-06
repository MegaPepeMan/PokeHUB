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


import model.PaymentBean;
import model.PaymentDAO;
import model.UserBean;



@WebServlet("/PaymentControl")
public class PaymentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PaymentControl() {
        super();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean utente = (UserBean) request.getSession().getAttribute("userID");
		
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		} else {
			PaymentDAO pagamento = new PaymentDAO();
			
			String action = request.getParameter("action");
			System.out.println("L'azione è: "+action);

			try {
				if (action != null) {
					 if (action.equalsIgnoreCase("delete")) {
						String numCarta = request.getParameter("id");
						pagamento.doDelete(numCarta, utente.getMail());
						
						//operazione di cancellazione
						
					} else if (action.equalsIgnoreCase("insert")) {
						
						
						//si potrebbe gestire con il try catch se fa errore:
						try {
							int mese = Integer.parseInt(request.getParameter("mese"));
							System.out.println(mese);
							int anno = Integer.parseInt(request.getParameter("anno"));
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
							
							String dataScadenza = anno+"-"+mese+"-1";
							System.out.println("La data di scadenza e': "+dataScadenza);
							
							nuova_carta.setScadenza(dataScadenza);

							pagamento.doSave(nuova_carta);
						} catch(Exception e) {
							System.out.println("Errore inserimento carta di credito");
						}
						
						
						
					} 
				}			
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
			//trova i metodi di pagamento di un cliente
			try {
				Collection<PaymentBean> metodiCliente = pagamento.doRetrieveAllByUser(utente.getMail(),new String());
				request.getSession().setAttribute("PaymentMethod", metodiCliente );
			} catch (SQLException e) {
				System.out.println("Errore ricerca dei metodi di pagamento del cliente");
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tipiPagamento.jsp");
			dispatcher.forward(request, response);

		}
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
