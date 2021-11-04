package project2002;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import project2002.Restaurant.handlerType;

public class ReservationHandler extends Handler {

	public ReservationHandler() {
		type = handlerType.RESERVATION;
	}

	public boolean checkReservation(Customer cust, int pax, LocalDateTime time) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeReservation(Customer cust, int pax, LocalDateTime time) {
		// TODO Auto-generated method stub

	}

	public boolean addReservation(Customer cust, int pax, LocalDateTime date, LocalDateTime time) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeReservation(Customer cust, int pax, LocalDateTime date, LocalDateTime time) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkReservation(Customer cust, int pax, LocalDateTime date, LocalDateTime time) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addNewTable(int pax) {
		// TODO Auto-generated method stub

	}

	public void removeTable(int pax) {
		// TODO Auto-generated method stub

	}

	public boolean updateTable(int pax, int pax2) {
		return false;
		// TODO Auto-generated method stub

	}

	public ArrayList<Reservation> retrieveNextReservationList(LocalDateTime time) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Reservation> retrieveBeforeReservationList(LocalDateTime time) {
		// TODO Auto-generated method stub
		return null;
	}

}
