package it.sondaggioCulinario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Servlet implementation class sondaggioCulinario
 */
public class SondaggioCulinario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String fileName = "risposte.csv";
	private static final String REGEX = ",";
	
	private final String websiteHead = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"UTF-8\">\r\n"
			+ "<title>Sondaggio Culinario</title>\r\n"
			+ "</head>\r\n"
			+ "<body>";
	private final String websiteTail = "</body>\r\n"
			+ "</html>";
 
    /**
     * Default constructor. 
     */
    public SondaggioCulinario() {
    	super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset/UTF-8"); //imposto il contenuto della risposta
		String selezione = request.getParameter("piattoPreferito");
		
		boolean success = writeAnswerToFile(selezione, fileName);
		
		int selezioni[] = getSelezioniTot(fileName);
		int nSelTot = Arrays.stream(selezioni).sum(); //somma tutte le selezioni, per fare il totale
		
		PrintWriter out = response.getWriter(); //prendo lo stream di output
		
		out.println(websiteHead);
		if(success) {
			out.println("<h1>Risposta registrata. Grazie!</h1>");
			out.println("<p>Hai scelto: <b>" + selezione + "</b></p> <br>");
			out.println("<p>Risultati:");
			out.println("Pizza: " + (double)(selezioni[0]/nSelTot)*100 + "% - Voti: " + selezioni[0]);
			out.println("Spaghetti: " + (double)(selezioni[1]/nSelTot)*100 + "% - Voti: " + selezioni[1]);
			out.println("Pastina: " + (double)(selezioni[2]/nSelTot)*100 + "% - Voti: " + selezioni[2]);
			out.println("Lasagne: " + (double)(selezioni[3]/nSelTot)*100 + "% - Voti: " + selezioni[3]);
			out.println("</p> <br>");
			out.println("<h2>Totale voti: " + nSelTot + "</h2>");
		} else {
			
		}
		out.println(websiteTail);

		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private boolean writeAnswerToFile(String selezione, String fileName) {
		File test = new File(fileName);
		if(!test.exists()) {
			try {
				test.createNewFile();
			} catch (IOException e) {
				return false;
			}
		}
		
		try(PrintWriter out = new PrintWriter(new FileWriter(fileName, true))) {
			out.println(selezione + REGEX);
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	private int[] getSelezioniTot(String fileName) {
		int[] selezioniTot = new int[4];
		String value;
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			while((value = in.readLine()) != null) {
				String tmp = value.split(REGEX)[0];
				
				switch(tmp) {
					case "Pizza":
						selezioniTot[0]++;
						break;
					case "Spaghetti":
						selezioniTot[1]++;
						break;
					case "Pastina":
						selezioniTot[2]++;
						break;
					case "Lasagne":
						selezioniTot[3]++;
						break;
					default: //(nulla)
				}
			}
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file: " + e.getMessage());
		}
		
		return selezioniTot;
	}

}
