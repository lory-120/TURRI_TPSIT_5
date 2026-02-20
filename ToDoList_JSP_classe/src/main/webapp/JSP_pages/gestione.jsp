<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>

<%
	ArrayList<Attivita> listaAttivita = (ArrayList)session.getAttribute("listaAttivita");
	
	if(listaAttivita == null) {
		listaAttivita = new ArrayList<>();
		session.setAttribute("ListaAttivita", listaAttivita);
	}
	
	String attivitaDaAggiungere = request.getParameter("aggiungi");
	String testo = request.getParameter("testo");
	
	listaAttivita.add(new Attivita(testo.trim()));
	
	response.sendRedirect("todo.jsp");
	
%>