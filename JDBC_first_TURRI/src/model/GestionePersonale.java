package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.GestoreDB;

public class GestionePersonale {
	Connection conn = null;
	// parametri connessione
	final String server = "localhost";
	final String database = "Azienda";
	final String utente = "root";
	final String password = "12345678";
	
	public GestionePersonale(String server, String db, String utente, String password) throws SQLException {
		conn = new GestoreDB(server, database, utente, password).getConnection();
	}
	
	public boolean close() {
		try {
			conn.close();
		} catch (SQLException exception) {
			return false;
		}
		return true;
	}
	

	//metodi CRUD da implementare
	//C-Create ->Inserimento unità di personale nel database
	//R-Read ->  Ricerca nel database i dati di una unità di personale
	//U-Update -> Aggiornamento dati unità di personale nel database
	//D-Delete -> Eliminazione unità di personale dal database
	
	/* Inserimento dipendente nel database */
	public void aggiungiPersonale(Personale personale) throws SQLException {
		String query = "USE personale;"
				+ "INSERT INTO Personale"
				+ "(matricola, id_dipartimento, nominativo, data_nascita, qualifica, stipendio) VALUES"
				+ "(?, ?, ?, ?, ?, ?);";
		String id_dip = cercaIDDipartimento(personale.getDipartimento());
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, personale.getMatricola());
		ps.setString(2, id_dip);
		ps.setString(3, personale.getNominativo());
		ps.setDate(4, Date.valueOf(personale.getDataNascita()));
		ps.setString(5, personale.getQualifica());
		ps.setDouble(6, personale.getStipendio());
		
		//per l'INSERT, DELETE e UPDATE si usa executeUpdate, mentre per le SELECT si usa execute
		ps.executeUpdate();
	}
	
	public String cercaIDDipartimento(String src) throws SQLException {
		String query = "USE personale;"
				+ "SELECT id_dipartimento FROM Dipartimenti WHERE nome_dipartimento = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, src);
		
		//adesso maneggiamo i risultati
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getString("id_dipartimento");
		} else {
			throw new SQLException("Dipartimento non trovato nel DB: " + src);
		}
	}
	
	public ArrayList<Personale> elencaPersonale() throws SQLException {
		ArrayList<Personale> lista = new ArrayList<>();
		
		String query = "USE personale;"
				+ "SELECT p.*, d.nome_dipartimento"
				+ "FROM personale p"
				+ "JOIN dipartimenti d ON p.id_dipartimento = d.id_dipartimento;";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Personale p = new Personale();
			p.setMatricola(rs.getString("matricola"));
			p.setNominativo(rs.getString("nominativo"));
			p.setQualifica(rs.getString("qualifica"));
			p.setStipendio(rs.getDouble("stipendio"));
			p.setDataNascita(rs.getDate("data_nascita").toLocalDate());
			p.setDipartimento(rs.getString("dipartimento"));
			lista.add(p);
		}
		
		return lista;
	}
	
	//aggiorna dati dipendenti dato l'oggetto p che punta a un dipendente
    public void updatePersonale(Personale p) throws SQLException {
        String query = "USE personale;"
        		+ "UPDATE personale"
        		+ "SET id_dipartimento=?, nominativo=?, data_nascita=?, qualifica=?, stipendio=?"
        		+ "WHERE matricola=?";
        String idDip = cercaIDDipartimento(p.getDipartimento());
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, idDip);
        ps.setString(2, p.getNominativo());
        ps.setDate(3, Date.valueOf(p.getDataNascita()));
        ps.setString(4, p.getQualifica());
        ps.setDouble(5, p.getStipendio());
        
        //per l'INSERT, DELETE e UPDATE si usa executeUpdate, mentre per le SELECT si usa execute
        ps.executeUpdate();
    }
	
    public boolean deletePersonale(String matricola) throws SQLException {
        String query = "USE personale;"
        		+ "DELETE FROM Personale"
        		+ "WHERE matricola=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, matricola);
        return ps.executeUpdate() > 0;
        
    }
	

    
    
    
    
    
    
    
    
	/* VECCHIA CLASSE
	
    // Ricerca nel database i dati di una unità di personale 
	public Personale datiPersonale(String matricola) {
	    Personale personale = null;
	    PreparedStatement stat = null;
	    ResultSet result = null;
	    String nominativo;
	    LocalDate data_nascita;
	    String nome_dipartimento;
	    String qualifica;
	    double stipendio;
	    
	    try {
	        // Query con PreparedStatement per evitare SQL injection
	        String query = "SELECT Personale.matricola, Personale.nominativo, Personale.data_nascita, "
	        		+ "Personale.qualifica, Personale.stipendio, Dipartimenti.nome_dipartimento " +
	                       "FROM Personale " +
	                       "JOIN Dipartimenti ON Personale.id_dipartimento = Dipartimenti.id_dipartimento " +
	                       "WHERE Personale.matricola = ?";
	        
	        // Prepara la query
	        stat = conn.prepareStatement(query);
	        stat.setString(1, matricola);  // Imposta la matricola nella query
	        
	        // Esegui la query
	        result = stat.executeQuery();
	        
	        if (result.next()) {
	            nominativo = result.getString("nominativo");
	            data_nascita = result.getDate("data_nascita").toLocalDate();
	            nome_dipartimento = result.getString("nome_dipartimento");
	            qualifica = result.getString("qualifica");
	            stipendio = result.getDouble("stipendio");
	            
	            // Crea l'oggetto Personale
	            //personale = new Personale(matricola, nome_dipartimento, nominativo, qualifica, data_nascita, stipendio);
	        }
	        
	        return personale;
	    } catch (SQLException exception) {
	        exception.printStackTrace(); // Log per il debug
	        return null;
	    } finally {
	        // Chiudi le risorse
	        chiudiRisorse(result, stat);
	    }
	}


	// Ricerca nel database i dati di tutte le unità di personale
	public Personale[] elencoPersonale() {
		Personale elenco_personale[];
		Statement stat = null;
		ResultSet result = null;
		int numero_personale;
		String matricola;
		String nominativo;
		LocalDate data_nascita;
		String nome_dipartimento;
		String qualifica;
		double stipendio;
		try { // conteggio unità di personale presenti nel database
			stat = conn.createStatement();
			String query = "SELECT COUNT(*) AS numero FROM Personale;";
			result = stat.executeQuery(query);
			result.next();
			numero_personale = result.getInt(1);
			if (numero_personale > 0) {
				elenco_personale = new Personale[numero_personale];
			}
			else {
				return null;
			}
		}
		catch (SQLException exception) {
			return null;
		} 
		try {
			stat = conn.createStatement();
			String query = "SELECT * FROM Personale, Dipartimenti "
					+ "WHERE Personale.id_dipartimento = Dipartimenti.id_dipartimento;";
			result = stat.executeQuery(query);
			numero_personale = 0;
			while (result.next()) {
				matricola = result.getString("matricola");
				nominativo = result.getString("nominativo");
				data_nascita = result.getDate("data_nascita").toLocalDate();
				nome_dipartimento = result.getString("nome_dipartimento");
				qualifica = result.getString("qualifica");
				stipendio = result.getDouble("stipendio");
				elenco_personale[numero_personale] =
					//new Personale(matricola, nome_dipartimento, nominativo,
								  qualifica, data_nascita, stipendio);
				numero_personale++;
			}
			return elenco_personale;
		}
		catch (SQLException exception) {
			return null;
		} finally {
			try { 
				if ((result != null)) {
					result.close(); 
				}
			} catch (Exception exception) {};
			try { if (stat != null) stat.close(); } catch (Exception exception) {};
		 }
	}
	
	// Eliminazione unità di personale dal database
	public boolean eliminaPersonale(String matricola) {
	    PreparedStatement stat = null;
	    try {
	        String command = "DELETE FROM Personale WHERE matricola = ?";
	        stat = conn.prepareStatement(command);
	        stat.setString(1, matricola);

	        if (stat.executeUpdate() == 0) {
	            return false; // Nessuna riga eliminata
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace(); // Log dell'eccezione per debugging
	        return false;
	    } finally {
	        chiudiRisorse(stat); // Metodo per chiudere le risorse
	    }
	    return true;
	}
	
	
	private void chiudiRisorse(AutoCloseable... risorse) {
	    for (AutoCloseable risorsa : risorse) {
	        if (risorsa != null) {
	            try {
	                risorsa.close();
	            } catch (Exception eccezione) {
	                // Puoi aggiungere un log dell'errore se necessario
	                eccezione.printStackTrace();
	            }
	        }
	    }
	}
	*/
}

