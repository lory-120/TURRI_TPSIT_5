package model;

import java.sql.*;
import java.time.LocalDate;

import dao.GestoreDB;

public class GestionePersonale {
	Connection con = null;
	// parametri connessione
	final String server = "localhost";
	final String database = "Azienda";
	final String utente = "root";
	final String password = "12345678";
	
	public GestionePersonale() throws SQLException {
		con = new GestoreDB(server, database, utente, password).getConnection();
	}
	
	public boolean close() {
		try {
			con.close();
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
	
/* Inserimento unità di personale nel database */
	public boolean aggiungiPersonale(Personale personale) {
		Statement stat = null;
		ResultSet result = null;
		String id_dipartimento;
		String data_nascita;
		
		try { 
			// Per aggiungere un membro del personale dovrò controllare qual è l'ID del  
			//dipartimento di appartenenza
			stat = con.createStatement();
			String query = "SELECT id_dipartimento FROM Dipartimenti WHERE nome_dipartimento = '" + personale.getDipartimento() + "';";
			result = stat.executeQuery(query);
			result.next();
			id_dipartimento = result.getString(1);
			}
		catch (SQLException exception) {
			return false;
		} 
		try {
			stat = con.createStatement();
			data_nascita = personale.getDataNascita().toString();
			String command = " INSERT INTO Personale(matricola,id_dipartimento, nominativo, data_nascita, qualifica, stipendio)" +
							 "VALUES ('" + personale.getMatricola() + "', '" +
											id_dipartimento + "', '" +
											personale.getNominativo() + "', '" +
											data_nascita + "', '" +
											personale.getQualifica() + "', " +
											personale.getStipendio() + ");";
			if (stat.executeUpdate(command) == 0) {
				return false;
			}
		} catch (SQLException exception) {
			return false;
		}
		return true;
	}
	
	public boolean aggiungiPersonalePrepared(Personale personale) {
	    PreparedStatement stat = null;
	    ResultSet result = null;
	    String id_dipartimento;

	    try {
	        // Recupera l'ID del dipartimento in base al nome del dipartimento
	        String query = "SELECT id_dipartimento FROM Dipartimenti WHERE nome_dipartimento = ?";
	        stat = con.prepareStatement(query);
	        stat.setString(1, personale.getDipartimento());
	        result = stat.executeQuery();

	        if (result.next()) {
	            id_dipartimento = result.getString(1);
	        } else {
	            // Dipartimento non trovato
	            return false;
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace();
	        return false;
	    } 
	    try {
	        // Inserisce il nuovo personale nel database
	        String insert = "INSERT INTO Personale(matricola, id_dipartimento, nominativo, data_nascita, qualifica, stipendio) " +
	                        "VALUES (?, ?, ?, ?, ?, ?)";
	        stat = con.prepareStatement(insert);
	        stat.setString(1, personale.getMatricola());
	        stat.setString(2, id_dipartimento);
	        stat.setString(3, personale.getNominativo());
	        stat.setDate(4, java.sql.Date.valueOf(personale.getDataNascita())); // Assumendo che `getDataNascita` restituisca un `LocalDate`
	        stat.setString(5, personale.getQualifica());
	        stat.setDouble(6, personale.getStipendio());

	        if (stat.executeUpdate() == 0) {
	            return false;
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace();
	        return false;
	    }

	    return true;
	}

/* Aggiornamento dati unità di personale nel database */
	public boolean aggiornaPersonale(Personale personale) {
	    PreparedStatement stat = null;
	    ResultSet result = null;
	    String id_dipartimento;
	    String data_nascita;
	    
	    try {
	        // Verifica se l'unità di personale esiste nel database
	        String queryVerifica = "SELECT COUNT(*) AS numero FROM Personale WHERE matricola = ?";
	        stat = con.prepareStatement(queryVerifica);
	        stat.setString(1, personale.getMatricola());
	        result = stat.executeQuery();
	        
	        if (result.next() && result.getInt("numero") == 0) {
	            return false; // Se il numero è 0, l'unità di personale non esiste
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace(); // Log dell'errore per il debug
	        return false;
	    } finally {
	        chiudiRisorse(result, stat);
	    }

	    try {
	        // Recupera l'id_dipartimento a partire dal nome del dipartimento
	        String queryDipartimento = "SELECT id_dipartimento FROM Dipartimenti WHERE nome_dipartimento = ?";
	        stat = con.prepareStatement(queryDipartimento);
	        stat.setString(1, personale.getDipartimento());
	        result = stat.executeQuery();
	        
	        if (result.next()) {
	            id_dipartimento = result.getString("id_dipartimento");
	        } else {
	            return false; // Se non trova il dipartimento, ritorna false
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace(); // Log dell'errore per il debug
	        return false;
	    } finally {
	        chiudiRisorse(result, stat);
	    }

	    try {
	        // Esegui l'aggiornamento dei dati nel database settando nuovi risultati
	        String queryUpdate = "UPDATE Personale SET id_dipartimento = ?, nominativo = ?, "
	        		+ "data_nascita = ?, qualifica = ?, stipendio = ? WHERE matricola = ?";
	        stat = con.prepareStatement(queryUpdate);
	        stat.setString(1, id_dipartimento);
	        stat.setString(2, personale.getNominativo());
	        stat.setString(3, personale.getDataNascita().toString());
	        stat.setString(4, personale.getQualifica());
	        stat.setDouble(5, personale.getStipendio());
	        stat.setString(6, personale.getMatricola());

	        if (stat.executeUpdate() == 0) {
	            return false; // Se non viene aggiornata alcuna riga
	        }
	    } catch (SQLException exception) {
	        exception.printStackTrace(); // Log dell'errore per il debug
	        return false;
	    } finally {
	        chiudiRisorse(result, stat);
	    }

	    return true; // Dati aggiornati con successo
	}

	
	
/* Ricerca nel database i dati di una unità di personale */
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
	        stat = con.prepareStatement(query);
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
	            personale = new Personale(matricola, nome_dipartimento, nominativo, qualifica, data_nascita, stipendio);
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


/* Ricerca nel database i dati di tutte le unità di personale */
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
			stat = con.createStatement();
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
			stat = con.createStatement();
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
					new Personale(matricola, nome_dipartimento, nominativo,
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
	
/* Eliminazione unità di personale dal database */
	public boolean eliminaPersonale(String matricola) {
	    PreparedStatement stat = null;
	    try {
	        String command = "DELETE FROM Personale WHERE matricola = ?";
	        stat = con.prepareStatement(command);
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
}

