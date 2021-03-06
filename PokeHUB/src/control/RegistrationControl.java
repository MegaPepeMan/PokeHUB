package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.UserBean;
import model.UserDAO;

//Servlet che gestisce la registrazione al sito
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
		
		
		UserBean utenti=new UserBean();
		
		utenti.setMail(request.getParameter("userid"));
		utenti.setPassword(request.getParameter("passid"));
		utenti.setNome(request.getParameter("nome"));
		utenti.setCognome(request.getParameter("cognome"));
		utenti.setCellulare(request.getParameter("telefono"));
		
		System.out.println(request.getParameter("userid"));
		System.out.println(request.getParameter("passid"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("cognome"));
		System.out.println(request.getParameter("telefono"));
		
		UserDAO dao = new UserDAO();
		try {
			dao.doSave(utenti);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
	}
		

			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
