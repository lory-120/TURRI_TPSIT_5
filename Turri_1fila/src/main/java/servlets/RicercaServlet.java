package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Docente;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RicercaServlet")
public class RicercaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RicercaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String codiceMecc = request.getParameter("codiceMecc");
		
		out.println(websiteHead);
		
		String HTMLPageTop = "<h1>Ricerca docenti in servizio</h1>"
				+ "<form action='RicercaServlet' method='GET'>"
				+ "<p>Codice Meccanografico scuola: <input type='text' name='codiceMecc' required></p>"
				+ "<button type='submit'>Ricerca</button>"
				+ "</form>";
		out.println(HTMLPageTop);
		
		if(codiceMecc != null) {
			out.println("<h2>Docenti in serivzio nella scuola di condice " + codiceMecc + "</h2>");
			out.println("<table border='1'>");
			out.println("<tr><th>Codice Fiscale</th><th>Cognome</th><th>Nome</th><th>Inizio servizio</th></tr>");
			for(Docente d : accessoDB.getDocentiInServizio(codiceMecc)) {
				out.println("<tr>");
                out.println("<td>" + d.getCod_fiscale() + "</td>");
                out.println("<td>" + d.getCognome() + "</td>");
                out.println("<td>" + d.getNome() + "</td>");
                out.println("<td>" + d.getDt_inizio() + "</td>");
                out.println("</tr>");
			}
			out.println("</table>");
		}
		
		out.println(websiteTail);
	}

}
