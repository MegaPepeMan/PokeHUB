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

import model.UserBean;
import model.UserDAO;

//Servlet dell'admin. Permette di aggiornare le autorizzazioni degli utenti
@WebServlet("/AdminUserControl")
public class AdminUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean utenteAdmin = (UserBean) request.getSession().getAttribute("userID");
		if(utenteAdmin == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			dispatcher.forward(request, response);
		} else {
			if(!utenteAdmin.getCategoriaUtente().equalsIgnoreCase("amministratore")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userLogged.jsp");
				dispatcher.forward(request, response);
			} else {
				String user = request.getParameter("cercaUtente");
				String userUpdate = request.getParameter("utente");
				
				System.out.println("L'utente e': "+user);
				
				
					if (user != null) {
							 UserDAO utenti = new UserDAO();
							 Collection<UserBean> utente = null;
							try {
								utente = utenti.doRetrieveAll(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 request.getSession().setAttribute("utenti", utente);
							 
					} else if (userUpdate != null) {
						String nuovaCategoria = request.getParameter("categoria");
						UserDAO utenti = new UserDAO();
						try {
							System.out.println("La categoria recuperata dal parametro è: "+nuovaCategoria);
							utenti.doUpdateCategory(userUpdate, nuovaCategoria);
							
							Collection<UserBean> utente = null;
							try {
								utente = utenti.doRetrieveAll("*");
								
							} catch (SQLException e1) {
								System.out.println("Errore, non ho trovato nessun utente");
								e1.printStackTrace();
							}
							utente.toString();
							request.getSession().setAttribute("utenti", utente);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						System.out.println("Stai facendo una ricerca di utenti globale");
						UserDAO utenti = new UserDAO();
						Collection<UserBean> utente = null;
						try {
							utente = utenti.doRetrieveAll("*");
							
						} catch (SQLException e1) {
							System.out.println("Errore, non ho trovato nessun utente");
							e1.printStackTrace();
						}
						utente.toString();
						request.getSession().setAttribute("utenti", utente);
					}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaUtentiAdmin.jsp");
				dispatcher.forward(request, response);
				
			
			}
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
