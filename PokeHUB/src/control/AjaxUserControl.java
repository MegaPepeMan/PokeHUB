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

//AJAX per la registrazioni di nuovi utenti al sito con controllo della mail 
//(Anche per cambiare la mail di utenti gi‡ iscritti)
@WebServlet("/AjaxUserControl")
public class AjaxUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AjaxUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		UserBean utente;
		UserDAO utenti = new UserDAO();
			
		//cerchera'† l'utente
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
				System.out.println("L'utente gi√† esiste");
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
