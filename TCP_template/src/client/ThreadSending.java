package client;

import java.io.PrintWriter;
import presentation.Tastiera;

public class ThreadSending extends Thread{
	
	private boolean eoR; //end of reading ----> controlla se la lettura è finita
	private PrintWriter out;
	
	public ThreadSending(PrintWriter out) {
		this.out=out;
		this.eoR=false;
	}
	
	@Override
	public void run() {
		while(!eoR) {
			String messaggio = Tastiera.leggiString("Input: ");
			if(messaggio.equalsIgnoreCase("Exit")) {
				eoR=true;
			} else {
				try {
					System.out.println("Sent: "+messaggio);
				    out.println(messaggio);
				} catch(IllegalStateException e) {
					System.out.println(e.getMessage());
					eoR=true;
				}
			}
			
		}
	}
	
	public void close() {
		eoR = true;
	}

}
