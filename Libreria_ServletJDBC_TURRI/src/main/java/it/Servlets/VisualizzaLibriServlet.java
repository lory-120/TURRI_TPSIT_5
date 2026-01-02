package it.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import it.model.Libro;

@WebServlet("/VisualizzaLibriServlet")
public class VisualizzaLibriServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzaLibriServlet() {
        super();
    }

    
    /*ottenere l'elenco dei libri stampati, con ISBN, titolo, autore, editore, in
	 * un determinato periodo di tempo determinato dall'utente*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String dataInizioStr = request.getParameter("dataInizio");
        String dataFineStr = request.getParameter("dataFine");
        
		out.println(websiteHead);
		
		String htmlPageTop = "<h1>Visualizzazione libri</h1>"
				+ "<form action='VisualizzaLibriServlet' method='GET'>"
				+ "<p>Da: <input type='number' name='dataInizio' value='"+ (dataInizioStr != null ? dataInizioStr : "") +"'></p>"
				+ "<p>A: <input type='number' name='dataFine' value='"+ (dataFineStr != null ? dataFineStr : "") +"'></p>"
				+ "<button type=\"submit\">Filtra</button>"
				+ "</form>";
		out.println(htmlPageTop);
		
		if(dataInizioStr != null && dataFineStr != null) { //stampa SOLO se trova i campi popolati
            
			int dataInizio = Integer.parseInt(dataInizioStr);
			int dataFine = Integer.parseInt(dataFineStr);
			
			String htmlPageBottom = "<h2>Risultati dal " + dataInizio + " al " + dataFine + "</h2>"
					+ "<table border='1'>"
					+ "<tr><th>ISBN</th><th>Titolo</th><th>Autore</th><th>Editore</th><th>Anno</th></tr>";
			out.println(htmlPageBottom);
			
            ArrayList<Libro> libri = accessoLibreria.getLibriInPeriodo(dataInizio, dataFine);
            for(Libro l : libri) { //stampa tutti i libri risultanti
                out.println("<tr>");
                out.println("<td>" + l.getIsbn() + "</td>");
                out.println("<td>" + l.getTitolo() + "</td>");
                out.println("<td>" + accessoLibreria.getAutoreByID(l.getIdAutore()) + "</td>");
                out.println("<td>" + accessoLibreria.getEditoreByID(l.getIdEditore()) + "</td>");
                out.println("<td>" + l.getAnno() + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
        }
		
		out.println(websiteTail);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
