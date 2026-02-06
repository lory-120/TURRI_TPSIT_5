<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini calcolatrice</title>
</head>
<body>
	
	<form action="calcolatrice.jsp" method="GET">
		<label>Numro 1:</label>
		<input type="number" name="n1">
		<label>Numro 2:</label>
		<input type="number" name="n2">
		<input type="submit" name="Calcola">
	</form>
	
	<%
		String n1str = request.getParameter("n1");
		String n2str = request.getParameter("n2");
		if(n1str != null && n2str != null) {
			try {
				int n1 = Integer.parseInt(n1str);
				int n2 = Integer.parseInt(n2str);
				int sum = n1 + n2;
			
	%>
	<div id="divResult">
		<p>La somma tra <b><%= n1 %></b> e <b><%= n2 %></b> è: <%= sum %></p>
	</div>
	<%
			} catch(NumberFormatException e) {
	%>
	<div id="divError">
		<p>Si è verificato un errore. Riprova.</p>
	</div>
	<%
			}
		}
	%>
	
</body>
</html>