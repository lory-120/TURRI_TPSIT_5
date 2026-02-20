<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi attività</title>
</head>
<body>
	
	<h1>Aggiungi attività</h1>
	<form action="gestione.jsp" method="POST">
		<label>Descrizione: </label> <input type="text" name="testo" required>
		<button type="submit" name="aggiungi" value="1"></button>
	</form>
	
	<p><a href="todo.jsp">Vai alla lista</a></p>
	
</body>
</html>