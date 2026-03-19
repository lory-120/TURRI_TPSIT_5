package dao;

import java.util.ArrayList;

import model.Ticket;

public class AccessoTicket {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	public static void aggiungiTicket(Ticket t) {
        tickets.add(t);
    }
	
	public static boolean segnaFatto(int ID) {
		for(Ticket t : tickets) {
			if(t.getID() == ID) {
				t.setDone(true);
				return true;
			}
		}
		return false;
	}

    public static ArrayList<Ticket> getTuttiITicket() {
        return tickets;
    }
    
    protected static int getLastTicket() {
    		int tmp = 0;
    		for(Ticket t : tickets) {
    			if(t.getID() > tmp) {
    				tmp = t.getID();
    			}
    		}
    		return tmp;
    }
	
}
