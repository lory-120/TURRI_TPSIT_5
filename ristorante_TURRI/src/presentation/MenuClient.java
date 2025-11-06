package presentation;

import utilities.*;
import client.*;

public class MenuClient {
	private Client c;
	
	public MenuClient(Client c) {
		this.c=c;
	}
	
	private void stampaMenu() {//menu
		System.out.println("----Menu----"
				+ "\n1. visualizza tavoli"
				+ "\n2. prenota tavolo"
				+ "\n3. libera tavolo"
				+ "\n4. inserisci tavolo"			
				+ "\n5. Esci");
	}
	
	public void eseguiMenu() {
		boolean esci=false;
		while(!esci) {
			stampaMenu();
			int scelta=Tastiera.leggiInt("Immetti scelta:");
			switch(scelta) {
				case 1 ->visualizza();
				case 2 ->prenota();
				case 3 ->libera();
				case 4 ->inserisci();
				case 5 -> {
					chiudi();
					System.out.println("Arrivederci!");
					esci=true;
				}
			}
		}
	}
	
	private void visualizza() {
		try {
			ProtocolloInv p=new ProtocolloInv("vis");
			c.invia(p.toString());
			System.out.println(c.ricevi());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void prenota() {
		String nomePrenotazione=Tastiera.leggiString("Inserisci prenotazione");
		int posti;
		do{
			posti=Tastiera.leggiInt("inserisci il numero di posti al tavolo");
			if(posti<=0) {
				System.out.println("i posti devono essere maggiri di 0");
			}
		}while(posti<=0);
		try {
		ProtocolloInv p=new ProtocolloInv("pre",posti,nomePrenotazione);
			c.invia(p.toString());
			System.out.println(c.ricevi());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void libera() {
		int ind;
		do{
			ind=Tastiera.leggiInt("indica il numero del tavolo che vuoi modificare");	
			if(ind<=0) {
				System.out.println("i posti devono essere maggiri di 0");
			}
		}while(ind<0);
		try {
			ProtocolloInv p=new ProtocolloInv("lib",ind);//creo il pacchetto da inviare
				c.invia(p.toString());
				System.out.println(c.ricevi());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	private void inserisci() {
		
		int posti;
		do{
			posti=Tastiera.leggiInt("inserisci il numero di posti al tavolo");
			if(posti<=0) {
				System.out.println("i posti devono essere maggiri di 0");
			}
		}while(posti<=0);
		try {
		ProtocolloInv p=new ProtocolloInv("add",posti);//creo il pacchetto da inviare
			c.invia(p.toString());
			System.out.println(c.ricevi());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void chiudi() {
		try {
				ProtocolloInv p=new ProtocolloInv("clo");
				System.out.println("ciao");
				c.invia(p.toString());
				System.out.println(c.ricevi());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
