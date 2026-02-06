<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contatore Accessi</title>
</head>
<body>
    <form action="contaAccessi.jsp" method="GET">
        <label>Nome: </label>
        <input type="text" name="nome" placeholder="il tuo nome" required>
        <input type="submit" value="Invia">
    </form>
    
    <%
        Integer count = 0;
        String nomeStr = request.getParameter("nome");
        String nomeView = "Guest";
        
        if(nomeStr != null) {
        	if(nomeStr.trim().isEmpty()) {
        		session.setAttribute("nomeutente", nomeStr);
        	}
        	
        	if(session.getAttribute("nomeutente") != null) {
        		nomeView = (String)session.getAttribute("nomeutente");
        	}
        	
        	count = (Integer)session.getAttribute("contatore");
        	if(count == null) {
        		count = 0;
        	}
        	
        	count++;
            session.setAttribute("contatore", count);
        }
        
    %>
    
    <h2>Ciao <%=nomeView %>!</h2>
    <h3>Hai effettuato <%=count %> accessi.</h3>
    
    <a href="contaAccessi.jsp">Ricarica pagina e incrementa il numero di accessi</a>
</body>
</html>