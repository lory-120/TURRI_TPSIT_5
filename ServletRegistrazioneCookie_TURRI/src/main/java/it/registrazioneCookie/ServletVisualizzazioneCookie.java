package it.registrazioneCookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletVisualizzazioneCookie
 */
public class ServletVisualizzazioneCookie extends HttpServlet {
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
    public ServletVisualizzazioneCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		PrintWriter out = response.getWriter();
		
		out.println(websiteHead);
		if (cookies != null) {
            out.println("<ul>"); //apro una lista per ordine
            
            for(Cookie cookie : cookies) {
                String nome = cookie.getName();
                String valore = cookie.getValue();
                
                out.println("<li>Nome Cookie: <strong>" + nome + "</strong> | Valore: " + valore + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<h3>Nessun cookie trovato! (Hai fatto la registrazione?)</h3>");
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
