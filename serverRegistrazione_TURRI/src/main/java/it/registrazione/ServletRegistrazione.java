package it.registrazione;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletRegistrazione
 */
public class ServletRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String websiteHead = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<title>Registrazione</title>\r\n"
			+ "</head>\r\n"
			+ "<body>";
	private final String websiteTail = "</body>\r\n"
			+ "</html>";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nameInput");
		String email = request.getParameter("emailInput");
		String eta = request.getParameter("etaInput");
		
		PrintWriter out = response.getWriter();
		out.println(websiteHead);
		if(name != null && !name.isBlank() && email != null && !email.isBlank() && Integer.parseInt(eta) > 0) {
			out.println("<h1>Registrazione completata, benvenuto/a " + name + "!<h1>");
		} else {
			out.println("<h1>Errore nella registrazione, ricontrolla i campi.<h1>");
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
