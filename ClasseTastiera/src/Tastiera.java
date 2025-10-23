import java.io.*;

public class Tastiera {
	
	private static InputStreamReader reader = new InputStreamReader(System.in);
	private static BufferedReader tastiera = new BufferedReader(reader);
	
	//legge i caratteri
	public static char leggiChar() {
		String stringaLetta = null;
		try {
			stringaLetta = tastiera.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return stringaLetta.charAt(0);
	}
	public static char leggiChar(String msg) {
		System.out.print(msg);
		
		String stringaLetta = null;
		try {
			stringaLetta = tastiera.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return stringaLetta.charAt(0);
	}
	
	//legge le stringhe
	public static String leggiString(String msg) {
		System.out.print(msg);
		
		String stringaLetta = null;
		try {
			stringaLetta = tastiera.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return stringaLetta;
	}
	public static String leggiString() {
		String stringaLetta = null;
		try {
			stringaLetta = tastiera.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return stringaLetta;
	}
	
	//legge gli interi
	public static int leggiInt() {
		String stringaLetta = null;
		int n = 0;
		
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Integer.parseInt(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
				System.out.println("Errore nel leggere l'intero. (1)");
			} catch(IOException e2) {
				e2.printStackTrace();
				System.out.println("Errore nel leggere l'intero. (2)");
			}
		} while(stringaLetta == null);
		
		return n;
	}
	public static int leggiInt(String msg) {
		String stringaLetta = null;
		int n = 0;
		
		System.out.print(msg);
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Integer.parseInt(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		} while(stringaLetta == null);
		
		return n;
	}
	
	//leggi i double
	public static double leggiDouble() {
		String stringaLetta = null;
		double n = 0;
		
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Double.parseDouble(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		} while(stringaLetta == null);
		
		return n;
	}
	public static double leggiDouble(String msg) {
		String stringaLetta = null;
		double n = 0;
		
		System.out.print(msg);
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Double.parseDouble(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		} while(stringaLetta == null);
		
		return n;
	}
	
	//leggi i float
	public static float leggiFloat() {
		String stringaLetta = null;
		float n = 0;
		
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Float.parseFloat(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		} while(stringaLetta == null);
		
		return n;
	}
	public static float leggiFloat(String msg) {
		String stringaLetta = null;
		float n = 0;
		
		System.out.print(msg);
		do {
			try {
				stringaLetta = tastiera.readLine();
				n = Float.parseFloat(stringaLetta);
			} catch(NumberFormatException e1) {
				stringaLetta = null;
				e1.printStackTrace();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		} while(stringaLetta == null);
		
		return n;
	}
	
}
