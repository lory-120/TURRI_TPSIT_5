package it.sitoturri;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServerSaluto
 */
public class ServerSaluto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String websiteHead =
			"<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<title>Saluto con Servlet</title>\r\n"
			+ "</head>\r\n"
			+ "<body>";
	private final String websiteTail = 
			"</body>\r\n"
			+ "</html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerSaluto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//leggi il parametro nome dalla pagina
		String nome = request.getParameter("nomeImmesso"); //si prende il "name" che gli ho messo nella pagina
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		PrintWriter out = response.getWriter(); //prendo lo stream di output
		
		out.println(websiteHead);
		if(nome != null && !nome.isBlank()) {
			out.println("<h1>Ciao, " + nome + "!</h1>");
		} else {
			out.println("<h1>Non hai inserito un nome.</h1>");
		}
		out.println(websiteTail);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
