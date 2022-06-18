package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDAO;


@WebServlet("/AccountControl")
public class AccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AccountControl() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		} else {
			
			String idUtente = utente.getMail();
			
			String nome = request.getParameter("nuovoNome");
			String cognome = request.getParameter("nuovoCognome");
			String cellulare = request.getParameter("nuovoCellulare");
			String mail = request.getParameter("nuovoMail");
			
			UserDAO utenti = new UserDAO();
			
			if (cognome != null) {
				System.out.println("Il cognome passato dalla servlet e': "+ cognome);
				try {
					utenti.doUpdateSurname(utente.getMail(), cognome );
					request.getSession().removeAttribute("userID");
					request.getSession().setAttribute("userID", utenti.doRetrieveByUser(idUtente));
				} catch (SQLException e) {
					System.out.println("Errore aggiornamento cognome");
					e.printStackTrace();
				}
			}
				 
			if (nome != null) {
				System.out.println("Il nome passato dalla servlet e': "+ nome);
				try {
					utenti.doUpdateName(utente.getMail(), nome );
					request.getSession().removeAttribute("userID");
					request.getSession().setAttribute("userID", utenti.doRetrieveByUser(idUtente));
				} catch (SQLException e) {
					System.out.println("Errore aggiornamento nome");
					e.printStackTrace();
				}
			}  
			
			if (cellulare != null) {
				System.out.println("Il cellulare passato dalla servlet e': "+ cellulare); 
				try {
					utenti.doUpdatePhone(utente.getMail(), cellulare );
					request.getSession().removeAttribute("userID");
					request.getSession().setAttribute("userID", utenti.doRetrieveByUser(idUtente));
				} catch (SQLException e) {
					System.out.println("Errore aggiornamento cellulare");
					e.printStackTrace();
				}
			}
			
			
			 if (mail != null) {
				System.out.println("La mail passata dalla servlet e': "+ mail); 
			}
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OpzioniAccount.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
