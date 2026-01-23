/*
 * B. ottenere l'elenco dei pagamenti effettuati da un cliente in un determinato perio-do di tempo tra due date specificate;
 * */

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pagamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/ListaPagamentiData")
public class ListaPagamentiData extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaPagamentiData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String idCliente = request.getParameter("idCliente");
		String dataInizioStr = request.getParameter("dataInizio");
        String dataFineStr = request.getParameter("dataFine");
        
		out.println(websiteHead);
		
		String htmlPageTop = "<h1>Visualizzazione pagamenti</h1>"
				+ "<form action='ListaPagamentiData' method='GET'>"
				+ "<p>Cliente: <input type='text' name='idCliente'></p>"
				+ "<p>Da: <input type='date' name='dataInizio' value='" + (dataInizioStr != null ? dataInizioStr : "") + "'></p>"
				+ "<p>A: <input type='date' name='dataFine' value='" + (dataFineStr != null ? dataFineStr : "") + "'></p>"
				+ "<button type=\"submit\">Filtra</button>"
				+ "</form>";
		out.println(htmlPageTop);
		
		if(dataInizioStr != null && dataFineStr != null) { //stampa SOLO se trova i campi popolati
            
			Date dataInizio = Date.valueOf(dataInizioStr);
			Date dataFine = Date.valueOf(dataFineStr);
			
			String HTMLPageBottom = "<h2>Risultati dal " + dataInizio.toString() + " al " + dataFine.toString() + "</h2>"
					+ "<table border='1'>"
					+ "<tr><th>ID Pagamento</th><th>ID Polizza</th><th>Data Scadenza</th><th>Data Pagamento</th><th>Importo</th><th>Note</th></tr>";
			out.println(HTMLPageBottom);
			
            ArrayList<Pagamento> pagamentiFiltrati = accessoDB.viewPagamentiInPeriodo(idCliente, dataInizio, dataFine);
            for(Pagamento p : pagamentiFiltrati) { //stampa tutti i pagamenti filtrati
                out.println("<tr>");
                out.println("<td>" + p.getId_pagamento() + "</td>");
                out.println("<td>" + p.getId_polizza() + "</td>");
                out.println("<td>" + p.getDt_scadenza() + "</td>");
                out.println("<td>" + p.getDt_pagamento() + "</td>");
                out.println("<td>" + p.getImporto() + "</td>");
                out.println("<td>" + p.getNote() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
		
		out.println(websiteTail);
	}

}
