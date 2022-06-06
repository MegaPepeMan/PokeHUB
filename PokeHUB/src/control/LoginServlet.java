package control;

import model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			String mail = request.getParameter("username");
			String password = request.getParameter("password");
			
			String redirectedPage;
			
			if(mail.equalsIgnoreCase("") || mail==null) {
				redirectedPage = "/LoginPage.jsp";
			} else {
				try {
					UserBean persona = checkLogin(mail, password);
					if (persona.getCategoriaUtente().equalsIgnoreCase("amministratore")) {
						request.getSession().setAttribute("adminRoles", true);
					}
					else {
						request.getSession().setAttribute("adminRoles", false);
					}
					request.getSession().setAttribute("userID", persona);
					redirectedPage = "/userLogged.jsp";
					
				} catch (Exception e) {
					request.getSession().setAttribute("adminRoles", false);
					request.getSession().setAttribute("userID", null);
					redirectedPage = "/LoginPage.jsp";
				}
			}
			
			
			
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}

	private UserBean checkLogin(String mail, String password) throws Exception {
		UserDAO userdao = new UserDAO();
		UserBean utente = userdao.doRetrieveByKey(mail, password);
		
		if (utente.getMail().equals(mail) && utente.getPassword().equals(password)) {
			return utente;
		} else
			throw new Exception("Invalid login and password");
	}
	
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}