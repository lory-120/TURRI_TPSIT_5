<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.*, dao.*" %>
    
<%
    //LOGICA DI RICEZIONE DATI
    if(request.getParameter("chiudiID") != null) {
    	int IDDaChiudere = Integer.parseInt(request.getParameter("chiudiID"));
    	boolean success = AccessoTicket.segnaFatto(IDDaChiudere);
    	response.sendRedirect("PaginaTecnico.jsp");
    	return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Tecnico - Gestione Guasti</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

	<h1>Gestione Ticket - Area Tecnico</h1>
	
	<table border='1'>
		<tr><th>Richiesta</th><th>Urgenza</th><th>Stato</th><th>Operazioni</th></tr>
		<% for(Ticket t : AccessoTicket.getTuttiITicket()) { 
			if(!t.isDone()) {
		%>
		<tr>
			<td>
				<%= t.getRichiesta() %>
			</td>
			<td>
				<%= t.getUrgenza() %>
			</td>
			<td>
				<%= t.isDone() ? "Risolto" : "In lavorazione" %>
			</td>
			<td>
				<form action="PaginaTecnico.jsp" method="POST">
                    <input type="hidden" name="chiudiID" value="<%= t.getID() %>">
                    <button type="submit">Segna come Chiuso</button>
                </form>
			</td>
		</tr>
		<% } } %>
	</table>

</body>
</html>