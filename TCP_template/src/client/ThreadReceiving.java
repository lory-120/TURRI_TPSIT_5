package client;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadReceiving extends Thread {
	
	private BufferedReader in;
	private boolean eoR; //end of reading
	
	public ThreadReceiving(BufferedReader in) {
		this.in=in;
		this.eoR=false;
	}
	
	@Override
	public void run() {
		while(!eoR) {
			try {
				String msg=in.readLine();
				System.out.println("Received: "+msg);
				
			} catch (IOException e) {
				eoR=true;
				e.printStackTrace();
			}


		}
	
	}
	
	public void close() {
		eoR = true;
	}
	
}
