<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TODO</title>
</head>
<body>

	<%
	
	ArrayList<Attivita> listaAttivita = (ArrayList<Attivita>)session.getAttribute("listaAttivita");
	if(listaAttivita.isEmpty() || listaAttivita == null) {
	
	%>
	
	<p>Nessuna attività inserita.</p>
	
	<%
	
	} else {
		for(Attivita a : listaAttivita) {
			

	
	%>
	
	<ul></ul>
	
	<%
	
		}
	}
	
	%>

</body>
</html>