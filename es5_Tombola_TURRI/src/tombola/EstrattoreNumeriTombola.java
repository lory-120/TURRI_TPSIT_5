package tombola;

import java.util.ArrayList;
import java.util.Collections;

public class EstrattoreNumeriTombola {

	private ArrayList<Integer> numeri;
	
	public EstrattoreNumeriTombola() {
		numeri = new ArrayList<Integer>(90);
		for(int i=0; i<numeri.size(); i++) {
			numeri.add(i, i+1);
		}
		Collections.shuffle(numeri);
	}
	
	public int estraiNumero() {
		return (numeri.isEmpty()) ? -1 : numeri.remove(0);
	}
	
}
