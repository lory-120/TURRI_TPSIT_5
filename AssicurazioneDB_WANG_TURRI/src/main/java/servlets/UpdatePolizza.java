/*
 * D. aggiornare lo stato di una polizza nel momento in cui essa viene estinta (inseri-mento data estinzione);
 * */

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utilities.PolizzaInesistenteException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdatePolizza")
public class UpdatePolizza extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePolizza() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String idPolizza = request.getParameter("idPolizza");
		
		out.println(websiteHead);
		
		String HTMLPageTop = "<h1>Estinzione Polizza</h1>"
				+ "<form action='UpdatePolizza' method='GET'>"
				+ "<p>ID Polizza assicurativa: <input type='text' name='idPolizza' required></p>"
				+ "<button type='submit'>Estingui polizza</button>"
				+ "</form>";
		out.println(HTMLPageTop);
		
		if(idPolizza != null) { //stampa SOLO se trova i campi popolati
			boolean success;
			
			try {
				accessoDB.estinguiPolizza(idPolizza);
				success = true;
			} catch(PolizzaInesistenteException e) {
				System.err.println("Errore nell'estinzione della polizza: " + e.getMessage());
				success = false;
			}
			
			if(success) {
				out.println("<p style='color: green;'>Estinzione riuscita.</p>");
			} else {
				out.println("<p style='color: red;'>Estinzione non riuscita.</p>");
			}
		}
		
		out.println(websiteTail);
	}

}
