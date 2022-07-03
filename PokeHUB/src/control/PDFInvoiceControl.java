package control;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
		
		
		Integer id = null;
		try {
			id = Integer.valueOf(request.getParameter("idOrdine"));
		}
		catch (Exception e) {
			System.out.println("Parsing non riuscito del valore Integer");
		}
		

		response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline; filename='Downloaded.pdf'");
  
        
        String path = "D:\\invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        
        
        
        try {
        	

            Document document = new Document();
  
            PdfWriter.getInstance(document, response.getOutputStream());
  
            document.open();
  
            document.add(new Paragraph("GeeksforGeeks"));
            document.add(new Paragraph("This is a demo to write data to pdf\n using servlet\nThank You"));
            document.
  
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
