package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import model.OrderDAO;
import model.UserBean;

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
		
		OrderDAO ordini = new OrderDAO();
		if(request.getParameter("action").equalsIgnoreCase("visualizza")) {
			
		
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
		}else if(request.getParameter("action").equalsIgnoreCase("data")) {
		
		try {
			Collection<OrderBean> totaleOrdini = ordini.doRetrieveByDate(Date.valueOf(request.getParameter("datai")), Date.valueOf(request.getParameter("dataf"))) ;
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
