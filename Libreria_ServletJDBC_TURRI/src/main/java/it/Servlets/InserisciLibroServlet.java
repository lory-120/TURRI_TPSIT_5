package it.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.model.Libro;


@WebServlet("/InserisciLibroServlet")
public class InserisciLibroServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public InserisciLibroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter(); 
		
		//recupera i parametri da tutti gli input
		String isbnInput = request.getParameter("isbn");
		String titoloInput = request.getParameter("titolo");
		String linguaInput = request.getParameter("lingua");
		String annoInputStr = request.getParameter("anno");
		String costoInputStr = request.getParameter("costo");
		String idAutoreInput = request.getParameter("idAutore");
		String idEditoreInput = request.getParameter("idEditore");
		
		out.println(websiteHead);
		
		String htmlPageTop = "<h1>Inserimento di un libro</h1>"
				+ "<form action='InserisciLibroServlet' method='GET'>"
				+ "<p>ISBN: <input type='text' name='isbn' maxlength='13' required></p>"
				+ "<p>Titolo: <input type='text' name='titolo' required></p>"
				+ "<p>Lingua: <input type='text' name='lingua' required></p>"
				+ "<p>Anno: <input type='number' step='1' name='anno' required></p>"
				+ "<p>Costo: <input type='number' step='.01' name='costo' required></p>"
				+ "<p>ID Autore: <input type='text' name='idAutore' maxlength='5' required></p>"
				+ "<p>ID Editore: <input type='text' name='idEditore' maxlength='5' required></p>"
				+ "<button type='submit'>Inserisci libro</button>"
				+ "</form>";
		out.println(htmlPageTop);
		
		if(isbnInput != null && !isbnInput.isEmpty()) { //se l'utente ha compilato i campi...
			Libro l = new Libro();
			l.setIsbn(isbnInput.trim());
			l.setTitolo(titoloInput.trim());
			l.setLingua(linguaInput.trim());
			l.setAnno(Integer.parseInt(annoInputStr.trim()));
			l.setCosto(Double.parseDouble(costoInputStr.trim()));
			l.setIdAutore(idAutoreInput.trim());
			l.setIdEditore(idEditoreInput.trim());
			
			if(accessoLibreria.inserisciLibro(l)) { //se l'inserimento è andato a buon fine
				out.println("<p style='color:green;'>Inserimento del libro " + l.getIsbn() + " completata.</p>");
			} else { //se l'inserimento NON è andato a buon fine
				out.println("<p style='color:red;'>Errore nell'inserimento del libro " + isbnInput + ".<br>"
						+ "Ricontrolla i dati inseriti.</p>");
			}
		}
		
		out.println(websiteTail);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
