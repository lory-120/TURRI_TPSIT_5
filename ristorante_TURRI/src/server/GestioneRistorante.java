package server;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import utilities.*;

public class GestioneRistorante {
	private ArrayList<Tavolo> tavoli;//tutti i tavoli
	private Semaphore mutex=new Semaphore(1);
	
	public GestioneRistorante() {// inizializzo l'arraylist
		tavoli=new ArrayList<>();
	}
	public GestioneRistorante(ArrayList<Tavolo> t) {
		tavoli=(ArrayList<Tavolo>)t.clone(); 
	}
	
	public String visualizza() {//metodo che rende la stringa in cui riassum,e l 'arrylist 
		String mess="";
		for(int i=0;i<tavoli.size();i++) {
			mess+=(i+1)+"["+tavoli.get(i).toString()+"]\n";
		}
		return mess;
	}
	public boolean prenotaTavolo(int posti,String nome) throws InterruptedException {//per prenotare il tavolo
		mutex.acquire();//lo faccio in sezione critica perchè devo modificare i dati quindi acquisisxo il semaforo
		int ind;
		Tavolo prenotato=null;
		for(Tavolo t :tavoli ) {
			if(!t.isPrenotato()&&t.getPosti()>=posti) {//controlo che il tavolo sia libero e che abbia bbastanza i posti
				if(prenotato==null ||(t.getPosti()-posti)<(prenotato.getPosti()-posti)) {// verifico che non sia la prima scelta e in piu controllo che la diferneza dia minore di quello che ho salvato per cambiarlo
					prenotato=t;
				}
			}
		}
		if(prenotato!=null){
			prenotato.prenota(nome);//prenoto il tavoo piu vicino al numero di posti
			mutex.release();//rilascio il semaforo
			return true;
		}else {
			mutex.release();//rilascio il semaforo
			return false;
		}
		
	}
	public boolean liberaTavolo(int ind) {//metodo libera tavolo
		if(tavoli.size()<=(ind-1)) {//contorllo che l indice rientri in quelli disponibili
			return false;
		}else {
			tavoli.get(ind-1).libera();//se esiste libero il tavolo
			return true;
		}
	}
	public boolean aggiungiTavolo(int posti) {//aggiungi tavolo
		if(posti>0) {//controllo che i posti siano positivi se no non puo  esistere
			tavoli.add(new Tavolo(posti));//aggiungo un tavolo 
			return true;
		}else {
			return false;
		}
	}
}
