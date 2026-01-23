package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Servizio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/InserimentoServlet")
public class InserimentoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public InserimentoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String codFis = request.getParameter("codFis");
		String codScuola = request.getParameter("codScuola");
		String dtInizioStr = request.getParameter("dtInizio");
		String ruolo = request.getParameter("ruolo");
		
		out.println(websiteHead);
		
		String HTMLPageTop = "<h1>Inserimento Servizio docente</h1>"
				+ "<form action='InserimentoServlet' method='GET'>"
				+ "<p>Codice fiscale: <input type='text' name='codFis' required></p>"
				+ "<p>Codice Meccanografico Scuola: <input type='text' name='codScuola' required></p>"
				+ "<p>Data inizio servizio: <input type='date' name='dtInizio' required></p>"
				+ "<p>Ruolo: <input type='text' name='ruolo' required></p>"
				+ "<button type='submit'>Inserisci</button>"
				+ "</form>";
		out.println(HTMLPageTop);
		
		if(codFis != null && codScuola != null && dtInizioStr != null && ruolo != null) {
			Date dtInizio = Date.valueOf(dtInizioStr);
			
			Servizio s = new Servizio();
			s.setCod_fiscale(codFis);
			s.setCod_meccanografico(codScuola);
			s.setDt_inizio(dtInizio);
			s.setRuolo(ruolo);
			
			if(accessoDB.inserisciServizio(s)) {
				out.println("<p style='color: green;'>Inserimento riuscito.</p>");
			} else {
				out.println("<p style='color: red;'>Inserimento non riuscito.</p>");
			}
			
		}
		
		out.println(websiteTail);
	}

}
