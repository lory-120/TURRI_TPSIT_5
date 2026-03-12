package model;

import java.util.ArrayList;

public class AccessoTicket {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	public static void aggiungiTicket(Ticket t) {
        tickets.add(t);
    }
	
	public static boolean segnaFatto(Ticket t) {
		for(Ticket tt : tickets) {
			if(tt.equals(t)) {
				tt.setDone(true);
				return true;
			}
		}
		return false;
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
