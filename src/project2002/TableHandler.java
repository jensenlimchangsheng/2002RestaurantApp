package project2002;

import java.util.ArrayList;

import project2002.Restaurant.handlerType;

public class TableHandler extends Handler {
	
	public TableHandler(){
		type=handlerType.TABLE;
	}

	public String assignTable(int pax) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkAvailability(int pax) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addNewTable(int pax) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeTable(int pax) {
		// TODO Auto-generated method stub
		return false;
	}

	public int updateTable(int tableID, int pax) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean reserveTables(ArrayList<Reservations> reservationList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeReservations(ArrayList<Reservations> reservationList) {
		// TODO Auto-generated method stub
		return false;
	}

	public void printAvailableTablesNow() {
		// TODO Auto-generated method stub
		
	}

}
