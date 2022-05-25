package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class RegistrationControl
 */
@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
		}
		
		UserBean utenti=new UserBean();
		
		utenti.setMail(request.getParameter("userid"));
		utenti.setPassword(request.getParameter("passid"));
		utenti.setNome(request.getParameter("username"));
		utenti.setCognome(request.getParameter("surname"));
		utenti.setCellulare(request.getParameter("number"));
		utenti.setMail(request.getParameter("email"));
		
		UserDAO dao = new UserDAO();
		try {
			dao.doSave(utenti);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
