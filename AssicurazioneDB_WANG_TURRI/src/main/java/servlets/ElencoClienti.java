/*
 * E. ottenere l'elenco dei clienti che hanno almeno due polizze attive;
 * */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet("/ElencoClienti")
public class ElencoClienti extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ElencoClienti() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		out.println(websiteHead);
		
		String HTMLPageTop = "<h1>Clienti con almeno due polizze attive</h1>";
		out.println(HTMLPageTop);
		
		ArrayList<Cliente> clientiConDuePolizze = accessoDB.viewClientiConDuePolizze();
		
		out.println("<table border='1'>");
		out.println("<tr><th>ID Cliente</th><th>Cognome</th><th>Nome</th><th>N° Polizze</th></tr>");
		for(Cliente c : clientiConDuePolizze) {
			String tmp = "<tr>"
					+ "<td>" + c.getId_cliente() + "</td>"
					+ "<td>" + c.getCognome() + "</td>"
					+ "<td>" + c.getNome() + "</td>"
					+ "<td>" + c.getN_polizze() + "</td>"
					+ "</tr>";
			out.println(tmp);
		}
		out.println("</table>");
		
		out.println(websiteTail);
	}

}
