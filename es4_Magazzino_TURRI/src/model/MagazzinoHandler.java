package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MagazzinoHandler {
	private ArrayList<Prodotto> prodotti=new ArrayList<>();
	private String filePath;
	public MagazzinoHandler(String filePath) {
		this.filePath=filePath;
		try {
			caricaProdottiDaFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void caricaProdottiDaFile() throws IOException, NumberFormatException {
		BufferedReader read=new BufferedReader(new FileReader(filePath));
		String app=null;
		Prodotto p=null;
		do {
			app=read.readLine();
			if(app!=null) {
				String campi[]=app.split(";");
				p=new Prodotto(campi);
			}
			prodotti.add(p);
		} while(app!=null);
		System.out.println("Lettura terminata!!");
		read.close();
	}
//	private void caricaProdottiDaFile() throws IOException {
//		try (BufferedReader letturFile = new BufferedReader(new FileReader(filePath))) {
//			String rigaLetta;
//			while ((rigaLetta = letturFile.readLine()) != null) {
//				rigaLetta = rigaLetta.trim();
//			    if (rigaLetta.isEmpty()) continue; // salta righe vuote
//			    String[] campi = rigaLetta.split(";");
//			    if (campi.length < 2) {
//			    	throw new IOException("Riga malformata: " + rigaLetta);
//			    }
//			    Prodotto p = new Prodotto(campi);
//			    prodotti.add(p);
//			}
//		} catch (NumberFormatException e) {
//			// Ts
//		}
//	    System.out.println("Lettura terminata!!");
//	 }

	//metodo aggiorna quantità
		public synchronized boolean ordinaProdotto (int codiceProdotto, int quantita) throws IOException {
			for (Prodotto p: prodotti) {
				if(p.getCODICE()==codiceProdotto && p.getQuantita()>=quantita) {
					p.setQuantita(p.getQuantita()-quantita);
					return true;
				}
			}
			salvaSuFile();
			return false;
		}
		//metodo visualizza quantità
		public synchronized int getQuantita(int codiceProdotto) {
			for (Prodotto p: prodotti) {
				if(p.getCODICE()==codiceProdotto) {
					return p.getQuantita();
				}
			}
			throw new IllegalStateException("Prodotto non disponibile!");
		}
		
		private void salvaSuFile() throws IOException {
		    try (FileWriter writer = new FileWriter(filePath);
		         BufferedWriter bw = new BufferedWriter(writer)) {
		        for (Prodotto p : prodotti) {
		            bw.write(p.getCODICE() + ";" + p.getQuantita());
		            bw.newLine();
		        }
		    }
		}
}
