/*
 * C. inserire un nuovo pagamento per una certa polizza;
 * */

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pagamento;
import utilities.PolizzaInesistenteException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/InsertPagamento")
public class InsertPagamento extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertPagamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String idPolizza = request.getParameter("idPolizza");
		String dtScadenzaStr = request.getParameter("dtScadenza");
		String dtPagamentoStr = request.getParameter("dtPagamento");
		String importoRaw = request.getParameter("importo");
		String note = request.getParameter("note");
		
		out.println(websiteHead);
		
		String HTMLPageTop = "<h1>Inserimento pagamento</h1>"
				+ "<form action='InsertPagamento' method='GET'>"
				+ "<p>ID Polizza assicurativa: <input type='text' name='idPolizza' required></p>"
				+ "<p>Data scadenza: <input type='date' name='dtScadenza' required></p>"
				+ "<p>Data pagamento: <input type='date' name='dtPagamento' required></p>"
				+ "<p>Importo: <input type='number' min='0' step='0.01' name='importo' required></p>"
				+ "<p>Note: <textarea rows='4' cols='100' name='note'></textarea></p>"
				+ "<button type='submit'>Inserisci</button>"
				+ "</form>";
		out.println(HTMLPageTop);
		
		if(idPolizza != null && dtScadenzaStr != null && dtPagamentoStr != null && importoRaw != null) {  //stampa SOLO se trova i campi popolati
			boolean success;
			
			Pagamento p = new Pagamento();
			p.setId_polizza(idPolizza);
			p.setDt_scadenza(Date.valueOf(LocalDate.parse(dtScadenzaStr)));
			p.setDt_pagamento(Date.valueOf(LocalDate.parse(dtPagamentoStr)));
			p.setImporto(Double.parseDouble(importoRaw));
			p.setNote(note);
			try {
				accessoDB.insertPagamento(p);
				success = true;
			} catch (PolizzaInesistenteException e) {
				System.err.println("Errore nell'inserimento del pagamento: " + e.getMessage());
				success = false;
			}
			
			if(success) {
				out.println("<p style='color: green;'>Inserimento riuscito.</p>");
			} else {
				out.println("<p style='color: red;'>Inserimento non riuscito.</p>");
			}
		}
		
		out.println(websiteTail);
	}

}
