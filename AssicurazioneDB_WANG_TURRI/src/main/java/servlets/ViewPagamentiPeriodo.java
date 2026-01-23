/*
 * F. calcolare l'ammontare dei pagamenti effettuati da un certo cliente in un determinato periodo di tempo delimitato da due date.
 * */

package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/ViewPagamentiPeriodo")
public class ViewPagamentiPeriodo extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewPagamentiPeriodo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String idCliente = request.getParameter("idCliente");
		String dataInizioStr = request.getParameter("dataInizio");
        String dataFineStr = request.getParameter("dataFine");
        
		out.println(websiteHead);
		
		String htmlPageTop = "<h1>Visualizzazione del totale di pagamenti</h1>"
				+ "<form action='ViewPagamentiPeriodo' method='GET'>"
				+ "<p>Cliente: <input type='text' name='idCliente'></p>"
				+ "<p>Da: <input type='date' name='dataInizio' value='" + (dataInizioStr != null ? dataInizioStr : "") + "'></p>"
				+ "<p>A: <input type='date' name='dataFine' value='" + (dataFineStr != null ? dataFineStr : "") + "'></p>"
				+ "<button type=\"submit\">Calcola</button>"
				+ "</form>";
		out.println(htmlPageTop);
		
		if(dataInizioStr != null && dataFineStr != null) { //stampa SOLO se trova i campi popolati
            
			Date dataInizio = Date.valueOf(dataInizioStr);
			Date dataFine = Date.valueOf(dataFineStr);
			
            double totImporti = accessoDB.calcolaTotImporti(idCliente, dataInizio, dataFine);
            String HTMLPageBottom = "<h2>Importo totale di " + idCliente + " dal " + dataInizio.toString() + " al " + dataFine.toString() + ": " + totImporti + "</h2>";
            out.println(HTMLPageBottom);
        }
		
		out.println(websiteTail);
	}

}
