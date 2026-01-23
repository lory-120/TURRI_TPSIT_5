package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import dao.AccessoDB;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected AccessoDB accessoDB;
    protected String websiteHead = "<!DOCTYPE html>\r\n"
    		+ "<html>\r\n"
    		+ "	<head>\r\n"
    		+ "		<meta charset=\"UTF-8\">\r\n"
    		+ "		<title>Ufficio Scolastico</title>\r\n"
    		+ "	</head>\r\n"
    		+ "	<body>";
    protected String websiteTail = "	</body>\r\n"
    		+ "</html>";
	
    public BaseServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		try {
            this.accessoDB = new AccessoDB("localhost", "ufficio_scolastico", "root", "");
            System.out.println("DAO Inizializzato con successo.");
        } catch (Exception e) {
            System.out.println("ERRORE INIZIALIZZAZIONE DAO: " + e.getMessage());
            throw new ServletException(e); //questo avvisa Tomcat che la servlet è KO
        }
	}

}
