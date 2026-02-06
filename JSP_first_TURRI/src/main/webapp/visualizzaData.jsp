<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<title>Visualizzatore Data Attuale</title>
</head>
<body>
	<div>
		<h1><%= LocalDate.now().toString() %></h1>
	</div>
</body>
</html>