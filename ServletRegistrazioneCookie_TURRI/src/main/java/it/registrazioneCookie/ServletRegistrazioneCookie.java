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
 * Servlet implementation class ServletRegistrazioneCookie
 */
public class ServletRegistrazioneCookie extends HttpServlet {
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
    public ServletRegistrazioneCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prendo i dati dalla pagina
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		String favoriteColor = request.getParameter("colorInput");
		
		//creo i cookie
		Cookie usernameCookie = new Cookie("user", username);
		Cookie passwordCookie = new Cookie("psw", password);
		Cookie colorCookie = new Cookie("color", favoriteColor);
		usernameCookie.setPath("/");
		passwordCookie.setPath("/");
		colorCookie.setPath("/");
		
		//dò i cookie al server
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);
		response.addCookie(colorCookie);
		
		//faccio la nuova pagina di risposta
		PrintWriter out = response.getWriter();
		out.println(websiteHead);
		out.println("<h1>Benvenuto, " + username + "! Il tuo colore preferito e' " + favoriteColor + ".</h1>");
		out.println("<button type=\"submit\">Vedi i cookie registrati</button>");
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
