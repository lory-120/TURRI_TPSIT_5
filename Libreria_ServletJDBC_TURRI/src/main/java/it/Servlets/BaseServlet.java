package it.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import it.dao.AccessoLibreria;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected AccessoLibreria accessoLibreria;
    protected String websiteHead = "<!DOCTYPE html>\r\n"
    		+ "<html>\r\n"
    		+ "	<head>\r\n"
    		+ "		<meta charset=\"UTF-8\">\r\n"
    		+ "		<title>Libreria</title>\r\n"
    		+ "	</head>\r\n"
    		+ "	<body>";
    protected String websiteTail = "	</body>\r\n"
    		+ "</html>";
	
	
    public BaseServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	try {
            this.accessoLibreria = new AccessoLibreria("localhost", "libri", "root", "");
            System.out.println("DAO Inizializzato con successo.");
        } catch (Exception e) {
            System.out.println("ERRORE INIZIALIZZAZIONE DAO: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException(e); //questo avvisa Tomcat che la servlet è KO
        }
    }

}
