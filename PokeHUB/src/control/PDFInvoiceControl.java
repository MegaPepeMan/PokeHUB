package control;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.OrderDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;

/**
 * Servlet implementation class PDFInvoiceControl
 */
@WebServlet("/PDFInvoiceControl")
public class PDFInvoiceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDFInvoiceControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("application/pdf");
		  
        response.setHeader("Content-disposition","inline; filename = invoice.pdf");
  
        
        
        UserBean utente = (UserBean) request.getSession().getAttribute("userID");		
		if(utente == null) {
			response.sendRedirect(request.getContextPath()+"/LoginPage.jsp");
			return;
		}
		
		Integer id = null;
		try {
			id = Integer.valueOf(request.getParameter("idOrdine"));
		}
		catch (Exception e) {
			System.out.println("Parsing non riuscito del valore Integer");
		}
		OrderDAO ordini = new OrderDAO();
		CompositionDAO fatture = new CompositionDAO();
		ProductDAO fotoProdotti = new ProductDAO();
		OrderBean ordine = new OrderBean();
		try {
			ordine = ordini.doRetrieveByKey(id.intValue());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Collection<CompositionBean> fattura = null;
		try {
			System.out.println("Ricerca composizione dell'ordine");
			fattura = fatture.doRetrieveByOrder(id, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}																																										
		Iterator<CompositionBean> it = fattura.iterator();
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();
		while(it.hasNext()) {
			try {
				prodotti.add( fotoProdotti.doRetrieveByKey(  it.next().getIdentificativo_prodotto()  ) );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
		
		DecimalFormat formatoPrezzo = new DecimalFormat();
		formatoPrezzo.setMaximumFractionDigits(2);
		formatoPrezzo.setMinimumFractionDigits(2);
		double totaleFattura=0;
        
        
        try {
  
            Document document = new Document();
  
            PdfWriter.getInstance(document, response.getOutputStream());
  
            document.open();
  
            document.add(new Paragraph("FATTURA"));
            
            
            Iterator<CompositionBean> iterFattura = fattura.iterator();
            while(iterFattura.hasNext()) {
    			CompositionBean composizione = iterFattura.next();
    			
    			ProductBean prodotto = null;
    			try {
    				prodotto = fotoProdotti.doRetrieveByKey(composizione.getIdentificativo_prodotto());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			document.add(new Paragraph( prodotto.getNomeProdotto() ) );
    			
    			double percentualeIVA = composizione.getIva_acquisto()/100;
				double prezzoConIVA = (percentualeIVA * composizione.getPrezzo_acquisto() ) + composizione.getPrezzo_acquisto();
				double totaleProdotti = prezzoConIVA * composizione.getQuantita();
				totaleFattura += totaleProdotti;
    			
    			document.add(new Paragraph("Quantit\u00E0: " + composizione.getQuantita() +" IVA: "+ composizione.getIva_acquisto()+"% €" + formatoPrezzo.format(totaleProdotti) ) );
    		}
            
            
  
            document.close();
        }
        catch (DocumentException de) {
            throw new IOException(de.getMessage());
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
