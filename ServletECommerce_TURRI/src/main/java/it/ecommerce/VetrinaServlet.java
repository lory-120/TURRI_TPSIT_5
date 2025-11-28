package it.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.apache.jasper.tagplugins.jstl.core.If;

/**
 * Servlet implementation class VetrinaServlet
 */
public class VetrinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String websiteHead = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<title>Catalogo Prodotti</title>\r\n"
			+ "</head>\r\n"
			+ "<body>";
	private final String websiteTail = "</body>\r\n"
			+ "</html>";
	
	private final String welcomePhraseFirstTime = "<h1>Benvenuto per la prima volta!</h1>";
	private final String welcomePhrase = "<h1>Bentornato!</h1>";
	
    public VetrinaServlet() {
        super();
    }
    
    //chiamato 1 volta al caricamento del servlet
    public void init() {
    	Catalogo.caricaArticoli();
    }
    
    /*
     * Si utilizza doGet() per tutte le operazioni di visualizzazione, e
     * doPost() per tutte le modifiche alla pagina.
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//"true" permette di creare una nuova session se questa non esiste
		HttpSession session = request.getSession(true);
		
		boolean doWelcome = false;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("visitaNegozio") && cookie.getValue().equals("si")) {
					doWelcome = true;
					break;
				}
			}
		}
		
		out.println(websiteHead);
		if(doWelcome == true) {
			out.println(welcomePhraseFirstTime);
		} else {
			out.println(welcomePhrase);
		}
		
		out.println("<h3>Prodotti disponibili:</h3>");
		out.println("<table border='1' cellspacing='10'>");
		out.println("<tr>"
        		+ "<th>ID</th>"
        		+ "<th>Nome</th>"
        		+ "<th>Prezzo</th>"
        		+ "<th>Azione</th>"
        		+ "</tr>");
		for(Articolo articolo : Catalogo.ARTICOLI.values()) {
        	//per ogni articolo dell'hashmap inseriamo una riga della tabella
            out.println("<tr>");
            out.println("<td>" + articolo.getID() + "</td>");
            out.println("<td>" + articolo.getNome() + "</td>");
            out.println("<td>" + articolo.getPrezzo() + " €</td>");
            // aggiungiamo in una cella il pulsante aggiungi al carrello
            out.println("<td><form method='post' action='CatalogoServlet'>"); 
	            //<input type='hidden'>: Questo è un campo di input nascosto all'utente. Non viene visualizzato nella pagina, 
            	//ma il suo contenuto verrà comunque inviato al server.
            	out.println("<input type='hidden' name='itemId' value='" + articolo.getID() + "'>");
	            out.println("<input type='submit' value='Aggiungi al Carrello'>");
            out.println("</form></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        
        out.println("<p>Session ID: " + session.getId() + "</p>");
        
        out.println("<a href='CarrelloServlet'>Vai al carrello</a>");
        
        out.println(websiteTail);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero l'ID del prodotto che l'utente ha scelto
		String itemID = request.getParameter("itemID");
		//controllo se esiste la session
		HttpSession session = request.getSession();
		if(itemID != null && Catalogo.ARTICOLI.containsKey(itemID)) {
			ArrayList<String> carrello = (ArrayList<String>)session.getAttribute(itemID);
			
			if(carrello == null) {
				carrello = new ArrayList<>();
				session.setAttribute("carrello", carrello);
			}
			carrello.add(itemID);
		}
		//pattern PRG, che evita il doppio invio
		response.sendRedirect("VetrinaServlet");
	}

}
