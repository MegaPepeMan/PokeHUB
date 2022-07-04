package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import model.OrderDAO;
import model.UserBean;

//Servlet che gestisce il recupero degli ordini di un utente per l'admin
@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public OrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean utente=(UserBean) request.getSession().getAttribute("userID");

		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
			return;
		}
		
		else if(!utente.getCategoriaUtente().equalsIgnoreCase("amministratore")) {
			response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
			return;
		}
		
		String action = request.getParameter("action");
		System.out.println("L'azione della sevlet OrderControl e': "+action);
		
		if(action == null){
			action = "all";
		}
		
		OrderDAO ordini = new OrderDAO();
		
		if(action.equalsIgnoreCase("all")) {
			
			String ordinamento= null;
			String sort=request.getParameter("sort");
			
			if(sort!=null) {
				switch (sort){
					
					case "id_ordine":
						ordinamento="id_ordine";
						break;
					case "mail_cliente":
						ordinamento="mail_cliente";
						break;
					case "data_ordine":
						ordinamento="data_ordine";
						break;
				}
			}
			
			try {
				Collection<OrderBean> totaleOrdini = ordini.doRetrieveAll(ordinamento);
				request.getSession().setAttribute("totaleOrdini", totaleOrdini);
			} catch (SQLException e) {
				System.out.println("Errore stringa SQL");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Errore di IO");
				e.printStackTrace();
			}
			
		}else if(action.equalsIgnoreCase("search")) {
			
			request.getSession().removeAttribute("totaleOrdini");
			
			try {
				Date data_inizio;
				Date data_fine;
				String user;
				try {
					data_inizio = Date.valueOf(request.getParameter("datai"));
					data_fine = Date.valueOf(request.getParameter("dataf"));
				} catch(Exception e){
					data_inizio = null;
					data_fine = null;
				}
				
				try {
					user = request.getParameter("username");
				} catch (Exception e) {
					user = "*";
				}
				
				if (data_inizio == null || data_fine == null) {
					
					data_inizio = Date.valueOf("1990-1-1");
					data_fine = Date.valueOf(LocalDate.now());
				}
				Collection<OrderBean> totaleOrdini = ordini.doRetrieveByDateAndUser(data_inizio, data_fine, user) ;
				System.out.println(totaleOrdini);
				request.getSession().setAttribute("totaleOrdini", totaleOrdini);
				
			} catch (SQLException e) {
				System.out.println("Errore stringa SQL");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Errore di IO");
				e.printStackTrace();
			}
			
		}
		response.sendRedirect(request.getContextPath()+"/PaginaOrdiniAdmin.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
