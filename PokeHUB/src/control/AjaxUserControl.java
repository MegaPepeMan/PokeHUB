package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class AjaxUserControl
 */
@WebServlet("/AjaxUserControl")
public class AjaxUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		UserBean utente;
		UserDAO utenti = new UserDAO();
			
		//cercherà l'utente
		try {
			utente = utenti.doRetrieveByUser(request.getParameter("testMail"));
			System.out.println("La mail che provo a cercare e': "+request.getParameter("testMail"));
			String oggettoJSON;
			
			if(utente.getMail().equalsIgnoreCase("")) {
				oggettoJSON = new Gson().toJson(true);
				System.out.println("Oggetto JSON: "+oggettoJSON);
				out.print(oggettoJSON);
				out.flush();
			} else {
				System.out.println("L'utente già esiste");
				oggettoJSON = new Gson().toJson(false);
				System.out.println("Oggetto JSON: "+oggettoJSON);
				out.print(oggettoJSON);
				out.flush();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
