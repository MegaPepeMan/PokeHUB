package control;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Base64;
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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.CompositionBean;
import model.CompositionDAO;
import model.OrderBean;
import model.OrderDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;
import model.UserDAO;

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
		UserDAO utenti = new UserDAO();
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
		
		if( !ordine.getMailCliente().equalsIgnoreCase(utente.getMail()) ) {
			if( utente.getCategoriaUtente().equalsIgnoreCase("amministratore") ) {
				
			} else {
				response.sendRedirect(request.getContextPath()+"/userLogged.jsp");
				return;
			}
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
            
            Font fontCliente = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            
            String relativeWebPath = "/Image/Logo.png";
            String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
            
            //Questo statement lancia un'eccezione
            Image logo = Image.getInstance(absoluteDiskPath);
            
            logo.scalePercent(10);
            
            PdfPTable logoAndUser = new PdfPTable(2);
            
            PdfPCell logoSinistro = new PdfPCell(logo);
            
            UserBean utenteFattura = new UserBean();
			try {
				utenteFattura = utenti.doRetrieveByUser(ordine.getMailCliente());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            PdfPCell ClienteDestro = new PdfPCell(new Phrase("Cliente: \n" + utenteFattura.getNome()+ " " + utenteFattura.getCognome()+ "\n" + ordine.getVia() + " " + ordine.getCivico() + " " + ordine.getCap() + "\nN. Telefono: " + ordine.getTelefono(),fontCliente ));
            
            //utente.getNome()+ " " + utente.getCognome()+ "\n" + ordine.getVia() + " " + ordine.getCivico() + " " + ordine.getCap() + "\nN. Telefono: " + ordine.getTelefono()
            
            logoSinistro.setBorder(Rectangle.NO_BORDER);
            ClienteDestro.setBorder(Rectangle.NO_BORDER);
            ClienteDestro.setHorizontalAlignment(Element.ALIGN_RIGHT);
            logoAndUser.addCell(logoSinistro);
            logoAndUser.addCell(ClienteDestro);
            logoAndUser.setSpacingAfter(13);
            logoAndUser.setWidthPercentage(95);
            document.add(logoAndUser);

            
            PdfPTable pdfPTable = new PdfPTable(6);
            
            pdfPTable.addCell("Immagine");
            pdfPTable.addCell("Articolo");
            pdfPTable.addCell("Quantit\u00E0");
            pdfPTable.addCell("IVA");
            pdfPTable.addCell("Prezzo con IVA per unit\u00E0");
            pdfPTable.addCell("Prezzo con IVA totale");
            
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
    			
    			double percentualeIVA = composizione.getIva_acquisto()/100;
				double prezzoConIVA = (percentualeIVA * composizione.getPrezzo_acquisto() ) + composizione.getPrezzo_acquisto();
				double totaleProdotti = prezzoConIVA * composizione.getQuantita();
				totaleFattura += totaleProdotti;
				
				byte[] decoded = Base64.getDecoder().decode(prodotto.getImmagineProdotto());
				Image immagineProdotto = Image.getInstance(decoded);
				immagineProdotto.scalePercent(10);
				
				pdfPTable.addCell(immagineProdotto);
				pdfPTable.addCell(prodotto.getNomeProdotto());
				pdfPTable.addCell( String.valueOf(composizione.getQuantita()) );
				pdfPTable.addCell( formatoPrezzo.format(composizione.getIva_acquisto())+"%" );
				pdfPTable.addCell("\u20ac " + formatoPrezzo.format(prezzoConIVA) );
				pdfPTable.addCell("\u20ac " + formatoPrezzo.format(totaleProdotti) );
				
		        
			 
    		}
            PdfPTable pdfPTable2 = new PdfPTable(2);
            
            pdfPTable2.addCell(new Phrase("Totale ordine: ",fontCliente));
            pdfPTable2.addCell(new Phrase("\u20ac " + formatoPrezzo.format(totaleFattura),fontCliente));
            pdfPTable.setWidthPercentage(95);
            pdfPTable2.setWidthPercentage(95);
            document.add(pdfPTable);            
            document.add(pdfPTable2);
            
            
  
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
