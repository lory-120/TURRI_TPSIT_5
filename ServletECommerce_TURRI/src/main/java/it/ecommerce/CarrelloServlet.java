package it.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class CarrelloServlet
 */
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String websiteHead = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<title>Carrello Prodotti</title>\r\n"
			+ "</head>\r\n"
			+ "<body>";
	private final String websiteTail = "</body>\r\n"
			+ "</html>";
	
    public CarrelloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		out.println(websiteHead);
		
		double totale = 0.0;
		if(session != null) {
			ArrayList<String> carrello = (ArrayList<String>)session.getAttribute("carrello");
			if(carrello != null && !carrello.isEmpty()) {
				out.println("<table border='1' cellpadding='10' cellspacing='10'>");
				out.println("<tr>"
				       + "<th>Nome Articolo</th>"
				       + "<th>Prezzo</th>"
				       + "<th>Azione</th>"
				       + "</tr>");

				// Contatore per l'indice nell'ArrayList per la rimozione
				int indice = 0;
				for(String itemId : carrello) {
				Articolo articolo = Catalogo.ARTICOLI.get(itemId);
				if(articolo != null) {
					out.println("<tr>");
					out.println("<td>" + articolo.getNome() + "</td>");
					out.println("<td>" + articolo.getPrezzo() + " €</td>");
				}
				// Inseriamo accanto al nome dell'articolo il bottone della rimozione
				//(rimanda POST a CarrelloServlet)
				out.println("<td><form method='post' action='CarrelloServlet'>");
				//campo nascosto non mostrato all'utente
				out.println("<input type='hidden' name='rimuoviIndice' value='" + indice + "'>");
				out.println("<input type='submit' value='Rimuovi'>");
				out.println("</form></td>");
				out.println("</tr>");
				totale += articolo.getPrezzo();
				}
				indice++;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
