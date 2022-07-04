package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.OrderDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;


//Vecchia servlet inutilizzata che generava la fattura
@WebServlet("/UserInvoiceControl")
public class UserInvoiceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserInvoiceControl() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean utente = (UserBean) request.getSession().getAttribute("userID");
		
		//Controllo lato servlet dell'utente
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
			return;
		}
		
		//Parse parametro action
		String action = request.getParameter("action");
		System.out.println("L'azione della sevlet OrderControl e': "+action);
		if(action == null){
			action = "all";
		}
		
		//Creazione dei DAO per le interrogazioni al DB
		ProductDAO prodotti = new ProductDAO();
		OrderDAO ordini = new OrderDAO();
		CompositionDAO composizioni = new CompositionDAO();
		
		//Ordinamento del risultato in base al parametro
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
			
			
			
			//Ordini ritrovati in base all'utente
			try {
				Collection<OrderBean> totaleOrdini = ordini.doRetrieveByUser(utente.getMail());
				request.getSession().setAttribute("totaleOrdini", totaleOrdini);
				
				//Importo dell'ordine pagato
				
				
				//Ricerca dei dettagli dei prodotti per aggiungere immagini alla pagina
				Iterator<OrderBean> iterProdotti1 = totaleOrdini.iterator();
				Iterator<CompositionBean> iterFotoProdotti1;
				
				//Istazio gli oggetti per il recupero delle composizioni degli ordini
				OrderBean ordine;
				
				Collection<CompositionBean> fattura = new LinkedList<CompositionBean>();
				
				Map<Integer, Double> mappaTotalePrezzi = new HashMap<Integer, Double>();
				Map<Integer, Collection<ProductBean> > mappaProdotti = new HashMap<Integer, Collection<ProductBean>>();
				
				
				while(iterProdotti1.hasNext()){
					ordine=iterProdotti1.next();
					System.out.println("L'ordine in focus è: "+ ordine.getIdOrdine());
					
					fattura = composizioni.doRetrieveByOrder(ordine.getIdOrdine(), null);
					iterFotoProdotti1 = fattura.iterator();
					Collection<ProductBean> fotoProdotti = new LinkedList<ProductBean>();
					while(iterFotoProdotti1.hasNext()) {
						
						fotoProdotti.add(prodotti.doRetrieveByKey(  iterFotoProdotti1.next().getIdentificativo_prodotto()  ) );
					}	
					mappaProdotti.put(ordine.getIdOrdine(), fotoProdotti );
					
					//Calcolo quanto e' costato l'ordine
					Iterator<CompositionBean> iterPrezzi = fattura.iterator();
					CompositionBean composizione;
					double totaleFattura = 0;
					while (iterPrezzi.hasNext()) {
						composizione = iterPrezzi.next();
						double percentualeIVA = composizione.getIva_acquisto()/100;
						double prezzoConIVA = (percentualeIVA * composizione.getPrezzo_acquisto() ) + composizione.getPrezzo_acquisto();
						double totaleProdotti = prezzoConIVA * composizione.getQuantita();
						
						totaleFattura = totaleFattura + totaleProdotti ;
						System.out.println("Il totale a questo punto e': "+totaleFattura);
						mappaTotalePrezzi.put(ordine.getIdOrdine(), totaleFattura);
					}
					
					
				}	
					System.out.println("Le chiavi nella mappa sono: "+mappaProdotti.keySet());
					

					request.getSession().setAttribute("totaleFattura", mappaTotalePrezzi);
					request.getSession().setAttribute("dettagliProdotti", mappaProdotti);
				
				
			} catch (SQLException e) {
				System.out.println("Errore stringa SQL");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Errore di IO");
				e.printStackTrace();
			}
			
			
			
			
			
		}else if(action.equalsIgnoreCase("search")) {
			//Ordini ritrovati in base alla data inizio e data fine
			request.getSession().removeAttribute("totaleOrdini");
			
			try {
				Date data_inizio;
				Date data_fine;
				String user = utente.getMail();
				try {
					data_inizio = Date.valueOf(request.getParameter("datai"));
					data_fine = Date.valueOf(request.getParameter("dataf"));
				} catch(Exception e){
					data_inizio = null;
					data_fine = null;
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
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaOrdiniUser.jsp");
		dispatcher.forward(request, response);
	
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
