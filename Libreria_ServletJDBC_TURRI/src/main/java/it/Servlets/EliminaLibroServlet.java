package it.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EliminaLibroServlet")
public class EliminaLibroServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminaLibroServlet() {
        super();
    }

    /*eliminare un libro dal DB a partire dal suo codice ISBN*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter();
		
		String isbnInput = request.getParameter("isbn");
		
		out.println(websiteHead);
		
		String htmlPageTop = "<h1>Eliminazione di un libro</h1>"
				+ "<form action='EliminaLibroServlet' method='GET'>"
				+ "<p>ISBN da eliminare: <input type='text' name='isbn' maxlength='13' required></p>"
				+ "<button type='submit'>Elimina</button>"
				+ "</form>";
		out.println(htmlPageTop);
		
		if(isbnInput != null && isbnInput.trim().length() == 13) { //se l'utente ha messo un ISBN...
			//controlla se elimina il libro correttamente
			if(accessoLibreria.eliminaConISBN(isbnInput)) {
				out.println("<p style='color:green;'>Eliminazione del libro " + isbnInput + " completata.</p>");
			} else {
				out.println("<p style='color:red;'>Errore nell'eliminazione di " + isbnInput + ".<br>");
				out.println("Ricontrolla l'ISBN immesso.</p>");
			}
		} else if(isbnInput != null) { //se l'utente ha messo qualcosa ma non di 13 cifre
	        out.println("<p style='color:orange;'>L'ISBN deve essere di 13 cifre.</p>");
	    }
		
		out.println(websiteTail);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
