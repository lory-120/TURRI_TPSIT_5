package it.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class Catalogo {

	public static final Map<String, Articolo> ARTICOLI = new HashMap<>();
	
	public static void caricaArticoli() {
		ARTICOLI.put("A01", new Articolo("A01", "Libro Java", 19.90));
		ARTICOLI.put("A02", new Articolo("A02", "Tazza arancione", 5.00));
		ARTICOLI.put("A03", new Articolo("A03", "Gioco", 20.00));
		ARTICOLI.put("A04", new Articolo("A04", "Servlet", 100.00));
	}
}
