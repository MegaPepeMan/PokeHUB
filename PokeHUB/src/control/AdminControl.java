package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.*;


@WebServlet("/admin")
@MultipartConfig
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProductDAO prodotto = new ProductDAO();
	static CategoryDAO categoria = new CategoryDAO();
	
	public AdminControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//controllo per l'identita' della persona collegata
		UserBean utente = (UserBean)request.getSession().getAttribute("userID");
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
		}
		else if(!utente.getCategoriaUtente().equalsIgnoreCase("amministratore")) {
			response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
		}
		else {
			
			String action = request.getParameter("action");

			try {
				if (action != null) {
					 if (action.equalsIgnoreCase("read")) {
						int id = Integer.parseInt(request.getParameter("id"));
						request.removeAttribute("IDproduct");
						request.setAttribute("IDproduct", prodotto.doRetrieveByKey(id));
					} else if (action.equalsIgnoreCase("delete")) {
						System.out.println("Nascondo prodotto");
						int id = Integer.parseInt(request.getParameter("id"));
						System.out.println("ID prodotto da nascondere: "+id);
						ProductBean beanProdottoNascosco = prodotto.doRetrieveByKey(id);
						beanProdottoNascosco.setProdottoMostrato(false);
						System.out.println("Prodotto da nascondere: "+ beanProdottoNascosco.isProdottoMostrato() );
						prodotto.doUpdate(beanProdottoNascosco);
						
					} else if (action.equalsIgnoreCase("show")) {	
						System.out.println("Mostra prodotto");
						int id = Integer.parseInt(request.getParameter("id"));
						ProductBean beanProdottoNascosco = prodotto.doRetrieveByKey(id);
						beanProdottoNascosco.setProdottoMostrato(true);
						prodotto.doUpdate(beanProdottoNascosco);
						
					} else if (action.equalsIgnoreCase("insert")) {
						String name = request.getParameter("name");
						String description = request.getParameter("description");
						double price = Double.parseDouble(request.getParameter("price"));
						int quantity = Integer.parseInt(request.getParameter("quantity"));
						double iva = Double.parseDouble(request.getParameter("iva"));
						boolean showing = Boolean.parseBoolean(request.getParameter("showing"));
						String category = request.getParameter("category");
						
						
						ProductBean beanProdotto = new ProductBean();
						
						
						Part filePart = request.getPart("photo");
						if (filePart != null) {
					  
					            // Obtains input stream of the upload file
								InputStream inputStream = null;
					            inputStream = filePart.getInputStream();
					            beanProdotto.setImmagineUpload(inputStream);
					        }
						
					beanProdotto.setNomeProdotto(name);
					beanProdotto.setDescrizione(description);
					beanProdotto.setPrezzoVetrina(price);
					beanProdotto.setQuantita(quantity);
					beanProdotto.setIva(iva);
					beanProdotto.setProdottoMostrato(showing);
					beanProdotto.setCategoriaProdotto(category);
					   prodotto.doSave(beanProdotto);
						
					} else if (action.equalsIgnoreCase("update")) {
						

						System.out.println("Stai facendo l'update");
						int id = Integer.parseInt(request.getParameter("id"));
						System.out.println("L'id e':"+id);
						String name = request.getParameter("name");
						String description = request.getParameter("description");
						double price = Double.parseDouble(request.getParameter("price"));
						int quantity = Integer.parseInt(request.getParameter("quantity"));
						double iva = Double.parseDouble(request.getParameter("iva"));
						boolean showing = Boolean.parseBoolean(request.getParameter("showing"));
						String category = request.getParameter("category");
						
						ProductBean beanProdotto = new ProductBean();
						beanProdotto.setNomeProdotto(name);
						beanProdotto.setDescrizione(description);
						beanProdotto.setPrezzoVetrina(price);
						beanProdotto.setQuantita(quantity);
						beanProdotto.setIva(iva);
						beanProdotto.setProdottoMostrato(showing);
						beanProdotto.setCategoriaProdotto(category);
						beanProdotto.setIdProdotto(id);

						Part filePart = request.getPart("photo");
						
						
						System.out.println( "La size e':"+filePart.getSize() );
						if (filePart.getSize() != 0) {
				  
							System.out.println("Immagine nuova ottenuta");
				            // Obtains input stream of the upload file
							InputStream inputStream = null;
				            inputStream = filePart.getInputStream();
				            beanProdotto.setImmagineProdotto(null);
				            beanProdotto.setImmagineUpload(null);
				            beanProdotto.setImmagineUpload(inputStream);
				            prodotto.doUpdate(beanProdotto);
				        } else {
				        	prodotto.doUpdateNoImage(beanProdotto);
				        }

						
					}
				}			
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			
			String sort = request.getParameter("sort");

			try {
				request.removeAttribute("products");
				request.setAttribute("products", prodotto.doRetrieveAll(sort));
			} catch (SQLException e) {
				System.out.println("Error products:" + e.getMessage());
			}
			
			try {
				request.removeAttribute("categories");
				request.setAttribute("categories", categoria.doRetrieveAll(sort));
			} catch (SQLException e) {
				System.out.println("Error categories:" + e.getMessage());
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminPage.jsp");
			dispatcher.forward(request, response);

			
		
		
		

		
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
