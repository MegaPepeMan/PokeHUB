package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ProductBean;
import model.ProductDAO;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class AjaxUserControl
 */
@WebServlet("/AjaxSuggestControl")
public class AjaxSuggestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSuggestControl() {
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
        String oggettoJSON = null;
        
		System.out.println(request.getParameter("stringaRicerca"));
		ProductDAO prodotti = new ProductDAO();
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				Collection<ProductBean> prodottiSuggest = prodotti.doRetrieveSuggest(request.getParameter("stringaRicerca"));
				Iterator<ProductBean> iter = prodottiSuggest.iterator();
				
				ProductBean prodotto = null;
				while(iter.hasNext()) {
					prodotto = iter.next();
					System.out.println("I prodotti ricevuti dalla servlet sono: "+ prodotto.getNomeProdotto() + " " + prodotto.getIdProdotto());
				}
				
				oggettoJSON = new Gson().toJson(prodottiSuggest);
				System.out.println("Oggetto JSON: "+oggettoJSON);
				
				response.getWriter().write(oggettoJSON.toString());
			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
