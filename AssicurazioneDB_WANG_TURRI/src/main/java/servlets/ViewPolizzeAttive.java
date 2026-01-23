/*
 * A. ottenere l'elenco delle polizze ancora attive (dt_estinzione non avvalorata) stipulate da un certo cliente;
 * */

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Polizza;
import utilities.ClienteNonTrovatoException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ViewPolizzeAttive")
public class ViewPolizzeAttive extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewPolizzeAttive() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String idCliente = request.getParameter("idCliente");
		
		String HTMLPageTop = "<h1>Visualizzazione polizze attive</h1>"
				+ "<form action='ViewPolizzeAttive' method='GET'>"
				+ "<p>ID Cliente: <input type='text' name='idCliente' required></p>"
				+ "<button type='submit'>Visualizza polizze attive</button>"
				+ "</form>";
		out.println(HTMLPageTop);
		
		ArrayList<Polizza> polizzeAttive;
		
		if(idCliente != null) {
			try {
				polizzeAttive = accessoDB.viewPolizzeAttive(idCliente);
				
				out.println("<table border='1'>");
				out.println("<tr><th>ID Polizza</th><th>ID Cliente</th><th>Data Inizio</th><th>Data fine</th><th>Periodicità</th><th>Premio annuo</th><th>Descrizione</th></tr>");
				for(Polizza p : polizzeAttive) {
					String dtInizio = (p.getDt_inizio() != null) ? p.getDt_inizio().toString() : "N/D";
	                String dtFine = (p.getDt_fine() != null) ? p.getDt_fine().toString() : "<i>In corso</i>";
					String tmp = "<tr>"
							+ "<td>" + p.getId_polizza() + "</td>"
							+ "<td>" + p.getId_cliente() + "</td>"
							+ "<td>" + dtInizio + "</td>"
							+ "<td>" + dtFine + "</td>"
							+ "<td>" + p.getPeriodicita_AST() + "</td>"
							+ "<td>" + p.getPremio_annuo() + "</td>"
							+ "<td>" + p.getDescrizione() + "</td>"
							+ "</tr>";
					out.println(tmp);
				}
				out.println("</table>");
			} catch(ClienteNonTrovatoException e) {
				System.err.println(e.getMessage());
				out.println("<p style='color: red;'>Cliente non trovato.</p>");
			}
		}
		
	}

}
