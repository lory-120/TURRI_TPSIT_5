package dao;

public class GeneratoreID {

	private static int currentID = AccessoTicket.getLastTicket() + 1;
	
	public static int getNewID() {
		currentID++;
		return currentID;
	}
	
}
