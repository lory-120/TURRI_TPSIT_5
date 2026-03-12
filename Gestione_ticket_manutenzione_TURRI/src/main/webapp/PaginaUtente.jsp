<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.*" %>

<%
    //LOGICA DI RICEZIONE DATI
    String richiesta = request.getParameter("richiesta");
    String urgenzaStr = request.getParameter("urgenza");

    if(richiesta != null && urgenzaStr != null) {
        Urgenza urgenza = Urgenza.valueOf(urgenzaStr);
        Ticket nuovoTicket = new Ticket(urgenza, richiesta);
        AccessoTicket.aggiungiTicket(nuovoTicket);
        
        response.sendRedirect("PaginaUtente.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Utente - Segnalazione Guasti</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <h1>Gestione Segnalazioni Scuola</h1>

    <div class="form-box">
        <h3>Crea un nuovo Ticket</h3>
        <form method="POST">
            <label>Cosa non funziona?</label><br>
            <input type="text" name="richiesta" placeholder="La tua richesta..." required><br><br>

            <label>Urgenza:</label>
            <select name="urgenza">
                <% for(Urgenza u : Urgenza.values()) { %>
                    <option value="<%= u.name() %>"><%= u.name() %></option>
                <% } %>
            </select><br><br>

            <input type="submit">
        </form>
    </div>

    <hr>

    <h3>I tuoi Ticket inviati</h3>
    <table>
            <tr><th>Richiesta</th><th>Urgenza</th><th>Stato</th></tr>
            <% 
                List<Ticket> mieiTicket = AccessoTicket.getTuttiITicket();
                for(Ticket t : mieiTicket) { 
            %>
            <tr>
                <td><%= t.getRichiesta() %></td>
                <td><b><%= t.getUrgenza() %></b></td>
                <td><%= t.isDone() ? "Chiuso" : "In lavorazione" %></td>
            </tr>
            <% } %>
    </table>

</body>
</html>