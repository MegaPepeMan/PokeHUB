package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet che invalida la sessione quando l'utente fa logout
@WebServlet("/Logout")
public class LogoutControl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.getSession().removeAttribute("adminRoles");
		request.getSession().removeAttribute("userID");
		request.getSession().invalidate();

		String redirectedPage = "/product";
		response.sendRedirect(request.getContextPath() + redirectedPage);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

	private static final long serialVersionUID = 1L;
    
    public LogoutControl() {
        super();
    }
	
}
