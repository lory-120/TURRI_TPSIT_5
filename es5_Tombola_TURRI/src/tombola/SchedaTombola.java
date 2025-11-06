package tombola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class SchedaTombola implements Serializable {

	private static final int DIM_SCHEDA = 15;
	private ArrayList<Integer> numeri;
	private final ArrayList<Integer> numeriIniziali;
	
	public SchedaTombola() {
		numeri = generaScheda();
		numeriIniziali = numeri;
	}
	
	//metodi get/set
	public ArrayList<Integer> getNumeri() {
		return numeri;
	}

	public void setNumeri(ArrayList<Integer> numeri) {
		this.numeri = numeri;
	}

	public ArrayList<Integer> getNumeriIniziali() {
		return numeriIniziali;
	}
	
	
	//metodi della funzione
	//segna un numero, se essite
	public boolean segnaNumero(int n) {
		return numeri.remove((Integer)n);
	}

	//genera una scheda di numeri
	private static ArrayList<Integer> generaScheda() {
		ArrayList<Integer> numeriTot = new ArrayList<Integer>(90);
		
		for(int i=0; i<numeriTot.size(); i++) {
			numeriTot.add(i, i+1);
		}
		
		Collections.shuffle(numeriTot);
		
		return (ArrayList<Integer>)numeriTot.subList(0, DIM_SCHEDA);
	}
	
}
